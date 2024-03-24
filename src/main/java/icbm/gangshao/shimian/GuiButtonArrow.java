package icbm.gangshao.shimian;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class GuiButtonArrow extends GuiButton {
    boolean isLeft;

    public GuiButtonArrow(
        final int par1, final int par2, final int par3, final boolean left
    ) {
        super(par1, par2, par3, 10, 10, "");
        this.isLeft = false;
        this.isLeft = left;
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
        int var5 = 126;
        int varWid = 20;

        if (this.isLeft) {
            varWid += 10;
        }

        if (var4) {
            var5 += this.height;
        }

        this.drawTexturedModalRect(
            this.xPosition, this.yPosition, varWid, var5, this.width, this.height
        );
    }
}
