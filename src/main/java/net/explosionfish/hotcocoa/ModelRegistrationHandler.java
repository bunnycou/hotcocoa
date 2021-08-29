package net.explosionfish.hotcocoa;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(value = Side.CLIENT, modid = Main.MOD_ID)
public class ModelRegistrationHandler {

    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
        registerModel(Main.Items.HOT_MILK_BUCKET, 0);

        registerModel(Main.Items.HOT_COCOA_BUCKET, 0);
        registerModel(Main.Items.HOT_COCOA_MUG, 0);

        registerModel(Main.Items.MARSHMALLOWS, 0);
        registerModel(Main.Items.MARSHMALLOW_COCOA_BUCKET, 0);
        registerModel(Main.Items.MARSHMALLOW_COCOA_MUG, 0);
    }

    private static void registerModel(Item item, int meta) {
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(item.getRegistryName(), "inventory"));
    }
}
