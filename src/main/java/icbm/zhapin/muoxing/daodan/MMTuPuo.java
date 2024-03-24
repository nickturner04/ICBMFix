package icbm.zhapin.muoxing.daodan;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import icbm.core.di.MICBM;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

@SideOnly(Side.CLIENT)
public class MMTuPuo extends MICBM {
    ModelRenderer Shape1;
    ModelRenderer Shape2;
    ModelRenderer Shape2b;
    ModelRenderer Shape2c;
    ModelRenderer Shape2d;
    ModelRenderer Shape3;
    ModelRenderer Shape3a;
    ModelRenderer Shape3b;
    ModelRenderer Shape3c;
    ModelRenderer Shape4;
    ModelRenderer Shape4a;
    ModelRenderer Shape4b;
    ModelRenderer Shape4c;
    ModelRenderer Shape5;
    ModelRenderer Shape5a;
    ModelRenderer Shape4d;
    ModelRenderer Shape4e;
    ModelRenderer Shape4f;
    ModelRenderer Shape4g;
    ModelRenderer Shape6;
    ModelRenderer Shape6a;
    ModelRenderer Shape7;
    ModelRenderer Shape7a;
    ModelRenderer Shape7b;

    public MMTuPuo() {
        super.textureWidth = 128;
        super.textureHeight = 128;
        (this.Shape1 = new ModelRenderer((ModelBase) this, 0, 0))
            .addBox(-4.0f, 0.0f, -4.0f, 8, 64, 8);
        this.Shape1.setRotationPoint(0.0f, -40.0f, 0.0f);
        this.Shape1.setTextureSize(128, 128);
        this.Shape1.mirror = true;
        this.setRotation(this.Shape1, 0.0f, 0.0f, 0.0f);
        (this.Shape2 = new ModelRenderer((ModelBase) this, 34, 19))
            .addBox(0.0f, 0.0f, 0.0f, 14, 10, 1);
        this.Shape2.setRotationPoint(-7.0f, 14.0f, 6.0f);
        this.Shape2.setTextureSize(128, 128);
        this.Shape2.mirror = true;
        this.setRotation(this.Shape2, 0.0f, 0.0f, 0.0f);
        (this.Shape2b = new ModelRenderer((ModelBase) this, 34, 19))
            .addBox(0.0f, 0.0f, 0.0f, 14, 10, 1);
        this.Shape2b.setRotationPoint(-7.0f, 14.0f, -7.0f);
        this.Shape2b.setTextureSize(128, 128);
        this.Shape2b.mirror = true;
        this.setRotation(this.Shape2b, 0.0f, 0.0f, 0.0f);
        (this.Shape2c = new ModelRenderer((ModelBase) this, 34, 31))
            .addBox(0.0f, 0.0f, 0.0f, 1, 10, 12);
        this.Shape2c.setRotationPoint(-7.0f, 14.0f, -6.0f);
        this.Shape2c.setTextureSize(128, 128);
        this.Shape2c.mirror = true;
        this.setRotation(this.Shape2c, 0.0f, 0.0f, 0.0f);
        (this.Shape2d = new ModelRenderer((ModelBase) this, 34, 31))
            .addBox(0.0f, 0.0f, 0.0f, 1, 10, 12);
        this.Shape2d.setRotationPoint(6.0f, 14.0f, -6.0f);
        this.Shape2d.setTextureSize(128, 128);
        this.Shape2d.mirror = true;
        this.setRotation(this.Shape2d, 0.0f, 0.0f, 0.0f);
        (this.Shape3 = new ModelRenderer((ModelBase) this, 72, 0))
            .addBox(-11.0f, 0.0f, -1.0f, 22, 12, 2);
        this.Shape3.setRotationPoint(0.0f, 12.0f, 0.0f);
        this.Shape3.setTextureSize(128, 128);
        this.Shape3.mirror = true;
        this.setRotation(this.Shape3, 0.0f, 0.7853982f, 0.0f);
        (this.Shape3a = new ModelRenderer((ModelBase) this, 72, 0))
            .addBox(-11.0f, 0.0f, -1.0f, 22, 12, 2);
        this.Shape3a.setRotationPoint(0.0f, 12.0f, 0.0f);
        this.Shape3a.setTextureSize(128, 128);
        this.Shape3a.mirror = true;
        this.setRotation(this.Shape3a, 0.0f, -0.7853982f, 0.0f);
        (this.Shape3b = new ModelRenderer((ModelBase) this, 34, 0))
            .addBox(-8.0f, -8.0f, -1.0f, 16, 16, 2);
        this.Shape3b.setRotationPoint(0.0f, 12.0f, 0.0f);
        this.Shape3b.setTextureSize(128, 128);
        this.Shape3b.mirror = true;
        this.setRotation(this.Shape3b, 0.0f, 0.7853982f, 0.7853982f);
        (this.Shape3c = new ModelRenderer((ModelBase) this, 34, 0))
            .addBox(-8.0f, -8.0f, -1.0f, 16, 16, 2);
        this.Shape3c.setRotationPoint(0.0f, 12.0f, 0.0f);
        this.Shape3c.setTextureSize(128, 128);
        this.Shape3c.mirror = true;
        this.setRotation(this.Shape3c, 0.0f, -0.7853982f, 0.7853982f);
        (this.Shape4 = new ModelRenderer((ModelBase) this, 22, 74))
            .addBox(0.0f, 0.0f, 0.0f, 2, 7, 5);
        this.Shape4.setRotationPoint(-1.0f, -23.0f, 4.0f);
        this.Shape4.setTextureSize(128, 128);
        this.Shape4.mirror = true;
        this.setRotation(this.Shape4, -0.5235988f, 0.0f, 0.0f);
        (this.Shape4a = new ModelRenderer((ModelBase) this, 0, 103))
            .addBox(0.0f, 0.0f, 0.0f, 5, 8, 2);
        this.Shape4a.setRotationPoint(4.0f, -23.0f, -1.0f);
        this.Shape4a.setTextureSize(128, 128);
        this.Shape4a.mirror = true;
        this.setRotation(this.Shape4a, 0.0f, 0.0f, 0.5235988f);
        (this.Shape4b = new ModelRenderer((ModelBase) this, 0, 81))
            .addBox(-1.0f, 0.0f, 0.0f, 2, 4, 5);
        this.Shape4b.setRotationPoint(0.0f, -35.0f, -4.0f);
        this.Shape4b.setTextureSize(128, 128);
        this.Shape4b.mirror = true;
        this.setRotation(this.Shape4b, -0.5235988f, 3.141593f, 0.0f);
        (this.Shape4c = new ModelRenderer((ModelBase) this, 0, 103))
            .addBox(0.0f, 0.0f, -1.0f, 5, 8, 2);
        this.Shape4c.setRotationPoint(-4.0f, -23.0f, 0.0f);
        this.Shape4c.setTextureSize(128, 128);
        this.Shape4c.mirror = true;
        this.setRotation(this.Shape4c, 0.0f, 3.141593f, 0.5235988f);
        (this.Shape5 = new ModelRenderer((ModelBase) this, 0, 74))
            .addBox(-1.0f, 0.0f, -8.0f, 2, 12, 16);
        this.Shape5.setRotationPoint(0.0f, -32.0f, 0.0f);
        this.Shape5.setTextureSize(128, 128);
        this.Shape5.mirror = true;
        this.setRotation(this.Shape5, 0.0f, 0.0f, 0.0f);
        (this.Shape5a = new ModelRenderer((ModelBase) this, 0, 74))
            .addBox(-1.0f, 0.0f, -8.0f, 2, 12, 16);
        this.Shape5a.setRotationPoint(0.0f, -32.0f, 0.0f);
        this.Shape5a.setTextureSize(128, 128);
        this.Shape5a.mirror = true;
        this.setRotation(this.Shape5a, 0.0f, 1.570796f, 0.0f);
        (this.Shape4d = new ModelRenderer((ModelBase) this, 22, 74))
            .addBox(-1.0f, 0.0f, 0.0f, 2, 8, 5);
        this.Shape4d.setRotationPoint(0.0f, -23.0f, -4.0f);
        this.Shape4d.setTextureSize(128, 128);
        this.Shape4d.mirror = true;
        this.setRotation(this.Shape4d, -0.5235988f, 3.141593f, 0.0f);
        (this.Shape4e = new ModelRenderer((ModelBase) this, 0, 81))
            .addBox(-1.0f, 0.0f, 0.0f, 2, 4, 5);
        this.Shape4e.setRotationPoint(0.0f, -35.0f, 4.0f);
        this.Shape4e.setTextureSize(128, 128);
        this.Shape4e.mirror = true;
        this.setRotation(this.Shape4e, -0.5235988f, 0.0f, 0.0f);
        (this.Shape4f = new ModelRenderer((ModelBase) this, 0, 74))
            .addBox(0.0f, 0.0f, -1.0f, 5, 4, 2);
        this.Shape4f.setRotationPoint(-4.0f, -35.0f, 0.0f);
        this.Shape4f.setTextureSize(128, 128);
        this.Shape4f.mirror = true;
        this.setRotation(this.Shape4f, 0.0f, 3.141593f, 0.5235988f);
        (this.Shape4g = new ModelRenderer((ModelBase) this, 0, 74))
            .addBox(0.0f, 0.0f, 0.0f, 5, 4, 2);
        this.Shape4g.setRotationPoint(4.0f, -35.0f, -1.0f);
        this.Shape4g.setTextureSize(128, 128);
        this.Shape4g.mirror = true;
        this.setRotation(this.Shape4g, 0.0f, 0.0f, 0.5235988f);
        (this.Shape6 = new ModelRenderer((ModelBase) this, 72, 16))
            .addBox(0.0f, 0.0f, 0.0f, 10, 1, 10);
        this.Shape6.setRotationPoint(-5.0f, -37.0f, -5.0f);
        this.Shape6.setTextureSize(128, 128);
        this.Shape6.mirror = true;
        this.setRotation(this.Shape6, 0.0f, 0.0f, 0.0f);
        (this.Shape6a = new ModelRenderer((ModelBase) this, 72, 16))
            .addBox(0.0f, 0.0f, 0.0f, 10, 1, 10);
        this.Shape6a.setRotationPoint(-5.0f, -41.0f, -5.0f);
        this.Shape6a.setTextureSize(128, 128);
        this.Shape6a.mirror = true;
        this.setRotation(this.Shape6a, 0.0f, 0.0f, 0.0f);
        (this.Shape7 = new ModelRenderer((ModelBase) this, 96, 115))
            .addBox(0.0f, 0.0f, 0.0f, 8, 4, 8);
        this.Shape7.setRotationPoint(-4.0f, -45.0f, -4.0f);
        this.Shape7.setTextureSize(128, 128);
        this.Shape7.mirror = true;
        this.setRotation(this.Shape7, 0.0f, 0.0f, 0.0f);
        (this.Shape7a = new ModelRenderer((ModelBase) this, 79, 109))
            .addBox(0.0f, 0.0f, 0.0f, 4, 14, 4);
        this.Shape7a.setRotationPoint(-2.0f, -59.0f, -2.0f);
        this.Shape7a.setTextureSize(128, 128);
        this.Shape7a.mirror = true;
        this.setRotation(this.Shape7a, 0.0f, 0.0f, 0.0f);
        (this.Shape7b = new ModelRenderer((ModelBase) this, 96, 110))
            .addBox(0.0f, 0.0f, 0.0f, 2, 2, 2);
        this.Shape7b.setRotationPoint(-1.0f, -61.0f, -1.0f);
        this.Shape7b.setTextureSize(128, 128);
        this.Shape7b.mirror = true;
        this.setRotation(this.Shape7b, 0.0f, 0.0f, 0.0f);
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
        this.Shape2b.render(f5);
        this.Shape2c.render(f5);
        this.Shape2d.render(f5);
        this.Shape3.render(f5);
        this.Shape3a.render(f5);
        this.Shape3b.render(f5);
        this.Shape3c.render(f5);
        this.Shape4.render(f5);
        this.Shape4a.render(f5);
        this.Shape4b.render(f5);
        this.Shape4c.render(f5);
        this.Shape5.render(f5);
        this.Shape5a.render(f5);
        this.Shape4d.render(f5);
        this.Shape4e.render(f5);
        this.Shape4f.render(f5);
        this.Shape4g.render(f5);
        this.Shape6.render(f5);
        this.Shape6a.render(f5);
        this.Shape7.render(f5);
        this.Shape7a.render(f5);
        this.Shape7b.render(f5);
    }

    private void
    setRotation(final ModelRenderer model, final float x, final float y, final float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
}
