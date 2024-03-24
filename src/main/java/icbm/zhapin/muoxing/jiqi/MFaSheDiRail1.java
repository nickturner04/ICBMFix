package icbm.zhapin.muoxing.jiqi;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

@SideOnly(Side.CLIENT)
public class MFaSheDiRail1 extends ModelBase {
    ModelRenderer Shape11;
    ModelRenderer Shape12;
    ModelRenderer Shape13;
    ModelRenderer Shape14;
    ModelRenderer Shape15;
    ModelRenderer Shape16;
    ModelRenderer Shape20;
    ModelRenderer Shape22;
    ModelRenderer Shape23;
    ModelRenderer Shape24;
    ModelRenderer Shape25;
    ModelRenderer Shape28;
    ModelRenderer Shape29;

    public MFaSheDiRail1() {
        super.textureWidth = 128;
        super.textureHeight = 128;
        (this.Shape11 = new ModelRenderer((ModelBase) this, 35, 52))
            .addBox(0.0f, 0.0f, 0.0f, 1, 41, 4);
        this.Shape11.setRotationPoint(-16.0f, -23.0f, -2.0f);
        this.Shape11.setTextureSize(128, 128);
        this.Shape11.mirror = true;
        this.setRotation(this.Shape11, 0.0f, -0.7853982f, 0.0f);
        (this.Shape12 = new ModelRenderer((ModelBase) this, 46, 52))
            .addBox(0.0f, 0.0f, 0.0f, 3, 19, 2);
        this.Shape12.setRotationPoint(-18.0f, -1.0f, -3.0f);
        this.Shape12.setTextureSize(128, 128);
        this.Shape12.mirror = true;
        this.setRotation(this.Shape12, 0.0f, -0.7853982f, 0.0f);
        (this.Shape13 = new ModelRenderer((ModelBase) this, 0, 20))
            .addBox(0.0f, 0.0f, 0.0f, 2, 12, 2);
        this.Shape13.setRotationPoint(-16.0f, 12.0f, -1.0f);
        this.Shape13.setTextureSize(128, 128);
        this.Shape13.mirror = true;
        this.setRotation(this.Shape13, 0.0f, -0.7853982f, 0.0f);
        (this.Shape14 = new ModelRenderer((ModelBase) this, 0, 10))
            .addBox(0.0f, 0.0f, 0.0f, 2, 4, 1);
        this.Shape14.setRotationPoint(-16.0f, -27.0f, -2.0f);
        this.Shape14.setTextureSize(128, 128);
        this.Shape14.mirror = true;
        this.setRotation(this.Shape14, 0.0f, -0.7853982f, 0.0f);
        (this.Shape15 = new ModelRenderer((ModelBase) this, 0, 0))
            .addBox(0.0f, 0.0f, 0.0f, 16, 1, 8);
        this.Shape15.setRotationPoint(-24.0f, 23.0f, 0.0f);
        this.Shape15.setTextureSize(128, 128);
        this.Shape15.mirror = true;
        this.setRotation(this.Shape15, 0.0f, 0.0f, 0.0f);
        (this.Shape16 = new ModelRenderer((ModelBase) this, 57, 52))
            .addBox(0.0f, 0.0f, 0.0f, 11, 6, 6);
        this.Shape16.setRotationPoint(-20.0f, 18.0f, -8.0f);
        this.Shape16.setTextureSize(128, 128);
        this.Shape16.mirror = true;
        this.setRotation(this.Shape16, 0.0f, -0.7853982f, 0.0f);
        (this.Shape20 = new ModelRenderer((ModelBase) this, 0, 10))
            .addBox(0.0f, 0.0f, 0.0f, 2, 4, 1);
        this.Shape20.setRotationPoint(-18.0f, -27.0f, 0.0f);
        this.Shape20.setTextureSize(128, 128);
        this.Shape20.mirror = true;
        this.setRotation(this.Shape20, 0.0f, -0.7853982f, 0.0f);
        (this.Shape22 = new ModelRenderer((ModelBase) this, 8, 10))
            .addBox(0.0f, 0.0f, 0.0f, 9, 1, 1);
        this.Shape22.setRotationPoint(-17.0f, 5.0f, -1.0f);
        this.Shape22.setTextureSize(128, 128);
        this.Shape22.mirror = true;
        this.setRotation(this.Shape22, 0.0f, 0.0f, 0.0f);
        (this.Shape23 = new ModelRenderer((ModelBase) this, 8, 10))
            .addBox(0.0f, 0.0f, 0.0f, 9, 1, 1);
        this.Shape23.setRotationPoint(-17.0f, -10.0f, -1.0f);
        this.Shape23.setTextureSize(128, 128);
        this.Shape23.mirror = true;
        this.setRotation(this.Shape23, 0.0f, 0.0f, 0.0f);
        (this.Shape24 = new ModelRenderer((ModelBase) this, 15, 20))
            .addBox(0.0f, 0.0f, 0.0f, 6, 1, 1);
        this.Shape24.setRotationPoint(-21.0f, 5.0f, 6.0f);
        this.Shape24.setTextureSize(128, 128);
        this.Shape24.mirror = true;
        this.setRotation(this.Shape24, 0.0f, 1.134464f, 0.0f);
        (this.Shape25 = new ModelRenderer((ModelBase) this, 15, 20))
            .addBox(0.0f, 0.0f, 0.0f, 6, 1, 1);
        this.Shape25.setRotationPoint(-20.0f, 5.0f, 6.0f);
        this.Shape25.setTextureSize(128, 128);
        this.Shape25.mirror = true;
        this.setRotation(this.Shape25, 0.0f, -1.047198f, 0.0f);
        (this.Shape28 = new ModelRenderer((ModelBase) this, 15, 19))
            .addBox(0.0f, 0.0f, 0.0f, 6, 1, 1);
        this.Shape28.setRotationPoint(-20.0f, -10.0f, 6.0f);
        this.Shape28.setTextureSize(128, 128);
        this.Shape28.mirror = true;
        this.setRotation(this.Shape28, 0.0f, -1.047198f, 0.0f);
        (this.Shape29 = new ModelRenderer((ModelBase) this, 15, 19))
            .addBox(0.0f, 0.0f, 0.0f, 6, 1, 1);
        this.Shape29.setRotationPoint(-21.0f, -10.0f, 6.0f);
        this.Shape29.setTextureSize(128, 128);
        this.Shape29.mirror = true;
        this.setRotation(this.Shape29, 0.0f, 1.047198f, 0.0f);
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
        this.Shape11.render(f5);
        this.Shape12.render(f5);
        this.Shape13.render(f5);
        this.Shape14.render(f5);
        this.Shape15.render(f5);
        this.Shape16.render(f5);
        this.Shape20.render(f5);
        this.Shape22.render(f5);
        this.Shape23.render(f5);
        this.Shape24.render(f5);
        this.Shape25.render(f5);
        this.Shape28.render(f5);
        this.Shape29.render(f5);
    }

    public void render(final float f5) {
        this.Shape11.render(f5);
        this.Shape12.render(f5);
        this.Shape13.render(f5);
        this.Shape14.render(f5);
        this.Shape15.render(f5);
        this.Shape16.render(f5);
        this.Shape20.render(f5);
        this.Shape22.render(f5);
        this.Shape23.render(f5);
        this.Shape24.render(f5);
        this.Shape25.render(f5);
        this.Shape28.render(f5);
        this.Shape29.render(f5);
    }

    private void
    setRotation(final ModelRenderer model, final float x, final float y, final float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
}
