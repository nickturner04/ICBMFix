package icbm.gangshao.shimian;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import icbm.gangshao.ICBMSentry;
import icbm.gangshao.platform.TTurretPlatform;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import universalelectricity.prefab.GuiBase;

@SideOnly(Side.CLIENT)
public abstract class GuiPlatformBase extends GuiBase {
    protected static final int MAX_BUTTON_ID = 3;
    protected TTurretPlatform tileEntity;
    protected EntityPlayer entityPlayer;

    public GuiPlatformBase(final EntityPlayer player, final TTurretPlatform tileEntity) {
        this.tileEntity = tileEntity;
        this.entityPlayer = player;
        super.ySize = 190;
    }

    @Override
    public void initGui() {
        super.initGui();
        this.buttonList.clear();
        this.buttonList.add(new GuiButtonImage(
            0, (this.width - super.xSize) / 2 - 22, (this.height - super.ySize) / 2 + 0, 3
        ));
        this.buttonList.add(new GuiButtonImage(
            1,
            (this.width - super.xSize) / 2 - 22,
            (this.height - super.ySize) / 2 + 22,
            0
        ));
        this.buttonList.add(new GuiButtonImage(
            2,
            (this.width - super.xSize) / 2 - 22,
            (this.height - super.ySize) / 2 + 44,
            2
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
    protected void drawForegroundLayer(final int x, final int y, final float var1) {
        if (((GuiButtonImage) this.buttonList.get(0)).isIntersect(x, y)) {
            this.drawTooltip(x - super.guiLeft, y - super.guiTop + 10, "Terminal");
        } else if (((GuiButtonImage) this.buttonList.get(1)).isIntersect(x, y)) {
            this.drawTooltip(x - super.guiLeft, y - super.guiTop + 10, "Access");
        } else if (((GuiButtonImage) this.buttonList.get(2)).isIntersect(x, y)) {
            this.drawTooltip(x - super.guiLeft, y - super.guiTop + 10, "Ammunition");
        }
    }

    @Override
    protected void drawBackgroundLayer(final int x, final int y, final float var1) {
        this.mc.renderEngine.bindTexture(
            new ResourceLocation("icbm", "textures/gui/gui_base.png")
        );
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        final int containerWidth = (this.width - super.xSize) / 2;
        final int containerHeight = (this.height - super.ySize) / 2;
        this.drawTexturedModalRect(
            containerWidth, containerHeight, 0, 0, super.xSize, super.ySize
        );
    }

    @Override
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

            if (super.guiTop + var7 + var8 + 6 > this.height) {
                var7 = this.height - var8 - super.guiTop - 6;
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
