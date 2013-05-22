package sobiohazardous.minestrappolation.extraores;

import java.util.Map;
import java.util.Random;
import java.io.File;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.EnumSet;
import java.util.logging.Level;

import javax.swing.text.html.parser.Entity;

import org.lwjgl.input.Keyboard;

import sobiohazardous.minestrappolation.creativetabs.CreativeTabExtraoresBlocks;
import sobiohazardous.minestrappolation.creativetabs.CreativeTabExtraoresItems;
import sobiohazardous.minestrappolation.extraores.block.*;
import sobiohazardous.minestrappolation.extraores.entity.EntityGrenade;
import sobiohazardous.minestrappolation.extraores.entity.EntityGrenadeImpact;
import sobiohazardous.minestrappolation.extraores.entity.EntityGrenadeSticky;
import sobiohazardous.minestrappolation.extraores.entity.EntityNukePrimed;
import sobiohazardous.minestrappolation.extraores.entity.EntityPlutoniumPrimed;
import sobiohazardous.minestrappolation.extraores.entity.RenderGrenade;
import sobiohazardous.minestrappolation.extraores.entity.RenderNukePrimed;
import sobiohazardous.minestrappolation.extraores.generation.ExtracraftOreGenerator;
import sobiohazardous.minestrappolation.extraores.handler.ClientPacketHandler;
import sobiohazardous.minestrappolation.extraores.handler.ClientTickHandler;
import sobiohazardous.minestrappolation.extraores.handler.EOGuiHandler;
import sobiohazardous.minestrappolation.extraores.handler.ServerPacketHandler;
import sobiohazardous.minestrappolation.extraores.handler.ServerTickHandler;
import sobiohazardous.minestrappolation.extraores.item.*;
import sobiohazardous.minestrappolation.extraores.misc.PlutoniumFuelHandler;
import sobiohazardous.minestrappolation.extraores.misc.UraniumFuelHandler;
import sobiohazardous.minestrappolation.extraores.plate.*;
import sobiohazardous.minestrappolation.extraores.proxy.CommonProxy;
import sobiohazardous.potionapi.PotionAPI;

import net.minecraft.block.Block;
import net.minecraft.block.BlockHalfSlab;
import net.minecraft.block.BlockStep;
import net.minecraft.block.BlockTNT;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemSimpleFoiled;
import net.minecraft.item.ItemSnowball;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.src.BaseMod;
import net.minecraft.src.ModLoader;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.EnumHelper;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.IWorldGenerator;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.Mod.ServerStarted;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartedEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkMod.SidedPacketHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

/**
 * 
 * 
 * 
 * @author Crzyguitardude
 */

@NetworkMod(clientSideRequired = true, serverSideRequired = true,
clientPacketHandlerSpec = @SidedPacketHandler(channels = {"extraoresChan"}, packetHandler = ClientPacketHandler.class),
serverPacketHandlerSpec = @SidedPacketHandler(channels = {"extroresChan"}, packetHandler = ServerPacketHandler.class))
@Mod ( modid = "ExtraoresID", name="Extrappolated Ores", version="A1.3.1")
public class ExtraOres 
{
	//Items
	
	@SidedProxy(clientSide = "sobiohazardous.minestrappolation.extraores.proxy.ClientProxy", serverSide = "sobiohazardous.minestrappolation.extraores.proxy.CommonProxy")
    public static CommonProxy proxy;
	
	public static final Block ZirconiumOre;
	public static final Block ZirconiumBlock;
	
	public static Item ZirconiumIngot;
	
	public static Item ZirconiumSword;
	public static Item ZirconiumPickaxe;
	public static Item ZirconiumAxe;
	public static Item ZirconiumHoe;
	public static Item ZirconiumShovel;
	
	public static Item ZirconiumHelmet;
	public static Item ZirconiumChest;
	public static Item ZirconiumPants;
	public static Item ZirconiumBoots;
	
	public static final Block UraniumOre;
	public static Item Uranium;
	public static Block RawUraniumBlock;
	
	public static final Block PlutoniumOre;
	public static Item Plutonium;
	public static Block RawPlutoniumBlock;
	
	public static final Block TitaniumOre;
	public static Item TitaniumIngot;
	
	public static Item TitaniumSword;
	public static Item TitaniumPickaxe;
	public static Item TitaniumAxe;
	public static Item TitaniumHoe;
	public static Item TitaniumShovel;
	
	public static Item TitaniumHelmet;
	public static Item TitaniumChest;
	public static Item TitaniumPants;
	public static Item TitaniumBoots;
	
	public static final Block TitaniumBlock;
	
	public static final Block Sunstone;
	public static Item SunstoneDust;
	public static final Block SunstoneOre;
	
	public static final Block ToriteOre; 
	public static Item ToriteIngot;
	public static final Block ToriteBlock;
	
	public static Item ToriteSword;
	public static Item ToritePickaxe;
	public static Item ToriteAxe;
	public static Item ToriteHoe;
	public static Item ToriteShovel;
	
	public static Item ToriteHelmet;
	public static Item ToriteChest;
	public static Item ToritePants;
	public static Item ToriteBoots;
	
	public static final Block Granite;
	public static final Block GraniteBrick;
	
	public static final Block Quartzite;
	public static final Block QuartziteTile;
	
	public static final Block BlaziumOre;
	public static final Block BlaziumBlock;
	
	public static Item BlazeShard;
	public static Item BlaziumIngot;
	
	public static Item BlazePowder = Item.blazePowder; 

	public static Item BlaziumSword;
	public static Item BlaziumPickaxe;
	public static Item BlaziumAxe;
	public static Item BlaziumHoe;
	public static Item BlaziumShovel;
	
	public static Item BlaziumHelmet;
	public static Item BlaziumChest;
	public static Item BlaziumPants;
	public static Item BlaziumBoots;
	
	public static final Block CopperOre;
	public static final Block CopperBlock;
	public static Item CopperIngot;
	
	public static final Block CopperBlockTarnished;
	
	public static Item CopperSword;
	public static final Item CopperPickaxe;
	public static Item CopperAxe;
	public static Item CopperHoe;
	public static Item CopperShovel;
	
	public static final Block TinOre;
	public static Item TinIngot;
	public static final Block TinBlock;
	
	public static Item TinHelmet;
	public static Item TinChest;
	public static Item TinPants;
	public static Item TinBoots;
	
	public static Block TinPlate;
	public static Block BronzePlate;
	public static Block SteelPlate;
	public static Item TinPlateItem;
	public static Item BronzePlateItem;
	public static Item SteelPlateItem;
	
	public static Item CoalIronIngot;
	public static Item SteelIngot;
	
	public static Item SteelPickaxe;
	public static Item SteelAxe;
	public static Item SteelShovel;
	public static Item SteelHoe;
	public static Item SteelSword;
	public static Item SteelHelmet;
	public static Item SteelChest;
	public static Item SteelPants;
	public static Item SteelBoots;
	public static Block SteelBlock;
	
	public static Block SoulOre;
	public static Item SoulGem;
	
	public static Block SmoothQuartzite;
	public static Block PillarQuartzite;
	public static Block ChiseledQuartzite;
	public static Block SmoothQuartzTile;
	public static Item PinkQuartz;
	
	public static Item SandstonePickaxe;
	public static Item SandstoneShovel;
	public static Item SandstoneAxe;
	public static Item SandstoneHoe;
	public static Item SandstoneSword;
	
	public static Block TinPlatedCobble;
	public static Block TinPlatedMossy;
	public static Block TinPlatedStoneBrick;
	public static Block TinPlatedChiseled;
	public static Block TinPlatedGranite;
	
	public static Item BronzeIngot;
	public static Item BronzePickaxe;
	public static Item BronzeShovel;
	public static Item BronzeAxe;
	public static Item BronzeHoe;
	public static Item BronzeSword;
	public static Item BronzeHelmet;
	public static Item BronzeChest;
	public static Item BronzePants;
	public static Item BronzeBoots;
	public static Block BronzeBlock;
	
	public static Block BronzePlatedCobble;
	public static Block BronzePlatedMossy;
	public static Block BronzePlatedStoneBrick;
	public static Block BronzePlatedChiseled;
	public static Block BronzePlatedGranite;
	
	public static Block Invincium;
	public static Block ExtraOresBedrock;
	
	public static Item BedrockPickaxe;
	public static Item BedrockAxe;
	public static Item BedrockShovel;
	public static Item BedrockHoe;
	
	public static Block SteelPlatedCobble;
	public static Block SteelPlatedMossy;
	public static Block SteelPlatedStoneBrick;
	public static Block SteelPlatedChiseled;
	public static Block SteelPlatedGranite;
	
	public static Item GranitePickaxe;
	public static Item GraniteShovel;
	public static Item GraniteAxe;
	public static Item GraniteHoe;
	public static Item GraniteSword;
	
	public static Item SoulBottle;
	
	public static final Block nuke;
	public static Item grenade;
	public static Item grenadeImpact;
	public static Item grenadeSticky;
	
	public static Item BPZirconiumSword;
	public static Item BPZirconiumPickaxe;
	public static Item BPZirconiumAxe;
	public static Item BPZirconiumHoe;
	public static Item BPZirconiumShovel;
	
	public static Item BPZirconiumHelmet;
	public static Item BPZirconiumChest;
	public static Item BPZirconiumPants;
	public static Item BPZirconiumBoots;
	
	public static Item BPTitaniumSword;
	public static Item BPTitaniumPickaxe;
	public static Item BPTitaniumAxe;
	public static Item BPTitaniumHoe;
	public static Item BPTitaniumShovel;
	
	public static Item BPTitaniumHelmet;
	public static Item BPTitaniumChest;
	public static Item BPTitaniumPants;
	public static Item BPTitaniumBoots;
	
	public static Item BPToriteSword;
	public static Item BPToritePickaxe;
	public static Item BPToriteAxe;
	public static Item BPToriteHoe;
	public static Item BPToriteShovel;
	
	public static Item BPToriteHelmet;
	public static Item BPToriteChest;
	public static Item BPToritePants;
	public static Item BPToriteBoots;
	
	public static Item BPBlaziumSword;
	public static Item BPBlaziumPickaxe;
	public static Item BPBlaziumAxe;
	public static Item BPBlaziumHoe;
	public static Item BPBlaziumShovel;
	
	public static Item BPBlaziumHelmet;
	public static Item BPBlaziumChest;
	public static Item BPBlaziumPants;
	public static Item BPBlaziumBoots;
	
	public static Item BPCopperSword;
	public static Item BPCopperPickaxe;
	public static Item BPCopperAxe;
	public static Item BPCopperHoe;
	public static Item BPCopperShovel;
	
	public static Item BPTinHelmet;
	public static Item BPTinChest;
	public static Item BPTinPants;
	public static Item BPTinBoots;
	
	public static Item BPSteelPickaxe;
	public static Item BPSteelAxe;
	public static Item BPSteelShovel;
	public static Item BPSteelHoe;
	public static Item BPSteelSword;
	
	public static Item BPSteelHelmet;
	public static Item BPSteelChest;
	public static Item BPSteelPants;
	public static Item BPSteelBoots;
	
	public static Item BPSandstonePickaxe;
	public static Item BPSandstoneShovel;
	public static Item BPSandstoneAxe;
	public static Item BPSandstoneHoe;
	public static Item BPSandstoneSword;
	
	public static Item BPBedrockPickaxe;
	public static Item BPBedrockAxe;
	public static Item BPBedrockShovel;
	public static Item BPBedrockHoe;
	
	public static Item BPGranitePickaxe;
	public static Item BPGraniteShovel;
	public static Item BPGraniteAxe;
	public static Item BPGraniteHoe;
	public static Item BPGraniteSword;
	
	public static Item BPWoodPickaxe;
	public static Item BPWoodAxe;
	public static Item BPWoodShovel;
	public static Item BPWoodHoe;
	public static Item BPWoodSword;
	
	public static Item BPClothHelmet;
	public static Item BPClothChest;
	public static Item BPClothPants;
	public static Item BPClothBoots;
	
	public static Item BPStonePickaxe;
	public static Item BPStoneAxe;
	public static Item BPStoneShovel;
	public static Item BPStoneHoe;
	public static Item BPStoneSword;
	
	public static Item BPChainHelmet;
	public static Item BPChainChest;
	public static Item BPChainPants;
	public static Item BPChainBoots;
	
