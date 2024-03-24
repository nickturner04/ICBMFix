package icbm.zhapin.muoxing.jiqi;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

@SideOnly(Side.CLIENT)
public class MFaSheDi2 extends ModelBase {
    ModelRenderer Shape12;
    ModelRenderer Shape13;

    public MFaSheDi2() {
        super.textureWidth = 256;
        super.textureHeight = 256;
        (this.Shape12 = new ModelRenderer((ModelBase) this, 0, 80))
            .addBox(0.0f, 0.0f, 0.0f, 11, 6, 6);
        this.Shape12.setRotationPoint(-5.0f, 18.0f, -6.0f);
        this.Shape12.setTextureSize(256, 256);
        this.Shape12.mirror = true;
        this.setRotation(this.Shape12, 0.0f, 0.0f, 0.0f);
        (this.Shape13 = new ModelRenderer((ModelBase) this, 0, 80))
            .addBox(0.0f, 0.0f, 0.0f, 11, 6, 6);
        this.Shape13.setRotationPoint(-5.0f, 18.0f, 1.0f);
        this.Shape13.setTextureSize(256, 256);
        this.Shape13.mirror = true;
        this.setRotation(this.Shape13, 0.0f, 0.0f, 0.0f);
    }

    public void render(final float f5) {
        this.Shape12.render(f5);
        this.Shape13.render(f5);
    }

    private void
    setRotation(final ModelRenderer model, final float x, final float y, final float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
}
