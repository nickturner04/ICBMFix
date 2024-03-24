package icbm.zhapin.render;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import icbm.zhapin.jiqi.TLauncherControlPanel;
import icbm.zhapin.muoxing.jiqi.MFaSheShiMuo0;
import icbm.zhapin.muoxing.jiqi.MFaSheShiMuo1;
import icbm.zhapin.muoxing.jiqi.MFaSheShiMuo2;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RLauncherControlPanel extends TileEntitySpecialRenderer {
    public static final MFaSheShiMuo0 model0;
    public static final MFaSheShiMuo1 model1;
    public static final MFaSheShiMuo2 model2;

    @Override
    public void renderTileEntityAt(
        final TileEntity var1,
        final double x,
        final double y,
        final double z,
        final float var8
    ) {
        final TLauncherControlPanel tileEntity = (TLauncherControlPanel) var1;
        GL11.glPushMatrix();
        GL11.glTranslatef((float) x + 0.5f, (float) y + 1.5f, (float) z + 0.5f);
        this.bindTexture(new ResourceLocation(
            "icbm", "textures/models/launcher_" + tileEntity.getTier() + ".png"
        ));
        GL11.glRotatef(180.0f, 0.0f, 0.0f, 1.0f);

        switch (tileEntity
                    .getDirection(
                        (IBlockAccess) tileEntity.getWorldObj(), (int) x, (int) y, (int) z
                    )
                    .ordinal()) {
            case 2: {
                GL11.glRotatef(180.0f, 0.0f, 180.0f, 1.0f);
                break;
            }

            case 4: {
                GL11.glRotatef(90.0f, 0.0f, 180.0f, 1.0f);
                break;
            }

            case 5: {
                GL11.glRotatef(-90.0f, 0.0f, 180.0f, 1.0f);
                break;
            }
        }

        switch (tileEntity.getTier()) {
            case 0: {
                RLauncherControlPanel.model0.render(0.0625f);
                break;
            }

            case 1: {
                RLauncherControlPanel.model1.render(0.0625f);
                break;
            }

            case 2: {
                RLauncherControlPanel.model2.render(0.0625f);
                break;
            }
        }

        GL11.glPopMatrix();
    }

    static {
        model0 = new MFaSheShiMuo0();
        model1 = new MFaSheShiMuo1();
        model2 = new MFaSheShiMuo2();
    }
}
