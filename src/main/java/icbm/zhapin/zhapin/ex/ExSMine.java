package icbm.zhapin.zhapin.ex;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import icbm.core.MainBase;
import icbm.zhapin.ESuiPian;
import icbm.zhapin.muoxing.jiqi.MSMine;
import icbm.zhapin.zhapin.ZhaPin;
import net.minecraft.entity.Entity;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.oredict.ShapedOreRecipe;
import universalelectricity.core.vector.Vector3;
import universalelectricity.prefab.RecipeHelper;

public class ExSMine extends ZhaPin {
    public ExSMine(final String name, final int ID, final int tier) {
        super(name, ID, tier);
        super.isMobile = true;
        this.setFuse(40);
    }

    @Override
    public void baoZhaQian(
        final World worldObj, final Vector3 position, final Entity explosionSource
    ) {
        if (!worldObj.isRemote) {
            worldObj.createExplosion(
                explosionSource, position.x, position.y, position.z, 1.5f, true
            );
        }

        explosionSource.motionX = -0.125 + 0.25 * worldObj.rand.nextFloat();
        explosionSource.motionY = 0.6 + 0.3 * worldObj.rand.nextFloat();
        explosionSource.motionZ = -0.125 + 0.25 * worldObj.rand.nextFloat();
    }

    @Override
    public boolean doBaoZha(
        final World worldObj,
        final Vector3 position,
        final Entity explosionSource,
        final int explosionMetadata,
        final int callCount
    ) {
        explosionSource.motionY -= 0.03;
        explosionSource.rotationPitch += 1.0f * worldObj.rand.nextFloat();

        if (callCount < 40 && !explosionSource.isCollided) {
            return true;
        }

        if (callCount >= 40 && callCount % 2 == 0 && !worldObj.isRemote) {
            final int amount = 5;
            final float amountToRotate = (float) (360 / amount);

            for (int i = 0; i < amount; ++i) {
                final float rotationYaw = 0.0f + amountToRotate * i;

                for (int ii = 0; ii < amount; ++ii) {
                    final ESuiPian arrow = new ESuiPian(
                        worldObj, position.x, position.y, position.z, true, false
                    );
                    arrow.arrowCritical = true;
                    arrow.setFire(60);
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
                        1.5f + 0.7f * worldObj.rand.nextFloat(),
                        2.0f
                    );
                    worldObj.spawnEntityInWorld((Entity) arrow);
                }
            }
        }

        return callCount < 60;
    }

    public int proceduralInterval() {
        return 1;
    }

    @Override
    public void
    onYinZha(final World worldObj, final Vector3 position, final int fuseTicks) {}

    @Override
    public void init() {
        RecipeHelper.addRecipe(
            (IRecipe) new ShapedOreRecipe(
                this.getItemStack(),
                new Object[] { "S",
                               "L",
                               "R",
                               'S',
                               ZhaPin.fragmentation.getItemStack(),
                               'L',
                               ZhaPin.attractive.getItemStack(),
                               'R',
                               ZhaPin.repulsive.getItemStack() }
            ),
            this.getUnlocalizedName(),
            MainBase.CONFIGURATION,
            true
        );
    }

    @SideOnly(Side.CLIENT)
    @Override
    public Object[] getRenderData() {
        return new Object[] {
            MSMine.INSTANCE, new ResourceLocation("icbm", "textures/models/s-mine.png")
        };
    }

    @Override
    public float getRadius() {
        return 20.0f;
    }

    @Override
    public double getEnergy() {
        return 2000.0;
    }
}
