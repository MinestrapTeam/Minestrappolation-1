package sobiohazardous.minestrappolation.extradecor.item;

import sobiohazardous.minestrappolation.extradecor.ExtraDecor;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.src.*;

public class EDItem extends Item
{
       	private String itemTexture;
       
		public EDItem(int i, String texture)
        {
                super(i);
                this.setCreativeTab(ExtraDecor.tabDecorBlocks);
                this.itemTexture = "ExtraDecor:" + texture;
        }
		
		public void registerIcons(IconRegister iconRegister)
		{
		         itemIcon = iconRegister.registerIcon(itemTexture);
		}
}