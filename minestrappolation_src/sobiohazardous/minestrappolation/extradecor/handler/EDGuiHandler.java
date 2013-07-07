package sobiohazardous.minestrappolation.extradecor.handler;

import sobiohazardous.minestrappolation.extradecor.container.ContainerCrate;
import sobiohazardous.minestrappolation.extradecor.gui.GuiCrate;
import sobiohazardous.minestrappolation.extradecor.tileentity.TileEntityCrate;
import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.src.*;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class EDGuiHandler implements IGuiHandler
{
	
	@Override
	public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z)
	{
		// This gets the TileEntity the player is currently activating
		TileEntity tile_entity = world.getBlockTileEntity(x, y, z);
		// This checks if the TileEntity is the TileTutorial
		if(tile_entity instanceof TileEntityCrate){
			// If it is it returns a new ContainerTutorial instance
			return new ContainerCrate((TileEntityCrate) tile_entity, player.inventory);
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z)
	{
		// This gets the TIleEntity the player is currently activating
		TileEntity tile_entity = world.getBlockTileEntity(x, y, z);

		// This checks if the TileEntity is the TileTutorial
		if(tile_entity instanceof TileEntityCrate){
			// If it is it returns a new GuiTutorial instance
			return new GuiCrate(player.inventory, (TileEntityCrate) tile_entity);
		}

		// Returns null if not
		return null;
	}

}