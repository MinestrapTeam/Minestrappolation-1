package sobiohazardous.minestrappolation.extradecor.block;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import sobiohazardous.minestrappolation.extradecor.CreativeTabExtraDecorBlocks;
import sobiohazardous.minestrappolation.extradecor.EDConfig;
import sobiohazardous.minestrappolation.extradecor.ExtraDecor;
import sobiohazardous.minestrappolation.extradecor.item.EDItem;
import sobiohazardous.minestrappolation.extradecor.item.EDItemManager;
import sobiohazardous.minestrappolation.extradecor.item.ItemBlockPlacer;
import sobiohazardous.minestrappolation.extradecor.material.MaterialOoze;
import net.minecraft.block.Block;
import net.minecraft.block.BlockHalfSlab;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class EDBlockManager {
	
	public static final Material materialOoze = new MaterialOoze(MapColor.foliageColor);
	
	public static Block stoneBlockRefined;
	public static Block stonePillar;
	public static Block stoneTile;
	public static Block stoneLamp;
	
	public static Block edgeStoneBrick;
	public static Block edgeStoneBrickLeft;
	public static Block edgeStoneBrickRight;
	
	public static Block brickPattern;
	
	public static Block obsidianTile;
	
	public static Block snowBrick;
	
	public static Block endstoneSmooth;
	public static Block endstoneRefined;
	public static Block endstoneBrick;
	
	public static Block glassRefined;
	public static Block glassRefinedPane;
	
	public static Block woodPanel;
	
	public static Block woodBeveled;

	public static Block flintBlock;
	
	public static Block gunpowderBlock;
	
	public static Block rope;
	public static Item itemRope;
	public static Block ropeCoil;
	
	public static Block oozeSlime;
	
	public static Block netherBrickPattern;
	
	public static Block sandstoneBricks;	
	public static Block sandstonePillar;
	
	public static Block woodBoards;
	
	public static Block flintTile;
	
	public static Block netherQuartzTile;
	
	public static Block sugarBlock;
	public static Block meatBlock;
	
	public static Block magmaOoze;
	
	public static Block enderBlock;
	
	public static Block crate;
	public static Block barrel;
	
	public static Block cardboard;
	public static Block cardboardBlock;
	public static Block cardboardWet;
	
	public static Item sandstoneBrickItem;
	public static Item stoneBrickItem;
	
	public static Block checkerTile;
	
	public static Block woodBoardsStairsOak;
	public static Block woodBoardsStairsBirch;
	public static Block woodBoardsStairsSpruce;
	public static Block woodBoardsStairsJungle;
	public static BlockHalfSlab woodBoardsSingleSlab;
	public static BlockHalfSlab woodBoardsDoubleSlab;
	
	public static Block checkerTileStairs;
	
	public static Block stainedBrick;
	
	public static Block cobbledRoad;
	
	public static CreativeTabs tabDecorBlocks = new CreativeTabExtraDecorBlocks(CreativeTabs.getNextID(),"Extrappolated Decor");
	public static void createBlocks(){
		
	stoneBlockRefined = (new EDBlock(EDConfig.stoneBlockRefinedId, Material.rock, "block_StoneRefined")).setHardness(1.5F).setResistance(10F).setStepSound(Block.soundStoneFootstep).setCreativeTab(tabDecorBlocks).setUnlocalizedName("stoneBlockRefined");
	stonePillar = (new BlockPillar(EDConfig.stonePillarId, "block_StonePillar", "block_StoneRefined")).setHardness(1.5F).setResistance(10F).setCreativeTab(tabDecorBlocks).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("stonePillar");
	stoneTile = (new EDBlock(EDConfig.stoneTileId, Material.rock, "block_StoneTile")).setHardness(1.5F).setResistance(10F).setCreativeTab(tabDecorBlocks).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("stoneTile");
	stoneLamp = (new BlockStoneLamp(EDConfig.stoneLampId)).setHardness(1.5F).setResistance(8F).setCreativeTab(tabDecorBlocks).setStepSound(Block.soundStoneFootstep).setLightValue(1.0F).setUnlocalizedName("stoneLamp");
	
	edgeStoneBrick = (new BlockEdgeStoneBrick(EDConfig.edgeStoneBrickId)).setHardness(1.5F).setResistance(10F).setCreativeTab(tabDecorBlocks).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("edgeStoneBrick");
	edgeStoneBrickLeft = (new BlockEdgeStoneBrickLeft(EDConfig.edgeStoneLeftId)).setHardness(1.5F).setResistance(10F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("edgeStoneBrickLeft");
	edgeStoneBrickRight = (new BlockEdgeStoneBrickRight(EDConfig.edgeStoneBrickRightId)).setHardness(1.5F).setResistance(10F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("edgeStoneBrickRight");
	
	brickPattern = (new EDBlock(EDConfig.brickPatternId, Material.rock, "block_PatternBrick")).setHardness(2.0F).setResistance(10.0F).setCreativeTab(tabDecorBlocks).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("brickPattern");
	
	obsidianTile = (new EDBlock(EDConfig.obsidianTileId, Material.rock, "block_ObsidianTile")).setHardness(50.0F).setResistance(2000.0F).setCreativeTab(tabDecorBlocks).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("obsidianTile");
	
	snowBrick = (new EDBlock(EDConfig.snowBrickId, Material.snow, "block_SnowBrick")).setHardness(0.3F).setCreativeTab(tabDecorBlocks).setStepSound(Block.soundSnowFootstep).setUnlocalizedName("snowBrick");
	
	endstoneSmooth = (new EDBlock(EDConfig.endstoneSmoothId, Material.rock, "block_EndstoneSmooth")).setHardness(3.0F).setResistance(15.0F).setCreativeTab(tabDecorBlocks).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("endstoneSmooth");
	endstoneRefined = (new EDBlock(EDConfig.endstoneRefinedId, Material.rock, "block_EndstoneRefined")).setHardness(3.5F).setResistance(15.0F).setCreativeTab(tabDecorBlocks).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("endstoneRefined");
	endstoneBrick = (new EDBlock(EDConfig.endstoneBrickId, Material.rock, "block_EndstoneBrick")).setHardness(3.5F).setResistance(15.0F).setCreativeTab(tabDecorBlocks).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("endstoneBrick");
	
	glassRefined = (new BlockGlassRefined(EDConfig.glassRefinedId, Material.glass, false)).setHardness(0.3F).setStepSound(Block.soundGlassFootstep).setCreativeTab(tabDecorBlocks).setUnlocalizedName("glassRefined");
	glassRefinedPane = (new EDBlockPane(EDConfig.glassRefinedPaneId, "block_ClearGlass", "block_ClearGlassTop", Material.glass, false)).setHardness(0.3F).setStepSound(Block.soundGlassFootstep).setCreativeTab(tabDecorBlocks).setUnlocalizedName("glassRefinedPane");
	
	flintBlock = (new EDBlock(EDConfig.flintBlockId, Material.rock, "block_FlintBlock")).setHardness(3F).setResistance(10.0F).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("flintBlock");
	
	gunpowderBlock = (new BlockGunpowderBlock(EDConfig.gunpowderBlockId, Material.ground, "block_GunpowderBlock")).setHardness(0.6F).setStepSound(Block.soundGravelFootstep).setUnlocalizedName("gunpowderBlock");
	
	rope = (new BlockRope(EDConfig.ropeId)).setHardness(0.9F).setStepSound(Block.soundClothFootstep).setUnlocalizedName("rope");
	itemRope = (new ItemBlockPlacer(EDConfig.itemRopeId, "item_Rope", rope)).setUnlocalizedName("itemRope").setCreativeTab(tabDecorBlocks);
	ropeCoil = (new BlockRopeCoil(EDConfig.ropeCoilId)).setHardness(0.9F).setCreativeTab(tabDecorBlocks).setStepSound(Block.soundClothFootstep).setUnlocalizedName("ropeCoil");
	
	oozeSlime = (new BlockOoze(EDConfig.oozeSlimeId, materialOoze, "block_SlimeOoze")).setHardness(1F).setResistance(2000F).setStepSound(Block.soundClothFootstep).setUnlocalizedName("oozeSlime");
	
	woodPanel = (new BlockWoodPanel(EDConfig.woodPanelId)).setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("woodPanel");
		
	woodBeveled = (new BlockWoodBeveled(EDConfig.woodBeveledId)).setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("woodBeveled");
	
	netherBrickPattern = (new EDBlock(EDConfig.netherBrickPatternId, Material.rock, "block_PatternBrickNether")).setHardness(2.0F).setResistance(10.0F).setCreativeTab(tabDecorBlocks).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("netherBrickPattern");
	
	sandstoneBricks = (new BlockSandstoneBrick(EDConfig.sandstoneBrickId)).setHardness(1F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("sandstoneBrick");
	sandstonePillar = new BlockPillar(EDConfig.sandstonePillarId, "block_SandstonePillarSide", "block_SandstonePillarTop").setUnlocalizedName("sandstonePillar").setHardness(1F).setStepSound(Block.soundStoneFootstep);
	
	woodBoards = new BlockWoodBoards(EDConfig.woodBoardsId).setHardness(2.0F).setResistance(5.0F).setUnlocalizedName("woodBoards").setStepSound(Block.soundWoodFootstep);
	//TODO add the rest of the boards after Extrapolated Nature
	
	flintTile = new EDBlock(EDConfig.flintTileId, Material.rock, "block_FlintTile").setUnlocalizedName("flintTile").setHardness(3F).setResistance(10.0F).setCreativeTab(tabDecorBlocks);
	
	netherQuartzTile = new EDBlock(EDConfig.netherQuartzTileId, Material.rock, "block_NetherTile").setHardness(0.8F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("netherQuartzTile");

	sugarBlock = new BlockSugarBlock(EDConfig.sugarBlockId, "block_SugarBlock").setHardness(0.6F).setStepSound(Block.soundGravelFootstep).setUnlocalizedName("sugarBlock");
	meatBlock = new BlockMeatBlock(EDConfig.meatBlockId, "block_MeatBlock").setHardness(0.8F).setStepSound(Block.soundClothFootstep).setUnlocalizedName("meatBlock");
	
	magmaOoze = new BlockOoze(EDConfig.magmaOozeId, materialOoze, "block_MagmaOoze").setHardness(1F).setResistance(2000F).setStepSound(Block.soundClothFootstep).setUnlocalizedName("magmaOoze");
	
	enderBlock = new BlockEnderblock(EDConfig.enderBlockId).setHardness(3.0F).setResistance(4.0F).setUnlocalizedName("enderBlock").setStepSound(Block.soundGlassFootstep).setCreativeTab(tabDecorBlocks);
	
	crate = new BlockCrate(EDConfig.crateId).setHardness(2.5F).setStepSound(Block.soundWoodFootstep).setCreativeTab(tabDecorBlocks).setUnlocalizedName("crate");
	barrel = new BlockBarrel(EDConfig.barrelId).setHardness(3F).setResistance(6.0F).setStepSound(Block.soundWoodFootstep).setCreativeTab(tabDecorBlocks).setUnlocalizedName("barrel");
	
	cardboard = new EDBlockPane(EDConfig.cardboardId, "block_CardboardBlock", "block_CardboardEdge", Material.cloth, true).setHardness(0.3F).setUnlocalizedName("cardboard");
	cardboardBlock = new BlockCardboard(EDConfig.cardboardBlockId, Material.cloth, "block_CardboardBlock").setHardness(0.4F).setUnlocalizedName("cardboardBlock").setCreativeTab(tabDecorBlocks);
	cardboardWet = new BlockCardboardWet(EDConfig.cardboardWetId, Material.cloth).setCreativeTab(tabDecorBlocks).setHardness(0.2F).setResistance(0.8F).setUnlocalizedName("cardboardWet");
	
	sandstoneBrickItem = new EDItem(EDConfig.sandstoneBrickItemId, "item_SandstoneBrick").setUnlocalizedName("sandstoneBrickItem");
	stoneBrickItem = new EDItem(EDConfig.stoneBrickItemId, "item_StoneBrick").setUnlocalizedName("stoneBrickItem");
	
	checkerTile = new EDBlock(EDConfig.checkerTileId, Material.rock, "block_CheckerTile").setUnlocalizedName("checkerTile").setHardness(3F).setResistance(10.0F).setStepSound(Block.soundStoneFootstep);
	
	woodBoardsStairsOak = new EDBlockStairs(EDConfig.woodBoardsStairsOakId, woodBoards, 0).setUnlocalizedName("woodBoardsStairsOak").setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundWoodFootstep);
	woodBoardsStairsBirch = new EDBlockStairs(EDConfig.woodBoardsStairsBirchId, woodBoards, 1).setUnlocalizedName("woodBoardsStairsBirch").setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundWoodFootstep);
	woodBoardsStairsSpruce = new EDBlockStairs(EDConfig.woodBoardsStairsSpruceId, woodBoards, 2).setUnlocalizedName("woodBoardsStairsSpruce").setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundWoodFootstep);
	woodBoardsStairsJungle = new EDBlockStairs(EDConfig.woodBoardsStairsJungleId, woodBoards, 3).setUnlocalizedName("woodBoardsStairsJungle").setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundWoodFootstep);
	woodBoardsSingleSlab = (BlockHalfSlab) new BlockWoodBoardSlab(EDConfig.woodBoardsSingleSlabId, false).setUnlocalizedName("woodBoardsSingleSlab").setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundWoodFootstep);
	woodBoardsDoubleSlab = (BlockHalfSlab) new BlockWoodBoardSlab(EDConfig.woodBoardsDoubleSlabId, true).setUnlocalizedName("woodBoardsSingleSlab").setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundWoodFootstep);

	checkerTileStairs = new EDBlockStairs(EDConfig.checkerTileStairsId, checkerTile, 0).setUnlocalizedName("checkerTileStairs").setHardness(3F).setResistance(10.0F).setStepSound(Block.soundStoneFootstep);
	
	stainedBrick = (new BlockStainedBrick(EDConfig.stainedBrickId)).setHardness(2F).setResistance(10F).setCreativeTab(tabDecorBlocks).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("stainedBrick");
	
	cobbledRoad = (new BlockCobbledRoad(EDConfig.cobbledRoadId)).setHardness(1).setResistance(5F).setCreativeTab(tabDecorBlocks).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("cobbledRoad");
	
	

	}
	
	public static void registerBlocks(){
		GameRegistry.registerBlock(stoneBlockRefined, "refinedStoneBrick");
		GameRegistry.registerBlock(stonePillar, "stonePillar");
		GameRegistry.registerBlock(stoneTile, "stoneTile");
		GameRegistry.registerBlock(stoneLamp, "stoneLamp");
		GameRegistry.registerBlock(edgeStoneBrick, "edgeStoneBrick");
		GameRegistry.registerBlock(edgeStoneBrickLeft, "edgeStoneBrickLeft");
		GameRegistry.registerBlock(edgeStoneBrickRight, "edgeStoneBrickRight");
		GameRegistry.registerBlock(brickPattern, "brickPattern");
		GameRegistry.registerBlock(obsidianTile, "Obsidian Tile");
		GameRegistry.registerBlock(snowBrick, "snowBrick");
		GameRegistry.registerBlock(endstoneSmooth, "endstoneSmooth");
		GameRegistry.registerBlock(endstoneRefined, "endstoneRefined");
		GameRegistry.registerBlock(endstoneBrick, "endstoneBrick");
		GameRegistry.registerBlock(glassRefined, "glassRefined");
		GameRegistry.registerBlock(glassRefinedPane, "glassRefinedPane");
		GameRegistry.registerBlock(flintBlock, "flintBlock");
		GameRegistry.registerBlock(gunpowderBlock, "gunpowderBlock");
		GameRegistry.registerBlock(rope, "rope");
		GameRegistry.registerBlock(ropeCoil, "ropeCoil");
		GameRegistry.registerBlock(woodPanel, "woodPanel");
		GameRegistry.registerBlock(woodBeveled, "woodBeveled");
		GameRegistry.registerBlock(oozeSlime, "oozeSlime");
		GameRegistry.registerBlock(netherBrickPattern, "netherBrickPattern");
		GameRegistry.registerBlock(sandstoneBricks, "sandstoneBrick");
		GameRegistry.registerBlock(sandstonePillar, "sandstonePillar");
		GameRegistry.registerBlock(woodBoards, "woodBoards");
		GameRegistry.registerBlock(flintTile, "flintTile");
		GameRegistry.registerBlock(netherQuartzTile, "netherQuartzTile");
		GameRegistry.registerBlock(sugarBlock, "sugarBlock");
		GameRegistry.registerBlock(meatBlock, "meatBlock");
		GameRegistry.registerBlock(magmaOoze, "magmaOoze");
		GameRegistry.registerBlock(enderBlock, "enderBlock");
		GameRegistry.registerBlock(crate, "crate");
		GameRegistry.registerBlock(barrel, "barrel");
		GameRegistry.registerBlock(cardboard, "cardboard");
		GameRegistry.registerBlock(cardboardBlock, "cardboardBlock");
		GameRegistry.registerBlock(cardboardWet, "cardboardWet");
		GameRegistry.registerBlock(checkerTile, "checkerTile");
		GameRegistry.registerBlock(woodBoardsStairsOak, "woodBoardStairsOak");
		GameRegistry.registerBlock(woodBoardsStairsBirch, "woodBoardStairsBirch");
		GameRegistry.registerBlock(woodBoardsStairsSpruce, "woodBoardStairsSpruce");
		GameRegistry.registerBlock(woodBoardsStairsJungle, "woodBoardStairsJungle");
		GameRegistry.registerBlock(woodBoardsSingleSlab, "woodBoardsSingleSlab");
		GameRegistry.registerBlock(woodBoardsDoubleSlab, "woodBoardsDoubleSlab");
		GameRegistry.registerBlock(checkerTileStairs, "checkerTileStairs");
		GameRegistry.registerBlock(stainedBrick, "stainedBrick");
		GameRegistry.registerBlock(cobbledRoad, "cobbledRoad");
	}
	
	public static void addNames(){
		LanguageRegistry.addName(stoneBlockRefined, "Refined Stone Block");
		LanguageRegistry.addName(stonePillar, "Stone Pillar");
		LanguageRegistry.addName(stoneTile, "Stone Tile");
		LanguageRegistry.addName(edgeStoneBrick, "Edge Stone Brick");
		LanguageRegistry.addName(brickPattern, "Patterned Brick");
		LanguageRegistry.addName(obsidianTile, "Obisidian Tile");
		LanguageRegistry.addName(snowBrick, "Snow Brick");
		LanguageRegistry.addName(endstoneSmooth, "Smooth End Stone");
		LanguageRegistry.addName(endstoneRefined, "Refined End Stone");
		LanguageRegistry.addName(endstoneBrick, "End Stone Brick");
		LanguageRegistry.addName(glassRefined, "Refined Glass");
		LanguageRegistry.addName(glassRefinedPane, "Refined Glass Pane");
		LanguageRegistry.addName(flintBlock, "Block of Flint");
		LanguageRegistry.addName(gunpowderBlock, "Block of Gunpowder");
		LanguageRegistry.addName(itemRope, "Rope");
		LanguageRegistry.addName(ropeCoil, "Rope Coil");
		LanguageRegistry.addName(oozeSlime, "Slime Ooze");
		LanguageRegistry.instance().addStringLocalization("tile.stoneLamp.glowstone.name", "Glowstone Stone Lamp");
		LanguageRegistry.instance().addStringLocalization("tile.stoneLamp.sunstone.name", "Sunstone Stone Lamp");
		LanguageRegistry.instance().addStringLocalization("tile.woodPanel.oak.name", "Oak Wood Panel");
		LanguageRegistry.instance().addStringLocalization("tile.woodPanel.birch.name", "Birch Wood Panel");
		LanguageRegistry.instance().addStringLocalization("tile.woodPanel.spruce.name", "Spruce Wood Panel");
		LanguageRegistry.instance().addStringLocalization("tile.woodPanel.jungle.name", "Jungle Wood Panel");
		LanguageRegistry.instance().addStringLocalization("tile.woodBeveled.oak.name", "Beveled Oak Wood Panel");
		LanguageRegistry.instance().addStringLocalization("tile.woodBeveled.birch.name", "Beveled Birch Wood Panel");
		LanguageRegistry.instance().addStringLocalization("tile.woodBeveled.spruce.name", "Beveled Spruce Wood Panel");
		LanguageRegistry.instance().addStringLocalization("tile.woodBeveled.jungle.name", "Beveled Jungle Wood Panel");
		LanguageRegistry.addName(netherBrickPattern, "Patterned Nether Bricks");
		LanguageRegistry.instance().addStringLocalization("tile.sandstoneBrick.brick.name", "Sandstone Bricks");
		LanguageRegistry.instance().addStringLocalization("tile.sandstoneBrick.chiseled.name", "Chiseled Sandstone Bricks");
		LanguageRegistry.instance().addStringLocalization("tile.sandstoneBrick.mossy.name", "Mossy Sandstone Bricks");
		LanguageRegistry.instance().addStringLocalization("tile.sandstoneBrick.heiroglyph.name", "Sandstone Heiroglyphs");
		LanguageRegistry.instance().addStringLocalization("tile.sandstoneBrick.heiroglyph_2.name", "Sandstone Heiroglyphs");
		LanguageRegistry.addName(sandstonePillar, "Sandstone Pillar");
		LanguageRegistry.instance().addStringLocalization("tile.woodBoards.oak.name", "Oak Wood Boards");
		LanguageRegistry.instance().addStringLocalization("tile.woodBoards.birch.name", "Birch Wood Boards");
		LanguageRegistry.instance().addStringLocalization("tile.woodBoards.spruce.name", "Spruce Wood Boards");
		LanguageRegistry.instance().addStringLocalization("tile.woodBoards.jungle.name", "Jungle Wood Boards");
		LanguageRegistry.addName(flintTile, "Flint Tile");
		LanguageRegistry.addName(netherQuartzTile, "Nether Quartz Tile");
		LanguageRegistry.addName(sugarBlock, "Block of Sugar");
		LanguageRegistry.addName(meatBlock, "Block of Meat");
		LanguageRegistry.addName(magmaOoze, "Magma Ooze");
		LanguageRegistry.addName(enderBlock, "Ender Block");
		LanguageRegistry.addName(crate, "Crate");
		LanguageRegistry.addName(barrel, "Barrel");
		//LanguageRegistry.addName(EDItemManager.cardboardItem, "Cardboard");
		LanguageRegistry.addName(cardboardBlock, "Cardboard Block");
		LanguageRegistry.addName(cardboardWet, "Wet Cardboard");
		LanguageRegistry.addName(sandstoneBrickItem, "Sandstone Brick");
		LanguageRegistry.addName(stoneBrickItem, "Stone Brick");
		LanguageRegistry.addName(checkerTile, "Checker Tile");
		LanguageRegistry.addName(woodBoardsStairsOak, "Oak Board Stairs");
		LanguageRegistry.addName(woodBoardsStairsBirch, "Birch Board Stairs");
		LanguageRegistry.addName(woodBoardsStairsSpruce, "Spruce Board Stairs");
		LanguageRegistry.addName(woodBoardsStairsJungle, "Jungle Board Stairs");
		LanguageRegistry.instance().addStringLocalization("tile.woodBoardsSingleSlab.oak.name", "Oak Board Slab");
		LanguageRegistry.instance().addStringLocalization("tile.woodBoardsSingleSlab.birch.name", "Birch Board Slab");
		LanguageRegistry.instance().addStringLocalization("tile.woodBoardsSingleSlab.spruce.name", "Spruce Board Slab");
		LanguageRegistry.instance().addStringLocalization("tile.woodBoardsSingleSlab.jungle.name", "Jungle Board Slab");	
		LanguageRegistry.addName(checkerTileStairs, "Checker Tile Stairs");
		LanguageRegistry.instance().addStringLocalization("tile.stainedBrick.white.name", "White Stained Bricks");
		LanguageRegistry.instance().addStringLocalization("tile.stainedBrick.lightGrey.name", "Light Grey Stained Bricks");
		LanguageRegistry.instance().addStringLocalization("tile.stainedBrick.darkGrey.name", "Grey Stained Bricks");
		LanguageRegistry.instance().addStringLocalization("tile.stainedBrick.black.name", "Black Stained Bricks");
		LanguageRegistry.instance().addStringLocalization("tile.stainedBrick.brown.name", "Brown Stained Bricks");
		LanguageRegistry.instance().addStringLocalization("tile.stainedBrick.pink.name", "Pink Stained Bricks");
		LanguageRegistry.instance().addStringLocalization("tile.stainedBrick.red.name", "Red Stained Bricks");
		LanguageRegistry.instance().addStringLocalization("tile.stainedBrick.orange.name", "Orange Stained Bricks");
		LanguageRegistry.instance().addStringLocalization("tile.stainedBrick.yellow.name", "Yellow Stained Bricks");
		LanguageRegistry.instance().addStringLocalization("tile.stainedBrick.lime.name", "Lime Stained Bricks");
		LanguageRegistry.instance().addStringLocalization("tile.stainedBrick.green.name", "Green Stained Bricks");
		LanguageRegistry.instance().addStringLocalization("tile.stainedBrick.cyan.name", "Cyan Stained Bricks");
		LanguageRegistry.instance().addStringLocalization("tile.stainedBrick.lightBlue.name", "Light Blue Stained Bricks");
		LanguageRegistry.instance().addStringLocalization("tile.stainedBrick.blue.name", "Blue Stained Bricks");
		LanguageRegistry.instance().addStringLocalization("tile.stainedBrick.purple.name", "Purple Stained Bricks");
		LanguageRegistry.instance().addStringLocalization("tile.stainedBrick.magenta.name", "Magenta Stained Bricks");
		LanguageRegistry.addName(cobbledRoad, "Cobbled Road");
	}
}
