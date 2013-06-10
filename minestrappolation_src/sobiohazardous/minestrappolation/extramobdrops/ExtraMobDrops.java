package sobiohazardous.minestrappolation.extramobdrops;

import java.util.EnumSet;

import sobiohazardous.minestrappolation.creativetabs.CreativeTabExtraMobDropsBlocks;
import sobiohazardous.minestrappolation.creativetabs.CreativeTabExtraMobDropsItems;
import sobiohazardous.minestrappolation.extramobdrops.handler.ClientPacketHandler;
import sobiohazardous.minestrappolation.extramobdrops.handler.OverallTickHandler;
import sobiohazardous.minestrappolation.extramobdrops.handler.ServerPacketHandler;
import sobiohazardous.minestrappolation.extramobdrops.proxy.CommonProxy;
import sobiohazardous.potionapi.PAPotionEffect;
import sobiohazardous.potionapi.PAItemPotion;
import sobiohazardous.potionapi.PotionAPI;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
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

/**
 * 
 * 
 * 
 * @author Crzyguitardude
 */
@NetworkMod(clientSideRequired = true, serverSideRequired = false,
clientPacketHandlerSpec = @SidedPacketHandler(channels = {"extraoresChan"}, packetHandler = ClientPacketHandler.class),
serverPacketHandlerSpec = @SidedPacketHandler(channels = {"extroresChan"}, packetHandler = ServerPacketHandler.class))
@Mod ( modid = "ExtraMobDrops", name="Extrappolated Mob Drops", version="1.0A")
public class ExtraMobDrops 
{

	@SidedProxy(clientSide = "sobiohazardous.minestrappolation.extramobdrops.proxy.ClientProxy", serverSide = "sobiohazardous.minestrappolation.extramobdrops.proxy.CommonProxy")
    public static CommonProxy proxy;
	public static CreativeTabs tabItems = new CreativeTabExtraMobDropsItems(CreativeTabs.getNextID(), "Extrappolated Mob Drops - Items");
	public static CreativeTabs tabBlocks = new CreativeTabExtraMobDropsBlocks(CreativeTabs.getNextID(), "Extrappolated Mob Drops - Blocks");
	@Instance("SoBiohazardous_smallexplosives")
	public static ExtraMobDrops instance;
	
	public static Potion potionTestEffect;
	public static Item potionTestItem;
	
	@Init
	public void load(FMLInitializationEvent e)
	{
		proxy.registerRenderThings();
		TickRegistry.registerTickHandler(new OverallTickHandler(EnumSet.of(TickType.PLAYER)), Side.SERVER);
		
		potionTestEffect = (new PAPotionEffect(32, false)).setIconIndex(0, 0).setPotionName("potionEffect.potionTest");
		potionTestItem = (new PAItemPotion(3200, this.potionTestEffect.id, 5, "")).setUnlocalizedName("potionTestItem").setCreativeTab(tabItems);
		
    	PotionAPI.addPotionEffectName("potionEffect.potionTest", "Test Potion");
	}
	
	@PreInit
	public void preLoad(FMLPreInitializationEvent e)
	{
		MinecraftForge.EVENT_BUS.register(new EntityLivingEvent());
		PotionAPI.initializePotionAPI();
	}
	
	@PostInit
	public void postLoad(FMLPostInitializationEvent e)
	{
		
	}
}
