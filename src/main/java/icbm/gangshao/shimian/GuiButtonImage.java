package icbm.gangshao.shimian;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class GuiButtonImage extends GuiButton {
    private int type;

    public GuiButtonImage(
        final int par1, final int par2, final int par3, final int type
    ) {
        super(par1, par2, par3, 20, 20, "");
        this.type = 0;
        this.type = type;
    }

    @Override
    public void
    drawButton(final Minecraft par1Minecraft, final int width, final int hight) {
        par1Minecraft.renderEngine.bindTexture(
            new ResourceLocation("icbm", "textures/gui/gui@.png")
        );
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        final boolean var4 = width >= this.xPosition && hight >= this.yPosition
            && width < this.xPosition + this.width
            && hight < this.yPosition + this.height;
        int var5 = 106;
        int var6 = 0;

        if (var4) {
            var5 += this.height;
        }

        switch (this.type) {
            case 0: {
                var5 += 40;
                break;
            }

            case 1: {
                var5 += 40;
                var6 += 20;
                break;
            }

            case 2: {
                var5 += 40;
                var6 += 40;
                break;
            }
        }

        this.drawTexturedModalRect(
            this.xPosition, this.yPosition, var6, var5, this.width, this.height
        );
    }

    public boolean isIntersect(final int x, final int y) {
        return x >= this.xPosition && y >= this.yPosition
            && x < this.xPosition + this.width && y < this.yPosition + this.height;
    }
}
