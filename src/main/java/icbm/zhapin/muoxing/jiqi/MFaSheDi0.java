package icbm.zhapin.muoxing.jiqi;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

@SideOnly(Side.CLIENT)
public class MFaSheDi0 extends ModelBase {
    ModelRenderer Shape1;
    ModelRenderer Shape6;
    ModelRenderer Shape8;
    ModelRenderer Shape13;

    public MFaSheDi0() {
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
        (this.Shape8 = new ModelRenderer((ModelBase) this, 0, 20))
            .addBox(0.0f, 0.0f, 0.0f, 2, 12, 2);
        this.Shape8.setRotationPoint(6.0f, 11.0f, -1.0f);
        this.Shape8.setTextureSize(128, 128);
        this.Shape8.mirror = true;
        this.setRotation(this.Shape8, 0.0f, 0.0f, 0.0f);
        (this.Shape13 = new ModelRenderer((ModelBase) this, 0, 20))
            .addBox(0.0f, 0.0f, 0.0f, 2, 12, 2);
        this.Shape13.setRotationPoint(-7.0f, 11.0f, -1.0f);
        this.Shape13.setTextureSize(128, 128);
        this.Shape13.mirror = true;
        this.setRotation(this.Shape13, 0.0f, 0.0f, 0.0f);
    }

    public void render(final float f5) {
        this.Shape1.render(f5);
        this.Shape6.render(f5);
        this.Shape8.render(f5);
        this.Shape13.render(f5);
    }

    private void
    setRotation(final ModelRenderer model, final float x, final float y, final float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
}
