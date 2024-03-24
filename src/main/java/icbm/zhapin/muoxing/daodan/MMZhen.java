package icbm.zhapin.muoxing.daodan;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import icbm.core.di.MICBM;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

@SideOnly(Side.CLIENT)
public class MMZhen extends MICBM {
    ModelRenderer A;
    ModelRenderer B;
    ModelRenderer C;
    ModelRenderer D;
    ModelRenderer E;
    ModelRenderer F;
    ModelRenderer G;
    ModelRenderer H;
    ModelRenderer I;
    ModelRenderer J;
    ModelRenderer K;
    ModelRenderer L;
    ModelRenderer M;
    ModelRenderer N;
    ModelRenderer O;
    ModelRenderer P;
    ModelRenderer Q;
    ModelRenderer R;
    ModelRenderer S;

    public MMZhen() {
        super.textureWidth = 128;
        super.textureHeight = 128;
        (this.A = new ModelRenderer((ModelBase) this, 0, 0))
            .addBox(0.0f, 0.0f, 0.0f, 6, 50, 6);
        this.A.setRotationPoint(-3.0f, -26.0f, -3.0f);
        this.A.setTextureSize(128, 128);
        this.A.mirror = true;
        this.setRotation(this.A, 0.0f, 0.0f, 0.0f);
        (this.B = new ModelRenderer((ModelBase) this, 0, 57))
            .addBox(-5.0f, 0.0f, -5.0f, 10, 16, 10);
        this.B.setRotationPoint(0.0f, 8.0f, 0.0f);
        this.B.setTextureSize(128, 128);
        this.B.mirror = true;
        this.setRotation(this.B, 0.0f, 0.7853982f, 0.0f);
        (this.C = new ModelRenderer((ModelBase) this, 59, 26))
            .addBox(-1.0f, 0.0f, -9.0f, 2, 12, 18);
        this.C.setRotationPoint(0.0f, 12.0f, 0.0f);
        this.C.setTextureSize(128, 128);
        this.C.mirror = true;
        this.setRotation(this.C, 0.0f, 0.7853982f, 0.0f);
        (this.D = new ModelRenderer((ModelBase) this, 59, 26))
            .addBox(-1.0f, 0.0f, -9.0f, 2, 12, 18);
        this.D.setRotationPoint(0.0f, 12.0f, 0.0f);
        this.D.setTextureSize(128, 128);
        this.D.mirror = true;
        this.setRotation(this.D, 0.0f, -0.7853982f, 0.0f);
        (this.E = new ModelRenderer((ModelBase) this, 59, 0))
            .addBox(-1.0f, 0.0f, 0.0f, 2, 10, 10);
        this.E.setRotationPoint(0.0f, -24.0f, 0.0f);
        this.E.setTextureSize(128, 128);
        this.E.mirror = true;
        this.setRotation(this.E, -0.7853982f, 0.7853982f, 0.0f);
        (this.F = new ModelRenderer((ModelBase) this, 59, 0))
            .addBox(-1.0f, 0.0f, 0.0f, 2, 10, 10);
        this.F.setRotationPoint(0.0f, -24.0f, 0.0f);
        this.F.setTextureSize(128, 128);
        this.F.mirror = true;
        this.setRotation(this.F, -0.7853982f, -0.7853982f, 0.0f);
        (this.G = new ModelRenderer((ModelBase) this, 25, 0))
            .addBox(-1.0f, 0.0f, -7.0f, 2, 10, 14);
        this.G.setRotationPoint(0.0f, -17.0f, 0.0f);
        this.G.setTextureSize(128, 128);
        this.G.mirror = true;
        this.setRotation(this.G, 0.0f, 0.7853982f, 0.0f);
        (this.H = new ModelRenderer((ModelBase) this, 25, 0))
            .addBox(-1.0f, 0.0f, -7.0f, 2, 10, 14);
        this.H.setRotationPoint(0.0f, -17.0f, 0.0f);
        this.H.setTextureSize(128, 128);
        this.H.mirror = true;
        this.setRotation(this.H, 0.0f, -0.7853982f, 0.0f);
        (this.I = new ModelRenderer((ModelBase) this, 25, 26))
            .addBox(-1.0f, 0.0f, 0.0f, 2, 13, 13);
        this.I.setRotationPoint(0.0f, 3.0f, 0.0f);
        this.I.setTextureSize(128, 128);
        this.I.mirror = true;
        this.setRotation(this.I, -0.7853982f, -0.7853982f, 0.0f);
        (this.J = new ModelRenderer((ModelBase) this, 25, 26))
            .addBox(-1.0f, 0.0f, 0.0f, 2, 13, 13);
        this.J.setRotationPoint(0.0f, 3.0f, 0.0f);
        this.J.setTextureSize(128, 128);
        this.J.mirror = true;
        this.setRotation(this.J, -0.7853982f, 0.7853982f, 0.0f);
        (this.K = new ModelRenderer((ModelBase) this, 44, 62))
            .addBox(0.0f, 0.0f, 0.0f, 8, 2, 8);
        this.K.setRotationPoint(-4.0f, -28.0f, -4.0f);
        this.K.setTextureSize(128, 128);
        this.K.mirror = true;
        this.setRotation(this.K, 0.0f, 0.0f, 0.0f);
        (this.L = new ModelRenderer((ModelBase) this, 72, 62))
            .addBox(0.0f, 0.0f, 0.0f, 8, 1, 2);
        this.L.setRotationPoint(-3.0f, -28.0f, -1.0f);
        this.L.setTextureSize(128, 128);
        this.L.mirror = true;
        this.setRotation(this.L, 0.0f, 0.0f, -0.9773844f);
        (this.M = new ModelRenderer((ModelBase) this, 72, 62))
            .addBox(0.0f, 0.0f, 0.0f, 8, 1, 2);
        this.M.setRotationPoint(3.0f, -28.0f, 1.0f);
        this.M.setTextureSize(128, 128);
        this.M.mirror = true;
        this.setRotation(this.M, 0.0f, 3.141593f, -0.9773844f);
        (this.N = new ModelRenderer((ModelBase) this, 44, 74))
            .addBox(0.0f, 0.0f, 0.0f, 6, 2, 6);
        this.N.setRotationPoint(-3.0f, -35.0f, -3.0f);
        this.N.setTextureSize(128, 128);
        this.N.mirror = true;
        this.setRotation(this.N, 0.0f, 0.0f, 0.0f);
        (this.O = new ModelRenderer((ModelBase) this, 44, 85))
            .addBox(0.0f, 0.0f, 0.0f, 6, 6, 8);
        this.O.setRotationPoint(-4.0f, -41.0f, -4.0f);
        this.O.setTextureSize(128, 128);
        this.O.mirror = true;
        this.setRotation(this.O, 0.0f, 0.0f, 0.0f);
        (this.P = new ModelRenderer((ModelBase) this, 75, 92))
            .addBox(0.0f, 0.0f, 0.0f, 7, 2, 8);
        this.P.setRotationPoint(-4.0f, -41.0f, -4.0f);
        this.P.setTextureSize(128, 128);
        this.P.mirror = true;
        this.setRotation(this.P, 0.0f, 0.0f, -0.1745329f);
        (this.Q = new ModelRenderer((ModelBase) this, 44, 101))
            .addBox(0.0f, 0.0f, 0.0f, 5, 2, 8);
        this.Q.setRotationPoint(3.0f, -42.0f, -4.0f);
        this.Q.setTextureSize(128, 128);
        this.Q.mirror = true;
        this.setRotation(this.Q, 0.0f, 0.0f, 1.186824f);
        (this.R = new ModelRenderer((ModelBase) this, 73, 85))
            .addBox(0.0f, 0.0f, 0.0f, 6, 2, 2);
        this.R.setRotationPoint(-3.0f, -39.0f, -6.0f);
        this.R.setTextureSize(128, 128);
        this.R.mirror = true;
        this.setRotation(this.R, 0.0f, 0.0f, 0.2094395f);
        (this.S = new ModelRenderer((ModelBase) this, 92, 83))
            .addBox(4.0f, 0.0f, 0.0f, 2, 2, 4);
        this.S.setRotationPoint(-3.0f, -39.0f, -6.0f);
        this.S.setTextureSize(128, 128);
        this.S.mirror = true;
        this.setRotation(this.S, 0.0f, 0.0f, 0.2094395f);
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
        this.render(f5);
    }

    @Override
    public void render(final float f5) {
        this.A.render(f5);
        this.B.render(f5);
        this.C.render(f5);
        this.D.render(f5);
        this.E.render(f5);
        this.F.render(f5);
        this.G.render(f5);
        this.H.render(f5);
        this.I.render(f5);
        this.J.render(f5);
        this.K.render(f5);
        this.L.render(f5);
        this.M.render(f5);
        this.N.render(f5);
        this.O.render(f5);
        this.P.render(f5);
        this.Q.render(f5);
        this.R.render(f5);
        this.S.render(f5);
    }

    private void
    setRotation(final ModelRenderer model, final float x, final float y, final float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
}
