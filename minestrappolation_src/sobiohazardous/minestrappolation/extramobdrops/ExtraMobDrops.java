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
	friedFleshId;
	
	public static Item snout;
	public static Item pigHoof;
	public static Item fat;
	public static Item grease;
	
	public Item friedApple;
	public Item friedBeef;
	public Item friedBread;
	public Item friedCarrot;
	public Item friedCookie;
	public Item friedFlesh;
	
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
		
		config.save();
		
		snout = new EMDItem(snoutId, "snout").setUnlocalizedName("snout");
		pigHoof = new EMDItem(pigHoofId, "pig_foot").setUnlocalizedName("pigHoof");
		fat = new EMDItem(fatId, "fat").setUnlocalizedName("fat");
		grease = new EMDItem(greaseId, "grease").setUnlocalizedName("grease");
		
		friedApple = new EMDItemFood(friedAppleId, 8, 0.3F, "grease_apple").setPotionEffect(Potion.hunger.id, 10 * 20, 1, 1.0F).setUnlocalizedName("friedApple");
		friedBread = new EMDItemFood(friedBreadId, 10, 0.6F, "grease_bread").setPotionEffect(Potion.hunger.id, 10 * 20, 1, 1.0F).setUnlocalizedName("friedBread");
		
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
