package icbm.zhapin.fx;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.relauncher.ReflectionHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.particle.EntityPortalFX;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import universalelectricity.core.vector.Vector3;

@SideOnly(Side.CLIENT)
public class FXPortal extends EntityPortalFX {
    public FXPortal(
        final World par1World,
        final Vector3 position,
        final float red,
        final float green,
        final float blue,
        final float scale,
        final double distance
    ) {
        super(par1World, position.x, position.y, position.z, 0.0, 0.0, 0.0);
        this.particleScale = scale;
        this.portalParticleScale = scale;
        ((Entity) this).renderDistanceWeight = distance;
    }
}
