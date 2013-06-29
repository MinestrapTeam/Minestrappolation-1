package sobiohazardous.minestrappolation.extradecor.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import java.util.Random;

import sobiohazardous.minestrappolation.extradecor.ExtraDecor;
import sobiohazardous.minestrappolation.util.BlockFunctions;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockRope extends Block
{

    public BlockRope(int par1)
    {
        super(par1, Material.circuits);
    }

    /**
     * Updates the blocks bounds based on its current state. Args: world, x, y, z
     */
    public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
    {
        float f = 0.375F;
        float f1 = 0.625F;
        float f2 = 0.375F;
        float f3 = 0.625F;

        this.setBlockBounds(f, 0.0F, f2, f1, 1.0F, f3);
    }

    /**
     * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
     * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
     */
 
    public boolean isOpaqueCube()
    {
        return false;
    }

    /**
     * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
     */
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    
    public boolean getBlocksMovement(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
    {
        return false;
    }
     
    /**
     * The type of render function that is called for this block
     */
    public int getRenderType()
    {
        return ExtraDecor.ropeRenderId;
    }


    public static boolean isIdAFence(int par0)
    {
        return par0 == ExtraDecor.rope.blockID;
    }

    @SideOnly(Side.CLIENT)

    /**
     * Returns true if the given side of this block type should be rendered, if the adjacent block is at the given
     * coordinates.  Args: blockAccess, x, y, z, side
     */
    public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
    {
        return true;
    }

    @SideOnly(Side.CLIENT)

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.blockIcon = par1IconRegister.registerIcon("extradecor:block_RopeCoilSide");
    }
    
    @Override
    public boolean isLadder(World world, int x, int y, int z)
    {
        return true;
    }
    
    public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5)
    {
    	if(BlockFunctions.isBlockAbove(par1World, par2, par3, par4, 0))
    	{
    		par1World.destroyBlock(par2, par3, par4, true);
    	}
    }
    
    public boolean canBlockStay(World par1World, int par2, int par3, int par4)
    {
    	if(BlockFunctions.isBlockAbove(par1World, par2, par3, par4, 0))
    	{
    		return true;
    	}
    	
    	return false;
    }
    
    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int par1, Random par2Random, int par3)
    {
        return ExtraDecor.itemRope.itemID;
    }
}
