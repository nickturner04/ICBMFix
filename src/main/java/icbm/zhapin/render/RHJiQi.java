package icbm.zhapin.render;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import icbm.zhapin.jiqi.BMachine;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RHJiQi implements ISimpleBlockRenderingHandler {
    public static final int ID;

    @Override
    public void renderInventoryBlock(
        final Block block,
        final int metadata,
        final int modelID,
        final RenderBlocks renderer
    ) {
        if (modelID == RHJiQi.ID) {
            GL11.glPushMatrix();

            if (metadata < BMachine.JiQi.FaSheDi.ordinal() * 3 + 3) {
                final int tier = metadata;
                GL11.glRotatef(180.0f, 0.0f, 0.0f, 1.0f);
                GL11.glScalef(0.4f, 0.4f, 0.4f);
                FMLClientHandler.instance().getClient().renderEngine.bindTexture(
                    new ResourceLocation(
                        "icbm", "textures/models/launcher_" + tier + ".png"
                    )
                );

                if (tier == 0) {
                    RFaSheDi.modelBase0.render(0.0625f);
                    RFaSheDi.modelRail0.render(0.0625f);
                } else if (tier == 1) {
                    RFaSheDi.modelBase1.render(0.0625f);
                    RFaSheDi.modelRail1.render(0.0625f);
                    GL11.glRotatef(180.0f, 0.0f, 180.0f, 1.0f);
                    RFaSheDi.modelRail1.render(0.0625f);
                } else if (tier == 2) {
                    RFaSheDi.modelBase2.render(0.0625f);
                    RFaSheDi.modelRail2.render(0.0625f);
                    GL11.glRotatef(180.0f, 0.0f, 180.0f, 1.0f);
                    RFaSheDi.modelRail2.render(0.0625f);
                }
            } else if (metadata < BMachine.JiQi.FaSheShiMuo.ordinal() * 3 + 3) {
                final int tier = metadata - 3;
                GL11.glTranslatef(0.0f, 0.9f, 0.0f);
                GL11.glRotatef(180.0f, 0.0f, 0.0f, 1.0f);
                GL11.glRotatef(180.0f, 0.0f, 180.0f, 1.0f);
                FMLClientHandler.instance().getClient().renderEngine.bindTexture(
                    new ResourceLocation(
                        "icbm", "textures/models/launcher_" + tier + ".png"
                    )
                );

                if (tier == 0) {
                    RLauncherControlPanel.model0.render(0.0625f);
                } else if (tier == 1) {
                    RLauncherControlPanel.model1.render(0.0625f);
                } else if (tier == 2) {
                    RLauncherControlPanel.model2.render(0.0625f);
                }
            } else if (metadata < BMachine.JiQi.FaSheJia.ordinal() * 3 + 3) {
                GL11.glTranslatef(0.0f, -0.1f, 0.0f);
                GL11.glRotatef(180.0f, 0.0f, 0.0f, 1.0f);
                GL11.glScalef(0.8f, 0.4f, 0.8f);
                FMLClientHandler.instance().getClient().renderEngine.bindTexture(
                    new ResourceLocation("icbm", "textures/models/launcher_0.png")
                );
                RFaSheJia.MODEL.render(0.0625f);
            } else if (metadata == BMachine.JiQi.LeiDaTai.ordinal() + 6) {
                GL11.glTranslatef(0.0f, 0.2f, 0.0f);
                GL11.glRotatef(180.0f, 0.0f, 0.0f, 1.0f);
                GL11.glScalef(0.55f, 0.6f, 0.55f);
                FMLClientHandler.instance().getClient().renderEngine.bindTexture(
                    new ResourceLocation("icbm", "textures/models/radar.png")
                );
                RRadarTower.MODEL.render(1.2f, 0.0625f);
            } else if (metadata == BMachine.JiQi.DianCiQi.ordinal() + 6) {
                GL11.glTranslatef(0.0f, 0.3f, 0.0f);
                GL11.glRotatef(180.0f, 0.0f, 0.0f, 1.0f);
                GL11.glScalef(0.6f, 0.6f, 0.6f);
                FMLClientHandler.instance().getClient().renderEngine.bindTexture(
                    new ResourceLocation("icbm", "textures/models/emp_tower.png")
                );
                REmpTower.MODEL.render(0.0f, 0.0625f);
            } else if (metadata == BMachine.JiQi.XiaoFaSheQi.ordinal() + 6) {
                GL11.glTranslatef(0.0f, 0.4f, 0.0f);
                GL11.glRotatef(180.0f, 0.0f, 0.0f, 1.0f);
                GL11.glScalef(0.55f, 0.5f, 0.55f);
                FMLClientHandler.instance().getClient().renderEngine.bindTexture(
                    new ResourceLocation("icbm", "textures/models/cruise_launcher.png")
                );
                RXiaoFaSheQi.MODEL0.render(0.0625f);
                RXiaoFaSheQi.MODEL1.render(0.0625f);
            } else if (metadata == BMachine.JiQi.YinDaoQi.ordinal() + 6) {
                GL11.glTranslatef(0.0f, 1.1f, 0.0f);
                GL11.glRotatef(180.0f, 0.0f, 0.0f, 1.0f);
                GL11.glRotatef(180.0f, 0.0f, 1.0f, 0.0f);
                FMLClientHandler.instance().getClient().renderEngine.bindTexture(
                    new ResourceLocation(
                        "icbm", "textures/models/missile_coordinator_off.png"
                    )
                );
                RYinDaoQi.MODEL.render(0.0f, 0.0625f);
            }

            GL11.glPopMatrix();
        }
    }

    @Override
    public boolean renderWorldBlock(
        final IBlockAccess iBlockAccess,
        final int x,
        final int y,
        final int z,
        final Block block,
        final int modelID,
        final RenderBlocks renderer
    ) {
        return false;
    }

    @Override
    public boolean shouldRender3DInInventory(int meta) {
        return true;
    }

    @Override
    public int getRenderId() {
        return RHJiQi.ID;
    }

    static {
        ID = RenderingRegistry.getNextAvailableRenderId();
    }
}
