package icbm.zhapin.dianqi;

import java.util.List;

import icbm.core.di.ItElectricICBM;
import icbm.zhapin.ICBMExplosion;
import icbm.zhapin.ItemUsePacket;
import icbm.zhapin.zhapin.TExplosive;
import icbm.zhapin.zhapin.ZhaPin;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import universalelectricity.core.electricity.ElectricityPack;
import universalelectricity.core.vector.Vector3;

public class ItRemoteDetonator extends ItElectricICBM {
    public static final int RADIUS = 100;
    public static final int ENERGY_USED = 1500;

    public ItRemoteDetonator() {
        super("remoteDetonator");
        this.setTextureName("icbm:remoteDetonator");
    }

    @Override
    public void addInformation(
        final ItemStack itemStack,
        final EntityPlayer player,
        final List par3List,
        final boolean par4
    ) {
        super.addInformation(itemStack, player, par3List, par4);
        final Vector3 coord = this.getSavedCoord(itemStack);

        if (this.nengZha(coord.getTileEntity((IBlockAccess) ((Entity) player).worldObj)
            )) {
            par3List.add("\uaa74Linked Explosive:");
            par3List.add(
                "X: " + (int) coord.x + ", Y: " + (int) coord.y + ", Z: " + (int) coord.z
            );
        } else {
            par3List.add("§4No Linked Explosive");
        }
    }

    @Override
    public boolean onItemUse(
        final ItemStack itemStack,
        final EntityPlayer entityPlayer,
        final World world,
        final int x,
        final int y,
        final int z,
        final int par7,
        final float par8,
        final float par9,
        final float par10
    ) {
        final TileEntity tileEntity = world.getTileEntity(x, y, z);

        if (entityPlayer.isSneaking() && tileEntity != null && this.nengZha(tileEntity)) {
            if (this.getJoules(itemStack) > 1500.0) {
                this.setSavedCoords(itemStack, new Vector3(x, y, z));
                this.onProvide(
                    ElectricityPack.getFromWatts(1500.0, this.getJoules(itemStack)),
                    itemStack
                );

                if (world.isRemote) {
                    entityPlayer.addChatMessage(new ChatComponentText(
                        "Remote Locked to: X:" + x + ", Y:" + y + ", Z:" + z
                    ));
                }
            } else if (world.isRemote) {
                entityPlayer.addChatMessage(
                    new ChatComponentText("Remote out of electricity!")
                );
            }

            return true;
        }

        return false;
    }

    @Override
    public ItemStack onItemRightClick(
        final ItemStack itemStack, final World world, final EntityPlayer entityPlayer
    ) {
        if (world.isRemote) {
            if (!entityPlayer.isSneaking()) {
                final MovingObjectPosition objectMouseOver
                    = entityPlayer.rayTrace(100.0, 1.0f);

                if (objectMouseOver != null
                    && objectMouseOver.typeOfHit == MovingObjectType.BLOCK) {
                    final TileEntity tileEntity = world.getTileEntity(
                        objectMouseOver.blockX,
                        objectMouseOver.blockY,
                        objectMouseOver.blockZ
                    );
                    final Block blockID = world.getBlock(
                        objectMouseOver.blockX,
                        objectMouseOver.blockY,
                        objectMouseOver.blockZ
                    );
                    final TileEntity tile = world.getTileEntity(
                        objectMouseOver.blockX,
                        objectMouseOver.blockY,
                        objectMouseOver.blockZ
                    );

                    if (tile != null && tile instanceof TExplosive) {
                        if (blockID == ICBMExplosion.bMachine) {
                            return itemStack;
                        }

                        if (this.nengZha(tileEntity)) {
                            if (this.getJoules(itemStack) > 1500.0) {
                                ICBMExplosion.channel.sendToServer(new ItemUsePacket(
                                    ItemUsePacket.Type.REMOTE, new Vector3(tileEntity)
                                ));
                                return itemStack;
                            }

                            entityPlayer.addChatMessage(
                                new ChatComponentText("Remote out of electricity!")
                            );
                        }
                    }
                }
            } else if (this.getJoules(itemStack) > 1500.0) {
                final TileEntity tileEntity2
                    = this.getSavedCoord(itemStack).getTileEntity((IBlockAccess) world);

                if (this.nengZha(tileEntity2)) {
                    ICBMExplosion.channel.sendToServer(new ItemUsePacket(
                        ItemUsePacket.Type.REMOTE, new Vector3(tileEntity2)
                    ));
                }
            } else {
                entityPlayer.addChatMessage(
                    new ChatComponentText("Remote out of electricity!")
                );
            }
        }

        return itemStack;
    }

    public boolean nengZha(final TileEntity tileEntity) {
        return tileEntity != null && tileEntity instanceof TExplosive
            && (((TExplosive) tileEntity).explosiveId == ZhaPin.condensed.getID()
                || ((TExplosive) tileEntity).explosiveId == ZhaPin.breaching.getID()
                || ((TExplosive) tileEntity).explosiveId == ZhaPin.sMine.getID());
    }

    public void setSavedCoords(final ItemStack itemStack, final Vector3 position) {
        if (itemStack.stackTagCompound == null) {
            itemStack.setTagCompound(new NBTTagCompound());
        }

        itemStack.stackTagCompound.setInteger("x", position.intX());
        itemStack.stackTagCompound.setInteger("y", position.intY());
        itemStack.stackTagCompound.setInteger("z", position.intZ());
    }

    public Vector3 getSavedCoord(final ItemStack par1ItemStack) {
        if (par1ItemStack.stackTagCompound == null) {
            return new Vector3();
        }

        return new Vector3(
            par1ItemStack.stackTagCompound.getInteger("x"),
            par1ItemStack.stackTagCompound.getInteger("y"),
            par1ItemStack.stackTagCompound.getInteger("z")
        );
    }

    @Override
    public double getVoltage(final ItemStack itemStack) {
        return 20.0;
    }

    @Override
    public double getMaxJoules(final ItemStack itemStack) {
        return 50000.0;
    }
}
