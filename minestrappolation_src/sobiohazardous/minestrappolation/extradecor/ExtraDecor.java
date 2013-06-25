package sobiohazardous.minestrappolation.extradecor;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import sobiohazardous.minestrappolation.creativetabs.CreativeTabExtraDecorBlocks;
import sobiohazardous.minestrappolation.creativetabs.CreativeTabExtraoresBlocks;
import sobiohazardous.minestrappolation.extradecor.block.*;
import sobiohazardous.minestrappolation.extradecor.handler.ClientPacketHandler;
import sobiohazardous.minestrappolation.extradecor.handler.ServerPacketHandler;
import sobiohazardous.minestrappolation.extradecor.item.ItemBlockPlacer;
import sobiohazardous.minestrappolation.extradecor.lib.EDBlockRegistry;
import sobiohazardous.minestrappolation.extradecor.lib.EDNameManager;
import sobiohazardous.minestrappolation.extradecor.lib.EDRecipeManager;
import sobiohazardous.minestrappolation.extradecor.proxy.CommonProxy;
import net.minecraft.block.Block;
import net.minecraft.block.BlockGlass;
import net.minecraft.block.BlockGlowStone;
import net.minecraft.block.BlockObsidian;
import net.minecraft.block.BlockPane;
import net.minecraft.block.BlockWood;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemMultiTextureTile;
import net.minecraft.potion.Potion;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkMod.SidedPacketHandler;

/**
 * 
 * 
 * 
 * @author Crzyguitardude
 */
@NetworkMod(clientSideRequired = true, serverSideRequired = false,
clientPacketHandlerSpec = @SidedPacketHandler(channels = {"extraoresChan"}, packetHandler = ClientPacketHandler.class),
serverPacketHandlerSpec = @SidedPacketHandler(channels = {"extroresChan"}, packetHandler = ServerPacketHandler.class))
@Mod ( modid = "ExtraDecor", name="Extrappolated Decor", version="A1.0")
public class ExtraDecor 
{
	@SidedProxy(clientSide = "sobiohazardous.minestrappolation.extradecor.proxy.ClientProxy", serverSide = "sobiohazardous.minestrappolation.extradecor.proxy.CommonProxy")
    public static CommonProxy proxy;

	public static int
	stoneBlockRefinedId,
	stonePillarId,
	stoneTileId,
	stoneLampId,
	edgeStoneBrickId,
	edgeStoneLeftId,
	edgeStoneBrickRightId,
	brickPatternId,
	obsidianTileId,
	snowBrickId,
	endstoneSmoothId,
	endstoneRefinedId,
	endstoneBrickId,
	glassRefinedId,
	glassRefinedPaneId,
	woodPanelId,
	woodBeveledId,
	flintBlockId,
	gunpowderBlockId,
	ropeId,
	itemRopeId,
	ropeCoilId,
	oozeSlimeId,
	netherBrickPatternId,
	sandstoneBrickId;
	
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
	
	public static Block sandstoneBrick;
	
	public static CreativeTabs tabDecorBlocks = new CreativeTabExtraDecorBlocks(CreativeTabs.getNextID(),"Extrappolated Decor - Blocks");
	
	public static int paneRenderId = RenderingRegistry.getNextAvailableRenderId();
	public static int ropeRenderId = RenderingRegistry.getNextAvailableRenderId();
	
