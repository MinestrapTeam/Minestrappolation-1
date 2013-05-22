package sobiohazardous.minestrappolation.extraores.block;

import java.util.Random;

import sobiohazardous.minestrappolation.extraores.ExtraOres;


import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.src.*;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class BlockCopperTarnished extends Block
{
	public Icon top;
	
	public BlockCopperTarnished(int par1)
    {
        super(par1, Material.iron);
        this.setCreativeTab(ExtraOres.tabOresBlocks);
    }

	public void registerIcons(IconRegister iconRegister)
	{
	         blockIcon = iconRegister.registerIcon("extraores:block_TarnishedCopperSide");
	         this.top = iconRegister.registerIcon("extraores:block_TarnishedCopperTop");
	}
    
    //Args: side, metadata
    
    public Icon getIcon(int i, int j)
    {
    	if (i == 0)//bottom
            
            return blockIcon;
    	if (i == 1)//top
           
            return top;
   
    	if (i == 2) // side
           
            return blockIcon;
    	if (i == 3)//side 
           
            return blockIcon;
    	if (i == 4) //side
   
    		return blockIcon;
    	if (i == 5) //side
   
    		return blockIcon;

    	if (j ==1)
    	{
    		return blockIcon;
    	}
		return top;
    }
}
