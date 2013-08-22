package sobiohazardous.minestrappolation.extradecor.block;

import sobiohazardous.minestrappolation.extradecor.ExtraDecor;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.ForgeDirection;

public class EDBlockStairs extends BlockStairs
{
	public EDBlockStairs(int id, Block block, int blockmeta)
	{
		super(id, block, blockmeta);
		this.setCreativeTab(ExtraDecor.tabDecorBlocks);
	}
	
	public int getFlammability(IBlockAccess world, int x, int y, int z, int metadata, ForgeDirection face)
    {
    	if(blockID == ExtraDecor.woodBoardsStairsOak.blockID || blockID == ExtraDecor.woodBoardsStairsBirch.blockID || blockID == ExtraDecor.woodBoardsStairsSpruce.blockID || blockID == ExtraDecor.woodBoardsStairsJungle.blockID)
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
