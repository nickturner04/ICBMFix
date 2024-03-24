package icbm.zhapin.daodan;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItModuleMissile extends ItMissile {
    public ItModuleMissile() {
        super("specialMissile");
    }

    @Override
    public String getUnlocalizedName(final ItemStack itemstack) {
        if (itemstack.getItemDamage() == 0) {
            return this.getUnlocalizedName() + ".missileModule";
        }

        return this.getUnlocalizedName() + "."
            + MissileBase.list[itemstack.getItemDamage() + 100].getUnlocalizedName();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(final IIconRegister iconRegister) {
        this.itemIcon = iconRegister.registerIcon("icbm:missileModule");
    }

    @Override
    public String getUnlocalizedName() {
        return "icbm.missile";
    }

    @Override
    public void getSubItems(
        final Item par1, final CreativeTabs par2CreativeTabs, final List par3List
    ) {
        for (int i = 0; i < 5; ++i) {
            par3List.add(new ItemStack((Item) this, 1, i));
        }
    }
}
