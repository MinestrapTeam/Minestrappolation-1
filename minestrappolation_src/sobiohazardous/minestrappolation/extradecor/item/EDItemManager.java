package sobiohazardous.minestrappolation.extradecor.item;

import cpw.mods.fml.common.registry.LanguageRegistry;
import sobiohazardous.minestrappolation.extradecor.EDConfig;
import sobiohazardous.minestrappolation.extradecor.block.BlockSandstoneBrick;
import sobiohazardous.minestrappolation.extradecor.block.BlockStainedBrick;
import sobiohazardous.minestrappolation.extradecor.block.BlockStoneLamp;
import sobiohazardous.minestrappolation.extradecor.block.BlockWoodBeveled;
import sobiohazardous.minestrappolation.extradecor.block.BlockWoodBoards;
import sobiohazardous.minestrappolation.extradecor.block.BlockWoodPanel;
import sobiohazardous.minestrappolation.extradecor.block.EDBlockManager;
import sobiohazardous.minestrappolation.extradecor.bridge.EDBridgeRecipes;
import net.minecraft.block.BlockHalfSlab;
import net.minecraft.item.Item;
import net.minecraft.item.ItemMultiTextureTile;
import net.minecraft.item.ItemSlab;
import net.minecraftforge.common.MinecraftForge;

public class EDItemManager {

	public static Item cardboardItem;	
	public static void addItems(){
		cardboardItem = new EDItem(3500, "item_Cardboard").setCreativeTab(EDBlockManager.tabDecorBlocks).setUnlocalizedName("cardboardItem");
	}
	
	public static void addItemNames(){
		LanguageRegistry.addName(cardboardItem, "CardBoard Piece");
	}
	
	public static void setHarvestLevels(){
		MinecraftForge.setBlockHarvestLevel(EDBlockManager.snowBrick, "shovel", 0);
		MinecraftForge.setBlockHarvestLevel(EDBlockManager.flintBlock, "pickaxe", 1);
		MinecraftForge.setBlockHarvestLevel(EDBlockManager.obsidianTile, "pickaxe", 3);
		MinecraftForge.setBlockHarvestLevel(EDBlockManager.gunpowderBlock, "shovel", 0);
		MinecraftForge.setBlockHarvestLevel(EDBlockManager.sugarBlock, "shovel", 0);
		MinecraftForge.setBlockHarvestLevel(EDBlockManager.ropeCoil, "shears", 0);
		MinecraftForge.setBlockHarvestLevel(EDBlockManager.rope, "shears", 0);
		MinecraftForge.setBlockHarvestLevel(EDBlockManager.flintTile, "pickaxe", 1);
		MinecraftForge.setBlockHarvestLevel(EDBlockManager.oozeSlime, "shovel", 0);
		MinecraftForge.setBlockHarvestLevel(EDBlockManager.magmaOoze, "shovel", 0);
		MinecraftForge.setBlockHarvestLevel(EDBlockManager.cobbledRoad, "pickaxe", 0);
	}
	
	public static void addItemsToItemList(){
		Item.itemsList[EDBlockManager.stoneLamp.blockID] = (new ItemMultiTextureTile(EDBlockManager.stoneLamp.blockID - 256, EDBlockManager.stoneLamp, BlockStoneLamp.lampType)).setUnlocalizedName("stoneLamp");
		Item.itemsList[EDBlockManager.woodPanel.blockID] = (new ItemMultiTextureTile(EDBlockManager.woodPanel.blockID - 256, EDBlockManager.woodPanel, BlockWoodPanel.woodType)).setUnlocalizedName("woodPanel");
		Item.itemsList[EDBlockManager.woodBeveled.blockID] = (new ItemMultiTextureTile(EDBlockManager.woodBeveled.blockID - 256, EDBlockManager.woodBeveled, BlockWoodBeveled.woodType)).setUnlocalizedName("woodBeveled");
		Item.itemsList[EDBlockManager.sandstoneBricks.blockID] = (new ItemMultiTextureTile(EDBlockManager.sandstoneBricks.blockID - 256, EDBlockManager.sandstoneBricks, BlockSandstoneBrick.sandType)).setUnlocalizedName("sandstoneBrick");
		Item.itemsList[EDBlockManager.woodBoards.blockID] = (new ItemMultiTextureTile(EDBlockManager.woodBoards.blockID - 256, EDBlockManager.woodBoards, BlockWoodBoards.woodType)).setUnlocalizedName("woodBoards");
		Item.itemsList[EDBlockManager.stainedBrick.blockID] = (new ItemMultiTextureTile(EDBlockManager.stainedBrick.blockID - 256, EDBlockManager.stainedBrick, BlockStainedBrick.brickType)).setUnlocalizedName("stainedBrick");
		
		//TODO Forms of adding slabs
		Item.itemsList[EDBlockManager.woodBoardsSingleSlab.blockID] = (new ItemSlab(EDBlockManager.woodBoardsSingleSlab.blockID - 256, (BlockHalfSlab)EDBlockManager.woodBoardsSingleSlab, (BlockHalfSlab)EDBlockManager.woodBoardsDoubleSlab, false));
		
		try 
		{
			EDBridgeRecipes.loadBridgeRecipes();
		} catch (Exception e) 
		{
			System.err.println("ExtraDecor: Could not load bridge recipes. Heres why: ");
			e.printStackTrace();
		}
	}

}
