package icbm.zhapin.render;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import icbm.zhapin.jiqi.TMissileCoordinator;
import icbm.zhapin.muoxing.jiqi.MYinDaoQi;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RYinDaoQi extends TileEntitySpecialRenderer {
    public static final MYinDaoQi MODEL;

    public void renderModelAt(
        final TMissileCoordinator tileEntity,
        final double x,
        final double y,
        final double z,
        final float f
    ) {
        GL11.glPushMatrix();
        GL11.glTranslatef((float) x + 0.5f, (float) y + 1.5f, (float) z + 0.5f);
        final int radius = 2;
        final List<EntityPlayer> players = tileEntity.getWorldObj().getEntitiesWithinAABB(
            EntityPlayer.class,
            AxisAlignedBB.getBoundingBox(
                (double) (tileEntity.xCoord - radius),
                (double) (tileEntity.yCoord - radius),
                (double) (tileEntity.zCoord - radius),
                (double) (tileEntity.xCoord + radius),
                (double) (tileEntity.yCoord + radius),
                (double) (tileEntity.zCoord + radius)
            )
        );

        if (players.size() > 0) {
            this.bindTexture(
                new ResourceLocation("icbm", "textures/models/missile_coordinator_on.png")
            );
        } else {
            this.bindTexture(new ResourceLocation(
                "icbm", "textures/models/missile_coordinator_off.png"
            ));
        }

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

        RYinDaoQi.MODEL.render(0.0f, 0.0625f);
        GL11.glPopMatrix();
    }

    public void renderTileEntityAt(
        final TileEntity tileentity,
        final double d,
        final double d1,
        final double d2,
        final float f
    ) {
        this.renderModelAt((TMissileCoordinator) tileentity, d, d1, d2, f);
    }

    static {
        MODEL = new MYinDaoQi();
    }
}
