package icbm.zhapin.render;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import icbm.zhapin.ICBMExplosion;
import icbm.zhapin.daodan.ItMissile;
import icbm.zhapin.daodan.MissileBase;
import icbm.zhapin.zhapin.ZhaPin;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RItDaoDan implements IItemRenderer {
    public boolean
    handleRenderType(final ItemStack item, final IItemRenderer.ItemRenderType type) {
        return this.shouldUseRenderHelper(type, item, null);
    }

    public boolean shouldUseRenderHelper(
        final IItemRenderer.ItemRenderType type,
        final ItemStack item,
        final IItemRenderer.ItemRendererHelper helper
    ) {
        return item.getItem() instanceof ItMissile;
    }

    public void renderItem(
        final IItemRenderer.ItemRenderType type,
        final ItemStack item,
        final Object... data
    ) {
        if (this.shouldUseRenderHelper(type, item, null)) {
            float scale = 0.7f;
            float right = 0.0f;

            if (type == IItemRenderer.ItemRenderType.INVENTORY) {
                scale = 0.4f;
                right = 0.15f;

                if (ZhaPin.list[item.getItemDamage()].getTier() == 2
                    || item.getItem() == ICBMExplosion.itTeBieDaoDan) {
                    scale /= 1.5f;
                } else if (ZhaPin.list[item.getItemDamage()].getTier() == 3) {
                    scale /= 1.7f;
                    right = 0.5f;
                } else if (ZhaPin.list[item.getItemDamage()].getTier() == 4) {
                    scale /= 1.4f;
                    right = 0.2f;
                }

                GL11.glTranslatef(right, 0.0f, 0.0f);
            }

            if (type == IItemRenderer.ItemRenderType.EQUIPPED) {
                GL11.glTranslatef(1.15f, 1.0f, 0.5f);
                GL11.glRotatef(180.0f, 0.0f, 0.0f, 1.0f);
            } else {
                GL11.glRotatef(-90.0f, 0.0f, 0.0f, 1.0f);
            }

            if (type == IItemRenderer.ItemRenderType.ENTITY) {
                scale /= 1.5f;
            }

            GL11.glScalef(scale, scale, scale);

            if (item.getItem() == ICBMExplosion.itTeBieDaoDan) {
                if (item.getItemDamage() < RMissile.SPECIAL_MODELS.length) {
                    FMLClientHandler.instance().getClient().renderEngine.bindTexture(
                        new ResourceLocation(
                            "icbm",
                            "textures/models/missile_"
                                + MissileBase.list[item.getItemDamage() + 100]
                                      .getUnlocalizedName()
                                + ".png"
                        )
                    );
                    RMissile.SPECIAL_MODELS[item.getItemDamage()].render(0.0625f);
                }
            } else {
                FMLClientHandler.instance().getClient().renderEngine.bindTexture(
                    new ResourceLocation(
                        "icbm",
                        "textures/models/missile_"
                            + ZhaPin.list[item.getItemDamage()].getUnlocalizedName()
                            + ".png"
                    )
                );
                RMissile.MODELS[item.getItemDamage()].render(0.0625f);
            }
        }
    }
}
