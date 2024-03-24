package icbm.zhapin.gui;

import icbm.zhapin.ICBMExplosion;
import icbm.zhapin.jiqi.EmpTowerGuiPacket;
import icbm.zhapin.jiqi.TEmpTower;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import universalelectricity.api.energy.UnitDisplay;
import universalelectricity.core.vector.Vector3;
import universalelectricity.prefab.GuiBase;

public class GEmpTower extends GuiBase {
    private TEmpTower tileEntity;
    private GuiTextField textFieldBanJing;
    private int containerWidth;
    private int containerHeight;

    public GEmpTower(final TEmpTower tileEntity) {
        this.tileEntity = tileEntity;
    }

    @Override
    public void initGui() {
        super.initGui();
        this.buttonList.clear();
        this.buttonList.add(new GuiButton(
            0, this.width / 2 - 77, this.height / 2 - 10, 50, 20, "Missiles"
        ));
        this.buttonList.add(new GuiButton(
            1, this.width / 2 - 25, this.height / 2 - 10, 65, 20, "Electricity"
        ));
        this.buttonList.add(
            new GuiButton(2, this.width / 2 + 43, this.height / 2 - 10, 35, 20, "Both")
        );
        (this.textFieldBanJing = new GuiTextField(this.fontRendererObj, 72, 28, 30, 12))
            .setMaxStringLength(3);
        this.textFieldBanJing.setText(this.tileEntity.radius + "");
    }

    @Override
    protected void actionPerformed(final GuiButton par1GuiButton) {
        switch (par1GuiButton.id) {
            case 0: {
                this.tileEntity.holzOhJa = 1;
                break;
            }

            case 1: {
                this.tileEntity.holzOhJa = 2;
                break;
            }

            case 2: {
                this.tileEntity.holzOhJa = 0;
                break;
            }
        }

        ICBMExplosion.channel.sendToServer(new EmpTowerGuiPacket(
            new Vector3(this.tileEntity), this.tileEntity.radius, this.tileEntity.holzOhJa
        ));
    }

    @Override
    public void keyTyped(final char par1, final int par2) {
        super.keyTyped(par1, par2);
        this.textFieldBanJing.textboxKeyTyped(par1, par2);

        try {
            final int radius = Math.min(
                Math.max(Integer.parseInt(this.textFieldBanJing.getText()), 10), 150
            );
            this.tileEntity.radius = radius;
            ICBMExplosion.channel.sendToServer(new EmpTowerGuiPacket(
                new Vector3(this.tileEntity),
                this.tileEntity.radius,
                this.tileEntity.holzOhJa
            ));
        } catch (final NumberFormatException ex) {}
    }

    @Override
    public void mouseClicked(final int par1, final int par2, final int par3) {
        super.mouseClicked(par1, par2, par3);
        this.textFieldBanJing.mouseClicked(
            par1 - this.containerWidth, par2 - this.containerHeight, par3
        );
    }

    @Override
    protected void drawForegroundLayer(final int var2, final int var3, final float var1) {
        this.fontRendererObj.drawString("§7EMP Tower", 65, 6, 4210752);
        this.fontRendererObj.drawString("EMP Radius:          blocks", 12, 30, 4210752);
        this.textFieldBanJing.drawTextBox();
        this.fontRendererObj.drawString("EMP Effect:", 12, 55, 4210752);
        String mode = "Debilitate Electronics";

        if (this.tileEntity.holzOhJa == 1) {
            mode = "Disrupt Missiles";
        } else if (this.tileEntity.holzOhJa == 2) {
            mode = "Deplete Electricity";
        }

        this.fontRendererObj.drawString("Mode: " + mode, 12, 105, 4210752);
        String color = "§4";
        String status = "Idle";

        if (this.tileEntity.isDisabled()) {
            status = "Disabled";
        } else if (this.tileEntity.getJoules() < this.tileEntity.getMaxJoules()) {
            status = "Insufficient electricity!";
        } else {
            color = "§2";
            status = "Ready to blast!";
        }

        this.fontRendererObj.drawString(color + "Status: " + status, 12, 120, 4210752);
        this.fontRendererObj.drawString(
            "Voltage: " + this.tileEntity.getVoltage() + "v", 12, 135, 4210752
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

        if (!this.textFieldBanJing.isFocused()) {
            this.textFieldBanJing.setText(this.tileEntity.radius + "");
        }
    }
}
