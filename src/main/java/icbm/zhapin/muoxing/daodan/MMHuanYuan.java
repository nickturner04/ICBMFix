package icbm.zhapin.muoxing.daodan;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import icbm.core.di.MICBM;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

@SideOnly(Side.CLIENT)
public class MMHuanYuan extends MICBM {
    ModelRenderer MAIN_MODULE;
    ModelRenderer WING_B1;
    ModelRenderer WING_B2;
    ModelRenderer WING_B3;
    ModelRenderer WING_B4;
    ModelRenderer WING_BA1;
    ModelRenderer WING_BB1;
    ModelRenderer WING_BA2;
    ModelRenderer WING_BB2;
    ModelRenderer WING_TA1;
    ModelRenderer WING_TB1;
    ModelRenderer WING_TC3;
    ModelRenderer WING_TD1;
    ModelRenderer WING_TAC2;
    ModelRenderer WING_TBD2;
    ModelRenderer WING_TC1;
    ModelRenderer WING_TA3;
    ModelRenderer WING_TD3;
    ModelRenderer WING_TB3;
    ModelRenderer T_SUPPORT_1;
    ModelRenderer T_SUPPORT_2;
    ModelRenderer WARHEAD_SUPPORT_A1;
    ModelRenderer WARHEAD_SUPPORT_B1;
    ModelRenderer WARHEAD_SUPPORT_C1;
    ModelRenderer WRHEAD_SUPPORT_D1;
    ModelRenderer WARHEAD_SUPPORT_A2;
    ModelRenderer WARHEAD_SUPPORT_C2;
    ModelRenderer WARHEAD_SUPPORT_D2;
    ModelRenderer WARHEAD_SUPPORT_B2;
    ModelRenderer Shape8;
    ModelRenderer Shape9;

