package icbm.zhapin.muoxing.jiqi;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

@SideOnly(Side.CLIENT)
public class MFaSheDi1 extends ModelBase {
    ModelRenderer Shape1;
    ModelRenderer Shape6;
    ModelRenderer Shape2;
    ModelRenderer Shape3;

    public MFaSheDi1() {
        super.textureWidth = 128;
        super.textureHeight = 128;
        (this.Shape1 = new ModelRenderer((ModelBase) this, 0, 111))
            .addBox(0.0f, 0.0f, 0.0f, 16, 1, 16);
        this.Shape1.setRotationPoint(-8.0f, 23.0f, -8.0f);
        this.Shape1.setTextureSize(128, 128);
        this.Shape1.mirror = true;
        this.setRotation(this.Shape1, 0.0f, 0.0f, 0.0f);
        (this.Shape6 = new ModelRenderer((ModelBase) this, 0, 111))
            .addBox(0.0f, 0.0f, 0.0f, 16, 1, 16);
        this.Shape6.setRotationPoint(-8.0f, 19.0f, -8.0f);
        this.Shape6.setTextureSize(128, 128);
        this.Shape6.mirror = true;
        this.setRotation(this.Shape6, 0.0f, 0.0f, 0.0f);
        (this.Shape2 = new ModelRenderer((ModelBase) this, 63, 0))
            .addBox(0.0f, 0.0f, 0.0f, 2, 4, 2);
        this.Shape2.setRotationPoint(-8.0f, 20.0f, -1.0f);
        this.Shape2.setTextureSize(128, 128);
        this.Shape2.mirror = true;
        this.setRotation(this.Shape2, 0.0f, 0.0f, 0.0f);
        (this.Shape3 = new ModelRenderer((ModelBase) this, 63, 0))
            .addBox(0.0f, 0.0f, 0.0f, 2, 4, 2);
        this.Shape3.setRotationPoint(6.0f, 20.0f, -1.0f);
        this.Shape3.setTextureSize(128, 128);
        this.Shape3.mirror = true;
        this.setRotation(this.Shape3, 0.0f, 0.0f, 0.0f);
    }

    public void render(final float f5) {
        this.Shape1.render(f5);
        this.Shape6.render(f5);
        this.Shape2.render(f5);
        this.Shape3.render(f5);
    }

    private void
    setRotation(final ModelRenderer model, final float x, final float y, final float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
}
