package icbm.gangshao.render;

import calclavia.lib.render.RenderTaggedTile;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import icbm.gangshao.muoxing.ModelRailgun;
import icbm.gangshao.turret.mount.TRailgunTurret;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RRailgun extends RenderTaggedTile {
    public static final String TEXTURE_FILE = "railgun.png";
    public static final ModelRailgun MODEL;

    @Override
    public void renderTileEntityAt(
        final TileEntity t, final double x, final double y, final double z, final float f
    ) {
        super.renderTileEntityAt(t, x, y, z, f);

        if (t instanceof TRailgunTurret) {
            final TRailgunTurret tileEntity = (TRailgunTurret) t;
            GL11.glPushMatrix();
            GL11.glTranslatef((float) x + 0.5f, (float) y + 2.2f, (float) z + 0.5f);
            GL11.glScalef(1.5f, 1.5f, 1.5f);
            this.bindTexture(new ResourceLocation("icbm", "textures/models/railgun.png"));
            GL11.glRotatef(180.0f, 0.0f, 0.0f, 1.0f);
            GL11.glRotatef(180.0f, 0.0f, 1.0f, 0.0f);
            RRailgun.MODEL.render(
                (float) Math.toRadians(tileEntity.currentRotationYaw),
                (float) Math.toRadians(tileEntity.currentRotationPitch),
                0.0625f
            );
            GL11.glPopMatrix();
        }
    }

    static {
        MODEL = new ModelRailgun();
    }
}