	public static Item BPIronPickaxe;
	public static Item BPIronAxe;
	public static Item BPIronShovel;
	public static Item BPIronHoe;
	public static Item BPIronSword;
	
	public static Item BPIronHelmet;
	public static Item BPIronChest;
	public static Item BPIronPants;
	public static Item BPIronBoots;
	
	public static Item BPGoldPickaxe;
	public static Item BPGoldAxe;
	public static Item BPGoldShovel;
	public static Item BPGoldHoe;
	public static Item BPGoldSword;
	
	public static Item BPGoldHelmet;
	public static Item BPGoldChest;
	public static Item BPGoldPants;
	public static Item BPGoldBoots;
	
	public static Item BPDiamondPickaxe;
	public static Item BPDiamondAxe;
	public static Item BPDiamondShovel;
	public static Item BPDiamondHoe;
	public static Item BPDiamondSword;
	
	public static Item BPDiamondHelmet;
	public static Item BPDiamondChest;
	public static Item BPDiamondPants;
	public static Item BPDiamondBoots;
	
	public static final Block SmoothRadiantQuartz;
	public static final Block ChiseledRadiantQuartz;
	public static final Block PillarRadiantQuartz;
	public static final Block RadiantQuartzOre;
	
	public static final Item RadiantQuartz;
	
	public static final Block Godstone;
	
	public static Block melterIdle;
	public static Block melterBurning;
	
	//less broken public static Block PinkQuartzSingleSlab;
	//less broken public static Block PinkQuartzDoubleSlab;
	
	public static int plateRenderId = RenderingRegistry.getNextAvailableRenderId();
	
	//broken public static BlockHalfSlab QuartziteSingleSlab;
	//broken public static BlockHalfSlab QuartziteDoubleSlab;
	
	public static CreativeTabs tabOresBlocks = new CreativeTabExtraoresBlocks(CreativeTabs.getNextID(),"Extra Ores");
	public static CreativeTabs tabOresItems = new CreativeTabExtraoresItems(CreativeTabs.getNextID(), "Extra Ores Items");
	
	static EnumArmorMaterial MaterialZirconium = EnumHelper.addArmorMaterial("Zirconium", 18, new int[]{2, 7, 6, 2}, 15);
	static EnumArmorMaterial MaterialTitanium = EnumHelper.addArmorMaterial("Titanium", 66, new int[]{4, 10, 8, 5}, 11);
	static EnumArmorMaterial MaterialTorite = EnumHelper.addArmorMaterial("Torite", 17, new int[]{2, 8, 6, 2}, 20);
	static EnumArmorMaterial MaterialBlazium = EnumHelper.addArmorMaterial("Blazium", 25, new int[]{3, 7, 6, 3}, 18);
	static EnumArmorMaterial MaterialTin = EnumHelper.addArmorMaterial("Tin", 5, new int[]{2, 4, 3, 2}, 5);
	static EnumArmorMaterial MaterialBronze = EnumHelper.addArmorMaterial("Bronze", 30, new int[]{3, 7, 5, 2}, 8);
	static EnumArmorMaterial MaterialSteel  = EnumHelper.addArmorMaterial("Steel", 17, new int[]{3, 8, 6, 3}, 15);
	static EnumArmorMaterial MaterialBPZirconium = EnumHelper.addArmorMaterial("Zirconium", 36, new int[]{2, 7, 6, 2}, 15);
	static EnumArmorMaterial MaterialBPTitanium = EnumHelper.addArmorMaterial("Titanium", 132, new int[]{4, 10, 8, 5}, 11);
	static EnumArmorMaterial MaterialBPTorite = EnumHelper.addArmorMaterial("Torite", 34, new int[]{2, 8, 6, 2}, 20);
	static EnumArmorMaterial MaterialBPBlazium = EnumHelper.addArmorMaterial("Blazium", 50, new int[]{3, 7, 6, 3}, 18);
	static EnumArmorMaterial MaterialBPTin = EnumHelper.addArmorMaterial("Tin", 10, new int[]{2, 4, 3, 2}, 5);
	static EnumArmorMaterial MaterialBPSteel  = EnumHelper.addArmorMaterial("Steel", 34, new int[]{3, 8, 6, 3}, 15);
	static EnumArmorMaterial MaterialBPCloth = EnumHelper.addArmorMaterial("BOCloth", 10, new int[]{1, 3, 2, 1}, 15);
	static EnumArmorMaterial MaterialBPChain = EnumHelper.addArmorMaterial("BPChain", 30, new int[]{2, 5, 4, 1}, 12);
	static EnumArmorMaterial MaterialBPIron = EnumHelper.addArmorMaterial("BPIron", 30, new int[]{2, 6, 5, 2}, 9);
	static EnumArmorMaterial MaterialBPGold = EnumHelper.addArmorMaterial("BPGold", 14, new int[]{2, 5, 3, 1}, 25);
	static EnumArmorMaterial MaterialBPDiamond = EnumHelper.addArmorMaterial("BPDiamond", 66, new int[]{3, 8, 6, 3}, 10);
	
	@Instance("ExtraoresID")
	public static ExtraOres instance;
	
	private EOGuiHandler guiHandler = new EOGuiHandler();
	
