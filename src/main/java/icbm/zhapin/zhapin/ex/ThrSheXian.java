package icbm.zhapin.zhapin.ex;

import java.util.HashSet;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import universalelectricity.core.vector.Vector3;

public class ThrSheXian extends Thread {
    public World world;
    public Vector3 position;
    public int radius;
    public int energyStored;
    public Entity source;
    public boolean isComplete;
    public final HashSet<Vector3> destroyed;

    public ThrSheXian(
        final World world,
        final Vector3 position,
        final int banJing,
        final int nengLiang,
        final Entity source
    ) {
        this.isComplete = false;
        this.destroyed = new HashSet<>();
        this.world = world;
        this.position = position;
        this.radius = banJing;
        this.energyStored = nengLiang;
        this.source = source;
        this.setPriority(4);
    }

    @Override
    public void run() {
        for (int steps
             = (int) Math.ceil(3.141592653589793 / Math.atan(1.0 / this.radius)),
             phi_n = 0;
             phi_n < 2 * steps;
             ++phi_n) {
            for (int theta_n = 0; theta_n < steps; ++theta_n) {
                final double phi = 6.283185307179586 / steps * phi_n;
                final double theta = 3.141592653589793 / steps * theta_n;
                final Vector3 delta = new Vector3(
                    Math.sin(theta) * Math.cos(phi),
                    Math.cos(theta),
                    Math.sin(theta) * Math.sin(phi)
                );
                float power = this.energyStored
                    - this.energyStored * this.world.rand.nextFloat() / 2.0f;
                final Vector3 targetPosition = this.position.clone();

                for (float var21 = 0.3f; power > 0.0f; power -= var21 * 0.75f * 10.0f) {
                    if (targetPosition.distanceTo(this.position) > this.radius) {
                        break;
                    }

                    final Block block = this.world.getBlock(
                        targetPosition.intX(),
                        targetPosition.intY(),
                        targetPosition.intZ()
                    );

                    if (block != Blocks.air) {
                        float resistance = 0.0f;

                        if (block == Blocks.bedrock) {
                            break;
                        }

                        if (block instanceof BlockLiquid) {
                            resistance = 1.0f;
                        } else {
                            resistance = block.getExplosionResistance(
                                             this.source,
                                             this.world,
                                             targetPosition.intX(),
                                             targetPosition.intY(),
                                             targetPosition.intZ(),
                                             (double) this.position.intX(),
                                             (double) this.position.intY(),
                                             (double) this.position.intZ()
                                         )
                                * 4.0f;
                        }

                        power -= resistance;

                        if (power > 0.0f && !this.destroyed.contains(targetPosition)) {
                            this.destroyed.add(targetPosition.clone());
                        }
                    }

                    final Vector3 vector3 = targetPosition;
                    vector3.x += delta.x;
                    final Vector3 vector4 = targetPosition;
                    vector4.y += delta.y;
                    final Vector3 vector5 = targetPosition;
                    vector5.z += delta.z;
                }
            }
        }

        this.isComplete = true;
    }
}
