package icbm.gangshao.render;

import calclavia.lib.render.RenderTaggedTile;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import icbm.gangshao.access.AccessLevel;
import icbm.gangshao.muoxing.ModelAATurret;
import icbm.gangshao.turret.TTurretBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RAATurret extends RenderTaggedTile {
    public static final String TEXTURE_FILE = "aa_turret_neutral.png";
    public static final String TEXTURE_FILE_FRIENDLY = "aa_turret_friendly.png";
    public static final String TEXTURE_FILE_HOSTILE = "aa_turret_hostile.png";
    public static final ModelAATurret MODEL;

    @Override
    public void renderTileEntityAt(
        final TileEntity t, final double x, final double y, final double z, final float f
    ) {
        super.renderTileEntityAt(t, x, y, z, f);

        if (t instanceof TTurretBase) {
            final TTurretBase tileEntity = (TTurretBase) t;
            GL11.glPushMatrix();
            GL11.glTranslatef((float) x + 0.5f, (float) y + 1.0f, (float) z + 0.5f);
            GL11.glScalef(0.7f, 0.7f, 0.7f);
            this.setTextureBaseOnState(tileEntity);
            render(tileEntity.currentRotationYaw, tileEntity.currentRotationPitch);
            GL11.glPopMatrix();
        }
    }

    public static void render(final float renderYaw, final float renderPitch) {
        GL11.glRotatef(180.0f, 0.0f, 0.0f, 1.0f);
        GL11.glRotatef(180.0f, 0.0f, 1.0f, 0.0f);
        GL11.glRotatef(renderYaw, 0.0f, 1.0f, 0.0f);
        RAATurret.MODEL.renderBody(0.0625f);
        RAATurret.MODEL.renderRadar(0.0625f);
        RAATurret.MODEL.renderCannon(0.0625f, (float) Math.toRadians(renderPitch));
    }

    public void setTextureBaseOnState(final TTurretBase tileEntity) {
        final EntityPlayer player = this.getPlayer();

        if (tileEntity.getPlatform() != null) {
            final AccessLevel level
                = tileEntity.getPlatform().getUserAccess(player.getDisplayName());

            if (level == AccessLevel.ADMIN) {
                this.bindTexture(
                    new ResourceLocation("icbm", "textures/models/aa_turret_neutral.png")
                );
                return;
            }

            if (level.ordinal() >= AccessLevel.USER.ordinal()) {
                this.bindTexture(
                    new ResourceLocation("icbm", "textures/models/aa_turret_friendly.png")
                );
                return;
            }
        }

        this.bindTexture(
            new ResourceLocation("icbm", "textures/models/aa_turret_hostile.png")
        );
    }

    static {
        MODEL = new ModelAATurret();
    }
}
