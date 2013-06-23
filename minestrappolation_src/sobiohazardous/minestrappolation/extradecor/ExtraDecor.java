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
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
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
	
	public static Block oakPanel;
	public static Block birchPanel;
	public static Block sprucePanel;
	public static Block junglePanel;
	
	public static Block oakBeveled;
	public static Block birchBeveled;
	public static Block spruceBeveled;
	public static Block jungleBeveled;
	
	public static Block flintBlock;
	
	public static Block gunpowderBlock;
	
	public static Block rope;
	public static Item itemRope;
	public static Block ropeCoil;
	
	public static Block oozeSlime;
	
	public static CreativeTabs tabDecorBlocks = new CreativeTabExtraDecorBlocks(CreativeTabs.getNextID(),"Extrappolated Decor - Blocks");
	
	public static int paneRenderId = RenderingRegistry.getNextAvailableRenderId();
	public static int ropeRenderId = RenderingRegistry.getNextAvailableRenderId();
	
	@Init
	public void load(FMLInitializationEvent event)
	{
		proxy.registerRenderThings();	
		
		stoneBlockRefined = (new EDBlock(600, Material.rock, "block_StoneRefined")).setHardness(2F).setResistance(5F).setCreativeTab(tabDecorBlocks).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("stoneBlockRefined");
		stonePillar = (new BlockStonePillar(601, Material.rock)).setHardness(2F).setResistance(5F).setCreativeTab(tabDecorBlocks).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("stonePillar");
		stoneTile = (new EDBlock(602, Material.rock, "block_StoneTile")).setHardness(3F).setResistance(5F).setCreativeTab(tabDecorBlocks).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("stoneTile");
		stoneLamp = (new EDBlock(603, Material.rock, "block_StoneLampGlowstone")).setHardness(2.0F).setCreativeTab(tabDecorBlocks).setStepSound(Block.soundGlassFootstep).setLightValue(1.0F).setUnlocalizedName("stoneLamp");
		
		edgeStoneBrick = (new BlockEdgeStoneBrick(604)).setHardness(4F).setResistance(6F).setCreativeTab(tabDecorBlocks).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("edgeStoneBrick");
		edgeStoneBrickLeft = (new BlockEdgeStoneBrickLeft(605)).setHardness(4F).setResistance(6F).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("edgeStoneBrickLeft");
		edgeStoneBrickRight = (new BlockEdgeStoneBrickRight(606)).setHardness(4F).setResistance(6F).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("edgeStoneBrickRight");
		
		brickPattern = (new EDBlock(607, Material.rock, "block_PatternBrick")).setHardness(2F).setResistance(10F).setCreativeTab(tabDecorBlocks).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("brickPattern");
		
		obsidianTile = (new EDBlock(608, Material.rock, "block_ObsidianTile")).setHardness(50.0F).setResistance(2000.0F).setCreativeTab(tabDecorBlocks).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("obsidianTile");
		
		snowBrick = (new EDBlock(609, Material.rock, "block_SnowBrick")).setHardness(10.0F).setResistance(1.0F).setCreativeTab(tabDecorBlocks).setStepSound(Block.soundSnowFootstep).setUnlocalizedName("snowBrick");
		
		endstoneSmooth = (new EDBlock(610, Material.rock, "block_EndstoneSmooth")).setHardness(3F).setResistance(15F).setCreativeTab(tabDecorBlocks).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("endstoneSmooth");
		endstoneRefined = (new EDBlock(611, Material.rock, "block_EndstoneRefined")).setHardness(3F).setResistance(15F).setCreativeTab(tabDecorBlocks).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("endstoneRefined");
		endstoneBrick = (new EDBlock(612, Material.rock, "block_EndstoneBrick")).setHardness(3F).setResistance(15F).setCreativeTab(tabDecorBlocks).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("endstoneBrick");
		
		glassRefined = (new BlockGlassRefined(613, Material.glass, false)).setHardness(0.3F).setStepSound(Block.soundGlassFootstep).setCreativeTab(tabDecorBlocks).setUnlocalizedName("glassRefined");
		glassRefinedPane = (new BlockGlassRefinedPane(614, "block_ClearGlass", "block_ClearGlassTop", Material.glass, false)).setHardness(0.3F).setStepSound(Block.soundGlassFootstep).setCreativeTab(tabDecorBlocks).setUnlocalizedName("glassRefinedPane");
		
		flintBlock = (new EDBlock(616, Material.ground, "block_FlintBlock")).setHardness(3.0F).setResistance(5.0F).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("flintBlock");
		
		gunpowderBlock = (new BlockGunpowderBlock(617, Material.ground, "block_GunpowderBlock")).setHardness(3.0F).setResistance(5.0F).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("gunpowderBlock");
		
		rope = (new BlockRope(618)).setHardness(2.0F).setResistance(4.0F).setStepSound(Block.soundLadderFootstep).setUnlocalizedName("rope");
		itemRope = (new ItemBlockPlacer(900, "item_Rope", rope)).setUnlocalizedName("itemRope").setCreativeTab(tabDecorBlocks);
		ropeCoil = (new BlockRopeCoil(619)).setHardness(2.0F).setResistance(5.0F).setCreativeTab(tabDecorBlocks).setStepSound(Block.soundLadderFootstep).setUnlocalizedName("ropeCoil");
		
		oozeSlime = (new BlockSlimeOoze(620)).setHardness(2.0F).setResistance(2.0F).setUnlocalizedName("oozeSlime");
		
		oakPanel = (new EDBlock(622, Material.wood, "block_PanelOak")).setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("oakPanel");
		birchPanel = (new EDBlock(623, Material.wood, "block_PanelBirch")).setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("birchPanel");
		sprucePanel = (new EDBlock(624, Material.wood, "block_PanelSpruce")).setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("sprucePanel");
		junglePanel = (new EDBlock(625, Material.wood, "block_PanelJungle")).setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("junglePanel");
		
		oakBeveled = (new EDBlock(626, Material.wood, "block_BeveledOak")).setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("oakBeveled");
		birchBeveled = (new EDBlock(627, Material.wood, "block_BeveledBirch")).setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("birchBeveled");
		spruceBeveled = (new EDBlock(628, Material.wood, "block_BeveledSpruce")).setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("spruceBeveled");
		jungleBeveled = (new EDBlock(629, Material.wood, "block_BeveledJungle")).setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("jungleBeveled");
		
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
	
	@PreInit
	public void preLoad(FMLPreInitializationEvent event)
	{
		
	}
	
	@PostInit
    public void postLoad(FMLPostInitializationEvent evt)
	{
		
	}
}
