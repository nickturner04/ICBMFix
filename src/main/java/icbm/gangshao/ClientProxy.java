package icbm.gangshao;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import icbm.gangshao.platform.TTurretPlatform;
import icbm.gangshao.render.BlockRenderingHandler;
import icbm.gangshao.render.FXBeam;
import icbm.gangshao.render.RAATurret;
import icbm.gangshao.render.RESeat;
import icbm.gangshao.render.RGunTurret;
import icbm.gangshao.render.RLaserTurret;
import icbm.gangshao.render.RRailgun;
import icbm.gangshao.shimian.GuiPlatformAccess;
import icbm.gangshao.shimian.GuiPlatformSlots;
import icbm.gangshao.shimian.GuiPlatformTerminal;
import icbm.gangshao.turret.mount.ESeat;
import icbm.gangshao.turret.mount.TRailgunTurret;
import icbm.gangshao.turret.sentries.TAATurret;
import icbm.gangshao.turret.sentries.TLaserTurret;
import icbm.gangshao.turret.sentries.TMachineGunTurret;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import universalelectricity.core.vector.Vector3;

public class ClientProxy extends CommonProxy {

    @Override
    public void init() {
        super.init();
        ClientRegistry.bindTileEntitySpecialRenderer(
            TMachineGunTurret.class, (TileEntitySpecialRenderer) new RGunTurret()
        );
        ClientRegistry.bindTileEntitySpecialRenderer(
            TAATurret.class, (TileEntitySpecialRenderer) new RAATurret()
        );
        ClientRegistry.bindTileEntitySpecialRenderer(
            TRailgunTurret.class, (TileEntitySpecialRenderer) new RRailgun()
        );
        ClientRegistry.bindTileEntitySpecialRenderer(
            TLaserTurret.class, (TileEntitySpecialRenderer) new RLaserTurret()
        );
        RenderingRegistry.registerEntityRenderingHandler(
            ESeat.class, (Render) new RESeat()
        );
        RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler
        ) new BlockRenderingHandler());
    }

    @Override
    public Object getClientGuiElement(
        final int ID,
        final EntityPlayer player,
        final World world,
        final int x,
        final int y,
        final int z
    ) {
        final TileEntity tileEntity = world.getTileEntity(x, y, z);

        if (tileEntity != null) {
            switch (ID) {
                case 0: {
                    return new GuiPlatformSlots(player.inventory, (TTurretPlatform) tileEntity);
                }

                case 1: {
                    return new GuiPlatformTerminal(player, (TTurretPlatform) tileEntity);
                }

                case 2: {
                    return new GuiPlatformAccess(player, (TTurretPlatform) tileEntity);
                }
            }
        }

        return null;
    }

    @Override
    public void renderBeam(
        final World world,
        final Vector3 position,
        final Vector3 target,
        final float red,
        final float green,
        final float blue,
        final int age
    ) {
        FMLClientHandler.instance().getClient().effectRenderer.addEffect((EntityFX
        ) new FXBeam(world, position, target, red, green, blue, age));
    }
}
