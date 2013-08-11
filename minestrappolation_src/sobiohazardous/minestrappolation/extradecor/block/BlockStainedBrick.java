package sobiohazardous.minestrappolation.extradecor.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;

import sobiohazardous.minestrappolation.extradecor.ExtraDecor;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;

public class BlockStainedBrick extends Block
{
    @SideOnly(Side.CLIENT)
    private Icon[] iconArray;
    public static final String[] stainedBrickTextures = new String[] {"ExtraDecor:block_WhiteStainedBrick", "ExtraDecor:block_LightGreyStainedBrick", "ExtraDecor:block_DarkGreyStainedBrick", "ExtraDecor:block_BlackStainedBrick", "ExtraDecor:block_BrownStainedBrick", "ExtraDecor:block_PinkStainedBrick", "ExtraDecor:block_RedStainedBrick", "ExtraDecor:block_OrangeStainedBrick", "ExtraDecor:block_YellowStainedBrick", "ExtraDecor:block_LimeStainedBrick", "ExtraDecor:block_GreenStainedBrick", "ExtraDecor:block_CyanStainedBrick", "ExtraDecor:block_LightBlueStainedBrick", "ExtraDecor:block_BlueStainedBrick", "ExtraDecor:block_PurpleStainedBrick", "ExtraDecor:block_MagentaStainedBrick"};
    public static final String[] brickType = new String[] {"white", "lightGrey", "darkGrey", "black", "brown", "pink", "red", "orange", "yellow", "lime", "green", "cyan", "lightBlue", "blue", "purple", "magenta"};
    
    public BlockStainedBrick(int id)
    {
        super(id, Material.rock);
        this.setCreativeTab(ExtraDecor.tabDecorBlocks);
    }

    @SideOnly(Side.CLIENT)

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public Icon getIcon(int par1, int par2)
    {
        if (par2 < 0 || par2 >= this.iconArray.length)
        {
            par2 = 0;
        }

        return this.iconArray[par2];
    }
    
    /**
     * Determines the damage on the item the block drops. Used in cloth and wood.
     */
    public int damageDropped(int par1)
    {
        return par1;
    }

    @SideOnly(Side.CLIENT)

    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
    	par3List.add(new ItemStack(par1, 1, 0));
    	par3List.add(new ItemStack(par1, 1, 1));
    	par3List.add(new ItemStack(par1, 1, 2));
    	par3List.add(new ItemStack(par1, 1, 3));
    	par3List.add(new ItemStack(par1, 1, 4));
    	par3List.add(new ItemStack(par1, 1, 5));
    	par3List.add(new ItemStack(par1, 1, 6));
    	par3List.add(new ItemStack(par1, 1, 7));
    	par3List.add(new ItemStack(par1, 1, 8));
    	par3List.add(new ItemStack(par1, 1, 9));
    	par3List.add(new ItemStack(par1, 1, 10));
    	par3List.add(new ItemStack(par1, 1, 11));
    	par3List.add(new ItemStack(par1, 1, 12));
    	par3List.add(new ItemStack(par1, 1, 13));
    	par3List.add(new ItemStack(par1, 1, 14));
    	par3List.add(new ItemStack(par1, 1, 15));
    }

    @SideOnly(Side.CLIENT)

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.iconArray = new Icon[stainedBrickTextures.length];

        for (int i = 0; i < this.iconArray.length; ++i)
        {
            this.iconArray[i] = par1IconRegister.registerIcon(stainedBrickTextures[i]);
        }
    }
}
