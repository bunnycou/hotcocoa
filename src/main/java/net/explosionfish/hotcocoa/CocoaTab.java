package net.explosionfish.hotcocoa;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class CocoaTab extends CreativeTabs {

    public CocoaTab() {
        super(Main.MOD_ID);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public ItemStack createIcon() {
        return new ItemStack(Main.Items.HOT_COCOA_BUCKET);
    }
}
