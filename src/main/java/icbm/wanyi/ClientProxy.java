package icbm.wanyi;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import icbm.wanyi.b.TProximityDetector;
import icbm.wanyi.gui.GProximityDetector;
import icbm.wanyi.gui.GSignalDisrupter;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy {
    @Override
    public void preInit() {
        super.preInit();
    }

    @Override
    public Object getClientGuiElement(
        final int ID,
        final EntityPlayer entityPlayer,
        final World world,
        final int x,
        final int y,
        final int z
    ) {
        final TileEntity tileEntity = world.getTileEntity(x, y, z);

        if (tileEntity != null || ID == 5) {
            switch (ID) {
                case 4: {
                    return new GProximityDetector((TProximityDetector) tileEntity);
                }

                case 5: {
                    return new GSignalDisrupter(entityPlayer.inventory.getCurrentItem());
                }
            }
        }

        return null;
    }
}
