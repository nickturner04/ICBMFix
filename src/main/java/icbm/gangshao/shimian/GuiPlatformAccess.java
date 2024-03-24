package icbm.gangshao.shimian;

import java.util.HashMap;
import java.util.Map;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import icbm.gangshao.access.AccessLevel;
import icbm.gangshao.access.UserAccess;
import icbm.gangshao.platform.TTurretPlatform;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import universalelectricity.core.vector.Vector2;
import universalelectricity.prefab.vector.Region2;

@SideOnly(Side.CLIENT)
public class GuiPlatformAccess extends GuiPlatformBase implements IScroll {
    private GuiTextField commandLine;
    private int scroll;
    private final HashMap<UserAccess, Vector2> outputMap;

    public GuiPlatformAccess(
        final EntityPlayer entityPlayer, final TTurretPlatform tileEntity
    ) {
        super(entityPlayer, tileEntity);
        this.scroll = 0;
        this.outputMap = new HashMap<>();
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

    @Override
    public void handleMouseInput() {
        super.handleMouseInput();
        final int wheel = Mouse.getEventDWheel();

        if (wheel > 0) {
            this.scroll(-2);
        } else if (wheel < 0) {
            this.scroll(2);
        }
    }

    @Override
    protected void actionPerformed(final GuiButton button) {
        super.actionPerformed(button);

        switch (button.id) {
            case 4: {
                this.scroll(-1);
                break;
            }

            case 5: {
                this.scroll(1);
                break;
            }
        }
    }

    @Override
    protected void keyTyped(final char character, final int keycode) {
        if (keycode == 1) {
            this.mc.thePlayer.closeScreen();
        } else if (keycode == 200) {
            this.scroll(-1);
        } else if (keycode == 208) {
            this.scroll(1);
        } else if (keycode == 28) {
            String command = "users add";
            final String username = this.commandLine.getText();

            for (final UserAccess access : super.tileEntity.getUsers()) {
                if (access.username.equalsIgnoreCase(username)) {
                    command = "users remove";
                    break;
                }
            }

            super.tileEntity.sendCommandToServer(
                super.entityPlayer, command + " " + username
            );
            this.commandLine.setText("");
        } else {
            this.commandLine.textboxKeyTyped(character, keycode);
        }
    }

    protected void mouseClicked(final int x, final int y, final int type) {
        super.mouseClicked(x, y, type);

        if (type == 0) {
            for (final Map.Entry<UserAccess, Vector2> entry : this.outputMap.entrySet()) {
                final Vector2 vector2;
                final Vector2 minPos = vector2 = entry.getValue();
                vector2.x -= 2.0;
                final Vector2 vector3 = minPos;
                vector3.y -= 2.0;
                final Vector2 clone;
                final Vector2 maxPos = clone = minPos.clone();
                clone.x += 132.0;
                final Vector2 vector4 = maxPos;
                vector4.y += 12.0;

                if (new Region2(minPos, maxPos)
                        .isIn(new Vector2(x - super.guiLeft, y - super.guiTop))) {
                    final UserAccess access = entry.getKey();
                    int newLevelOrdinal = access.level.ordinal() + 1;

                    if (newLevelOrdinal >= AccessLevel.values().length) {
                        newLevelOrdinal -= AccessLevel.values().length;
                    }

                    if (newLevelOrdinal <= 0) {
                        newLevelOrdinal = 1;
                    }

                    final AccessLevel newLevel = AccessLevel.get(newLevelOrdinal);
                    super.tileEntity.sendCommandToServer(
                        super.entityPlayer,
                        "access set " + access.username + " " + newLevel.displayName
                    );
                    break;
                }
            }
        }

        this.commandLine.mouseClicked(x, y, type);
    }

    @Override
    protected void drawForegroundLayer(final int x, final int y, final float var1) {
        final String title = "User Access";
        this.fontRendererObj.drawString(
            "§7" + title, super.xSize / 2 - title.length() * 3, 4, 4210752
        );
        this.drawConsole(15, 25, 15);
        super.drawForegroundLayer(x, y, var1);
    }

    public void drawConsole(final int x, final int y, final int lines) {
        final int color = 14737632;
        this.outputMap.clear();

        for (int i = 0; i < lines; ++i) {
            final int currentLine = i + this.getScroll();

            if (currentLine < super.tileEntity.getUsers().size() && currentLine >= 0) {
                final UserAccess accesInfo = super.tileEntity.getUsers().get(currentLine);
                final String line
                    = accesInfo.username + " (" + accesInfo.level.displayName + ")";

                if (line != null && !line.isEmpty()) {
                    final Vector2 drawPosition = new Vector2(x, 10 * i + y);
                    this.outputMap.put(accesInfo, drawPosition);
                    this.fontRendererObj.drawString(
                        line, drawPosition.intX(), drawPosition.intY(), color
                    );
                }
            }
        }
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

    @Override
    public void scroll(final int amount) {
        this.setScroll(this.scroll + amount);
    }

    @Override
    public void setScroll(final int length) {
        this.scroll = Math.max(Math.min(length, super.tileEntity.getUsers().size()), 0);
    }

    @Override
    public int getScroll() {
        return this.scroll;
    }
}
