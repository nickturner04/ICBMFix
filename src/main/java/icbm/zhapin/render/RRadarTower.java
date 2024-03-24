package icbm.zhapin.render;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import icbm.zhapin.jiqi.TRadarTower;
import icbm.zhapin.muoxing.jiqi.MLeiDa;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RRadarTower extends TileEntitySpecialRenderer {
    public static final MLeiDa MODEL;

    public void renderAModelAt(
        final TRadarTower tileEntity,
        final double x,
        final double y,
        final double z,
        final float f
    ) {
        GL11.glPushMatrix();
        GL11.glTranslatef((float) x + 0.5f, (float) y + 1.5f, (float) z + 0.5f);
        this.bindTexture(new ResourceLocation("icbm", "textures/models/radar.png"));
        GL11.glRotatef(180.0f, 0.0f, 0.0f, 1.0f);
        RRadarTower.MODEL.render(tileEntity.rotation, 0.0625f);
        GL11.glPopMatrix();
    }

    @Override
    public void renderTileEntityAt(
        final TileEntity tileentity,
        final double d,
        final double d1,
        final double d2,
        final float f
    ) {
        this.renderAModelAt((TRadarTower) tileentity, d, d1, d2, f);
    }

    static {
        MODEL = new MLeiDa();
    }
}
