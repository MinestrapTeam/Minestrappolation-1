package sobiohazardous.minestrappolation.extradecor.block;

import sobiohazardous.minestrappolation.extradecor.tileentity.TileEntityBarrel;
import sobiohazardous.minestrappolation.extradecor.tileentity.TileEntityCardboardWet;
import sobiohazardous.minestrappolation.extradecor.tileentity.TileEntityCrate;
import cpw.mods.fml.common.registry.GameRegistry;

public class EDTileEntityManager {

	public static void registerTileEntitys(){
		GameRegistry.registerTileEntity(TileEntityCrate.class, "tileEntityCrate");
		GameRegistry.registerTileEntity(TileEntityBarrel.class, "tileEntityBarrel");
		GameRegistry.registerTileEntity(TileEntityCardboardWet.class, "tileEntityCardboardWet");
	}
}
