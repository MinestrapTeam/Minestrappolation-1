package sobiohazardous.minestrappolation.extradecor;

import sobiohazardous.minestrappolation.extraores.ExtraOres;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;

public class EDRecipeManager 
{

	public static void loadAllRecipes()
	{
		GameRegistry.addRecipe(new ItemStack(ExtraDecor.stoneBlockRefined, 9), new Object[]
				{
			"SSS", "SSS", "SSS", Character.valueOf('S'), Block.stone
				});
		
		GameRegistry.addRecipe(new ItemStack(ExtraDecor.stonePillar, 2), new Object[]
				{
			"S", "S", Character.valueOf('S'), ExtraDecor.stoneBlockRefined
				});
		GameRegistry.addRecipe(new ItemStack(ExtraDecor.stoneTile, 4), new Object[]
				{
			"SS", "SS", Character.valueOf('S'), ExtraDecor.stoneBlockRefined
				});
		GameRegistry.addRecipe(new ItemStack(ExtraDecor.stoneLamp, 8), new Object[]
				{
			"SSS", "SGS", "SSS", Character.valueOf('S'), ExtraDecor.stoneBlockRefined, Character.valueOf('G'), Block.glowStone
				});
		GameRegistry.addRecipe(new ItemStack(ExtraDecor.edgeStoneBrick, 4), new Object[]
				{
			"SG", "SG", Character.valueOf('S'), new ItemStack(Block.stoneBrick, 1, 0), Character.valueOf('G'), new ItemStack(Block.blockNetherQuartz, 1, 0)
				});
		GameRegistry.addRecipe(new ItemStack(ExtraDecor.brickPattern, 2), new Object[]
				{
			"SSS", "SSS", "SSS", Character.valueOf('S'), Item.brick
				});
		GameRegistry.addRecipe(new ItemStack(ExtraDecor.obsidianTile, 4), new Object[]
				{
			"SS", "SS", Character.valueOf('S'), Block.obsidian
				});
		GameRegistry.addRecipe(new ItemStack(ExtraDecor.snowBrick, 4), new Object[]
				{
			"SS", "SS", Character.valueOf('S'), Block.blockSnow
				});
		GameRegistry.addSmelting(Block.whiteStone.blockID, new ItemStack(ExtraDecor.endstoneSmooth), 20);
		GameRegistry.addRecipe(new ItemStack(ExtraDecor.endstoneRefined, 9), new Object[]
				{
			"SSS", "SSS", "SSS", Character.valueOf('S'), ExtraDecor.endstoneSmooth
				});
		GameRegistry.addRecipe(new ItemStack(ExtraDecor.endstoneBrick, 4), new Object[]
				{
			"SS", "SS", Character.valueOf('S'), ExtraDecor.endstoneSmooth
				});
		GameRegistry.addSmelting(Block.glass.blockID, new ItemStack(ExtraDecor.glassRefined), 20);
		GameRegistry.addRecipe(new ItemStack(ExtraDecor.glassRefinedPane, 4), new Object[]
				{
			"SSS", "SSS", Character.valueOf('S'), ExtraDecor.glassRefined
				});
		GameRegistry.addRecipe(new ItemStack(ExtraDecor.flintBlock, 2), new Object[]
				{
			"SS", "SS", Character.valueOf('S'), Item.flint
				});
		GameRegistry.addRecipe(new ItemStack(ExtraDecor.gunpowderBlock, 9), new Object[]
				{
			"SSS", "SSS","SSS", Character.valueOf('S'), Item.gunpowder
				});
		GameRegistry.addRecipe(new ItemStack(ExtraDecor.rope, 1), new Object[]
				{
			"S", "S", "S", Character.valueOf('S'), Item.silk
				});
		GameRegistry.addRecipe(new ItemStack(ExtraDecor.ropeCoil, 1), new Object[]
				{
			"SSS", "SSS", "SSS", Character.valueOf('S'), ExtraDecor.itemRope
				});
		GameRegistry.addRecipe(new ItemStack(ExtraDecor.oozeSlime, 1), new Object[]
				{
			"SSS", "SSS", "SSS", Character.valueOf('S'), Item.slimeBall
				});
		
		
		
		GameRegistry.addRecipe(new ItemStack(ExtraDecor.oakPanel, 9), new Object[]
				{
			"SSS", "SSS", "SSS", Character.valueOf('S'), new ItemStack(Block.planks, 1, 0)
				});
		
		GameRegistry.addRecipe(new ItemStack(ExtraDecor.birchPanel, 9), new Object[]
				{
			"SSS", "SSS", "SSS", Character.valueOf('S'), new ItemStack(Block.planks, 1, 1)
				});
		
		GameRegistry.addRecipe(new ItemStack(ExtraDecor.sprucePanel, 9), new Object[]
				{
			"SSS", "SSS", "SSS", Character.valueOf('S'), new ItemStack(Block.planks, 1, 2)
				});
		
		GameRegistry.addRecipe(new ItemStack(ExtraDecor.junglePanel, 9), new Object[]
				{
			"SSS", "SSS", "SSS", Character.valueOf('S'), new ItemStack(Block.planks, 1, 3)
				});
		
		
		
		GameRegistry.addRecipe(new ItemStack(ExtraDecor.oakBeveled, 4), new Object[]
				{
			"SS", "SS", Character.valueOf('S'), ExtraDecor.oakPanel
				});
		GameRegistry.addRecipe(new ItemStack(ExtraDecor.birchBeveled, 4), new Object[]
				{
			"SS", "SS", Character.valueOf('S'), ExtraDecor.birchPanel
				});
		GameRegistry.addRecipe(new ItemStack(ExtraDecor.spruceBeveled, 4), new Object[]
				{
			"SS", "SS", Character.valueOf('S'), ExtraDecor.sprucePanel
				});
		GameRegistry.addRecipe(new ItemStack(ExtraDecor.jungleBeveled, 4), new Object[]
				{
			"SS", "SS", Character.valueOf('S'), ExtraDecor.junglePanel
				});
	}
}
