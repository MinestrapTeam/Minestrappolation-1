package sobiohazardous.minestrappolation.extraores;

import java.util.HashMap;
import java.util.Map;

import sobiohazardous.minestrappolation.extraores.gui.ContainerMelter;
import sobiohazardous.minestrappolation.extraores.tileentity.TileEntityMelter;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public class MelterRecipes
{
private static final MelterRecipes melterBase = new MelterRecipes();

/** The list of smelting results. */
private Map melterList = new HashMap();
private Map melterExperience = new HashMap();

	/**
	 * Used to call methods addSmelting and getSmeltingResult.
	 */
	public static final MelterRecipes smelting()
	{
	return melterBase;
	}

	private MelterRecipes()
	{
		addSmelting(Block.dirt.blockID, new ItemStack(Block.cobblestone, 1, 0), 0.7F, false);
		addSmelting(Block.cobblestone.blockID, new ItemStack(Block.dirt, 1, 0), 0.7F, true);
	}	

/**
* Adds a smelting recipe.
*/
	public void addSmelting(int id, ItemStack itemStack, float experience, boolean needBucket)
	{
		if (needBucket)
		{
			melterList.put(Integer.valueOf(id), itemStack);
			this.melterExperience.put(Integer.valueOf(itemStack.itemID), Float.valueOf(experience));
		}
		else
		{
			melterList.put(Integer.valueOf(id), itemStack);
			this.melterExperience.put(Integer.valueOf(itemStack.itemID), Float.valueOf(experience));
		}
		
		
	}

/**
* Returns the smelting result of an item.
*/
public ItemStack getSmeltingResult(int id)
{
return (ItemStack)melterList.get(Integer.valueOf(id));
}

public Map getSmeltingList()
{
return melterList;
}
public float getExperience(int par1)
{
return this.melterExperience.containsKey(Integer.valueOf(par1)) ? ((Float)this.melterExperience.get(Integer.valueOf(par1))).floatValue() : 0.0F;
}
}