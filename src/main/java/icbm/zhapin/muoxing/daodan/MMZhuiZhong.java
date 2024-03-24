package icbm.zhapin.muoxing.daodan;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import icbm.core.di.MICBM;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

@SideOnly(Side.CLIENT)
public class MMZhuiZhong extends MICBM {
    ModelRenderer MAIN_MODULE;
    ModelRenderer MOTOR_MODULE;
    ModelRenderer WING_1A;
    ModelRenderer WING_2A;
    ModelRenderer WING_3B;
    ModelRenderer WING_4B;
    ModelRenderer WING_3A;
    ModelRenderer WING_4A;
    ModelRenderer WING_2B;
    ModelRenderer WING_1B;
    ModelRenderer WARHEAD_1;
    ModelRenderer WARHEAD_2;
    ModelRenderer WARHEAD_3;
    ModelRenderer WARHEAD_4;

    public MMZhuiZhong() {
        super.textureWidth = 128;
        super.textureHeight = 128;
        (this.MAIN_MODULE = new ModelRenderer((ModelBase) this, 0, 0))
            .addBox(0.0f, 0.0f, 0.0f, 6, 50, 6);
        this.MAIN_MODULE.setRotationPoint(-3.0f, -26.0f, -3.0f);
        this.MAIN_MODULE.setTextureSize(128, 128);
        this.MAIN_MODULE.mirror = true;
        this.setRotation(this.MAIN_MODULE, 0.0f, 0.0f, 0.0f);
        (this.MOTOR_MODULE = new ModelRenderer((ModelBase) this, 0, 57))
            .addBox(-5.0f, 0.0f, -5.0f, 10, 16, 10);
        this.MOTOR_MODULE.setRotationPoint(0.0f, 8.0f, 0.0f);
        this.MOTOR_MODULE.setTextureSize(128, 128);
        this.MOTOR_MODULE.mirror = true;
        this.setRotation(this.MOTOR_MODULE, 0.0f, 0.7853982f, 0.0f);
        (this.WING_1A = new ModelRenderer((ModelBase) this, 59, 26))
            .addBox(-1.0f, 0.0f, -9.0f, 2, 12, 18);
        this.WING_1A.setRotationPoint(0.0f, 12.0f, 0.0f);
        this.WING_1A.setTextureSize(128, 128);
        this.WING_1A.mirror = true;
        this.setRotation(this.WING_1A, 0.0f, 0.7853982f, 0.0f);
        (this.WING_2A = new ModelRenderer((ModelBase) this, 59, 26))
            .addBox(-1.0f, 0.0f, -9.0f, 2, 12, 18);
        this.WING_2A.setRotationPoint(0.0f, 12.0f, 0.0f);
        this.WING_2A.setTextureSize(128, 128);
        this.WING_2A.mirror = true;
        this.setRotation(this.WING_2A, 0.0f, -0.7853982f, 0.0f);
        (this.WING_3B = new ModelRenderer((ModelBase) this, 59, 0))
            .addBox(-1.0f, 0.0f, 0.0f, 2, 10, 10);
        this.WING_3B.setRotationPoint(0.0f, -24.0f, 0.0f);
        this.WING_3B.setTextureSize(128, 128);
        this.WING_3B.mirror = true;
        this.setRotation(this.WING_3B, -0.7853982f, 0.7853982f, 0.0f);
        (this.WING_4B = new ModelRenderer((ModelBase) this, 59, 0))
            .addBox(-1.0f, 0.0f, 0.0f, 2, 10, 10);
        this.WING_4B.setRotationPoint(0.0f, -24.0f, 0.0f);
        this.WING_4B.setTextureSize(128, 128);
        this.WING_4B.mirror = true;
        this.setRotation(this.WING_4B, -0.7853982f, -0.7853982f, 0.0f);
        (this.WING_3A = new ModelRenderer((ModelBase) this, 25, 0))
            .addBox(-1.0f, 0.0f, -7.0f, 2, 10, 14);
        this.WING_3A.setRotationPoint(0.0f, -17.0f, 0.0f);
        this.WING_3A.setTextureSize(128, 128);
        this.WING_3A.mirror = true;
        this.setRotation(this.WING_3A, 0.0f, 0.7853982f, 0.0f);
        (this.WING_4A = new ModelRenderer((ModelBase) this, 25, 0))
            .addBox(-1.0f, 0.0f, -7.0f, 2, 10, 14);
        this.WING_4A.setRotationPoint(0.0f, -17.0f, 0.0f);
        this.WING_4A.setTextureSize(128, 128);
        this.WING_4A.mirror = true;
        this.setRotation(this.WING_4A, 0.0f, -0.7853982f, 0.0f);
        (this.WING_2B = new ModelRenderer((ModelBase) this, 25, 26))
            .addBox(-1.0f, 0.0f, 0.0f, 2, 13, 13);
        this.WING_2B.setRotationPoint(0.0f, 3.0f, 0.0f);
        this.WING_2B.setTextureSize(128, 128);
        this.WING_2B.mirror = true;
        this.setRotation(this.WING_2B, -0.7853982f, -0.7853982f, 0.0f);
        (this.WING_1B = new ModelRenderer((ModelBase) this, 25, 26))
            .addBox(-1.0f, 0.0f, 0.0f, 2, 13, 13);
        this.WING_1B.setRotationPoint(0.0f, 3.0f, 0.0f);
        this.WING_1B.setTextureSize(128, 128);
        this.WING_1B.mirror = true;
        this.setRotation(this.WING_1B, -0.7853982f, 0.7853982f, 0.0f);
        (this.WARHEAD_1 = new ModelRenderer((ModelBase) this, 0, 85))
            .addBox(0.0f, 0.0f, 0.0f, 4, 6, 4);
        this.WARHEAD_1.setRotationPoint(-2.0f, -32.0f, -2.0f);
        this.WARHEAD_1.setTextureSize(128, 128);
        this.WARHEAD_1.mirror = true;
        this.setRotation(this.WARHEAD_1, 0.0f, 0.0f, 0.0f);
        (this.WARHEAD_2 = new ModelRenderer((ModelBase) this, 0, 97))
            .addBox(0.0f, 0.0f, 0.0f, 6, 6, 6);
        this.WARHEAD_2.setRotationPoint(-3.0f, -38.0f, -3.0f);
        this.WARHEAD_2.setTextureSize(128, 128);
        this.WARHEAD_2.mirror = true;
        this.setRotation(this.WARHEAD_2, 0.0f, 0.0f, 0.0f);
        (this.WARHEAD_3 = new ModelRenderer((ModelBase) this, 26, 97))
            .addBox(0.0f, 0.0f, 0.0f, 4, 3, 4);
        this.WARHEAD_3.setRotationPoint(-2.0f, -41.0f, -2.0f);
        this.WARHEAD_3.setTextureSize(128, 128);
        this.WARHEAD_3.mirror = true;
        this.setRotation(this.WARHEAD_3, 0.0f, 0.0f, 0.0f);
        (this.WARHEAD_4 = new ModelRenderer((ModelBase) this, 26, 105))
            .addBox(0.0f, 0.0f, 0.0f, 2, 3, 2);
        this.WARHEAD_4.setRotationPoint(-1.0f, -44.0f, -1.0f);
        this.WARHEAD_4.setTextureSize(128, 128);
        this.WARHEAD_4.mirror = true;
        this.setRotation(this.WARHEAD_4, 0.0f, 0.0f, 0.0f);
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
        this.MOTOR_MODULE.render(f5);
        this.WING_1A.render(f5);
        this.WING_2A.render(f5);
        this.WING_3B.render(f5);
        this.WING_4B.render(f5);
        this.WING_3A.render(f5);
        this.WING_4A.render(f5);
        this.WING_2B.render(f5);
        this.WING_1B.render(f5);
        this.WARHEAD_1.render(f5);
        this.WARHEAD_2.render(f5);
        this.WARHEAD_3.render(f5);
        this.WARHEAD_4.render(f5);
    }

    private void
    setRotation(final ModelRenderer model, final float x, final float y, final float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
}
