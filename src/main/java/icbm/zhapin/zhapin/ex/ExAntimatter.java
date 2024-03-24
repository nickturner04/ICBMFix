package icbm.zhapin.zhapin.ex;

import icbm.core.MainBase;
import icbm.zhapin.zhapin.EExplosion;
import icbm.zhapin.zhapin.ZhaPin;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.oredict.ShapedOreRecipe;
import universalelectricity.core.vector.Vector3;
import universalelectricity.prefab.RecipeHelper;

public class ExAntimatter extends ZhaPin {
    public boolean destroyBedrock;

    public ExAntimatter(final String name, final int ID, final int tier) {
        super(name, ID, tier);
        this.destroyBedrock = true;
        this.setFuse(300);
        MainBase.CONFIGURATION.load();
        this.destroyBedrock
            = MainBase.CONFIGURATION
                  .get("general", "Antimatter Destroy Bedrock", this.destroyBedrock)
                  .getBoolean(this.destroyBedrock);
        MainBase.CONFIGURATION.save();
    }

    @Override
    public void baoZhaQian(
        final World worldObj, final Vector3 position, final Entity explosionSource
    ) {
        super.baoZhaQian(worldObj, position, explosionSource);
        worldObj.playSoundEffect(
            position.x,
            position.y,
            position.z,
            "icbm:antimatter",
            7.0f,
            (float) (worldObj.rand.nextFloat() * 0.1 + 0.8999999761581421)
        );
        explosionSource.posY += 5.0;
        this.doDamageEntities(worldObj, position, this.getRadius() * 2.0f, 2.1474836E9f);
    }

    @Override
    public boolean doBaoZha(
        final World worldObj,
        final Vector3 position,
        final Entity explosionSource,
        final int callCount
    ) {
        if (!worldObj.isRemote && callCount == 1) {
            for (int x = (int) (-this.getRadius()); x < this.getRadius(); ++x) {
                for (int y = (int) (-this.getRadius()); y < this.getRadius(); ++y) {
                    for (int z = (int) (-this.getRadius()); z < this.getRadius(); ++z) {
                        final Vector3 targetPosition
                            = Vector3.add(position, new Vector3(x, y, z));
                        final double dist = position.distanceTo(targetPosition);

                        if (dist < this.getRadius()) {
                            final Block block
                                = targetPosition.getBlock((IBlockAccess) worldObj);

                            if (block != Blocks.air) {
                                if (block != Blocks.bedrock || this.destroyBedrock) {
                                    if (dist < this.getRadius() - 1.0f
                                        || worldObj.rand.nextFloat() > 0.7) {
                                        targetPosition.setBlock(worldObj, Blocks.air);
                                    }
                                }
                            }
                        }
                    }
                }
            }

            return false;
        }

        return callCount <= this.getRadius();
    }

    @Override
    public void baoZhaHou(
        final World worldObj, final Vector3 position, final Entity explosionSource
    ) {
        super.baoZhaHou(worldObj, position, explosionSource);
        this.doDamageEntities(worldObj, position, this.getRadius() * 2.0f, 2.1474836E9f);
    }

    @Override
    protected boolean onDamageEntity(final Entity entity) {
        if (entity instanceof EExplosion
            && ((EExplosion) entity).haoMa == ZhaPin.redMatter.getID()) {
            entity.setDead();
            return true;
        }

        return false;
    }

    @Override
    public void
    onYinZha(final World worldObj, final Vector3 position, final int fuseTicks) {
        super.onYinZha(worldObj, position, fuseTicks);

        if (fuseTicks % 25 == 0) {
            worldObj.playSoundEffect(
                position.x, position.y, position.z, "icbm:alarm", 4.0f, 1.0f
            );
        }
    }

    @Override
    public void init() {
        RecipeHelper.addRecipe(
            (IRecipe) new ShapedOreRecipe(
                this.getItemStack(),
                new Object[] { "AAA",
                               "AEA",
                               "AAA",
                               'E',
                               ZhaPin.nuclear.getItemStack(),
                               'A',
                               "antimatterGram" }
            ),
            this.getUnlocalizedName(),
            MainBase.CONFIGURATION,
            true
        );
    }

    @Override
    protected int proceduralInterval() {
        return 1;
    }

    @Override
    public float getRadius() {
        return 40.0f;
    }

    @Override
    public double getEnergy() {
        return 1000000.0;
    }
}
