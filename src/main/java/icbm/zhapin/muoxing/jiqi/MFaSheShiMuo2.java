package icbm.zhapin.muoxing.jiqi;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

@SideOnly(Side.CLIENT)
public class MFaSheShiMuo2 extends ModelBase {
    ModelRenderer Shape27;
    ModelRenderer Shape29;
    ModelRenderer Shape30;
    ModelRenderer Shape31;

    public MFaSheShiMuo2() {
        super.textureWidth = 256;
        super.textureHeight = 256;
        (this.Shape27 = new ModelRenderer((ModelBase) this, 0, 30))
            .addBox(0.0f, 0.0f, 0.0f, 8, 9, 3);
        this.Shape27.setRotationPoint(-4.0f, 15.0f, -2.0f);
        this.Shape27.setTextureSize(256, 256);
        this.Shape27.mirror = true;
        this.setRotation(this.Shape27, 0.0f, 0.0f, 0.0f);
        (this.Shape29 = new ModelRenderer((ModelBase) this, 0, 19))
            .addBox(0.0f, 0.0f, 0.0f, 10, 1, 8);
        this.Shape29.setRotationPoint(-5.0f, 16.0f, -6.0f);
        this.Shape29.setTextureSize(256, 256);
        this.Shape29.mirror = true;
        this.setRotation(this.Shape29, 0.3141593f, 0.0f, 0.0f);
        (this.Shape30 = new ModelRenderer((ModelBase) this, 0, 0))
            .addBox(0.0f, 0.0f, 0.0f, 10, 8, 1);
        this.Shape30.setRotationPoint(-5.0f, 6.0f, 5.0f);
        this.Shape30.setTextureSize(256, 256);
        this.Shape30.mirror = true;
        this.setRotation(this.Shape30, -0.4363323f, 0.0f, 0.0f);
        (this.Shape31 = new ModelRenderer((ModelBase) this, 25, 0))
            .addBox(0.0f, 0.0f, 0.0f, 2, 10, 1);
        this.Shape31.setRotationPoint(-1.0f, 10.0f, 4.0f);
        this.Shape31.setTextureSize(256, 256);
        this.Shape31.mirror = true;
        this.setRotation(this.Shape31, -0.4363323f, 0.0f, 0.0f);
    }

    public void render(final float f5) {
        this.Shape27.render(f5);
        this.Shape29.render(f5);
        this.Shape30.render(f5);
        this.Shape31.render(f5);
    }

    private void
    setRotation(final ModelRenderer model, final float x, final float y, final float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
}
