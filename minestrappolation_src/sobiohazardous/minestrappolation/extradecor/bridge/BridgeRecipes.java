package sobiohazardous.minestrappolation.extradecor.bridge;

import java.lang.reflect.Field;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import sobiohazardous.minestrappolation.extradecor.ExtraDecor;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;
/**
 * This class loads recipes that use other mods from minestrappolation
 * @author SoBiohazardous
 *
 */
public class BridgeRecipes 
{
	private static boolean hasExtraOres = Loader.isModLoaded("ExtraOres");
	
	public static void loadBridgeRecipes() throws Exception
	{	
		if(hasExtraOres)
		{
			GameRegistry.addRecipe(new ItemStack(ExtraDecor.stoneLamp, 8, 1), new Object[]
					{
				"SSS", "SGS", "SSS", Character.valueOf('S'), ExtraDecor.stoneBlockRefined, Character.valueOf('G'), sobiohazardous.minestrappolation.extraores.ExtraOres.Sunstone
					});
		}
	}
	
	//EXPERIMENTAL\\
	
	/**
	 * Used for getting blocks and items from classes if they do not exist (reflection)
	 * @param pkg the package for the class
	 * @param modClassName the name of the class
	 * @param blockOrItemName the block of item you are wanting to reference
	 * @return itemstack of the block or item your referencing
	 */
	public static ItemStack getItemStackFromObject(String pkg, String modClassName, String blockOrItemName) 
	{
		try 
		{
			Class eoReflect = Class.forName(pkg + "." + modClassName);
			
			Object itemstackField = eoReflect.getField(blockOrItemName).get(null);

			if (itemstackField instanceof ItemStack)
			{
				return (ItemStack) itemstackField;
			} 
				else 
				{
					return null;
				}
			} 
			catch (Exception e) 
			{
				System.out.println("Minestrappolation: getItemStackFromMod failed for "+blockOrItemName);

				return null;
			}
	}
		
}
