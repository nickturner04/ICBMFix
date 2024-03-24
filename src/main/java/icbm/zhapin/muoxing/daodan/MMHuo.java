package icbm.zhapin.muoxing.daodan;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import icbm.core.di.MICBM;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

@SideOnly(Side.CLIENT)
public class MMHuo extends MICBM {
    ModelRenderer MAIN_MISSILE_MODULE;
    ModelRenderer PROPULSOR_MODULE;
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
    ModelRenderer WARHEAD_3;
    ModelRenderer WARHEAD_4;
    ModelRenderer WARHEAD_5;
    ModelRenderer WARHEAD_6;

    public MMHuo() {
        super.textureWidth = 128;
        super.textureHeight = 128;
        (this.MAIN_MISSILE_MODULE = new ModelRenderer((ModelBase) this, 0, 0))
            .addBox(0.0f, 0.0f, 0.0f, 6, 50, 6);
        this.MAIN_MISSILE_MODULE.setRotationPoint(-3.0f, -26.0f, -3.0f);
        this.MAIN_MISSILE_MODULE.setTextureSize(128, 128);
        this.MAIN_MISSILE_MODULE.mirror = true;
        this.setRotation(this.MAIN_MISSILE_MODULE, 0.0f, 0.0f, 0.0f);
        (this.PROPULSOR_MODULE = new ModelRenderer((ModelBase) this, 0, 57))
            .addBox(-5.0f, 0.0f, -5.0f, 10, 16, 10);
        this.PROPULSOR_MODULE.setRotationPoint(0.0f, 8.0f, 0.0f);
        this.PROPULSOR_MODULE.setTextureSize(128, 128);
        this.PROPULSOR_MODULE.mirror = true;
        this.setRotation(this.PROPULSOR_MODULE, 0.0f, 0.7853982f, 0.0f);
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
        (this.WARHEAD_1 = new ModelRenderer((ModelBase) this, 0, 85))
            .addBox(0.0f, 0.0f, 0.0f, 6, 7, 6);
        this.WARHEAD_1.setRotationPoint(-3.0f, -33.0f, -3.0f);
        this.WARHEAD_1.setTextureSize(128, 128);
        this.WARHEAD_1.mirror = true;
        this.setRotation(this.WARHEAD_1, 0.0f, 0.0f, 0.0f);
        (this.WARHEAD_2 = new ModelRenderer((ModelBase) this, 26, 101))
            .addBox(0.0f, 0.0f, 0.0f, 2, 6, 2);
        this.WARHEAD_2.setRotationPoint(-1.0f, -39.0f, -1.0f);
        this.WARHEAD_2.setTextureSize(128, 128);
        this.WARHEAD_2.mirror = true;
        this.setRotation(this.WARHEAD_2, 0.0f, 0.0f, 0.0f);
        (this.WARHEAD_3 = new ModelRenderer((ModelBase) this, 26, 85))
            .addBox(-1.0f, 0.0f, 0.0f, 2, 7, 7);
        this.WARHEAD_3.setRotationPoint(0.0f, -39.0f, 0.0f);
        this.WARHEAD_3.setTextureSize(128, 128);
        this.WARHEAD_3.mirror = true;
        this.setRotation(this.WARHEAD_3, -0.7853982f, 0.7853982f, 0.0f);
        (this.WARHEAD_4 = new ModelRenderer((ModelBase) this, 26, 85))
            .addBox(-1.0f, 0.0f, 0.0f, 2, 7, 7);
        this.WARHEAD_4.setRotationPoint(0.0f, -39.0f, 0.0f);
        this.WARHEAD_4.setTextureSize(128, 128);
        this.WARHEAD_4.mirror = true;
        this.setRotation(this.WARHEAD_4, -0.7853982f, -0.7853982f, 0.0f);
        (this.WARHEAD_5 = new ModelRenderer((ModelBase) this, 0, 101))
            .addBox(-1.0f, 0.0f, -5.0f, 2, 7, 10);
        this.WARHEAD_5.setRotationPoint(0.0f, -34.0f, 0.0f);
        this.WARHEAD_5.setTextureSize(128, 128);
        this.WARHEAD_5.mirror = true;
        this.setRotation(this.WARHEAD_5, 0.0f, 0.7853982f, 0.0f);
        (this.WARHEAD_6 = new ModelRenderer((ModelBase) this, 0, 101))
            .addBox(-1.0f, 0.0f, -5.0f, 2, 7, 10);
        this.WARHEAD_6.setRotationPoint(0.0f, -34.0f, 0.0f);
        this.WARHEAD_6.setTextureSize(128, 128);
        this.WARHEAD_6.mirror = true;
        this.setRotation(this.WARHEAD_6, 0.0f, -0.7853982f, 0.0f);
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
        this.PROPULSOR_MODULE.render(f5);
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
        this.WARHEAD_3.render(f5);
        this.WARHEAD_4.render(f5);
        this.WARHEAD_5.render(f5);
        this.WARHEAD_6.render(f5);
    }

    private void
    setRotation(final ModelRenderer model, final float x, final float y, final float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
}
