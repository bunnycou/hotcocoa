package net.explosionfish.hotcocoa;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModRecipes {
    public static void initSmelting() {
        GameRegistry.addSmelting(Items.MILK_BUCKET, new ItemStack(Main.Items.HOT_MILK_BUCKET), 0F);

        GameRegistry.addSmelting(Items.SUGAR, new ItemStack(Main.Items.MARSHMALLOWS), 0F);
    }
}
