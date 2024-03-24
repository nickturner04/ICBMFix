package icbm.zhapin.gui;

import icbm.zhapin.jiqi.TLauncherPlatform;
import icbm.zhapin.rongqi.CFaShiDi;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;

public class GLauncherPlatform extends GuiContainer {
    private TLauncherPlatform tileEntity;
    private int containerWidth;
    private int containerHeight;

    public GLauncherPlatform(
        final InventoryPlayer par1InventoryPlayer, final TLauncherPlatform tileEntity
    ) {
        super((Container) new CFaShiDi(par1InventoryPlayer, tileEntity));
        this.tileEntity = tileEntity;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(final int par1, final int par2) {
        this.fontRendererObj.drawString(
            "§7" + this.tileEntity.getInventoryName(), 48, 6, 4210752
        );
        this.fontRendererObj.drawString("Place Missile", 63, 28, 4210752);
        this.fontRendererObj.drawString(
            StatCollector.translateToLocal("container.inventory"),
            8,
            this.ySize - 96 + 2,
            4210752
        );
    }

    @Override
    protected void
    drawGuiContainerBackgroundLayer(final float par1, final int par2, final int par3) {
        this.mc.renderEngine.bindTexture(
            new ResourceLocation("icbm", "textures/gui/gui_launcher.png")
        );
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        this.containerWidth = (this.width - this.xSize) / 2;
        this.containerHeight = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(
            this.containerWidth, this.containerHeight, 0, 0, this.xSize, this.ySize
        );
    }
}
