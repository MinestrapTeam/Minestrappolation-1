package sobiohazardous.minestrappolation.extraores.item;

import sobiohazardous.minestrappolation.extraores.ExtraOres;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.src.*;

public class EItem extends Item
{
       	private String itemTexture;
       
		public EItem(int i, String texture)
        {
                super(i);
                this.setCreativeTab(ExtraOres.tabOresItems);
                this.itemTexture = "extraores:" + texture;
                this.setPotionEffect("potion.testPotion");
        }
		
		public void registerIcons(IconRegister iconRegister)
		{
		         itemIcon = iconRegister.registerIcon(itemTexture);
		}
}