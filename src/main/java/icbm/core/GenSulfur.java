package icbm.core;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import universalelectricity.core.vector.Vector3;
import universalelectricity.prefab.ore.OreGenReplace;

public class GenSulfur extends OreGenReplace {
    public GenSulfur(
        final String name,
        final String oreDiectionaryName,
        final ItemStack stack,
        final Block replaceID,
        final int minGenerateLevel,
        final int maxGenerateLevel,
        final int amountPerChunk,
        final int amountPerBranch,
        final String harvestTool,
        final int harvestLevel
    ) {
        super(
            name,
            oreDiectionaryName,
            stack,
            replaceID,
            minGenerateLevel,
            maxGenerateLevel,
            amountPerChunk,
            amountPerBranch,
            harvestTool,
            harvestLevel
        );
    }

    public GenSulfur(
        final String name,
        final String oreDiectionaryName,
        final ItemStack stack,
        final Block replaceID,
        final int maxGenerateLevel,
        final int amountPerChunk,
        final int amountPerBranch
    ) {
        this(
            name,
            oreDiectionaryName,
            stack,
            replaceID,
            0,
            maxGenerateLevel,
            amountPerChunk,
            amountPerBranch,
            "pickaxe",
            1
        );
    }

    @Override
    public void
    generate(final World world, final Random random, final int varX, final int varZ) {
        for (int y = super.minGenerateLevel; y < super.maxGenerateLevel; ++y) {
            for (int x = 0; x < 16; ++x) {
                for (int z = 0; z < 16; ++z) {
                    this.generateReplace(world, random, varX + x, y, varZ + z);
                }
            }
        }
    }

    @Override
    public boolean generateReplace(
        final World world, final Random rand, final int x, final int y, final int z
    ) {
        if (this.nearLava(world, x, y, z)) {
            this.placeOre(world, rand, x, y, z);
            return true;
        }

        return false;
    }

    private void placeOre(
        final World world, final Random rand, final int x, final int y, final int z
    ) {
        final Vector3 position = new Vector3(x, y, z);

        for (int amount = 0; amount < super.amountPerBranch; ++amount) {
            final Block block = world.getBlock(x, y, z);

            if (block != Blocks.air) {
                world.setBlock(x, y, z, super.oreID, super.oreMeta, 2);
            }

            final ForgeDirection dir = ForgeDirection.values()[rand.nextInt(6)];
            position.modifyPositionFromSide(dir);
        }
    }

    private boolean nearLava(final World world, final int x, final int y, final int z) {
        for (int side = 2; side < 6; ++side) {
            final Vector3 position = new Vector3(x, y, z);
            final ForgeDirection s = ForgeDirection.values()[side];
            position.modifyPositionFromSide(s);

            if (world.blockExists(position.intX(), position.intY(), position.intZ())) {
                final Block id
                    = world.getBlock(position.intX(), position.intY(), position.intZ());

                if (id == Blocks.lava || id == Blocks.flowing_lava) {
                    return true;
                }
            }
        }

        for (int j = 0; j < 4; ++j) {
            final Block id2 = world.getBlock(x, y - j, z);

            if (id2 == Blocks.lava || id2 == Blocks.flowing_lava) {
                return true;
            }

            if (id2 != Blocks.air) {
                return false;
            }
        }

        return false;
    }
}
