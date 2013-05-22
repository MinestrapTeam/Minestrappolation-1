package sobiohazardous.minestrappolation.extraores.misc;

import sobiohazardous.minestrappolation.extraores.ExtraOres;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.IFuelHandler;

public class UraniumFuelHandler implements IFuelHandler {

	@Override
	public int getBurnTime(ItemStack fuel) {
		if(fuel.itemID == ExtraOres.Uranium.itemID)
		{
			return 30000;
		}
		else
		return 0;
	}

}
