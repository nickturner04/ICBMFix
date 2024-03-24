package icbm.gangshao.turret;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockTurret extends ItemBlock {
    public ItemBlockTurret(final Block par1) {
        super(par1);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
    }

    public int getMetadata(final int par1) {
        return par1;
    }

    public String getUnlocalizedName(final ItemStack par1ItemStack) {
        return this.getUnlocalizedName() + "." + par1ItemStack.getItemDamage();
    }
}
