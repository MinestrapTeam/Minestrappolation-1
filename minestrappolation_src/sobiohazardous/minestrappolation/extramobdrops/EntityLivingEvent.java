package sobiohazardous.minestrappolation.extramobdrops;

import net.minecraft.util.DamageSource;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;

public class EntityLivingEvent 
{
	@ForgeSubscribe
	public void onEntityUpdate(LivingUpdateEvent event)
	{
		if (event.entityLiving.isPotionActive(ExtraMobDrops.potionTestEffect)) 
		{
			if (event.entityLiving.worldObj.rand.nextInt(20) == 0) 
			{
				event.entityLiving.attackEntityFrom(DamageSource.generic, 2);
			}
		}
	}
}