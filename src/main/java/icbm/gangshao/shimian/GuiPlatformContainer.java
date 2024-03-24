package icbm.gangshao.shimian;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import icbm.gangshao.ICBMSentry;
import icbm.gangshao.container.ContainerTurretPlatform;
import icbm.gangshao.platform.TTurretPlatform;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public abstract class GuiPlatformContainer extends GuiContainer {
    protected static final int MAX_BUTTON_ID = 3;
    protected TTurretPlatform tileEntity;
    protected EntityPlayer entityPlayer;

    public GuiPlatformContainer(
        final InventoryPlayer inventoryPlayer, final TTurretPlatform tileEntity
    ) {
        super((Container) new ContainerTurretPlatform(inventoryPlayer, tileEntity));
        this.tileEntity = tileEntity;
        this.entityPlayer = inventoryPlayer.player;
        this.ySize = 190;
    }

    @Override
    public void initGui() {
        super.initGui();
        this.buttonList.clear();
        this.buttonList.add(new GuiButtonImage(
            0, (this.width - this.xSize) / 2 - 22, (this.height - this.ySize) / 2 + 0, 3
        ));
        this.buttonList.add(new GuiButtonImage(
            1, (this.width - this.xSize) / 2 - 22, (this.height - this.ySize) / 2 + 22, 0
        ));
        this.buttonList.add(new GuiButtonImage(
            2, (this.width - this.xSize) / 2 - 22, (this.height - this.ySize) / 2 + 44, 2
        ));
    }

    @Override
    public void updateScreen() {
        super.updateScreen();

        if (this.tileEntity.getTurret(false) == null) {
            this.mc.thePlayer.closeScreen();
        }
    }

    @Override
    protected void actionPerformed(final GuiButton button) {
        if (this.tileEntity.getTurret(false) != null) {
            switch (button.id) {
                case 0: {
                    this.entityPlayer.openGui(
                        (Object) ICBMSentry.instance,
                        1,
                        this.tileEntity.getWorldObj(),
                        this.tileEntity.xCoord,
                        this.tileEntity.yCoord,
                        this.tileEntity.zCoord
                    );
                    break;
                }

                case 1: {
                    this.entityPlayer.openGui(
                        (Object) ICBMSentry.instance,
                        2,
                        this.tileEntity.getWorldObj(),
                        this.tileEntity.xCoord,
                        this.tileEntity.yCoord,
                        this.tileEntity.zCoord
                    );
                    break;
                }

                case 2: {
                    this.entityPlayer.openGui(
                        (Object) ICBMSentry.instance,
                        0,
                        this.tileEntity.getWorldObj(),
                        this.tileEntity.xCoord,
                        this.tileEntity.yCoord,
                        this.tileEntity.zCoord
                    );
                    break;
                }
            }
        }
    }

    @Override
    protected void drawGuiContainerForegroundLayer(final int x, final int y) {
        if (this.tileEntity.getTurret(false) != null) {
            final String title = this.tileEntity.getTurret(false).getName();
            this.fontRendererObj.drawString(
                "§7" + title, (int) (this.xSize / 2 - title.length() * 2.5), 4, 4210752
            );

            if (((GuiButtonImage) this.buttonList.get(0)).isIntersect(x, y)) {
                this.drawTooltip(x - this.guiLeft, y - this.guiTop + 10, "Terminal");
            } else if (((GuiButtonImage) this.buttonList.get(1)).isIntersect(x, y)) {
                this.drawTooltip(x - this.guiLeft, y - this.guiTop + 10, "Access");
            } else if (((GuiButtonImage) this.buttonList.get(2)).isIntersect(x, y)) {
                this.drawTooltip(x - this.guiLeft, y - this.guiTop + 10, "Ammunition");
            }
        }
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(final float par1, final int x, final int y) {
        this.mc.renderEngine.bindTexture(
            new ResourceLocation("icbm", "textures/gui/gui_base.png")
        );
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        final int containerWidth = (this.width - this.xSize) / 2;
        final int containerHeight = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(
            containerWidth, containerHeight, 0, 0, this.xSize, this.ySize
        );
    }

    public void drawTooltip(final int x, final int y, final String... toolTips) {
        GL11.glDisable(32826);
        RenderHelper.disableStandardItemLighting();
        GL11.glDisable(2896);
        GL11.glDisable(2929);

        if (toolTips != null) {
            int var5 = 0;

            for (int var6 = 0; var6 < toolTips.length; ++var6) {
                final int var7 = this.fontRendererObj.getStringWidth(toolTips[var6]);

                if (var7 > var5) {
                    var5 = var7;
                }
            }

            int var6 = x + 12;
            int var7 = y - 12;
            int var8 = 8;

            if (toolTips.length > 1) {
                var8 += 2 + (toolTips.length - 1) * 10;
            }

            if (this.guiTop + var7 + var8 + 6 > this.height) {
                var7 = this.height - var8 - this.guiTop - 6;
            }

            this.zLevel = 300.0f;
            final int var9 = -267386864;
            this.drawGradientRect(
                var6 - 3, var7 - 4, var6 + var5 + 3, var7 - 3, var9, var9
            );
            this.drawGradientRect(
                var6 - 3, var7 + var8 + 3, var6 + var5 + 3, var7 + var8 + 4, var9, var9
            );
            this.drawGradientRect(
                var6 - 3, var7 - 3, var6 + var5 + 3, var7 + var8 + 3, var9, var9
            );
            this.drawGradientRect(
                var6 - 4, var7 - 3, var6 - 3, var7 + var8 + 3, var9, var9
            );
            this.drawGradientRect(
                var6 + var5 + 3, var7 - 3, var6 + var5 + 4, var7 + var8 + 3, var9, var9
            );
            final int var10 = 1347420415;
            final int var11 = (var10 & 0xFEFEFE) >> 1 | (var10 & 0xFF000000);
            this.drawGradientRect(
                var6 - 3, var7 - 3 + 1, var6 - 3 + 1, var7 + var8 + 3 - 1, var10, var11
            );
            this.drawGradientRect(
                var6 + var5 + 2,
                var7 - 3 + 1,
                var6 + var5 + 3,
                var7 + var8 + 3 - 1,
                var10,
                var11
            );
            this.drawGradientRect(
                var6 - 3, var7 - 3, var6 + var5 + 3, var7 - 3 + 1, var10, var10
            );
            this.drawGradientRect(
                var6 - 3, var7 + var8 + 2, var6 + var5 + 3, var7 + var8 + 3, var11, var11
            );

            for (int var12 = 0; var12 < toolTips.length; ++var12) {
                final String var13 = "§7" + toolTips[var12];
                this.fontRendererObj.drawStringWithShadow(var13, var6, var7, -1);

                if (var12 == 0) {
                    var7 += 2;
                }

                var7 += 10;
            }

            this.zLevel = 0.0f;
        }
    }
}
