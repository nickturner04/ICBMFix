package icbm.wanyi;

import java.util.List;

import icbm.api.IItemFrequency;
import icbm.core.di.ItElectricICBM;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import universalelectricity.core.electricity.ElectricityPack;

public class ItSignalDisrupter extends ItElectricICBM implements IItemFrequency {
    public ItSignalDisrupter() {
        super("signalDisrupter");
        this.setTextureName("icbm:signalDisrupter");
    }

    @Override
    public void addInformation(
        final ItemStack itemStack,
        final EntityPlayer par2EntityPlayer,
        final List par3List,
        final boolean par4
    ) {
        super.addInformation(itemStack, par2EntityPlayer, par3List, par4);
        par3List.add("Frequency: " + this.getFrequency(itemStack));
    }

    @Override
    public int getFrequency(final ItemStack itemStack) {
        if (itemStack.stackTagCompound == null) {
            return 0;
        }

        return itemStack.stackTagCompound.getInteger("frequency");
    }

    @Override
    public void setFrequency(final int frequency, final ItemStack itemStack) {
        if (itemStack.stackTagCompound == null) {
            itemStack.setTagCompound(new NBTTagCompound());
        }

        itemStack.stackTagCompound.setInteger("frequency", frequency);
    }

    public void onUpdate(
        final ItemStack itemStack,
        final World par2World,
        final Entity par3Entity,
        final int par4,
        final boolean par5
    ) {
        if (!par2World.isRemote) {
            super.onUpdate(itemStack, par2World, par3Entity, par4, par5);

            if (this.getJoules(itemStack) > 1.0) {
                this.onProvide(
                    ElectricityPack.getFromWatts(1.0, this.getJoules(itemStack)),
                    itemStack
                );
            }
        }
    }

    public ItemStack onItemRightClick(
        final ItemStack par1ItemStack,
        final World par2World,
        final EntityPlayer par3EntityPlayer
    ) {
        par3EntityPlayer.openGui(
            (Object) ICBMContraption.instance,
            5,
            par2World,
            (int) ((Entity) par3EntityPlayer).posX,
            (int) ((Entity) par3EntityPlayer).posY,
            (int) ((Entity) par3EntityPlayer).posZ
        );
        return par1ItemStack;
    }

    public double getVoltage(final ItemStack itemStack) {
        return 25.0;
    }

    public double getMaxJoules(final ItemStack itemStack) {
        return 80000.0;
    }
}
