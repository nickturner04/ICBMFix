package icbm.zhapin.muoxing.jiqi;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

@SideOnly(Side.CLIENT)
public class MFaSheDiRail2 extends ModelBase {
    ModelRenderer Shape9;
    ModelRenderer Shape20;
    ModelRenderer Shape21;
    ModelRenderer Shape16;

    public MFaSheDiRail2() {
        super.textureWidth = 256;
        super.textureHeight = 256;
        (this.Shape9 = new ModelRenderer((ModelBase) this, 170, 0))
            .addBox(0.0f, 0.0f, 0.0f, 10, 37, 10);
        this.Shape9.setRotationPoint(-22.0f, -19.0f, -5.0f);
        this.Shape9.setTextureSize(256, 256);
        this.Shape9.mirror = true;
        this.setRotation(this.Shape9, 0.0f, 0.0f, 0.0f);
        (this.Shape20 = new ModelRenderer((ModelBase) this, 170, 50))
            .addBox(0.0f, 0.0f, 0.0f, 1, 40, 2);
        this.Shape20.setRotationPoint(-19.0f, -22.0f, -6.0f);
        this.Shape20.setTextureSize(256, 256);
        this.Shape20.mirror = true;
        this.setRotation(this.Shape20, 0.0f, 0.0f, 0.0f);
        (this.Shape21 = new ModelRenderer((ModelBase) this, 100, 0))
            .addBox(0.0f, 0.0f, 0.0f, 16, 6, 14);
        this.Shape21.setRotationPoint(-25.0f, 18.0f, -7.0f);
        this.Shape21.setTextureSize(256, 256);
        this.Shape21.mirror = true;
        this.setRotation(this.Shape21, 0.0f, 0.0f, 0.0f);
        (this.Shape16 = new ModelRenderer((ModelBase) this, 170, 50))
            .addBox(0.0f, 0.0f, 0.0f, 1, 40, 2);
        this.Shape16.setRotationPoint(-16.0f, -22.0f, -6.0f);
        this.Shape16.setTextureSize(256, 256);
        this.Shape16.mirror = true;
        this.setRotation(this.Shape16, 0.0f, 0.0f, 0.0f);
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
        this.Shape9.render(f5);
        this.Shape20.render(f5);
        this.Shape21.render(f5);
        this.Shape16.render(f5);
    }

    public void render(final float f5) {
        this.Shape9.render(f5);
        this.Shape20.render(f5);
        this.Shape21.render(f5);
        this.Shape16.render(f5);
    }

    private void
    setRotation(final ModelRenderer model, final float x, final float y, final float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
}
