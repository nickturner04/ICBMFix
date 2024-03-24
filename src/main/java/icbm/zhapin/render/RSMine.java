package icbm.zhapin.render;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import icbm.core.di.MICBM;
import icbm.zhapin.zhapin.TExplosive;
import icbm.zhapin.zhapin.ZhaPin;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RSMine extends TileEntitySpecialRenderer {
    @Override
    public void renderTileEntityAt(
        final TileEntity var1,
        final double x,
        final double y,
        final double z,
        final float var8
    ) {
        final TExplosive tileEntity = (TExplosive) var1;
        final Object[] data = ZhaPin.list[tileEntity.explosiveId].getRenderData();

        if (data != null) {
            GL11.glPushMatrix();
            GL11.glTranslatef((float) x + 0.5f, (float) y + 1.5f, (float) z + 0.5f);
            this.bindTexture((ResourceLocation) data[1]);
            GL11.glRotatef(180.0f, 0.0f, 0.0f, 1.0f);
            ((MICBM) data[0]).render(0.0625f);
            GL11.glPopMatrix();
        }
    }
}