	static
	{
		ZirconiumOre = (new EOBlock(244, "block_ZirconiumOre", Material.rock)).setHardness(5F).setCreativeTab(tabOresBlocks).setResistance(10F).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("ZirconiumOre");
		ZirconiumBlock = (new EOBlock(246, "block_Zirconium", Material.iron)).setHardness(5F).setCreativeTab(tabOresBlocks).setResistance(10F).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("ZirconiumBlock");
		ZirconiumIngot = (new EItem(280, "item_ZirconiumIngot")).setCreativeTab(tabOresItems).setUnlocalizedName("ZirconiumIngot");
		
		ZirconiumSword = (new ItemESword(281, "item_ZirconiumSword", ExtracraftToolMaterial.ZIRCONIUM)).setCreativeTab(tabOresItems).setUnlocalizedName("ZirconiumSword");
		ZirconiumPickaxe = (new ItemEPickaxe(282, "item_ZirconiumPickaxe", ExtracraftToolMaterial.ZIRCONIUM)).setCreativeTab(tabOresItems).setUnlocalizedName("ZirconiumPickaxe");
		ZirconiumShovel = (new ItemEShovel(283, "item_ZirconiumShovel", ExtracraftToolMaterial.ZIRCONIUM)).setCreativeTab(tabOresItems).setUnlocalizedName("ZirconiumShovel");
		ZirconiumHoe = (new ItemEHoe(284, "item_ZirconumHoe", ExtracraftToolMaterial.ZIRCONIUM)).setCreativeTab(tabOresItems).setUnlocalizedName("ZirconiumHoe");
		ZirconiumAxe = (new ItemEAxe(285, "item_ZirconiumAxe", ExtracraftToolMaterial.ZIRCONIUM)).setCreativeTab(tabOresItems).setUnlocalizedName("ZirconiumAxe");
	
		ZirconiumHelmet = (new ItemExtracraftHelmet(286, "item_ZirconiumHelmet", MaterialZirconium, proxy.addArmor("zirconium"), 0)).setCreativeTab(tabOresItems).setUnlocalizedName("ZirconiumHelmet");
		ZirconiumChest = (new ItemExtracraftChest(287, "item_ZirconiumChestplate", MaterialZirconium, proxy.addArmor("zirconium"), 1)).setCreativeTab(tabOresItems).setUnlocalizedName("ZurconiumChest");
		ZirconiumPants = (new ItemExtracraftPants(288, "item_ZirconiumLeggings",MaterialZirconium, proxy.addArmor("zirconium"),2)).setCreativeTab(tabOresItems).setUnlocalizedName("ZirconiumPants");
		ZirconiumBoots = (new ItemExtracraftBoots(289, "item_ZirconiumBoots", MaterialZirconium, proxy.addArmor("zirconium"),3)).setCreativeTab(tabOresItems).setUnlocalizedName("ZirconiumBoots");
				
		UraniumOre = (new BlockUraniumOre(243, Material.rock)).setHardness(5F).setResistance(10F).setCreativeTab(tabOresBlocks).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("UraniumOre");
		Uranium = (new EItem(290, "item_Uranium")).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("Uranium");
		RawUraniumBlock = (new BlockUraniumRaw(241, Material.rock)).setHardness(6F).setResistance(9F).setCreativeTab(tabOresBlocks).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("RawUraniumBlock");
		
		PlutoniumOre = (new BlockPlutoniumOre(245, Material.rock)).setHardness(5F).setResistance(10F).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("PlutoniumOre").setCreativeTab(tabOresBlocks);
		Plutonium = (new EItem(291, "item_Plutonium")).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("Plutonium");
		RawPlutoniumBlock = (new BlockPlutoniumRaw(242, Material.rock)).setHardness(6F).setResistance(9F).setCreativeTab(tabOresBlocks).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("RawPlutoniumBlock");
		
		TitaniumOre = (new EOBlock(247, "block_TitaniumOre", Material.rock)).setHardness(10F).setResistance(15F).setCreativeTab(ExtraOres.tabOresBlocks).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("TitaniumOre");
		TitaniumIngot = (new EItem(292, "item_TitaniumIngot")).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("Titanium");
		
		TitaniumSword = (new ItemESword(293,"item_TitaniumSword", ExtracraftToolMaterial.TITANIUM)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("TitaniumSword");
		TitaniumPickaxe = (new ItemEPickaxe(294, "item_TitaniumPickaxe", ExtracraftToolMaterial.TITANIUM)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("TitaniumPickaxe");
		TitaniumShovel = (new ItemEShovel(295, "item_TitaniumShovel", ExtracraftToolMaterial.TITANIUM)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("TitaniumShovel");
		TitaniumHoe = (new ItemEHoe(296, "item_TitaniumHoe",ExtracraftToolMaterial.TITANIUM)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("TitaniumHoe");
		TitaniumAxe = (new ItemEAxe(297, "item_TitaniumAxe",ExtracraftToolMaterial.TITANIUM)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("TitaniumAxe");
	
		TitaniumHelmet = (new ItemExtracraftHelmet(298, "item_TitaniumHelmet", MaterialTitanium, proxy.addArmor("titanium"), 0)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("TitaniumHelmet");
		TitaniumChest = (new ItemExtracraftChest(299, "item_TitaniumChestplate",MaterialTitanium, proxy.addArmor("titanium"), 1)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("TitaniumChest");
		TitaniumPants = (new ItemExtracraftPants(301, "item_TitaniumLeggings", MaterialTitanium, proxy.addArmor("titanium"),2)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("TitaniumPants");
		TitaniumBoots = (new ItemExtracraftBoots(302, "item_TitaniumBoots",MaterialTitanium, proxy.addArmor("titanium"),3)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("TitaniumBoots");
		
		TitaniumBlock = (new EOBlock(252, "block_Titanium", Material.iron)).setHardness(10F).setResistance(12000000.0F).setCreativeTab(ExtraOres.tabOresBlocks).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("Titanium Block");
		
		Sunstone = (new BlockSunstone(253,Material.glass)).setHardness(0.3F).setStepSound(Block.soundGlassFootstep).setCreativeTab(ExtraOres.tabOresBlocks).setLightValue(1.0F).setUnlocalizedName("Sunstone");
		SunstoneDust = (new EItem(303, "item_SunstoneShard")).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("SunstoneDust");
		SunstoneOre = (new EOBlock(212, "block_SunstoneOre", Material.rock)).setHardness(7F).setResistance(11F).setStepSound(Block.soundMetalFootstep).setCreativeTab(ExtraOres.tabOresBlocks).setUnlocalizedName("SunstoneOre").setLightValue(1F);
		//Old Sunstone Ore code: SunstoneOre = (new BlockSunstoneOre(212, 40)).setHardness(7F).setStepSound(Block.soundStoneFootstep).setCreativeTab(ExtraOres.tabExtra).setLightValue(1.0F).setBlockName("Sunstone Ore");
		
		ToriteOre = (new EOBlock(250, "block_ToriteOre", Material.rock)).setHardness(7F).setResistance(11F).setStepSound(Block.soundMetalFootstep).setCreativeTab(ExtraOres.tabOresBlocks).setUnlocalizedName("ToriteOre");
		ToriteIngot = (new EItem(304, "item_ToriteIngot")).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("ToriteIngot");
		ToriteBlock = (new EOBlock(251, "block_Torite", Material.iron)).setHardness(6F).setResistance(10F).setCreativeTab(ExtraOres.tabOresBlocks).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("ToriteBlock");
	
		ToriteSword = (new ItemESword(305, "item_ToriteSword", ExtracraftToolMaterial.TORITE)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("ToriteSword");
		ToritePickaxe = (new ItemEPickaxe(306, "item_ToritePickaxe", ExtracraftToolMaterial.TORITE)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("ToritePickaxe");
		ToriteShovel = (new ItemEShovel(307, "item_ToriteShovel", ExtracraftToolMaterial.TORITE)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("ToriteShovel");
		ToriteHoe = (new ItemEHoe(308, "item_ToriteHoe",ExtracraftToolMaterial.TORITE)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("ToriteHoe");
		ToriteAxe = (new ItemEAxe(309, "item_ToriteAxe",ExtracraftToolMaterial.TORITE)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("ToriteAxe");
		
		ToriteHelmet = (new ItemExtracraftHelmet(310, "item_ToriteHelmet",MaterialTorite, proxy.addArmor("torite"), 0)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("ToriteHelmet");
		ToriteChest = (new ItemExtracraftChest(311, "item_ToriteChestplate",MaterialTorite, proxy.addArmor("torite"), 1)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("ToriteChest");
		ToritePants = (new ItemExtracraftPants(312, "item_ToriteLeggings",MaterialTorite, proxy.addArmor("torite"),2)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("ToritePants");
		ToriteBoots = (new ItemExtracraftBoots(313, "item_ToriteBoots",MaterialTorite, proxy.addArmor("torite"),3)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("ToriteBoots");
		
		Granite = (new EOBlock(200, "block_Granite", Material.rock)).setHardness(5F).setResistance(9F).setStepSound(Block.soundMetalFootstep).setCreativeTab(ExtraOres.tabOresBlocks).setUnlocalizedName("Granite");
		GraniteBrick = (new EOBlock(201, "block_GraniteBrick", Material.rock)).setHardness(6F).setResistance(10F).setStepSound(Block.soundMetalFootstep).setCreativeTab(ExtraOres.tabOresBlocks).setUnlocalizedName("GraniteBrick");
		
		Quartzite = (new EOBlock(202, "block_PinkQuartzRaw", Material.rock)).setHardness(5F).setResistance(9F).setStepSound(Block.soundMetalFootstep).setCreativeTab(ExtraOres.tabOresBlocks).setUnlocalizedName("Quartzite");
		QuartziteTile = (new EOBlock(203, "block_PinkQuartzTileRough", Material.rock)).setHardness(6F).setResistance(10F).setStepSound(Block.soundMetalFootstep).setCreativeTab(ExtraOres.tabOresBlocks).setUnlocalizedName("QuartziteTile");
	    SmoothQuartzite = (new EOBlock(217, "block_PinkQuartzSmooth", Material.rock)).setHardness(6F).setResistance(10F).setStepSound(Block.soundMetalFootstep).setCreativeTab(ExtraOres.tabOresBlocks).setUnlocalizedName("SmoothQuartzite");
		PillarQuartzite = (new BlockPinkPillar(218)).setHardness(6F).setResistance(10F).setStepSound(Block.soundMetalFootstep).setCreativeTab(ExtraOres.tabOresBlocks).setUnlocalizedName("QuartzitePillar");
		ChiseledQuartzite = (new BlockPinkChiseled(219)).setHardness(6F).setResistance(10F).setStepSound(Block.soundMetalFootstep).setCreativeTab(ExtraOres.tabOresBlocks).setUnlocalizedName("ChiseledQuartzite");
		SmoothQuartzTile = (new EOBlock(220, "block_PinkQuartzTileRefined", Material.rock)).setHardness(6F).setResistance(10F).setStepSound(Block.soundMetalFootstep).setCreativeTab(ExtraOres.tabOresBlocks).setUnlocalizedName("SmoothQuartzTile");
		PinkQuartz = (new EItem(340, "item_PinkQuartz")).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("PinkQuartz");
	    
		BlaziumOre = (new EOBlock(204, "block_BlaziumOre", Material.rock)).setHardness(7F).setResistance(11F).setStepSound(Block.soundMetalFootstep).setCreativeTab(ExtraOres.tabOresBlocks).setUnlocalizedName("BlaziumOre").setLightValue(0.5F);
		//Experimental Blazium Ore Code: BlaziumOre = (new BlockBlaziumOre(204, 5)).setHardness(7F).setResistance(11F).setStepSound(Block.soundMetalFootstep).setCreativeTab(ExtraOres.tabExtra).setLightValue(0.5F).setBlockName("Blazium Ore");
		BlaziumBlock = (new BlockBlazium(205, Material.iron)).setHardness(8F).setResistance(12F).setStepSound(Block.soundMetalFootstep).setCreativeTab(ExtraOres.tabOresBlocks).setUnlocalizedName("BlaziumBlock").setLightValue(0.7F);
		
		BlaziumIngot = (new EItem(315, "item_BlaziumIngot")).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BlaziumIngot");
		BlazeShard = (new EItem(316, "item_BlazeShard")).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BlazeShard");
	
		BlaziumSword = (new ItemESword(317, "item_FireSword", ExtracraftToolMaterial.BLAZIUM)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BlaziumSword");
		BlaziumPickaxe = (new ItemEPickaxe(318, "item_FirePickaxe", ExtracraftToolMaterial.BLAZIUM)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BlaziumPickaxe");
		BlaziumShovel = (new ItemEShovel(319, "item_FireShovel",ExtracraftToolMaterial.BLAZIUM)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BlaziumShovel");
		BlaziumHoe = (new ItemEHoe(320, "item_FireHoe",ExtracraftToolMaterial.BLAZIUM)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BlaziumHoe");
		BlaziumAxe = (new ItemEAxe(321, "item_FireAxe",ExtracraftToolMaterial.BLAZIUM)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BlaziumAxe");
		
		BlaziumHelmet = (new ItemExtracraftHelmet(322,"item_FireHelmet", MaterialBlazium, proxy.addArmor("fire"), 0)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BlaziumHelmet");
		BlaziumChest = (new ItemExtracraftChest(323, "item_FireChestplate",MaterialBlazium, proxy.addArmor("fire"), 1)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BlaziumChest");
		BlaziumPants = (new ItemExtracraftPants(324, "item_FireLeggings",MaterialBlazium, proxy.addArmor("fire"),2)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BlaziumPants");
		BlaziumBoots = (new ItemExtracraftBoots(325, "item_FireBoots",MaterialBlazium, proxy.addArmor("fire"),3)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BlaziumBoots");
	
		CopperOre = (new EOBlock(206, "block_CopperOre", Material.rock)).setHardness(3F).setResistance(5F).setCreativeTab(ExtraOres.tabOresBlocks).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("CopperOre");
		CopperBlock = (new BlockCopper(207, Material.iron)).setHardness(5F).setResistance(10F).setCreativeTab(ExtraOres.tabOresBlocks).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("CopperBlock");
		CopperIngot = (new EItem(326, "item_CopperIngot")).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("CopperIngot");
		
		CopperBlockTarnished = (new BlockCopperTarnished(254)).setHardness(6F).setResistance(12F).setCreativeTab(ExtraOres.tabOresBlocks).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("CopperBlockTarnished");
		
		CopperSword = (new ItemESword(327, "item_CopperSword",ExtracraftToolMaterial.COPPER)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("CopperSword");
		CopperPickaxe = (new ItemEPickaxe(328, "item_CopperPickaxe",ExtracraftToolMaterial.COPPER)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("CopperPickaxe");
		CopperShovel = (new ItemEShovel(329, "item_CopperShovel",ExtracraftToolMaterial.COPPER)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("CopperShovel");
		CopperHoe = (new ItemEHoe(330, "item_CopperHoe",ExtracraftToolMaterial.COPPER)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("CopperHoe");
		CopperAxe = (new ItemEAxe(331, "item_CopperAxe",ExtracraftToolMaterial.COPPER)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("CopperAxe");
		
		TinOre = (new EOBlock(208, "block_TinOre", Material.rock)).setHardness(3F).setResistance(5F).setCreativeTab(ExtraOres.tabOresBlocks).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("TinOre");
		TinBlock = (new EOBlock(209, "block_Tin", Material.iron)).setHardness(3F).setResistance(2F).setCreativeTab(ExtraOres.tabOresBlocks).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("TinBlock");
		TinIngot = (new EItem(332, "item_TinIngot")).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("TinIngot");

		TinHelmet = (new ItemExtracraftHelmet(333,"item_TinHelmet", MaterialTin, proxy.addArmor("tin"), 0)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("TinHelmet");
		TinChest = (new ItemExtracraftChest(334, "item_TinChestplate",MaterialTin, proxy.addArmor("tin"), 1)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("TinChest");
		TinPants = (new ItemExtracraftPants(335, "item_TinLeggings",MaterialTin, proxy.addArmor("tin"),2)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("TinPants");
		TinBoots = (new ItemExtracraftBoots(336, "item_TinBoots",MaterialTin, proxy.addArmor("tin"),3)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("TinBoots");
	
		TinPlate = (new Plate(210,"block_Tin")).setHardness(0.7F).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("TinPlate");
		BronzePlate = (new Plate(211, "block_Bronze")).setHardness(0.7F).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("BronzePlate");
		SteelPlate = (new Plate(227, "block_SteelSide")).setHardness(0.7F).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("SteelPlate");
		TinPlateItem = (new ItemBlockPlacer(369,"item_TinPlate", TinPlate)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("TinPlateItem");
		BronzePlateItem = (new ItemBlockPlacer(370, "item_BronzePlate", BronzePlate)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BronzePlateItem");
		SteelPlateItem = (new ItemBlockPlacer(371,"item_SteelPlate", SteelPlate)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("SteelPlateItem");
		
		CoalIronIngot = (new EItem(337,"item_CoalIronIngot")).setCreativeTab(tabOresItems).setUnlocalizedName("CoalIronIngot");
		SteelIngot = (new EItem(338, "item_SteelIngot")).setCreativeTab(tabOresItems).setUnlocalizedName("SteelIngot");
		
		SteelPickaxe = (new ItemEPickaxe(356, "item_SteelPickaxe",ExtracraftToolMaterial.STEEL)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("SteelPickaxe");
		SteelShovel = (new ItemEShovel(357, "item_SteelShovel",ExtracraftToolMaterial.STEEL)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("SteelShovel");
		SteelAxe = (new ItemEAxe(358, "item_SteelAxe",ExtracraftToolMaterial.STEEL)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("SteelAxe");
		SteelHoe = (new ItemEHoe(359, "item_SteelHoe",ExtracraftToolMaterial.STEEL)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("SteelHoe");
		SteelSword = (new ItemESword(360, "item_SteelSword",ExtracraftToolMaterial.STEEL)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("SteelSword");
		
		SteelHelmet = (new ItemExtracraftHelmet(361,"item_SteelHelmet", MaterialSteel, proxy.addArmor("steel"), 0)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("SteelHelmet");
		SteelChest = (new ItemExtracraftChest(362, "item_SteelChestplate",MaterialSteel, proxy.addArmor("steel"), 1)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("SteelChest");
		SteelPants = (new ItemExtracraftPants(363, "item_SteelLeggings",MaterialSteel, proxy.addArmor("steel"), 2)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("SteelPants");
		SteelBoots = (new ItemExtracraftBoots(364, "item_SteelBoots",MaterialSteel, proxy.addArmor("steel"), 3)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("SteelBoots");
		
		SteelBlock = (new BlockEOSteel(235)).setHardness(6F).setResistance(10F).setStepSound(Block.soundMetalFootstep).setCreativeTab(ExtraOres.tabOresBlocks).setUnlocalizedName("SteelBlock");
		
		SoulOre = (new EOBlock(216, "block_SoulOre", Material.sand)).setHardness(2F).setResistance(3F).setCreativeTab(ExtraOres.tabOresBlocks).setStepSound(Block.soundSandFootstep).setUnlocalizedName("SoulOre");
		SoulGem = (new EItemFoiled(339, "item_SoulGem")).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("SoulGem");
		
		SandstonePickaxe = (new ItemEPickaxe(341,"item_SandstonePickaxe", ExtracraftToolMaterial.SANDSTONE)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("SandstonePickaxe");
		SandstoneShovel = (new ItemEShovel(342, "item_SandstoneShovel",ExtracraftToolMaterial.SANDSTONE)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("SandstoneShovel");
		SandstoneAxe = (new ItemEAxe(343, "item_SandstoneAxe", ExtracraftToolMaterial.SANDSTONE)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("SandstoneAxe");
		SandstoneHoe = (new ItemEHoe(344, "item_SandstoneHoe",ExtracraftToolMaterial.SANDSTONE)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("SandstoneHoe");
		SandstoneSword = (new ItemESword(345, "item_SandstoneSword",ExtracraftToolMaterial.SANDSTONE)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("SandstoneSword");
		
		TinPlatedCobble = (new EOBlock(221, "block_TinCobble", Material.rock)).setHardness(2.0F).setResistance(10.0F).setCreativeTab(ExtraOres.tabOresBlocks).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("TinPlatedCobble");
		TinPlatedMossy = (new EOBlock(222, "block_TinMossy", Material.rock)).setHardness(2.0F).setResistance(10.0F).setCreativeTab(ExtraOres.tabOresBlocks).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("TinPlatedMossy");
		TinPlatedStoneBrick = (new EOBlock(223, "block_TinStoneBrick", Material.rock)).setHardness(1.5F).setResistance(10.0F).setCreativeTab(ExtraOres.tabOresBlocks).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("TinPlatedStoneBrick");
		TinPlatedChiseled = (new EOBlock(224, "block_TinChiseled", Material.rock)).setHardness(1.5F).setResistance(10.0F).setCreativeTab(ExtraOres.tabOresBlocks).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("TinPlatedChiseled");
		TinPlatedGranite = (new EOBlock(225,"block_TinGraniteBrick", Material.rock)).setHardness(6F).setResistance(10.0F).setCreativeTab(ExtraOres.tabOresBlocks).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("TinPlatedGranite");
		
		BronzeIngot = (new EItem(346, "item_BronzeIngot")).setCreativeTab(tabOresItems).setUnlocalizedName("BronzeIngot");
		BronzePickaxe = (new ItemEPickaxe(347, "item_BronzePickaxe",ExtracraftToolMaterial.BRONZE)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BronzePickaxe");
		BronzeShovel = (new ItemEShovel(348, "item_BronzeShovel",ExtracraftToolMaterial.BRONZE)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BronzeShovel");
		BronzeAxe = (new ItemEAxe(349, "item_BronzeAxe",ExtracraftToolMaterial.BRONZE)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BronzeAxe");
		BronzeHoe = (new ItemEHoe(350,"item_BronzeHoe", ExtracraftToolMaterial.BRONZE)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BronzeHoe");
		BronzeSword = (new ItemESword(351,"item_BronzeSword", ExtracraftToolMaterial.BRONZE)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BronzeSword");
		BronzeHelmet = (new ItemExtracraftHelmet(352,"item_BronzeHelmet", MaterialBronze, proxy.addArmor("bronze"), 0)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BronzeHelmet");
		BronzeChest = (new ItemExtracraftChest(353, "item_BronzeChestplate",MaterialBronze, proxy.addArmor("bronze"), 1)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BronzeChest");
		BronzePants = (new ItemExtracraftPants(354, "item_BronzeLeggings",MaterialBronze, proxy.addArmor("bronze"),2)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BronzePants");
		BronzeBoots = (new ItemExtracraftBoots(355, "item_BronzeBoots",MaterialBronze, proxy.addArmor("bronze"),3)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BronzeBoots");
		BronzeBlock = (new EOBlock(226, "block_Bronze", Material.iron)).setHardness(7F).setResistance(20F).setCreativeTab(ExtraOres.tabOresBlocks).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("BronzeBlock");
		
		BronzePlatedCobble = (new EOBlock(228, "block_BronzeCobble", Material.rock)).setHardness(2.0F).setResistance(30.0F).setCreativeTab(ExtraOres.tabOresBlocks).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("BronzePlatedCobble");
		BronzePlatedMossy = (new EOBlock(229, "block_BronzeMossy", Material.rock)).setHardness(2.0F).setResistance(30.0F).setCreativeTab(ExtraOres.tabOresBlocks).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("BronzePlatedMossy");
		BronzePlatedStoneBrick = (new EOBlock(230, "block_BronzeStoneBrick", Material.rock)).setHardness(1.5F).setResistance(30.0F).setCreativeTab(ExtraOres.tabOresBlocks).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("BronzePlatedStoneBrick");
		BronzePlatedChiseled = (new EOBlock(231, "block_BronzeChiseled", Material.rock)).setHardness(1.5F).setResistance(30.0F).setCreativeTab(ExtraOres.tabOresBlocks).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("BronzePlatedChiseled");
		BronzePlatedGranite = (new EOBlock(232, "block_BronzeGraniteBrick", Material.rock)).setHardness(6F).setResistance(30.0F).setCreativeTab(ExtraOres.tabOresBlocks).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("BronzePlatedGranite");
		
		Invincium = (new EOBlock(233, "block_Invincium", Material.rock)).setBlockUnbreakable().setResistance(12000000.0F).setCreativeTab(ExtraOres.tabOresBlocks).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("Invincium");
		ExtraOresBedrock = (new EOBlock(234, "block_NewBedrock", Material.rock)).setHardness(80F).setResistance(24000000.0F).setCreativeTab(ExtraOres.tabOresBlocks).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("ExtraOresBedrock");
		//Replacement block for vanilla Bedrock to make it breakable. Currently modifies net.minecraft.world.gen.ChunkProviderGenerate, net.minecraft.world.gen.ChunkProviderHell, and net.minecraft.world.gen.feature.WorldGenSpikes.
		//TODO: Find a solution to removing Bedrock invincibility that doesn't edit base classes.
		
		SteelPlatedCobble = (new EOBlock(236, "block_SteelCobble", Material.rock)).setHardness(2.0F).setResistance(20.0F).setCreativeTab(ExtraOres.tabOresBlocks).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("SteelPlatedCobble");
		SteelPlatedMossy = (new EOBlock(237, "block_SteelMossy", Material.rock)).setHardness(2.0F).setResistance(20.0F).setCreativeTab(ExtraOres.tabOresBlocks).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("SteelPlatedMossy");
		SteelPlatedStoneBrick = (new EOBlock(238, "block_SteelStoneBrick", Material.rock)).setHardness(1.5F).setResistance(20.0F).setCreativeTab(ExtraOres.tabOresBlocks).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("SteelPlatedStoneBrick");
		SteelPlatedChiseled = (new EOBlock(239, "block_SteelChiseled", Material.rock)).setHardness(1.5F).setResistance(20.0F).setCreativeTab(ExtraOres.tabOresBlocks).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("SteelPlatedChiseled");
		SteelPlatedGranite = (new EOBlock(240, "block_SteelGraniteBrick", Material.rock)).setHardness(6F).setResistance(20.0F).setCreativeTab(ExtraOres.tabOresBlocks).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("SteelPlatedGranite");
		
		BedrockPickaxe = (new ItemEPickaxe(365, "item_BedrockPickaxe", ExtracraftToolMaterial.BEDROCK)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BedrockPickaxe");
		BedrockShovel = (new ItemEShovel(366, "item_BedrockShovel",ExtracraftToolMaterial.BEDROCK)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BedrockShovel");
		BedrockAxe = (new ItemEAxe(367, "item_BedrockAxe",ExtracraftToolMaterial.BEDROCK)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BedrockAxe");
		BedrockHoe = (new ItemEHoe(368, "item_BedrockHoe",ExtracraftToolMaterial.BEDROCK)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BedrockHoe");
		
		GranitePickaxe = (new ItemEPickaxe(372, "item_GranitePickaxe",ExtracraftToolMaterial.GRANITE)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("GranitePickaxe");
		GraniteShovel = (new ItemEShovel(373, "item_GraniteShovel",ExtracraftToolMaterial.GRANITE)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("GraniteShovel");
		GraniteAxe = (new ItemEAxe(374, "item_GraniteAxe",ExtracraftToolMaterial.GRANITE)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("GraniteAxe");
		GraniteHoe = (new ItemEHoe(375, "item_GraniteHoe",ExtracraftToolMaterial.GRANITE)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("GraniteHoe");
		GraniteSword = (new ItemESword(376, "item_GraniteSword",ExtracraftToolMaterial.GRANITE)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("GraniteSword");
		
		SoulBottle = (new ItemSoulBottle(377)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("SoulBottle");
		
		nuke = (new BlockNuke(255)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("nuke");
		grenade = (new ItemGrenade(378)).setUnlocalizedName("grenade");
		grenadeImpact = (new ItemGrenadeImpact(492)).setUnlocalizedName("grenadeImpact");
		grenadeSticky = (new ItemGrenadeSticky(493)).setUnlocalizedName("grenadeSticky");	
	
		
		
		BPZirconiumSword = (new ItemESword(379, "item_BronzePlatedZirconiumSword", ExtracraftToolMaterial.BPZIRCONIUM)).setCreativeTab(tabOresItems).setUnlocalizedName("BPZirconiumSword");
		BPZirconiumPickaxe = (new ItemEPickaxe(380, "item_BronzePlatedZirconiumPickaxe", ExtracraftToolMaterial.BPZIRCONIUM)).setCreativeTab(tabOresItems).setUnlocalizedName("BPZirconiumPickaxe");
		BPZirconiumShovel = (new ItemEShovel(381, "item_BronzePlatedZirconiumShovel", ExtracraftToolMaterial.BPZIRCONIUM)).setCreativeTab(tabOresItems).setUnlocalizedName("BPZirconiumShovel");
		BPZirconiumHoe = (new ItemEHoe(382, "item_BronzePlatedZirconiumHoe", ExtracraftToolMaterial.BPZIRCONIUM)).setCreativeTab(tabOresItems).setUnlocalizedName("BPZirconiumHoe");
		BPZirconiumAxe = (new ItemEAxe(383, "item_BronzePlatedZirconiumAxe", ExtracraftToolMaterial.BPZIRCONIUM)).setCreativeTab(tabOresItems).setUnlocalizedName("BPZirconiumAxe");
	
		BPZirconiumHelmet = (new ItemExtracraftHelmet(384, "item_BronzePlatedZirconiumHelmet", MaterialBPZirconium, proxy.addArmor("BPzirconium"), 0)).setCreativeTab(tabOresItems).setUnlocalizedName("BPZirconiumHelmet");
		BPZirconiumChest = (new ItemExtracraftChest(385, "item_BronzePlatedZirconiumChestplate", MaterialBPZirconium, proxy.addArmor("BPzirconium"), 1)).setCreativeTab(tabOresItems).setUnlocalizedName("BPZirconiumChest");
		BPZirconiumPants = (new ItemExtracraftPants(386, "item_BronzePlatedZirconiumLeggings",MaterialBPZirconium, proxy.addArmor("BPzirconium"),2)).setCreativeTab(tabOresItems).setUnlocalizedName("BPZirconiumPants");
		BPZirconiumBoots = (new ItemExtracraftBoots(387, "item_BronzePlatedZirconiumBoots", MaterialBPZirconium, proxy.addArmor("BPzirconium"),3)).setCreativeTab(tabOresItems).setUnlocalizedName("BPZirconiumBoots");
		
		BPTitaniumSword = (new ItemESword(388,"item_BronzePlatedTitaniumSword", ExtracraftToolMaterial.BPTITANIUM)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPTitaniumSword");
		BPTitaniumPickaxe = (new ItemEPickaxe(389, "item_BronzePlatedTitaniumPickaxe", ExtracraftToolMaterial.BPTITANIUM)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPTitaniumPickaxe");
		BPTitaniumShovel = (new ItemEShovel(390, "item_BronzePlatedTitaniumShovel", ExtracraftToolMaterial.BPTITANIUM)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPTitaniumShovel");
		BPTitaniumHoe = (new ItemEHoe(391, "item_BronzePlatedTitaniumHoe",ExtracraftToolMaterial.BPTITANIUM)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPTitaniumHoe");
		BPTitaniumAxe = (new ItemEAxe(392, "item_BronzePlatedTitaniumAxe",ExtracraftToolMaterial.BPTITANIUM)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPTitaniumAxe");
	
		BPTitaniumHelmet = (new ItemExtracraftHelmet(393, "item_BronzePlatedTitaniumHelmet", MaterialBPTitanium, proxy.addArmor("BPtitanium"), 0)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPTitaniumHelmet");
		BPTitaniumChest = (new ItemExtracraftChest(394, "item_BronzePlatedTitaniumChestplate",MaterialBPTitanium, proxy.addArmor("BPtitanium"), 1)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPTitaniumChest");
		BPTitaniumPants = (new ItemExtracraftPants(395, "item_BronzePlatedTitaniumLeggings", MaterialBPTitanium, proxy.addArmor("BPtitanium"),2)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPTitaniumPants");
		BPTitaniumBoots = (new ItemExtracraftBoots(396, "item_BronzePlatedTitaniumBoots",MaterialBPTitanium, proxy.addArmor("BPtitanium"),3)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPTitaniumBoots");
		
		BPToriteSword = (new ItemESword(397, "item_BronzePlatedToriteSword", ExtracraftToolMaterial.BPTORITE)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPToriteSword");
		BPToritePickaxe = (new ItemEPickaxe(398, "item_BronzePlatedToritePickaxe", ExtracraftToolMaterial.BPTORITE)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPToritePickaxe");
		BPToriteShovel = (new ItemEShovel(399, "item_BronzePlatedToriteShovel", ExtracraftToolMaterial.BPTORITE)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPToriteShovel");
		BPToriteHoe = (new ItemEHoe(400, "item_BronzePlatedToriteHoe",ExtracraftToolMaterial.BPTORITE)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPToriteHoe");
		BPToriteAxe = (new ItemEAxe(401, "item_BronzePlatedToriteAxe",ExtracraftToolMaterial.BPTORITE)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPToriteAxe");
		
		BPToriteHelmet = (new ItemExtracraftHelmet(402, "item_BronzePlatedToriteHelmet",MaterialBPTorite, proxy.addArmor("BPtorite"), 0)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPToriteHelmet");
		BPToriteChest = (new ItemExtracraftChest(403, "item_BronzePlatedToriteChestplate",MaterialBPTorite, proxy.addArmor("BPtorite"), 1)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPToriteChest");
		BPToritePants = (new ItemExtracraftPants(404, "item_BronzePlatedToriteLeggings",MaterialBPTorite, proxy.addArmor("BPtorite"),2)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPToritePants");
		BPToriteBoots = (new ItemExtracraftBoots(405, "item_BronzePlatedToriteBoots",MaterialBPTorite, proxy.addArmor("BPtorite"),3)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPToriteBoots");
		
		BPBlaziumSword = (new ItemESword(406, "item_BronzePlatedFireSword", ExtracraftToolMaterial.BPBLAZIUM)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPBlaziumSword");
		BPBlaziumPickaxe = (new ItemEPickaxe(407, "item_BronzePlatedFirePickaxe", ExtracraftToolMaterial.BPBLAZIUM)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPBlaziumPickaxe");
		BPBlaziumShovel = (new ItemEShovel(408, "item_BronzePlatedFireShovel",ExtracraftToolMaterial.BPBLAZIUM)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPBlaziumShovel");
		BPBlaziumHoe = (new ItemEHoe(409, "item_BronzePlatedFireHoe",ExtracraftToolMaterial.BPBLAZIUM)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPBlaziumHoe");
		BPBlaziumAxe = (new ItemEAxe(410, "item_BronzePlatedFireAxe",ExtracraftToolMaterial.BPBLAZIUM)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPBlaziumAxe");
		
		BPBlaziumHelmet = (new ItemExtracraftHelmet(411,"item_BronzePlatedFireHelmet", MaterialBPBlazium, proxy.addArmor("BPfire"), 0)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPBlaziumHelmet");
		BPBlaziumChest = (new ItemExtracraftChest(412, "item_BronzePlatedFireChestplate",MaterialBPBlazium, proxy.addArmor("BPfire"), 1)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPBlaziumChest");
		BPBlaziumPants = (new ItemExtracraftPants(413, "item_BronzePlatedFireLeggings",MaterialBPBlazium, proxy.addArmor("BPfire"),2)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPBlaziumPants");
		BPBlaziumBoots = (new ItemExtracraftBoots(414, "item_BronzePlatedFireBoots",MaterialBPBlazium, proxy.addArmor("BPfire"),3)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPBlaziumBoots");
		
		BPCopperSword = (new ItemESword(415, "item_BronzePlatedCopperSword",ExtracraftToolMaterial.BPCOPPER)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPCopperSword");
		BPCopperPickaxe = (new ItemEPickaxe(416, "item_BronzePlatedCopperPickaxe",ExtracraftToolMaterial.BPCOPPER)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPCopperPickaxe");
		BPCopperShovel = (new ItemEShovel(417, "item_BronzePlatedCopperShovel",ExtracraftToolMaterial.BPCOPPER)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPCopperShovel");
		BPCopperHoe = (new ItemEHoe(418, "item_BronzePlatedCopperHoe",ExtracraftToolMaterial.BPCOPPER)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPCopperHoe");
		BPCopperAxe = (new ItemEAxe(419, "item_BronzePlatedCopperAxe",ExtracraftToolMaterial.BPCOPPER)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPCopperAxe");
		
		BPTinHelmet = (new ItemExtracraftHelmet(420,"item_BronzePlatedTinHelmet", MaterialBPTin, proxy.addArmor("BPtin"), 0)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPTinHelmet");
		BPTinChest = (new ItemExtracraftChest(421, "item_BronzePlatedTinChestplate",MaterialBPTin, proxy.addArmor("BPtin"), 1)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPTinChest");
		BPTinPants = (new ItemExtracraftPants(422, "item_BronzePlatedTinLeggings",MaterialBPTin, proxy.addArmor("BPtin"),2)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPTinPants");
		BPTinBoots = (new ItemExtracraftBoots(423, "item_BronzePlatedTinBoots",MaterialBPTin, proxy.addArmor("BPtin"),3)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPTinBoots");
		
		BPSteelPickaxe = (new ItemEPickaxe(424, "item_BronzePlatedSteelPickaxe",ExtracraftToolMaterial.BPSTEEL)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPSteelPickaxe");
		BPSteelShovel = (new ItemEShovel(425, "item_BronzePlatedSteelShovel",ExtracraftToolMaterial.BPSTEEL)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPSteelShovel");
		BPSteelAxe = (new ItemEAxe(426, "item_BronzePlatedSteelAxe",ExtracraftToolMaterial.BPSTEEL)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPSteelAxe");
		BPSteelHoe = (new ItemEHoe(427, "item_BronzePlatedSteelHoe",ExtracraftToolMaterial.BPSTEEL)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPSteelHoe");
		BPSteelSword = (new ItemESword(428, "item_BronzePlatedSteelSword",ExtracraftToolMaterial.BPSTEEL)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPSteelSword");
		
		BPSteelHelmet = (new ItemExtracraftHelmet(429,"item_BronzePlatedSteelHelmet", MaterialBPSteel, proxy.addArmor("BPsteel"), 0)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPSteelHelmet");
		BPSteelChest = (new ItemExtracraftChest(430, "item_BronzePlatedSteelChestplate",MaterialBPSteel, proxy.addArmor("BPsteel"), 1)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPSteelChest");
		BPSteelPants = (new ItemExtracraftPants(431, "item_BronzePlatedSteelLeggings",MaterialBPSteel, proxy.addArmor("BPsteel"), 2)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPSteelPants");
		BPSteelBoots = (new ItemExtracraftBoots(432, "item_BronzePlatedSteelBoots",MaterialBPSteel, proxy.addArmor("BPsteel"), 3)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPSteelBoots");
		
		BPSandstonePickaxe = (new ItemEPickaxe(433,"item_BronzePlatedSandstonePickaxe", ExtracraftToolMaterial.BPSANDSTONE)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPSandstonePickaxe");
		BPSandstoneShovel = (new ItemEShovel(434, "item_BronzePlatedSandstoneShovel",ExtracraftToolMaterial.BPSANDSTONE)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPSandstoneShovel");
		BPSandstoneAxe = (new ItemEAxe(435, "item_BronzePlatedSandstoneAxe", ExtracraftToolMaterial.BPSANDSTONE)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPSandstoneAxe");
		BPSandstoneHoe = (new ItemEHoe(436, "item_BronzePlatedSandstoneHoe",ExtracraftToolMaterial.BPSANDSTONE)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPSandstoneHoe");
		BPSandstoneSword = (new ItemESword(437, "item_BronzePlatedSandstoneSword",ExtracraftToolMaterial.BPSANDSTONE)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPSandstoneSword");
		
		BPBedrockPickaxe = (new ItemEPickaxe(438, "item_BronzePlatedBedrockPickaxe", ExtracraftToolMaterial.BPBEDROCK)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPBedrockPickaxe");
		BPBedrockShovel = (new ItemEShovel(439, "item_BronzePlatedBedrockShovel",ExtracraftToolMaterial.BPBEDROCK)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPBedrockShovel");
		BPBedrockAxe = (new ItemEAxe(440, "item_BronzePlatedBedrockAxe",ExtracraftToolMaterial.BPBEDROCK)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPBedrockAxe");
		BPBedrockHoe = (new ItemEHoe(441, "item_BronzePlatedBedrockHoe",ExtracraftToolMaterial.BPBEDROCK)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPBedrockHoe");
		
		BPGranitePickaxe = (new ItemEPickaxe(442, "item_BronzePlatedGranitePickaxe",ExtracraftToolMaterial.BPGRANITE)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPGranitePickaxe");
		BPGraniteShovel = (new ItemEShovel(443, "item_BronzePlatedGraniteShovel",ExtracraftToolMaterial.BPGRANITE)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPGraniteShovel");
		BPGraniteAxe = (new ItemEAxe(444, "item_BronzePlatedGraniteAxe",ExtracraftToolMaterial.BPGRANITE)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPGraniteAxe");
		BPGraniteHoe = (new ItemEHoe(445, "item_BronzePlatedGraniteHoe",ExtracraftToolMaterial.BPGRANITE)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPGraniteHoe");
		BPGraniteSword = (new ItemESword(446, "item_BronzePlatedGraniteSword",ExtracraftToolMaterial.BPGRANITE)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPGraniteSword");
		
		BPWoodPickaxe = (new ItemEPickaxe(447, "item_BronzePlatedWoodPickaxe",ExtracraftToolMaterial.BPWOOD)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPWoodPickaxe");
		BPWoodShovel = (new ItemEShovel(448, "item_BronzePlatedWoodShovel",ExtracraftToolMaterial.BPWOOD)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPWoodShovel");
		BPWoodAxe = (new ItemEAxe(449, "item_BronzePlatedWoodAxe",ExtracraftToolMaterial.BPWOOD)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPWoodAxe");
		BPWoodHoe = (new ItemEHoe(450, "item_BronzePlatedWoodHoe",ExtracraftToolMaterial.BPWOOD)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPWoodHoe");
		BPWoodSword = (new ItemESword(451, "item_BronzePlatedWoodSword",ExtracraftToolMaterial.BPWOOD)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPWoodSword");
		
		BPClothHelmet = (new ItemExtracraftHelmet(452,"item_BronzePlatedLeatherHelmet", MaterialBPCloth, proxy.addArmor("BPcloth"), 0)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPClothHelmet");
		BPClothChest = (new ItemExtracraftChest(453, "item_BronzePlatedLeatherChestplate",MaterialBPCloth, proxy.addArmor("BPcloth"), 1)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPClothChest");
		BPClothPants = (new ItemExtracraftPants(454, "item_BronzePlatedLeatherLeggings",MaterialBPCloth, proxy.addArmor("BPcloth"), 2)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPClothPants");
		BPClothBoots = (new ItemExtracraftBoots(455, "item_BronzePlatedLeatherBoots",MaterialBPCloth, proxy.addArmor("BPcloth"), 3)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPClothBoots");
		
		BPStonePickaxe = (new ItemEPickaxe(456, "item_BronzePlatedStonePickaxe",ExtracraftToolMaterial.BPSTONE)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPStonePickaxe");
		BPStoneShovel = (new ItemEShovel(457, "item_BronzePlatedStoneShovel",ExtracraftToolMaterial.BPSTONE)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPStoneShovel");
		BPStoneAxe = (new ItemEAxe(458, "item_BronzePlatedStoneAxe",ExtracraftToolMaterial.BPSTONE)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPStoneAxe");
		BPStoneHoe = (new ItemEHoe(459, "item_BronzePlatedStoneHoe",ExtracraftToolMaterial.BPSTONE)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPStoneHoe");
		BPStoneSword = (new ItemESword(460, "item_BronzePlatedStoneSword",ExtracraftToolMaterial.BPSTONE)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPStoneSword");
		
		BPChainHelmet = (new ItemExtracraftHelmet(461,"item_BronzePlatedChainHelmet", MaterialBPChain, proxy.addArmor("BPchain"), 0)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPChainHelmet");
		BPChainChest = (new ItemExtracraftChest(462, "item_BronzePlatedChainChestplate",MaterialBPChain, proxy.addArmor("BPchain"), 1)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPChainChest");
		BPChainPants = (new ItemExtracraftPants(463, "item_BronzePlatedChainLeggings",MaterialBPChain, proxy.addArmor("BPchain"), 2)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPChainPants");
		BPChainBoots = (new ItemExtracraftBoots(464, "item_BronzePlatedChainBoots",MaterialBPChain, proxy.addArmor("BPchain"), 3)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPChainBoots");
		
		BPIronPickaxe = (new ItemEPickaxe(465, "item_BronzePlatedIronPickaxe",ExtracraftToolMaterial.BPIRON)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPIronPickaxe");
		BPIronShovel = (new ItemEShovel(466, "item_BronzePlatedIronShovel",ExtracraftToolMaterial.BPIRON)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPIronShovel");
		BPIronAxe = (new ItemEAxe(467, "item_BronzePlatedIronAxe",ExtracraftToolMaterial.BPIRON)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPIronAxe");
		BPIronHoe = (new ItemEHoe(468, "item_BronzePlatedIronHoe",ExtracraftToolMaterial.BPIRON)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPIronHoe");
		BPIronSword = (new ItemESword(469, "item_BronzePlatedIronSword",ExtracraftToolMaterial.BPIRON)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPStoneSword");
		
		BPIronHelmet = (new ItemExtracraftHelmet(470,"item_BronzePlatedIronHelmet", MaterialBPIron, proxy.addArmor("BPiron"), 0)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPIronHelmet");
		BPIronChest = (new ItemExtracraftChest(471, "item_BronzePlatedIronChestplate",MaterialBPIron, proxy.addArmor("BPiron"), 1)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPIronChest");
		BPIronPants = (new ItemExtracraftPants(472, "item_BronzePlatedIronLeggings",MaterialBPIron, proxy.addArmor("BPiron"), 2)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPIronPants");
		BPIronBoots = (new ItemExtracraftBoots(473, "item_BronzePlatedIronBoots",MaterialBPIron, proxy.addArmor("BPiron"), 3)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPIronBoots");
		
		BPGoldPickaxe = (new ItemEPickaxe(474, "item_BronzePlatedGoldPickaxe",ExtracraftToolMaterial.BPGOLD)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPGoldPickaxe");
		BPGoldShovel = (new ItemEShovel(475, "item_BronzePlatedGoldShovel",ExtracraftToolMaterial.BPGOLD)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPGoldShovel");
		BPGoldAxe = (new ItemEAxe(476, "item_BronzePlatedGoldAxe",ExtracraftToolMaterial.BPGOLD)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPGoldAxe");
		BPGoldHoe = (new ItemEHoe(477, "item_BronzePlatedGoldHoe",ExtracraftToolMaterial.BPGOLD)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPGoldHoe");
		BPGoldSword = (new ItemESword(478, "item_BronzePlatedGoldSword",ExtracraftToolMaterial.BPGOLD)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPGoldSword");
		
		BPGoldHelmet = (new ItemExtracraftHelmet(479,"item_BronzePlatedGoldHelmet", MaterialBPGold, proxy.addArmor("BPgold"), 0)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPGoldHelmet");
		BPGoldChest = (new ItemExtracraftChest(480, "item_BronzePlatedGoldChestplate",MaterialBPGold, proxy.addArmor("BPgold"), 1)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPGoldChest");
		BPGoldPants = (new ItemExtracraftPants(481, "item_BronzePlatedGoldLeggings",MaterialBPGold, proxy.addArmor("BPgold"), 2)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPGoldPants");
		BPGoldBoots = (new ItemExtracraftBoots(482, "item_BronzePlatedGoldBoots",MaterialBPGold, proxy.addArmor("BPgold"), 3)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPGoldBoots");
		
		BPDiamondPickaxe = (new ItemEPickaxe(483, "item_BronzePlatedDiamondPickaxe",ExtracraftToolMaterial.BPEMERALD)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPDiamondPickaxe");
		BPDiamondShovel = (new ItemEShovel(484, "item_BronzePlatedDiamondShovel",ExtracraftToolMaterial.BPEMERALD)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPDiamondShovel");
		BPDiamondAxe = (new ItemEAxe(485, "item_BronzePlatedDiamondAxe",ExtracraftToolMaterial.BPEMERALD)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPDiamondAxe");
		BPDiamondHoe = (new ItemEHoe(486, "item_BronzePlatedDiamondHoe",ExtracraftToolMaterial.BPEMERALD)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPDiamondHoe");
		BPDiamondSword = (new ItemESword(487, "item_BronzePlatedDiamondSword",ExtracraftToolMaterial.BPEMERALD)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPDiamondSword");
		
		BPDiamondHelmet = (new ItemExtracraftHelmet(488,"item_BronzePlatedDiamondHelmet", MaterialBPDiamond, proxy.addArmor("BPdiamond"), 0)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPDiamondHelmet");
		BPDiamondChest = (new ItemExtracraftChest(489, "item_BronzePlatedDiamondChestplate",MaterialBPDiamond, proxy.addArmor("BPdiamond"), 1)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPDiamondChest");
		BPDiamondPants = (new ItemExtracraftPants(490, "item_BronzePlatedDiamondLeggings",MaterialBPDiamond, proxy.addArmor("BPdiamond"), 2)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPDiamondPants");
		BPDiamondBoots = (new ItemExtracraftBoots(491, "item_BronzePlatedDiamondBoots",MaterialBPDiamond, proxy.addArmor("BPdiamond"), 3)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPDiamondBoots");
		
		
	
		
		
		BPZirconiumSword = (new ItemESword(379, "item_BronzePlatedZirconiumSword", ExtracraftToolMaterial.BPZIRCONIUM)).setCreativeTab(tabOresItems).setUnlocalizedName("BPZirconiumSword");
		BPZirconiumPickaxe = (new ItemEPickaxe(380, "item_BronzePlatedZirconiumPickaxe", ExtracraftToolMaterial.BPZIRCONIUM)).setCreativeTab(tabOresItems).setUnlocalizedName("BPZirconiumPickaxe");
		BPZirconiumShovel = (new ItemEShovel(381, "item_BronzePlatedZirconiumShovel", ExtracraftToolMaterial.BPZIRCONIUM)).setCreativeTab(tabOresItems).setUnlocalizedName("BPZirconiumShovel");
		BPZirconiumHoe = (new ItemEHoe(382, "item_BronzePlatedZirconiumHoe", ExtracraftToolMaterial.BPZIRCONIUM)).setCreativeTab(tabOresItems).setUnlocalizedName("BPZirconiumHoe");
		BPZirconiumAxe = (new ItemEAxe(383, "item_BronzePlatedZirconiumAxe", ExtracraftToolMaterial.BPZIRCONIUM)).setCreativeTab(tabOresItems).setUnlocalizedName("BPZirconiumAxe");
	
		BPZirconiumHelmet = (new ItemExtracraftHelmet(384, "item_BronzePlatedZirconiumHelmet", MaterialBPZirconium, proxy.addArmor("BPzirconium"), 0)).setCreativeTab(tabOresItems).setUnlocalizedName("BPZirconiumHelmet");
		BPZirconiumChest = (new ItemExtracraftChest(385, "item_BronzePlatedZirconiumChestplate", MaterialBPZirconium, proxy.addArmor("BPzirconium"), 1)).setCreativeTab(tabOresItems).setUnlocalizedName("BPZirconiumChest");
		BPZirconiumPants = (new ItemExtracraftPants(386, "item_BronzePlatedZirconiumLeggings",MaterialBPZirconium, proxy.addArmor("BPzirconium"),2)).setCreativeTab(tabOresItems).setUnlocalizedName("BPZirconiumPants");
		BPZirconiumBoots = (new ItemExtracraftBoots(387, "item_BronzePlatedZirconiumBoots", MaterialBPZirconium, proxy.addArmor("BPzirconium"),3)).setCreativeTab(tabOresItems).setUnlocalizedName("BPZirconiumBoots");
		
		BPTitaniumSword = (new ItemESword(388,"item_BronzePlatedTitaniumSword", ExtracraftToolMaterial.BPTITANIUM)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPTitaniumSword");
		BPTitaniumPickaxe = (new ItemEPickaxe(389, "item_BronzePlatedTitaniumPickaxe", ExtracraftToolMaterial.BPTITANIUM)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPTitaniumPickaxe");
		BPTitaniumShovel = (new ItemEShovel(390, "item_BronzePlatedTitaniumShovel", ExtracraftToolMaterial.BPTITANIUM)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPTitaniumShovel");
		BPTitaniumHoe = (new ItemEHoe(391, "item_BronzePlatedTitaniumHoe",ExtracraftToolMaterial.BPTITANIUM)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPTitaniumHoe");
		BPTitaniumAxe = (new ItemEAxe(392, "item_BronzePlatedTitaniumAxe",ExtracraftToolMaterial.BPTITANIUM)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPTitaniumAxe");
	
		BPTitaniumHelmet = (new ItemExtracraftHelmet(393, "item_BronzePlatedTitaniumHelmet", MaterialBPTitanium, proxy.addArmor("BPtitanium"), 0)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPTitaniumHelmet");
		BPTitaniumChest = (new ItemExtracraftChest(394, "item_BronzePlatedTitaniumChestplate",MaterialBPTitanium, proxy.addArmor("BPtitanium"), 1)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPTitaniumChest");
		BPTitaniumPants = (new ItemExtracraftPants(395, "item_BronzePlatedTitaniumLeggings", MaterialBPTitanium, proxy.addArmor("BPtitanium"),2)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPTitaniumPants");
		BPTitaniumBoots = (new ItemExtracraftBoots(396, "item_BronzePlatedTitaniumBoots",MaterialBPTitanium, proxy.addArmor("BPtitanium"),3)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPTitaniumBoots");
		
		BPToriteSword = (new ItemESword(397, "item_BronzePlatedToriteSword", ExtracraftToolMaterial.BPTORITE)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPToriteSword");
		BPToritePickaxe = (new ItemEPickaxe(398, "item_BronzePlatedToritePickaxe", ExtracraftToolMaterial.BPTORITE)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPToritePickaxe");
		BPToriteShovel = (new ItemEShovel(399, "item_BronzePlatedToriteShovel", ExtracraftToolMaterial.BPTORITE)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPToriteShovel");
		BPToriteHoe = (new ItemEHoe(400, "item_BronzePlatedToriteHoe",ExtracraftToolMaterial.BPTORITE)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPToriteHoe");
		BPToriteAxe = (new ItemEAxe(401, "item_BronzePlatedToriteAxe",ExtracraftToolMaterial.BPTORITE)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPToriteAxe");
		
		BPToriteHelmet = (new ItemExtracraftHelmet(402, "item_BronzePlatedToriteHelmet",MaterialBPTorite, proxy.addArmor("BPtorite"), 0)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPToriteHelmet");
		BPToriteChest = (new ItemExtracraftChest(403, "item_BronzePlatedToriteChestplate",MaterialBPTorite, proxy.addArmor("BPtorite"), 1)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPToriteChest");
		BPToritePants = (new ItemExtracraftPants(404, "item_BronzePlatedToriteLeggings",MaterialBPTorite, proxy.addArmor("BPtorite"),2)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPToritePants");
		BPToriteBoots = (new ItemExtracraftBoots(405, "item_BronzePlatedToriteBoots",MaterialBPTorite, proxy.addArmor("BPtorite"),3)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPToriteBoots");
		
		BPBlaziumSword = (new ItemESword(406, "item_BronzePlatedFireSword", ExtracraftToolMaterial.BPBLAZIUM)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPBlaziumSword");
		BPBlaziumPickaxe = (new ItemEPickaxe(407, "item_BronzePlatedFirePickaxe", ExtracraftToolMaterial.BPBLAZIUM)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPBlaziumPickaxe");
		BPBlaziumShovel = (new ItemEShovel(408, "item_BronzePlatedFireShovel",ExtracraftToolMaterial.BPBLAZIUM)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPBlaziumShovel");
		BPBlaziumHoe = (new ItemEHoe(409, "item_BronzePlatedFireHoe",ExtracraftToolMaterial.BPBLAZIUM)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPBlaziumHoe");
		BPBlaziumAxe = (new ItemEAxe(410, "item_BronzePlatedFireAxe",ExtracraftToolMaterial.BPBLAZIUM)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPBlaziumAxe");
		
		BPBlaziumHelmet = (new ItemExtracraftHelmet(411,"item_BronzePlatedFireHelmet", MaterialBPBlazium, proxy.addArmor("BPfire"), 0)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPBlaziumHelmet");
		BPBlaziumChest = (new ItemExtracraftChest(412, "item_BronzePlatedFireChestplate",MaterialBPBlazium, proxy.addArmor("BPfire"), 1)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPBlaziumChest");
		BPBlaziumPants = (new ItemExtracraftPants(413, "item_BronzePlatedFireLeggings",MaterialBPBlazium, proxy.addArmor("BPfire"),2)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPBlaziumPants");
		BPBlaziumBoots = (new ItemExtracraftBoots(414, "item_BronzePlatedFireBoots",MaterialBPBlazium, proxy.addArmor("BPfire"),3)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPBlaziumBoots");
		
		BPCopperSword = (new ItemESword(415, "item_BronzePlatedCopperSword",ExtracraftToolMaterial.BPCOPPER)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPCopperSword");
		BPCopperPickaxe = (new ItemEPickaxe(416, "item_BronzePlatedCopperPickaxe",ExtracraftToolMaterial.BPCOPPER)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPCopperPickaxe");
		BPCopperShovel = (new ItemEShovel(417, "item_BronzePlatedCopperShovel",ExtracraftToolMaterial.BPCOPPER)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPCopperShovel");
		BPCopperHoe = (new ItemEHoe(418, "item_BronzePlatedCopperHoe",ExtracraftToolMaterial.BPCOPPER)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPCopperHoe");
		BPCopperAxe = (new ItemEAxe(419, "item_BronzePlatedCopperAxe",ExtracraftToolMaterial.BPCOPPER)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPCopperAxe");
		
		BPTinHelmet = (new ItemExtracraftHelmet(420,"item_BronzePlatedTinHelmet", MaterialBPTin, proxy.addArmor("BPtin"), 0)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPTinHelmet");
		BPTinChest = (new ItemExtracraftChest(421, "item_BronzePlatedTinChestplate",MaterialBPTin, proxy.addArmor("BPtin"), 1)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPTinChest");
		BPTinPants = (new ItemExtracraftPants(422, "item_BronzePlatedTinLeggings",MaterialBPTin, proxy.addArmor("BPtin"),2)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPTinPants");
		BPTinBoots = (new ItemExtracraftBoots(423, "item_BronzePlatedTinBoots",MaterialBPTin, proxy.addArmor("BPtin"),3)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPTinBoots");
		
		BPSteelPickaxe = (new ItemEPickaxe(424, "item_BronzePlatedSteelPickaxe",ExtracraftToolMaterial.BPSTEEL)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPSteelPickaxe");
		BPSteelShovel = (new ItemEShovel(425, "item_BronzePlatedSteelShovel",ExtracraftToolMaterial.BPSTEEL)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPSteelShovel");
		BPSteelAxe = (new ItemEAxe(426, "item_BronzePlatedSteelAxe",ExtracraftToolMaterial.BPSTEEL)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPSteelAxe");
		BPSteelHoe = (new ItemEHoe(427, "item_BronzePlatedSteelHoe",ExtracraftToolMaterial.BPSTEEL)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPSteelHoe");
		BPSteelSword = (new ItemESword(428, "item_BronzePlatedSteelSword",ExtracraftToolMaterial.BPSTEEL)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPSteelSword");
		
		BPSteelHelmet = (new ItemExtracraftHelmet(429,"item_BronzePlatedSteelHelmet", MaterialBPSteel, proxy.addArmor("BPsteel"), 0)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPSteelHelmet");
		BPSteelChest = (new ItemExtracraftChest(430, "item_BronzePlatedSteelChestplate",MaterialBPSteel, proxy.addArmor("BPsteel"), 1)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPSteelChest");
		BPSteelPants = (new ItemExtracraftPants(431, "item_BronzePlatedSteelLeggings",MaterialBPSteel, proxy.addArmor("BPsteel"), 2)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPSteelPants");
		BPSteelBoots = (new ItemExtracraftBoots(432, "item_BronzePlatedSteelBoots",MaterialBPSteel, proxy.addArmor("BPsteel"), 3)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPSteelBoots");
		
		BPSandstonePickaxe = (new ItemEPickaxe(433,"item_BronzePlatedSandstonePickaxe", ExtracraftToolMaterial.BPSANDSTONE)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPSandstonePickaxe");
		BPSandstoneShovel = (new ItemEShovel(434, "item_BronzePlatedSandstoneShovel",ExtracraftToolMaterial.BPSANDSTONE)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPSandstoneShovel");
		BPSandstoneAxe = (new ItemEAxe(435, "item_BronzePlatedSandstoneAxe", ExtracraftToolMaterial.BPSANDSTONE)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPSandstoneAxe");
		BPSandstoneHoe = (new ItemEHoe(436, "item_BronzePlatedSandstoneHoe",ExtracraftToolMaterial.BPSANDSTONE)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPSandstoneHoe");
		BPSandstoneSword = (new ItemESword(437, "item_BronzePlatedSandstoneSword",ExtracraftToolMaterial.BPSANDSTONE)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPSandstoneSword");
		
		BPBedrockPickaxe = (new ItemEPickaxe(438, "item_BronzePlatedBedrockPickaxe", ExtracraftToolMaterial.BPBEDROCK)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPBedrockPickaxe");
		BPBedrockShovel = (new ItemEShovel(439, "item_BronzePlatedBedrockShovel",ExtracraftToolMaterial.BPBEDROCK)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPBedrockShovel");
		BPBedrockAxe = (new ItemEAxe(440, "item_BronzePlatedBedrockAxe",ExtracraftToolMaterial.BPBEDROCK)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPBedrockAxe");
		BPBedrockHoe = (new ItemEHoe(441, "item_BronzePlatedBedrockHoe",ExtracraftToolMaterial.BPBEDROCK)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPBedrockHoe");
		
		BPGranitePickaxe = (new ItemEPickaxe(442, "item_BronzePlatedGranitePickaxe",ExtracraftToolMaterial.BPGRANITE)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPGranitePickaxe");
		BPGraniteShovel = (new ItemEShovel(443, "item_BronzePlatedGraniteShovel",ExtracraftToolMaterial.BPGRANITE)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPGraniteShovel");
		BPGraniteAxe = (new ItemEAxe(444, "item_BronzePlatedGraniteAxe",ExtracraftToolMaterial.BPGRANITE)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPGraniteAxe");
		BPGraniteHoe = (new ItemEHoe(445, "item_BronzePlatedGraniteHoe",ExtracraftToolMaterial.BPGRANITE)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPGraniteHoe");
		BPGraniteSword = (new ItemESword(446, "item_BronzePlatedGraniteSword",ExtracraftToolMaterial.BPGRANITE)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPGraniteSword");
		
		BPWoodPickaxe = (new ItemEPickaxe(447, "item_BronzePlatedWoodPickaxe",ExtracraftToolMaterial.BPWOOD)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPWoodPickaxe");
		BPWoodShovel = (new ItemEShovel(448, "item_BronzePlatedWoodShovel",ExtracraftToolMaterial.BPWOOD)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPWoodShovel");
		BPWoodAxe = (new ItemEAxe(449, "item_BronzePlatedWoodAxe",ExtracraftToolMaterial.BPWOOD)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPWoodAxe");
		BPWoodHoe = (new ItemEHoe(450, "item_BronzePlatedWoodHoe",ExtracraftToolMaterial.BPWOOD)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPWoodHoe");
		BPWoodSword = (new ItemESword(451, "item_BronzePlatedWoodSword",ExtracraftToolMaterial.BPWOOD)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPWoodSword");
		
		BPClothHelmet = (new ItemExtracraftHelmet(452,"item_BronzePlatedLeatherHelmet", MaterialBPCloth, proxy.addArmor("BPcloth"), 0)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPClothHelmet");
		BPClothChest = (new ItemExtracraftChest(453, "item_BronzePlatedLeatherChestplate",MaterialBPCloth, proxy.addArmor("BPcloth"), 1)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPClothChest");
		BPClothPants = (new ItemExtracraftPants(454, "item_BronzePlatedLeatherLeggings",MaterialBPCloth, proxy.addArmor("BPcloth"), 2)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPClothPants");
		BPClothBoots = (new ItemExtracraftBoots(455, "item_BronzePlatedLeatherBoots",MaterialBPCloth, proxy.addArmor("BPcloth"), 3)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPClothBoots");
		
		BPStonePickaxe = (new ItemEPickaxe(456, "item_BronzePlatedStonePickaxe",ExtracraftToolMaterial.BPSTONE)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPStonePickaxe");
		BPStoneShovel = (new ItemEShovel(457, "item_BronzePlatedStoneShovel",ExtracraftToolMaterial.BPSTONE)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPStoneShovel");
		BPStoneAxe = (new ItemEAxe(458, "item_BronzePlatedStoneAxe",ExtracraftToolMaterial.BPSTONE)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPStoneAxe");
		BPStoneHoe = (new ItemEHoe(459, "item_BronzePlatedStoneHoe",ExtracraftToolMaterial.BPSTONE)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPStoneHoe");
		BPStoneSword = (new ItemESword(460, "item_BronzePlatedStoneSword",ExtracraftToolMaterial.BPSTONE)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPStoneSword");
		
		BPChainHelmet = (new ItemExtracraftHelmet(461,"item_BronzePlatedChainHelmet", MaterialBPChain, proxy.addArmor("BPchain"), 0)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPChainHelmet");
		BPChainChest = (new ItemExtracraftChest(462, "item_BronzePlatedChainChestplate",MaterialBPChain, proxy.addArmor("BPchain"), 1)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPChainChest");
		BPChainPants = (new ItemExtracraftPants(463, "item_BronzePlatedChainLeggings",MaterialBPChain, proxy.addArmor("BPchain"), 2)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPChainPants");
		BPChainBoots = (new ItemExtracraftBoots(464, "item_BronzePlatedChainBoots",MaterialBPChain, proxy.addArmor("BPchain"), 3)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPChainBoots");
		
		BPIronPickaxe = (new ItemEPickaxe(465, "item_BronzePlatedIronPickaxe",ExtracraftToolMaterial.BPIRON)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPIronPickaxe");
		BPIronShovel = (new ItemEShovel(466, "item_BronzePlatedIronShovel",ExtracraftToolMaterial.BPIRON)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPIronShovel");
		BPIronAxe = (new ItemEAxe(467, "item_BronzePlatedIronAxe",ExtracraftToolMaterial.BPIRON)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPIronAxe");
		BPIronHoe = (new ItemEHoe(468, "item_BronzePlatedIronHoe",ExtracraftToolMaterial.BPIRON)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPIronHoe");
		BPIronSword = (new ItemESword(469, "item_BronzePlatedIronSword",ExtracraftToolMaterial.BPIRON)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPStoneSword");
		
		BPIronHelmet = (new ItemExtracraftHelmet(470,"item_BronzePlatedIronHelmet", MaterialBPIron, proxy.addArmor("BPiron"), 0)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPIronHelmet");
		BPIronChest = (new ItemExtracraftChest(471, "item_BronzePlatedIronChestplate",MaterialBPIron, proxy.addArmor("BPiron"), 1)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPIronChest");
		BPIronPants = (new ItemExtracraftPants(472, "item_BronzePlatedIronLeggings",MaterialBPIron, proxy.addArmor("BPiron"), 2)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPIronPants");
		BPIronBoots = (new ItemExtracraftBoots(473, "item_BronzePlatedIronBoots",MaterialBPIron, proxy.addArmor("BPiron"), 3)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPIronBoots");
		
		BPGoldPickaxe = (new ItemEPickaxe(474, "item_BronzePlatedGoldPickaxe",ExtracraftToolMaterial.BPGOLD)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPGoldPickaxe");
		BPGoldShovel = (new ItemEShovel(475, "item_BronzePlatedGoldShovel",ExtracraftToolMaterial.BPGOLD)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPGoldShovel");
		BPGoldAxe = (new ItemEAxe(476, "item_BronzePlatedGoldAxe",ExtracraftToolMaterial.BPGOLD)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPGoldAxe");
		BPGoldHoe = (new ItemEHoe(477, "item_BronzePlatedGoldHoe",ExtracraftToolMaterial.BPGOLD)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPGoldHoe");
		BPGoldSword = (new ItemESword(478, "item_BronzePlatedGoldSword",ExtracraftToolMaterial.BPGOLD)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPGoldSword");
		
		BPGoldHelmet = (new ItemExtracraftHelmet(479,"item_BronzePlatedGoldHelmet", MaterialBPGold, proxy.addArmor("BPgold"), 0)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPGoldHelmet");
		BPGoldChest = (new ItemExtracraftChest(480, "item_BronzePlatedGoldChestplate",MaterialBPGold, proxy.addArmor("BPgold"), 1)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPGoldChest");
		BPGoldPants = (new ItemExtracraftPants(481, "item_BronzePlatedGoldLeggings",MaterialBPGold, proxy.addArmor("BPgold"), 2)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPGoldPants");
		BPGoldBoots = (new ItemExtracraftBoots(482, "item_BronzePlatedGoldBoots",MaterialBPGold, proxy.addArmor("BPgold"), 3)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPGoldBoots");
		
		BPDiamondPickaxe = (new ItemEPickaxe(483, "item_BronzePlatedDiamondPickaxe",ExtracraftToolMaterial.BPEMERALD)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPDiamondPickaxe");
		BPDiamondShovel = (new ItemEShovel(484, "item_BronzePlatedDiamondShovel",ExtracraftToolMaterial.BPEMERALD)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPDiamondShovel");
		BPDiamondAxe = (new ItemEAxe(485, "item_BronzePlatedDiamondAxe",ExtracraftToolMaterial.BPEMERALD)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPDiamondAxe");
		BPDiamondHoe = (new ItemEHoe(486, "item_BronzePlatedDiamondHoe",ExtracraftToolMaterial.BPEMERALD)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPDiamondHoe");
		BPDiamondSword = (new ItemESword(487, "item_BronzePlatedDiamondSword",ExtracraftToolMaterial.BPEMERALD)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPDiamondSword");
		
		BPDiamondHelmet = (new ItemExtracraftHelmet(488,"item_BronzePlatedDiamondHelmet", MaterialBPDiamond, proxy.addArmor("BPdiamond"), 0)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPDiamondHelmet");
		BPDiamondChest = (new ItemExtracraftChest(489, "item_BronzePlatedDiamondChestplate",MaterialBPDiamond, proxy.addArmor("BPdiamond"), 1)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPDiamondChest");
		BPDiamondPants = (new ItemExtracraftPants(490, "item_BronzePlatedDiamondLeggings",MaterialBPDiamond, proxy.addArmor("BPdiamond"), 2)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPDiamondPants");
		BPDiamondBoots = (new ItemExtracraftBoots(491, "item_BronzePlatedDiamondBoots",MaterialBPDiamond, proxy.addArmor("BPdiamond"), 3)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPDiamondBoots");
		
		SmoothRadiantQuartz = (new EOBlock(800, "block_SmoothRadiantQuartz", Material.rock)).setHardness(6F).setResistance(10F).setStepSound(Block.soundMetalFootstep).setCreativeTab(ExtraOres.tabOresBlocks).setLightValue(0.5F).setUnlocalizedName("SmoothRadiantQuartz");
		ChiseledRadiantQuartz = (new EOBlock(801, "block_ChiseledRadiantQuartz", Material.rock)).setHardness(6F).setResistance(10F).setStepSound(Block.soundMetalFootstep).setCreativeTab(ExtraOres.tabOresBlocks).setLightValue(0.5F).setUnlocalizedName("ChiseledRadiantQuartz");
		PillarRadiantQuartz = (new BlockRadiantPillar(802)).setHardness(6F).setResistance(10F).setStepSound(Block.soundMetalFootstep).setCreativeTab(ExtraOres.tabOresBlocks).setLightValue(0.5F).setUnlocalizedName("PillarRadiantQuartz");
		RadiantQuartzOre = (new EOBlock(803, "block_RadiantQuartzOre", Material.rock)).setHardness(5.5F).setResistance(10F).setStepSound(Block.soundMetalFootstep).setCreativeTab(ExtraOres.tabOresBlocks).setLightValue(0.4F).setUnlocalizedName("RadiantQuartzOre");
		
		RadiantQuartz = (new EItemFoiled(804, "item_RadiantQuartz")).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("RadiantQuartz");
		
		Godstone = (new BlockGodstone(805, Material.rock)).setHardness(6F).setResistance(9F).setCreativeTab(tabOresBlocks).setStepSound(Block.soundMetalFootstep).setLightValue(1F).setUnlocalizedName("Godstone");
		
		melterIdle = (new BlockMelter(806, false)).setHardness(6F).setResistance(8.0F).setCreativeTab(ExtraOres.tabOresBlocks).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("melter");
		melterBurning = (new BlockMelter(807, true)).setHardness(6F).setResistance(8.0F).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("melter");
	}
	
	@Init
    public void loadNew(FMLInitializationEvent event)

	{

		//this is the same as the load() method. add all names and recipes here.
		//recipes are GameRegistry.addRecipe, ect. No longer Modloader.blablabla (except for add names)
        TickRegistry.registerTickHandler(new ClientTickHandler(EnumSet.of(TickType.CLIENT)), Side.CLIENT);
        TickRegistry.registerTickHandler(new ServerTickHandler(EnumSet.of(TickType.SERVER)), Side.SERVER);
        proxy.registerRenderThings(); //this allows seperate renderings for server and client
        
        GameRegistry.registerFuelHandler(new UraniumFuelHandler());
        GameRegistry.registerFuelHandler(new PlutoniumFuelHandler());
        
        EONameManager.loadNames();
		EORecipeManager.loadRecipes();
		
		MinecraftForge.setBlockHarvestLevel(CopperOre, "pickaxe", 0);
		MinecraftForge.setBlockHarvestLevel(CopperBlock, "pickaxe", 0);
		MinecraftForge.setBlockHarvestLevel(TinOre, "pickaxe", 1);
		MinecraftForge.setBlockHarvestLevel(TinBlock, "pickaxe", 1);
		MinecraftForge.setBlockHarvestLevel(ZirconiumOre, "pickaxe", 2);
		MinecraftForge.setBlockHarvestLevel(ZirconiumBlock, "pickaxe", 2);
		MinecraftForge.setBlockHarvestLevel(ToriteOre, "pickaxe", 2);
		MinecraftForge.setBlockHarvestLevel(ToriteBlock, "pickaxe", 2);
		MinecraftForge.setBlockHarvestLevel(TitaniumOre, "pickaxe", 3);
		MinecraftForge.setBlockHarvestLevel(TitaniumBlock, "pickaxe", 3);
		MinecraftForge.setBlockHarvestLevel(UraniumOre, "pickaxe", 2);
		MinecraftForge.setBlockHarvestLevel(PlutoniumOre, "pickaxe", 2);
		MinecraftForge.setBlockHarvestLevel(SunstoneOre, "pickaxe", 2);
		MinecraftForge.setBlockHarvestLevel(Granite, "pickaxe", 1);
		MinecraftForge.setBlockHarvestLevel(GraniteBrick, "pickaxe", 1);
		MinecraftForge.setBlockHarvestLevel(Quartzite, "pickaxe", 1);
		MinecraftForge.setBlockHarvestLevel(QuartziteTile, "pickaxe", 1);
		MinecraftForge.setBlockHarvestLevel(SmoothQuartzite, "pickaxe", 1);
		MinecraftForge.setBlockHarvestLevel(PillarQuartzite, "pickaxe", 1);
		MinecraftForge.setBlockHarvestLevel(ChiseledQuartzite, "pickaxe", 1);
		MinecraftForge.setBlockHarvestLevel(SmoothQuartzTile, "pickaxe", 1);
		MinecraftForge.setBlockHarvestLevel(BlaziumOre, "pickaxe", 2);
		MinecraftForge.setBlockHarvestLevel(BlaziumBlock, "pickaxe", 2);
		MinecraftForge.setBlockHarvestLevel(SoulOre, "shovel", 2);
		MinecraftForge.setBlockHarvestLevel(TinPlatedCobble, "pickaxe", 1);
		MinecraftForge.setBlockHarvestLevel(TinPlatedMossy, "pickaxe", 1);
		MinecraftForge.setBlockHarvestLevel(TinPlatedStoneBrick, "pickaxe", 1);
		MinecraftForge.setBlockHarvestLevel(TinPlatedChiseled, "pickaxe", 1);
		MinecraftForge.setBlockHarvestLevel(TinPlatedGranite, "pickaxe", 1);
		MinecraftForge.setBlockHarvestLevel(BronzeBlock, "pickaxe", 1);
		MinecraftForge.setBlockHarvestLevel(BronzePlatedCobble, "pickaxe", 1);
		MinecraftForge.setBlockHarvestLevel(BronzePlatedMossy, "pickaxe", 1);
		MinecraftForge.setBlockHarvestLevel(BronzePlatedStoneBrick, "pickaxe", 1);
		MinecraftForge.setBlockHarvestLevel(BronzePlatedChiseled, "pickaxe", 1);
		MinecraftForge.setBlockHarvestLevel(BronzePlatedGranite, "pickaxe", 1);
		MinecraftForge.setBlockHarvestLevel(ExtraOresBedrock, "pickaxe", 4);
		MinecraftForge.setBlockHarvestLevel(SteelPlatedCobble, "pickaxe", 1);
		MinecraftForge.setBlockHarvestLevel(SteelPlatedMossy, "pickaxe", 1);
		MinecraftForge.setBlockHarvestLevel(SteelPlatedStoneBrick, "pickaxe", 1);
		MinecraftForge.setBlockHarvestLevel(SteelPlatedChiseled, "pickaxe", 1);
		MinecraftForge.setBlockHarvestLevel(SteelPlatedGranite, "pickaxe", 1);
		MinecraftForge.setBlockHarvestLevel(RawUraniumBlock, "pickaxe", 0);
		MinecraftForge.setBlockHarvestLevel(RawPlutoniumBlock, "pickaxe", 0);
		MinecraftForge.setBlockHarvestLevel(SmoothRadiantQuartz, "pickaxe", 3);
		MinecraftForge.setBlockHarvestLevel(PillarRadiantQuartz, "pickaxe", 3);
		MinecraftForge.setBlockHarvestLevel(ChiseledRadiantQuartz, "pickaxe", 3);
		MinecraftForge.setBlockHarvestLevel(RadiantQuartzOre, "pickaxe", 3);
		MinecraftForge.setBlockHarvestLevel(Godstone, "pickaxe", 2);
		
		EOBlockRegister.registerBlocks();
		NetworkRegistry.instance().registerGuiHandler(this, guiHandler);
		
	}
	
	@PreInit  // never before implemented
     public void myNewPreLoadMethod(FMLPreInitializationEvent evt)
	
    {
		GameRegistry.registerWorldGenerator(new ExtracraftOreGenerator());
		
		EntityRegistry.registerModEntity(EntityPlutoniumPrimed.class, "Plutonium", 4, this, 350, 5, false);
		EntityRegistry.registerModEntity(EntityGrenade.class, "Grenade", 2, this, 40, 3, true);
		EntityRegistry.registerModEntity(EntityNukePrimed.class, "NukePrimed", 3, this, 350, 5, false);
		EntityRegistry.registerModEntity(EntityGrenadeImpact.class, "GrenadeImpact", 4, this, 40, 3, true);
		EntityRegistry.registerModEntity(EntityGrenadeSticky.class, "GrenadeSticky", 5, this, 40, 3, true);
		
    }
	
	@PostInit // like the modsLoaded thing from ModLoader
    public void myNewPostLoadMethod(FMLPostInitializationEvent evt)
    {
		
    }
	
	public static void addRenderer(Map map)
	{
	map.put(EntityNukePrimed.class, new RenderNukePrimed());
	}
	

}
