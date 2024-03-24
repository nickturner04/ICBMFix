package icbm.zhapin.muoxing.jiqi;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

@SideOnly(Side.CLIENT)
public class MFaSheShiMuo0 extends ModelBase {
    ModelRenderer Shape4;
    ModelRenderer Shape5;

    public MFaSheShiMuo0() {
        super.textureWidth = 128;
        super.textureHeight = 128;
        (this.Shape4 = new ModelRenderer((ModelBase) this, 0, 35))
            .addBox(0.0f, 0.0f, 0.0f, 2, 8, 1);
        this.Shape4.setRotationPoint(-1.0f, 16.0f, 0.0f);
        this.Shape4.setTextureSize(128, 128);
        this.Shape4.mirror = true;
        this.setRotation(this.Shape4, 0.0f, 0.0f, 0.0f);
        (this.Shape5 = new ModelRenderer((ModelBase) this, 15, 30))
            .addBox(0.0f, 0.0f, 0.0f, 10, 1, 5);
        this.Shape5.setRotationPoint(-5.0f, 17.0f, -2.0f);
        this.Shape5.setTextureSize(128, 128);
        this.Shape5.mirror = true;
        this.setRotation(this.Shape5, 0.5235988f, 0.0f, 0.0f);
    }

    public void render(final float f5) {
        this.Shape4.render(f5);
        this.Shape5.render(f5);
    }

    private void
    setRotation(final ModelRenderer model, final float x, final float y, final float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
}
