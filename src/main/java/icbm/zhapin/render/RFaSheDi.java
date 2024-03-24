package icbm.zhapin.render;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import icbm.zhapin.jiqi.TLauncherPlatform;
import icbm.zhapin.muoxing.jiqi.MFaSheDi0;
import icbm.zhapin.muoxing.jiqi.MFaSheDi1;
import icbm.zhapin.muoxing.jiqi.MFaSheDi2;
import icbm.zhapin.muoxing.jiqi.MFaSheDiRail0;
import icbm.zhapin.muoxing.jiqi.MFaSheDiRail1;
import icbm.zhapin.muoxing.jiqi.MFaSheDiRail2;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RFaSheDi extends TileEntitySpecialRenderer {
    public static final MFaSheDi0 modelBase0;
    public static final MFaSheDiRail0 modelRail0;
    public static final MFaSheDi1 modelBase1;
    public static final MFaSheDiRail1 modelRail1;
    public static final MFaSheDi2 modelBase2;
    public static final MFaSheDiRail2 modelRail2;

    @Override
    public void renderTileEntityAt(
        final TileEntity tileentity,
        final double x,
        final double y,
        final double z,
        final float f
    ) {
        final TLauncherPlatform tileEntity = (TLauncherPlatform) tileentity;
        GL11.glPushMatrix();
        GL11.glTranslatef((float) x + 0.5f, (float) y + 1.5f, (float) z + 0.5f);
        this.bindTexture(new ResourceLocation(
            "icbm", "textures/models/launcher_" + tileEntity.getTier() + ".png"
        ));
        GL11.glRotatef(180.0f, 0.0f, 0.0f, 1.0f);

        if (tileEntity.getDirection(
                (IBlockAccess) tileEntity.getWorldObj(), (int) x, (int) y, (int) z
            ) != ForgeDirection.NORTH
            && tileEntity.getDirection(
                   (IBlockAccess) tileEntity.getWorldObj(), (int) x, (int) y, (int) z
               ) != ForgeDirection.SOUTH) {
            GL11.glRotatef(90.0f, 0.0f, 180.0f, 1.0f);
        }

        if (tileEntity.getTier() == 0) {
            RFaSheDi.modelBase0.render(0.0625f);
            RFaSheDi.modelRail0.render(0.0625f);
        } else if (tileEntity.getTier() == 1) {
            RFaSheDi.modelBase1.render(0.0625f);
            RFaSheDi.modelRail1.render(0.0625f);
            GL11.glRotatef(180.0f, 0.0f, 180.0f, 1.0f);
            RFaSheDi.modelRail1.render(0.0625f);
        } else if (tileEntity.getTier() == 2) {
            RFaSheDi.modelBase2.render(0.0625f);
            RFaSheDi.modelRail2.render(0.0625f);
            GL11.glRotatef(180.0f, 0.0f, 180.0f, 1.0f);
            RFaSheDi.modelRail2.render(0.0625f);
        }

        GL11.glPopMatrix();
    }

    static {
        modelBase0 = new MFaSheDi0();
        modelRail0 = new MFaSheDiRail0();
        modelBase1 = new MFaSheDi1();
        modelRail1 = new MFaSheDiRail1();
        modelBase2 = new MFaSheDi2();
        modelRail2 = new MFaSheDiRail2();
    }
}
