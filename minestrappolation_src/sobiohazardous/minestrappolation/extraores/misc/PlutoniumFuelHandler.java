package sobiohazardous.minestrappolation.extraores.misc;

import sobiohazardous.minestrappolation.extraores.ExtraOres;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.IFuelHandler;

public class PlutoniumFuelHandler implements IFuelHandler {

	@Override
	public int getBurnTime(ItemStack fuel) {
		if(fuel.itemID == ExtraOres.Plutonium.itemID)
		{
			return 35000;
		}
		else
		return 0;
	}

}
