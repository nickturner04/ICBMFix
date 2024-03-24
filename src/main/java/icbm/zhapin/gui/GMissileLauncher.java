package icbm.zhapin.gui;

import icbm.zhapin.ICBMExplosion;
import icbm.zhapin.jiqi.LauncherControlPanelGuiPacket;
import icbm.zhapin.jiqi.TLauncherControlPanel;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import universalelectricity.api.energy.UnitDisplay;
import universalelectricity.core.vector.Vector3;
import universalelectricity.prefab.GuiBase;

public class GMissileLauncher extends GuiBase {
    private TLauncherControlPanel tileEntity;
    private GuiTextField tFX;
    private GuiTextField tFY;
    private GuiTextField tFZ;
    private GuiTextField tFFreq;
    private GuiTextField tFGaoDu;
    private int containerWidth;
    private int containerHeight;

    public GMissileLauncher(final TLauncherControlPanel par2ICBMTileEntityMissileLauncher
    ) {
        this.tileEntity = par2ICBMTileEntityMissileLauncher;
    }

    @Override
    public void initGui() {
        super.initGui();
        this.tFX = new GuiTextField(this.fontRendererObj, 110, 37, 45, 12);
        this.tFZ = new GuiTextField(this.fontRendererObj, 110, 52, 45, 12);
        this.tFY = new GuiTextField(this.fontRendererObj, 110, 67, 45, 12);
        this.tFGaoDu = new GuiTextField(this.fontRendererObj, 110, 82, 45, 12);
        (this.tFFreq = new GuiTextField(this.fontRendererObj, 110, 97, 45, 12))
            .setMaxStringLength(4);
        this.tFX.setMaxStringLength(6);
        this.tFZ.setMaxStringLength(6);
        this.tFY.setMaxStringLength(2);
        this.tFGaoDu.setMaxStringLength(2);
        this.tFFreq.setText(this.tileEntity.getFrequency() + "");
        this.tFGaoDu.setText(this.tileEntity.height + "");

        if (this.tileEntity.getTarget() == null) {
            this.tFX.setText(Math.round((float) this.tileEntity.xCoord) + "");
            this.tFZ.setText(Math.round((float) this.tileEntity.zCoord) + "");
            this.tFY.setText("0");
        } else {
            this.tFX.setText(Math.round(this.tileEntity.getTarget().x) + "");
            this.tFZ.setText(Math.round(this.tileEntity.getTarget().z) + "");
            this.tFY.setText(Math.round(this.tileEntity.getTarget().y) + "");
        }
    }

    @Override
    public void keyTyped(final char par1, final int par2) {
        super.keyTyped(par1, par2);
        this.tFX.textboxKeyTyped(par1, par2);
        this.tFZ.textboxKeyTyped(par1, par2);

        if (this.tileEntity.getTier() >= 1) {
            this.tFY.textboxKeyTyped(par1, par2);
            this.tFGaoDu.textboxKeyTyped(par1, par2);

            if (this.tileEntity.getTier() > 1) {
                this.tFFreq.textboxKeyTyped(par1, par2);
            }
        }

        try {
            final Vector3 newTarget = new Vector3(
                Integer.parseInt(this.tFX.getText()),
                Math.max(Integer.parseInt(this.tFY.getText()), 0),
                Integer.parseInt(this.tFZ.getText())
            );
            this.tileEntity.setTarget(newTarget);
            ICBMExplosion.channel.sendToServer(
                new LauncherControlPanelGuiPacket(this.tileEntity)
            );
        } catch (final NumberFormatException ex) {}

        try {
            final short newFrequency
                = (short) Math.max(Short.parseShort(this.tFFreq.getText()), 0);
            this.tileEntity.setFrequency(newFrequency);
            ICBMExplosion.channel.sendToServer(
                new LauncherControlPanelGuiPacket(this.tileEntity)
            );
        } catch (final NumberFormatException ex2) {}

        try {
            final short newGaoDu = (short
            ) Math.max(Math.min(Short.parseShort(this.tFGaoDu.getText()), 99), 3);
            this.tileEntity.height = newGaoDu;
            ICBMExplosion.channel.sendToServer(
                new LauncherControlPanelGuiPacket(this.tileEntity)
            );
        } catch (final NumberFormatException ex3) {}
    }

