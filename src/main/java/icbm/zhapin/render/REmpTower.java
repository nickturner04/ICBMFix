package icbm.zhapin.render;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import icbm.zhapin.jiqi.TEmpTower;
import icbm.zhapin.muoxing.jiqi.MDianCiQi;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class REmpTower extends TileEntitySpecialRenderer {
    public static final MDianCiQi MODEL;
    public static final String TEXTURE_FILE = "emp_tower.png";

    public void renderAModelAt(
        final TEmpTower tileEntity,
        final double x,
        final double y,
        final double z,
        final float f
    ) {
        GL11.glPushMatrix();
        GL11.glTranslatef((float) x + 0.5f, (float) y + 1.5f, (float) z + 0.5f);
        this.bindTexture(new ResourceLocation("icbm", "textures/models/emp_tower.png"));
        GL11.glRotatef(180.0f, 0.0f, 0.0f, 1.0f);
        REmpTower.MODEL.render(tileEntity.rotation, 0.0625f);
        GL11.glPopMatrix();
    }

    @Override
    public void renderTileEntityAt(
        final TileEntity tileentity,
        final double x,
        final double y,
        final double z,
        final float f
    ) {
        this.renderAModelAt((TEmpTower) tileentity, x, y, z, f);
    }

    static {
        MODEL = new MDianCiQi();
    }
}
