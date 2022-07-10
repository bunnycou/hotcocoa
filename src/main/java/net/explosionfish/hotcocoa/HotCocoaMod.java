package net.explosionfish.hotcocoa;

import net.explosionfish.hotcocoa.item.ItemBucket;
import net.explosionfish.hotcocoa.item.ItemMilk;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HotCocoaMod implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("hotcocoa");

	// hot cocoa item tab
	public static final ItemGroup Cocoa_Group = FabricItemGroupBuilder.create(new Identifier("hotcocoa"))
			.icon(() -> new ItemStack(HotCocoaMod.HOT_COCOA_BUCKET))
			.build();

	// milk
	public static final ItemMilk HOT_MILK_BUCKET = new ItemMilk(new FabricItemSettings().group(Cocoa_Group)
			.maxCount(1)
			.food(new FoodComponent.Builder()
					.hunger(2)
					.alwaysEdible()
					.build()));

	// hot cocoa
	public static final ItemBucket HOT_COCOA_BUCKET = new ItemBucket(new FabricItemSettings().group(Cocoa_Group)
			.maxCount(1)
			.recipeRemainder(Items.BUCKET)
			.food(new FoodComponent.Builder()
					.hunger(6)
					.build()));
	public static final Item HOT_COCOA_MUG = new Item(new FabricItemSettings().group(Cocoa_Group)
			.food(new FoodComponent.Builder()
					.hunger(3)
					.build()));

	// marshmallows
	public static final Item MARSHMALLOWS = new Item(new FabricItemSettings().group(Cocoa_Group)
			.food(new FoodComponent.Builder()
					.hunger(1)
					.snack()
					.statusEffect(new StatusEffectInstance(StatusEffects.SPEED, 30*20, 1), 0.5f)
					.build()));

	public static final ItemBucket MARSHMALLOW_COCOA_BUCKET = new ItemBucket(new FabricItemSettings().group(Cocoa_Group)
			.maxCount(1)
			.recipeRemainder(Items.BUCKET)
			.food(new FoodComponent.Builder()
					.hunger(6)
					.alwaysEdible()
					.statusEffect(new StatusEffectInstance(StatusEffects.SPEED, 120*20, 2), 1)
					.build()));
	public static final Item MARSHMALLOW_COCOA_MUG = new Item(new FabricItemSettings().group(Cocoa_Group)
			.food(new FoodComponent.Builder()
					.hunger(3)
					.alwaysEdible()
					.statusEffect(new StatusEffectInstance(StatusEffects.SPEED, 60*20, 1), 1)
					.build()));


	@Override
	public void onInitialize() {
		LOGGER.info("Heating up a mug of hot cocoa");

		// register items
		// Buckets
		Registry.register(Registry.ITEM, new Identifier("hotcocoa", "hot_milk_bucket"), HOT_MILK_BUCKET);
		Registry.register(Registry.ITEM, new Identifier("hotcocoa", "hot_cocoa_bucket"), HOT_COCOA_BUCKET);
		Registry.register(Registry.ITEM, new Identifier("hotcocoa", "marshmallow_cocoa_bucket"), MARSHMALLOW_COCOA_BUCKET);

		// Mugs
		Registry.register(Registry.ITEM, new Identifier("hotcocoa", "hot_cocoa_mug"), HOT_COCOA_MUG);
		Registry.register(Registry.ITEM, new Identifier("hotcocoa", "marshmallow_cocoa_mug"), MARSHMALLOW_COCOA_MUG);

		// Other
		Registry.register(Registry.ITEM, new Identifier("hotcocoa", "marshmallows"), MARSHMALLOWS);

	}
}