    public MMHuanYuan() {
        super.textureWidth = 128;
        super.textureHeight = 128;
        (this.MAIN_MODULE = new ModelRenderer((ModelBase) this, 0, 0))
            .addBox(-4.0f, 0.0f, -4.0f, 8, 64, 8);
        this.MAIN_MODULE.setRotationPoint(0.0f, -40.0f, 0.0f);
        this.MAIN_MODULE.setTextureSize(128, 128);
        this.MAIN_MODULE.mirror = true;
        this.setRotation(this.MAIN_MODULE, 0.0f, 0.0f, 0.0f);
        (this.WING_B1 = new ModelRenderer((ModelBase) this, 34, 19))
            .addBox(0.0f, 0.0f, 0.0f, 14, 10, 1);
        this.WING_B1.setRotationPoint(-7.0f, 14.0f, 6.0f);
        this.WING_B1.setTextureSize(128, 128);
        this.WING_B1.mirror = true;
        this.setRotation(this.WING_B1, 0.0f, 0.0f, 0.0f);
        (this.WING_B2 = new ModelRenderer((ModelBase) this, 34, 19))
            .addBox(0.0f, 0.0f, 0.0f, 14, 10, 1);
        this.WING_B2.setRotationPoint(-7.0f, 14.0f, -7.0f);
        this.WING_B2.setTextureSize(128, 128);
        this.WING_B2.mirror = true;
        this.setRotation(this.WING_B2, 0.0f, 0.0f, 0.0f);
        (this.WING_B3 = new ModelRenderer((ModelBase) this, 34, 31))
            .addBox(0.0f, 0.0f, 0.0f, 1, 10, 12);
        this.WING_B3.setRotationPoint(-7.0f, 14.0f, -6.0f);
        this.WING_B3.setTextureSize(128, 128);
        this.WING_B3.mirror = true;
        this.setRotation(this.WING_B3, 0.0f, 0.0f, 0.0f);
        (this.WING_B4 = new ModelRenderer((ModelBase) this, 34, 31))
            .addBox(0.0f, 0.0f, 0.0f, 1, 10, 12);
        this.WING_B4.setRotationPoint(6.0f, 14.0f, -6.0f);
        this.WING_B4.setTextureSize(128, 128);
        this.WING_B4.mirror = true;
        this.setRotation(this.WING_B4, 0.0f, 0.0f, 0.0f);
        (this.WING_BA1 = new ModelRenderer((ModelBase) this, 72, 0))
            .addBox(-11.0f, 0.0f, -1.0f, 22, 12, 2);
        this.WING_BA1.setRotationPoint(0.0f, 12.0f, 0.0f);
        this.WING_BA1.setTextureSize(128, 128);
        this.WING_BA1.mirror = true;
        this.setRotation(this.WING_BA1, 0.0f, 0.7853982f, 0.0f);
        (this.WING_BB1 = new ModelRenderer((ModelBase) this, 72, 0))
            .addBox(-11.0f, 0.0f, -1.0f, 22, 12, 2);
        this.WING_BB1.setRotationPoint(0.0f, 12.0f, 0.0f);
        this.WING_BB1.setTextureSize(128, 128);
        this.WING_BB1.mirror = true;
        this.setRotation(this.WING_BB1, 0.0f, -0.7853982f, 0.0f);
        (this.WING_BA2 = new ModelRenderer((ModelBase) this, 34, 0))
            .addBox(-8.0f, -8.0f, -1.0f, 16, 16, 2);
        this.WING_BA2.setRotationPoint(0.0f, 12.0f, 0.0f);
        this.WING_BA2.setTextureSize(128, 128);
        this.WING_BA2.mirror = true;
        this.setRotation(this.WING_BA2, 0.0f, 0.7853982f, 0.7853982f);
        (this.WING_BB2 = new ModelRenderer((ModelBase) this, 34, 0))
            .addBox(-8.0f, -8.0f, -1.0f, 16, 16, 2);
        this.WING_BB2.setRotationPoint(0.0f, 12.0f, 0.0f);
        this.WING_BB2.setTextureSize(128, 128);
        this.WING_BB2.mirror = true;
        this.setRotation(this.WING_BB2, 0.0f, -0.7853982f, 0.7853982f);
        (this.WING_TA1 = new ModelRenderer((ModelBase) this, 22, 74))
            .addBox(0.0f, 0.0f, 0.0f, 2, 7, 5);
        this.WING_TA1.setRotationPoint(-1.0f, -23.0f, 4.0f);
        this.WING_TA1.setTextureSize(128, 128);
        this.WING_TA1.mirror = true;
        this.setRotation(this.WING_TA1, -0.5235988f, 0.0f, 0.0f);
        (this.WING_TB1 = new ModelRenderer((ModelBase) this, 0, 103))
            .addBox(0.0f, 0.0f, 0.0f, 5, 8, 2);
        this.WING_TB1.setRotationPoint(4.0f, -23.0f, -1.0f);
        this.WING_TB1.setTextureSize(128, 128);
        this.WING_TB1.mirror = true;
        this.setRotation(this.WING_TB1, 0.0f, 0.0f, 0.5235988f);
        (this.WING_TC3 = new ModelRenderer((ModelBase) this, 0, 81))
            .addBox(-1.0f, 0.0f, 0.0f, 2, 4, 5);
        this.WING_TC3.setRotationPoint(0.0f, -35.0f, -4.0f);
        this.WING_TC3.setTextureSize(128, 128);
        this.WING_TC3.mirror = true;
        this.setRotation(this.WING_TC3, -0.5235988f, 3.141593f, 0.0f);
        (this.WING_TD1 = new ModelRenderer((ModelBase) this, 0, 103))
            .addBox(0.0f, 0.0f, -1.0f, 5, 8, 2);
        this.WING_TD1.setRotationPoint(-4.0f, -23.0f, 0.0f);
        this.WING_TD1.setTextureSize(128, 128);
        this.WING_TD1.mirror = true;
        this.setRotation(this.WING_TD1, 0.0f, 3.141593f, 0.5235988f);
        (this.WING_TAC2 = new ModelRenderer((ModelBase) this, 0, 74))
            .addBox(-1.0f, 0.0f, -8.0f, 2, 12, 16);
        this.WING_TAC2.setRotationPoint(0.0f, -32.0f, 0.0f);
        this.WING_TAC2.setTextureSize(128, 128);
        this.WING_TAC2.mirror = true;
        this.setRotation(this.WING_TAC2, 0.0f, 0.0f, 0.0f);
        (this.WING_TBD2 = new ModelRenderer((ModelBase) this, 0, 74))
            .addBox(-1.0f, 0.0f, -8.0f, 2, 12, 16);
        this.WING_TBD2.setRotationPoint(0.0f, -32.0f, 0.0f);
        this.WING_TBD2.setTextureSize(128, 128);
        this.WING_TBD2.mirror = true;
        this.setRotation(this.WING_TBD2, 0.0f, 1.570796f, 0.0f);
        (this.WING_TC1 = new ModelRenderer((ModelBase) this, 22, 74))
            .addBox(-1.0f, 0.0f, 0.0f, 2, 8, 5);
        this.WING_TC1.setRotationPoint(0.0f, -23.0f, -4.0f);
        this.WING_TC1.setTextureSize(128, 128);
        this.WING_TC1.mirror = true;
        this.setRotation(this.WING_TC1, -0.5235988f, 3.141593f, 0.0f);
        (this.WING_TA3 = new ModelRenderer((ModelBase) this, 0, 81))
            .addBox(-1.0f, 0.0f, 0.0f, 2, 4, 5);
        this.WING_TA3.setRotationPoint(0.0f, -35.0f, 4.0f);
        this.WING_TA3.setTextureSize(128, 128);
        this.WING_TA3.mirror = true;
        this.setRotation(this.WING_TA3, -0.5235988f, 0.0f, 0.0f);
        (this.WING_TD3 = new ModelRenderer((ModelBase) this, 0, 74))
            .addBox(0.0f, 0.0f, -1.0f, 5, 4, 2);
        this.WING_TD3.setRotationPoint(-4.0f, -35.0f, 0.0f);
        this.WING_TD3.setTextureSize(128, 128);
        this.WING_TD3.mirror = true;
        this.setRotation(this.WING_TD3, 0.0f, 3.141593f, 0.5235988f);
        (this.WING_TB3 = new ModelRenderer((ModelBase) this, 0, 74))
            .addBox(0.0f, 0.0f, 0.0f, 5, 4, 2);
        this.WING_TB3.setRotationPoint(4.0f, -35.0f, -1.0f);
        this.WING_TB3.setTextureSize(128, 128);
        this.WING_TB3.mirror = true;
        this.setRotation(this.WING_TB3, 0.0f, 0.0f, 0.5235988f);
        (this.T_SUPPORT_1 = new ModelRenderer((ModelBase) this, 72, 16))
            .addBox(0.0f, 0.0f, 0.0f, 10, 1, 10);
        this.T_SUPPORT_1.setRotationPoint(-5.0f, -37.0f, -5.0f);
        this.T_SUPPORT_1.setTextureSize(128, 128);
        this.T_SUPPORT_1.mirror = true;
        this.setRotation(this.T_SUPPORT_1, 0.0f, 0.0f, 0.0f);
        (this.T_SUPPORT_2 = new ModelRenderer((ModelBase) this, 72, 16))
            .addBox(0.0f, 0.0f, 0.0f, 10, 1, 10);
        this.T_SUPPORT_2.setRotationPoint(-5.0f, -41.0f, -5.0f);
        this.T_SUPPORT_2.setTextureSize(128, 128);
        this.T_SUPPORT_2.mirror = true;
        this.setRotation(this.T_SUPPORT_2, 0.0f, 0.0f, 0.0f);
        (this.WARHEAD_SUPPORT_A1 = new ModelRenderer((ModelBase) this, 90, 38))
            .addBox(0.0f, 0.0f, 0.0f, 2, 6, 2);
        this.WARHEAD_SUPPORT_A1.setRotationPoint(-5.0f, -47.0f, -1.0f);
        this.WARHEAD_SUPPORT_A1.setTextureSize(128, 128);
        this.WARHEAD_SUPPORT_A1.mirror = true;
        this.setRotation(this.WARHEAD_SUPPORT_A1, 0.0f, 0.0f, 0.0f);
        (this.WARHEAD_SUPPORT_B1 = new ModelRenderer((ModelBase) this, 90, 38))
            .addBox(0.0f, 0.0f, 0.0f, 2, 6, 2);
        this.WARHEAD_SUPPORT_B1.setRotationPoint(-1.0f, -47.0f, 3.0f);
        this.WARHEAD_SUPPORT_B1.setTextureSize(128, 128);
        this.WARHEAD_SUPPORT_B1.mirror = true;
        this.setRotation(this.WARHEAD_SUPPORT_B1, 0.0f, 0.0f, 0.0f);
        (this.WARHEAD_SUPPORT_C1 = new ModelRenderer((ModelBase) this, 90, 38))
            .addBox(0.0f, 0.0f, 0.0f, 2, 6, 2);
        this.WARHEAD_SUPPORT_C1.setRotationPoint(3.0f, -47.0f, -1.0f);
        this.WARHEAD_SUPPORT_C1.setTextureSize(128, 128);
        this.WARHEAD_SUPPORT_C1.mirror = true;
        this.setRotation(this.WARHEAD_SUPPORT_C1, 0.0f, 0.0f, 0.0f);
        (this.WRHEAD_SUPPORT_D1 = new ModelRenderer((ModelBase) this, 90, 38))
            .addBox(0.0f, 0.0f, 0.0f, 2, 6, 2);
        this.WRHEAD_SUPPORT_D1.setRotationPoint(-1.0f, -47.0f, -5.0f);
        this.WRHEAD_SUPPORT_D1.setTextureSize(128, 128);
        this.WRHEAD_SUPPORT_D1.mirror = true;
        this.setRotation(this.WRHEAD_SUPPORT_D1, 0.0f, 0.0f, 0.0f);
        (this.WARHEAD_SUPPORT_A2 = new ModelRenderer((ModelBase) this, 90, 38))
            .addBox(0.0f, -6.0f, 0.0f, 2, 6, 2);
        this.WARHEAD_SUPPORT_A2.setRotationPoint(-5.0f, -47.0f, -1.0f);
        this.WARHEAD_SUPPORT_A2.setTextureSize(128, 128);
        this.WARHEAD_SUPPORT_A2.mirror = true;
        this.setRotation(this.WARHEAD_SUPPORT_A2, 0.0f, 0.0f, 0.5235988f);
        (this.WARHEAD_SUPPORT_C2 = new ModelRenderer((ModelBase) this, 90, 38))
            .addBox(-2.0f, -6.0f, 0.0f, 2, 6, 2);
        this.WARHEAD_SUPPORT_C2.setRotationPoint(5.0f, -47.0f, -1.0f);
        this.WARHEAD_SUPPORT_C2.setTextureSize(128, 128);
        this.WARHEAD_SUPPORT_C2.mirror = true;
        this.setRotation(this.WARHEAD_SUPPORT_C2, 0.0f, 0.0f, -0.5235988f);
        (this.WARHEAD_SUPPORT_D2 = new ModelRenderer((ModelBase) this, 90, 38))
            .addBox(0.0f, -6.0f, 0.0f, 2, 6, 2);
        this.WARHEAD_SUPPORT_D2.setRotationPoint(-1.0f, -47.0f, -5.0f);
        this.WARHEAD_SUPPORT_D2.setTextureSize(128, 128);
        this.WARHEAD_SUPPORT_D2.mirror = true;
        this.setRotation(this.WARHEAD_SUPPORT_D2, -0.5235988f, 0.0f, 0.0f);
        (this.WARHEAD_SUPPORT_B2 = new ModelRenderer((ModelBase) this, 90, 38))
            .addBox(0.0f, -6.0f, -2.0f, 2, 6, 2);
        this.WARHEAD_SUPPORT_B2.setRotationPoint(-1.0f, -47.0f, 5.0f);
        this.WARHEAD_SUPPORT_B2.setTextureSize(128, 128);
        this.WARHEAD_SUPPORT_B2.mirror = true;
        this.setRotation(this.WARHEAD_SUPPORT_B2, 0.5235988f, 0.0f, 0.0f);
        (this.Shape8 = new ModelRenderer((ModelBase) this, 72, 32))
            .addBox(-2.0f, 0.0f, -2.0f, 4, 4, 4);
        this.Shape8.setRotationPoint(0.0f, -45.0f, 0.0f);
        this.Shape8.setTextureSize(128, 128);
        this.Shape8.mirror = true;
        this.setRotation(this.Shape8, 0.0f, 0.0f, 0.0f);
        (this.Shape9 = new ModelRenderer((ModelBase) this, 90, 32))
            .addBox(-1.0f, 0.0f, -1.0f, 2, 2, 2);
        this.Shape9.setRotationPoint(0.0f, -48.0f, 0.0f);
        this.Shape9.setTextureSize(128, 128);
        this.Shape9.mirror = true;
        this.setRotation(this.Shape9, 0.0f, 0.0f, 0.0f);
    }

