package icbm.zhapin.render;

import java.util.Random;

import calclavia.lib.render.CalclaviaRenderHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import icbm.core.di.MICBM;
import icbm.zhapin.zhapin.EExplosion;
import icbm.zhapin.zhapin.ZhaPin;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.Sphere;

@SideOnly(Side.CLIENT)
public class RZhaPin extends Render {
    public Random random;

    public RZhaPin() {
        this.random = new Random();
    }

    public void doRender(
        final Entity entity,
        final double x,
        final double y,
        final double z,
        final float par8,
        final float par9
    ) {
        final EExplosion eZhaPin = (EExplosion) entity;

        if (eZhaPin.haoMa == ZhaPin.redMatter.getID()) {
            final Tessellator tessellator = Tessellator.instance;
            GL11.glPushMatrix();
            GL11.glTranslatef((float) x, (float) y, (float) z);
            CalclaviaRenderHelper.enableBlending();
            CalclaviaRenderHelper.disableLighting();
            GL11.glColor4f(0.0f, 0.0f, 0.0f, 0.9f);
            final Sphere sphere = new Sphere();
            sphere.draw(5.0f, 32, 32);
            CalclaviaRenderHelper.enableLighting();
            CalclaviaRenderHelper.disableBlending();
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            GL11.glDepthMask(false);
            CalclaviaRenderHelper.enableBlending();
            CalclaviaRenderHelper.disableLighting();
            GL11.glTranslated(x, y, z);
            GL11.glRotatef((float) entity.ticksExisted, 0.0f, 1.0f, 0.0f);
            final float size = 10.0f;
            final int textureSize = 50;
            final float size2 = size * 5.0f;
            final float float_sizeMinus0_01 = textureSize - 0.01f;
            final float x2 = (textureSize + 0.0f) / size2;
            final float x3 = (textureSize + float_sizeMinus0_01) / size2;
            final float x4 = (textureSize + 0.0f) / size2;
            final float x5 = (textureSize + float_sizeMinus0_01) / size2;
            Minecraft.getMinecraft().renderEngine.bindTexture(
                new ResourceLocation("icbm", "textures/blackhole.png")
            );
            tessellator.startDrawingQuads();
            tessellator.setBrightness(240);
            tessellator.setColorRGBA_F(1.0f, 1.0f, 1.0f, 1.0f);
            tessellator.addVertexWithUV(
                (double) (-size), 0.0, (double) (-size), (double) x3, (double) x5
            );
            tessellator.addVertexWithUV(
                (double) (-size), 0.0, (double) size, (double) x3, (double) x4
            );
            tessellator.addVertexWithUV(
                (double) size, 0.0, (double) size, (double) x2, (double) x4
            );
            tessellator.addVertexWithUV(
                (double) size, 0.0, (double) (-size), (double) x2, (double) x5
            );
            tessellator.draw();
            CalclaviaRenderHelper.enableLighting();
            CalclaviaRenderHelper.disableBlending();
            GL11.glDepthMask(true);
            GL11.glPopMatrix();
            float par10;

            for (par10 = (float) entity.ticksExisted; par10 > 200.0f; par10 -= 100.0f) {}

            RenderHelper.disableStandardItemLighting();
            final float var41 = (5.0f + par10) / 200.0f;
            float var42 = 0.0f;

            if (var41 > 0.8f) {
                var42 = (var41 - 0.8f) / 0.2f;
            }

            final Random rand = new Random(432L);
            GL11.glPushMatrix();
            GL11.glTranslatef((float) x, (float) y, (float) z);
            GL11.glDisable(3553);
            GL11.glShadeModel(7425);
            GL11.glEnable(3042);
            GL11.glBlendFunc(770, 1);
            GL11.glDisable(3008);
            GL11.glEnable(2884);
            GL11.glDepthMask(false);
            GL11.glPushMatrix();
            GL11.glTranslatef(0.0f, -1.0f, -2.0f);

            for (int i1 = 0; i1 < (var41 + var41 * var41) / 2.0f * 60.0f; ++i1) {
                GL11.glRotatef(rand.nextFloat() * 360.0f, 1.0f, 0.0f, 0.0f);
                GL11.glRotatef(rand.nextFloat() * 360.0f, 0.0f, 1.0f, 0.0f);
                GL11.glRotatef(rand.nextFloat() * 360.0f, 0.0f, 0.0f, 1.0f);
                GL11.glRotatef(rand.nextFloat() * 360.0f, 1.0f, 0.0f, 0.0f);
                GL11.glRotatef(rand.nextFloat() * 360.0f, 0.0f, 1.0f, 0.0f);
                GL11.glRotatef(
                    rand.nextFloat() * 360.0f + var41 * 90.0f, 0.0f, 0.0f, 1.0f
                );
                tessellator.startDrawing(6);
                final float var43 = rand.nextFloat() * 20.0f + 5.0f + var42 * 10.0f;
                final float var44 = rand.nextFloat() * 2.0f + 1.0f + var42 * 2.0f;
                tessellator.setColorRGBA_I(16777215, (int) (255.0f * (1.0f - var42)));
                tessellator.addVertex(0.0, 0.0, 0.0);
                tessellator.setColorRGBA_I(0, 0);
                tessellator.addVertex(
                    -0.866 * var44, (double) var43, (double) (-0.5f * var44)
                );
                tessellator.addVertex(
                    0.866 * var44, (double) var43, (double) (-0.5f * var44)
                );
                tessellator.addVertex(0.0, (double) var43, (double) (1.0f * var44));
                tessellator.addVertex(
                    -0.866 * var44, (double) var43, (double) (-0.5f * var44)
                );
                tessellator.draw();
            }

            GL11.glPopMatrix();
            GL11.glDepthMask(true);
            GL11.glDisable(2884);
            GL11.glDisable(3042);
            GL11.glShadeModel(7424);
            GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
            GL11.glEnable(3553);
            GL11.glEnable(3008);
            RenderHelper.enableStandardItemLighting();
            GL11.glPopMatrix();
        } else {
            final Object[] data = ZhaPin.list[eZhaPin.haoMa].getRenderData();

            if (data != null) {
                GL11.glPushMatrix();
                GL11.glTranslatef((float) x, (float) y + 1.0f, (float) z);
                GL11.glRotatef(eZhaPin.rotationPitch, 0.0f, 0.0f, 1.0f);
                this.bindTexture((ResourceLocation) data[1]);
                ((MICBM) data[0])
                    .render(
                        (Entity) eZhaPin,
                        (float) x,
                        (float) y,
                        (float) z,
                        par8,
                        par9,
                        0.0625f
                    );
                GL11.glPopMatrix();
            }
        }
    }

    public void drawCircle(
        final double x, final double y, final double radius, final double accuracy
    ) {
        GL11.glDisable(3553);
        final double da = Math.min(2.0 * Math.asin(1.0 / radius) / accuracy, 10000.0);
        GL11.glBegin(6);
        GL11.glVertex2d(x, y);

        for (double a = 0.0; a <= 6.283185307179586; a += da) {
            GL11.glVertex2d(x + Math.cos(a) * radius, y + Math.sin(a) * radius);
        }

        GL11.glVertex2d(x + radius, y);
        GL11.glEnd();
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
        return null;
    }
}
