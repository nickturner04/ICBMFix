package icbm.zhapin.render;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import icbm.zhapin.EGravityBlock;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RFeiBlock extends Render {
    public RFeiBlock() {
        super.shadowSize = 0.5f;
    }

    public void doRenderGravityBlock(
        final EGravityBlock entity,
        final double x,
        final double y,
        final double z,
        final float par8,
        final float par9
    ) {
        GL11.glPushMatrix();
        GL11.glTranslatef((float) x, (float) y, (float) z);
        this.bindTexture(TextureMap.locationBlocksTexture);
        final Block block = entity.block;
        final World world = entity.worldObj;
        GL11.glDisable(2896);
        GL11.glRotatef(entity.rotationPitch, 0.0f, 0.0f, 1.0f);
        GL11.glRotatef(entity.rotationYaw, 0.0f, 1.0f, 0.0f);
        this.field_147909_c.blockAccess = (IBlockAccess) world;

        // TODO: looks as shit as vanilla furnace fuel impl
        if (block == Blocks.dragon_egg || block == Blocks.grass || block == Blocks.fence
            || block == Blocks.wheat || block == Blocks.leaves
            || block == Blocks.redstone_torch || block == Blocks.torch
            || block == Blocks.tallgrass || block == Blocks.vine || block == Blocks.log
            || block == Blocks.bookshelf || block == Blocks.pumpkin) {
            final Tessellator tessellator = Tessellator.instance;
            tessellator.startDrawingQuads();
            tessellator.setTranslation(
                (double) (-MathHelper.floor_double(entity.posX) - 0.5f),
                (double) (-MathHelper.floor_double(entity.posY) - 0.5f),
                (double) (-MathHelper.floor_double(entity.posZ) - 0.5f)
            );
            this.field_147909_c.renderBlockByRenderType(
                block,
                MathHelper.floor_double(entity.posX),
                MathHelper.floor_double(entity.posY),
                MathHelper.floor_double(entity.posZ)
            );
            tessellator.setTranslation(0.0, 0.0, 0.0);
            tessellator.draw();
        } else {
            this.renderBlockGravity(block, entity.metadata, this.field_147909_c);
        }

        GL11.glEnable(2896);
        GL11.glPopMatrix();
    }

    public void renderBlockGravity(
        final Block block, final int metadata, final RenderBlocks renderer
    ) {
        final float var6 = 0.5f;
        final float var7 = 1.0f;
        final float var8 = 0.8f;
        final float var9 = 0.6f;
        final Tessellator tess = Tessellator.instance;
        tess.startDrawingQuads();
        final float var10 = 1.0f;
        tess.setColorOpaque_F(var6 * var10, var6 * var10, var6 * var10);
        renderer.renderFaceYNeg(
            block,
            -0.5,
            -0.5,
            -0.5,
            renderer.getBlockIconFromSideAndMetadata(block, 0, metadata)
        );
        tess.setColorOpaque_F(var7 * var10, var7 * var10, var7 * var10);
        renderer.renderFaceYPos(
            block,
            -0.5,
            -0.5,
            -0.5,
            renderer.getBlockIconFromSideAndMetadata(block, 1, metadata)
        );
        tess.setColorOpaque_F(var8 * var10, var8 * var10, var8 * var10);
        renderer.renderFaceZNeg(
            block,
            -0.5,
            -0.5,
            -0.5,
            renderer.getBlockIconFromSideAndMetadata(block, 2, metadata)
        );
        tess.setColorOpaque_F(var8 * var10, var8 * var10, var8 * var10);
        renderer.renderFaceZPos(
            block,
            -0.5,
            -0.5,
            -0.5,
            renderer.getBlockIconFromSideAndMetadata(block, 3, metadata)
        );
        tess.setColorOpaque_F(var9 * var10, var9 * var10, var9 * var10);
        renderer.renderFaceXNeg(
            block,
            -0.5,
            -0.5,
            -0.5,
            renderer.getBlockIconFromSideAndMetadata(block, 4, metadata)
        );
        tess.setColorOpaque_F(var9 * var10, var9 * var10, var9 * var10);
        renderer.renderFaceXPos(
            block,
            -0.5,
            -0.5,
            -0.5,
            renderer.getBlockIconFromSideAndMetadata(block, 5, metadata)
        );
        tess.draw();
    }

    @Override
    public void doRender(
        final Entity par1Entity,
        final double par2,
        final double par4,
        final double par6,
        final float par8,
        final float par9
    ) {
        this.doRenderGravityBlock(
            (EGravityBlock) par1Entity, par2, par4, par6, par8, par9
        );
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
        return null;
    }
}
