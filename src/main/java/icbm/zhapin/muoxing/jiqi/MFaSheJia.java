package icbm.zhapin.muoxing.jiqi;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

@SideOnly(Side.CLIENT)
public class MFaSheJia extends ModelBase {
    ModelRenderer Shape18;
    ModelRenderer Shape19;

    public MFaSheJia() {
        super.textureWidth = 128;
        super.textureHeight = 128;
        (this.Shape18 = new ModelRenderer((ModelBase) this, 57, 65))
            .addBox(0.0f, 0.0f, 0.0f, 16, 6, 8);
        this.Shape18.setRotationPoint(-8.0f, 18.0f, -4.0f);
        this.Shape18.setTextureSize(128, 128);
        this.Shape18.mirror = true;
        this.setRotation(this.Shape18, 0.0f, 0.0f, 0.0f);
        (this.Shape19 = new ModelRenderer((ModelBase) this, 0, 52))
            .addBox(0.0f, 0.0f, 0.0f, 8, 50, 8);
        this.Shape19.setRotationPoint(-4.0f, -32.0f, -4.0f);
        this.Shape19.setTextureSize(128, 128);
        this.Shape19.mirror = true;
        this.setRotation(this.Shape19, 0.0f, 0.0f, 0.0f);
    }

    public void render(final float f5) {
        this.Shape18.render(f5);
        this.Shape19.render(f5);
    }

    private void
    setRotation(final ModelRenderer model, final float x, final float y, final float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
}
