package icbm.wanyi;

import icbm.core.di.ItICBM;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItAntidote extends ItICBM {
    public ItAntidote() {
        super("antidote");
        this.setTextureName("icbm:antidote");
    }

    public ItemStack onEaten(
        final ItemStack par1ItemStack,
        final World par2World,
        final EntityPlayer par3EntityPlayer
    ) {
        --par1ItemStack.stackSize;

        if (!par2World.isRemote) {
            par3EntityPlayer.clearActivePotions();
        }

        return par1ItemStack;
    }

    public int getMaxItemUseDuration(final ItemStack par1ItemStack) {
        return 32;
    }

    public EnumAction getItemUseAction(final ItemStack par1ItemStack) {
        return EnumAction.eat;
    }

    public ItemStack onItemRightClick(
        final ItemStack par1ItemStack,
        final World par2World,
        final EntityPlayer par3EntityPlayer
    ) {
        par3EntityPlayer.setItemInUse(
            par1ItemStack, this.getMaxItemUseDuration(par1ItemStack)
        );
        return par1ItemStack;
    }
}
