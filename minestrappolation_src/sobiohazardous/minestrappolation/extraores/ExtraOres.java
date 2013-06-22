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
import sobiohazardous.minestrappolation.extraores.lib.EOBlockRegister;
import sobiohazardous.minestrappolation.extraores.lib.EONameManager;
import sobiohazardous.minestrappolation.extraores.lib.EORecipeManager;
import sobiohazardous.minestrappolation.extraores.misc.PlutoniumFuelHandler;
import sobiohazardous.minestrappolation.extraores.misc.UraniumFuelHandler;
import sobiohazardous.minestrappolation.extraores.plate.*;
import sobiohazardous.minestrappolation.extraores.proxy.CommonProxy;
import sobiohazardous.potionapi.PotionAPI;

import net.minecraft.block.Block;
import net.minecraft.block.BlockHalfSlab;
import net.minecraft.block.BlockStairs;
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
import net.minecraft.item.ItemSlab;
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
import net.minecraftforge.common.Configuration;
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
@Mod ( modid = "ExtraOres", name="Extrappolated Ores", version="A1.4")
public class ExtraOres 
{	
	@SidedProxy(clientSide = "sobiohazardous.minestrappolation.extraores.proxy.ClientProxy", serverSide = "sobiohazardous.minestrappolation.extraores.proxy.CommonProxy")
    public static CommonProxy proxy;
	
	public static int 
	zirconiumOreId, 
	zirconiumBlockId, 
	zirconiumIngotId, 
	zirconiumSwordId, 
	zirconiumPickaxeId, 
	zirconiumAxeId, 
	zirconiumHoeId, 
	zirconiumShovelId, 
	zirconiumHelmetId, 
	zirconiumChestId, 
	zirconiumPantsId, 
	zirconiumBootsId,
	uraniumOreId,
	uraniumId,
	rawUraniumBlockId,
	plutoniumOreId,
	plutoniumId,
	rawPlutoniumBlockId,
	titaniumOreId,
	titaniumIngotId,
	titaniumSwordId,
	titaniumPickaxeId,
	titaniumAxeId,
	titaniumHoeId,
	titaniumShovelId,
	titaniumHelmetId,
	titaniumChestId,
	titaniumPantsId,
	titaniumBootsId,
	titaniumBlockId,
	sunstoneId,
	sunstoneDustId,
	sunstoneOreId,
	toriteOreId,
	toriteIngotId,
	toriteBlockId,
	toriteSwordId,
	toritePickaxeId,
	toriteAxeId,
	toriteHoeId,
	toriteShovelId,
	toriteHelmetId,
	toriteChestId,
	toritePantsId,
	toriteBootsId,
	graniteId,
	graniteBrickId,
	quartziteId,
	quartziteTileId,
	blaziumOreId,
	blaziumBlockId,
	blaziumShardId,
	blaziumIngotId,
	blaziumSwordId,
	blaziumPickaxeId,
	blaziumAxeId,
	blaziumHoeId,
	blaziumShovelId,
	blaziumHelmetId,
	blaziumChestId,
	blaziumPantsId,
	blaziumBootsId,
	copperOreId,
	copperBlockId,
	copperIngotId,
	copperTarnishedId,
	copperSwordId,
	copperPickaxeId,
	copperAxeId,
	copperHoeId,
	copperShovelId,
	tinOreId,
	tinIngotId,
	tinBlockId,
	tinHelmetId,
	tinChestId,
	tinPantsId,
	tinBootsId,
	tinPlateId,
	bronzePlateId,
	steelPlateId,
	tinPlateItemId,
	bronzePlateItemId,
	steelPlateItemId,
	coalIronIngotId,
	steelIngotId,
	steelPickaxeId,
	steelAxeId,
	steelShovelId,
	steelHoeId,
	steelSwordId,
	steelHelmetId,
	steelChestId,
	steelPantsId,
	steelBootsId,
	steelBlockId,
	soulOreId,
	soulGemId,
	smoothQuartziteId,
	pillarQuartziteId,
	chiseledQuartziteId,
	smoothQuartzTileId,
	pinkQuartzId,
	sandstonePickaxeId,
	sandstoneShovelId,
	sandstoneAxeId,
	sandstoneHoeId,
	sandstoneSwordId,
	tinPlatedCobbleId,
	tinPlatedMossyId,
	tinPlatedStoneBrickId,
	tinPlatedChiseledId,
	tinPlatedGraniteId,
	bronzeIngotId,
	bronzePickaxeId,
	bronzeShovelId,
	bronzeAxeId,
	bronzeHoeId,
	bronzeSwordId,
	bronzeHelmetId,
	bronzeChestId,
	bronzePantsId,
	bronzeBootsId,
	bronzeBlockId,
	bronzePlatedCobbleId,
	bronzePlatedMossyId,
	bronzePlatedStoneBrickId,
	bronzePlatedChiseledId,
	bronzePlatedGraniteId,
	invinciumId,
	extraOresBedrockId,
	bedrockPickaxeId,
	bedrockAxeId,
	bedrockShovelId,
	bedrockHoeId,
	steelPlatedCobbleId,
	steelPlatedMossyId,
	steelPlatedStoneBrickId,
	steelPlatedChiseledId,
	steelPlatedGraniteId,
	granitePickaxeId,
	graniteShovelId,
	graniteAxeId,
	graniteHoeId,
	graniteSwordId,
	soulBottleId,
	nukeId,
	grenadeId,
	grenadeImpactId,
	grenadeStickyId,
	BPZirconiumSwordId,
	BPZirconiumPickaxeId,
	BPZirconiumAxeId,
	BPZirconiumHoeId,
	BPZirconiumShovelId,
	BPZirconiumHelmetId,
	BPZirconiumChestId,
	BPZirconiumPantsId,
	BPZirconiumBootsId,
	BPTitaniumSwordId,
	BPTitaniumPickaxeId,
	BPTitaniumAxeId,
	BPTitaniumHoeId,
	BPTitaniumShovelId,
	BPTitaniumHelmetId,
	BPTitaniumChestId,
	BPTitaniumPantsId,
	BPTitaniumBootsId,
	BPToriteSwordId,
	BPToritePickaxeId,
	BPToriteAxeId,
	BPToriteHoeId,
	BPToriteShovelId,
	BPToriteHelmetId,
	BPToriteChestId,
	BPToritePantsId,
	BPToriteBootsId,
	BPBlaziumSwordId,
	BPBlaziumPickaxeId,
	BPBlaziumAxeId,
	BPBlaziumHoeId,
	BPBlaziumShovelId,
	BPBlaziumHelmetId,
	BPBlaziumChestId,
	BPBlaziumPantsId,
	BPBlaziumBootsId,
	BPCopperSwordId,
	BPCopperPickaxeId,
	BPCopperAxeId,
	BPCopperHoeId,
	BPCopperShovelId,
	BPTinHelmetId,
	BPTinChestId,
	BPTinPantsId,
	BPTinBootsId,
	BPSteelPickaxeId,
	BPSteelAxeId,
	BPSteelShovelId,
	BPSteelHoeId,
	BPSteelSwordId,
	BPSteelHelmetId,
	BPSteelChestId,
	BPSteelPantsId,
	BPSteelBootsId,
	BPSandstonePickaxeId,
	BPSandstoneShovelId,
	BPSandstoneAxeId,
	BPSandstoneHoeId,
	BPSandstoneSwordId,
	BPBedrockPickaxeId,
	BPBedrockAxeId,
	BPBedrockShovelId,
	BPBedrockHoeId,
	BPGranitePickaxeId,
	BPGraniteShovelId,
	BPGraniteAxeId,
	BPGraniteHoeId,
	BPGraniteSwordId,
	BPWoodPickaxeId,
	BPWoodAxeId,
	BPWoodShovelId,
	BPWoodHoeId,
	BPWoodSwordId,
    BPClothHelmetId,
	BPClothChestId,
	BPClothPantsId,
	BPClothBootsId,
	BPStonePickaxeId,
	BPStoneAxeId,
	BPStoneShovelId,
	BPStoneHoeId,
	BPStoneSwordId,
	BPChainHelmetId,
	BPChainChestId,
	BPChainPantsId,
	BPChainBootsId,
	BPIronPickaxeId,
	BPIronAxeId,
	BPIronShovelId,
	BPIronHoeId,
	BPIronSwordId,
	BPIronHelmetId,
	BPIronChestId,
	BPIronPantsId,
	BPIronBootsId,
	BPGoldPickaxeId,
	BPGoldAxeId,
	BPGoldShovelId,
	BPGoldHoeId,
	BPGoldSwordId,
	BPGoldHelmetId,
	BPGoldChestId,
	BPGoldPantsId,
	BPGoldBootsId,
	BPDiamondPickaxeId,
	BPDiamondAxeId,
	BPDiamondShovelId,
	BPDiamondHoeId,
	BPDiamondSwordId,
	BPDiamondHelmetId,
	BPDiamondChestId,
	BPDiamondPantsId,
	BPDiamondBootsId,
	smoothRadiantQuartzId,
	chiseledRadiantQuartzId,
	pillarRadiantQuartzId,
	radiantQuartzOreId,
	radiantQuartzId,
	godstoneId,
	melterIdleId,
	melterBurningId,
	radiantQuartzStairsId,
	radiantQuartzSingleSlabId,
	radiantQuartzDoubleSlabId,
	pinkQuartzStairsId,
	pinkQuartzSingleSlabId,
	pinkQuartzDoubleSlabId;	
	
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
	
