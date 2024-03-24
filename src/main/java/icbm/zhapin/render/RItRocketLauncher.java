package icbm.zhapin.render;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import icbm.zhapin.ICBMExplosion;
import icbm.zhapin.muoxing.jiqi.MShouFaSheQi;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RItRocketLauncher implements IItemRenderer {
    public static final MShouFaSheQi MODEL;

    public boolean
    handleRenderType(final ItemStack item, final IItemRenderer.ItemRenderType type) {
        return item.getItem() == ICBMExplosion.itFaSheQi;
    }

    public boolean shouldUseRenderHelper(
        final IItemRenderer.ItemRenderType type,
        final ItemStack item,
        final IItemRenderer.ItemRendererHelper helper
    ) {
        return item.getItem() == ICBMExplosion.itFaSheQi;
    }

    public void renderItem(
        final IItemRenderer.ItemRenderType type,
        final ItemStack item,
        final Object... data
    ) {
        GL11.glPushMatrix();
        FMLClientHandler.instance().getClient().renderEngine.bindTexture(
            new ResourceLocation("icbm", "textures/models/rocket_launcher.png")
        );

        if (type == IItemRenderer.ItemRenderType.INVENTORY) {
            GL11.glTranslatef(0.0f, 1.5f, 0.0f);
            GL11.glRotatef(180.0f, 0.0f, 0.0f, 1.0f);
            GL11.glScalef(0.8f, 1.0f, 0.8f);
            GL11.glTranslatef(0.0f, 0.3f, 0.0f);
        } else if (type == IItemRenderer.ItemRenderType.EQUIPPED) {
            boolean isThisEntity = false;
            final boolean isFirstPerson
                = Minecraft.getMinecraft().gameSettings.thirdPersonView == 0;

            if (data != null && data.length >= 2) {
                isThisEntity = (data[1] == Minecraft.getMinecraft().renderViewEntity);
            }

            if (isThisEntity && isFirstPerson) {
                GL11.glTranslatef(0.0f, 2.0f, 0.0f);
                GL11.glRotatef(180.0f, 0.0f, 0.0f, 1.0f);
                GL11.glRotatef(20.0f, 0.0f, 1.0f, 0.0f);
            } else {
                final float scale = 2.0f;
                GL11.glScalef(scale, scale, scale);
                GL11.glRotatef(-105.0f, 0.0f, 0.0f, 1.0f);
                GL11.glRotatef(-75.0f, 0.0f, 1.0f, 0.0f);
                GL11.glTranslatef(0.1f, -0.9f, 0.6f);
            }
        }

        RItRocketLauncher.MODEL.render(0.0625f);
        GL11.glPopMatrix();
    }

    static {
        MODEL = new MShouFaSheQi();
    }
}
