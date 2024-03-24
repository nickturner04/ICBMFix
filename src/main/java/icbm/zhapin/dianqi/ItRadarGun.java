package icbm.zhapin.dianqi;

import java.util.List;

import icbm.core.di.ItElectricICBM;
import icbm.zhapin.ICBMExplosion;
import icbm.zhapin.ItemUsePacket;
import icbm.zhapin.jiqi.TCruiseLauncher;
import icbm.zhapin.jiqi.TLauncherControlPanel;
import icbm.zhapin.jiqi.TLauncherController;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import universalelectricity.core.electricity.ElectricityPack;
import universalelectricity.core.vector.Vector3;

public class ItRadarGun extends ItElectricICBM {
    public static final int USED_ENERGY = 1000;
    public static final int DISTANCE = 1000;

    public ItRadarGun() {
        super("radarGun");
        this.setTextureName("icbm:radarGun");
    }

    @Override
    public void addInformation(
        final ItemStack itemStack,
        final EntityPlayer par2EntityPlayer,
        final List par3List,
        final boolean par4
    ) {
        super.addInformation(itemStack, par2EntityPlayer, par3List, par4);
        final Vector3 coord = this.getSavedCoord(itemStack);
        par3List.add("\uaa74Saved Coordinates:");
        par3List.add(
            "X: " + (int) coord.x + ", Y: " + (int) coord.y + ", Z: " + (int) coord.z
        );
    }

    public ItemStack onItemRightClick(
        final ItemStack itemStack,
        final World par2World,
        final EntityPlayer par3EntityPlayer
    ) {
        if (par2World.isRemote) {
            final MovingObjectPosition objectMouseOver
                = par3EntityPlayer.rayTrace(1000.0, 1.0f);

            if (objectMouseOver != null) {
                final TileEntity tileEntity = par2World.getTileEntity(
                    objectMouseOver.blockX, objectMouseOver.blockY, objectMouseOver.blockZ
                );

                if (!(tileEntity instanceof TLauncherController)) {
                    if (this.getJoules(itemStack) > 1000.0) {
                        ICBMExplosion.channel.sendToServer(new ItemUsePacket(
                            ItemUsePacket.Type.RADAR_GUN,
                            new Vector3(
                                objectMouseOver.blockX,
                                objectMouseOver.blockY,
                                objectMouseOver.blockZ
                            )
                        ));
                        this.onProvide(
                            ElectricityPack.getFromWatts(
                                1000.0, this.getJoules(itemStack)
                            ),
                            itemStack
                        );
                        par3EntityPlayer.addChatMessage(new ChatComponentText(
                            "Scanned Coordinates: X:" + objectMouseOver.blockX + ", Y:"
                            + objectMouseOver.blockY + ", Z:" + objectMouseOver.blockZ
                        ));
                    } else {
                        par3EntityPlayer.addChatMessage(
                            new ChatComponentText("Radar gun out of electricity!")
                        );
                    }
                }
            }
        }

        return itemStack;
    }

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
        final Block blockId = par3World.getBlock(x, y, z);

        if (blockId == ICBMExplosion.bMachine) {
            final TileEntity tileEntity = par3World.getTileEntity(x, y, z);

            if (tileEntity != null) {
                if (tileEntity instanceof TLauncherControlPanel) {
                    final TLauncherControlPanel missileLauncher
                        = (TLauncherControlPanel) tileEntity;
                    final Vector3 savedCords = this.getSavedCoord(par1ItemStack);

                    if (!savedCords.equals(new Vector3())) {
                        if (missileLauncher.getTarget() == null) {
                            missileLauncher.setTarget(new Vector3());
                        }

                        missileLauncher.getTarget().x = (int) savedCords.x;
                        missileLauncher.getTarget().z = (int) savedCords.z;

                        if (par3World.isRemote) {
                            par2EntityPlayer.addChatMessage(new ChatComponentText(
                                "Coordinate information transfered!"
                            ));
                        }
                    } else if (par3World.isRemote) {
                        par2EntityPlayer.addChatMessage(
                            new ChatComponentText("You must scan a coordinate!")
                        );
                    }
                } else if (tileEntity instanceof TCruiseLauncher) {
                    final TCruiseLauncher missileLauncher2 = (TCruiseLauncher) tileEntity;
                    final Vector3 savedCords = this.getSavedCoord(par1ItemStack);

                    if (!savedCords.equals(new Vector3())) {
                        if (missileLauncher2.getTarget() == null) {
                            missileLauncher2.setTarget(new Vector3());
                        }

                        missileLauncher2.setTarget(
                            new Vector3(savedCords.x, savedCords.y, savedCords.z)
                        );

                        if (par3World.isRemote) {
                            par2EntityPlayer.addChatMessage(new ChatComponentText(
                                "Coordinate information transfered!"
                            ));
                        }
                    } else if (par3World.isRemote) {
                        par2EntityPlayer.addChatMessage(
                            new ChatComponentText("You must scan a coordinate!")
                        );
                    }
                }
            }
        }

        return false;
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

    public double getVoltage(final ItemStack itemStack) {
        return 20.0;
    }

    public double getMaxJoules(final ItemStack itemStack) {
        return 80000.0;
    }
}