	public static Block RadiantQuartzStairs;
	public static BlockHalfSlab RadiantQuartzSingleSlab;
	public static BlockHalfSlab RadiantQuartzDoubleSlab;
	
	public static Block PinkQuartzStairs;
	public static BlockHalfSlab PinkQuartzSingleSlab;
	public static BlockHalfSlab PinkQuartzDoubleSlab;
	
	//less broken public static Block PinkQuartzSingleSlab;
	//less broken public static Block PinkQuartzDoubleSlab;
	
	public static int plateRenderId = RenderingRegistry.getNextAvailableRenderId();
	
	//broken public static BlockHalfSlab QuartziteSingleSlab;
	//broken public static BlockHalfSlab QuartziteDoubleSlab;
	
	public static CreativeTabs tabOresBlocks = new CreativeTabExtraoresBlocks(CreativeTabs.getNextID(),"Extrappolated Ores - Blocks");
	public static CreativeTabs tabOresItems = new CreativeTabExtraoresItems(CreativeTabs.getNextID(), "Extrappolated Ores - Items");
	
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
		ZirconiumOre = (new EOBlock(zirconiumOreId, "block_ZirconiumOre", Material.rock)).setHardness(5F).setCreativeTab(tabOresBlocks).setResistance(10F).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("ZirconiumOre");
		ZirconiumBlock = (new EOBlock(zirconiumBlockId, "block_Zirconium", Material.iron)).setHardness(5F).setCreativeTab(tabOresBlocks).setResistance(10F).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("ZirconiumBlock");
		ZirconiumIngot = (new EItem(zirconiumIngotId, "item_ZirconiumIngot")).setCreativeTab(tabOresItems).setUnlocalizedName("ZirconiumIngot");
		
		ZirconiumSword = (new ItemESword(zirconiumSwordId, "item_ZirconiumSword", ExtracraftToolMaterial.ZIRCONIUM)).setCreativeTab(tabOresItems).setUnlocalizedName("ZirconiumSword");
		ZirconiumPickaxe = (new ItemEPickaxe(zirconiumPickaxeId, "item_ZirconiumPickaxe", ExtracraftToolMaterial.ZIRCONIUM)).setCreativeTab(tabOresItems).setUnlocalizedName("ZirconiumPickaxe");
		ZirconiumShovel = (new ItemEShovel(zirconiumShovelId, "item_ZirconiumShovel", ExtracraftToolMaterial.ZIRCONIUM)).setCreativeTab(tabOresItems).setUnlocalizedName("ZirconiumShovel");
		ZirconiumHoe = (new ItemEHoe(zirconiumHoeId, "item_ZirconumHoe", ExtracraftToolMaterial.ZIRCONIUM)).setCreativeTab(tabOresItems).setUnlocalizedName("ZirconiumHoe");
		ZirconiumAxe = (new ItemEAxe(zirconiumAxeId, "item_ZirconiumAxe", ExtracraftToolMaterial.ZIRCONIUM)).setCreativeTab(tabOresItems).setUnlocalizedName("ZirconiumAxe");
	
		ZirconiumHelmet = (new ItemExtracraftHelmet(zirconiumHelmetId, "item_ZirconiumHelmet", MaterialZirconium, proxy.addArmor("zirconium"), 0)).setCreativeTab(tabOresItems).setUnlocalizedName("ZirconiumHelmet");
		ZirconiumChest = (new ItemExtracraftChest(zirconiumChestId, "item_ZirconiumChestplate", MaterialZirconium, proxy.addArmor("zirconium"), 1)).setCreativeTab(tabOresItems).setUnlocalizedName("ZurconiumChest");
		ZirconiumPants = (new ItemExtracraftPants(zirconiumPantsId, "item_ZirconiumLeggings",MaterialZirconium, proxy.addArmor("zirconium"),2)).setCreativeTab(tabOresItems).setUnlocalizedName("ZirconiumPants");
		ZirconiumBoots = (new ItemExtracraftBoots(zirconiumBootsId, "item_ZirconiumBoots", MaterialZirconium, proxy.addArmor("zirconium"),3)).setCreativeTab(tabOresItems).setUnlocalizedName("ZirconiumBoots");
				
		UraniumOre = (new BlockUraniumOre(uraniumOreId, Material.rock)).setHardness(5F).setResistance(10F).setCreativeTab(tabOresBlocks).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("UraniumOre");
		Uranium = (new EItem(uraniumId, "item_Uranium")).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("Uranium");
		RawUraniumBlock = (new BlockUraniumRaw(rawUraniumBlockId, Material.rock)).setHardness(6F).setResistance(9F).setCreativeTab(tabOresBlocks).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("RawUraniumBlock");
		
		PlutoniumOre = (new BlockPlutoniumOre(plutoniumOreId, Material.rock)).setHardness(5F).setResistance(10F).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("PlutoniumOre").setCreativeTab(tabOresBlocks);
		Plutonium = (new EItem(plutoniumOreId, "item_Plutonium")).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("Plutonium");
		RawPlutoniumBlock = (new BlockPlutoniumRaw(rawPlutoniumBlockId, Material.rock)).setHardness(6F).setResistance(9F).setCreativeTab(tabOresBlocks).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("RawPlutoniumBlock");
		
		TitaniumOre = (new EOBlock(titaniumOreId, "block_TitaniumOre", Material.rock)).setHardness(10F).setResistance(15F).setCreativeTab(ExtraOres.tabOresBlocks).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("TitaniumOre");
		TitaniumIngot = (new EItem(titaniumIngotId, "item_TitaniumIngot")).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("Titanium");
		
