package sobiohazardous.minestrappolation.extraores.lib;

import sobiohazardous.minestrappolation.extraores.ExtraOres;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class ArmorEffects 
{
    
	/**
	 * @param player
	 */
	public static void loadArmorEffects(EntityPlayer player)
	{ 
		//all
		if (player.getCurrentItemOrArmor(4) != null && player.getCurrentItemOrArmor(3) != null && player.getCurrentItemOrArmor(2) != null && player.getCurrentItemOrArmor(1) != null)
		  {
			  ItemStack helmet = player.getCurrentItemOrArmor(4);
			  ItemStack chest = player.getCurrentItemOrArmor(3);
			  ItemStack pants = player.getCurrentItemOrArmor(2);
			  ItemStack boots = player.getCurrentItemOrArmor(1);
			  
			  if (helmet.getItem() == ExtraOres.ZirconiumHelmet && chest.getItem() == ExtraOres.ZirconiumChest && pants.getItem() == ExtraOres.ZirconiumPants && boots.getItem() == ExtraOres.ZirconiumBoots) 
			  {	
				  player.addPotionEffect(new PotionEffect(Potion.fireResistance.id, 40, 1));
			  }
			  
			  if (helmet.getItem() == ExtraOres.ToriteHelmet && chest.getItem() == ExtraOres.ToriteChest && pants.getItem() == ExtraOres.ToritePants && boots.getItem() == ExtraOres.ToriteBoots) 
			  {	
				  player.addPotionEffect(new PotionEffect(Potion.regeneration.id, 40, 1));
			  }
			  
			  if (helmet.getItem() == ExtraOres.TitaniumHelmet && chest.getItem() == ExtraOres.TitaniumChest && pants.getItem() == ExtraOres.TitaniumPants && boots.getItem() == ExtraOres.TitaniumBoots) 
			  {	
				  player.addPotionEffect(new PotionEffect(Potion.resistance.id, 40, 1));
			  }			  
		  }
	}
	
}