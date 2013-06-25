package sobiohazardous.minestrappolation.extradecor.block;

import java.util.Random;

import sobiohazardous.minestrappolation.extradecor.ExtraDecor;
import sobiohazardous.minestrappolation.extraores.ExtraOres;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

public class EDBlock extends Block
{
	private String texture;
	
	public EDBlock(int par1, Material material, String texture)
    {
        super(par1, material);
        this.setCreativeTab(ExtraDecor.tabDecorBlocks);
        this.texture = "extradecor:" + texture;
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
        blockIcon = par1IconRegister.registerIcon(texture);
    }    
    public boolean canDragonDestroy(World world, int x, int y, int z)
    {
        return blockID != obsidian.blockID && blockID != whiteStone.blockID && blockID != bedrock.blockID && blockID != ExtraDecor.endstoneSmooth.blockID && blockID != ExtraDecor.endstoneRefined.blockID && blockID != ExtraDecor.endstoneBrick.blockID && blockID != ExtraDecor.obsidianTile.blockID;
    }
    public int getFlammability(IBlockAccess world, int x, int y, int z, int metadata, ForgeDirection face)
    {
    	if(blockID == ExtraDecor.woodPanel.blockID || blockID == ExtraDecor.woodBeveled.blockID)
    	{
    		if(face == ForgeDirection.UP || face == ForgeDirection.DOWN || face == ForgeDirection.NORTH || face == ForgeDirection.SOUTH || face == ForgeDirection.EAST || face == ForgeDirection.WEST)
    		{
                return 75;
    		}
    		else
    			return 0;
    	}
    	else
    		return 0;
    }
}
