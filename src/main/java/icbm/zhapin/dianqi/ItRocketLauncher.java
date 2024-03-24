package icbm.zhapin.dianqi;

import icbm.core.di.ItElectricICBM;
import icbm.zhapin.ICBMExplosion;
import icbm.zhapin.daodan.EMissile;
import icbm.zhapin.daodan.ItMissile;
import icbm.zhapin.daodan.ItModuleMissile;
import icbm.zhapin.daodan.MissileBase;
import icbm.zhapin.zhapin.ZhaPin;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import universalelectricity.core.electricity.ElectricityPack;
import universalelectricity.core.vector.Vector3;

public class ItRocketLauncher extends ItElectricICBM {
    public ItRocketLauncher() {
        super("rocketLauncher");
    }

    public EnumAction getItemUseAction(final ItemStack par1ItemStack) {
        return EnumAction.bow;
    }

    public ItemStack onItemRightClick(
        final ItemStack itemStack, final World world, final EntityPlayer player
    ) {
        if (!world.isRemote && this.getJoules(itemStack) >= 5000.0) {
            for (int i = 0; i < player.inventory.getSizeInventory(); ++i) {
                final ItemStack inventoryStack = player.inventory.getStackInSlot(i);

                if (inventoryStack != null
                    && inventoryStack.getItem() instanceof ItMissile) {
                    int haoMa = inventoryStack.getItemDamage();

                    if (inventoryStack.getItem() instanceof ItModuleMissile) {
                        haoMa += 100;
                    }

                    final MissileBase daoDan = MissileBase.list[haoMa];

                    if (daoDan != null
                        && !ICBMExplosion.shiBaoHu(
                            world,
                            new Vector3((Entity) player),
                            ZhaPin.ZhaPinType.DAO_DAN,
                            haoMa
                        )) {
                        if (daoDan.getTier() <= 2 && daoDan.isCruise()) {
                            final Vector3 diDian = Vector3.add(
                                new Vector3((Entity) player), new Vector3(0.0, 0.5, 0.0)
                            );
                            final Vector3 kan = new Vector3(player.getLook(1.0f));
                            final Vector3 kaiShiDiDian
                                = Vector3.add(diDian, Vector3.multiply(kan, 1.1));
                            final Vector3 muBiao
                                = Vector3.add(diDian, Vector3.multiply(kan, 100.0));
                            final EMissile eDaoDan = new EMissile(
                                world,
                                kaiShiDiDian,
                                daoDan.getID(),
                                ((Entity) player).rotationYaw,
                                ((Entity) player).rotationPitch
                            );
                            world.spawnEntityInWorld((Entity) eDaoDan);
                            eDaoDan.launch(muBiao);

                            if (!player.capabilities.isCreativeMode) {
                                player.inventory.setInventorySlotContents(
                                    i, (ItemStack) null
                                );
                            }

                            this.onProvide(
                                ElectricityPack.getFromWatts(
                                    5000.0, this.getJoules(itemStack)
                                ),
                                itemStack
                            );
                            return itemStack;
                        }
                    } else {
                        player.addChatMessage(
                            new ChatComponentText("Region being is protected.")
                        );
                    }
                }
            }
        }

        return itemStack;
    }

    public double getVoltage(final ItemStack itemStack) {
        return 25.0;
    }

    public double getMaxJoules(final ItemStack itemStack) {
        return 100000.0;
    }
}
