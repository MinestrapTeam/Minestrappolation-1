package sobiohazardous.minestrappolation.extradecor.block;

import sobiohazardous.minestrappolation.extradecor.ExtraDecor;
import net.minecraft.block.material.Material;

public class BlockSugarBlock extends EDBlock
{
	public BlockSugarBlock(int id, String texture)
	{
		super(id, Material.cloth, texture);
		this.setCreativeTab(ExtraDecor.tabDecorBlocks);
	}
	
	//TODO Add code from the features list
}
