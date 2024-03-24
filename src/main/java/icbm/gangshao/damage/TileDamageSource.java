package icbm.gangshao.damage;

import icbm.gangshao.turret.TTurretBase;
import net.minecraft.entity.Entity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;

public class TileDamageSource extends EntityDamageSource {
    public TileDamageSource(final String damageName, final TTurretBase tileEntity) {
        super(damageName, (Entity) tileEntity.getDamageEntity());
    }

    public static TileDamageSource doBulletDamage(final TTurretBase tileEntity) {
        return (TileDamageSource) new TileDamageSource("bullet", tileEntity)
            .setProjectile();
    }

    public static TileDamageSource doLaserDamage(final TTurretBase tileEntity) {
        return (TileDamageSource) new TileDamageSource("laser", tileEntity)
            .setDamageBypassesArmor()
            .setProjectile();
    }

    public DamageSource setDamageBypassesArmor() {
        return super.setDamageBypassesArmor();
    }
}
