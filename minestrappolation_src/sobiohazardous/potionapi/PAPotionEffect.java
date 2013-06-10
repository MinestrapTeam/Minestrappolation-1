package sobiohazardous.potionapi;

import java.util.HashMap;
import java.util.Map;

import sobiohazardous.minestrappolation.extramobdrops.ExtraMobDrops;

import net.minecraft.potion.Potion;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;

public class PAPotionEffect extends Potion 
{
	
	public PAPotionEffect(int par1, boolean par2) 
	{
		super(par1, par2, 0);
	}
	
	public Potion setIconIndex(int par1, int par2) 
	{
		super.setIconIndex(par1, par2);
		return this;
	}
}