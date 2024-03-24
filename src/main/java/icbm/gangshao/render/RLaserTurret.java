package icbm.gangshao.render;

import calclavia.lib.render.RenderTaggedTile;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import icbm.gangshao.access.AccessLevel;
import icbm.gangshao.muoxing.MLeiShe;
import icbm.gangshao.turret.TTurretBase;
import icbm.gangshao.turret.sentries.TLaserTurret;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RLaserTurret extends RenderTaggedTile {
    public static final String TEXTURE_FILE = "laser_turret_neutral.png";
    public static final String TEXTURE_FILE_FRIENDLY = "laser_turret_friendly.png";
    public static final String TEXTURE_FILE_HOSTILE = "laser_turret_hostile.png";
    public static final MLeiShe MODEL;

    @Override
    public void renderTileEntityAt(
        final TileEntity t, final double x, final double y, final double z, final float f
    ) {
        super.renderTileEntityAt(t, x, y, z, f);

        if (t instanceof TLaserTurret) {
            final TLaserTurret tileEntity = (TLaserTurret) t;
            GL11.glPushMatrix();
            GL11.glTranslatef((float) x + 0.5f, (float) y + 1.5f, (float) z + 0.5f);
            this.setTextureBaseOnState(tileEntity);
            render(
                tileEntity.currentRotationYaw,
                tileEntity.currentRotationPitch,
                tileEntity.barrelRotation
            );
            GL11.glPopMatrix();
        }
    }

    public static void render(final float renderYaw, final float renderPitch) {
        render(renderYaw, renderPitch, 0.0f);
    }

    public static void
    render(final float renderYaw, final float renderPitch, final float barrelRotation) {
        GL11.glRotatef(180.0f, 0.0f, 0.0f, 1.0f);
        GL11.glRotatef(180.0f, 0.0f, 1.0f, 0.0f);
        GL11.glRotatef(renderYaw, 0.0f, 1.0f, 0.0f);
        RLaserTurret.MODEL.renderYaw(0.0625f);
        GL11.glRotatef(renderPitch, 1.0f, 0.0f, 0.0f);
        RLaserTurret.MODEL.renderYawPitch(0.0625f, barrelRotation);
    }

    public void setTextureBaseOnState(final TTurretBase tileEntity) {
        final EntityPlayer player = this.getPlayer();

        if (tileEntity.getPlatform() != null) {
            final AccessLevel level
                = tileEntity.getPlatform().getUserAccess(player.getDisplayName());

            if (level == AccessLevel.ADMIN) {
                this.bindTexture(new ResourceLocation(
                    "icbm", "textures/models/laser_turret_neutral.png"
                ));
                return;
            }

            if (level.ordinal() >= AccessLevel.USER.ordinal()) {
                this.bindTexture(new ResourceLocation(
                    "icbm", "textures/models/laser_turret_friendly.png"
                ));
                return;
            }
        }

        this.bindTexture(
            new ResourceLocation("icbm", "textures/models/laser_turret_hostile.png")
        );
    }

    static {
        MODEL = new MLeiShe();
    }
}