    public void render(
        final Entity entity,
        final float f,
        final float f1,
        final float f2,
        final float f3,
        final float f4,
        final float f5
    ) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        this.render(f5);
    }

    @Override
    public void render(final float f5) {
        this.MAIN_MODULE.render(f5);
        this.WING_B1.render(f5);
        this.WING_B2.render(f5);
        this.WING_B3.render(f5);
        this.WING_B4.render(f5);
        this.WING_BA1.render(f5);
        this.WING_BB1.render(f5);
        this.WING_BA2.render(f5);
        this.WING_BB2.render(f5);
        this.WING_TA1.render(f5);
        this.WING_TB1.render(f5);
        this.WING_TC3.render(f5);
        this.WING_TD1.render(f5);
        this.WING_TAC2.render(f5);
        this.WING_TBD2.render(f5);
        this.WING_TC1.render(f5);
        this.WING_TA3.render(f5);
        this.WING_TD3.render(f5);
        this.WING_TB3.render(f5);
        this.T_SUPPORT_1.render(f5);
        this.T_SUPPORT_2.render(f5);
        this.WARHEAD_SUPPORT_A1.render(f5);
        this.WARHEAD_SUPPORT_B1.render(f5);
        this.WARHEAD_SUPPORT_C1.render(f5);
        this.WRHEAD_SUPPORT_D1.render(f5);
        this.WARHEAD_SUPPORT_A2.render(f5);
        this.WARHEAD_SUPPORT_C2.render(f5);
        this.WARHEAD_SUPPORT_D2.render(f5);
        this.WARHEAD_SUPPORT_B2.render(f5);
        this.Shape8.render(f5);
        this.Shape9.render(f5);
    }

    private void
    setRotation(final ModelRenderer model, final float x, final float y, final float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
}
