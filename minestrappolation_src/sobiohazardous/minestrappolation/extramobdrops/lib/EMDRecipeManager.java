package sobiohazardous.minestrappolation.extramobdrops.lib;

import net.minecraft.item.ItemStack;
import sobiohazardous.minestrappolation.extramobdrops.ExtraMobDrops;
import cpw.mods.fml.common.registry.GameRegistry;

public class EMDRecipeManager 
{
	public static void loadRecipes()
	{
		GameRegistry.addSmelting(ExtraMobDrops.fat.itemID, new ItemStack(ExtraMobDrops.grease, 1), 0.7F);
	}
}
