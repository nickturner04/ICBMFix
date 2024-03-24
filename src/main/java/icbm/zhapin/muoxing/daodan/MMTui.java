package icbm.zhapin.muoxing.daodan;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import icbm.core.di.MICBM;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

@SideOnly(Side.CLIENT)
public class MMTui extends MICBM {
    ModelRenderer a;
    ModelRenderer b;
    ModelRenderer c;
    ModelRenderer d;
    ModelRenderer e;
    ModelRenderer f;
    ModelRenderer g;
    ModelRenderer h;
    ModelRenderer i;
    ModelRenderer j;
    ModelRenderer k;
    ModelRenderer l;
    ModelRenderer m;
    ModelRenderer n;
    ModelRenderer o;
    ModelRenderer p;

    public MMTui() {
        super.textureWidth = 128;
        super.textureHeight = 128;
        (this.a = new ModelRenderer((ModelBase) this, 0, 0))
            .addBox(0.0f, 0.0f, 0.0f, 6, 50, 6);
        this.a.setRotationPoint(-3.0f, -26.0f, -3.0f);
        this.a.setTextureSize(128, 128);
        this.a.mirror = true;
        this.setRotation(this.a, 0.0f, 0.0f, 0.0f);
        (this.b = new ModelRenderer((ModelBase) this, 0, 57))
            .addBox(-5.0f, 0.0f, -5.0f, 10, 16, 10);
        this.b.setRotationPoint(0.0f, 8.0f, 0.0f);
        this.b.setTextureSize(128, 128);
        this.b.mirror = true;
        this.setRotation(this.b, 0.0f, 0.7853982f, 0.0f);
        (this.c = new ModelRenderer((ModelBase) this, 59, 26))
            .addBox(-1.0f, 0.0f, -9.0f, 2, 12, 18);
        this.c.setRotationPoint(0.0f, 12.0f, 0.0f);
        this.c.setTextureSize(128, 128);
        this.c.mirror = true;
        this.setRotation(this.c, 0.0f, 0.7853982f, 0.0f);
        (this.d = new ModelRenderer((ModelBase) this, 59, 26))
            .addBox(-1.0f, 0.0f, -9.0f, 2, 12, 18);
        this.d.setRotationPoint(0.0f, 12.0f, 0.0f);
        this.d.setTextureSize(128, 128);
        this.d.mirror = true;
        this.setRotation(this.d, 0.0f, -0.7853982f, 0.0f);
        (this.e = new ModelRenderer((ModelBase) this, 59, 0))
            .addBox(-1.0f, 0.0f, 0.0f, 2, 10, 10);
        this.e.setRotationPoint(0.0f, -24.0f, 0.0f);
        this.e.setTextureSize(128, 128);
        this.e.mirror = true;
        this.setRotation(this.e, -0.7853982f, 0.7853982f, 0.0f);
        (this.f = new ModelRenderer((ModelBase) this, 59, 0))
            .addBox(-1.0f, 0.0f, 0.0f, 2, 10, 10);
        this.f.setRotationPoint(0.0f, -24.0f, 0.0f);
        this.f.setTextureSize(128, 128);
        this.f.mirror = true;
        this.setRotation(this.f, -0.7853982f, -0.7853982f, 0.0f);
        (this.g = new ModelRenderer((ModelBase) this, 25, 0))
            .addBox(-1.0f, 0.0f, -7.0f, 2, 10, 14);
        this.g.setRotationPoint(0.0f, -17.0f, 0.0f);
        this.g.setTextureSize(128, 128);
        this.g.mirror = true;
        this.setRotation(this.g, 0.0f, 0.7853982f, 0.0f);
        (this.h = new ModelRenderer((ModelBase) this, 25, 0))
            .addBox(-1.0f, 0.0f, -7.0f, 2, 10, 14);
        this.h.setRotationPoint(0.0f, -17.0f, 0.0f);
        this.h.setTextureSize(128, 128);
        this.h.mirror = true;
        this.setRotation(this.h, 0.0f, -0.7853982f, 0.0f);
        (this.i = new ModelRenderer((ModelBase) this, 25, 26))
            .addBox(-1.0f, 0.0f, 0.0f, 2, 13, 13);
        this.i.setRotationPoint(0.0f, 3.0f, 0.0f);
        this.i.setTextureSize(128, 128);
        this.i.mirror = true;
        this.setRotation(this.i, -0.7853982f, -0.7853982f, 0.0f);
        (this.j = new ModelRenderer((ModelBase) this, 25, 26))
            .addBox(-1.0f, 0.0f, 0.0f, 2, 13, 13);
        this.j.setRotationPoint(0.0f, 3.0f, 0.0f);
        this.j.setTextureSize(128, 128);
        this.j.mirror = true;
        this.setRotation(this.j, -0.7853982f, 0.7853982f, 0.0f);
        (this.k = new ModelRenderer((ModelBase) this, 0, 86))
            .addBox(2.0f, 0.0f, 2.0f, 2, 8, 2);
        this.k.setRotationPoint(0.0f, -29.0f, 0.0f);
        this.k.setTextureSize(128, 128);
        this.k.mirror = true;
        this.setRotation(this.k, 0.0f, 0.0f, 0.0f);
        (this.l = new ModelRenderer((ModelBase) this, 0, 86))
            .addBox(2.0f, 0.0f, 2.0f, 2, 8, 2);
        this.l.setRotationPoint(0.0f, -29.0f, 0.0f);
        this.l.setTextureSize(128, 128);
        this.l.mirror = true;
        this.setRotation(this.l, 0.0f, 1.570796f, 0.0f);
        (this.m = new ModelRenderer((ModelBase) this, 0, 86))
            .addBox(2.0f, 0.0f, 2.0f, 2, 8, 2);
        this.m.setRotationPoint(0.0f, -29.0f, 0.0f);
        this.m.setTextureSize(128, 128);
        this.m.mirror = true;
        this.setRotation(this.m, 0.0f, 3.141593f, 0.0f);
        (this.n = new ModelRenderer((ModelBase) this, 0, 86))
            .addBox(2.0f, 0.0f, 2.0f, 2, 8, 2);
        this.n.setRotationPoint(0.0f, -29.0f, 0.0f);
        this.n.setTextureSize(128, 128);
        this.n.mirror = true;
        this.setRotation(this.n, 0.0f, -1.570796f, 0.0f);
        (this.o = new ModelRenderer((ModelBase) this, 10, 86))
            .addBox(-2.0f, 0.0f, -2.0f, 4, 6, 4);
        this.o.setRotationPoint(0.0f, -32.0f, 0.0f);
        this.o.setTextureSize(128, 128);
        this.o.mirror = true;
        this.setRotation(this.o, 0.0f, 0.0f, 0.0f);
        (this.p = new ModelRenderer((ModelBase) this, 28, 86))
            .addBox(-1.0f, 0.0f, -1.0f, 2, 6, 2);
        this.p.setRotationPoint(0.0f, -38.0f, 0.0f);
        this.p.setTextureSize(128, 128);
        this.p.mirror = true;
        this.setRotation(this.p, 0.0f, 0.0f, 0.0f);
    }

    public void render(
        final Entity entity,
        final float x,
        final float y,
        final float z,
        final float f3,
        final float f4,
        final float f5
    ) {
        super.render(entity, x, y, z, f3, f4, f5);
        this.setRotationAngles(x, y, z, f3, f4, f5, entity);
        this.render(f5);
    }

    @Override
    public void render(final float f5) {
        this.a.render(f5);
        this.b.render(f5);
        this.c.render(f5);
        this.d.render(f5);
        this.e.render(f5);
        this.f.render(f5);
        this.g.render(f5);
        this.h.render(f5);
        this.i.render(f5);
        this.j.render(f5);
        this.k.render(f5);
        this.l.render(f5);
        this.m.render(f5);
        this.n.render(f5);
        this.o.render(f5);
        this.p.render(f5);
    }

    private void
    setRotation(final ModelRenderer model, final float x, final float y, final float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
}
