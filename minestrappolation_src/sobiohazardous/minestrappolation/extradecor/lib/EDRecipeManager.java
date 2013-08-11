package sobiohazardous.minestrappolation.extradecor.lib;

import sobiohazardous.minestrappolation.extradecor.ExtraDecor;
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
		GameRegistry.addRecipe(new ItemStack(ExtraDecor.glassRefinedPane, 16), new Object[]
				{
			"SSS", "SSS", Character.valueOf('S'), ExtraDecor.glassRefined
				});
		GameRegistry.addRecipe(new ItemStack(ExtraDecor.flintBlock, 2), new Object[]
				{
			"SS", "SS", Character.valueOf('S'), Item.flint
				});
		GameRegistry.addRecipe(new ItemStack(Item.flint, 2), new Object[]
				{
			"S", Character.valueOf('S'), ExtraDecor.flintBlock
				});
		GameRegistry.addRecipe(new ItemStack(ExtraDecor.gunpowderBlock, 1), new Object[]
				{
			"SSS", "SSS","SSS", Character.valueOf('S'), Item.gunpowder
				});
		GameRegistry.addRecipe(new ItemStack(Item.gunpowder, 9), new Object[]
				{
			"S", Character.valueOf('S'), ExtraDecor.gunpowderBlock
				});
		GameRegistry.addRecipe(new ItemStack(ExtraDecor.itemRope, 1), new Object[]
				{
			"S", "S", "S", Character.valueOf('S'), Item.silk
				});
		GameRegistry.addRecipe(new ItemStack(ExtraDecor.ropeCoil, 1), new Object[]
				{
			"SSS", "SSS", "SSS", Character.valueOf('S'), ExtraDecor.itemRope
				});
		GameRegistry.addRecipe(new ItemStack(ExtraDecor.itemRope, 9), new Object[]
				{
			"S", Character.valueOf('S'), ExtraDecor.ropeCoil
				});
		GameRegistry.addRecipe(new ItemStack(ExtraDecor.oozeSlime, 1), new Object[]
				{
			"SSS", "SSS", "SSS", Character.valueOf('S'), Item.slimeBall
				});
		GameRegistry.addRecipe(new ItemStack(Item.slimeBall, 9), new Object[]
				{
			"S", Character.valueOf('S'), ExtraDecor.oozeSlime
				});
		
		GameRegistry.addRecipe(new ItemStack(ExtraDecor.woodPanel, 9, 0), new Object[]
				{
			"SSS", "SSS", "SSS", Character.valueOf('S'), new ItemStack(Block.planks, 1, 0)
				});
		
		GameRegistry.addRecipe(new ItemStack(ExtraDecor.woodPanel, 9, 1), new Object[]
				{
			"SSS", "SSS", "SSS", Character.valueOf('S'), new ItemStack(Block.planks, 1, 2)
				});
		
		GameRegistry.addRecipe(new ItemStack(ExtraDecor.woodPanel, 9,2), new Object[]
				{
			"SSS", "SSS", "SSS", Character.valueOf('S'), new ItemStack(Block.planks, 1, 1)
				});
		
		GameRegistry.addRecipe(new ItemStack(ExtraDecor.woodPanel, 9,3), new Object[]
				{
			"SSS", "SSS", "SSS", Character.valueOf('S'), new ItemStack(Block.planks, 1, 3)
				});
		
		GameRegistry.addRecipe(new ItemStack(ExtraDecor.woodBeveled, 4, 0), new Object[]
				{
			"SS", "SS", Character.valueOf('S'), new ItemStack(ExtraDecor.woodPanel, 1 , 0)
				});
		GameRegistry.addRecipe(new ItemStack(ExtraDecor.woodBeveled, 4, 1), new Object[]
				{
			"SS", "SS", Character.valueOf('S'), new ItemStack(ExtraDecor.woodPanel, 1 , 1)
				});
		GameRegistry.addRecipe(new ItemStack(ExtraDecor.woodBeveled, 4, 2), new Object[]
				{
			"SS", "SS", Character.valueOf('S'), new ItemStack(ExtraDecor.woodPanel, 1 , 2)
				});
		GameRegistry.addRecipe(new ItemStack(ExtraDecor.woodBeveled, 4, 3), new Object[]
				{
			"SS", "SS", Character.valueOf('S'), new ItemStack(ExtraDecor.woodPanel, 1 , 3)
				});
		GameRegistry.addRecipe(new ItemStack(ExtraDecor.stoneLamp, 8, 0), new Object[]
				{
			"SSS", "SGS", "SSS", Character.valueOf('S'), ExtraDecor.stoneBlockRefined, Character.valueOf('G'), Block.glowStone
				});
		GameRegistry.addRecipe(new ItemStack(ExtraDecor.netherBrickPattern, 9), new Object[]
				{
			"SSS", "SSS", "SSS", Character.valueOf('S'), Item.netherrackBrick
				});
		
		//TODO add sandstone brick recipes
		GameRegistry.addRecipe(new ItemStack(ExtraDecor.sandstonePillar, 2), new Object[]
				{
			"S", "S", Character.valueOf('S'), Block.sandStone
				});
		GameRegistry.addRecipe(new ItemStack(ExtraDecor.woodBoards, 16, 0), new Object[]
				{
			"SS", "SS", Character.valueOf('S'), new ItemStack(Block.wood, 1 , 0)
				});
		GameRegistry.addRecipe(new ItemStack(ExtraDecor.woodBoards, 16, 1), new Object[]
				{
			"SS", "SS", Character.valueOf('S'), new ItemStack(Block.wood, 1 , 2)
				});
		GameRegistry.addRecipe(new ItemStack(ExtraDecor.woodBoards, 16, 2), new Object[]
				{
			"SS", "SS", Character.valueOf('S'), new ItemStack(Block.wood, 1 , 1)
				});
		GameRegistry.addRecipe(new ItemStack(ExtraDecor.woodBoards, 16, 3), new Object[]
				{
			"SS", "SS", Character.valueOf('S'), new ItemStack(Block.wood, 1 , 3)
				});
		GameRegistry.addRecipe(new ItemStack(ExtraDecor.flintTile, 4), new Object[]
				{
			"FF", "FF", Character.valueOf('F'), ExtraDecor.flintBlock
				});
		GameRegistry.addRecipe(new ItemStack(ExtraDecor.netherQuartzTile, 4), new Object[]
				{
			"FF", "FF", Character.valueOf('F'), Block.blockNetherQuartz
				});
		GameRegistry.addRecipe(new ItemStack(ExtraDecor.sugarBlock), new Object[]
				{
			"SSS","SSS","SSS", Character.valueOf('S'), Item.sugar
				});
		GameRegistry.addRecipe(new ItemStack(Item.sugar, 9), new Object[]
				{
			"S", Character.valueOf('S'), ExtraDecor.sugarBlock
				});
		GameRegistry.addRecipe(new ItemStack(ExtraDecor.meatBlock), new Object[]
				{
			"SSS","SSS","SSS", Character.valueOf('S'), Item.chickenRaw
				});
		GameRegistry.addRecipe(new ItemStack(ExtraDecor.meatBlock), new Object[]
				{
			"SSS","SSS","SSS", Character.valueOf('S'), Item.beefRaw
				});
		GameRegistry.addRecipe(new ItemStack(ExtraDecor.meatBlock), new Object[]
				{
			"SSS","SSS","SSS", Character.valueOf('S'), Item.fishRaw
				});
		GameRegistry.addRecipe(new ItemStack(ExtraDecor.meatBlock), new Object[]
				{
			"SSS","SSS","SSS", Character.valueOf('S'), Item.rottenFlesh
				});
		GameRegistry.addRecipe(new ItemStack(ExtraDecor.meatBlock), new Object[]
				{
			"SSS","SSS","SSS", Character.valueOf('S'), Item.porkRaw
				});
		GameRegistry.addRecipe(new ItemStack(ExtraDecor.magmaOoze), new Object[]
				{
			"SSS","SSS","SSS", Character.valueOf('S'), Item.magmaCream
				});
		GameRegistry.addRecipe(new ItemStack(Item.magmaCream, 9), new Object[]
				{
			"S", Character.valueOf('S'), ExtraDecor.magmaOoze
				});
		GameRegistry.addRecipe(new ItemStack(ExtraDecor.enderBlock), new Object[]
				{
			"SSS","SSS","SSS", Character.valueOf('S'), Item.enderPearl
				});
		GameRegistry.addRecipe(new ItemStack(Item.enderPearl, 9), new Object[]
				{
			"S", Character.valueOf('S'), ExtraDecor.enderBlock
				});
		GameRegistry.addRecipe(new ItemStack(ExtraDecor.crate), new Object[]
				{
			"WWW","SSS","WWW", Character.valueOf('S'), Item.stick, Character.valueOf('W'), Block.planks
				});
		GameRegistry.addRecipe(new ItemStack(ExtraDecor.barrel), new Object[]
				{
			"I", "C", "I", Character.valueOf('I'), Item.ingotIron, Character.valueOf('C'), ExtraDecor.crate
				});
		GameRegistry.addRecipe(new ItemStack(ExtraDecor.cardboardItem, 3), new Object[]
				{
			"SSS", "SSS", Character.valueOf('S'), Item.paper
				});
		GameRegistry.addRecipe(new ItemStack(ExtraDecor.cardboardBlock, 2), new Object[]
				{
			"CCC", " C ", " CC", Character.valueOf('C'), ExtraDecor.cardboardItem
				});
		GameRegistry.addSmelting(Block.sandStone.blockID, new ItemStack(ExtraDecor.sandstoneBrickItem, 4),  0.8F);
		GameRegistry.addRecipe(new ItemStack(Block.stoneBrick, 1, 0), new Object[]
				{
			"BB", "BB", Character.valueOf('B'), ExtraDecor.stoneBrickItem
				});
		GameRegistry.addShapelessRecipe(new ItemStack(ExtraDecor.stoneBrickItem , 4), new Object[]
				{
			new ItemStack(Block.stoneBrick, 1 ,0)
				});
		GameRegistry.addRecipe(new ItemStack(Block.stoneBrick, 2, 3), new Object[]
				{
			"BBB", "BBB", "BBB", Character.valueOf('B'), ExtraDecor.stoneBrickItem
				});
		GameRegistry.addRecipe(new ItemStack(ExtraDecor.sandstoneBricks, 1, 0), new Object[]
				{
			"BB", "BB", Character.valueOf('B'), ExtraDecor.sandstoneBrickItem
				});
		GameRegistry.addRecipe(new ItemStack(ExtraDecor.sandstoneBricks, 2, 1), new Object[]
				{
			"BBB", "BBB", "BBB", Character.valueOf('B'), ExtraDecor.sandstoneBrickItem
				});
		GameRegistry.addRecipe(new ItemStack(ExtraDecor.checkerTile, 4), new Object[]
				{
			"FQ", "QF", Character.valueOf('F'), ExtraDecor.flintTile, Character.valueOf('Q'), ExtraDecor.netherQuartzTile
				});
		GameRegistry.addRecipe(new ItemStack(ExtraDecor.woodBoardsSingleSlab, 6, 0), new Object[]
				{
			"FFF", Character.valueOf('F'), new ItemStack(ExtraDecor.woodBoards, 1, 0)
				});
		GameRegistry.addRecipe(new ItemStack(ExtraDecor.woodBoardsSingleSlab, 6, 1), new Object[]
				{
			"FFF", Character.valueOf('F'), new ItemStack(ExtraDecor.woodBoards, 1, 1)
				});
		GameRegistry.addRecipe(new ItemStack(ExtraDecor.woodBoardsSingleSlab, 6, 2), new Object[]
				{
			"FFF", Character.valueOf('F'), new ItemStack(ExtraDecor.woodBoards, 1, 2)
				});
		GameRegistry.addRecipe(new ItemStack(ExtraDecor.woodBoardsSingleSlab, 6, 3), new Object[]
				{
			"FFF", Character.valueOf('F'), new ItemStack(ExtraDecor.woodBoards, 1, 3)
				});
		GameRegistry.addRecipe(new ItemStack(ExtraDecor.woodBoardsStairsOak, 4), new Object[]
				{
			"  F", " FF", "FFF", Character.valueOf('F'), new ItemStack(ExtraDecor.woodBoards, 1, 0)
				});
		GameRegistry.addRecipe(new ItemStack(ExtraDecor.woodBoardsStairsBirch, 4), new Object[]
				{
			"  F", " FF", "FFF", Character.valueOf('F'), new ItemStack(ExtraDecor.woodBoards, 1, 1)
				});
		GameRegistry.addRecipe(new ItemStack(ExtraDecor.woodBoardsStairsSpruce, 4), new Object[]
				{
			"  F", " FF", "FFF", Character.valueOf('F'), new ItemStack(ExtraDecor.woodBoards, 1, 2)
				});
		GameRegistry.addRecipe(new ItemStack(ExtraDecor.woodBoardsStairsJungle, 4), new Object[]
				{
			"  F", " FF", "FFF", Character.valueOf('F'), new ItemStack(ExtraDecor.woodBoards, 1, 3)
				});
		GameRegistry.addShapelessRecipe(new ItemStack(Item.enderPearl, 9), new Object[]
				{
			ExtraDecor.enderBlock
				});
		GameRegistry.addRecipe(new ItemStack(ExtraDecor.checkerTileStairs, 4), new Object[]
				{
			"  T",  " TT", "TTT", Character.valueOf('T'), ExtraDecor.checkerTile
				});
		
		
		
		GameRegistry.addRecipe(new ItemStack(ExtraDecor.stainedBrick, 8, 0), new Object[]
				{
			"BBB", "BDB", "BBB", Character.valueOf('B'), Block.brick, Character.valueOf('D'), new ItemStack(Item.dyePowder, 1, 15)
				});
		GameRegistry.addRecipe(new ItemStack(ExtraDecor.stainedBrick, 8, 1), new Object[]
				{
			"BBB", "BDB", "BBB", Character.valueOf('B'), Block.brick, Character.valueOf('D'), new ItemStack(Item.dyePowder, 1, 7)
				});
		GameRegistry.addRecipe(new ItemStack(ExtraDecor.stainedBrick, 8, 2), new Object[]
				{
			"BBB", "BDB", "BBB", Character.valueOf('B'), Block.brick, Character.valueOf('D'), new ItemStack(Item.dyePowder, 1, 8)
				});
		GameRegistry.addRecipe(new ItemStack(ExtraDecor.stainedBrick, 8, 3), new Object[]
				{
			"BBB", "BDB", "BBB", Character.valueOf('B'), Block.brick, Character.valueOf('D'), new ItemStack(Item.dyePowder, 1, 0)
				});
		GameRegistry.addRecipe(new ItemStack(ExtraDecor.stainedBrick, 8, 4), new Object[]
				{
			"BBB", "BDB", "BBB", Character.valueOf('B'), Block.brick, Character.valueOf('D'), new ItemStack(Item.dyePowder, 1, 3)
				});
		GameRegistry.addRecipe(new ItemStack(ExtraDecor.stainedBrick, 8, 5), new Object[]
				{
			"BBB", "BDB", "BBB", Character.valueOf('B'), Block.brick, Character.valueOf('D'), new ItemStack(Item.dyePowder, 1, 9)
				});
		GameRegistry.addRecipe(new ItemStack(ExtraDecor.stainedBrick, 8, 6), new Object[]
				{
			"BBB", "BDB", "BBB", Character.valueOf('B'), Block.brick, Character.valueOf('D'), new ItemStack(Item.dyePowder, 1, 1)
				});
		GameRegistry.addRecipe(new ItemStack(ExtraDecor.stainedBrick, 8, 7), new Object[]
				{
			"BBB", "BDB", "BBB", Character.valueOf('B'), Block.brick, Character.valueOf('D'), new ItemStack(Item.dyePowder, 1, 14)
				});
		GameRegistry.addRecipe(new ItemStack(ExtraDecor.stainedBrick, 8, 8), new Object[]
				{
			"BBB", "BDB", "BBB", Character.valueOf('B'), Block.brick, Character.valueOf('D'), new ItemStack(Item.dyePowder, 1, 11)
				});
		GameRegistry.addRecipe(new ItemStack(ExtraDecor.stainedBrick, 8, 9), new Object[]
				{
			"BBB", "BDB", "BBB", Character.valueOf('B'), Block.brick, Character.valueOf('D'), new ItemStack(Item.dyePowder, 1, 10)
				});
		GameRegistry.addRecipe(new ItemStack(ExtraDecor.stainedBrick, 8, 10), new Object[]
				{
			"BBB", "BDB", "BBB", Character.valueOf('B'), Block.brick, Character.valueOf('D'), new ItemStack(Item.dyePowder, 1, 2)
				});
		GameRegistry.addRecipe(new ItemStack(ExtraDecor.stainedBrick, 8, 11), new Object[]
				{
			"BBB", "BDB", "BBB", Character.valueOf('B'), Block.brick, Character.valueOf('D'), new ItemStack(Item.dyePowder, 1, 6)
				});
		GameRegistry.addRecipe(new ItemStack(ExtraDecor.stainedBrick, 8, 12), new Object[]
				{
			"BBB", "BDB", "BBB", Character.valueOf('B'), Block.brick, Character.valueOf('D'), new ItemStack(Item.dyePowder, 1, 12)
				});
		GameRegistry.addRecipe(new ItemStack(ExtraDecor.stainedBrick, 8, 13), new Object[]
				{
			"BBB", "BDB", "BBB", Character.valueOf('B'), Block.brick, Character.valueOf('D'), new ItemStack(Item.dyePowder, 1, 4)
				});
		GameRegistry.addRecipe(new ItemStack(ExtraDecor.stainedBrick, 8, 14), new Object[]
				{
			"BBB", "BDB", "BBB", Character.valueOf('B'), Block.brick, Character.valueOf('D'), new ItemStack(Item.dyePowder, 1, 5)
				});
		GameRegistry.addRecipe(new ItemStack(ExtraDecor.stainedBrick, 8, 15), new Object[]
				{
			"BBB", "BDB", "BBB", Character.valueOf('B'), Block.brick, Character.valueOf('D'), new ItemStack(Item.dyePowder, 1, 13)
				});
		
		
		
		GameRegistry.addRecipe(new ItemStack(ExtraDecor.stainedBrick, 4, 0), new Object[]
				{
			"BB", "BB", Character.valueOf('B'), new ItemStack(Block.field_111039_cA, 1, 0)
				});
		GameRegistry.addRecipe(new ItemStack(ExtraDecor.stainedBrick, 4, 1), new Object[]
				{
			"BB", "BB", Character.valueOf('B'), new ItemStack(Block.field_111039_cA, 1, 8)
				});
		GameRegistry.addRecipe(new ItemStack(ExtraDecor.stainedBrick, 4, 2), new Object[]
				{
			"BB", "BB", Character.valueOf('B'), new ItemStack(Block.field_111039_cA, 1, 7)
				});
		GameRegistry.addRecipe(new ItemStack(ExtraDecor.stainedBrick, 4, 3), new Object[]
				{
			"BB", "BB", Character.valueOf('B'), new ItemStack(Block.field_111039_cA, 1, 15)
				});
		GameRegistry.addRecipe(new ItemStack(ExtraDecor.stainedBrick, 4, 4), new Object[]
				{
			"BB", "BB", Character.valueOf('B'), new ItemStack(Block.field_111039_cA, 1, 12)
				});
		GameRegistry.addRecipe(new ItemStack(ExtraDecor.stainedBrick, 4, 5), new Object[]
				{
			"BB", "BB", Character.valueOf('B'), new ItemStack(Block.field_111039_cA, 1, 6)
				});
		GameRegistry.addRecipe(new ItemStack(ExtraDecor.stainedBrick, 4, 6), new Object[]
				{
			"BB", "BB", Character.valueOf('B'), new ItemStack(Block.field_111039_cA, 1, 14)
				});
		GameRegistry.addRecipe(new ItemStack(ExtraDecor.stainedBrick, 4, 7), new Object[]
				{
			"BB", "BB", Character.valueOf('B'), new ItemStack(Block.field_111039_cA, 1, 1)
				});
		GameRegistry.addRecipe(new ItemStack(ExtraDecor.stainedBrick, 4, 8), new Object[]
				{
			"BB", "BB", Character.valueOf('B'), new ItemStack(Block.field_111039_cA, 1, 4)
				});
		GameRegistry.addRecipe(new ItemStack(ExtraDecor.stainedBrick, 4, 9), new Object[]
				{
			"BB", "BB", Character.valueOf('B'), new ItemStack(Block.field_111039_cA, 1, 5)
				});
		GameRegistry.addRecipe(new ItemStack(ExtraDecor.stainedBrick, 4, 10), new Object[]
				{
			"BB", "BB", Character.valueOf('B'), new ItemStack(Block.field_111039_cA, 1, 13)
				});
		GameRegistry.addRecipe(new ItemStack(ExtraDecor.stainedBrick, 4, 11), new Object[]
				{
			"BB", "BB", Character.valueOf('B'), new ItemStack(Block.field_111039_cA, 1, 9)
				});
		GameRegistry.addRecipe(new ItemStack(ExtraDecor.stainedBrick, 4, 12), new Object[]
				{
			"BB", "BB", Character.valueOf('B'), new ItemStack(Block.field_111039_cA, 1, 3)
				});
		GameRegistry.addRecipe(new ItemStack(ExtraDecor.stainedBrick, 4, 13), new Object[]
				{
			"BB", "BB", Character.valueOf('B'), new ItemStack(Block.field_111039_cA, 1, 11)
				});
		GameRegistry.addRecipe(new ItemStack(ExtraDecor.stainedBrick, 4, 14), new Object[]
				{
			"BB", "BB", Character.valueOf('B'), new ItemStack(Block.field_111039_cA, 1, 10)
				});
		GameRegistry.addRecipe(new ItemStack(ExtraDecor.stainedBrick, 4, 15), new Object[]
				{
			"BB", "BB", Character.valueOf('B'), new ItemStack(Block.field_111039_cA, 1, 2)
				});
	}
}
