package icbm.zhapin.cart;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import icbm.core.di.ItICBM;
import icbm.zhapin.zhapin.ZhaPin;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRailBase;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItCart extends ItICBM {
    public ItCart() {
        super("minecart");
        this.setMaxStackSize(1);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
    }

    @Override
    public boolean onItemUse(
        final ItemStack par1ItemStack,
        final EntityPlayer par2EntityPlayer,
        final World par3World,
        final int x,
        final int y,
        final int z,
        final int par7,
        final float par8,
        final float par9,
        final float par10
    ) {
        final Block var11 = par3World.getBlock(x, y, z);

        if (BlockRailBase.func_150051_a(var11)) {
            if (!par3World.isRemote) {
                par3World.spawnEntityInWorld((Entity) new ECart(
                    par3World, x + 0.5f, y + 0.5f, z + 0.5f, par1ItemStack.getItemDamage()
                ));
            }

            --par1ItemStack.stackSize;
            return true;
        }

        return false;
    }

    @Override
    public int getMetadata(final int damage) {
        return damage;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(final IIconRegister iconRegister) {
        this.itemIcon = iconRegister.registerIcon("minecart_tnt");
    }

    @Override
    public String getUnlocalizedName(final ItemStack itemstack) {
        return "icbm.minecart."
            + ZhaPin.list[itemstack.getItemDamage()].getUnlocalizedName();
    }

    @Override
    public void getSubItems(
        final Item par1, final CreativeTabs par2CreativeTabs, final List par3List
    ) {
        for (int metadata = 0; metadata < ZhaPin.E_ER_ID; ++metadata) {
            par3List.add(new ItemStack(par1, 1, metadata));
        }
    }
}
