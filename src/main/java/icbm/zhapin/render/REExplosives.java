package icbm.zhapin.render;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import icbm.core.di.MICBM;
import icbm.zhapin.ICBMExplosion;
import icbm.zhapin.zhapin.EExplosive;
import icbm.zhapin.zhapin.ZhaPin;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class REExplosives extends Render {
    private RenderBlocks blockRenderer;

    public REExplosives() {
        this.blockRenderer = new RenderBlocks();
        super.shadowSize = 0.5f;
    }

    @Override
    public void doRender(
        final Entity par1Entity,
        final double x,
        final double y,
        final double z,
        final float par8,
        final float par9
    ) {
        final EExplosive entityExplosive = (EExplosive) par1Entity;
        final Object[] data = ZhaPin.list[entityExplosive.haoMa].getRenderData();

        if (data != null) {
            GL11.glPushMatrix();
            GL11.glTranslatef((float) x, (float) y + 1.0f, (float) z);
            this.bindTexture((ResourceLocation) data[1]);
            GL11.glRotatef(180.0f, 0.0f, 0.0f, 1.0f);
            ((MICBM) data[0])
                .render(
                    (Entity) entityExplosive,
                    (float) x,
                    (float) y,
                    (float) z,
                    par8,
                    par9,
                    0.0625f
                );
            GL11.glPopMatrix();
        } else {
            GL11.glPushMatrix();
            GL11.glTranslatef((float) x, (float) y, (float) z);

            if (entityExplosive.fuse - par9 + 1.0f < 10.0f) {
                float f2 = 1.0f - (entityExplosive.fuse - par9 + 1.0f) / 10.0f;

                if (f2 < 0.0f) {
                    f2 = 0.0f;
                }

                if (f2 > 1.0f) {
                    f2 = 1.0f;
                }

                f2 *= f2;
                f2 *= f2;
                final float f3 = 1.0f + f2 * 0.3f;
                GL11.glScalef(f3, f3, f3);
            }

            float f2 = (1.0f - (entityExplosive.fuse - par9 + 1.0f) / 100.0f) * 0.8f;
            this.bindTexture(TextureMap.locationBlocksTexture);
            this.blockRenderer.renderBlockAsItem(
                ICBMExplosion.bExplosives,
                entityExplosive.haoMa,
                entityExplosive.getBrightness(par9)
            );

            if (entityExplosive.fuse / 5 % 2 == 0) {
                GL11.glDisable(3553);
                GL11.glDisable(2896);
                GL11.glEnable(3042);
                GL11.glBlendFunc(770, 772);
                GL11.glColor4f(1.0f, 1.0f, 1.0f, f2);
                this.blockRenderer.renderBlockAsItem(Blocks.tnt, 0, 1.0f);
                GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
                GL11.glDisable(3042);
                GL11.glEnable(2896);
                GL11.glEnable(3553);
            }

            GL11.glPopMatrix();
        }
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
        return null;
    }
}
