package icbm.zhapin.fx;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;
import universalelectricity.core.vector.Vector3;

@SideOnly(Side.CLIENT)
public class FXAntimatter extends EntityFX {
    float antimatterParticleScale;

    public FXAntimatter(
        final World par1World,
        final Vector3 position,
        final double par8,
        final double par10,
        final double par12,
        final double distance
    ) {
        this(par1World, position, par8, par10, par12, 1.0f, distance);
    }

    public FXAntimatter(
        final World par1World,
        final Vector3 position,
        final double par8,
        final double par10,
        final double par12,
        final float par14,
        final double distance
    ) {
        super(par1World, position.x, position.y, position.z, 0.0, 0.0, 0.0);
        ((Entity) this).motionX *= 0.10000000149011612;
        ((Entity) this).motionY *= 0.10000000149011612;
        ((Entity) this).motionZ *= 0.10000000149011612;
        ((Entity) this).motionX += par8;
        ((Entity) this).motionY += par10;
        ((Entity) this).motionZ += par12;
        final float particleRed = (float) (Math.random() * 0.30000001192092896);
        super.particleBlue = particleRed;
        super.particleGreen = particleRed;
        super.particleRed = particleRed;
        super.particleScale *= 0.75f;
        super.particleScale *= par14;
        this.antimatterParticleScale = super.particleScale;
        super.particleMaxAge = (int) (10.0 / (Math.random() * 0.8 + 0.2));
        super.particleMaxAge *= (int) par14;
        ((Entity) this).renderDistanceWeight = distance;
        ((Entity) this).noClip = false;
    }

    public void renderParticle(
        final Tessellator tessellator,
        final float par2,
        final float par3,
        final float par4,
        final float par5,
        final float par6,
        final float par7
    ) {
        float agescale = 0.0f;
        agescale = (float) (super.particleAge / (super.particleMaxAge / 2));

        if (agescale > 1.0f) {
            agescale = 2.0f - agescale;
        }

        super.particleScale = this.antimatterParticleScale * agescale;
        tessellator.draw();
        GL11.glPushMatrix();
        GL11.glDepthMask(false);
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 1);
        Minecraft.getMinecraft().renderEngine.bindTexture(
            new ResourceLocation("icbm", "textures/antimatter.png")
        );
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 0.75f);
        final float f10 = 0.5f * super.particleScale;
        final float f11 = (float
        ) (((Entity) this).prevPosX
           + (((Entity) this).posX - ((Entity) this).prevPosX) * par2
           - EntityFX.interpPosX);
        final float f12 = (float
        ) (((Entity) this).prevPosY
           + (((Entity) this).posY - ((Entity) this).prevPosY) * par2
           - EntityFX.interpPosY);
        final float f13 = (float
        ) (((Entity) this).prevPosZ
           + (((Entity) this).posZ - ((Entity) this).prevPosZ) * par2
           - EntityFX.interpPosZ);
        tessellator.startDrawingQuads();
        tessellator.setBrightness(240);
        tessellator.setColorRGBA_F(
            super.particleRed, super.particleGreen, super.particleBlue, 0.5f
        );
        tessellator.addVertexWithUV(
            (double) (f11 - par3 * f10 - par5 * f10),
            (double) (f12 - par4 * f10),
            (double) (f13 - par5 * f10 - par7 * f10),
            0.0,
            1.0
        );
        tessellator.addVertexWithUV(
            (double) (f11 - par3 * f10 + par5 * f10),
            (double) (f12 + par4 * f10),
            (double) (f13 - par5 * f10 + par7 * f10),
            1.0,
            1.0
        );
        tessellator.addVertexWithUV(
            (double) (f11 + par3 * f10 + par5 * f10),
            (double) (f12 + par4 * f10),
            (double) (f13 + par5 * f10 + par7 * f10),
            1.0,
            0.0
        );
        tessellator.addVertexWithUV(
            (double) (f11 + par3 * f10 - par5 * f10),
            (double) (f12 - par4 * f10),
            (double) (f13 + par5 * f10 - par7 * f10),
            0.0,
            0.0
        );
        tessellator.draw();
        GL11.glDisable(3042);
        GL11.glDepthMask(true);
        GL11.glPopMatrix();
        FMLClientHandler.instance().getClient().renderEngine.bindTexture(
            new ResourceLocation("icbm", "textures/antimatter.png")
        );
        tessellator.startDrawingQuads();
    }

    public void onUpdate() {
        ((Entity) this).prevPosX = ((Entity) this).posX;
        ((Entity) this).prevPosY = ((Entity) this).posY;
        ((Entity) this).prevPosZ = ((Entity) this).posZ;

        if (super.particleAge++ >= super.particleMaxAge) {
            this.setDead();
        }
    }
}