    @Override
    public void mouseClicked(final int par1, final int par2, final int par3) {
        super.mouseClicked(par1, par2, par3);
        this.tFX.mouseClicked(
            par1 - this.containerWidth, par2 - this.containerHeight, par3
        );
        this.tFZ.mouseClicked(
            par1 - this.containerWidth, par2 - this.containerHeight, par3
        );

        if (this.tileEntity.getTier() >= 1) {
            this.tFY.mouseClicked(
                par1 - this.containerWidth, par2 - this.containerHeight, par3
            );
            this.tFGaoDu.mouseClicked(
                par1 - this.containerWidth, par2 - this.containerHeight, par3
            );

            if (this.tileEntity.getTier() > 1) {
                this.tFFreq.mouseClicked(
                    par1 - this.containerWidth, par2 - this.containerHeight, par3
                );
            }
        }
    }

    @Override
    public void drawForegroundLayer(final int var2, final int var3, final float var1) {
        this.tFX.drawTextBox();
        this.tFZ.drawTextBox();

        if (this.tileEntity.getTier() >= 1) {
            this.tFY.drawTextBox();
            this.fontRendererObj.drawString("Detonation Height:", 12, 68, 4210752);
            this.tFGaoDu.drawTextBox();
            this.fontRendererObj.drawString("Lock Height:", 12, 83, 4210752);

            if (this.tileEntity.getTier() > 1) {
                this.tFFreq.drawTextBox();
                this.fontRendererObj.drawString("Frequency:", 12, 98, 4210752);
            }
        }

        this.fontRendererObj.drawString("", 45, 6, 4210752);
        this.fontRendererObj.drawString("§7Launcher Control Panel", 30, 6, 4210752);
        this.fontRendererObj.drawString("Missile Target", 12, 25, 4210752);
        this.fontRendererObj.drawString("X-Coord:", 25, 40, 4210752);
        this.fontRendererObj.drawString("Z-Coord:", 25, 55, 4210752);
        int inaccuracy = 30;

        if (this.tileEntity.faSheDi != null && this.tileEntity.faSheDi.jiaZi != null) {
            inaccuracy = this.tileEntity.faSheDi.jiaZi.getInaccuracy();
        }

        this.fontRendererObj.drawString(
            "Inaccuracy: " + inaccuracy + " blocks", 12, 113, 4210752
        );
        this.fontRendererObj.drawString(
            "Status: " + this.tileEntity.getStatus(), 12, 125, 4210752
        );
        this.fontRendererObj.drawString(
            "Voltage: " + this.tileEntity.getVoltage() + "v", 12, 137, 4210752
        );
        this.fontRendererObj.drawString(
            UnitDisplay.getDisplayShort(
                this.tileEntity.getJoules(), UnitDisplay.Unit.JOULES
            ) + "/"
                + UnitDisplay.getDisplayShort(
                    this.tileEntity.getMaxJoules(), UnitDisplay.Unit.JOULES
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

        if (!this.tFX.isFocused()) {
            this.tFX.setText(Math.round(this.tileEntity.getTarget().x) + "");
        }

        if (!this.tFZ.isFocused()) {
            this.tFZ.setText(Math.round(this.tileEntity.getTarget().z) + "");
        }

        if (!this.tFY.isFocused()) {
            this.tFY.setText(Math.round(this.tileEntity.getTarget().y) + "");
        }

        if (!this.tFGaoDu.isFocused()) {
            this.tFGaoDu.setText(this.tileEntity.height + "");
        }

        if (!this.tFFreq.isFocused()) {
            this.tFFreq.setText(this.tileEntity.getFrequency() + "");
        }
    }
}
