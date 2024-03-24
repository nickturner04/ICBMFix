package icbm.zhapin.muoxing.jiqi;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

@SideOnly(Side.CLIENT)
public class MShouFaSheQi extends ModelBase {
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
    ModelRenderer q;
    ModelRenderer r;
    ModelRenderer s;
    ModelRenderer t;
    ModelRenderer u;

    public MShouFaSheQi() {
        super.textureWidth = 128;
        super.textureHeight = 128;
        (this.a = new ModelRenderer((ModelBase) this, 0, 0))
            .addBox(-2.0f, -2.0f, -16.0f, 4, 4, 34);
        this.a.setRotationPoint(0.0f, 19.0f, 0.0f);
        this.a.setTextureSize(128, 128);
        this.a.mirror = true;
        this.setRotation(this.a, 0.0f, 0.0f, 0.0f);
        (this.b = new ModelRenderer((ModelBase) this, 0, 7))
            .addBox(0.0f, 0.0f, 0.0f, 1, 3, 2);
        this.b.setRotationPoint(0.0f, 21.0f, -10.0f);
        this.b.setTextureSize(128, 128);
        this.b.mirror = true;
        this.setRotation(this.b, 0.2792527f, 0.0f, 0.0f);
        (this.c = new ModelRenderer((ModelBase) this, 7, 6))
            .addBox(0.0f, 0.0f, 0.0f, 1, 4, 2);
        this.c.setRotationPoint(0.0f, 21.0f, 2.0f);
        this.c.setTextureSize(128, 128);
        this.c.mirror = true;
        this.setRotation(this.c, 0.0f, 0.0f, 0.0f);
        (this.d = new ModelRenderer((ModelBase) this, 14, 3))
            .addBox(-1.0f, 0.0f, 0.0f, 2, 4, 0);
        this.d.setRotationPoint(0.0f, 11.0f, -14.0f);
        this.d.setTextureSize(128, 128);
        this.d.mirror = true;
        this.setRotation(this.d, 0.0f, 0.0f, 0.0f);
        (this.e = new ModelRenderer((ModelBase) this, 0, 0))
            .addBox(-1.0f, 0.0f, 0.0f, 2, 1, 4);
        this.e.setRotationPoint(0.0f, 16.0f, -15.0f);
        this.e.setTextureSize(128, 128);
        this.e.mirror = true;
        this.setRotation(this.e, 0.0f, 0.0f, 0.0f);
        (this.f = new ModelRenderer((ModelBase) this, 14, 0))
            .addBox(-2.0f, 0.0f, 0.0f, 4, 2, 0);
        this.f.setRotationPoint(0.0f, 15.0f, -14.0f);
        this.f.setTextureSize(128, 128);
        this.f.mirror = true;
        this.setRotation(this.f, 0.0f, 0.0f, 0.0f);
        (this.g = new ModelRenderer((ModelBase) this, 0, 40))
            .addBox(0.0f, -1.0f, 0.0f, 3, 1, 12);
        this.g.setRotationPoint(-3.0f, 20.0f, 9.0f);
        this.g.setTextureSize(128, 128);
        this.g.mirror = true;
        this.setRotation(this.g, 0.0f, 0.0f, 0.7853982f);
        (this.h = new ModelRenderer((ModelBase) this, 0, 53))
            .addBox(-3.0f, 0.0f, 0.0f, 3, 1, 8);
        this.h.setRotationPoint(-1.0f, 16.0f, 11.0f);
        this.h.setTextureSize(128, 128);
        this.h.mirror = true;
        this.setRotation(this.h, 0.0f, 0.0f, -0.7853982f);
        (this.i = new ModelRenderer((ModelBase) this, 31, 40))
            .addBox(0.0f, 0.0f, 0.0f, 2, 1, 12);
        this.i.setRotationPoint(-1.0f, 21.0f, 9.0f);
        this.i.setTextureSize(128, 128);
        this.i.mirror = true;
        this.setRotation(this.i, 0.0f, 0.0f, 0.0f);
        (this.j = new ModelRenderer((ModelBase) this, 23, 53))
            .addBox(0.0f, 0.0f, 0.0f, 2, 1, 8);
        this.j.setRotationPoint(-1.0f, 16.0f, 11.0f);
        this.j.setTextureSize(128, 128);
        this.j.mirror = true;
        this.setRotation(this.j, 0.0f, 0.0f, 0.0f);
        (this.k = new ModelRenderer((ModelBase) this, 0, 53))
            .addBox(0.0f, 0.0f, 0.0f, 3, 1, 8);
        this.k.setRotationPoint(1.0f, 16.0f, 11.0f);
        this.k.setTextureSize(128, 128);
        this.k.mirror = true;
        this.setRotation(this.k, 0.0f, 0.0f, 0.7853982f);
        (this.l = new ModelRenderer((ModelBase) this, 0, 40))
            .addBox(0.0f, -1.0f, 0.0f, 3, 1, 12);
        this.l.setRotationPoint(1.0f, 22.0f, 9.0f);
        this.l.setTextureSize(128, 128);
        this.l.mirror = true;
        this.setRotation(this.l, 0.0f, 0.0f, -0.7853982f);
        (this.m = new ModelRenderer((ModelBase) this, 47, 0))
            .addBox(0.0f, 0.0f, 0.0f, 2, 2, 6);
        this.m.setRotationPoint(2.0f, 15.0f, -10.0f);
        this.m.setTextureSize(128, 128);
        this.m.mirror = true;
        this.setRotation(this.m, 0.0f, 0.0f, 0.0f);
        (this.n = new ModelRenderer((ModelBase) this, 47, 10))
            .addBox(0.0f, 0.0f, 0.0f, 2, 3, 1);
        this.n.setRotationPoint(2.0f, 15.0f, -9.0f);
        this.n.setTextureSize(128, 128);
        this.n.mirror = true;
        this.setRotation(this.n, 0.0f, 0.0f, 0.7853982f);
        (this.o = new ModelRenderer((ModelBase) this, 47, 10))
            .addBox(0.0f, 0.0f, 0.0f, 2, 3, 1);
        this.o.setRotationPoint(2.0f, 15.0f, -6.0f);
        this.o.setTextureSize(128, 128);
        this.o.mirror = true;
        this.setRotation(this.o, 0.0f, 0.0f, 0.7853982f);
        (this.p = new ModelRenderer((ModelBase) this, 0, 14))
            .addBox(0.0f, 0.0f, 0.0f, 3, 1, 4);
        this.p.setRotationPoint(1.0f, 16.0f, -17.0f);
        this.p.setTextureSize(128, 128);
        this.p.mirror = true;
        this.setRotation(this.p, 0.0f, 0.0f, 0.7853982f);
        (this.q = new ModelRenderer((ModelBase) this, 0, 14))
            .addBox(0.0f, -1.0f, 0.0f, 3, 1, 4);
        this.q.setRotationPoint(1.0f, 22.0f, -17.0f);
        this.q.setTextureSize(128, 128);
        this.q.mirror = true;
        this.setRotation(this.q, 0.0f, 0.0f, -0.7853982f);
        (this.r = new ModelRenderer((ModelBase) this, 0, 21))
            .addBox(-1.0f, 0.0f, 0.0f, 1, 2, 4);
        this.r.setRotationPoint(3.0f, 18.0f, -17.0f);
        this.r.setTextureSize(128, 128);
        this.r.mirror = true;
        this.setRotation(this.r, 0.0f, 0.0f, 0.0f);
        (this.s = new ModelRenderer((ModelBase) this, 0, 14))
            .addBox(0.0f, 0.0f, 0.0f, 3, 1, 4);
        this.s.setRotationPoint(-3.0f, 18.0f, -17.0f);
        this.s.setTextureSize(128, 128);
        this.s.mirror = true;
        this.setRotation(this.s, 0.0f, 0.0f, -0.7853982f);
        (this.t = new ModelRenderer((ModelBase) this, 0, 21))
            .addBox(0.0f, -1.0f, 0.0f, 1, 2, 4);
        this.t.setRotationPoint(-3.0f, 19.0f, -17.0f);
        this.t.setTextureSize(128, 128);
        this.t.mirror = true;
        this.setRotation(this.t, 0.0f, 0.0f, 0.0f);
        (this.u = new ModelRenderer((ModelBase) this, 0, 14))
            .addBox(0.0f, -1.0f, 0.0f, 3, 1, 4);
        this.u.setRotationPoint(-3.0f, 20.0f, -17.0f);
        this.u.setTextureSize(128, 128);
        this.u.mirror = true;
        this.setRotation(this.u, 0.0f, 0.0f, 0.7853982f);
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
        this.q.render(f5);
        this.r.render(f5);
        this.s.render(f5);
        this.t.render(f5);
        this.u.render(f5);
    }

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
        this.q.render(f5);
        this.r.render(f5);
        this.s.render(f5);
        this.t.render(f5);
        this.u.render(f5);
    }

    private void
    setRotation(final ModelRenderer model, final float x, final float y, final float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
}
