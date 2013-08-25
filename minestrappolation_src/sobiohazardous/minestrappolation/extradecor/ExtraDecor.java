package sobiohazardous.minestrappolation.extradecor;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import sobiohazardous.minestrappolation.extradecor.block.*;
import sobiohazardous.minestrappolation.extradecor.bridge.EDBridgeRecipes;
import sobiohazardous.minestrappolation.extradecor.gen.EDOreGenerator;
import sobiohazardous.minestrappolation.extradecor.handler.ClientPacketHandler;
import sobiohazardous.minestrappolation.extradecor.handler.EDGuiHandler;
import sobiohazardous.minestrappolation.extradecor.handler.ServerPacketHandler;
import sobiohazardous.minestrappolation.extradecor.item.EDItem;
import sobiohazardous.minestrappolation.extradecor.item.EDItemManager;
import sobiohazardous.minestrappolation.extradecor.item.ItemBlockPlacer;
import sobiohazardous.minestrappolation.extradecor.lib.EDRecipeManager;
import sobiohazardous.minestrappolation.extradecor.proxy.CommonProxy;
import sobiohazardous.minestrappolation.extradecor.tileentity.TileEntityBarrel;
import sobiohazardous.minestrappolation.extradecor.tileentity.TileEntityCardboardWet;
import sobiohazardous.minestrappolation.extradecor.tileentity.TileEntityCrate;
import net.minecraft.block.Block;
import net.minecraft.block.BlockGlass;
import net.minecraft.block.BlockGlowStone;
import net.minecraft.block.BlockHalfSlab;
import net.minecraft.block.BlockObsidian;
import net.minecraft.block.BlockPane;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.BlockWood;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemMultiTextureTile;
import net.minecraft.item.ItemSlab;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraftforge.common.ChestGenHooks;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.Mod;
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
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import sobiohazardous.minestrappolation.extradecor.material.*;

/**
 * 
 * 
 * 
 * @author SoBiohazardous
 */
@NetworkMod(clientSideRequired = true, serverSideRequired = false,
clientPacketHandlerSpec = @SidedPacketHandler(channels = {"extradecorChan"}, packetHandler = ClientPacketHandler.class),
serverPacketHandlerSpec = @SidedPacketHandler(channels = {"extradecorChanS"}, packetHandler = ServerPacketHandler.class))
@Mod ( modid = "ExtraDecor", name="Extrappolated Decor", version="B1.1")
public class ExtraDecor 
{
	@SidedProxy(clientSide = "sobiohazardous.minestrappolation.extradecor.proxy.ClientProxy", serverSide = "sobiohazardous.minestrappolation.extradecor.proxy.CommonProxy")
    public static CommonProxy proxy;
	
	@Instance("ExtraDecor")
	public static ExtraDecor instance;

	
	

	
	
	
	public static int paneRenderId = RenderingRegistry.getNextAvailableRenderId();
	public static int ropeRenderId = RenderingRegistry.getNextAvailableRenderId();
	
	
	@Mod.EventHandler
	public void preLoad(FMLPreInitializationEvent event)
	{
		EDConfig.initilize(event);

			
	}
	
	
	@Mod.EventHandler
	public void load(FMLInitializationEvent event)
	{
		proxy.registerRenderThings();
				
		NetworkRegistry.instance().registerGuiHandler(this, new EDGuiHandler());
		
		EDTileEntityManager.registerTileEntitys();
		
		MinecraftForge.setToolClass(Item.shears, "shears", 0);
		EDItemManager.addItems();
		EDItemManager.addItemNames();
		EDItemManager.setHarvestLevels();
		EDBlockManager.createBlocks();
	    EDBlockManager.registerBlocks();
	    EDBlockManager.addNames();
		EDRecipeManager.loadAllRecipes();
		
		GameRegistry.registerWorldGenerator(new EDOreGenerator());	
		EDOreRegistry.oreRegistration();
		EDOreRegistry.addOreRecipes();
	}

	@Mod.EventHandler
    public void postLoad(FMLPostInitializationEvent evt)
	{
		EDItemManager.addItemsToItemList();
	}
	
	
}
