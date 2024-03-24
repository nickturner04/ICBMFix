package icbm.zhapin.muoxing.daodan;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import icbm.core.di.MICBM;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

@SideOnly(Side.CLIENT)
public class MMPiaoFu extends MICBM {
    ModelRenderer Shape1;
    ModelRenderer Shape2;
    ModelRenderer Shape3;
    ModelRenderer Shape4;
    ModelRenderer Shape5;
    ModelRenderer Shape6;
    ModelRenderer Shape7;
    ModelRenderer Shape8;
    ModelRenderer Shape9;

    public MMPiaoFu() {
        super.textureWidth = 128;
        super.textureHeight = 128;
        (this.Shape1 = new ModelRenderer((ModelBase) this, 0, 0))
            .addBox(0.0f, 0.0f, 0.0f, 6, 70, 6);
        this.Shape1.setRotationPoint(-3.0f, -46.0f, -3.0f);
        this.Shape1.setTextureSize(128, 128);
        this.Shape1.mirror = true;
        this.setRotation(this.Shape1, 0.0f, 0.0f, 0.0f);
        (this.Shape2 = new ModelRenderer((ModelBase) this, 25, 0))
            .addBox(0.0f, 0.0f, 0.0f, 2, 5, 18);
        this.Shape2.setRotationPoint(-1.0f, 19.0f, -9.0f);
        this.Shape2.setTextureSize(128, 128);
        this.Shape2.mirror = true;
        this.setRotation(this.Shape2, 0.0f, 0.0f, 0.0f);
        (this.Shape3 = new ModelRenderer((ModelBase) this, 25, 25))
            .addBox(0.0f, 0.0f, 0.0f, 18, 5, 2);
        this.Shape3.setRotationPoint(-9.0f, 19.0f, -1.0f);
        this.Shape3.setTextureSize(128, 128);
        this.Shape3.mirror = true;
        this.setRotation(this.Shape3, 0.0f, 0.0f, 0.0f);
        (this.Shape4 = new ModelRenderer((ModelBase) this, 25, 65))
            .addBox(0.0f, 0.0f, 0.0f, 12, 12, 2);
        this.Shape4.setRotationPoint(-6.0f, -40.0f, -1.0f);
        this.Shape4.setTextureSize(128, 128);
        this.Shape4.mirror = true;
        this.setRotation(this.Shape4, 0.0f, 0.0f, 0.0f);
        (this.Shape5 = new ModelRenderer((ModelBase) this, 25, 40))
            .addBox(0.0f, 0.0f, 0.0f, 2, 12, 12);
        this.Shape5.setRotationPoint(-1.0f, -40.0f, -6.0f);
        this.Shape5.setTextureSize(128, 128);
        this.Shape5.mirror = true;
        this.setRotation(this.Shape5, 0.0f, 0.0f, 0.0f);
        (this.Shape6 = new ModelRenderer((ModelBase) this, 0, 80))
            .addBox(0.0f, 0.0f, 0.0f, 10, 15, 10);
        this.Shape6.setRotationPoint(-5.0f, 9.0f, -5.0f);
        this.Shape6.setTextureSize(128, 128);
        this.Shape6.mirror = true;
        this.setRotation(this.Shape6, 0.0f, 0.0f, 0.0f);
        (this.Shape7 = new ModelRenderer((ModelBase) this, 0, 106))
            .addBox(0.0f, 0.0f, 0.0f, 8, 7, 8);
        this.Shape7.setRotationPoint(-4.0f, -53.0f, -4.0f);
        this.Shape7.setTextureSize(128, 128);
        this.Shape7.mirror = true;
        this.setRotation(this.Shape7, 0.0f, 0.0f, 0.0f);
        (this.Shape8 = new ModelRenderer((ModelBase) this, 70, 0))
            .addBox(0.0f, 0.0f, 0.0f, 4, 4, 4);
        this.Shape8.setRotationPoint(0.0f, -54.0f, 0.0f);
        this.Shape8.setTextureSize(128, 128);
        this.Shape8.mirror = true;
        this.setRotation(this.Shape8, -0.7853982f, 0.0f, 0.6108652f);
        (this.Shape9 = new ModelRenderer((ModelBase) this, 70, 10))
            .addBox(-1.0f, 0.0f, -1.0f, 2, 5, 2);
        this.Shape9.setRotationPoint(0.0f, -55.0f, 0.0f);
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
        this.Shape1.render(f5);
        this.Shape2.render(f5);
        this.Shape3.render(f5);
        this.Shape4.render(f5);
        this.Shape5.render(f5);
        this.Shape6.render(f5);
        this.Shape7.render(f5);
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
