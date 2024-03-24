package icbm.zhapin.zhapin.ex;

import icbm.core.MainBase;
import icbm.zhapin.zhapin.ZhaPin;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.ChunkProviderServer;
import net.minecraftforge.oredict.ShapedOreRecipe;
import universalelectricity.core.vector.Vector3;
import universalelectricity.prefab.RecipeHelper;

public class ExRejuvenation extends ZhaPin {
    public ExRejuvenation(final String name, final int ID, final int tier) {
        super(name, ID, tier);
    }

    @Override
    public boolean doBaoZha(
        final World worldObj,
        final Vector3 position,
        final Entity explosionSource,
        final int explosionMetadata,
        final int callCount
    ) {
        if (!worldObj.isRemote) {
            try {
                final Chunk oldChunk
                    = worldObj.getChunkFromBlockCoords(position.intX(), position.intZ());

                if (worldObj instanceof WorldServer) {
                    final WorldServer worldServer = (WorldServer) worldObj;
                    final ChunkProviderServer chunkProviderServer
                        = worldServer.theChunkProviderServer;
                    final IChunkProvider chunkProviderGenerate
                        = chunkProviderServer.currentChunkProvider;
                    final Chunk newChunk = chunkProviderGenerate.provideChunk(
                        oldChunk.xPosition, oldChunk.zPosition
                    );

                    for (int x = 0; x < 16; ++x) {
                        for (int z = 0; z < 16; ++z) {
                            for (int y = 0; y < worldObj.getHeight(); ++y) {
                                final Block block = newChunk.getBlock(x, y, z);
                                final int metadata = newChunk.getBlockMetadata(x, y, z);
                                worldServer.setBlock(
                                    x + oldChunk.xPosition * 16,
                                    y,
                                    z + oldChunk.zPosition * 16,
                                    block,
                                    metadata,
                                    3
                                );
                                final TileEntity tileEntity
                                    = newChunk.getTileEntityUnsafe(x, y, z);

                                if (tileEntity != null) {
                                    worldServer.setTileEntity(
                                        x + oldChunk.xPosition * 16,
                                        y,
                                        z + oldChunk.zPosition * 16,
                                        tileEntity
                                    );
                                }
                            }
                        }
                    }

                    oldChunk.isTerrainPopulated = false;
                    chunkProviderServer.populate(
                        chunkProviderServer, oldChunk.xPosition, oldChunk.zPosition
                    );
                    oldChunk.isModified = true;
                }
            } catch (final Exception e) {
                System.out.println("ICBM Rejuvenation Failed!");
                e.printStackTrace();
            }
        }

        return false;
    }

    @Override
    public void init() {
        RecipeHelper.addRecipe(
            (IRecipe) new ShapedOreRecipe(
                this.getItemStack(),
                new Object[] { "ICI",
                               "CDC",
                               "ICI",
                               'D',
                               Blocks.diamond_block,
                               'C',
                               Items.clock,
                               'I',
                               Blocks.iron_block }
            ),
            this.getUnlocalizedName(),
            MainBase.CONFIGURATION,
            true
        );
    }

    @Override
    public float getRadius() {
        return 16.0f;
    }

    @Override
    public double getEnergy() {
        return 0.0;
    }
}
