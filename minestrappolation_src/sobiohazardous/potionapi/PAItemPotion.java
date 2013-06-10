package sobiohazardous.potionapi;

import sobiohazardous.minestrappolation.extramobdrops.ExtraMobDrops;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityPotion;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class PAItemPotion extends Item
{
	private int potionId;
	private String texture;
	private boolean returnBottle;
	private int potiontime;
	
	public PAItemPotion(int id, int potionId, String texture, int potiontime, boolean returnBottle)
	{
		super(id);
		this.potionId = potionId;
		this.texture = texture;
		this.returnBottle = returnBottle;
		this.potiontime = potiontime;
	}
	
	public PAItemPotion(int id, int potionId, int potiontime, String texture)
	{
		super(id);
		this.potionId = potionId;
		this.texture = texture;
		this.returnBottle = false;
		this.potiontime = potiontime;
	}
	
	/**
     * How long it takes to use or consume an item
     */
    public int getMaxItemUseDuration(ItemStack par1ItemStack)
    {
        return 32;
    }

    /**
     * returns the action that specifies what animation to play when the items is being used
     */
    public EnumAction getItemUseAction(ItemStack par1ItemStack)
    {
        return EnumAction.drink;
    }

    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
    	par3EntityPlayer.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));
        return par1ItemStack;
    }

    /**
     * Callback for item usage. If the item does something special on right clicking, he will have one of those. Return
     * True if something happen and false if it don't. This is for ITEMS, not BLOCKS
     */
    public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
    {
        return false;
    }
    
    public ItemStack onEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
    	if (!par3EntityPlayer.capabilities.isCreativeMode)
        {
            --par1ItemStack.stackSize;
        }
    	
    	if(this.returnBottle)
    	{
    		if (!par3EntityPlayer.capabilities.isCreativeMode)
            {
                if (par1ItemStack.stackSize <= 0)
                {
                    return new ItemStack(Item.glassBottle);
                }

                par3EntityPlayer.inventory.addItemStackToInventory(new ItemStack(Item.glassBottle));
            }
    	}
    	par3EntityPlayer.addPotionEffect(new PotionEffect(potionId, 20 * this.potiontime, 0));
    	return par1ItemStack;
    }

}