		TitaniumSword = (new ItemESword(titaniumSwordId,"item_TitaniumSword", ExtracraftToolMaterial.TITANIUM)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("TitaniumSword");
		TitaniumPickaxe = (new ItemEPickaxe(titaniumPickaxeId, "item_TitaniumPickaxe", ExtracraftToolMaterial.TITANIUM)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("TitaniumPickaxe");
		TitaniumShovel = (new ItemEShovel(titaniumShovelId, "item_TitaniumShovel", ExtracraftToolMaterial.TITANIUM)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("TitaniumShovel");
		TitaniumHoe = (new ItemEHoe(titaniumHoeId, "item_TitaniumHoe",ExtracraftToolMaterial.TITANIUM)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("TitaniumHoe");
		TitaniumAxe = (new ItemEAxe(titaniumAxeId, "item_TitaniumAxe",ExtracraftToolMaterial.TITANIUM)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("TitaniumAxe");
	
		TitaniumHelmet = (new ItemExtracraftHelmet(titaniumHelmetId, "item_TitaniumHelmet", MaterialTitanium, proxy.addArmor("titanium"), 0)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("TitaniumHelmet");
		TitaniumChest = (new ItemExtracraftChest(titaniumChestId, "item_TitaniumChestplate",MaterialTitanium, proxy.addArmor("titanium"), 1)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("TitaniumChest");
		TitaniumPants = (new ItemExtracraftPants(titaniumPantsId, "item_TitaniumLeggings", MaterialTitanium, proxy.addArmor("titanium"),2)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("TitaniumPants");
		TitaniumBoots = (new ItemExtracraftBoots(titaniumBootsId, "item_TitaniumBoots",MaterialTitanium, proxy.addArmor("titanium"),3)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("TitaniumBoots");
		
		TitaniumBlock = (new EOBlock(titaniumBlockId, "block_Titanium", Material.iron)).setHardness(10F).setResistance(12000000.0F).setCreativeTab(ExtraOres.tabOresBlocks).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("Titanium Block");
		
		Sunstone = (new BlockSunstone(sunstoneId,Material.glass)).setHardness(0.3F).setStepSound(Block.soundGlassFootstep).setCreativeTab(ExtraOres.tabOresBlocks).setLightValue(1.0F).setUnlocalizedName("Sunstone");
		SunstoneDust = (new EItem(sunstoneDustId, "item_SunstoneShard")).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("SunstoneDust");
		SunstoneOre = (new EOBlock(sunstoneOreId, "block_SunstoneOre", Material.rock)).setHardness(7F).setResistance(11F).setStepSound(Block.soundMetalFootstep).setCreativeTab(ExtraOres.tabOresBlocks).setUnlocalizedName("SunstoneOre").setLightValue(1F);
		//Old Sunstone Ore code: SunstoneOre = (new BlockSunstoneOre(212, 40)).setHardness(7F).setStepSound(Block.soundStoneFootstep).setCreativeTab(ExtraOres.tabExtra).setLightValue(1.0F).setBlockName("Sunstone Ore");
		
		ToriteOre = (new EOBlock(toriteOreId, "block_ToriteOre", Material.rock)).setHardness(7F).setResistance(11F).setStepSound(Block.soundMetalFootstep).setCreativeTab(ExtraOres.tabOresBlocks).setUnlocalizedName("ToriteOre");
		ToriteIngot = (new EItem(toriteIngotId, "item_ToriteIngot")).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("ToriteIngot");
		ToriteBlock = (new EOBlock(toriteBlockId, "block_Torite", Material.iron)).setHardness(6F).setResistance(10F).setCreativeTab(ExtraOres.tabOresBlocks).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("ToriteBlock");
	
		ToriteSword = (new ItemESword(toriteSwordId, "item_ToriteSword", ExtracraftToolMaterial.TORITE)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("ToriteSword");
		ToritePickaxe = (new ItemEPickaxe(toritePickaxeId, "item_ToritePickaxe", ExtracraftToolMaterial.TORITE)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("ToritePickaxe");
		ToriteShovel = (new ItemEShovel(toriteShovelId, "item_ToriteShovel", ExtracraftToolMaterial.TORITE)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("ToriteShovel");
		ToriteHoe = (new ItemEHoe(toriteHoeId, "item_ToriteHoe",ExtracraftToolMaterial.TORITE)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("ToriteHoe");
		ToriteAxe = (new ItemEAxe(toriteAxeId, "item_ToriteAxe",ExtracraftToolMaterial.TORITE)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("ToriteAxe");
		
		ToriteHelmet = (new ItemExtracraftHelmet(toriteHelmetId, "item_ToriteHelmet",MaterialTorite, proxy.addArmor("torite"), 0)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("ToriteHelmet");
		ToriteChest = (new ItemExtracraftChest(toriteChestId, "item_ToriteChestplate",MaterialTorite, proxy.addArmor("torite"), 1)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("ToriteChest");
		ToritePants = (new ItemExtracraftPants(toritePantsId, "item_ToriteLeggings",MaterialTorite, proxy.addArmor("torite"),2)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("ToritePants");
		ToriteBoots = (new ItemExtracraftBoots(toriteBootsId, "item_ToriteBoots",MaterialTorite, proxy.addArmor("torite"),3)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("ToriteBoots");
		
		Granite = (new EOBlock(graniteId, "block_Granite", Material.rock)).setHardness(5F).setResistance(9F).setStepSound(Block.soundMetalFootstep).setCreativeTab(ExtraOres.tabOresBlocks).setUnlocalizedName("Granite");
		GraniteBrick = (new EOBlock(graniteBrickId, "block_GraniteBrick", Material.rock)).setHardness(6F).setResistance(10F).setStepSound(Block.soundMetalFootstep).setCreativeTab(ExtraOres.tabOresBlocks).setUnlocalizedName("GraniteBrick");
		
		Quartzite = (new EOBlock(quartziteId, "block_PinkQuartzRaw", Material.rock)).setHardness(5F).setResistance(9F).setStepSound(Block.soundMetalFootstep).setCreativeTab(ExtraOres.tabOresBlocks).setUnlocalizedName("Quartzite");
		QuartziteTile = (new EOBlock(quartziteTileId, "block_PinkQuartzTileRough", Material.rock)).setHardness(6F).setResistance(10F).setStepSound(Block.soundMetalFootstep).setCreativeTab(ExtraOres.tabOresBlocks).setUnlocalizedName("QuartziteTile");
	    SmoothQuartzite = (new EOBlock(smoothQuartziteId, "block_PinkQuartzSmooth", Material.rock)).setHardness(6F).setResistance(10F).setStepSound(Block.soundMetalFootstep).setCreativeTab(ExtraOres.tabOresBlocks).setUnlocalizedName("SmoothQuartzite");
		PillarQuartzite = (new BlockPinkPillar(pillarQuartziteId)).setHardness(6F).setResistance(10F).setStepSound(Block.soundMetalFootstep).setCreativeTab(ExtraOres.tabOresBlocks).setUnlocalizedName("QuartzitePillar");
		ChiseledQuartzite = (new BlockPinkChiseled(chiseledQuartziteId)).setHardness(6F).setResistance(10F).setStepSound(Block.soundMetalFootstep).setCreativeTab(ExtraOres.tabOresBlocks).setUnlocalizedName("ChiseledQuartzite");
		SmoothQuartzTile = (new EOBlock(smoothQuartziteId, "block_PinkQuartzTileRefined", Material.rock)).setHardness(6F).setResistance(10F).setStepSound(Block.soundMetalFootstep).setCreativeTab(ExtraOres.tabOresBlocks).setUnlocalizedName("SmoothQuartzTile");
		PinkQuartz = (new EItem(pinkQuartzId, "item_PinkQuartz")).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("PinkQuartz");
	    
		BlaziumOre = (new EOBlock(blaziumOreId, "block_BlaziumOre", Material.rock)).setHardness(7F).setResistance(11F).setStepSound(Block.soundMetalFootstep).setCreativeTab(ExtraOres.tabOresBlocks).setUnlocalizedName("BlaziumOre").setLightValue(0.5F);
		//Experimental Blazium Ore Code: BlaziumOre = (new BlockBlaziumOre(204, 5)).setHardness(7F).setResistance(11F).setStepSound(Block.soundMetalFootstep).setCreativeTab(ExtraOres.tabExtra).setLightValue(0.5F).setBlockName("Blazium Ore");
		BlaziumBlock = (new BlockBlazium(blaziumBlockId, Material.iron)).setHardness(8F).setResistance(12F).setStepSound(Block.soundMetalFootstep).setCreativeTab(ExtraOres.tabOresBlocks).setUnlocalizedName("BlaziumBlock").setLightValue(0.7F);
		
		BlaziumIngot = (new EItem(blaziumIngotId, "item_BlaziumIngot")).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BlaziumIngot");
		BlazeShard = (new EItem(blaziumShardId, "item_BlazeShard")).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BlazeShard");
	
		BlaziumSword = (new ItemESword(blaziumSwordId, "item_FireSword", ExtracraftToolMaterial.BLAZIUM)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BlaziumSword");
		BlaziumPickaxe = (new ItemEPickaxe(blaziumPickaxeId, "item_FirePickaxe", ExtracraftToolMaterial.BLAZIUM)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BlaziumPickaxe");
		BlaziumShovel = (new ItemEShovel(blaziumShardId, "item_FireShovel",ExtracraftToolMaterial.BLAZIUM)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BlaziumShovel");
		BlaziumHoe = (new ItemEHoe(blaziumHoeId, "item_FireHoe",ExtracraftToolMaterial.BLAZIUM)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BlaziumHoe");
		BlaziumAxe = (new ItemEAxe(blaziumAxeId, "item_FireAxe",ExtracraftToolMaterial.BLAZIUM)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BlaziumAxe");
		
		BlaziumHelmet = (new ItemExtracraftHelmet(blaziumHelmetId,"item_FireHelmet", MaterialBlazium, proxy.addArmor("fire"), 0)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BlaziumHelmet");
		BlaziumChest = (new ItemExtracraftChest(blaziumChestId, "item_FireChestplate",MaterialBlazium, proxy.addArmor("fire"), 1)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BlaziumChest");
		BlaziumPants = (new ItemExtracraftPants(blaziumPantsId, "item_FireLeggings",MaterialBlazium, proxy.addArmor("fire"),2)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BlaziumPants");
		BlaziumBoots = (new ItemExtracraftBoots(blaziumBootsId, "item_FireBoots",MaterialBlazium, proxy.addArmor("fire"),3)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BlaziumBoots");
	
		CopperOre = (new EOBlock(copperOreId, "block_CopperOre", Material.rock)).setHardness(3F).setResistance(5F).setCreativeTab(ExtraOres.tabOresBlocks).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("CopperOre");
		CopperBlock = (new BlockCopper(copperBlockId, Material.iron)).setHardness(5F).setResistance(10F).setCreativeTab(ExtraOres.tabOresBlocks).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("CopperBlock");
		CopperIngot = (new EItem(copperBlockId, "item_CopperIngot")).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("CopperIngot");
		
		CopperBlockTarnished = (new BlockCopperTarnished(copperTarnishedId)).setHardness(6F).setResistance(12F).setCreativeTab(ExtraOres.tabOresBlocks).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("CopperBlockTarnished");
		
		CopperSword = (new ItemESword(copperSwordId, "item_CopperSword",ExtracraftToolMaterial.COPPER)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("CopperSword");
		CopperPickaxe = (new ItemEPickaxe(copperPickaxeId, "item_CopperPickaxe",ExtracraftToolMaterial.COPPER)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("CopperPickaxe");
		CopperShovel = (new ItemEShovel(copperShovelId, "item_CopperShovel",ExtracraftToolMaterial.COPPER)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("CopperShovel");
		CopperHoe = (new ItemEHoe(copperHoeId, "item_CopperHoe",ExtracraftToolMaterial.COPPER)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("CopperHoe");
		CopperAxe = (new ItemEAxe(copperAxeId, "item_CopperAxe",ExtracraftToolMaterial.COPPER)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("CopperAxe");
		
		TinOre = (new EOBlock(tinOreId, "block_TinOre", Material.rock)).setHardness(3F).setResistance(5F).setCreativeTab(ExtraOres.tabOresBlocks).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("TinOre");
		TinBlock = (new EOBlock(tinBlockId, "block_Tin", Material.iron)).setHardness(3F).setResistance(2F).setCreativeTab(ExtraOres.tabOresBlocks).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("TinBlock");
		TinIngot = (new EItem(tinIngotId, "item_TinIngot")).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("TinIngot");

		TinHelmet = (new ItemExtracraftHelmet(tinHelmetId,"item_TinHelmet", MaterialTin, proxy.addArmor("tin"), 0)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("TinHelmet");
		TinChest = (new ItemExtracraftChest(tinChestId, "item_TinChestplate",MaterialTin, proxy.addArmor("tin"), 1)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("TinChest");
		TinPants = (new ItemExtracraftPants(tinPantsId, "item_TinLeggings",MaterialTin, proxy.addArmor("tin"),2)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("TinPants");
		TinBoots = (new ItemExtracraftBoots(tinBootsId, "item_TinBoots",MaterialTin, proxy.addArmor("tin"),3)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("TinBoots");
	
		TinPlate = (new Plate(tinPlateId,"block_Tin")).setHardness(0.7F).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("TinPlate");
		BronzePlate = (new Plate(bronzePlateId, "block_Bronze")).setHardness(0.7F).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("BronzePlate");
		SteelPlate = (new Plate(steelPlateId, "block_SteelSide")).setHardness(0.7F).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("SteelPlate");
		TinPlateItem = (new ItemBlockPlacer(tinPlateItemId,"item_TinPlate", TinPlate)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("TinPlateItem");
		BronzePlateItem = (new ItemBlockPlacer(bronzePlateItemId, "item_BronzePlate", BronzePlate)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BronzePlateItem");
		SteelPlateItem = (new ItemBlockPlacer(steelPlateItemId,"item_SteelPlate", SteelPlate)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("SteelPlateItem");
		
		CoalIronIngot = (new EItem(coalIronIngotId,"item_CoalIronIngot")).setCreativeTab(tabOresItems).setUnlocalizedName("CoalIronIngot");
		SteelIngot = (new EItem(steelIngotId, "item_SteelIngot")).setCreativeTab(tabOresItems).setUnlocalizedName("SteelIngot");
		
		SteelPickaxe = (new ItemEPickaxe(steelPickaxeId, "item_SteelPickaxe",ExtracraftToolMaterial.STEEL)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("SteelPickaxe");
		SteelShovel = (new ItemEShovel(steelShovelId, "item_SteelShovel",ExtracraftToolMaterial.STEEL)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("SteelShovel");
		SteelAxe = (new ItemEAxe(steelAxeId, "item_SteelAxe",ExtracraftToolMaterial.STEEL)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("SteelAxe");
		SteelHoe = (new ItemEHoe(steelHoeId, "item_SteelHoe",ExtracraftToolMaterial.STEEL)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("SteelHoe");
		SteelSword = (new ItemESword(steelSwordId, "item_SteelSword",ExtracraftToolMaterial.STEEL)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("SteelSword");
		
		SteelHelmet = (new ItemExtracraftHelmet(steelHelmetId,"item_SteelHelmet", MaterialSteel, proxy.addArmor("steel"), 0)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("SteelHelmet");
		SteelChest = (new ItemExtracraftChest(steelChestId, "item_SteelChestplate",MaterialSteel, proxy.addArmor("steel"), 1)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("SteelChest");
		SteelPants = (new ItemExtracraftPants(steelPantsId, "item_SteelLeggings",MaterialSteel, proxy.addArmor("steel"), 2)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("SteelPants");
		SteelBoots = (new ItemExtracraftBoots(steelBootsId, "item_SteelBoots",MaterialSteel, proxy.addArmor("steel"), 3)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("SteelBoots");
		
		SteelBlock = (new BlockSteel(steelBlockId)).setHardness(6F).setResistance(10F).setStepSound(Block.soundMetalFootstep).setCreativeTab(ExtraOres.tabOresBlocks).setUnlocalizedName("SteelBlock");
		
		SoulOre = (new EOBlock(soulOreId, "block_SoulOre", Material.sand)).setHardness(2F).setResistance(3F).setCreativeTab(ExtraOres.tabOresBlocks).setStepSound(Block.soundSandFootstep).setUnlocalizedName("SoulOre");
		SoulGem = (new EItemFoiled(soulGemId, "item_SoulGem")).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("SoulGem");
		
		SandstonePickaxe = (new ItemEPickaxe(sandstonePickaxeId,"item_SandstonePickaxe", ExtracraftToolMaterial.SANDSTONE)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("SandstonePickaxe");
		SandstoneShovel = (new ItemEShovel(sandstoneShovelId, "item_SandstoneShovel",ExtracraftToolMaterial.SANDSTONE)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("SandstoneShovel");
		SandstoneAxe = (new ItemEAxe(sandstoneAxeId, "item_SandstoneAxe", ExtracraftToolMaterial.SANDSTONE)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("SandstoneAxe");
		SandstoneHoe = (new ItemEHoe(sandstoneHoeId, "item_SandstoneHoe",ExtracraftToolMaterial.SANDSTONE)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("SandstoneHoe");
		SandstoneSword = (new ItemESword(sandstoneSwordId, "item_SandstoneSword",ExtracraftToolMaterial.SANDSTONE)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("SandstoneSword");
		
		TinPlatedCobble = (new EOBlock(tinPlatedCobbleId, "block_TinCobble", Material.rock)).setHardness(2.0F).setResistance(10.0F).setCreativeTab(ExtraOres.tabOresBlocks).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("TinPlatedCobble");
		TinPlatedMossy = (new EOBlock(tinPlatedMossyId, "block_TinMossy", Material.rock)).setHardness(2.0F).setResistance(10.0F).setCreativeTab(ExtraOres.tabOresBlocks).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("TinPlatedMossy");
		TinPlatedStoneBrick = (new EOBlock(tinPlatedStoneBrickId, "block_TinStoneBrick", Material.rock)).setHardness(1.5F).setResistance(10.0F).setCreativeTab(ExtraOres.tabOresBlocks).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("TinPlatedStoneBrick");
		TinPlatedChiseled = (new EOBlock(tinPlatedChiseledId, "block_TinChiseled", Material.rock)).setHardness(1.5F).setResistance(10.0F).setCreativeTab(ExtraOres.tabOresBlocks).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("TinPlatedChiseled");
		TinPlatedGranite = (new EOBlock(tinPlatedGraniteId,"block_TinGraniteBrick", Material.rock)).setHardness(6F).setResistance(10.0F).setCreativeTab(ExtraOres.tabOresBlocks).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("TinPlatedGranite");
		
		BronzeIngot = (new EItem(bronzeIngotId, "item_BronzeIngot")).setCreativeTab(tabOresItems).setUnlocalizedName("BronzeIngot");
		BronzePickaxe = (new ItemEPickaxe(bronzePickaxeId, "item_BronzePickaxe",ExtracraftToolMaterial.BRONZE)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BronzePickaxe");
		BronzeShovel = (new ItemEShovel(bronzeShovelId, "item_BronzeShovel",ExtracraftToolMaterial.BRONZE)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BronzeShovel");
		BronzeAxe = (new ItemEAxe(bronzeAxeId, "item_BronzeAxe",ExtracraftToolMaterial.BRONZE)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BronzeAxe");
		BronzeHoe = (new ItemEHoe(bronzeHoeId,"item_BronzeHoe", ExtracraftToolMaterial.BRONZE)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BronzeHoe");
		BronzeSword = (new ItemESword(bronzeSwordId,"item_BronzeSword", ExtracraftToolMaterial.BRONZE)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BronzeSword");
		BronzeHelmet = (new ItemExtracraftHelmet(bronzeHelmetId,"item_BronzeHelmet", MaterialBronze, proxy.addArmor("bronze"), 0)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BronzeHelmet");
		BronzeChest = (new ItemExtracraftChest(bronzeChestId, "item_BronzeChestplate",MaterialBronze, proxy.addArmor("bronze"), 1)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BronzeChest");
		BronzePants = (new ItemExtracraftPants(bronzePantsId, "item_BronzeLeggings",MaterialBronze, proxy.addArmor("bronze"),2)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BronzePants");
		BronzeBoots = (new ItemExtracraftBoots(bronzeBootsId, "item_BronzeBoots",MaterialBronze, proxy.addArmor("bronze"),3)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BronzeBoots");
		BronzeBlock = (new EOBlock(bronzeBlockId, "block_Bronze", Material.iron)).setHardness(7F).setResistance(20F).setCreativeTab(ExtraOres.tabOresBlocks).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("BronzeBlock");
		
		BronzePlatedCobble = (new EOBlock(bronzePlatedCobbleId, "block_BronzeCobble", Material.rock)).setHardness(2.0F).setResistance(30.0F).setCreativeTab(ExtraOres.tabOresBlocks).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("BronzePlatedCobble");
		BronzePlatedMossy = (new EOBlock(bronzePlatedMossyId, "block_BronzeMossy", Material.rock)).setHardness(2.0F).setResistance(30.0F).setCreativeTab(ExtraOres.tabOresBlocks).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("BronzePlatedMossy");
		BronzePlatedStoneBrick = (new EOBlock(bronzePlatedStoneBrickId, "block_BronzeStoneBrick", Material.rock)).setHardness(1.5F).setResistance(30.0F).setCreativeTab(ExtraOres.tabOresBlocks).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("BronzePlatedStoneBrick");
		BronzePlatedChiseled = (new EOBlock(bronzePlatedChiseledId, "block_BronzeChiseled", Material.rock)).setHardness(1.5F).setResistance(30.0F).setCreativeTab(ExtraOres.tabOresBlocks).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("BronzePlatedChiseled");
		BronzePlatedGranite = (new EOBlock(bronzePlatedGraniteId, "block_BronzeGraniteBrick", Material.rock)).setHardness(6F).setResistance(30.0F).setCreativeTab(ExtraOres.tabOresBlocks).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("BronzePlatedGranite");
		
		Invincium = (new EOBlock(invinciumId, "block_Invincium", Material.rock)).setBlockUnbreakable().setResistance(12000000.0F).setCreativeTab(ExtraOres.tabOresBlocks).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("Invincium");
		ExtraOresBedrock = (new EOBlock(extraOresBedrockId, "block_NewBedrock", Material.rock)).setHardness(80F).setResistance(24000000.0F).setCreativeTab(ExtraOres.tabOresBlocks).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("ExtraOresBedrock");
		//Replacement block for vanilla Bedrock to make it breakable. Currently modifies net.minecraft.world.gen.ChunkProviderGenerate, net.minecraft.world.gen.ChunkProviderHell, and net.minecraft.world.gen.feature.WorldGenSpikes.
		//TODO: Find a solution to removing Bedrock invincibility that doesn't edit base classes.
		
		SteelPlatedCobble = (new EOBlock(steelPlatedCobbleId, "block_SteelCobble", Material.rock)).setHardness(2.0F).setResistance(20.0F).setCreativeTab(ExtraOres.tabOresBlocks).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("SteelPlatedCobble");
		SteelPlatedMossy = (new EOBlock(steelPlatedMossyId, "block_SteelMossy", Material.rock)).setHardness(2.0F).setResistance(20.0F).setCreativeTab(ExtraOres.tabOresBlocks).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("SteelPlatedMossy");
		SteelPlatedStoneBrick = (new EOBlock(steelPlatedStoneBrickId, "block_SteelStoneBrick", Material.rock)).setHardness(1.5F).setResistance(20.0F).setCreativeTab(ExtraOres.tabOresBlocks).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("SteelPlatedStoneBrick");
		SteelPlatedChiseled = (new EOBlock(steelPlatedChiseledId, "block_SteelChiseled", Material.rock)).setHardness(1.5F).setResistance(20.0F).setCreativeTab(ExtraOres.tabOresBlocks).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("SteelPlatedChiseled");
		SteelPlatedGranite = (new EOBlock(steelPlatedGraniteId, "block_SteelGraniteBrick", Material.rock)).setHardness(6F).setResistance(20.0F).setCreativeTab(ExtraOres.tabOresBlocks).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("SteelPlatedGranite");
		
		BedrockPickaxe = (new ItemEPickaxe(bedrockPickaxeId, "item_BedrockPickaxe", ExtracraftToolMaterial.BEDROCK)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BedrockPickaxe");
		BedrockShovel = (new ItemEShovel(bedrockShovelId, "item_BedrockShovel",ExtracraftToolMaterial.BEDROCK)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BedrockShovel");
		BedrockAxe = (new ItemEAxe(bedrockAxeId, "item_BedrockAxe",ExtracraftToolMaterial.BEDROCK)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BedrockAxe");
		BedrockHoe = (new ItemEHoe(bedrockHoeId, "item_BedrockHoe",ExtracraftToolMaterial.BEDROCK)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BedrockHoe");
		
		GranitePickaxe = (new ItemEPickaxe(granitePickaxeId, "item_GranitePickaxe",ExtracraftToolMaterial.GRANITE)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("GranitePickaxe");
		GraniteShovel = (new ItemEShovel(graniteShovelId, "item_GraniteShovel",ExtracraftToolMaterial.GRANITE)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("GraniteShovel");
		GraniteAxe = (new ItemEAxe(graniteAxeId, "item_GraniteAxe",ExtracraftToolMaterial.GRANITE)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("GraniteAxe");
		GraniteHoe = (new ItemEHoe(graniteHoeId, "item_GraniteHoe",ExtracraftToolMaterial.GRANITE)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("GraniteHoe");
		GraniteSword = (new ItemESword(graniteSwordId, "item_GraniteSword",ExtracraftToolMaterial.GRANITE)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("GraniteSword");
		
		SoulBottle = (new ItemSoulBottle(soulBottleId)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("SoulBottle");
		
		nuke = (new BlockNuke(nukeId)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("nuke");
		grenade = (new ItemGrenade(grenadeId)).setUnlocalizedName("grenade");
		grenadeImpact = (new ItemGrenadeImpact(grenadeImpactId)).setUnlocalizedName("grenadeImpact");
		grenadeSticky = (new ItemGrenadeSticky(grenadeStickyId)).setUnlocalizedName("grenadeSticky");	
	
		
		
		BPZirconiumSword = (new ItemESword(BPZirconiumSwordId, "item_BronzePlatedZirconiumSword", ExtracraftToolMaterial.BPZIRCONIUM)).setUnlocalizedName("BPZirconiumSword");
		BPZirconiumPickaxe = (new ItemEPickaxe(BPZirconiumPickaxeId, "item_BronzePlatedZirconiumPickaxe", ExtracraftToolMaterial.BPZIRCONIUM)).setUnlocalizedName("BPZirconiumPickaxe");
		BPZirconiumShovel = (new ItemEShovel(BPZirconiumShovelId, "item_BronzePlatedZirconiumShovel", ExtracraftToolMaterial.BPZIRCONIUM)).setUnlocalizedName("BPZirconiumShovel");
		BPZirconiumHoe = (new ItemEHoe(BPZirconiumHoeId, "item_BronzePlatedZirconiumHoe", ExtracraftToolMaterial.BPZIRCONIUM)).setUnlocalizedName("BPZirconiumHoe");
		BPZirconiumAxe = (new ItemEAxe(BPZirconiumAxeId, "item_BronzePlatedZirconiumAxe", ExtracraftToolMaterial.BPZIRCONIUM)).setUnlocalizedName("BPZirconiumAxe");
	
		BPZirconiumHelmet = (new ItemExtracraftHelmet(BPZirconiumHelmetId, "item_BronzePlatedZirconiumHelmet", MaterialBPZirconium, proxy.addArmor("BPzirconium"), 0)).setUnlocalizedName("BPZirconiumHelmet");
		BPZirconiumChest = (new ItemExtracraftChest(BPZirconiumChestId, "item_BronzePlatedZirconiumChestplate", MaterialBPZirconium, proxy.addArmor("BPzirconium"), 1)).setUnlocalizedName("BPZirconiumChest");
		BPZirconiumPants = (new ItemExtracraftPants(BPZirconiumPantsId, "item_BronzePlatedZirconiumLeggings",MaterialBPZirconium, proxy.addArmor("BPzirconium"),2)).setUnlocalizedName("BPZirconiumPants");
		BPZirconiumBoots = (new ItemExtracraftBoots(BPZirconiumBootsId, "item_BronzePlatedZirconiumBoots", MaterialBPZirconium, proxy.addArmor("BPzirconium"),3)).setUnlocalizedName("BPZirconiumBoots");
		
		BPTitaniumSword = (new ItemESword(BPZirconiumSwordId,"item_BronzePlatedTitaniumSword", ExtracraftToolMaterial.BPTITANIUM)).setUnlocalizedName("BPTitaniumSword");
		BPTitaniumPickaxe = (new ItemEPickaxe(BPZirconiumPickaxeId, "item_BronzePlatedTitaniumPickaxe", ExtracraftToolMaterial.BPTITANIUM)).setUnlocalizedName("BPTitaniumPickaxe");
		BPTitaniumShovel = (new ItemEShovel(BPTitaniumShovelId, "item_BronzePlatedTitaniumShovel", ExtracraftToolMaterial.BPTITANIUM)).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("BPTitaniumShovel");
		BPTitaniumHoe = (new ItemEHoe(BPTitaniumHoeId, "item_BronzePlatedTitaniumHoe",ExtracraftToolMaterial.BPTITANIUM)).setUnlocalizedName("BPTitaniumHoe");
		BPTitaniumAxe = (new ItemEAxe(BPTitaniumAxeId, "item_BronzePlatedTitaniumAxe",ExtracraftToolMaterial.BPTITANIUM)).setUnlocalizedName("BPTitaniumAxe");
	
		BPTitaniumHelmet = (new ItemExtracraftHelmet(BPTitaniumHelmetId, "item_BronzePlatedTitaniumHelmet", MaterialBPTitanium, proxy.addArmor("BPtitanium"), 0)).setUnlocalizedName("BPTitaniumHelmet");
		BPTitaniumChest = (new ItemExtracraftChest(BPTitaniumChestId, "item_BronzePlatedTitaniumChestplate",MaterialBPTitanium, proxy.addArmor("BPtitanium"), 1)).setUnlocalizedName("BPTitaniumChest");
		BPTitaniumPants = (new ItemExtracraftPants(BPTitaniumPantsId, "item_BronzePlatedTitaniumLeggings", MaterialBPTitanium, proxy.addArmor("BPtitanium"),2)).setUnlocalizedName("BPTitaniumPants");
		BPTitaniumBoots = (new ItemExtracraftBoots(BPTitaniumBootsId, "item_BronzePlatedTitaniumBoots",MaterialBPTitanium, proxy.addArmor("BPtitanium"),3)).setUnlocalizedName("BPTitaniumBoots");
		
		BPToriteSword = (new ItemESword(BPToriteSwordId, "item_BronzePlatedToriteSword", ExtracraftToolMaterial.BPTORITE)).setUnlocalizedName("BPToriteSword");
		BPToritePickaxe = (new ItemEPickaxe(BPToritePickaxeId, "item_BronzePlatedToritePickaxe", ExtracraftToolMaterial.BPTORITE)).setUnlocalizedName("BPToritePickaxe");
		BPToriteShovel = (new ItemEShovel(BPToriteShovelId, "item_BronzePlatedToriteShovel", ExtracraftToolMaterial.BPTORITE)).setUnlocalizedName("BPToriteShovel");
		BPToriteHoe = (new ItemEHoe(BPToriteHoeId, "item_BronzePlatedToriteHoe",ExtracraftToolMaterial.BPTORITE)).setUnlocalizedName("BPToriteHoe");
		BPToriteAxe = (new ItemEAxe(BPToriteAxeId, "item_BronzePlatedToriteAxe",ExtracraftToolMaterial.BPTORITE)).setUnlocalizedName("BPToriteAxe");
		
		BPToriteHelmet = (new ItemExtracraftHelmet(BPToriteHelmetId, "item_BronzePlatedToriteHelmet",MaterialBPTorite, proxy.addArmor("BPtorite"), 0)).setUnlocalizedName("BPToriteHelmet");
		BPToriteChest = (new ItemExtracraftChest(BPToriteChestId, "item_BronzePlatedToriteChestplate",MaterialBPTorite, proxy.addArmor("BPtorite"), 1)).setUnlocalizedName("BPToriteChest");
		BPToritePants = (new ItemExtracraftPants(BPToritePantsId, "item_BronzePlatedToriteLeggings",MaterialBPTorite, proxy.addArmor("BPtorite"),2)).setUnlocalizedName("BPToritePants");
		BPToriteBoots = (new ItemExtracraftBoots(BPToriteBootsId, "item_BronzePlatedToriteBoots",MaterialBPTorite, proxy.addArmor("BPtorite"),3)).setUnlocalizedName("BPToriteBoots");
		
		BPBlaziumSword = (new ItemESword(BPBlaziumSwordId, "item_BronzePlatedFireSword", ExtracraftToolMaterial.BPBLAZIUM)).setUnlocalizedName("BPBlaziumSword");
		BPBlaziumPickaxe = (new ItemEPickaxe(BPBlaziumPickaxeId, "item_BronzePlatedFirePickaxe", ExtracraftToolMaterial.BPBLAZIUM)).setUnlocalizedName("BPBlaziumPickaxe");
		BPBlaziumShovel = (new ItemEShovel(BPBlaziumShovelId, "item_BronzePlatedFireShovel",ExtracraftToolMaterial.BPBLAZIUM)).setUnlocalizedName("BPBlaziumShovel");
		BPBlaziumHoe = (new ItemEHoe(BPBlaziumHoeId, "item_BronzePlatedFireHoe",ExtracraftToolMaterial.BPBLAZIUM)).setUnlocalizedName("BPBlaziumHoe");
		BPBlaziumAxe = (new ItemEAxe(BPBlaziumAxeId, "item_BronzePlatedFireAxe",ExtracraftToolMaterial.BPBLAZIUM)).setUnlocalizedName("BPBlaziumAxe");
		
		BPBlaziumHelmet = (new ItemExtracraftHelmet(BPBlaziumHelmetId,"item_BronzePlatedFireHelmet", MaterialBPBlazium, proxy.addArmor("BPfire"), 0)).setUnlocalizedName("BPBlaziumHelmet");
		BPBlaziumChest = (new ItemExtracraftChest(BPBlaziumChestId, "item_BronzePlatedFireChestplate",MaterialBPBlazium, proxy.addArmor("BPfire"), 1)).setUnlocalizedName("BPBlaziumChest");
		BPBlaziumPants = (new ItemExtracraftPants(BPBlaziumPantsId, "item_BronzePlatedFireLeggings",MaterialBPBlazium, proxy.addArmor("BPfire"),2)).setUnlocalizedName("BPBlaziumPants");
		BPBlaziumBoots = (new ItemExtracraftBoots(BPBlaziumBootsId, "item_BronzePlatedFireBoots",MaterialBPBlazium, proxy.addArmor("BPfire"),3)).setUnlocalizedName("BPBlaziumBoots");
		
		BPCopperSword = (new ItemESword(BPCopperSwordId, "item_BronzePlatedCopperSword",ExtracraftToolMaterial.BPCOPPER)).setUnlocalizedName("BPCopperSword");
		BPCopperPickaxe = (new ItemEPickaxe(BPCopperPickaxeId, "item_BronzePlatedCopperPickaxe",ExtracraftToolMaterial.BPCOPPER)).setUnlocalizedName("BPCopperPickaxe");
		BPCopperShovel = (new ItemEShovel(BPCopperShovelId, "item_BronzePlatedCopperShovel",ExtracraftToolMaterial.BPCOPPER)).setUnlocalizedName("BPCopperShovel");
		BPCopperHoe = (new ItemEHoe(BPCopperHoeId, "item_BronzePlatedCopperHoe",ExtracraftToolMaterial.BPCOPPER)).setUnlocalizedName("BPCopperHoe");
		BPCopperAxe = (new ItemEAxe(BPCopperAxeId, "item_BronzePlatedCopperAxe",ExtracraftToolMaterial.BPCOPPER)).setUnlocalizedName("BPCopperAxe");
		
		BPTinHelmet = (new ItemExtracraftHelmet(BPTinHelmetId,"item_BronzePlatedTinHelmet", MaterialBPTin, proxy.addArmor("BPtin"), 0)).setUnlocalizedName("BPTinHelmet");
		BPTinChest = (new ItemExtracraftChest(BPTinChestId, "item_BronzePlatedTinChestplate",MaterialBPTin, proxy.addArmor("BPtin"), 1)).setUnlocalizedName("BPTinChest");
		BPTinPants = (new ItemExtracraftPants(BPTinPantsId, "item_BronzePlatedTinLeggings",MaterialBPTin, proxy.addArmor("BPtin"),2)).setUnlocalizedName("BPTinPants");
		BPTinBoots = (new ItemExtracraftBoots(BPTinBootsId, "item_BronzePlatedTinBoots",MaterialBPTin, proxy.addArmor("BPtin"),3)).setUnlocalizedName("BPTinBoots");
		
		BPSteelPickaxe = (new ItemEPickaxe(BPSteelPickaxeId, "item_BronzePlatedSteelPickaxe",ExtracraftToolMaterial.BPSTEEL)).setUnlocalizedName("BPSteelPickaxe");
		BPSteelShovel = (new ItemEShovel(BPSteelShovelId, "item_BronzePlatedSteelShovel",ExtracraftToolMaterial.BPSTEEL)).setUnlocalizedName("BPSteelShovel");
		BPSteelAxe = (new ItemEAxe(BPSteelAxeId, "item_BronzePlatedSteelAxe",ExtracraftToolMaterial.BPSTEEL)).setUnlocalizedName("BPSteelAxe");
		BPSteelHoe = (new ItemEHoe(BPSteelHoeId, "item_BronzePlatedSteelHoe",ExtracraftToolMaterial.BPSTEEL)).setUnlocalizedName("BPSteelHoe");
		BPSteelSword = (new ItemESword(BPSteelSwordId, "item_BronzePlatedSteelSword",ExtracraftToolMaterial.BPSTEEL)).setUnlocalizedName("BPSteelSword");
		
		BPSteelHelmet = (new ItemExtracraftHelmet(BPSteelHelmetId,"item_BronzePlatedSteelHelmet", MaterialBPSteel, proxy.addArmor("BPsteel"), 0)).setUnlocalizedName("BPSteelHelmet");
		BPSteelChest = (new ItemExtracraftChest(BPSteelChestId, "item_BronzePlatedSteelChestplate",MaterialBPSteel, proxy.addArmor("BPsteel"), 1)).setUnlocalizedName("BPSteelChest");
		BPSteelPants = (new ItemExtracraftPants(BPSteelPantsId, "item_BronzePlatedSteelLeggings",MaterialBPSteel, proxy.addArmor("BPsteel"), 2)).setUnlocalizedName("BPSteelPants");
		BPSteelBoots = (new ItemExtracraftBoots(BPSteelBootsId, "item_BronzePlatedSteelBoots",MaterialBPSteel, proxy.addArmor("BPsteel"), 3)).setUnlocalizedName("BPSteelBoots");
		
		BPSandstonePickaxe = (new ItemEPickaxe(BPSandstonePickaxeId,"item_BronzePlatedSandstonePickaxe", ExtracraftToolMaterial.BPSANDSTONE)).setUnlocalizedName("BPSandstonePickaxe");
		BPSandstoneShovel = (new ItemEShovel(BPSandstoneShovelId, "item_BronzePlatedSandstoneShovel",ExtracraftToolMaterial.BPSANDSTONE)).setUnlocalizedName("BPSandstoneShovel");
		BPSandstoneAxe = (new ItemEAxe(BPSandstoneAxeId, "item_BronzePlatedSandstoneAxe", ExtracraftToolMaterial.BPSANDSTONE)).setUnlocalizedName("BPSandstoneAxe");
		BPSandstoneHoe = (new ItemEHoe(BPSandstoneHoeId, "item_BronzePlatedSandstoneHoe",ExtracraftToolMaterial.BPSANDSTONE)).setUnlocalizedName("BPSandstoneHoe");
		BPSandstoneSword = (new ItemESword(BPSandstoneSwordId, "item_BronzePlatedSandstoneSword",ExtracraftToolMaterial.BPSANDSTONE)).setUnlocalizedName("BPSandstoneSword");
		
		BPBedrockPickaxe = (new ItemEPickaxe(BPBedrockPickaxeId, "item_BronzePlatedBedrockPickaxe", ExtracraftToolMaterial.BPBEDROCK)).setUnlocalizedName("BPBedrockPickaxe");
		BPBedrockShovel = (new ItemEShovel(BPBedrockShovelId, "item_BronzePlatedBedrockShovel",ExtracraftToolMaterial.BPBEDROCK)).setUnlocalizedName("BPBedrockShovel");
		BPBedrockAxe = (new ItemEAxe(BPBedrockAxeId, "item_BronzePlatedBedrockAxe",ExtracraftToolMaterial.BPBEDROCK)).setUnlocalizedName("BPBedrockAxe");
		BPBedrockHoe = (new ItemEHoe(BPBedrockHoeId, "item_BronzePlatedBedrockHoe",ExtracraftToolMaterial.BPBEDROCK)).setUnlocalizedName("BPBedrockHoe");
		
		BPGranitePickaxe = (new ItemEPickaxe(BPGranitePickaxeId, "item_BronzePlatedGranitePickaxe",ExtracraftToolMaterial.BPGRANITE)).setUnlocalizedName("BPGranitePickaxe");
		BPGraniteShovel = (new ItemEShovel(BPGraniteShovelId, "item_BronzePlatedGraniteShovel",ExtracraftToolMaterial.BPGRANITE)).setUnlocalizedName("BPGraniteShovel");
		BPGraniteAxe = (new ItemEAxe(BPGraniteAxeId, "item_BronzePlatedGraniteAxe",ExtracraftToolMaterial.BPGRANITE)).setUnlocalizedName("BPGraniteAxe");
		BPGraniteHoe = (new ItemEHoe(BPGraniteHoeId, "item_BronzePlatedGraniteHoe",ExtracraftToolMaterial.BPGRANITE)).setUnlocalizedName("BPGraniteHoe");
		BPGraniteSword = (new ItemESword(BPGraniteSwordId, "item_BronzePlatedGraniteSword",ExtracraftToolMaterial.BPGRANITE)).setUnlocalizedName("BPGraniteSword");
		
		BPWoodPickaxe = (new ItemEPickaxe(BPWoodPickaxeId, "item_BronzePlatedWoodPickaxe",ExtracraftToolMaterial.BPWOOD)).setUnlocalizedName("BPWoodPickaxe");
		BPWoodShovel = (new ItemEShovel(BPWoodShovelId, "item_BronzePlatedWoodShovel",ExtracraftToolMaterial.BPWOOD)).setUnlocalizedName("BPWoodShovel");
		BPWoodAxe = (new ItemEAxe(BPWoodAxeId, "item_BronzePlatedWoodAxe",ExtracraftToolMaterial.BPWOOD)).setUnlocalizedName("BPWoodAxe");
		BPWoodHoe = (new ItemEHoe(BPWoodHoeId, "item_BronzePlatedWoodHoe",ExtracraftToolMaterial.BPWOOD)).setUnlocalizedName("BPWoodHoe");
		BPWoodSword = (new ItemESword(BPWoodSwordId, "item_BronzePlatedWoodSword",ExtracraftToolMaterial.BPWOOD)).setUnlocalizedName("BPWoodSword");
		
		BPClothHelmet = (new ItemExtracraftHelmet(BPClothHelmetId,"item_BronzePlatedLeatherHelmet", MaterialBPCloth, proxy.addArmor("BPcloth"), 0)).setUnlocalizedName("BPClothHelmet");
		BPClothChest = (new ItemExtracraftChest(BPClothChestId, "item_BronzePlatedLeatherChestplate",MaterialBPCloth, proxy.addArmor("BPcloth"), 1)).setUnlocalizedName("BPClothChest");
		BPClothPants = (new ItemExtracraftPants(BPClothPantsId, "item_BronzePlatedLeatherLeggings",MaterialBPCloth, proxy.addArmor("BPcloth"), 2)).setUnlocalizedName("BPClothPants");
		BPClothBoots = (new ItemExtracraftBoots(BPClothBootsId, "item_BronzePlatedLeatherBoots",MaterialBPCloth, proxy.addArmor("BPcloth"), 3)).setUnlocalizedName("BPClothBoots");
		
		BPStonePickaxe = (new ItemEPickaxe(BPStonePickaxeId, "item_BronzePlatedStonePickaxe",ExtracraftToolMaterial.BPSTONE)).setUnlocalizedName("BPStonePickaxe");
		BPStoneShovel = (new ItemEShovel(BPStoneShovelId, "item_BronzePlatedStoneShovel",ExtracraftToolMaterial.BPSTONE)).setUnlocalizedName("BPStoneShovel");
		BPStoneAxe = (new ItemEAxe(BPStoneAxeId, "item_BronzePlatedStoneAxe",ExtracraftToolMaterial.BPSTONE)).setUnlocalizedName("BPStoneAxe");
		BPStoneHoe = (new ItemEHoe(BPStoneHoeId, "item_BronzePlatedStoneHoe",ExtracraftToolMaterial.BPSTONE)).setUnlocalizedName("BPStoneHoe");
		BPStoneSword = (new ItemESword(BPStoneSwordId, "item_BronzePlatedStoneSword",ExtracraftToolMaterial.BPSTONE)).setUnlocalizedName("BPStoneSword");
		
		BPChainHelmet = (new ItemExtracraftHelmet(BPChainHelmetId,"item_BronzePlatedChainHelmet", MaterialBPChain, proxy.addArmor("BPchain"), 0)).setUnlocalizedName("BPChainHelmet");
		BPChainChest = (new ItemExtracraftChest(BPChainChestId, "item_BronzePlatedChainChestplate",MaterialBPChain, proxy.addArmor("BPchain"), 1)).setUnlocalizedName("BPChainChest");
		BPChainPants = (new ItemExtracraftPants(BPChainPantsId, "item_BronzePlatedChainLeggings",MaterialBPChain, proxy.addArmor("BPchain"), 2)).setUnlocalizedName("BPChainPants");
		BPChainBoots = (new ItemExtracraftBoots(BPChainBootsId, "item_BronzePlatedChainBoots",MaterialBPChain, proxy.addArmor("BPchain"), 3)).setUnlocalizedName("BPChainBoots");
		
		BPIronPickaxe = (new ItemEPickaxe(BPIronPickaxeId, "item_BronzePlatedIronPickaxe",ExtracraftToolMaterial.BPIRON)).setUnlocalizedName("BPIronPickaxe");
		BPIronShovel = (new ItemEShovel(BPIronShovelId, "item_BronzePlatedIronShovel",ExtracraftToolMaterial.BPIRON)).setUnlocalizedName("BPIronShovel");
		BPIronAxe = (new ItemEAxe(BPIronAxeId, "item_BronzePlatedIronAxe",ExtracraftToolMaterial.BPIRON)).setUnlocalizedName("BPIronAxe");
		BPIronHoe = (new ItemEHoe(BPIronHoeId, "item_BronzePlatedIronHoe",ExtracraftToolMaterial.BPIRON)).setUnlocalizedName("BPIronHoe");
		BPIronSword = (new ItemESword(BPIronSwordId, "item_BronzePlatedIronSword",ExtracraftToolMaterial.BPIRON)).setUnlocalizedName("BPStoneSword");
		
		BPIronHelmet = (new ItemExtracraftHelmet(BPIronHelmetId,"item_BronzePlatedIronHelmet", MaterialBPIron, proxy.addArmor("BPiron"), 0)).setUnlocalizedName("BPIronHelmet");
		BPIronChest = (new ItemExtracraftChest(BPIronChestId, "item_BronzePlatedIronChestplate",MaterialBPIron, proxy.addArmor("BPiron"), 1)).setUnlocalizedName("BPIronChest");
		BPIronPants = (new ItemExtracraftPants(BPIronPantsId, "item_BronzePlatedIronLeggings",MaterialBPIron, proxy.addArmor("BPiron"), 2)).setUnlocalizedName("BPIronPants");
		BPIronBoots = (new ItemExtracraftBoots(BPIronBootsId, "item_BronzePlatedIronBoots",MaterialBPIron, proxy.addArmor("BPiron"), 3)).setUnlocalizedName("BPIronBoots");
		
		BPGoldPickaxe = (new ItemEPickaxe(BPGoldPickaxeId, "item_BronzePlatedGoldPickaxe",ExtracraftToolMaterial.BPGOLD)).setUnlocalizedName("BPGoldPickaxe");
		BPGoldShovel = (new ItemEShovel(BPGoldShovelId, "item_BronzePlatedGoldShovel",ExtracraftToolMaterial.BPGOLD)).setUnlocalizedName("BPGoldShovel");
		BPGoldAxe = (new ItemEAxe(BPGoldAxeId, "item_BronzePlatedGoldAxe",ExtracraftToolMaterial.BPGOLD)).setUnlocalizedName("BPGoldAxe");
		BPGoldHoe = (new ItemEHoe(BPGoldHoeId, "item_BronzePlatedGoldHoe",ExtracraftToolMaterial.BPGOLD)).setUnlocalizedName("BPGoldHoe");
		BPGoldSword = (new ItemESword(BPGoldSwordId, "item_BronzePlatedGoldSword",ExtracraftToolMaterial.BPGOLD)).setUnlocalizedName("BPGoldSword");
		
		BPGoldHelmet = (new ItemExtracraftHelmet(BPGoldHelmetId,"item_BronzePlatedGoldHelmet", MaterialBPGold, proxy.addArmor("BPgold"), 0)).setUnlocalizedName("BPGoldHelmet");
		BPGoldChest = (new ItemExtracraftChest(BPGoldChestId, "item_BronzePlatedGoldChestplate",MaterialBPGold, proxy.addArmor("BPgold"), 1)).setUnlocalizedName("BPGoldChest");
		BPGoldPants = (new ItemExtracraftPants(BPGoldPantsId, "item_BronzePlatedGoldLeggings",MaterialBPGold, proxy.addArmor("BPgold"), 2)).setUnlocalizedName("BPGoldPants");
		BPGoldBoots = (new ItemExtracraftBoots(BPGoldBootsId, "item_BronzePlatedGoldBoots",MaterialBPGold, proxy.addArmor("BPgold"), 3)).setUnlocalizedName("BPGoldBoots");
		
		BPDiamondPickaxe = (new ItemEPickaxe(BPDiamondPickaxeId, "item_BronzePlatedDiamondPickaxe",ExtracraftToolMaterial.BPEMERALD)).setUnlocalizedName("BPDiamondPickaxe");
		BPDiamondShovel = (new ItemEShovel(BPDiamondShovelId, "item_BronzePlatedDiamondShovel",ExtracraftToolMaterial.BPEMERALD)).setUnlocalizedName("BPDiamondShovel");
		BPDiamondAxe = (new ItemEAxe(BPDiamondAxeId, "item_BronzePlatedDiamondAxe",ExtracraftToolMaterial.BPEMERALD)).setUnlocalizedName("BPDiamondAxe");
		BPDiamondHoe = (new ItemEHoe(BPDiamondHoeId, "item_BronzePlatedDiamondHoe",ExtracraftToolMaterial.BPEMERALD)).setUnlocalizedName("BPDiamondHoe");
		BPDiamondSword = (new ItemESword(BPDiamondSwordId, "item_BronzePlatedDiamondSword",ExtracraftToolMaterial.BPEMERALD)).setUnlocalizedName("BPDiamondSword");
		
		BPDiamondHelmet = (new ItemExtracraftHelmet(BPDiamondHelmetId,"item_BronzePlatedDiamondHelmet", MaterialBPDiamond, proxy.addArmor("BPdiamond"), 0)).setUnlocalizedName("BPDiamondHelmet");
		BPDiamondChest = (new ItemExtracraftChest(BPDiamondChestId, "item_BronzePlatedDiamondChestplate",MaterialBPDiamond, proxy.addArmor("BPdiamond"), 1)).setUnlocalizedName("BPDiamondChest");
		BPDiamondPants = (new ItemExtracraftPants(BPDiamondPantsId, "item_BronzePlatedDiamondLeggings",MaterialBPDiamond, proxy.addArmor("BPdiamond"), 2)).setUnlocalizedName("BPDiamondPants");
		BPDiamondBoots = (new ItemExtracraftBoots(BPDiamondBootsId, "item_BronzePlatedDiamondBoots",MaterialBPDiamond, proxy.addArmor("BPdiamond"), 3)).setUnlocalizedName("BPDiamondBoots");
		
		
	
		
		
		
		SmoothRadiantQuartz = (new EOBlock(smoothRadiantQuartzId, "block_SmoothRadiantQuartz", Material.rock)).setHardness(6F).setResistance(10F).setStepSound(Block.soundMetalFootstep).setCreativeTab(ExtraOres.tabOresBlocks).setLightValue(0.5F).setUnlocalizedName("SmoothRadiantQuartz");
		ChiseledRadiantQuartz = (new EOBlock(chiseledRadiantQuartzId, "block_ChiseledRadiantQuartz", Material.rock)).setHardness(6F).setResistance(10F).setStepSound(Block.soundMetalFootstep).setCreativeTab(ExtraOres.tabOresBlocks).setLightValue(0.5F).setUnlocalizedName("ChiseledRadiantQuartz");
		PillarRadiantQuartz = (new BlockRadiantPillar(pillarRadiantQuartzId)).setHardness(6F).setResistance(10F).setStepSound(Block.soundMetalFootstep).setCreativeTab(ExtraOres.tabOresBlocks).setLightValue(0.5F).setUnlocalizedName("PillarRadiantQuartz");
		RadiantQuartzOre = (new EOBlock(radiantQuartzOreId, "block_RadiantQuartzOre", Material.rock)).setHardness(5.5F).setResistance(10F).setStepSound(Block.soundMetalFootstep).setCreativeTab(ExtraOres.tabOresBlocks).setLightValue(0.4F).setUnlocalizedName("RadiantQuartzOre");
		
		RadiantQuartz = (new EItemFoiled(radiantQuartzId, "item_RadiantQuartz")).setCreativeTab(ExtraOres.tabOresItems).setUnlocalizedName("RadiantQuartz");
		
		Godstone = (new BlockGodstone(godstoneId, Material.rock)).setHardness(6F).setResistance(9F).setCreativeTab(tabOresBlocks).setStepSound(Block.soundMetalFootstep).setLightValue(1F).setUnlocalizedName("Godstone");
		
		melterIdle = (new BlockMelter(melterIdleId, false)).setHardness(6F).setResistance(8.0F).setCreativeTab(ExtraOres.tabOresBlocks).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("melter");
		melterBurning = (new BlockMelter(melterBurningId, true)).setHardness(6F).setResistance(8.0F).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("melter");
	
		RadiantQuartzStairs = new EOBlockStairs(radiantQuartzStairsId, SmoothRadiantQuartz, 0).setUnlocalizedName("RadiantQuartzStairs");
		RadiantQuartzSingleSlab = (BlockHalfSlab) new RadiantQuartzSlab(radiantQuartzSingleSlabId, false).setUnlocalizedName("RadiantQuartzSingleSlab").setCreativeTab(tabOresBlocks);
		RadiantQuartzDoubleSlab = (BlockHalfSlab) new RadiantQuartzSlab(radiantQuartzDoubleSlabId, true).setUnlocalizedName("RadiantQuartzDoubleSlab");
	
		PinkQuartzStairs = new EOBlockStairs(pinkQuartzStairsId, SmoothQuartzite, 0).setUnlocalizedName("PinkQuartzStairs");
		PinkQuartzSingleSlab = (BlockHalfSlab) new PinkQuartzSlab(pinkQuartzSingleSlabId, false).setUnlocalizedName("PinkQuartzSingleSlab").setCreativeTab(tabOresBlocks);
		PinkQuartzDoubleSlab = (BlockHalfSlab) new PinkQuartzSlab(pinkQuartzDoubleSlabId, true).setUnlocalizedName("PinkQuartzDoubleSlab");
	}
	
	@Init
    public void loadNew(FMLInitializationEvent event)

	{

        TickRegistry.registerTickHandler(new ClientTickHandler(EnumSet.of(TickType.CLIENT)), Side.CLIENT);
        TickRegistry.registerTickHandler(new ServerTickHandler(EnumSet.of(TickType.PLAYER)), Side.SERVER);
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
		//blocks 3000
		//items 1000;
		Configuration config = new Configuration(evt.getSuggestedConfigurationFile());
	    config.load();
	    
	    zirconiumOreId = config.getBlock("Zirconium Ore", 3000).getInt();
	    zirconiumBlockId = config.getBlock("Zirconium Block", 3001).getInt();
	    zirconiumIngotId = config.getItem("Zirconium Ingot", 1000).getInt();
	    
	    config.save();
	    
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
		Item.itemsList[this.RadiantQuartzSingleSlab.blockID] = (new ItemSlab(this.RadiantQuartzSingleSlab.blockID - 256, (BlockHalfSlab)RadiantQuartzSingleSlab, (BlockHalfSlab)RadiantQuartzDoubleSlab, false));
		Item.itemsList[this.PinkQuartzSingleSlab.blockID] = (new ItemSlab(this.PinkQuartzSingleSlab.blockID - 256, (BlockHalfSlab)PinkQuartzSingleSlab, (BlockHalfSlab)PinkQuartzDoubleSlab, false));
    }

}
