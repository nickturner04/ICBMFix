package icbm.zhapin.muoxing.jiqi;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

@SideOnly(Side.CLIENT)
public class MXiaoFaSheQi extends ModelBase {
    ModelRenderer SUPPORT_1;
    ModelRenderer SUPPORT_2;
    ModelRenderer SUPPORT_3;
    ModelRenderer SUPPORT_4;
    ModelRenderer SUPPORT_5;
    ModelRenderer SUPPORT_6;
    ModelRenderer MAIN_BODY;
    ModelRenderer MAIN_SUPPORT;
    ModelRenderer TORQUE_ROT;

    public MXiaoFaSheQi() {
        super.textureWidth = 128;
        super.textureHeight = 128;
        (this.SUPPORT_1 = new ModelRenderer((ModelBase) this, 0, 0))
            .addBox(-1.0f, 0.0f, -21.0f, 2, 1, 42);
        this.SUPPORT_1.setRotationPoint(0.0f, 21.0f, 0.0f);
        this.SUPPORT_1.setTextureSize(128, 128);
        this.SUPPORT_1.mirror = true;
        this.setRotation(this.SUPPORT_1, 0.0f, 0.7853982f, 0.0f);
        (this.SUPPORT_2 = new ModelRenderer((ModelBase) this, 0, 0))
            .addBox(-1.0f, 0.0f, -21.0f, 2, 1, 42);
        this.SUPPORT_2.setRotationPoint(0.0f, 21.0f, 0.0f);
        this.SUPPORT_2.setTextureSize(128, 128);
        this.SUPPORT_2.mirror = true;
        this.setRotation(this.SUPPORT_2, 0.0f, 2.356194f, 0.0f);
        (this.SUPPORT_3 = new ModelRenderer((ModelBase) this, 0, 28))
            .addBox(0.0f, 0.0f, 0.0f, 4, 3, 4);
        this.SUPPORT_3.setRotationPoint(-16.0f, 21.0f, -16.0f);
        this.SUPPORT_3.setTextureSize(128, 128);
        this.SUPPORT_3.mirror = true;
        this.setRotation(this.SUPPORT_3, 0.0f, 0.0f, 0.0f);
        (this.SUPPORT_4 = new ModelRenderer((ModelBase) this, 0, 28))
            .addBox(0.0f, 0.0f, 0.0f, 4, 3, 4);
        this.SUPPORT_4.setRotationPoint(12.0f, 21.0f, -16.0f);
        this.SUPPORT_4.setTextureSize(128, 128);
        this.SUPPORT_4.mirror = true;
        this.setRotation(this.SUPPORT_4, 0.0f, 0.0f, 0.0f);
        (this.SUPPORT_5 = new ModelRenderer((ModelBase) this, 0, 28))
            .addBox(0.0f, 0.0f, 0.0f, 4, 3, 4);
        this.SUPPORT_5.setRotationPoint(12.0f, 21.0f, 12.0f);
        this.SUPPORT_5.setTextureSize(128, 128);
        this.SUPPORT_5.mirror = true;
        this.setRotation(this.SUPPORT_5, 0.0f, 0.0f, 0.0f);
        (this.SUPPORT_6 = new ModelRenderer((ModelBase) this, 0, 28))
            .addBox(0.0f, 0.0f, 0.0f, 4, 3, 4);
        this.SUPPORT_6.setRotationPoint(-16.0f, 21.0f, 12.0f);
        this.SUPPORT_6.setTextureSize(128, 128);
        this.SUPPORT_6.mirror = true;
        this.setRotation(this.SUPPORT_6, 0.0f, 0.0f, 0.0f);
        (this.MAIN_BODY = new ModelRenderer((ModelBase) this, 0, 46))
            .addBox(0.0f, 0.0f, 0.0f, 6, 4, 6);
        this.MAIN_BODY.setRotationPoint(-3.0f, 20.0f, -3.0f);
        this.MAIN_BODY.setTextureSize(128, 128);
        this.MAIN_BODY.mirror = true;
        this.setRotation(this.MAIN_BODY, 0.0f, 0.0f, 0.0f);
        (this.MAIN_SUPPORT = new ModelRenderer((ModelBase) this, 0, 59))
            .addBox(0.0f, 0.0f, 0.0f, 4, 2, 4);
        this.MAIN_SUPPORT.setRotationPoint(-2.0f, 18.0f, -2.0f);
        this.MAIN_SUPPORT.setTextureSize(128, 128);
        this.MAIN_SUPPORT.mirror = true;
        this.setRotation(this.MAIN_SUPPORT, 0.0f, 0.0f, 0.0f);
        (this.TORQUE_ROT = new ModelRenderer((ModelBase) this, 0, 68))
            .addBox(-1.0f, -4.0f, -1.0f, 2, 4, 2);
        this.TORQUE_ROT.setRotationPoint(0.0f, 18.0f, 0.0f);
        this.TORQUE_ROT.setTextureSize(128, 128);
        this.TORQUE_ROT.mirror = true;
        this.setRotation(this.TORQUE_ROT, 0.0f, 0.7853982f, 0.0f);
    }

    public void render(final float f5) {
        this.SUPPORT_1.render(f5);
        this.SUPPORT_2.render(f5);
        this.SUPPORT_3.render(f5);
        this.SUPPORT_4.render(f5);
        this.SUPPORT_5.render(f5);
        this.SUPPORT_6.render(f5);
        this.MAIN_BODY.render(f5);
        this.MAIN_SUPPORT.render(f5);
        this.TORQUE_ROT.render(f5);
    }

    private void
    setRotation(final ModelRenderer model, final float x, final float y, final float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(
        final float f,
        final float f1,
        final float f2,
        final float f3,
        final float f4,
        final float f5,
        final Entity entity
    ) {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    }
}
