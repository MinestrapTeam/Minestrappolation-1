package sobiohazardous.minestrappolation.extradecor.block;

import java.util.Random;

import sobiohazardous.minestrappolation.extradecor.ExtraDecor;
import sobiohazardous.minestrappolation.extraores.ExtraOres;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.Icon;

public class BlockStonePillar extends Block
{
	
	private Icon top;
	public BlockStonePillar(int par1, Material material)
    {
        super(par1, material);
        this.setCreativeTab(ExtraDecor.tabDecorBlocks);
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int par1, Random par2Random, int par3)
    {
        return this.blockID == ExtraDecor.stoneBlockRefined.blockID ? ExtraDecor.stoneBlockRefined.blockID : this.blockID;
    }
    
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.blockIcon = par1IconRegister.registerIcon("extradecor:block_StonePillar");
        this.top = par1IconRegister.registerIcon("extradecor:block_StoneRefined");
    }
    
    public Icon getIcon(int i, int j)
    {
    	if (i == 0)//bottom
            
            return top;
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
		return blockIcon;
    }  
}
