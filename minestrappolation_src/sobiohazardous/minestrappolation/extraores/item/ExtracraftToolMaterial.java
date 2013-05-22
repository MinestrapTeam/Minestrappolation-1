package sobiohazardous.minestrappolation.extraores.item;

import sobiohazardous.minestrappolation.extraores.ExtraOres;
import net.minecraft.block.Block;
import net.minecraft.item.Item;

public enum ExtracraftToolMaterial
{
    ZIRCONIUM(2, 805, 7.0F, 2, 15),
    TITANIUM(4, 2999, 10.0F, 5, 11),
    TORITE(2, 699, 6.0F, 3, 20),
    BLAZIUM(2, 799, 8.0F, 3, 18),
    COPPER(1, 139, 5.0F, 2, 10),
    SANDSTONE(1, 111, 4.5F, 2, 6),
    BRONZE(2, 1501, 6.5F, 2, 8),
    STEEL(2, 300, 7.5F, 3, 15),
    BEDROCK(4, 6247, 7F, 2, 5),
    GRANITE(1, 199, 4.3F, 2, 4),
    BPZIRCONIUM(2, 1610, 7.0F, 2, 15),
    BPTITANIUM(4, 5998, 10.0F, 5, 11),
    BPTORITE(2, 1398, 6.0F, 3, 20),
    BPBLAZIUM(2, 1598, 8.0F, 3, 18),
    BPCOPPER(1, 278, 5.0F, 2, 10),
    BPSANDSTONE(1, 222, 4.5F, 2, 6),
    BPSTEEL(2, 600, 7.5F, 3, 15),
    BPBEDROCK(4, 12494, 7F, 2, 5),
    BPGRANITE(1, 398, 4.3F, 2, 4),
    BPWOOD(0, 118, 2.0F, 0, 15),
    BPSTONE(1, 262, 4.0F, 1, 5),
    BPIRON(2, 500, 6.0F, 2, 14),
    BPEMERALD(3, 3122, 8.0F, 3, 10),
    BPGOLD(0, 64, 12.0F, 0, 22);

    //harvest level, max uses, speed, damage on entity, enchantibility
    
    /**
     * The level of material this tool can harvest (3 = DIAMOND, 2 = IRON, 1 = STONE, 0 = IRON/GOLD)
     */
    private final int harvestLevel;

    /**
     * The number of uses this material allows. (wood = 59, stone = 131, iron = 250, diamond = 1561, gold = 32)
     */
    private final int maxUses;

    /**
     * The strength of this tool material against blocks which it is effective against.
     */
    private final float efficiencyOnProperMaterial;

    /** Damage versus entities. */
    private final int damageVsEntity;

    /** Defines the natural enchantability factor of the material. */
    private final int enchantability;
    
    public Item customCraftingMaterial = null;

    private ExtracraftToolMaterial(int par3, int par4, float par5, int par6, int par7)
    {
        harvestLevel = par3;
        maxUses = par4;
        efficiencyOnProperMaterial = par5;
        damageVsEntity = par6;
        enchantability = par7;
    }

    /**
     * The number of uses this material allows. (wood = 59, stone = 131, iron = 250, diamond = 1561, gold = 32)
     */
    public int getMaxUses()
    {
        return maxUses;
    }

    /**
     * The strength of this tool material against blocks which it is effective against.
     */
    public float getEfficiencyOnProperMaterial()
    {
        return efficiencyOnProperMaterial;
    }

    /**
     * Damage versus entities.
     */
    public int getDamageVsEntity()
    {
        return damageVsEntity;
    }

    /**
     * The level of material this tool can harvest (3 = DIAMOND, 2 = IRON, 1 = STONE, 0 = IRON/GOLD)
     */
    public int getHarvestLevel()
    {
        return harvestLevel;
    }

    /**
     * Return the natural enchantability factor of the material.
     */
    public int getEnchantability()
    {
        return enchantability;
    }

	public int getToolCraftingMaterial()
    {
        switch (this)
        {
            case ZIRCONIUM:    return ExtraOres.ZirconiumIngot.itemID;
            case TITANIUM:     return ExtraOres.TitaniumIngot.itemID;
            case TORITE:       return ExtraOres.ToriteIngot.itemID;
            case BLAZIUM:      return ExtraOres.BlaziumIngot.itemID;
            case COPPER:       return ExtraOres.CopperIngot.itemID;
            case SANDSTONE:    return Block.sandStone.blockID;
            case BRONZE:       return ExtraOres.BronzeIngot.itemID;
            case STEEL:        return ExtraOres.SteelIngot.itemID;
            case BEDROCK:      return ExtraOres.ExtraOresBedrock.blockID;
            case GRANITE:      return ExtraOres.Granite.blockID;
            case BPZIRCONIUM:  return ExtraOres.ZirconiumIngot.itemID;
            case BPTITANIUM:   return ExtraOres.TitaniumIngot.itemID;
            case BPTORITE:     return ExtraOres.ToriteIngot.itemID;
            case BPBLAZIUM:    return ExtraOres.BlaziumIngot.itemID;
            case BPCOPPER:     return ExtraOres.CopperIngot.itemID;
            case BPSANDSTONE:  return Block.sandStone.blockID;
            case BPSTEEL:      return ExtraOres.SteelIngot.itemID;
            case BPBEDROCK:    return ExtraOres.ExtraOresBedrock.blockID;
            case BPGRANITE:    return ExtraOres.Granite.blockID;
            case BPWOOD:       return Block.planks.blockID;
            case BPSTONE:      return Block.cobblestone.blockID;
            case BPGOLD:       return Item.ingotGold.itemID;
            case BPIRON:       return Item.ingotIron.itemID;
            case BPEMERALD:    return Item.diamond.itemID;
            default:           return (customCraftingMaterial == null ? 0 : customCraftingMaterial.itemID);
        }
    }
}
