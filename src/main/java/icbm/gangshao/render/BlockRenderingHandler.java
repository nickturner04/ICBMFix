package icbm.gangshao.render;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import icbm.gangshao.turret.BlockTurret;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class BlockRenderingHandler implements ISimpleBlockRenderingHandler {
    public static final int ID;

    @Override
    public void renderInventoryBlock(
        final Block block,
        final int metadata,
        final int modelID,
        final RenderBlocks renderer
    ) {
        if (modelID == BlockRenderingHandler.ID) {
            GL11.glPushMatrix();

            if (metadata == BlockTurret.TurretType.GUN.ordinal()) {
                GL11.glTranslatef(0.1f, 1.0f, 0.0f);
                FMLClientHandler.instance().getClient().renderEngine.bindTexture(
                    new ResourceLocation("icbm", "textures/models/gun_turret_neutral.png")
                );
                RGunTurret.render(0.0f, 0.0f);
            }

            if (metadata == BlockTurret.TurretType.LASER.ordinal()) {
                GL11.glTranslatef(0.4f, 1.4f, 0.0f);
                FMLClientHandler.instance().getClient().renderEngine.bindTexture(
                    new ResourceLocation(
                        "icbm", "textures/models/laser_turret_neutral.png"
                    )
                );
                RLaserTurret.render(0.0f, 0.0f);
            } else if (metadata == BlockTurret.TurretType.AA.ordinal()) {
                GL11.glTranslatef(0.2f, 0.3f, 0.0f);
                GL11.glScalef(0.45f, 0.45f, 0.45f);
                FMLClientHandler.instance().getClient().renderEngine.bindTexture(
                    new ResourceLocation("icbm", "textures/models/aa_turret_neutral.png")
                );
                RAATurret.render(0.0f, 0.0f);
            } else if (metadata == BlockTurret.TurretType.RAILGUN.ordinal()) {
                GL11.glTranslatef(0.0f, 0.9f, 0.0f);
                GL11.glRotatef(180.0f, 0.0f, 0.0f, 1.0f);
                FMLClientHandler.instance().getClient().renderEngine.bindTexture(
                    new ResourceLocation("icbm", "textures/models/railgun.png")
                );
                RRailgun.MODEL.render(90.0f, 0.0f, 0.0625f);
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
    public boolean shouldRender3DInInventory(int id) {
        return true;
    }

    @Override
    public int getRenderId() {
        return BlockRenderingHandler.ID;
    }

    static {
        ID = RenderingRegistry.getNextAvailableRenderId();
    }
}
