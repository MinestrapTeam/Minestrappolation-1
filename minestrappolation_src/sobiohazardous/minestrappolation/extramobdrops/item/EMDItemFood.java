package sobiohazardous.minestrappolation.extramobdrops.item;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.ItemFood;

public class EMDItemFood extends ItemFood
{
	private String texture;
	public EMDItemFood(int id, int healAmt, float sat, String texture)
	{
		super(id, healAmt, sat, false);
		this.texture = texture;
	}
	
	public void registerIcons(IconRegister reg)
	{
		itemIcon = reg.registerIcon(texture);
	}
}
