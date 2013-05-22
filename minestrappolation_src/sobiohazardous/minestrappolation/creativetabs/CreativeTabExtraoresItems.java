package sobiohazardous.minestrappolation.creativetabs;

import sobiohazardous.minestrappolation.extraores.*;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;


public final class CreativeTabExtraoresItems extends CreativeTabs
{
        public CreativeTabExtraoresItems(int par1, String par2Str)
        {
                super(par1, par2Str);
        }
        @SideOnly(Side.CLIENT)
        public int getTabIconItemIndex()
        {
                return ExtraOres.CopperIngot.itemID;
        }
   
        public String getTranslatedTabLabel()
        {
         return "Extra Ores Items";
        }
}