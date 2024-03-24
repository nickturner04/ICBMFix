package icbm.zhapin.muoxing.daodan;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import icbm.core.di.MICBM;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

@SideOnly(Side.CLIENT)
public class MMDuQi extends MICBM {
    ModelRenderer MAIN_MISSILE_MODULE;
    ModelRenderer MOTOR_MODULE;
    ModelRenderer WING_B_A_1;
    ModelRenderer WING_B_B_1;
    ModelRenderer WING_T_B_1;
    ModelRenderer WING_T_A_1;
    ModelRenderer WING_T_B_2;
    ModelRenderer WING_T_A_2;
    ModelRenderer WING_B_B_2;
    ModelRenderer WING_B_A_2;
    ModelRenderer CHEMICAL_CONTROL_MODULE;
    ModelRenderer CHEMICAL_CONDUCT;
    ModelRenderer CHEM_WARHEAD_1;
    ModelRenderer CHEM_WARHEAD_2;

    public MMDuQi() {
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
        (this.WING_T_B_1 = new ModelRenderer((ModelBase) this, 59, 0))
            .addBox(-1.0f, 0.0f, 0.0f, 2, 10, 10);
        this.WING_T_B_1.setRotationPoint(0.0f, -24.0f, 0.0f);
        this.WING_T_B_1.setTextureSize(128, 128);
        this.WING_T_B_1.mirror = true;
        this.setRotation(this.WING_T_B_1, -0.7853982f, 0.7853982f, 0.0f);
        (this.WING_T_A_1 = new ModelRenderer((ModelBase) this, 59, 0))
            .addBox(-1.0f, 0.0f, 0.0f, 2, 10, 10);
        this.WING_T_A_1.setRotationPoint(0.0f, -24.0f, 0.0f);
        this.WING_T_A_1.setTextureSize(128, 128);
        this.WING_T_A_1.mirror = true;
        this.setRotation(this.WING_T_A_1, -0.7853982f, -0.7853982f, 0.0f);
        (this.WING_T_B_2 = new ModelRenderer((ModelBase) this, 25, 0))
            .addBox(-1.0f, 0.0f, -7.0f, 2, 10, 14);
        this.WING_T_B_2.setRotationPoint(0.0f, -17.0f, 0.0f);
        this.WING_T_B_2.setTextureSize(128, 128);
        this.WING_T_B_2.mirror = true;
        this.setRotation(this.WING_T_B_2, 0.0f, 0.7853982f, 0.0f);
        (this.WING_T_A_2 = new ModelRenderer((ModelBase) this, 25, 0))
            .addBox(-1.0f, 0.0f, -7.0f, 2, 10, 14);
        this.WING_T_A_2.setRotationPoint(0.0f, -17.0f, 0.0f);
        this.WING_T_A_2.setTextureSize(128, 128);
        this.WING_T_A_2.mirror = true;
        this.setRotation(this.WING_T_A_2, 0.0f, -0.7853982f, 0.0f);
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
        (this.CHEMICAL_CONTROL_MODULE = new ModelRenderer((ModelBase) this, 0, 86))
            .addBox(-4.0f, 0.0f, -4.0f, 8, 8, 8);
        this.CHEMICAL_CONTROL_MODULE.setRotationPoint(0.0f, -4.0f, 0.0f);
        this.CHEMICAL_CONTROL_MODULE.setTextureSize(128, 128);
        this.CHEMICAL_CONTROL_MODULE.mirror = true;
        this.setRotation(this.CHEMICAL_CONTROL_MODULE, 0.0f, 0.0f, 0.0f);
        (this.CHEMICAL_CONDUCT = new ModelRenderer((ModelBase) this, 34, 86))
            .addBox(0.0f, 0.0f, 0.0f, 1, 24, 1);
        this.CHEMICAL_CONDUCT.setRotationPoint(0.0f, -28.0f, -4.0f);
        this.CHEMICAL_CONDUCT.setTextureSize(128, 128);
        this.CHEMICAL_CONDUCT.mirror = true;
        this.setRotation(this.CHEMICAL_CONDUCT, 0.0f, 0.0f, 0.0f);
        (this.CHEM_WARHEAD_1 = new ModelRenderer((ModelBase) this, 0, 103))
            .addBox(-4.0f, 0.0f, -4.0f, 8, 5, 8);
        this.CHEM_WARHEAD_1.setRotationPoint(0.0f, -31.0f, 0.0f);
        this.CHEM_WARHEAD_1.setTextureSize(128, 128);
        this.CHEM_WARHEAD_1.mirror = true;
        this.setRotation(this.CHEM_WARHEAD_1, 0.0f, 0.7853982f, 0.0f);
        (this.CHEM_WARHEAD_2 = new ModelRenderer((ModelBase) this, 40, 86))
            .addBox(-3.0f, 0.0f, -3.0f, 6, 5, 6);
        this.CHEM_WARHEAD_2.setRotationPoint(0.0f, -36.0f, 0.0f);
        this.CHEM_WARHEAD_2.setTextureSize(128, 128);
        this.CHEM_WARHEAD_2.mirror = true;
        this.setRotation(this.CHEM_WARHEAD_2, 0.0f, 0.7853982f, 0.0f);
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
        this.WING_T_B_1.render(f5);
        this.WING_T_A_1.render(f5);
        this.WING_T_B_2.render(f5);
        this.WING_T_A_2.render(f5);
        this.WING_B_B_2.render(f5);
        this.WING_B_A_2.render(f5);
        this.CHEMICAL_CONTROL_MODULE.render(f5);
        this.CHEMICAL_CONDUCT.render(f5);
        this.CHEM_WARHEAD_1.render(f5);
        this.CHEM_WARHEAD_2.render(f5);
    }

    private void
    setRotation(final ModelRenderer model, final float x, final float y, final float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
}
