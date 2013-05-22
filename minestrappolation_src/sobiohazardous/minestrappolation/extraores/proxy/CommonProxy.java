package sobiohazardous.minestrappolation.extraores.proxy;

import java.util.Map;

import sobiohazardous.minestrappolation.extraores.EOBlockRegister;
import sobiohazardous.minestrappolation.extraores.entity.EntityNukePrimed;
import sobiohazardous.minestrappolation.extraores.entity.RenderNukePrimed;
import sobiohazardous.minestrappolation.extraores.plate.IPlateRenderingHandler;

import net.minecraft.src.ModLoader;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;


public class CommonProxy
{
    /*
     * Anything you put in here can be implemented DIFFERENTLY on client/server. Serverside is here obviously, clientside is at ClientProxy
     * Hint: Client has access to Classes and Methods a Server doesn't know
     */
    
    public void registerRenderThings()
    {
        /* NOOP, servers do not need Renderers */
    	
    }
    
    public void registerRenders(){};
    public static int addArmor(String armor) { return 0; }
}