package sobiohazardous.minestrappolation.extramobdrops;

import java.util.EnumSet;

import sobiohazardous.minestrappolation.extramobdrops.handler.ClientPacketHandler;
import sobiohazardous.minestrappolation.extramobdrops.handler.EMDEventHandler;
import sobiohazardous.minestrappolation.extramobdrops.handler.OverallTickHandler;
import sobiohazardous.minestrappolation.extramobdrops.handler.ServerPacketHandler;
import sobiohazardous.minestrappolation.extramobdrops.proxy.CommonProxy;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkMod.SidedPacketHandler;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;
import sobiohazardous.minestrappolation.extramobdrops.item.*;
import sobiohazardous.minestrappolation.extramobdrops.lib.EMDNameManager;
import sobiohazardous.minestrappolation.extramobdrops.lib.EMDRecipeManager;

/**
 * 
 * 
 * 
 * @author Crzyguitardude
 */
@NetworkMod(clientSideRequired = true, serverSideRequired = false,
clientPacketHandlerSpec = @SidedPacketHandler(channels = {"extraoresChan"}, packetHandler = ClientPacketHandler.class),
serverPacketHandlerSpec = @SidedPacketHandler(channels = {"extroresChan"}, packetHandler = ServerPacketHandler.class))
@Mod ( modid = "ExtraMobDrops", name="Extrappolated Mob Drops", version="1.0B")
public class ExtraMobDrops 
{

	@SidedProxy(clientSide = "sobiohazardous.minestrappolation.extramobdrops.proxy.ClientProxy", serverSide = "sobiohazardous.minestrappolation.extramobdrops.proxy.CommonProxy")
    public static CommonProxy proxy;
	public static CreativeTabs tabItems = new CreativeTabExtraMobDropsItems(CreativeTabs.getNextID(), "Extrappolated Mob Drops - Items");
	public static CreativeTabs tabBlocks = new CreativeTabExtraMobDropsBlocks(CreativeTabs.getNextID(), "Extrappolated Mob Drops - Blocks");
	@Instance("ExtraMobDrops")
	public static ExtraMobDrops instance;
	
	public static int snoutId,
	pigHoofId,
	fatId,
	greaseId,
	friedAppleId,
	friedBeefId,
	friedBreadId,
	friedCarrotId,
	friedCookieId,
	friedFleshId,
	animalBonesId,
	cowHoofId,
	hornId,
	hornSwordWoodId,
	hornSwordStoneId,
	hornSwordIronId,
	hornSwordDiamondId;
	
	public static Item snout;
	public static Item pigHoof;
	public static Item fat;
	public static Item grease;
	
	public static Item friedApple;
	public static Item friedBeef;
	public static Item friedBread;
	public static Item friedCarrot;
	public static Item friedCookie;
	public static Item friedFlesh;
	
	public static Item animalBones;
	public static Item cowHoof;
	public static Item horn;
	
	public static Item hornSwordWood;
	public static Item hornSwordStone;
	public static Item hornSwordIron;
	public static Item hornSwordDiamond;
	
