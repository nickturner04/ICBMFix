package icbm.wanyi.gui;

import java.util.Optional;

import icbm.wanyi.ICBMContraption;
import icbm.wanyi.ProximityDetectorModePacket;
import icbm.wanyi.b.TProximityDetector;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import universalelectricity.api.energy.UnitDisplay;
import universalelectricity.core.vector.Vector3;
import universalelectricity.prefab.GuiBase;

public class GProximityDetector extends GuiBase {
    private TProximityDetector tileEntity;
    private int containerWidth;
    private int containerHeight;
    private GuiTextField textFieldFreq;
    private GuiTextField textFieldminX;
    private GuiTextField textFieldminY;
    private GuiTextField textFieldminZ;
    private GuiTextField textFieldmaxX;
    private GuiTextField textFieldmaxY;
    private GuiTextField textFieldmaxZ;

    public GProximityDetector(final TProximityDetector tileEntity) {
        this.tileEntity = tileEntity;
    }

    @Override
    public void initGui() {
        super.initGui();
        this.buttonList.clear();
        String mode = "All";

        if (this.tileEntity.mode == 1) {
            mode = "Players";
        } else if (this.tileEntity.mode == 2) {
            mode = "Mobs";
        }

        this.buttonList.add(
            new GuiButton(0, this.width / 2 - 15, this.height / 2 + 32, 45, 20, mode)
        );
        (this.textFieldFreq = new GuiTextField(this.fontRendererObj, 75, 100, 40, 12))
            .setMaxStringLength(4);
        this.textFieldFreq.setText(this.tileEntity.frequency + "");
        (this.textFieldminX = new GuiTextField(this.fontRendererObj, 75, 50, 20, 12))
            .setMaxStringLength(2);
        this.textFieldminX.setText(this.tileEntity.minCoord.intX() + "");
        (this.textFieldminY = new GuiTextField(this.fontRendererObj, 75, 67, 20, 12))
            .setMaxStringLength(2);
        this.textFieldminY.setText(this.tileEntity.minCoord.intY() + "");
        (this.textFieldminZ = new GuiTextField(this.fontRendererObj, 75, 82, 20, 12))
            .setMaxStringLength(2);
        this.textFieldminZ.setText(this.tileEntity.minCoord.intZ() + "");
        (this.textFieldmaxX = new GuiTextField(this.fontRendererObj, 130, 50, 20, 12))
            .setMaxStringLength(2);
        this.textFieldmaxX.setText(this.tileEntity.maxCoord.intX() + "");
        (this.textFieldmaxY = new GuiTextField(this.fontRendererObj, 130, 67, 20, 12))
            .setMaxStringLength(2);
        this.textFieldmaxY.setText(this.tileEntity.maxCoord.intY() + "");
        (this.textFieldmaxZ = new GuiTextField(this.fontRendererObj, 130, 82, 20, 12))
            .setMaxStringLength(2);
        this.textFieldmaxZ.setText(this.tileEntity.maxCoord.intZ() + "");
    }

    @Override
    protected void actionPerformed(final GuiButton par1GuiButton) {
        if (par1GuiButton.id == 0) {
            final TProximityDetector tileEntity = this.tileEntity;
            ++tileEntity.mode;

            if (this.tileEntity.mode > 2) {
                this.tileEntity.mode = 0;
            }

            ProximityDetectorModePacket pkt
                = new ProximityDetectorModePacket(new Vector3(this.tileEntity));

            pkt.mode = Optional.of(this.tileEntity.mode);

            ICBMContraption.channel.sendToServer(pkt);
        }
    }

    @Override
    public void keyTyped(final char par1, final int par2) {
        super.keyTyped(par1, par2);
        this.textFieldminX.textboxKeyTyped(par1, par2);
        this.textFieldminY.textboxKeyTyped(par1, par2);
        this.textFieldminZ.textboxKeyTyped(par1, par2);
        this.textFieldmaxX.textboxKeyTyped(par1, par2);
        this.textFieldmaxY.textboxKeyTyped(par1, par2);
        this.textFieldmaxZ.textboxKeyTyped(par1, par2);
        this.textFieldFreq.textboxKeyTyped(par1, par2);

        try {
            final Vector3 newMinCoord = new Vector3(
                Integer.parseInt(this.textFieldminX.getText()),
                Integer.parseInt(this.textFieldminY.getText()),
                Integer.parseInt(this.textFieldminZ.getText())
            );
            this.tileEntity.minCoord = newMinCoord;
            ProximityDetectorModePacket pkt
                = new ProximityDetectorModePacket(new Vector3(this.tileEntity));
            pkt.minCoord = Optional.of(this.tileEntity.minCoord);
            ICBMContraption.channel.sendToServer(pkt);
        } catch (final Exception ex) {}

        try {
            final Vector3 newMaxCoord = new Vector3(
                Integer.parseInt(this.textFieldmaxX.getText()),
                Integer.parseInt(this.textFieldmaxY.getText()),
                Integer.parseInt(this.textFieldmaxZ.getText())
            );
            this.tileEntity.maxCoord = newMaxCoord;

            ProximityDetectorModePacket pkt
                = new ProximityDetectorModePacket(new Vector3(this.tileEntity));
            pkt.maxCoord = Optional.of(this.tileEntity.maxCoord);
            ICBMContraption.channel.sendToServer(pkt);
        } catch (final Exception ex2) {}

        try {
            final short newFrequency
                = (short) Math.max(0, Short.parseShort(this.textFieldFreq.getText()));
            this.tileEntity.frequency = newFrequency;

            ProximityDetectorModePacket pkt
                = new ProximityDetectorModePacket(new Vector3(this.tileEntity));
            pkt.frequency = Optional.of(this.tileEntity.frequency);
            ICBMContraption.channel.sendToServer(pkt);
        } catch (final Exception ex3) {}
    }

