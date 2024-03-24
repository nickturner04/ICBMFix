package icbm.zhapin.render;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import icbm.zhapin.ESuiPian;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RShrapnel extends Render {
    public void renderArrow(
        final ESuiPian suiPian,
        final double par2,
        final double par4,
        final double par6,
        final float par8,
        final float par9
    ) {
        if (suiPian.isAnvil) {
            GL11.glPushMatrix();
            GL11.glTranslatef((float) par2, (float) par4, (float) par6);
            this.bindTexture(TextureMap.locationBlocksTexture);
            // yay anvilcraft
            final Block block = Blocks.anvil;
            final World world = suiPian.worldObj;
            GL11.glDisable(2896);
            this.field_147909_c.blockAccess = (IBlockAccess) world;
            final Tessellator var12 = Tessellator.instance;
            var12.startDrawingQuads();
            var12.setTranslation(
                (double) (-MathHelper.floor_double(suiPian.posX) - 0.5f),
                (double) (-MathHelper.floor_double(suiPian.posY) - 0.5f),
                (double) (-MathHelper.floor_double(suiPian.posZ) - 0.5f)
            );
            this.field_147909_c.renderBlockByRenderType(
                block,
                MathHelper.floor_double(suiPian.posX),
                MathHelper.floor_double(suiPian.posY),
                MathHelper.floor_double(suiPian.posZ)
            );
            var12.setTranslation(0.0, 0.0, 0.0);
            var12.draw();
            GL11.glEnable(2896);
            GL11.glPopMatrix();
        } else {
            this.bindTexture(new ResourceLocation("icbm", "textures/models/fragment.png")
            );
            GL11.glPushMatrix();
            GL11.glTranslatef((float) par2, (float) par4, (float) par6);
            GL11.glRotatef(
                suiPian.prevRotationYaw
                    + (suiPian.rotationYaw - suiPian.prevRotationYaw) * par9 - 90.0f,
                0.0f,
                1.0f,
                0.0f
            );
            GL11.glRotatef(
                suiPian.prevRotationPitch
                    + (suiPian.rotationPitch - suiPian.prevRotationPitch) * par9,
                0.0f,
                0.0f,
                1.0f
            );
            final Tessellator var13 = Tessellator.instance;
            final byte var14 = 0;
            final float var15 = 0.0f;
            final float var16 = 0.5f;
            final float var17 = (0 + var14 * 10) / 32.0f;
            final float var18 = (5 + var14 * 10) / 32.0f;
            final float var19 = 0.0f;
            final float var20 = 0.15625f;
            final float var21 = (5 + var14 * 10) / 32.0f;
            final float var22 = (10 + var14 * 10) / 32.0f;
            final float var23 = 0.05625f;
            GL11.glEnable(32826);
            final float var24 = suiPian.arrowShake - par9;

            if (var24 > 0.0f) {
                final float var25 = -MathHelper.sin(var24 * 3.0f) * var24;
                GL11.glRotatef(var25, 0.0f, 0.0f, 1.0f);
            }

            GL11.glRotatef(45.0f, 1.0f, 0.0f, 0.0f);
            GL11.glScalef(var23, var23, var23);
            GL11.glTranslatef(-4.0f, 0.0f, 0.0f);
            GL11.glNormal3f(var23, 0.0f, 0.0f);
            var13.startDrawingQuads();
            var13.addVertexWithUV(-7.0, -2.0, -2.0, (double) var19, (double) var21);
            var13.addVertexWithUV(-7.0, -2.0, 2.0, (double) var20, (double) var21);
            var13.addVertexWithUV(-7.0, 2.0, 2.0, (double) var20, (double) var22);
            var13.addVertexWithUV(-7.0, 2.0, -2.0, (double) var19, (double) var22);
            var13.draw();
            GL11.glNormal3f(-var23, 0.0f, 0.0f);
            var13.startDrawingQuads();
            var13.addVertexWithUV(-7.0, 2.0, -2.0, (double) var19, (double) var21);
            var13.addVertexWithUV(-7.0, 2.0, 2.0, (double) var20, (double) var21);
            var13.addVertexWithUV(-7.0, -2.0, 2.0, (double) var20, (double) var22);
            var13.addVertexWithUV(-7.0, -2.0, -2.0, (double) var19, (double) var22);
            var13.draw();

            for (int var26 = 0; var26 < 4; ++var26) {
                GL11.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
                GL11.glNormal3f(0.0f, 0.0f, var23);
                var13.startDrawingQuads();
                var13.addVertexWithUV(-8.0, -2.0, 0.0, (double) var15, (double) var17);
                var13.addVertexWithUV(8.0, -2.0, 0.0, (double) var16, (double) var17);
                var13.addVertexWithUV(8.0, 2.0, 0.0, (double) var16, (double) var18);
                var13.addVertexWithUV(-8.0, 2.0, 0.0, (double) var15, (double) var18);
                var13.draw();
            }

            GL11.glDisable(32826);
            GL11.glPopMatrix();
        }
    }

    public void doRender(
        final Entity par1Entity,
        final double par2,
        final double par4,
        final double par6,
        final float par8,
        final float par9
    ) {
        this.renderArrow((ESuiPian) par1Entity, par2, par4, par6, par8, par9);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
        return null;
    }
}