	@Mod.EventHandler
	public void preLoad(FMLPreInitializationEvent e)
	{
		Configuration config = new Configuration(e.getSuggestedConfigurationFile());
		config.load();
		//items 400
		snoutId = config.getItem("Snout", 4000).getInt();
		pigHoofId = config.getItem("Pig Hoof", 4001).getInt();
		fatId = config.getItem("Fat", 4002).getInt();
		greaseId = config.getItem("Grease", 4003).getInt();
		friedAppleId = config.getItem("Fried Apple", 4004).getInt();
		friedBeefId = config.getItem("Fried Beef", 4005).getInt();
		friedBreadId = config.getItem("Fried Bread", 4006).getInt();
		friedCarrotId = config.getItem("Fried Carrot", 4007).getInt();
		friedCookieId = config.getItem("Fried Cookie", 4008).getInt();
		friedFleshId = config.getItem("Fried Flesh", 4009).getInt();
		animalBonesId = config.getItem("Animal Bones", 4010).getInt();
		cowHoofId = config.getItem("Cow Hoof", 4011).getInt();
		hornId = config.getItem("Horn", 4012).getInt();
		hornSwordWoodId = config.getItem("Horned Wood Sword", 4013).getInt();
		hornSwordStoneId = config.getItem("Horned Stone Sword", 4014).getInt();
		hornSwordIronId = config.getItem("Horned Iron Sword", 4015).getInt();
		hornSwordDiamondId = config.getItem("Horned Diamond Sword", 4016).getInt();

		
		config.save();
		
		snout = new EMDItemFood(snoutId, 3, 0.2F, "snout").setPotionEffect(Potion.hunger.id, 10 * 20, 1, 0.25F).setUnlocalizedName("snout");
		pigHoof = new EMDItem(pigHoofId, "pig_foot").setUnlocalizedName("pigHoof");
		fat = new EMDItemFood(fatId, 8, 0.2F, "fat").setPotionEffect(Potion.hunger.id, 25 * 20, 1, 1.0F).setUnlocalizedName("fat");
		grease = new EMDItem(greaseId, "grease").setUnlocalizedName("grease");
		
		friedApple = new EMDItemFood(friedAppleId, 8, 0.3F, "grease_apple").setPotionEffect(Potion.hunger.id, 15 * 20, 1, 0.4F).setUnlocalizedName("friedApple");
		friedBeef = new EMDItemFood(friedBeefId, 16, 0.8F, "grease_beef").setPotionEffect(Potion.hunger.id, 15 * 20, 1, 0.4F).setUnlocalizedName("friedBeef");
		friedBread = new EMDItemFood(friedBreadId, 10, 0.6F, "grease_bread").setPotionEffect(Potion.hunger.id, 15 * 20, 1, 0.4F).setUnlocalizedName("friedBread");
		friedCarrot = new EMDItemFood(friedCarrotId, 8, 0.6F, "grease_carrot").setPotionEffect(Potion.hunger.id, 15 * 20, 1, 0.4F).setUnlocalizedName("friedCarrot");
		friedCookie = new EMDItemFood(friedCookieId, 8, 0.6F, "grease_cookie").setPotionEffect(Potion.hunger.id, 15 * 20, 1, 0.4F).setUnlocalizedName("friedCookie");
		friedFlesh = new EMDItemFood(friedFleshId, 8, 0.6F, "grease_flesh").setPotionEffect(Potion.hunger.id, 15 * 20, 1, 0.4F).setUnlocalizedName("friedFlesh");
		
		animalBones = new EMDItem(animalBonesId, "animal_bones").setUnlocalizedName("animalBones");
		cowHoof = new EMDItem(cowHoofId, "cow_hoof").setUnlocalizedName("cowHoof");
		
		horn = new EMDItem(hornId, "horn").setUnlocalizedName("horn");
		
		hornSwordWood = new ItemHornSword(hornSwordWoodId, "horned_wood_sword", EnumHornSwordMaterial.WOODH).setUnlocalizedName("hornedSwordWood");
		hornSwordStone = new ItemHornSword(hornSwordStoneId, "horned_stone_sword", EnumHornSwordMaterial.STONEH).setUnlocalizedName("hornedSwordStone");
		hornSwordIron = new ItemHornSword(hornSwordIronId, "horned_iron_sword", EnumHornSwordMaterial.IRONH).setUnlocalizedName("hornedSwordIron");
		hornSwordDiamond = new ItemHornSword(hornSwordDiamondId, "horned_diamond_sword", EnumHornSwordMaterial.EMERALDH).setUnlocalizedName("hornedSwordDiamond");

		EMDNameManager.loadNames();
		EMDRecipeManager.loadRecipes();
	}
	
	@Mod.EventHandler
	public void load(FMLInitializationEvent e)
	{
		proxy.registerRenderThings();
		TickRegistry.registerTickHandler(new OverallTickHandler(EnumSet.of(TickType.PLAYER)), Side.SERVER);
		MinecraftForge.EVENT_BUS.register(new EMDEventHandler());
	}

	@Mod.EventHandler
	public void postLoad(FMLPostInitializationEvent e)
	{
		
	}
}
