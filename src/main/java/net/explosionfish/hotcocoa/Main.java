package net.explosionfish.hotcocoa;

import net.explosionfish.hotcocoa.items.ItemBucketCocoa;
import net.explosionfish.hotcocoa.items.ItemMug;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.registry.GameRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@Mod(
        modid = Main.MOD_ID,
        name = Main.MOD_NAME,
        version = Main.VERSION,
        acceptedMinecraftVersions = Main.MC_VERSION
)
public class Main {

    public static final String MOD_ID = "hotcocoa";
    public static final String MOD_NAME = "Hot Cocoa";
    public static final String VERSION = "1.3";
    public static final String MC_VERSION = "[1.12.2]";

    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

    public static final CreativeTabs COCOA_TAB = new CocoaTab();

    public static final PotionEffect SPEED = new PotionEffect(Potion.getPotionById(1), 60*20);
    public static final PotionEffect SPEED2 = new PotionEffect(Potion.getPotionById(1), 120*20);

    /**
     * This is the instance of your mod as created by Forge. It will never be null.
     */
    @Mod.Instance(MOD_ID)
    public static Main INSTANCE;

    /**
     * This is the first initialization event. Register tile entities here.
     * The registry events below will have fired prior to entry to this method.
     */
    @Mod.EventHandler
    public void preinit(FMLPreInitializationEvent event) {

    }

    /**
     * This is the second initialization event. Register custom recipes
     */
    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        LOGGER.info("Have a sip!");
        ModRecipes.initSmelting();
    }

    /**
     * This is the final initialization event. Register actions from other mods here
     */
    @Mod.EventHandler
    public void postinit(FMLPostInitializationEvent event) {

    }

    /**
     * Forge will automatically look up and bind blocks to the fields in this class
     * based on their registry name.
     */
    @GameRegistry.ObjectHolder(MOD_ID)
    public static class Blocks {
      /*
          public static final MySpecialBlock mySpecialBlock = null; // placeholder for special block below
      */
    }

    /**
     * Forge will automatically look up and bind items to the fields in this class
     * based on their registry name.
     */
    @GameRegistry.ObjectHolder(MOD_ID)
    public static class Items {
        public static final Item HOT_MILK_BUCKET = null;

        public static final Item HOT_COCOA_BUCKET = null;
        public static final Item HOT_COCOA_MUG = null;

        public static final Item MARSHMALLOWS = null;
        public static final Item MARSHMALLOW_COCOA_BUCKET = null;
        public static final Item MARSHMALLOW_COCOA_MUG = null;
    }

    /**
     * This is a special class that listens to registry events, to allow creation of mod blocks and items at the proper time.
     */
    @Mod.EventBusSubscriber
    public static class ObjectRegistryHandler {
        /**
         * Listen for the register event for creating custom items
         */
        @SubscribeEvent
        public static void addItems(RegistryEvent.Register<Item> event) {
            final Item[] items = {
                    new ItemBucketCocoa(2, true).setRegistryName(MOD_ID, "hot_milk_bucket").setTranslationKey(MOD_ID + "." + "hot_milk_bucket"),

                    new ItemBucketCocoa(6, false).setRegistryName(MOD_ID, "hot_cocoa_bucket").setTranslationKey(MOD_ID + "." + "hot_cocoa_bucket"),
                    new ItemMug(4).setRegistryName(MOD_ID, "hot_cocoa_mug").setTranslationKey(MOD_ID + "." + "hot_cocoa_mug"),

                    new ItemFood(1, false).setRegistryName(MOD_ID, "marshmallows").setTranslationKey(MOD_ID + "." + "marshmallows").setCreativeTab(COCOA_TAB),
                    new ItemBucketCocoa(6, SPEED2).setRegistryName(MOD_ID, "marshmallow_cocoa_bucket").setTranslationKey(MOD_ID + "." + "hot_cocoa_bucket"),
                    new ItemMug(4, SPEED).setRegistryName(MOD_ID, "marshmallow_cocoa_mug").setTranslationKey(MOD_ID + "." + "marshmallow_cocoa_mug")
            };

            event.getRegistry().registerAll(items);
        }

        /**
         * Listen for the register event for creating custom blocks
         */
        @SubscribeEvent
        public static void addBlocks(RegistryEvent.Register<Block> event) {
           /*
             event.getRegistry().register(new MySpecialBlock().setRegistryName(MOD_ID, "mySpecialBlock"));
            */
        }
    }
}
