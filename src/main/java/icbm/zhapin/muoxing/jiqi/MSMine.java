package icbm.zhapin.muoxing.jiqi;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import icbm.core.di.MICBM;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

@SideOnly(Side.CLIENT)
public class MSMine extends MICBM {
    public static final MSMine INSTANCE;
    ModelRenderer A;
    ModelRenderer B;
    ModelRenderer C;

    public MSMine() {
        super.textureWidth = 45;
        super.textureHeight = 26;
        (this.A = new ModelRenderer((ModelBase) this, 0, 0))
            .addBox(-5.0f, 0.0f, -5.0f, 10, 2, 10);
        this.A.setRotationPoint(0.0f, 22.0f, 0.0f);
        this.A.setTextureSize(45, 26);
        this.A.mirror = true;
        this.setRotation(this.A, 0.0f, 0.7853982f, 0.0f);
        (this.B = new ModelRenderer((ModelBase) this, 0, 14))
            .addBox(-8.0f, 0.0f, -2.0f, 16, 1, 4);
        this.B.setRotationPoint(0.0f, 23.0f, 0.0f);
        this.B.setTextureSize(45, 26);
        this.B.mirror = true;
        this.setRotation(this.B, 0.0f, 0.7853982f, 0.0f);
        (this.C = new ModelRenderer((ModelBase) this, 0, 14))
            .addBox(-8.0f, 0.0f, -2.0f, 16, 1, 4);
        this.C.setRotationPoint(0.0f, 23.0f, 0.0f);
        this.C.setTextureSize(45, 26);
        this.C.mirror = true;
        this.setRotation(this.C, 0.0f, 2.356194f, 0.0f);
    }

    @Override
    public void render(final float f5) {
        this.A.render(f5);
        this.B.render(f5);
        this.C.render(f5);
    }

    public void render(
        final Entity entity,
        final float x,
        final float y,
        final float z,
        final float f1,
        final float f2,
        final float f3
    ) {
        super.render(entity, x, y, z, f1, f2, f3);
        this.setRotationAngles(x, y, z, f1, f2, f3, entity);
        this.render(f3);
    }

    private void
    setRotation(final ModelRenderer model, final float x, final float y, final float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    static {
        INSTANCE = new MSMine();
    }
}