	@PreInit
	public void preLoad(FMLPreInitializationEvent event)
	{
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		config.load();
		//blocks 600
		
		stoneBlockRefinedId = config.getBlock("Stone Block Refined", 600).getInt();
		stonePillarId = config.getBlock("Stone Pillar", 601).getInt();
		stoneTileId = config.getBlock("Stone Tile", 602).getInt();
		stoneLampId = config.getBlock("Stone Lamp", 603).getInt();
		edgeStoneBrickId = config.getBlock("Edge Stone Brick", 604).getInt();
		edgeStoneLeftId = config.getBlock("Left Edgestone Brick", 605).getInt();
		edgeStoneBrickRightId = config.getBlock("Rigt Edgestone Brick", 606).getInt();
		brickPatternId = config.getBlock("Pattern Brick", 607).getInt();
		obsidianTileId = config.getBlock("Obsidian Tile", 608).getInt();
		snowBrickId = config.getBlock("Snow Brick", 609).getInt();
		endstoneSmoothId = config.getBlock("Smooth Endstone", 610).getInt();
		endstoneRefinedId = config.getBlock("Refined Endstone", 611).getInt();
		endstoneBrickId = config.getBlock("Endstone Brick", 612).getInt();
		glassRefinedId = config.getBlock("Refined Glass", 613).getInt();
		glassRefinedPaneId = config.getBlock("Refined Glass Pane", 614).getInt();
		woodPanelId = config.getBlock("Wood Panel", 615).getInt();
		woodBeveledId = config.getBlock("Beveled Wood", 616).getInt();
		flintBlockId = config.getBlock("Flint Block", 617).getInt();
		gunpowderBlockId = config.getBlock("Gunpowder Block", 618).getInt();
		ropeId = config.getBlock("Rope", 619).getInt();
		itemRopeId = config.getItem("Rope Item", 620).getInt();
		ropeCoilId = config.getBlock("Rope Coil", 621).getInt();
		oozeSlimeId = config.getBlock("Slime Oooze", 622).getInt();
		netherBrickPatternId = config.getBlock("Patterned Nether Brick", 623).getInt();
		
		config.save();
	}
	
	
	@Init
	public void load(FMLInitializationEvent event)
	{
		proxy.registerRenderThings();	
		
		stoneBlockRefined = (new EDBlock(stoneBlockRefinedId, Material.rock, "block_StoneRefined")).setHardness(2F).setResistance(5F).setCreativeTab(tabDecorBlocks).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("stoneBlockRefined");
		stonePillar = (new BlockStonePillar(stonePillarId, Material.rock)).setHardness(2F).setResistance(5F).setCreativeTab(tabDecorBlocks).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("stonePillar");
		stoneTile = (new EDBlock(stoneTileId, Material.rock, "block_StoneTile")).setHardness(3F).setResistance(5F).setCreativeTab(tabDecorBlocks).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("stoneTile");
		stoneLamp = (new BlockStoneLamp(stoneLampId)).setHardness(2.0F).setCreativeTab(tabDecorBlocks).setStepSound(Block.soundMetalFootstep).setLightValue(1.0F).setUnlocalizedName("stoneLamp");
		
		edgeStoneBrick = (new BlockEdgeStoneBrick(edgeStoneBrickId)).setHardness(4F).setResistance(6F).setCreativeTab(tabDecorBlocks).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("edgeStoneBrick");
		edgeStoneBrickLeft = (new BlockEdgeStoneBrickLeft(edgeStoneLeftId)).setHardness(4F).setResistance(6F).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("edgeStoneBrickLeft");
		edgeStoneBrickRight = (new BlockEdgeStoneBrickRight(edgeStoneBrickRightId)).setHardness(4F).setResistance(6F).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("edgeStoneBrickRight");
		
		brickPattern = (new EDBlock(brickPatternId, Material.rock, "block_PatternBrick")).setHardness(2F).setResistance(10F).setCreativeTab(tabDecorBlocks).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("brickPattern");
		
		obsidianTile = (new EDBlock(obsidianTileId, Material.rock, "block_ObsidianTile")).setHardness(50.0F).setResistance(2000.0F).setCreativeTab(tabDecorBlocks).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("obsidianTile");
		
		snowBrick = (new EDBlock(snowBrickId, Material.rock, "block_SnowBrick")).setHardness(3.0F).setResistance(1.0F).setCreativeTab(tabDecorBlocks).setStepSound(Block.soundSnowFootstep).setUnlocalizedName("snowBrick");
		
		endstoneSmooth = (new EDBlock(endstoneSmoothId, Material.rock, "block_EndstoneSmooth")).setHardness(3F).setResistance(15F).setCreativeTab(tabDecorBlocks).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("endstoneSmooth");
		endstoneRefined = (new EDBlock(endstoneRefinedId, Material.rock, "block_EndstoneRefined")).setHardness(3F).setResistance(15F).setCreativeTab(tabDecorBlocks).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("endstoneRefined");
		endstoneBrick = (new EDBlock(endstoneBrickId, Material.rock, "block_EndstoneBrick")).setHardness(3F).setResistance(15F).setCreativeTab(tabDecorBlocks).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("endstoneBrick");
		
		glassRefined = (new BlockGlassRefined(glassRefinedId, Material.glass, false)).setHardness(0.3F).setStepSound(Block.soundGlassFootstep).setCreativeTab(tabDecorBlocks).setUnlocalizedName("glassRefined");
		glassRefinedPane = (new BlockGlassRefinedPane(glassRefinedPaneId, "block_ClearGlass", "block_ClearGlassTop", Material.glass, false)).setHardness(0.3F).setStepSound(Block.soundGlassFootstep).setCreativeTab(tabDecorBlocks).setUnlocalizedName("glassRefinedPane");
		
		flintBlock = (new EDBlock(flintBlockId, Material.ground, "block_FlintBlock")).setHardness(3.0F).setResistance(5.0F).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("flintBlock");
		
		gunpowderBlock = (new BlockGunpowderBlock(gunpowderBlockId, Material.ground, "block_GunpowderBlock")).setHardness(3.0F).setResistance(5.0F).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("gunpowderBlock");
		
		rope = (new BlockRope(ropeId)).setHardness(2.0F).setResistance(4.0F).setStepSound(Block.soundLadderFootstep).setUnlocalizedName("rope");
		itemRope = (new ItemBlockPlacer(itemRopeId, "item_Rope", rope)).setUnlocalizedName("itemRope").setCreativeTab(tabDecorBlocks);
		ropeCoil = (new BlockRopeCoil(ropeCoilId)).setHardness(2.0F).setResistance(5.0F).setCreativeTab(tabDecorBlocks).setStepSound(Block.soundLadderFootstep).setUnlocalizedName("ropeCoil");
		
		oozeSlime = (new BlockSlimeOoze(oozeSlimeId)).setHardness(2.0F).setResistance(2.0F).setUnlocalizedName("oozeSlime");
		
		woodPanel = (new BlockWoodPanel(woodPanelId)).setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("woodPanel");
			
		woodBeveled = (new BlockWoodBeveled(woodBeveledId)).setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("woodBeveled");
		
		netherBrickPattern = (new EDBlock(netherBrickPatternId, Material.rock, "block_PatternBrickNether")).setHardness(2.5F).setResistance(11F).setCreativeTab(tabDecorBlocks).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("netherBrickPattern");
		
		sandstoneBrick = (new BlockSandstoneBrick(sandstoneBrickId)).setHardness(1F).setStepSound(Block.soundSandFootstep).setUnlocalizedName("sandstoneBrick");
		
		
		EDBlockRegistry.registerBlocks();
		EDNameManager.registerNames();
		EDRecipeManager.loadAllRecipes();
		
		MinecraftForge.setBlockHarvestLevel(snowBrick, "shovel", 0);
		MinecraftForge.setBlockHarvestLevel(flintBlock, "pickaxe", 1);
		MinecraftForge.setBlockHarvestLevel(obsidianTile, "pickaxe", 3);
		MinecraftForge.setBlockHarvestLevel(gunpowderBlock, "shovel", 0);
		MinecraftForge.setBlockHarvestLevel(ropeCoil, "shears", 0);
		MinecraftForge.setBlockHarvestLevel(rope, "shears", 0);
		//test
	}

	@PostInit
    public void postLoad(FMLPostInitializationEvent evt)
	{
		Item.itemsList[stoneLamp.blockID] = (new ItemMultiTextureTile(stoneLamp.blockID - 256, stoneLamp, BlockStoneLamp.lampType)).setUnlocalizedName("stoneLamp");
		Item.itemsList[woodPanel.blockID] = (new ItemMultiTextureTile(woodPanel.blockID - 256, woodPanel, BlockWoodPanel.woodType)).setUnlocalizedName("woodPanel");
		Item.itemsList[woodBeveled.blockID] = (new ItemMultiTextureTile(woodBeveled.blockID - 256, woodBeveled, BlockWoodBeveled.woodType)).setUnlocalizedName("woodBeveled");
		Item.itemsList[sandstoneBrick.blockID] = (new ItemMultiTextureTile(sandstoneBrick.blockID - 256, sandstoneBrick, BlockSandstoneBrick.sandType)).setUnlocalizedName("sandstoneBrick");
		
	}
}
