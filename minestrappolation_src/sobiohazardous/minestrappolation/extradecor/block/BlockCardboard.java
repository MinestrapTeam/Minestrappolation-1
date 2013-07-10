package sobiohazardous.minestrappolation.extradecor.block;

import java.util.Random;

import sobiohazardous.minestrappolation.extradecor.ExtraDecor;
import sobiohazardous.minestrappolation.util.BlockFunctions;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockCardboard extends EDBlock
{
	public BlockCardboard(int par1, Material material, String texture) 
	{
		super(par1, material, texture);
	}

    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor blockID
     */
    public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5) 
    {
    	if(par5 == Block.waterMoving.blockID || par5 == Block.waterStill.blockID)
    	{
            par1World.setBlock(par2, par3, par4, ExtraDecor.cardboardWet.blockID);
    	}
    }

    /**
     * Called whenever the block is added into the world. Args: world, x, y, z
     */
    public void onBlockAdded(World par1World, int par2, int par3, int par4) 
    {
    	if(BlockFunctions.isWaterTouchingAllSides(par1World, par2, par3, par4))
    	{
    		par1World.setBlock(par2, par3, par4, ExtraDecor.cardboardWet.blockID);
    	}
    }
    
    public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack)
    {
    	if(BlockFunctions.isWaterTouchingAllSides(par1World, par2, par3, par4))
    	{
    		par1World.setBlock(par2, par3, par4, ExtraDecor.cardboardWet.blockID);
    	}
    }

}
