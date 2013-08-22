package sobiohazardous.minestrappolation.extradecor.block;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

import sobiohazardous.minestrappolation.extradecor.ExtraDecor;
import sobiohazardous.minestrappolation.util.BlockFunctions;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.src.*;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class BlockEdgeStoneBrickLeft extends Block
{
	
	public BlockEdgeStoneBrickLeft(int par1)
    {
        super(par1, Material.rock);
    }
	
	public void registerIcons(IconRegister iconRegister)
	{
	         blockIcon = iconRegister.registerIcon("ExtraDecor:block_EdgeStoneSideLeft");
	}
	
	public int idDropped(int par1, Random par2Random, int par3)
    {
        return ExtraDecor.edgeStoneBrick.blockID;
    }

}
