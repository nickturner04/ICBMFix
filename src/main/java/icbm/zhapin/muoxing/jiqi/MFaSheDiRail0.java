package icbm.zhapin.muoxing.jiqi;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

@SideOnly(Side.CLIENT)
public class MFaSheDiRail0 extends ModelBase {
    ModelRenderer Shape7;
    ModelRenderer Shape8;
    ModelRenderer Shape9;
    ModelRenderer Shape10;
    ModelRenderer Shape11;
    ModelRenderer Shape12;
    ModelRenderer Shape13;
    ModelRenderer Shape14;
    ModelRenderer Shape15;
    ModelRenderer Shape16;
    ModelRenderer Shape17;

    public MFaSheDiRail0() {
        super.textureWidth = 128;
        super.textureHeight = 128;
        (this.Shape7 = new ModelRenderer((ModelBase) this, 35, 52))
            .addBox(0.0f, 0.0f, 0.0f, 1, 41, 4);
        this.Shape7.setRotationPoint(8.0f, -24.0f, -2.0f);
        this.Shape7.setTextureSize(128, 128);
        this.Shape7.mirror = true;
        this.setRotation(this.Shape7, 0.0f, 0.0f, 0.0f);
        (this.Shape8 = new ModelRenderer((ModelBase) this, 0, 20))
            .addBox(0.0f, 0.0f, 0.0f, 2, 12, 2);
        this.Shape8.setRotationPoint(6.0f, 11.0f, -1.0f);
        this.Shape8.setTextureSize(128, 128);
        this.Shape8.mirror = true;
        this.setRotation(this.Shape8, 0.0f, 0.0f, 0.0f);
        (this.Shape9 = new ModelRenderer((ModelBase) this, 46, 52))
            .addBox(0.0f, 0.0f, 0.0f, 3, 19, 2);
        this.Shape9.setRotationPoint(9.0f, -2.0f, -1.0f);
        this.Shape9.setTextureSize(128, 128);
        this.Shape9.mirror = true;
        this.setRotation(this.Shape9, 0.0f, 0.0f, 0.0f);
        (this.Shape10 = new ModelRenderer((ModelBase) this, 0, 10))
            .addBox(0.0f, 0.0f, 0.0f, 2, 4, 2);
        this.Shape10.setRotationPoint(7.0f, -28.0f, -1.0f);
        this.Shape10.setTextureSize(128, 128);
        this.Shape10.mirror = true;
        this.setRotation(this.Shape10, 0.0f, 0.0f, 0.0f);
        (this.Shape11 = new ModelRenderer((ModelBase) this, 35, 52))
            .addBox(0.0f, 0.0f, 0.0f, 1, 41, 4);
        this.Shape11.setRotationPoint(-8.0f, -24.0f, -2.0f);
        this.Shape11.setTextureSize(128, 128);
        this.Shape11.mirror = true;
        this.setRotation(this.Shape11, 0.0f, 0.0f, 0.0f);
        (this.Shape12 = new ModelRenderer((ModelBase) this, 46, 52))
            .addBox(0.0f, 0.0f, 0.0f, 3, 19, 2);
        this.Shape12.setRotationPoint(-11.0f, -2.0f, -1.0f);
        this.Shape12.setTextureSize(128, 128);
        this.Shape12.mirror = true;
        this.setRotation(this.Shape12, 0.0f, 0.0f, 0.0f);
        (this.Shape13 = new ModelRenderer((ModelBase) this, 0, 20))
            .addBox(0.0f, 0.0f, 0.0f, 2, 12, 2);
        this.Shape13.setRotationPoint(-7.0f, 11.0f, -1.0f);
        this.Shape13.setTextureSize(128, 128);
        this.Shape13.mirror = true;
        this.setRotation(this.Shape13, 0.0f, 0.0f, 0.0f);
        (this.Shape14 = new ModelRenderer((ModelBase) this, 0, 10))
            .addBox(0.0f, 0.0f, 0.0f, 2, 4, 2);
        this.Shape14.setRotationPoint(-8.0f, -28.0f, -1.0f);
        this.Shape14.setTextureSize(128, 128);
        this.Shape14.mirror = true;
        this.setRotation(this.Shape14, 0.0f, 0.0f, 0.0f);
        (this.Shape15 = new ModelRenderer((ModelBase) this, 0, 0))
            .addBox(0.0f, 0.0f, 0.0f, 48, 1, 8);
        this.Shape15.setRotationPoint(-24.0f, 23.0f, -4.0f);
        this.Shape15.setTextureSize(128, 128);
        this.Shape15.mirror = true;
        this.setRotation(this.Shape15, 0.0f, 0.0f, 0.0f);
        (this.Shape16 = new ModelRenderer((ModelBase) this, 57, 52))
            .addBox(0.0f, 0.0f, 0.0f, 9, 6, 6);
        this.Shape16.setRotationPoint(-16.0f, 17.0f, -3.0f);
        this.Shape16.setTextureSize(128, 128);
        this.Shape16.mirror = true;
        this.setRotation(this.Shape16, 0.0f, 0.0f, 0.0f);
        (this.Shape17 = new ModelRenderer((ModelBase) this, 57, 52))
            .addBox(0.0f, 0.0f, 0.0f, 9, 6, 6);
        this.Shape17.setRotationPoint(8.0f, 17.0f, -3.0f);
        this.Shape17.setTextureSize(128, 128);
        this.Shape17.mirror = true;
        this.setRotation(this.Shape17, 0.0f, 0.0f, 0.0f);
    }

    public void render(final float f5) {
        this.Shape7.render(f5);
        this.Shape8.render(f5);
        this.Shape9.render(f5);
        this.Shape10.render(f5);
        this.Shape11.render(f5);
        this.Shape12.render(f5);
        this.Shape13.render(f5);
        this.Shape14.render(f5);
        this.Shape15.render(f5);
        this.Shape16.render(f5);
        this.Shape17.render(f5);
    }

    private void
    setRotation(final ModelRenderer model, final float x, final float y, final float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
}
