//
// Decompiled by Procyon v0.6.0
//

package icbm.wanyi;

import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import icbm.wanyi.b.TCamouflage;
import icbm.wanyi.b.TProximityDetector;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class CommonProxy implements IGuiHandler {
    public void preInit() {}

    public void init() {
        GameRegistry.registerTileEntity((Class) TCamouflage.class, "ICBMCamouflage");
        GameRegistry.registerTileEntity((Class) TProximityDetector.class, "ICBMProximityDetector");
    }

    public Object getClientGuiElement(
        final int ID,
        final EntityPlayer player,
        final World world,
        final int x,
        final int y,
        final int z
    ) {
        return null;
    }

    public Object getServerGuiElement(
        final int ID,
        final EntityPlayer player,
        final World world,
        final int x,
        final int y,
        final int z
    ) {
        return null;
    }
}
