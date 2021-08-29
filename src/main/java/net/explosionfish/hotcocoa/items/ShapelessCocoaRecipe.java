package net.explosionfish.hotcocoa.items;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.IRecipeFactory;
import net.minecraftforge.common.crafting.JsonContext;
import net.minecraftforge.oredict.ShapelessOreRecipe;

import javax.annotation.Nullable;

// code for shapeless processing taken from someone else many many months ago and I can't remember who sorry and slightly modified

public class ShapelessCocoaRecipe extends ShapelessOreRecipe {

    public ShapelessCocoaRecipe(@Nullable ResourceLocation group, final NonNullList<Ingredient> input, final ItemStack result) {
        super(group, input, result);
    }

    @Override
    public NonNullList<ItemStack> getRemainingItems(final InventoryCrafting inventoryCrafting) {
        final NonNullList<ItemStack> remainingItems = NonNullList.withSize(inventoryCrafting.getSizeInventory(), ItemStack.EMPTY);

        for (int i = 0; i < remainingItems.size(); ++i) {
            final ItemStack itemstack = inventoryCrafting.getStackInSlot(i);

            if (!itemstack.isEmpty() && itemstack.getItem() instanceof ItemBucketCocoa && !(super.getRecipeOutput().getItem() instanceof ItemBucketCocoa)) {
                remainingItems.set(i, new ItemStack(Items.BUCKET));
            } else {
                remainingItems.set(i, ForgeHooks.getContainerItem(itemstack));
            }
        }
        return remainingItems;
    }

    @Override
    public String getGroup() {
        return group == null ? "" : group.toString();
    }

    public static class Factory implements IRecipeFactory {

        @Override
        public IRecipe parse(JsonContext context, JsonObject json) {
            final String group = JsonUtils.getString(json, "group", "");
            final NonNullList<Ingredient> ingredients = this.parseShapeless(context, json);
            final ItemStack result = CraftingHelper.getItemStack(JsonUtils.getJsonObject(json, "result"), context);

            return new ShapelessCocoaRecipe(group.isEmpty() ? null : new ResourceLocation(group), ingredients, result);
        }

        public static NonNullList<Ingredient> parseShapeless(final JsonContext context, final JsonObject json) {
            final NonNullList<Ingredient> ingredients = NonNullList.create();
            for (final JsonElement element : JsonUtils.getJsonArray(json, "ingredients"))
                ingredients.add(CraftingHelper.getIngredient(element, context));

            if (ingredients.isEmpty())
                throw new JsonParseException("No ingredients for shapeless recipe");

            return ingredients;
        }
    }
}
