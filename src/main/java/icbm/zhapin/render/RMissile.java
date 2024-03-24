package icbm.zhapin.render;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import icbm.core.di.MICBM;
import icbm.zhapin.daodan.EMissile;
import icbm.zhapin.daodan.MissileBase;
import icbm.zhapin.muoxing.daodan.MMBingDan;
import icbm.zhapin.muoxing.daodan.MMChaoShengBuo;
import icbm.zhapin.muoxing.daodan.MMDianCi;
import icbm.zhapin.muoxing.daodan.MMDuQi;
import icbm.zhapin.muoxing.daodan.MMFanDan;
import icbm.zhapin.muoxing.daodan.MMFanWuSu;
import icbm.zhapin.muoxing.daodan.MMFenZiDan;
import icbm.zhapin.muoxing.daodan.MMGanRanDu;
import icbm.zhapin.muoxing.daodan.MMHongSu;
import icbm.zhapin.muoxing.daodan.MMHuanYuan;
import icbm.zhapin.muoxing.daodan.MMHuo;
import icbm.zhapin.muoxing.daodan.MMLa;
import icbm.zhapin.muoxing.daodan.MMLiZi;
import icbm.zhapin.muoxing.daodan.MMPiaoFu;
import icbm.zhapin.muoxing.daodan.MMQi;
import icbm.zhapin.muoxing.daodan.MMQunDan;
import icbm.zhapin.muoxing.daodan.MMShengBuo;
import icbm.zhapin.muoxing.daodan.MMTaiYang;
import icbm.zhapin.muoxing.daodan.MMTuPuo;
import icbm.zhapin.muoxing.daodan.MMTui;
import icbm.zhapin.muoxing.daodan.MMWan;
import icbm.zhapin.muoxing.daodan.MMXiaoQunDan;
import icbm.zhapin.muoxing.daodan.MMYaSuo;
import icbm.zhapin.muoxing.daodan.MMYuanZi;
import icbm.zhapin.muoxing.daodan.MMZhen;
import icbm.zhapin.muoxing.daodan.MMZhuiZhong;
import icbm.zhapin.zhapin.ZhaPin;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RMissile extends Render {
    public static final MICBM[] MODELS;
    public static MICBM[] SPECIAL_MODELS;

    public RMissile(final float f) {
        super.shadowSize = f;
    }

    @Override
    public void doRender(
        final Entity entity,
        final double x,
        final double y,
        final double z,
        final float f,
        final float f1
    ) {
        final EMissile entityMissile = (EMissile) entity;
        GL11.glPushMatrix();
        GL11.glTranslated(x, y, z);
        GL11.glRotatef(
            entityMissile.prevRotationYaw
                + (entityMissile.rotationYaw - entityMissile.prevRotationYaw) * f1
                - 90.0f,
            0.0f,
            1.0f,
            0.0f
        );
        GL11.glRotatef(
            entityMissile.prevRotationPitch
                + (entityMissile.rotationPitch - entityMissile.prevRotationPitch) * f1
                + 90.0f,
            0.0f,
            0.0f,
            1.0f
        );

        if (entityMissile.missileType == EMissile.MissileType.SMALL_MISSILE) {
            GL11.glScalef(0.5f, 0.5f, 0.5f);
        }

        if (entityMissile.missileId >= 100) {
            this.bindTexture(new ResourceLocation(
                "icbm",
                "textures/models/missile_"
                    + MissileBase.list[entityMissile.missileId].getUnlocalizedName()
                    + ".png"
            ));
            RMissile.SPECIAL_MODELS[entityMissile.missileId - 100].render(
                (Entity) entityMissile, (float) x, (float) y, (float) z, f, f1, 0.0625f
            );
        } else {
            this.bindTexture(new ResourceLocation(
                "icbm",
                "textures/models/missile_"
                    + ZhaPin.list[entityMissile.missileId].getUnlocalizedName() + ".png"
            ));
            RMissile.MODELS[entityMissile.missileId].render(
                (Entity) entityMissile, (float) x, (float) y, (float) z, f, f1, 0.0625f
            );
        }

        GL11.glPopMatrix();
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
        return null;
    }

    static {
        MODELS = new MICBM[] { new MMYaSuo(),    new MMXiaoQunDan(), new MMHuo(),
                               new MMQi(),       new MMDuQi(),       new MMZhen(),
                               new MMTui(),      new MMLa(),         new MMQunDan(),
                               new MMGanRanDu(), new MMShengBuo(),   new MMTuPuo(),
                               new MMHuanYuan(), new MMLiZi(),       new MMYuanZi(),
                               new MMDianCi(),   new MMTaiYang(),    new MMBingDan(),
                               new MMPiaoFu(),   new MMWan(),        new MMChaoShengBuo(),
                               new MMFanWuSu(),  new MMHongSu() };
        RMissile.SPECIAL_MODELS = new MICBM[] { new MMYaSuo(),
                                                new MMZhuiZhong(),
                                                new MMFanDan(),
                                                new MMFenZiDan(),
                                                new MMFenZiDan() };
    }
}
