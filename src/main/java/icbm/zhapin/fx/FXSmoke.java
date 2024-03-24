package icbm.zhapin.fx;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.particle.EntitySmokeFX;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import universalelectricity.core.vector.Vector3;

@SideOnly(Side.CLIENT)
public class FXSmoke extends EntitySmokeFX {
    public FXSmoke(
        final World par1World,
        final Vector3 position,
        final float red,
        final float green,
        final float blue,
        final float scale,
        final double distance
    ) {
        super(par1World, position.x, position.y, position.z, 0.0, 0.0, 0.0, scale);
        ((Entity) this).renderDistanceWeight = distance;
        this.particleRed = red;
        this.particleBlue = blue;
        this.particleGreen = green;
        final float colorVarient = (float) (Math.random() * 0.9000000119209289);
        this.particleRed *= colorVarient;
        this.particleBlue *= colorVarient;
        this.particleGreen *= colorVarient;
    }

    public FXSmoke setAge(final int age) {
        this.particleMaxAge = age;
        return this;
    }
}
