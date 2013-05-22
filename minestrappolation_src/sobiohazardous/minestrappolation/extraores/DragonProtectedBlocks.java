package sobiohazardous.minestrappolation.extraores;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;

public class DragonProtectedBlocks extends Block 
{
	
	public DragonProtectedBlocks(int par1, Material par2Material) 
	{
		super(par1, par2Material);
		// TODO Sucky attempt at making Invincium and the new Bedrock to be impervious to contact with the Ender Dragon. Needless to say, this doesn't work at all.
	}

	public boolean canDragonDestroy(World world, int x, int y, int z)
    {
        return blockID != obsidian.blockID && blockID != whiteStone.blockID && blockID != bedrock.blockID && blockID != ExtraOres.ExtraOresBedrock.blockID && blockID != ExtraOres.Invincium.blockID;
    }
}
