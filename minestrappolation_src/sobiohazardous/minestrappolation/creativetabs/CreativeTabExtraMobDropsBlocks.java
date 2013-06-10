package sobiohazardous.minestrappolation.creativetabs;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;


public final class CreativeTabExtraMobDropsBlocks extends CreativeTabs
{
        public CreativeTabExtraMobDropsBlocks(int par1, String par2Str)
        {
                super(par1, par2Str);
        }
        
        /*
        @SideOnly(Side.CLIENT)
        public int getTabIconItemIndex()
        {
                return;
        }
        */
   
        public String getTranslatedTabLabel()
        {
        	return "Extrappolated Mob Drops - Items";
        }
}