    @Override
    public void mouseClicked(final int par1, final int par2, final int par3) {
        super.mouseClicked(par1, par2, par3);
        this.textFieldminX.mouseClicked(
            par1 - this.containerWidth, par2 - this.containerHeight, par3
        );
        this.textFieldminY.mouseClicked(
            par1 - this.containerWidth, par2 - this.containerHeight, par3
        );
        this.textFieldminZ.mouseClicked(
            par1 - this.containerWidth, par2 - this.containerHeight, par3
        );
        this.textFieldmaxX.mouseClicked(
            par1 - this.containerWidth, par2 - this.containerHeight, par3
        );
        this.textFieldmaxY.mouseClicked(
            par1 - this.containerWidth, par2 - this.containerHeight, par3
        );
        this.textFieldmaxZ.mouseClicked(
            par1 - this.containerWidth, par2 - this.containerHeight, par3
        );
        this.textFieldFreq.mouseClicked(
            par1 - this.containerWidth, par2 - this.containerHeight, par3
        );
    }

    @Override
    protected void drawForegroundLayer(final int var2, final int var3, final float var1) {
        this.fontRendererObj.drawString("§7Proximity Detector", 48, 6, 4210752);
        this.fontRendererObj.drawString("Detection Range", 12, 25, 4210752);
        this.fontRendererObj.drawString("Min", 75, 40, 4210752);
        this.fontRendererObj.drawString("Max", 130, 40, 4210752);
        this.fontRendererObj.drawString("X-Coord:", 15, 51, 4210752);
        this.fontRendererObj.drawString("Y-Coord:", 15, 68, 4210752);
        this.fontRendererObj.drawString("Z-Coord:", 15, 83, 4210752);
        this.textFieldminX.drawTextBox();
        this.textFieldminY.drawTextBox();
        this.textFieldminZ.drawTextBox();
        this.textFieldmaxX.drawTextBox();
        this.textFieldmaxY.drawTextBox();
        this.textFieldmaxZ.drawTextBox();
        this.fontRendererObj.drawString("Frequency:", 15, 102, 4210752);

        if (!this.tileEntity.isInverted) {
            this.fontRendererObj.drawString("Exclude", 120, 102, 4210752);
        } else {
            this.fontRendererObj.drawString("Include", 120, 102, 4210752);
        }

        this.fontRendererObj.drawString("Target:", 15, 120, 4210752);
        this.textFieldFreq.drawTextBox();
        String color = "§4";
        String status = "Idle";

        if (this.tileEntity.isDisabled()) {
            status = "Disabled";
        } else if (this.tileEntity.wattsForDisplay < this.tileEntity.getRequest().getWatts()) {
            status = "Insufficient electricity!";
        } else {
            color = "§2";
            status = "On";
        }

        this.fontRendererObj.drawString(color + "Status: " + status, 12, 138, 4210752);
        this.fontRendererObj.drawString(
            UnitDisplay.getDisplay(
                this.tileEntity.getRequest().getWatts() * 20.0,
                UnitDisplay.Unit.WATT
            ) + " "
                + UnitDisplay.getDisplay(
                    this.tileEntity.getVoltage(), UnitDisplay.Unit.VOLTAGE
                ),
            12,
            150,
            4210752
        );
    }

    @Override
    protected void drawBackgroundLayer(final int var2, final int var3, final float var1) {
        this.mc.renderEngine.bindTexture(
            new ResourceLocation("icbm", "textures/gui/gui_empty.png")
        );
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        this.containerWidth = (this.width - super.xSize) / 2;
        this.containerHeight = (this.height - super.ySize) / 2;
        this.drawTexturedModalRect(
            this.containerWidth, this.containerHeight, 0, 0, super.xSize, super.ySize
        );
    }

    @Override
    public void updateScreen() {
        super.updateScreen();
        String mode = "All";

        if (this.tileEntity.mode == 1) {
            mode = "Players";
        } else if (this.tileEntity.mode == 2) {
            mode = "Mobs";
        }

        ((GuiButton) this.buttonList.get(0)).displayString = mode;

        if (!this.textFieldminX.isFocused()) {
            this.textFieldminX.setText(this.tileEntity.minCoord.intX() + "");
        }

        if (!this.textFieldminY.isFocused()) {
            this.textFieldminY.setText(this.tileEntity.minCoord.intY() + "");
        }

        if (!this.textFieldminZ.isFocused()) {
            this.textFieldminZ.setText(this.tileEntity.minCoord.intZ() + "");
        }

        if (!this.textFieldmaxX.isFocused()) {
            this.textFieldmaxX.setText(this.tileEntity.maxCoord.intX() + "");
        }

        if (!this.textFieldmaxY.isFocused()) {
            this.textFieldmaxY.setText(this.tileEntity.maxCoord.intY() + "");
        }

        if (!this.textFieldmaxZ.isFocused()) {
            this.textFieldmaxZ.setText(this.tileEntity.maxCoord.intZ() + "");
        }

        if (!this.textFieldFreq.isFocused()) {
            this.textFieldFreq.setText(this.tileEntity.frequency + "");
        }
    }
}
