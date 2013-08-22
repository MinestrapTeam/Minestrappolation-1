package sobiohazardous.minestrappolation.extraores.block;

import java.util.Random;

import sobiohazardous.minestrappolation.extraores.ExtraOres;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.src.*;
import net.minecraft.util.MathHelper;

public class BlockSunstoneOre extends Block
{
    public BlockSunstoneOre(int par1)
    {
        super(par1, Material.rock);
        this.setCreativeTab(ExtraOres.tabOresBlocks);
    }
    
    public void registerIcons(IconRegister iconRegister)
    {
             blockIcon = iconRegister.registerIcon("extraores:block_SunstoneOre");
    }
    
    /**
     * Returns the usual quantity dropped by the block plus a bonus of 1 to 'i' (inclusive).
     */
    public int quantityDroppedWithBonus(int par1, Random par2Random)
    {
        return MathHelper.clamp_int(this.quantityDropped(par2Random) + par2Random.nextInt(par1 + 1), 1, 4);
    }

    /**
     * Returns the quantity of items to drop on block destruction.
     */
    public int quantityDropped(Random par1Random)
    {
        return 2 + par1Random.nextInt(3);
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int par1, Random par2Random, int par3)
    {
        return ExtraOres.SunstoneDust.itemID;
    }
}
