package icbm.zhapin.muoxing.daodan;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import icbm.core.di.MICBM;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

@SideOnly(Side.CLIENT)
public class MMHongSu extends MICBM {
    ModelRenderer Shape1;
    ModelRenderer Shape1a;
    ModelRenderer Shape2;
    ModelRenderer Shape2a;
    ModelRenderer Shape3;
    ModelRenderer Shape4;
    ModelRenderer Shape5;
    ModelRenderer Shape6;
    ModelRenderer Shape6a;
    ModelRenderer Shape6b;
    ModelRenderer Shape6c;
    ModelRenderer Shape6d;
    ModelRenderer Shape6e;
    ModelRenderer Shape6f;
    ModelRenderer Shape6g;
    ModelRenderer Shape7;
    ModelRenderer Shape8;
    ModelRenderer Shape9;
    ModelRenderer Shape10;
    ModelRenderer Shape10a;
    ModelRenderer Shape10b;
    ModelRenderer Shape10c;
    ModelRenderer Shape11d;
    ModelRenderer Shape11e;
    ModelRenderer Shape2z;
    ModelRenderer Shape12;
    ModelRenderer Shape13;
    ModelRenderer Shape14;

    public MMHongSu() {
        super.textureWidth = 128;
        super.textureHeight = 128;
        (this.Shape1 = new ModelRenderer((ModelBase) this, 27, 0))
            .addBox(0.0f, 0.0f, 0.0f, 8, 10, 14);
        this.Shape1.setRotationPoint(-8.0f, 14.0f, -2.0f);
        this.Shape1.setTextureSize(128, 128);
        this.Shape1.mirror = true;
        this.setRotation(this.Shape1, 0.0f, 0.7853982f, 0.0f);
        (this.Shape1a = new ModelRenderer((ModelBase) this, 27, 0))
            .addBox(0.0f, 0.0f, 0.0f, 8, 10, 14);
        this.Shape1a.setRotationPoint(2.0f, 14.0f, -8.0f);
        this.Shape1a.setTextureSize(128, 128);
        this.Shape1a.mirror = true;
        this.setRotation(this.Shape1a, 0.0f, -0.7853982f, 0.0f);
        (this.Shape2 = new ModelRenderer((ModelBase) this, 36, 47))
            .addBox(0.0f, 0.0f, 0.0f, 21, 11, 2);
        this.Shape2.setRotationPoint(-8.0f, 13.0f, 7.0f);
        this.Shape2.setTextureSize(128, 128);
        this.Shape2.mirror = true;
        this.setRotation(this.Shape2, 0.0f, 0.7853982f, 0.0f);
        (this.Shape2a = new ModelRenderer((ModelBase) this, 36, 47))
            .addBox(0.0f, 0.0f, 0.0f, 21, 11, 2);
        this.Shape2a.setRotationPoint(-7.0f, 13.0f, -8.0f);
        this.Shape2a.setTextureSize(128, 128);
        this.Shape2a.mirror = true;
        this.setRotation(this.Shape2a, 0.0f, -0.7853982f, 0.0f);
        (this.Shape3 = new ModelRenderer((ModelBase) this, 0, 0))
            .addBox(0.0f, 0.0f, 0.0f, 6, 40, 6);
        this.Shape3.setRotationPoint(-3.0f, -26.0f, -3.0f);
        this.Shape3.setTextureSize(128, 128);
        this.Shape3.mirror = true;
        this.setRotation(this.Shape3, 0.0f, 0.0f, 0.0f);
        (this.Shape4 = new ModelRenderer((ModelBase) this, 27, 25))
            .addBox(0.0f, 0.0f, 0.0f, 10, 10, 10);
        this.Shape4.setRotationPoint(-5.0f, 4.0f, -5.0f);
        this.Shape4.setTextureSize(128, 128);
        this.Shape4.mirror = true;
        this.setRotation(this.Shape4, 0.0f, 0.0f, 0.0f);
        (this.Shape5 = new ModelRenderer((ModelBase) this, 72, 31))
            .addBox(0.0f, 0.0f, 0.0f, 8, 4, 8);
        this.Shape5.setRotationPoint(-4.0f, -30.0f, -4.0f);
        this.Shape5.setTextureSize(128, 128);
        this.Shape5.mirror = true;
        this.setRotation(this.Shape5, 0.0f, 0.0f, 0.0f);
        (this.Shape6 = new ModelRenderer((ModelBase) this, 89, 17))
            .addBox(0.0f, 0.0f, 0.0f, 2, 6, 2);
        this.Shape6.setRotationPoint(-1.0f, -35.0f, 4.0f);
        this.Shape6.setTextureSize(128, 128);
        this.Shape6.mirror = true;
        this.setRotation(this.Shape6, 0.0f, 0.0f, 0.0f);
        (this.Shape6a = new ModelRenderer((ModelBase) this, 89, 17))
            .addBox(0.0f, 0.0f, 0.0f, 2, 6, 2);
        this.Shape6a.setRotationPoint(4.0f, -35.0f, -1.0f);
        this.Shape6a.setTextureSize(128, 128);
        this.Shape6a.mirror = true;
        this.setRotation(this.Shape6a, 0.0f, 0.0f, 0.0f);
        (this.Shape6b = new ModelRenderer((ModelBase) this, 89, 17))
            .addBox(0.0f, 0.0f, 0.0f, 2, 6, 2);
        this.Shape6b.setRotationPoint(-6.0f, -35.0f, -1.0f);
        this.Shape6b.setTextureSize(128, 128);
        this.Shape6b.mirror = true;
        this.setRotation(this.Shape6b, 0.0f, 0.0f, 0.0f);
        (this.Shape6c = new ModelRenderer((ModelBase) this, 89, 17))
            .addBox(0.0f, 0.0f, 0.0f, 2, 6, 2);
        this.Shape6c.setRotationPoint(-1.0f, -35.0f, -6.0f);
        this.Shape6c.setTextureSize(128, 128);
        this.Shape6c.mirror = true;
        this.setRotation(this.Shape6c, 0.0f, 0.0f, 0.0f);
        (this.Shape6d = new ModelRenderer((ModelBase) this, 103, 8))
            .addBox(0.0f, 0.0f, 0.0f, 1, 6, 2);
        this.Shape6d.setRotationPoint(-2.0f, -39.0f, -1.0f);
        this.Shape6d.setTextureSize(128, 128);
        this.Shape6d.mirror = true;
        this.setRotation(this.Shape6d, 0.0f, 0.0f, 0.6108652f);
        (this.Shape6e = new ModelRenderer((ModelBase) this, 103, 8))
            .addBox(0.0f, 0.0f, 0.0f, 1, 6, 2);
        this.Shape6e.setRotationPoint(1.0f, -39.0f, -1.0f);
        this.Shape6e.setTextureSize(128, 128);
        this.Shape6e.mirror = true;
        this.setRotation(this.Shape6e, 0.0f, 0.0f, -0.6108652f);
        (this.Shape6f = new ModelRenderer((ModelBase) this, 96, 8))
            .addBox(0.0f, 0.0f, 0.0f, 2, 6, 1);
        this.Shape6f.setRotationPoint(-1.0f, -39.0f, -2.0f);
        this.Shape6f.setTextureSize(128, 128);
        this.Shape6f.mirror = true;
        this.setRotation(this.Shape6f, -0.6108652f, 0.0f, 0.0f);
        (this.Shape6g = new ModelRenderer((ModelBase) this, 96, 8))
            .addBox(0.0f, 0.0f, 0.0f, 2, 6, 1);
        this.Shape6g.setRotationPoint(-1.0f, -39.0f, 1.0f);
        this.Shape6g.setTextureSize(128, 128);
        this.Shape6g.mirror = true;
        this.setRotation(this.Shape6g, 0.6108652f, 0.0f, 0.0f);
        (this.Shape7 = new ModelRenderer((ModelBase) this, 110, 0))
            .addBox(0.0f, 0.0f, 0.0f, 4, 2, 4);
        this.Shape7.setRotationPoint(-2.0f, -40.0f, -2.0f);
        this.Shape7.setTextureSize(128, 128);
        this.Shape7.mirror = true;
        this.setRotation(this.Shape7, 0.0f, 0.0f, 0.0f);
        (this.Shape8 = new ModelRenderer((ModelBase) this, 84, 46))
            .addBox(0.0f, 0.0f, 0.0f, 10, 5, 10);
        this.Shape8.setRotationPoint(-5.0f, -34.0f, -5.0f);
        this.Shape8.setTextureSize(128, 128);
        this.Shape8.mirror = true;
        this.setRotation(this.Shape8, 0.0f, 0.0f, 0.0f);
        (this.Shape9 = new ModelRenderer((ModelBase) this, 110, 8))
            .addBox(0.0f, 0.0f, 0.0f, 2, 8, 2);
        this.Shape9.setRotationPoint(-1.0f, -42.0f, -1.0f);
        this.Shape9.setTextureSize(128, 128);
        this.Shape9.mirror = true;
        this.setRotation(this.Shape9, 0.0f, 0.0f, 0.0f);
        (this.Shape10 = new ModelRenderer((ModelBase) this, 72, 8))
            .addBox(0.0f, 0.0f, 0.0f, 6, 6, 2);
        this.Shape10.setRotationPoint(8.0f, -23.0f, 1.0f);
        this.Shape10.setTextureSize(128, 128);
        this.Shape10.mirror = false;
        this.setRotation(this.Shape10, 0.0f, -3.141593f, -0.7853982f);
        (this.Shape10a = new ModelRenderer((ModelBase) this, 72, 17))
            .addBox(0.0f, 0.0f, 0.0f, 2, 6, 6);
        this.Shape10a.setRotationPoint(-1.0f, -23.0f, -8.0f);
        this.Shape10a.setTextureSize(128, 128);
        this.Shape10a.mirror = false;
        this.setRotation(this.Shape10a, 0.7853982f, 0.0f, 0.0f);
        (this.Shape10b = new ModelRenderer((ModelBase) this, 72, 8))
            .addBox(0.0f, 0.0f, 0.0f, 6, 6, 2);
        this.Shape10b.setRotationPoint(-8.0f, -23.0f, -1.0f);
        this.Shape10b.setTextureSize(128, 128);
        this.Shape10b.mirror = false;
        this.setRotation(this.Shape10b, 0.0f, 0.0f, -0.7853982f);
        (this.Shape10c = new ModelRenderer((ModelBase) this, 72, 8))
            .addBox(0.0f, 0.0f, 0.0f, 6, 6, 2);
        this.Shape10c.setRotationPoint(-1.0f, -23.0f, 8.0f);
        this.Shape10c.setTextureSize(128, 128);
        this.Shape10c.mirror = false;
        this.setRotation(this.Shape10c, 0.0f, 1.570796f, -0.7853982f);
        (this.Shape11d = new ModelRenderer((ModelBase) this, 72, 0))
            .addBox(0.0f, 0.0f, 0.0f, 16, 5, 2);
        this.Shape11d.setRotationPoint(-8.0f, -23.0f, -1.0f);
        this.Shape11d.setTextureSize(128, 128);
        this.Shape11d.mirror = true;
        this.setRotation(this.Shape11d, 0.0f, 0.0f, 0.0f);
        (this.Shape11e = new ModelRenderer((ModelBase) this, 72, 0))
            .addBox(0.0f, 0.0f, 0.0f, 16, 5, 2);
        this.Shape11e.setRotationPoint(-1.0f, -23.0f, 8.0f);
        this.Shape11e.setTextureSize(128, 128);
        this.Shape11e.mirror = true;
        this.setRotation(this.Shape11e, 0.0f, 1.570796f, 0.0f);
        (this.Shape2z = new ModelRenderer((ModelBase) this, 0, 103))
            .addBox(0.0f, 0.0f, 0.0f, 16, 10, 4);
        this.Shape2z.setRotationPoint(-8.0f, 14.0f, -2.0f);
        this.Shape2z.setTextureSize(128, 128);
        this.Shape2z.mirror = true;
        this.setRotation(this.Shape2z, 0.0f, 0.0f, 0.0f);
        (this.Shape12 = new ModelRenderer((ModelBase) this, 0, 76))
            .addBox(0.0f, 0.0f, 0.0f, 4, 10, 16);
        this.Shape12.setRotationPoint(-2.0f, 14.0f, -8.0f);
        this.Shape12.setTextureSize(128, 128);
        this.Shape12.mirror = true;
        this.setRotation(this.Shape12, 0.0f, 0.0f, 0.0f);
        (this.Shape13 = new ModelRenderer((ModelBase) this, 0, 47))
            .addBox(0.0f, 0.0f, 0.0f, 3, 14, 14);
        this.Shape13.setRotationPoint(6.0f, 13.0f, -8.0f);
        this.Shape13.setTextureSize(128, 128);
        this.Shape13.mirror = true;
        this.setRotation(this.Shape13, 0.7853982f, -0.7853982f, 0.0f);
        (this.Shape14 = new ModelRenderer((ModelBase) this, 0, 47))
            .addBox(0.0f, 0.0f, 0.0f, 3, 14, 14);
        this.Shape14.setRotationPoint(8.0f, 13.0f, 6.0f);
        this.Shape14.setTextureSize(128, 128);
        this.Shape14.mirror = true;
        this.setRotation(this.Shape14, 0.7853982f, -2.356194f, 0.0f);
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
        this.Shape1a.render(f5);
        this.Shape2.render(f5);
        this.Shape2a.render(f5);
        this.Shape3.render(f5);
        this.Shape4.render(f5);
        this.Shape5.render(f5);
        this.Shape6.render(f5);
        this.Shape6a.render(f5);
        this.Shape6b.render(f5);
        this.Shape6c.render(f5);
        this.Shape6d.render(f5);
        this.Shape6e.render(f5);
        this.Shape6f.render(f5);
        this.Shape6g.render(f5);
        this.Shape7.render(f5);
        this.Shape8.render(f5);
        this.Shape9.render(f5);
        this.Shape10.render(f5);
        this.Shape10a.render(f5);
        this.Shape10b.render(f5);
        this.Shape10c.render(f5);
        this.Shape11d.render(f5);
        this.Shape11e.render(f5);
        this.Shape2z.render(f5);
        this.Shape12.render(f5);
        this.Shape13.render(f5);
        this.Shape14.render(f5);
    }

    private void
    setRotation(final ModelRenderer model, final float x, final float y, final float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
}
