package net.explosionfish.hotcocoa.items;


import net.explosionfish.hotcocoa.Main;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class ItemBucketCocoa extends Item {

    /** Number of ticks to run while 'EnumAction'ing until result. */
    public final int itemUseDuration;

    /** The amount this food item heals the player. */
    private final int healAmount;
    private final float saturationModifier;

    /**
     * represents the potion effect that will occurr upon eating this food. Set by setPotionEffect
     */
    private final PotionEffect potionId;

    /** Whether or not status effects get cleared */
    private final boolean clearEffects;

    public ItemBucketCocoa(int amount, PotionEffect id)
    {
        this.itemUseDuration = 32;
        this.healAmount = amount;
        this.saturationModifier = 0.6F;
        this.potionId = id;
        this.clearEffects = false;
        this.setMaxStackSize(1);
        this.setCreativeTab(Main.COCOA_TAB);
    }
    public ItemBucketCocoa(int amount, boolean milk)
    {
        this.itemUseDuration = 32;
        this.healAmount = amount;
        this.saturationModifier = 0.6F;
        this.potionId = null;
        this.clearEffects = milk;
        this.setMaxStackSize(1);
        this.setCreativeTab(Main.COCOA_TAB);
    }

    /**
     * Called when the player finishes using this Item (E.g. finishes eating.).
     * Not called when the player stops using the Item before the action is complete.
     */
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving)
    {
        if (entityLiving instanceof EntityPlayerMP)
        {
            EntityPlayerMP entityplayermp = (EntityPlayerMP)entityLiving;
            CriteriaTriggers.CONSUME_ITEM.trigger(entityplayermp, stack);
        }

        if (!worldIn.isRemote && this.clearEffects)
        {
            entityLiving.clearActivePotions();
        }

        if (entityLiving instanceof EntityPlayer && !((EntityPlayer)entityLiving).capabilities.isCreativeMode)
        {
            EntityPlayer entityplayer = (EntityPlayer) entityLiving;
            entityplayer.getFoodStats().addStats(this.healAmount, this.saturationModifier);
            worldIn.playSound((EntityPlayer) null, entityplayer.posX, entityplayer.posY, entityplayer.posZ, SoundEvents.ENTITY_PLAYER_BURP, SoundCategory.PLAYERS, 0.5F, worldIn.rand.nextFloat() * 0.1F + 0.9F);
            this.onFoodEaten(stack, worldIn, entityplayer);
            stack.shrink(1);

            if (entityplayer instanceof EntityPlayerMP) {
                CriteriaTriggers.CONSUME_ITEM.trigger((EntityPlayerMP) entityplayer, stack);
            }
        }

        return stack.isEmpty() ? new ItemStack(Items.BUCKET) : stack;

    }

    /**
     * How long it takes to use or consume an item
     */
    public int getMaxItemUseDuration(ItemStack stack)
    {
        return 32;
    }

    /**
     * returns the action that specifies what animation to play when the items is being used
     */
    public EnumAction getItemUseAction(ItemStack stack)
    {
        return EnumAction.DRINK;
    }

    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
        playerIn.setActiveHand(handIn);
        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
    }

    protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player)
    {
        if (!worldIn.isRemote && this.potionId != null)
        {
            player.addPotionEffect(new PotionEffect(this.potionId));
        }
    }
}
