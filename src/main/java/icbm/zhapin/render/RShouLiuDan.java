package icbm.zhapin.render;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import icbm.zhapin.ICBMExplosion;
import icbm.zhapin.zhapin.EGrenade;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RShouLiuDan extends Render {
    @Override
    public void doRender(
        final Entity entity,
        final double x,
        final double y,
        final double z,
        final float par8,
        final float par9
    ) {
        final IIcon icon
            = ICBMExplosion.itShouLiuDan.getIconFromDamage(((EGrenade) entity).haoMa);
        GL11.glPushMatrix();
        GL11.glTranslatef((float) x, (float) y + 0.4f, (float) z);
        GL11.glEnable(32826);
        GL11.glScalef(0.6f, 0.6f, 0.6f);
        this.bindTexture(TextureMap.locationItemsTexture);
        final Tessellator tessellator = Tessellator.instance;
        this.renderIcon(tessellator, icon);
        GL11.glDisable(32826);
        GL11.glPopMatrix();
    }

    private void renderIcon(final Tessellator par1Tessellator, final IIcon icon) {
        final float f = icon.getMinU();
        final float f2 = icon.getMaxU();
        final float f3 = icon.getMinV();
        final float f4 = icon.getMaxV();
        final float f5 = 1.0f;
        final float f6 = 0.5f;
        final float f7 = 0.25f;
        GL11.glRotatef(180.0f - super.renderManager.playerViewY, 0.0f, 1.0f, 0.0f);
        GL11.glRotatef(-super.renderManager.playerViewX, 1.0f, 0.0f, 0.0f);
        par1Tessellator.startDrawingQuads();
        par1Tessellator.setNormal(0.0f, 1.0f, 0.0f);
        par1Tessellator.addVertexWithUV(
            (double) (0.0f - f6), (double) (0.0f - f7), 0.0, (double) f, (double) f4
        );
        par1Tessellator.addVertexWithUV(
            (double) (f5 - f6), (double) (0.0f - f7), 0.0, (double) f2, (double) f4
        );
        par1Tessellator.addVertexWithUV(
            (double) (f5 - f6), (double) (f5 - f7), 0.0, (double) f2, (double) f3
        );
        par1Tessellator.addVertexWithUV(
            (double) (0.0f - f6), (double) (f5 - f7), 0.0, (double) f, (double) f3
        );
        par1Tessellator.draw();
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
        return null;
    }
}
