package icbm.zhapin.zhapin.ex;

import icbm.core.MainBase;
import icbm.zhapin.ESuiPian;
import icbm.zhapin.zhapin.EGrenade;
import icbm.zhapin.zhapin.ZhaPin;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.oredict.ShapedOreRecipe;
import universalelectricity.core.vector.Vector3;
import universalelectricity.prefab.RecipeHelper;

public class ExShrapnel extends ZhaPin {
    public ExShrapnel(final String name, final int ID, final int tier) {
        super(name, ID, tier);
    }

    @Override
    public boolean doBaoZha(
        final World worldObj,
        final Vector3 position,
        final Entity explosionSource,
        final int metadata,
        final int callCount
    ) {
        if (!worldObj.isRemote) {
            int amount = 30;

            if (this.getTier() == 2) {
                amount = 15;
            } else if (this.getID() == ZhaPin.anvil.getID()) {
                amount = 25;
            }

            if (explosionSource instanceof EGrenade) {
                amount /= 2;
                position.y += 0.5;
            }

            final float amountToRotate = (float) (360 / amount);

            for (int i = 0; i < amount; ++i) {
                final float rotationYaw = 0.0f + amountToRotate * i;

                for (int ii = 0; ii < amount; ++ii) {
                    final ESuiPian arrow = new ESuiPian(
                        worldObj,
                        position.x,
                        position.y,
                        position.z,
                        this.getTier() == 2,
                        this.getID() == ZhaPin.anvil.getID()
                    );

                    if (this.getID() != ZhaPin.anvil.getID()) {
                        arrow.arrowCritical = true;
                        arrow.setFire(100);
                    }

                    final float rotationPitch = 0.0f + amountToRotate * ii;
                    arrow.setLocationAndAngles(
                        position.x, position.y, position.z, rotationYaw, rotationPitch
                    );
                    final ESuiPian eSuiPian = arrow;
                    eSuiPian.posX
                        -= MathHelper.cos(rotationYaw / 180.0f * 3.1415927f) * 0.16f;
                    final ESuiPian eSuiPian2 = arrow;
                    eSuiPian2.posY -= 0.10000000149011612;
                    final ESuiPian eSuiPian3 = arrow;
                    eSuiPian3.posZ
                        -= MathHelper.sin(rotationYaw / 180.0f * 3.1415927f) * 0.16f;
                    arrow.setPosition(arrow.posX, arrow.posY, arrow.posZ);
                    arrow.yOffset = 0.0f;
                    arrow.motionX = -MathHelper.sin(rotationYaw / 180.0f * 3.1415927f)
                        * MathHelper.cos(rotationPitch / 180.0f * 3.1415927f);
                    arrow.motionZ = MathHelper.cos(rotationYaw / 180.0f * 3.1415927f)
                        * MathHelper.cos(rotationPitch / 180.0f * 3.1415927f);
                    arrow.motionY = -MathHelper.sin(rotationPitch / 180.0f * 3.1415927f);
                    arrow.setThrowableHeading(
                        arrow.motionX * worldObj.rand.nextFloat(),
                        arrow.motionY * worldObj.rand.nextFloat(),
                        arrow.motionZ * worldObj.rand.nextFloat(),
                        0.5f + 0.7f * worldObj.rand.nextFloat(),
                        1.0f
                    );
                    worldObj.spawnEntityInWorld((Entity) arrow);
                }
            }
        }

        return false;
    }

    @Override
    public void init() {
        if (this.getID() == ZhaPin.shrapnel.getID()) {
            RecipeHelper.addRecipe(
                (IRecipe) new ShapedOreRecipe(
                    this.getItemStack(),
                    new Object[] { "???",
                                   "?@?",
                                   "???",
                                   '@',
                                   ZhaPin.repulsive.getItemStack(),
                                   '?',
                                   Items.arrow }
                ),
                this.getUnlocalizedName(),
                MainBase.CONFIGURATION,
                true
            );
        } else if (this.getID() == ZhaPin.anvil.getID()) {
            RecipeHelper.addRecipe(
                (IRecipe) new ShapedOreRecipe(
                    this.getItemStack(10),
                    new Object[] { "SSS",
                                   "SAS",
                                   "SSS",
                                   'A',
                                   Blocks.anvil,
                                   'S',
                                   ZhaPin.shrapnel.getItemStack() }
                ),
                this.getUnlocalizedName(),
                MainBase.CONFIGURATION,
                true
            );
        } else if (this.getID() == ZhaPin.fragmentation.getID()) {
            RecipeHelper.addRecipe(
                (IRecipe) new ShapedOreRecipe(
                    this.getItemStack(),
                    new Object[] { " @ ",
                                   "@?@",
                                   " @ ",
                                   '?',
                                   ZhaPin.indenciary.getItemStack(),
                                   '@',
                                   ZhaPin.shrapnel.getItemStack() }
                ),
                this.getUnlocalizedName(),
                MainBase.CONFIGURATION,
                true
            );
        }
    }

    @Override
    public float getRadius() {
        return 20.0f;
    }

    @Override
    public double getEnergy() {
        return 0.0;
    }
}
