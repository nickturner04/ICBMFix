package icbm.gangshao.render;

import calclavia.lib.render.CalclaviaRenderHelper;
import calclavia.lib.render.ITagRender;
import calclavia.lib.render.RenderTaggedTile;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import icbm.gangshao.access.AccessLevel;
import icbm.gangshao.muoxing.MLeiShe;
import icbm.gangshao.turret.TTurretBase;
import icbm.gangshao.turret.sentries.TLaserTurret;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

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
        if (t != null && t instanceof ITagRender
            && this.getPlayer().getDistance(
            (double) t.xCoord, (double) t.yCoord, (double) t.zCoord
        ) <= (double) RendererLivingEntity.NAME_TAG_RANGE) {
            HashMap tags = new HashMap();
            float height = ((ITagRender) t).addInformation(tags, this.getPlayer());
            EntityClientPlayerMP player = Minecraft.getMinecraft().thePlayer;

            if (player.ridingEntity == null) {
                MovingObjectPosition objectPosition = player.rayTrace(8.0D, 1.0F);

                if (objectPosition != null) {
                    boolean isLooking = false;

                    for (int it = 0; (float) it < height; ++it) {
                        if (objectPosition.blockX == t.xCoord
                            && objectPosition.blockY == t.yCoord + it
                            && objectPosition.blockZ == t.zCoord) {
                            isLooking = true;
                        }
                    }

                    if (isLooking) {
                        Iterator var17 = tags.entrySet().iterator();

                        for (int i = 0; var17.hasNext(); ++i) {
                            Map.Entry entry = (Map.Entry) var17.next();

                            if (entry.getKey() != null) {
                                CalclaviaRenderHelper.renderFloatingText(
                                    (String) entry.getKey(),
                                    (float) x + 0.5F,
                                    (float) y + (float) i * 0.25F - 2.0F + height,
                                    (float) z + 0.5F,
                                    ((Integer) entry.getValue()).intValue()
                                );
                            }
                        }
                    }
                }
            }
        }

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
