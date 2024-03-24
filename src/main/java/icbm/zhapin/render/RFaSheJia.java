package icbm.zhapin.render;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import icbm.zhapin.jiqi.TLauncher;
import icbm.zhapin.muoxing.jiqi.MFaSheJia;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RFaSheJia extends TileEntitySpecialRenderer {
    public static final MFaSheJia MODEL;

    @Override
    public void renderTileEntityAt(
        final TileEntity var1,
        final double x,
        final double y,
        final double z,
        final float var8
    ) {
        final TLauncher tileEntity = (TLauncher) var1;

        if (tileEntity != null && tileEntity.getWorldObj() != null) {
            GL11.glPushMatrix();
            GL11.glTranslatef((float) x + 0.5f, (float) y + 1.25f, (float) z + 0.5f);
            GL11.glScalef(1.0f, 0.85f, 1.0f);
            this.bindTexture(
                new ResourceLocation("icbm", "textures/models/launcher_0.png")
            );
            GL11.glRotatef(180.0f, 0.0f, 0.0f, 1.0f);

            if (tileEntity.getDirection(
                    (IBlockAccess) tileEntity.getWorldObj(), (int) x, (int) y, (int) z
                ) != ForgeDirection.NORTH
                && tileEntity.getDirection(
                       (IBlockAccess) tileEntity.getWorldObj(), (int) x, (int) y, (int) z
                   ) != ForgeDirection.SOUTH) {
                GL11.glRotatef(90.0f, 0.0f, 180.0f, 1.0f);
            }

            RFaSheJia.MODEL.render(0.0625f);
            GL11.glPopMatrix();
        }
    }

    static {
        MODEL = new MFaSheJia();
    }
}
