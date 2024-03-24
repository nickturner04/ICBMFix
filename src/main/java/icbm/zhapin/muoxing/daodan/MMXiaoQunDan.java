package icbm.zhapin.muoxing.daodan;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import icbm.core.di.MICBM;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

@SideOnly(Side.CLIENT)
public class MMXiaoQunDan extends MICBM {
    ModelRenderer MAIN_MISSILE_MODULE;
    ModelRenderer MOTOR_MODULE;
    ModelRenderer WING_B_A_1;
    ModelRenderer WING_B_B_1;
    ModelRenderer WING_T_A_1;
    ModelRenderer WING_T_B_1;
    ModelRenderer WING_T_A_2;
    ModelRenderer WING_T_B_2;
    ModelRenderer WING_B_B_2;
    ModelRenderer WING_B_A_2;
    ModelRenderer WARHEAD_1;
    ModelRenderer WARHEAD_2;
    ModelRenderer SHRAPNEL_PIECE_1;
    ModelRenderer SHRAPNEL_PIECE_2;
    ModelRenderer SHRAPNEL_PIECE_3;
    ModelRenderer SHRAPNEL_PIECE_4;

    public MMXiaoQunDan() {
        super.textureWidth = 128;
        super.textureHeight = 128;
        (this.MAIN_MISSILE_MODULE = new ModelRenderer((ModelBase) this, 0, 0))
            .addBox(0.0f, 0.0f, 0.0f, 6, 50, 6);
        this.MAIN_MISSILE_MODULE.setRotationPoint(-3.0f, -26.0f, -3.0f);
        this.MAIN_MISSILE_MODULE.setTextureSize(128, 128);
        this.MAIN_MISSILE_MODULE.mirror = true;
        this.setRotation(this.MAIN_MISSILE_MODULE, 0.0f, 0.0f, 0.0f);
        (this.MOTOR_MODULE = new ModelRenderer((ModelBase) this, 0, 57))
            .addBox(-5.0f, 0.0f, -5.0f, 10, 16, 10);
        this.MOTOR_MODULE.setRotationPoint(0.0f, 8.0f, 0.0f);
        this.MOTOR_MODULE.setTextureSize(128, 128);
        this.MOTOR_MODULE.mirror = true;
        this.setRotation(this.MOTOR_MODULE, 0.0f, 0.7853982f, 0.0f);
        (this.WING_B_A_1 = new ModelRenderer((ModelBase) this, 59, 26))
            .addBox(-1.0f, 0.0f, -9.0f, 2, 12, 18);
        this.WING_B_A_1.setRotationPoint(0.0f, 12.0f, 0.0f);
        this.WING_B_A_1.setTextureSize(128, 128);
        this.WING_B_A_1.mirror = true;
        this.setRotation(this.WING_B_A_1, 0.0f, 0.7853982f, 0.0f);
        (this.WING_B_B_1 = new ModelRenderer((ModelBase) this, 59, 26))
            .addBox(-1.0f, 0.0f, -9.0f, 2, 12, 18);
        this.WING_B_B_1.setRotationPoint(0.0f, 12.0f, 0.0f);
        this.WING_B_B_1.setTextureSize(128, 128);
        this.WING_B_B_1.mirror = true;
        this.setRotation(this.WING_B_B_1, 0.0f, -0.7853982f, 0.0f);
        (this.WING_T_A_1 = new ModelRenderer((ModelBase) this, 59, 0))
            .addBox(-1.0f, 0.0f, 0.0f, 2, 10, 10);
        this.WING_T_A_1.setRotationPoint(0.0f, -24.0f, 0.0f);
        this.WING_T_A_1.setTextureSize(128, 128);
        this.WING_T_A_1.mirror = true;
        this.setRotation(this.WING_T_A_1, -0.7853982f, 0.7853982f, 0.0f);
        (this.WING_T_B_1 = new ModelRenderer((ModelBase) this, 59, 0))
            .addBox(-1.0f, 0.0f, 0.0f, 2, 10, 10);
        this.WING_T_B_1.setRotationPoint(0.0f, -24.0f, 0.0f);
        this.WING_T_B_1.setTextureSize(128, 128);
        this.WING_T_B_1.mirror = true;
        this.setRotation(this.WING_T_B_1, -0.7853982f, -0.7853982f, 0.0f);
        (this.WING_T_A_2 = new ModelRenderer((ModelBase) this, 25, 0))
            .addBox(-1.0f, 0.0f, -7.0f, 2, 10, 14);
        this.WING_T_A_2.setRotationPoint(0.0f, -17.0f, 0.0f);
        this.WING_T_A_2.setTextureSize(128, 128);
        this.WING_T_A_2.mirror = true;
        this.setRotation(this.WING_T_A_2, 0.0f, 0.7853982f, 0.0f);
        (this.WING_T_B_2 = new ModelRenderer((ModelBase) this, 25, 0))
            .addBox(-1.0f, 0.0f, -7.0f, 2, 10, 14);
        this.WING_T_B_2.setRotationPoint(0.0f, -17.0f, 0.0f);
        this.WING_T_B_2.setTextureSize(128, 128);
        this.WING_T_B_2.mirror = true;
        this.setRotation(this.WING_T_B_2, 0.0f, -0.7853982f, 0.0f);
        (this.WING_B_B_2 = new ModelRenderer((ModelBase) this, 25, 26))
            .addBox(-1.0f, 0.0f, 0.0f, 2, 13, 13);
        this.WING_B_B_2.setRotationPoint(0.0f, 3.0f, 0.0f);
        this.WING_B_B_2.setTextureSize(128, 128);
        this.WING_B_B_2.mirror = true;
        this.setRotation(this.WING_B_B_2, -0.7853982f, -0.7853982f, 0.0f);
        (this.WING_B_A_2 = new ModelRenderer((ModelBase) this, 25, 26))
            .addBox(-1.0f, 0.0f, 0.0f, 2, 13, 13);
        this.WING_B_A_2.setRotationPoint(0.0f, 3.0f, 0.0f);
        this.WING_B_A_2.setTextureSize(128, 128);
        this.WING_B_A_2.mirror = true;
        this.setRotation(this.WING_B_A_2, -0.7853982f, 0.7853982f, 0.0f);
        (this.WARHEAD_1 = new ModelRenderer((ModelBase) this, 0, 87))
            .addBox(0.0f, 0.0f, 0.0f, 8, 4, 8);
        this.WARHEAD_1.setRotationPoint(-4.0f, -30.0f, -4.0f);
        this.WARHEAD_1.setTextureSize(128, 128);
        this.WARHEAD_1.mirror = true;
        this.setRotation(this.WARHEAD_1, 0.0f, 0.0f, 0.0f);
        (this.WARHEAD_2 = new ModelRenderer((ModelBase) this, 0, 101))
            .addBox(0.0f, 0.0f, 0.0f, 6, 4, 6);
        this.WARHEAD_2.setRotationPoint(-3.0f, -34.0f, -3.0f);
        this.WARHEAD_2.setTextureSize(128, 128);
        this.WARHEAD_2.mirror = true;
        this.setRotation(this.WARHEAD_2, 0.0f, 0.0f, 0.0f);
        (this.SHRAPNEL_PIECE_1 = new ModelRenderer((ModelBase) this, 0, 112))
            .addBox(0.0f, 0.0f, 0.0f, 10, 2, 2);
        this.SHRAPNEL_PIECE_1.setRotationPoint(-5.0f, -29.0f, -1.0f);
        this.SHRAPNEL_PIECE_1.setTextureSize(128, 128);
        this.SHRAPNEL_PIECE_1.mirror = true;
        this.setRotation(this.SHRAPNEL_PIECE_1, 0.0f, 0.0f, 0.0f);
        (this.SHRAPNEL_PIECE_2 = new ModelRenderer((ModelBase) this, 27, 112))
            .addBox(0.0f, 0.0f, 0.0f, 2, 2, 10);
        this.SHRAPNEL_PIECE_2.setRotationPoint(-1.0f, -29.0f, -5.0f);
        this.SHRAPNEL_PIECE_2.setTextureSize(128, 128);
        this.SHRAPNEL_PIECE_2.mirror = true;
        this.setRotation(this.SHRAPNEL_PIECE_2, 0.0f, 0.0f, 0.0f);
        (this.SHRAPNEL_PIECE_3 = new ModelRenderer((ModelBase) this, 0, 119))
            .addBox(0.0f, 0.0f, 0.0f, 8, 2, 2);
        this.SHRAPNEL_PIECE_3.setRotationPoint(-4.0f, -33.0f, -1.0f);
        this.SHRAPNEL_PIECE_3.setTextureSize(128, 128);
        this.SHRAPNEL_PIECE_3.mirror = true;
        this.setRotation(this.SHRAPNEL_PIECE_3, 0.0f, 0.0f, 0.0f);
        (this.SHRAPNEL_PIECE_4 = new ModelRenderer((ModelBase) this, 53, 112))
            .addBox(0.0f, 0.0f, 0.0f, 2, 2, 8);
        this.SHRAPNEL_PIECE_4.setRotationPoint(-1.0f, -33.0f, -4.0f);
        this.SHRAPNEL_PIECE_4.setTextureSize(128, 128);
        this.SHRAPNEL_PIECE_4.mirror = true;
        this.setRotation(this.SHRAPNEL_PIECE_4, 0.0f, 0.0f, 0.0f);
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
        this.MAIN_MISSILE_MODULE.render(f5);
        this.MOTOR_MODULE.render(f5);
        this.WING_B_A_1.render(f5);
        this.WING_B_B_1.render(f5);
        this.WING_T_A_1.render(f5);
        this.WING_T_B_1.render(f5);
        this.WING_T_A_2.render(f5);
        this.WING_T_B_2.render(f5);
        this.WING_B_B_2.render(f5);
        this.WING_B_A_2.render(f5);
        this.WARHEAD_1.render(f5);
        this.WARHEAD_2.render(f5);
        this.SHRAPNEL_PIECE_1.render(f5);
        this.SHRAPNEL_PIECE_2.render(f5);
        this.SHRAPNEL_PIECE_3.render(f5);
        this.SHRAPNEL_PIECE_4.render(f5);
    }

    private void
    setRotation(final ModelRenderer model, final float x, final float y, final float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
}
