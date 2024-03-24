package icbm.wanyi.gui;

import icbm.api.IItemFrequency;
import icbm.wanyi.ICBMContraption;
import icbm.wanyi.SetSignalDisrupterFrequencyPacket;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import universalelectricity.prefab.GuiBase;

public class GSignalDisrupter extends GuiBase {
    private ItemStack itemStack;
    private GuiTextField textFieldFrequency;
    private int containerWidth;
    private int containerHeight;

    public GSignalDisrupter(final ItemStack par1ItemStack) {
        this.itemStack = par1ItemStack;
    }

    @Override
    public void initGui() {
        super.initGui();
        (this.textFieldFrequency = new GuiTextField(this.fontRendererObj, 80, 50, 40, 12))
            .setMaxStringLength(4);
        this.textFieldFrequency.setText(
            ((IItemFrequency) this.itemStack.getItem()).getFrequency(this.itemStack) + ""
        );
    }

    public void keyTyped(final char par1, final int par2) {
        super.keyTyped(par1, par2);
        this.textFieldFrequency.textboxKeyTyped(par1, par2);

        try {
            final short newFrequency = (short
            ) Math.max(0, Short.parseShort(this.textFieldFrequency.getText()));
            this.textFieldFrequency.setText(newFrequency + "");

            if (((IItemFrequency) this.itemStack.getItem()).getFrequency(this.itemStack)
                != newFrequency) {
                ((IItemFrequency) this.itemStack.getItem())
                    .setFrequency(newFrequency, this.itemStack);
                ICBMContraption.channel.sendToServer(
                    new SetSignalDisrupterFrequencyPacket(newFrequency)
                );
            }
        } catch (final NumberFormatException ex) {}
    }

    public void mouseClicked(final int par1, final int par2, final int par3) {
        super.mouseClicked(par1, par2, par3);
        this.textFieldFrequency.mouseClicked(
            par1 - this.containerWidth, par2 - this.containerHeight, par3
        );
    }

    @Override
    protected void drawForegroundLayer(final int var2, final int var3, final float var1) {
        this.fontRendererObj.drawString("§7Frequency", 62, 6, 4210752);
        this.fontRendererObj.drawString("Frequency:", 15, 52, 4210752);
        this.textFieldFrequency.drawTextBox();
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
}
