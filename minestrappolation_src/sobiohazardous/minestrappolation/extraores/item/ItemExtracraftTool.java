package sobiohazardous.minestrappolation.extraores.item;

import sobiohazardous.minestrappolation.extraores.ExtraOres;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;

public class ItemExtracraftTool extends Item
{
    private Block[] blocksEffectiveAgainst;
    public float efficiencyOnProperMaterial = 4.0F;

    /** Damage versus entities. */
    public int damageVsEntity;

    /** The material this tool is made from. */
    protected ExtracraftToolMaterial toolMaterial;
    

    protected ItemExtracraftTool(int par1, int par2, ExtracraftToolMaterial par3ExtracraftToolMaterial, Block par4ArrayOfBlock[])
    {
        super(par1);
        this.toolMaterial = par3ExtracraftToolMaterial;
        this.blocksEffectiveAgainst = par4ArrayOfBlock;
        this.maxStackSize = 1;
        this.setMaxDamage(par3ExtracraftToolMaterial.getMaxUses());
        this.efficiencyOnProperMaterial = par3ExtracraftToolMaterial.getEfficiencyOnProperMaterial();
        this.damageVsEntity = par2 + par3ExtracraftToolMaterial.getDamageVsEntity();
    }

    /**
     * Returns the strength of the stack against a given block. 1.0F base, (Quality+1)*2 if correct blocktype, 1.5F if
     * sword
     */
    public float getStrVsBlock(ItemStack par1ItemStack, Block par2Block)
    {
        for (int var3 = 0; var3 < this.blocksEffectiveAgainst.length; ++var3)
        {
            if (this.blocksEffectiveAgainst[var3] == par2Block)
            {
                return this.efficiencyOnProperMaterial;
            }
        }

        return 1.0F;
    }

    /**
     * Current implementations of this method in child classes do not use the entry argument beside ev. They just raise
     * the damage on the stack.
     */
    public boolean hitEntity(ItemStack par1ItemStack, EntityLiving par2EntityLiving, EntityLiving par3EntityLiving)
    {
        if(toolMaterial == ExtracraftToolMaterial.TORITE)
        	{
        	par1ItemStack.damageItem(1, par3EntityLiving);
        	return true;
        	}
        else
        {
        	par1ItemStack.damageItem(2, par3EntityLiving);
        	return true;
        }
    }

    public boolean onBlockDestroyed(ItemStack par1ItemStack, World par2World, int par3, int par4, int par5, int par6, EntityLiving par7EntityLiving)
    {
        if((double)Block.blocksList[par3].blockID != Block.bedrock.blockID)
        {
    	    if ((double)Block.blocksList[par3].getBlockHardness(par2World, par4, par5, par6) != 0.0D)
            {
                par1ItemStack.damageItem(1, par7EntityLiving);
            }
        }
        else
        {
        	if(toolMaterial == ExtracraftToolMaterial.TITANIUM || toolMaterial == ExtracraftToolMaterial.BEDROCK)
        	{
        	    par1ItemStack.damageItem(20, par7EntityLiving);
        	}
        	else
        	{
        		par1ItemStack.damageItem(1, par7EntityLiving);
        	}
        }
        return true;
    }

    /**
     * Returns the damage against a given entity.
     */
    public int getDamageVsEntity(Entity par1Entity)
    {
        return damageVsEntity;
    }

    /**
     * Returns True is the item is renderer in full 3D when hold.
     */
    public boolean isFull3D()
    {
        return true;
    }

    /**
     * Return the enchantability factor of the item, most of the time is based on material.
     */
    public int getItemEnchantability()
    {
        return toolMaterial.getEnchantability();
    }
    public String getToolMaterialName()
    {
        return this.toolMaterial.toString();
    }

    /**
     * Return whether this item is repairable in an anvil.
     */
    public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack)
    {
        return this.toolMaterial.getToolCraftingMaterial() == par2ItemStack.itemID ? true : super.getIsRepairable(par1ItemStack, par2ItemStack);
    }

    /** FORGE: Overridden to allow custom tool effectiveness */
    @Override
    public float getStrVsBlock(ItemStack stack, Block block, int meta) 
    {
        if (ForgeHooks.isToolEffective(stack, block, meta))
        {
            return efficiencyOnProperMaterial;
        }
        return getStrVsBlock(stack, block);
    }
}
