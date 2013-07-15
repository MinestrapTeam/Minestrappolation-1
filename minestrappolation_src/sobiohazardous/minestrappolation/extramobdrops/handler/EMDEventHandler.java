package sobiohazardous.minestrappolation.extramobdrops.handler;

import sobiohazardous.minestrappolation.extramobdrops.ExtraMobDrops;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.passive.*;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingDropsEvent;

public class EMDEventHandler 
{
	@ForgeSubscribe
	public void onMobDrops(LivingDropsEvent event)
	{
		if (event.source.getDamageType().equals("player")) 
		{
			double rand = Math.random();
			if (event.entityLiving instanceof EntityPig)
			{
				if (rand < 1D) //Makes drop 100% drop chance. Example: (0.25D = 25%, 1D = 100%, etc.)
				{	
					event.entityLiving.dropItem(ExtraMobDrops.snout.itemID, 1);		
					event.entityLiving.dropItem(ExtraMobDrops.pigHoof.itemID, 1);	
					event.entityLiving.dropItem(ExtraMobDrops.fat.itemID, 1);	

				}
			}
			
			if(event.entityLiving instanceof EntityCow)
			{
				event.entityLiving.dropItem(ExtraMobDrops.snout.itemID, 1);
			}
			
			if(event.entityLiving instanceof EntityMooshroom)
			{
				event.entityLiving.dropItem(ExtraMobDrops.snout.itemID, 1);
			}
			
			if(event.entityLiving instanceof EntityWolf)
			{
				event.entityLiving.dropItem(ExtraMobDrops.snout.itemID, 1);
			}
			
			if(event.entityLiving instanceof EntityPigZombie)
			{
				event.entityLiving.dropItem(ExtraMobDrops.snout.itemID, 1);
				event.entityLiving.dropItem(ExtraMobDrops.pigHoof.itemID, 1);
				event.entityLiving.dropItem(ExtraMobDrops.fat.itemID, 1);	
			}
		}
	}
}
