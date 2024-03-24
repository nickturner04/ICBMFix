package icbm.gangshao.shimian;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import icbm.gangshao.platform.TTurretPlatform;
import icbm.gangshao.terminal.TileEntityTerminal;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class GuiPlatformTerminal extends GuiPlatformBase {
    private TileEntityTerminal tileEntity;
    private GuiTextField commandLine;

    public GuiPlatformTerminal(
        final EntityPlayer entityPlayer, final TTurretPlatform tileEntity
    ) {
        super(entityPlayer, tileEntity);
        this.tileEntity = tileEntity;
    }

    @Override
    public void initGui() {
        super.initGui();
        final int width = (this.width - super.xSize) / 2;
        final int height = (this.height - super.ySize) / 2;
        (this.commandLine
         = new GuiTextField(this.fontRendererObj, width + 12, height + 165, 135, 11))
            .setMaxStringLength(30);
        this.buttonList.add(new GuiButtonArrow(4, width + 151, height + 21, false));
        this.buttonList.add(new GuiButtonArrow(5, width + 151, height + 152, true));
        Keyboard.enableRepeatEvents(true);
    }

    @Override
    public void onGuiClosed() {
        super.onGuiClosed();
        Keyboard.enableRepeatEvents(false);
    }

    @Override
    public void updateScreen() {
        super.updateScreen();
        this.commandLine.setFocused(true);
    }

    public void handleMouseInput() {
        super.handleMouseInput();
        final int wheel = Mouse.getEventDWheel();

        if (wheel > 0) {
            this.tileEntity.scroll(-2);
        } else if (wheel < 0) {
            this.tileEntity.scroll(2);
        }
    }

    @Override
    protected void actionPerformed(final GuiButton button) {
        super.actionPerformed(button);

        switch (button.id) {
            case 4: {
                this.tileEntity.scroll(-1);
                break;
            }

            case 5: {
                this.tileEntity.scroll(1);
                break;
            }
        }
    }

    @Override
    protected void keyTyped(final char character, final int keycode) {
        if (keycode == 1) {
            this.mc.thePlayer.closeScreen();
        } else if (keycode == 200) {
            this.tileEntity.scroll(-1);
        } else if (keycode == 208) {
            this.tileEntity.scroll(1);
        } else if (keycode == 28) {
            this.tileEntity.sendCommandToServer(super.entityPlayer,
                    this.commandLine.getText());
            this.commandLine.setText("");
        } else {
            this.commandLine.textboxKeyTyped(character, keycode);
        }
    }

    protected void mouseClicked(final int par1, final int par2, final int par3) {
        super.mouseClicked(par1, par2, par3);
        this.commandLine.mouseClicked(par1, par2, par3);
    }

    @Override
    protected void drawForegroundLayer(final int x, final int y, final float var1) {
        final String title = "Terminal";
        this.fontRendererObj.drawString(
            "§7" + title, (int) (super.xSize / 2 - title.length() * 2.5), 4, 4210752
        );
        this.drawConsole(25, 16, 15);
        super.drawForegroundLayer(x, y, var1);
    }

    public void drawConsole(final int x, final int y, final int lines) {
        final int spacing = 10;
        final int color = 14737632;
        GL11.glPushMatrix();
        final float scale = 0.92f;
        GL11.glScalef(scale, scale, scale);

        for (int i = 0; i < lines; ++i) {
            final int currentLine = i + this.tileEntity.getScroll();

            if (currentLine < this.tileEntity.getTerminalOuput().size()
                && currentLine >= 0) {
                final String line = this.tileEntity.getTerminalOuput().get(currentLine);

                if (line != null && line != "") {
                    this.fontRendererObj.drawString(line, y, spacing * i + x, color);
                }
            }
        }

        GL11.glPopMatrix();
    }

    @Override
    protected void drawBackgroundLayer(final int x, final int y, final float var1) {
        super.drawBackgroundLayer(x, y, var1);
        this.mc.renderEngine.bindTexture(
            new ResourceLocation("icbm", "textures/gui/gui_platform_terminal.png")
        );
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        final int var2 = (this.width - super.xSize) / 2;
        final int var3 = (this.height - super.ySize) / 2;
        this.drawTexturedModalRect(var2, var3, 0, 0, super.xSize, super.ySize);
        this.commandLine.drawTextBox();
    }
}
