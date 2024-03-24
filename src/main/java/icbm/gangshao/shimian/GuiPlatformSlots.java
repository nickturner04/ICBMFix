package icbm.gangshao.shimian;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import icbm.gangshao.platform.TTurretPlatform;
import icbm.gangshao.turret.TTurretBase;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import universalelectricity.api.energy.UnitDisplay;

@SideOnly(Side.CLIENT)
public class GuiPlatformSlots extends GuiPlatformContainer {
    public GuiPlatformSlots(
        final InventoryPlayer inventoryPlayer, final TTurretPlatform tileEntity
    ) {
        super(inventoryPlayer, tileEntity);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(final int x, final int y) {
        this.fontRendererObj.drawString("Ammunition", 8, 30, 4210752);
        final TTurretBase turret = super.tileEntity.getTurret(false);

        if (turret != null && turret.getFiringRequest() > 0.0) {
            String color = "§4";

            if (super.tileEntity.isRunning()) {
                color = "§a";
            }

            this.fontRendererObj.drawString("Energy Per Shot", 85, 33, 4210752);
            this.fontRendererObj.drawString(
                color
                    + UnitDisplay.getDisplayShort(
                        Math.min(
                            super.tileEntity.wattsReceived, turret.getFiringRequest()
                        ),
                        UnitDisplay.Unit.JOULES
                    ),
                87,
                43,
                4210752
            );
            this.fontRendererObj.drawString(
                color + "of "
                    + UnitDisplay.getDisplayShort(
                        super.tileEntity.getTurret(false).getFiringRequest(),
                        UnitDisplay.Unit.JOULES
                    ),
                87,
                53,
                4210752
            );
        }

        this.fontRendererObj.drawString("Upgrades", 87, 66, 4210752);
        super.drawGuiContainerForegroundLayer(x, y);
    }

    @Override
    protected void
    drawGuiContainerBackgroundLayer(final float par1, final int x, final int y) {
        super.drawGuiContainerBackgroundLayer(par1, x, y);
        this.mc.renderEngine.bindTexture(
            new ResourceLocation("icbm", "textures/gui/gui_platform_slot.png")
        );
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        final int containerWidth = (this.width - this.xSize) / 2;
        final int containerHeight = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(
            containerWidth, containerHeight, 0, 0, this.xSize, this.ySize
        );
    }
}
