package icbm.zhapin.fx;

import calclavia.lib.render.CalclaviaRenderHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.Sphere;
import universalelectricity.core.vector.Vector3;

@SideOnly(Side.CLIENT)
public class FXShockwave extends EntityFX {
    public FXShockwave(
        final World par1World,
        final Vector3 position,
        final float par8,
        final float par10,
        final float par12,
        final double distance
    ) {
        this(par1World, position, par8, par10, par12, 1.0f, distance);
    }

    public FXShockwave(
        final World par1World,
        final Vector3 position,
        final float r,
        final float g,
        final float b,
        final float size,
        final double distance
    ) {
        super(par1World, position.x, position.y, position.z, 0.0, 0.0, 0.0);
        super.particleRed = r;
        super.particleGreen = g;
        super.particleBlue = b;
        super.particleScale = size;
        super.particleMaxAge = (int) (10.0 / (Math.random() * 0.8 + 0.2));
        super.particleMaxAge *= (int) size;
        ((Entity) this).renderDistanceWeight = distance;
        ((Entity) this).noClip = false;
    }

    @Override
    public void renderParticle(
        final Tessellator tessellator,
        final float par2,
        final float par3,
        final float par4,
        final float par5,
        final float par6,
        final float par7
    ) {
        GL11.glPushMatrix();
        GL11.glTranslated(
            ((Entity) this).posX, ((Entity) this).posY, ((Entity) this).posZ
        );
        CalclaviaRenderHelper.enableBlending();
        CalclaviaRenderHelper.disableLighting();
        GL11.glColor4f(
            super.particleRed / 255.0f,
            super.particleGreen / 255.0f,
            super.particleBlue / 255.0f,
            0.5f
        );
        final Sphere sphere = new Sphere();
        sphere.draw(super.particleScale, 32, 32);
        CalclaviaRenderHelper.enableLighting();
        CalclaviaRenderHelper.disableBlending();
        GL11.glPopMatrix();
    }

    @Override
    public void onUpdate() {
        ((Entity) this).prevPosX = ((Entity) this).posX;
        ((Entity) this).prevPosY = ((Entity) this).posY;
        ((Entity) this).prevPosZ = ((Entity) this).posZ;
        ++super.particleScale;

        if (super.particleAge++ >= super.particleMaxAge) {
            this.setDead();
        }
    }
}
