package icbm.zhapin.gui;

import icbm.zhapin.ICBMExplosion;
import icbm.zhapin.jiqi.CruiseLauncherGuiPacket;
import icbm.zhapin.jiqi.TCruiseLauncher;
import icbm.zhapin.rongqi.CCruiseLauncher;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;
import universalelectricity.api.energy.UnitDisplay;
import universalelectricity.core.vector.Vector3;

public class GCruiseLauncher extends GuiContainer {
    private TCruiseLauncher tileEntity;
    private GuiTextField textFieldX;
    private GuiTextField textFieldZ;
    private GuiTextField textFieldY;
    private GuiTextField textFieldFreq;
    private int containerWidth;
    private int containerHeight;

    public GCruiseLauncher(
        final InventoryPlayer par1InventoryPlayer, final TCruiseLauncher tileEntity
    ) {
        super((Container) new CCruiseLauncher(par1InventoryPlayer, tileEntity));
        this.tileEntity = tileEntity;
    }

    @Override
    public void initGui() {
        super.initGui();
        this.textFieldX = new GuiTextField(this.fontRendererObj, 20, 21, 35, 12);
        this.textFieldY = new GuiTextField(this.fontRendererObj, 20, 37, 35, 12);
        this.textFieldZ = new GuiTextField(this.fontRendererObj, 20, 52, 35, 12);
        (this.textFieldFreq = new GuiTextField(this.fontRendererObj, 70, 33, 35, 12))
            .setMaxStringLength(4);
        this.textFieldX.setMaxStringLength(6);
        this.textFieldZ.setMaxStringLength(6);
        this.textFieldY.setMaxStringLength(6);
        this.textFieldFreq.setText(this.tileEntity.getFrequency() + "");

        if (this.tileEntity.getTarget() == null) {
            this.textFieldX.setText(Math.round((float) this.tileEntity.xCoord) + "");
            this.textFieldZ.setText(Math.round((float) this.tileEntity.zCoord) + "");
            this.textFieldY.setText(Math.round((float) this.tileEntity.yCoord) + "");
        } else {
            this.textFieldX.setText(Math.round(this.tileEntity.getTarget().x) + "");
            this.textFieldZ.setText(Math.round(this.tileEntity.getTarget().z) + "");
            this.textFieldY.setText(Math.round(this.tileEntity.getTarget().y) + "");
        }
    }

    @Override
    public void keyTyped(final char par1, final int par2) {
        super.keyTyped(par1, par2);
        this.textFieldX.textboxKeyTyped(par1, par2);
        this.textFieldZ.textboxKeyTyped(par1, par2);
        this.textFieldY.textboxKeyTyped(par1, par2);
        this.textFieldFreq.textboxKeyTyped(par1, par2);

        try {
            final Vector3 newTarget = new Vector3(
                Integer.parseInt(this.textFieldX.getText()),
                Integer.parseInt(this.textFieldY.getText()),
                Integer.parseInt(this.textFieldZ.getText())
            );
            this.tileEntity.setTarget(newTarget);
            ICBMExplosion.channel.sendToServer(new CruiseLauncherGuiPacket(this.tileEntity
            ));
        } catch (final NumberFormatException ex) {}

        try {
            final short newFrequency
                = (short) Math.max(Short.parseShort(this.textFieldFreq.getText()), 0);
            this.tileEntity.setFrequency(newFrequency);
            ICBMExplosion.channel.sendToServer(new CruiseLauncherGuiPacket(this.tileEntity
            ));
        } catch (final NumberFormatException ex2) {}
    }

    @Override
    public void mouseClicked(final int par1, final int par2, final int par3) {
        super.mouseClicked(par1, par2, par3);
        this.textFieldX.mouseClicked(
            par1 - this.containerWidth, par2 - this.containerHeight, par3
        );
        this.textFieldZ.mouseClicked(
            par1 - this.containerWidth, par2 - this.containerHeight, par3
        );
        this.textFieldY.mouseClicked(
            par1 - this.containerWidth, par2 - this.containerHeight, par3
        );
        this.textFieldFreq.mouseClicked(
            par1 - this.containerWidth, par2 - this.containerHeight, par3
        );
    }

    @Override
    protected void drawGuiContainerForegroundLayer(final int par1, final int par2) {
        this.fontRendererObj.drawString(
            "§7" + this.tileEntity.getInventoryName(), 52, 6, 4210752
        );
        this.fontRendererObj.drawString("X:", 8, 23, 4210752);
        this.fontRendererObj.drawString("Y:", 8, 39, 4210752);
        this.fontRendererObj.drawString("Z:", 8, 54, 4210752);
        this.fontRendererObj.drawString("Frequency:", 70, 20, 4210752);
        this.textFieldX.drawTextBox();
        this.textFieldZ.drawTextBox();
        this.textFieldY.drawTextBox();
        this.textFieldFreq.drawTextBox();
        this.fontRendererObj.drawString(this.tileEntity.getStatus(), 70, 50, 4210752);
        this.fontRendererObj.drawString(
            this.tileEntity.getVoltage() + "v", 70, 60, 4210752
        );
        this.fontRendererObj.drawString(
            UnitDisplay.getDisplayShort(
                this.tileEntity.getJoules(), UnitDisplay.Unit.JOULES
            ) + "/"
                + UnitDisplay.getDisplayShort(
                    this.tileEntity.getMaxJoules(), UnitDisplay.Unit.JOULES
                ),
            70,
            70,
            4210752
        );
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
            new ResourceLocation("icbm", "textures/gui/gui_cruise_launcher.png")
        );
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        this.containerWidth = (this.width - this.xSize) / 2;
        this.containerHeight = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(
            this.containerWidth, this.containerHeight, 0, 0, this.xSize, this.ySize
        );
    }

    @Override
    public void updateScreen() {
        super.updateScreen();

        if (!this.textFieldX.isFocused()) {
            this.textFieldX.setText(Math.round(this.tileEntity.getTarget().x) + "");
        }

        if (!this.textFieldZ.isFocused()) {
            this.textFieldZ.setText(Math.round(this.tileEntity.getTarget().z) + "");
        }

        if (!this.textFieldY.isFocused()) {
            this.textFieldY.setText(Math.round(this.tileEntity.getTarget().y) + "");
        }

        if (!this.textFieldFreq.isFocused()) {
            this.textFieldFreq.setText(this.tileEntity.getFrequency() + "");
        }
    }
}
