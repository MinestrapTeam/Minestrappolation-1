package sobiohazardous.minestrappolation.extraores.item;

import java.util.List;

import sobiohazardous.minestrappolation.extraores.ExtraOres;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.src.*;
import net.minecraft.util.EnumChatFormatting;

public class ItemSoulBottle extends Item
{
        public ItemSoulBottle(int i)
        {
                super(i);
                this.setCreativeTab(ExtraOres.tabOresItems);
        }

        public void registerIcons(IconRegister iconRegister)
    	{
    	         itemIcon = iconRegister.registerIcon("extraores:item_SoulBottle");
    	}
        public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer)
        {
        	if (par2EntityPlayer.experienceTotal < 10 && !par2EntityPlayer.capabilities.isCreativeMode)
            {
        		return false;
            }
        	else
        	{
        		par2EntityPlayer.addExperienceLevel(1);
        		return true;
        	}
        }
        
        public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) 
        {
        	par3List.add(EnumChatFormatting.RED + "WIP");
        }
        
}