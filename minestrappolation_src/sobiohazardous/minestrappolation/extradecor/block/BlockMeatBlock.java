package sobiohazardous.minestrappolation.extradecor.block;

import sobiohazardous.minestrappolation.extradecor.ExtraDecor;
import net.minecraft.block.material.Material;

public class BlockMeatBlock extends EDBlock
{
	public BlockMeatBlock(int id, String texture)
	{
		super(id, Material.cloth, texture);
		this.setCreativeTab(EDBlockManager.tabDecorBlocks);
	}
	
	//TODO Add code from the features list
}
