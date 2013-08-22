package sobiohazardous.minestrappolation.extraores.item;

import sobiohazardous.minestrappolation.extraores.ExtraOres;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.src.*;

public class EItemFoiled extends Item
{
        private String itemTexture;
		public EItemFoiled(int i, String texture)
        {
                super(i);
                this.setCreativeTab(ExtraOres.tabOresItems);
                this.itemTexture = "extraores:" + texture;
        }

        public void registerIcons(IconRegister iconRegister)
		{
		         itemIcon = iconRegister.registerIcon(itemTexture);
		}
        @SideOnly(Side.CLIENT)
        public boolean hasEffect(ItemStack par1ItemStack)
        {
            return true;
        }
}