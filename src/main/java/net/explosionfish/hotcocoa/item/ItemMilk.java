package net.explosionfish.hotcocoa.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.World;

public class ItemMilk extends Item {
    public ItemMilk(Settings settings) {
        super(settings);
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        user.clearStatusEffects();
        return new ItemStack(Items.BUCKET, 1);
    }
}
