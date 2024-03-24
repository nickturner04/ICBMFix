package icbm.zhapin.zhapin.ex;

import icbm.core.MainBase;
import icbm.zhapin.ICBMExplosion;
import icbm.zhapin.zhapin.ZhaPin;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;
import net.minecraftforge.oredict.ShapedOreRecipe;
import universalelectricity.core.vector.Vector3;
import universalelectricity.prefab.RecipeHelper;

public class ExEmp extends ZhaPin {
    public ExEmp(final String name, final int ID, final int tier) {
        super(name, ID, tier);
    }

    @Override
    public boolean doBaoZha(
        final World worldObj,
        final Vector3 position,
        final Entity explosionSource,
        int radius,
        final int callCount
    ) {
        if (radius < 0) {
            radius = (int) this.getRadius();
        }

        ZhaPin.empSignal.doBaoZha(worldObj, position, explosionSource, radius, callCount);
        ZhaPin.empWave.doBaoZha(worldObj, position, explosionSource, radius, callCount);
        ICBMExplosion.proxy.spawnParticle(
            "shockwave", worldObj, position, 0.0, 0.0, 0.0, 0.0f, 0.0f, 255.0f, 10.0f, 3.0
        );
        return false;
    }

    @Override
    public void init() {
        RecipeHelper.addRecipe(
            (IRecipe) new ShapedOreRecipe(
                this.getItemStack(),
                "RBR",
                "BTB",
                "RBR",
                'T',
                ZhaPin.repulsive.getItemStack(),
                'R',
                Blocks.redstone_block,
                'B',
                "battery"
            ),
            this.getUnlocalizedName(),
            MainBase.CONFIGURATION,
            true
        );
    }

    @Override
    public float getRadius() {
        return 50.0f;
    }

    @Override
    public double getEnergy() {
        return 0.0;
    }
}
