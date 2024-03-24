package icbm.zhapin.render;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import icbm.zhapin.jiqi.TCruiseLauncher;
import icbm.zhapin.muoxing.jiqi.MXiaoFaSheQi;
import icbm.zhapin.muoxing.jiqi.MXiaoFaSheQiJia;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RXiaoFaSheQi extends TileEntitySpecialRenderer {
    public static final MXiaoFaSheQi MODEL0;
    public static final MXiaoFaSheQiJia MODEL1;

    public void renderModelAt(
        final TCruiseLauncher tileEntity,
        final double d,
        final double d1,
        final double d2,
        final float f
    ) {
        GL11.glPushMatrix();
        GL11.glTranslatef((float) d + 0.5f, (float) d1 + 1.5f, (float) d2 + 0.5f);
        this.bindTexture(
            new ResourceLocation("icbm", "textures/models/cruise_launcher.png")
        );
        GL11.glRotatef(180.0f, 0.0f, 0.0f, 1.0f);
        RXiaoFaSheQi.MODEL0.render(0.0625f);
        GL11.glRotatef(tileEntity.rotationYaw + 90.0f, 0.0f, 1.0f, 0.0f);
        GL11.glRotatef(-tileEntity.rotationPitch, 1.0f, 0.0f, 0.0f);
        RXiaoFaSheQi.MODEL1.render(0.0625f);
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
        this.renderModelAt((TCruiseLauncher) tileentity, d, d1, d2, f);
    }

    static {
        MODEL0 = new MXiaoFaSheQi();
        MODEL1 = new MXiaoFaSheQiJia();
    }
}
