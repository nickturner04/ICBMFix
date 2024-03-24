package icbm.gangshao;

import net.minecraft.tileentity.TileEntity;
import universalelectricity.core.vector.Vector3;

public interface ISentry {
    void setRotation(final float p0, final float p1);

    Vector3 getMuzzle();

    void onWeaponActivated();

    boolean canActivateWeapon();

    TileEntity getPlatform();

    String getName();
}
