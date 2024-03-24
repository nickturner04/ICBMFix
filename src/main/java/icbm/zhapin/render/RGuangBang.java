package icbm.zhapin.render;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import icbm.zhapin.ELightBeam;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RGuangBang extends Render {
    @Override
    public void doRender(
        final Entity par1Entity,
        final double x,
        final double y,
        final double z,
        final float f,
        final float f1
    ) {
        if (super.renderManager == null) {
            this.setRenderManager(RenderManager.instance);
        }

        final ELightBeam entity = (ELightBeam) par1Entity;
        final Tessellator tessellator = Tessellator.instance;
        GL11.glDisable(3553);
        GL11.glDisable(2896);
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 1);
        final double[] ad = new double[80];
        final double[] ad2 = new double[80];
        final double d3 = 0.0;
        final double d4 = 0.0;

        for (int j = 7; j >= 0; --j) {
            ad[j] = d3;
            ad2[j] = d4;
        }

        for (int i = 0; i < 4; ++i) {
            for (int ii = 0; ii < 3; ++ii) {
                int l = 7;
                int i2 = 0;

                if (ii > 0) {
                    l = 7 - ii;
                }

                if (ii > 0) {
                    i2 = l - 2;
                }

                final double d5 = ad[l] - d3;
                final double d6 = ad2[l] - d4;

                for (int iii = l; iii >= i2; --iii) {
                    final double d7 = d5;
                    final double d8 = d6;
                    tessellator.startDrawing(5);
                    tessellator.setColorRGBA_F(
                        entity.red, entity.green, entity.blue, 10.0f
                    );
                    double d9 = 0.1 + i * 0.2;

                    if (ii == 0) {
                        d9 *= iii * 0.1 + 1.0;
                    }

                    double d10 = 0.1 + i * 0.2;

                    if (ii == 0) {
                        d10 *= (iii - 1) * 0.1 + 1.0;
                    }

                    for (int iiii = 0; iiii < 5; ++iiii) {
                        double d11 = x + 0.5 - d9;
                        double d12 = z + 0.5 - d9;

                        if (iiii == 1 || iiii == 2) {
                            d11 += d9 * 2.0;
                        }

                        if (iiii == 2 || iiii == 3) {
                            d12 += d9 * 2.0;
                        }

                        double d13 = x + 0.5 - d10;
                        double d14 = z + 0.5 - d10;

                        if (iiii == 1 || iiii == 2) {
                            d13 += d10 * 2.0;
                        }

                        if (iiii == 2 || iiii == 3) {
                            d14 += d10 * 2.0;
                        }

                        tessellator.addVertex(d13 + d5, y + iii * 16, d14 + d6);
                        tessellator.addVertex(d11 + d7, y + (iii + 1) * 16, d12 + d8);
                    }

                    tessellator.draw();
                }
            }
        }

        GL11.glDisable(3042);
        GL11.glEnable(2896);
        GL11.glEnable(3553);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
        return null;
    }
}
