package sobiohazardous.minestrappolation.extradecor.block;

import sobiohazardous.minestrappolation.extradecor.ExtraDecor;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;

public class EDBlockStairs extends BlockStairs
{
	public EDBlockStairs(int id, Block block, int blockmeta)
	{
		super(id, block, blockmeta);
		this.setCreativeTab(ExtraDecor.tabDecorBlocks);
	}
}
