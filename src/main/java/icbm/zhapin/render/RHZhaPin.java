package icbm.zhapin.render;

import calclavia.lib.render.CalclaviaRenderHelper;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import icbm.core.MainBase;
import icbm.zhapin.muoxing.jiqi.MSMine;
import icbm.zhapin.zhapin.TExplosive;
import icbm.zhapin.zhapin.ZhaPin;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RHZhaPin implements ISimpleBlockRenderingHandler {
    public static final int ID;

    @Override
    public void renderInventoryBlock(
        final Block block,
        final int metadata,
        final int modelID,
        final RenderBlocks renderer
    ) {
        if (modelID == RHZhaPin.ID) {
            if (metadata == ZhaPin.sMine.getID()) {
                GL11.glPushMatrix();
                GL11.glTranslatef(0.0f, 1.5f, 0.0f);
                GL11.glRotatef(180.0f, 0.0f, 0.0f, 1.0f);
                FMLClientHandler.instance().getClient().renderEngine.bindTexture(
                    new ResourceLocation("icbm", "textures/models/s-mine.png")
                );
                MSMine.INSTANCE.render(0.0625f);
                GL11.glPopMatrix();
            } else {
                try {
                    CalclaviaRenderHelper.renderNormalBlockAsItem(
                        block, metadata, renderer
                    );
                } catch (final Exception e) {
                    MainBase.LOGGER.severe(
                        "ICBM Explosive Rendering Crash with: " + block
                        + " and metadata: " + metadata
                    );
                    e.printStackTrace();
                }
            }
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
        if (modelID != RHZhaPin.ID) {
            return false;
        }

        if (((TExplosive) iBlockAccess.getTileEntity(x, y, z)).explosiveId
            == ZhaPin.sMine.getID()) {
            return false;
        }

        renderer.renderStandardBlock(block, x, y, z);
        return true;
    }

    @Override
    public boolean shouldRender3DInInventory(int meta) {
        return true;
    }

    @Override
    public int getRenderId() {
        return RHZhaPin.ID;
    }

    static {
        ID = RenderingRegistry.getNextAvailableRenderId();
    }
}
