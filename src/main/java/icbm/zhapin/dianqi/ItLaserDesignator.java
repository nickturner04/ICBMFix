package icbm.zhapin.dianqi;

import java.util.ArrayList;
import java.util.List;

import icbm.api.IItemFrequency;
import icbm.core.MainBase;
import icbm.core.di.ItElectricICBM;
import icbm.zhapin.ICBMExplosion;
import icbm.zhapin.ItemUsePacket;
import icbm.zhapin.jiqi.CruiseLauncherGuiPacket;
import icbm.zhapin.jiqi.LauncherControlPanelGuiPacket;
import icbm.zhapin.jiqi.LauncherManager;
import icbm.zhapin.jiqi.TCruiseLauncher;
import icbm.zhapin.jiqi.TLauncherControlPanel;
import icbm.zhapin.jiqi.TLauncherController;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.world.World;
import universalelectricity.core.vector.Vector2;
import universalelectricity.core.vector.Vector3;

public class ItLaserDesignator extends ItElectricICBM implements IItemFrequency {
    public static final int BAN_JING;
    public static final int YONG_DIAN_LIANG = 6000;

    public ItLaserDesignator() {
        super("laserDesignator");
        this.setTextureName("icbm:laserDesignator");
    }

    @Override
    public void addInformation(
        final ItemStack itemStack,
        final EntityPlayer par2EntityPlayer,
        final List par3List,
        final boolean par4
    ) {
        super.addInformation(itemStack, par2EntityPlayer, par3List, par4);

        if (this.getFrequency(itemStack) > 0) {
            par3List.add("Frequency: " + this.getFrequency(itemStack));
        } else {
            par3List.add("Frequency: Not Set");
        }
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

    public int getLauncherCountDown(final ItemStack par1ItemStack) {
        if (par1ItemStack.stackTagCompound == null) {
            return -1;
        }

        return par1ItemStack.stackTagCompound.getInteger("countDown");
    }

    public void setLauncherCountDown(final ItemStack par1ItemStack, final int value) {
        if (par1ItemStack.stackTagCompound == null) {
            par1ItemStack.setTagCompound(new NBTTagCompound());
        }

        par1ItemStack.stackTagCompound.setInteger("countDown", value);
    }

    public int getLauncherCount(final ItemStack par1ItemStack) {
        if (par1ItemStack.stackTagCompound == null) {
            return 0;
        }

        return par1ItemStack.stackTagCompound.getInteger("launcherCount");
    }

    public void setLauncherCount(final ItemStack par1ItemStack, final int value) {
        if (par1ItemStack.stackTagCompound == null) {
            par1ItemStack.setTagCompound(new NBTTagCompound());
        }

        par1ItemStack.stackTagCompound.setInteger("launcherCount", value);
    }

    public int getLauncherDelay(final ItemStack par1ItemStack) {
        if (par1ItemStack.stackTagCompound == null) {
            return 0;
        }

        return par1ItemStack.stackTagCompound.getInteger("launcherDelay");
    }

    public void setLauncherDelay(final ItemStack par1ItemStack, final int value) {
        if (par1ItemStack.stackTagCompound == null) {
            par1ItemStack.setTagCompound(new NBTTagCompound());
        }

        par1ItemStack.stackTagCompound.setInteger("launcherDelay", value);
    }

    public void onUpdate(
        final ItemStack par1ItemStack,
        final World par2World,
        final Entity par3Entity,
        final int par4,
        final boolean par5
    ) {
        super.onUpdate(par1ItemStack, par2World, par3Entity, par4, par5);

        if (!par2World.isRemote) {
            final List<TLauncherController> connectedLaunchers = new ArrayList<>();

            if (this.getLauncherCountDown(par1ItemStack) > 0
                || this.getLauncherCount(par1ItemStack) > 0) {
                final Vector3 position
                    = new Vector3(par3Entity.posX, par3Entity.posY, par3Entity.posZ);
                final List<TLauncherController> launchers
                    = LauncherManager.launchersInArea(
                        new Vector2(
                            position.x - ItLaserDesignator.BAN_JING,
                            position.z - ItLaserDesignator.BAN_JING
                        ),
                        new Vector2(
                            position.x + ItLaserDesignator.BAN_JING,
                            position.z + ItLaserDesignator.BAN_JING
                        )
                    );

                for (final TLauncherController missileLauncher : launchers) {
                    if (missileLauncher != null
                        && missileLauncher.getFrequency()
                            == this.getFrequency(par1ItemStack)
                        && missileLauncher.canLaunch()) {
                        connectedLaunchers.add(missileLauncher);
                    }
                }
            }

            if (this.getLauncherCountDown(par1ItemStack) > 0
                && connectedLaunchers.size() > 0) {
                if (this.getLauncherCountDown(par1ItemStack) % 20 == 0) {
                    ((EntityPlayer) par3Entity)
                        .addChatMessage(new ChatComponentText(
                            "Calling air strike in: "
                            + (int
                            ) Math.floor(this.getLauncherCountDown(par1ItemStack) / 20)
                        ));
                }

                if (this.getLauncherCountDown(par1ItemStack) == 1) {
                    this.setLauncherCount(par1ItemStack, connectedLaunchers.size());
                    this.setLauncherDelay(par1ItemStack, 0);
                    ((EntityPlayer) par3Entity)
                        .addChatMessage(new ChatComponentText("Incoming air strike!"));
                }

                this.setLauncherCountDown(
                    par1ItemStack, this.getLauncherCountDown(par1ItemStack) - 1
                );
            }

            if (this.getLauncherCount(par1ItemStack) > 0
                && this.getLauncherCount(par1ItemStack) <= connectedLaunchers.size()
                && connectedLaunchers.size() > 0) {
                if (this.getLauncherDelay(par1ItemStack) % 40 == 0) {
                    connectedLaunchers.get(this.getLauncherCount(par1ItemStack) - 1)
                        .launch();
                    this.setLauncherCount(
                        par1ItemStack, this.getLauncherCount(par1ItemStack) - 1
                    );
                }

                if (this.getLauncherCount(par1ItemStack) == 0) {
                    this.setLauncherDelay(par1ItemStack, 0);
                    connectedLaunchers.clear();
                }

                this.setLauncherDelay(
                    par1ItemStack, this.getLauncherDelay(par1ItemStack) + 1
                );
            }
        }
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
        if (!par3World.isRemote) {
            final TileEntity tileEntity = par3World.getTileEntity(x, y, z);

            if (tileEntity != null && tileEntity instanceof TLauncherController) {
                final TLauncherController missileLauncher
                    = (TLauncherController) tileEntity;

                if (missileLauncher.getFrequency() > 0) {
                    this.setFrequency(missileLauncher.getFrequency(), par1ItemStack);
                    par2EntityPlayer.addChatMessage(new ChatComponentText(
                        "Laser designator frequency Set: "
                        + this.getFrequency(par1ItemStack)
                    ));
                } else {
                    par2EntityPlayer.addChatMessage(
                        new ChatComponentText("Frequency must be greater than zero.")
                    );
                }
            }
        }

        return false;
    }

    public ItemStack onItemRightClick(
        final ItemStack par1ItemStack,
        final World par2World,
        final EntityPlayer par3EntityPlayer
    ) {
        if (par2World.isRemote) {
            final MovingObjectPosition objectMouseOver = par3EntityPlayer.rayTrace(
                (double) (ItLaserDesignator.BAN_JING * 2), 1.0f
            );

            if (objectMouseOver != null
                && objectMouseOver.typeOfHit == MovingObjectType.BLOCK) {
                final Block block = par2World.getBlock(
                    objectMouseOver.blockX, objectMouseOver.blockY, objectMouseOver.blockZ
                );
                final int blockMetadata = par2World.getBlockMetadata(
                    objectMouseOver.blockX, objectMouseOver.blockY, objectMouseOver.blockZ
                );

                if (this.getLauncherCountDown(par1ItemStack) > 0) {
                    return par1ItemStack;
                }

                if (block == ICBMExplosion.bMachine) {
                    return par1ItemStack;
                }

                final int airStrikeFreq = this.getFrequency(par1ItemStack);

                if (airStrikeFreq > 0) {
                    if (this.getJoules(par1ItemStack) > 6000.0) {
                        final Vector3 position = new Vector3(
                            ((Entity) par3EntityPlayer).posX,
                            ((Entity) par3EntityPlayer).posY,
                            ((Entity) par3EntityPlayer).posZ
                        );
                        final List<TLauncherController> launchers
                            = LauncherManager.launchersInArea(
                                new Vector2(
                                    position.x - ItLaserDesignator.BAN_JING,
                                    position.z - ItLaserDesignator.BAN_JING
                                ),
                                new Vector2(
                                    position.x + ItLaserDesignator.BAN_JING,
                                    position.z + ItLaserDesignator.BAN_JING
                                )
                            );
                        boolean doAirStrike = false;

                        for (final TLauncherController missileLauncher : launchers) {
                            if (missileLauncher != null
                                && missileLauncher.getFrequency() == airStrikeFreq) {
                                if (missileLauncher instanceof TCruiseLauncher) {
                                    missileLauncher.setTarget(new Vector3(
                                        objectMouseOver.blockX,
                                        objectMouseOver.blockY,
                                        objectMouseOver.blockZ
                                    ));

                                    ICBMExplosion.channel.sendToServer(
                                        new CruiseLauncherGuiPacket((TCruiseLauncher
                                        ) missileLauncher)
                                    );
                                } else if (missileLauncher instanceof TLauncherController) {
                                    double previousY = 0.0;

                                    if (missileLauncher.getTarget() != null) {
                                        previousY = missileLauncher.getTarget().y;
                                    }

                                    missileLauncher.setTarget(new Vector3(
                                        objectMouseOver.blockX,
                                        previousY,
                                        objectMouseOver.blockZ
                                    ));

                                    ICBMExplosion.channel.sendToServer(
                                        new LauncherControlPanelGuiPacket(
                                            (TLauncherControlPanel) missileLauncher
                                        )
                                    );
                                }

                                if (missileLauncher.canLaunch()) {
                                    doAirStrike = true;
                                }
                            }
                        }

                        if (doAirStrike
                            && this.getLauncherCountDown(par1ItemStack) >= 0) {
                            ICBMExplosion.channel.sendToServer(new ItemUsePacket(
                                ItemUsePacket.Type.LASER_DESIGNATOR,
                                new Vector3(
                                    objectMouseOver.blockX,
                                    objectMouseOver.blockY,
                                    objectMouseOver.blockZ
                                )
                            ));
                            par3EntityPlayer.addChatMessage(new ChatComponentText(
                                "Calling air strike into designated position!"
                            ));
                        }
                    } else {
                        par3EntityPlayer.addChatMessage(
                            new ChatComponentText("Laser designator out of electricity!")
                        );
                    }
                } else {
                    par3EntityPlayer.addChatMessage(
                        new ChatComponentText("Laser designator frequency not set!")
                    );
                }
            }
        }

        return par1ItemStack;
    }

    public double getVoltage(final ItemStack itemStack) {
        return 30.0;
    }

    public double getMaxJoules(final ItemStack itemStack) {
        return 80000.0;
    }

    static {
        BAN_JING = MainBase.DAO_DAN_ZUI_YUAN;
    }
}
