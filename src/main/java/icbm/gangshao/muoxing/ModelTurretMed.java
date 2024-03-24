package icbm.gangshao.muoxing;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

@SideOnly(Side.CLIENT)
public class ModelTurretMed extends ModelBase {
    ModelRenderer Center;
    ModelRenderer FRBrace;
    ModelRenderer BRBrace;
    ModelRenderer FLBrace;
    ModelRenderer BLBrace;
    ModelRenderer FLBrace2;
    ModelRenderer FLBrace3;
    ModelRenderer FRBrace2;
    ModelRenderer FRBrace3;
    ModelRenderer BRBrace2;
    ModelRenderer BRBrace3;
    ModelRenderer BLBrace3;
    ModelRenderer BLBrace2;
    ModelRenderer neck;
    ModelRenderer LeftFace;
    ModelRenderer RightFace;
    ModelRenderer Head;
    ModelRenderer Barrel;
    ModelRenderer Center2;
    ModelRenderer BarrelBrace;
    ModelRenderer BarrelCap;

    public ModelTurretMed() {
        super.textureWidth = 128;
        super.textureHeight = 128;
        (this.Center = new ModelRenderer((ModelBase) this, 0, 0))
            .addBox(-2.0f, 0.0f, -2.0f, 4, 5, 4);
        this.Center.setRotationPoint(0.0f, 14.0f, 0.0f);
        this.Center.setTextureSize(128, 128);
        this.Center.mirror = true;
        this.setRotation(this.Center, 0.0f, 0.7853982f, 0.0f);
        (this.FRBrace = new ModelRenderer((ModelBase) this, 0, 20))
            .addBox(-1.0f, 14.5f, -8.5f, 2, 3, 6);
        this.FRBrace.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.FRBrace.setTextureSize(128, 128);
        this.FRBrace.mirror = true;
        this.setRotation(this.FRBrace, 0.1745329f, 0.7853982f, 0.0f);
        (this.BRBrace = new ModelRenderer((ModelBase) this, 0, 20))
            .addBox(-1.0f, 14.5f, -8.5f, 2, 3, 6);
        this.BRBrace.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.BRBrace.setTextureSize(128, 128);
        this.BRBrace.mirror = true;
        this.setRotation(this.BRBrace, 0.1745329f, -3.926991f, 0.0f);
        (this.FLBrace = new ModelRenderer((ModelBase) this, 0, 20))
            .addBox(-1.0f, 14.5f, -8.5f, 2, 3, 6);
        this.FLBrace.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.FLBrace.setTextureSize(128, 128);
        this.FLBrace.mirror = true;
        this.setRotation(this.FLBrace, 0.1745329f, -0.7853982f, 0.0f);
        (this.BLBrace = new ModelRenderer((ModelBase) this, 0, 20))
            .addBox(-1.0f, 14.5f, -8.5f, 2, 3, 6);
        this.BLBrace.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.BLBrace.setTextureSize(128, 128);
        this.BLBrace.mirror = true;
        this.setRotation(this.BLBrace, 0.1745329f, -2.356194f, 0.0f);
        (this.FLBrace2 = new ModelRenderer((ModelBase) this, 0, 20))
            .addBox(-1.0f, 7.0f, -21.0f, 2, 3, 6);
        this.FLBrace2.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.FLBrace2.setTextureSize(128, 128);
        this.FLBrace2.mirror = true;
        this.setRotation(this.FLBrace2, 0.7853982f, -0.7853982f, 0.0f);
        (this.FLBrace3 = new ModelRenderer((ModelBase) this, 20, 20))
            .addBox(-2.0f, -10.0f, -24.0f, 4, 3, 4);
        this.FLBrace3.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.FLBrace3.setTextureSize(128, 128);
        this.FLBrace3.mirror = true;
        this.setRotation(this.FLBrace3, 1.570796f, -0.7853982f, 0.0f);
        (this.FRBrace2 = new ModelRenderer((ModelBase) this, 0, 20))
            .addBox(-1.0f, 7.0f, -21.0f, 2, 3, 6);
        this.FRBrace2.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.FRBrace2.setTextureSize(128, 128);
        this.FRBrace2.mirror = true;
        this.setRotation(this.FRBrace2, 0.7853982f, 0.7853982f, 0.0f);
        (this.FRBrace3 = new ModelRenderer((ModelBase) this, 20, 20))
            .addBox(-2.0f, -10.03333f, -24.0f, 4, 3, 4);
        this.FRBrace3.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.FRBrace3.setTextureSize(128, 128);
        this.FRBrace3.mirror = true;
        this.setRotation(this.FRBrace3, 1.570796f, 0.7853982f, 0.0f);
        (this.BRBrace2 = new ModelRenderer((ModelBase) this, 0, 20))
            .addBox(-1.0f, 7.0f, -21.0f, 2, 3, 6);
        this.BRBrace2.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.BRBrace2.setTextureSize(128, 128);
        this.BRBrace2.mirror = true;
        this.setRotation(this.BRBrace2, 0.7853982f, -3.926991f, 0.0f);
        (this.BRBrace3 = new ModelRenderer((ModelBase) this, 20, 20))
            .addBox(-2.0f, -10.0f, -24.0f, 4, 3, 4);
        this.BRBrace3.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.BRBrace3.setTextureSize(128, 128);
        this.BRBrace3.mirror = true;
        this.setRotation(this.BRBrace3, 1.570796f, -3.926991f, 0.0f);
        (this.BLBrace3 = new ModelRenderer((ModelBase) this, 20, 20))
            .addBox(-2.0f, -10.0f, -24.0f, 4, 3, 4);
        this.BLBrace3.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.BLBrace3.setTextureSize(128, 128);
        this.BLBrace3.mirror = true;
        this.setRotation(this.BLBrace3, 1.570796f, -2.356194f, 0.0f);
        (this.BLBrace2 = new ModelRenderer((ModelBase) this, 0, 20))
            .addBox(-1.0f, 7.0f, -21.0f, 2, 3, 6);
        this.BLBrace2.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.BLBrace2.setTextureSize(128, 128);
        this.BLBrace2.mirror = true;
        this.setRotation(this.BLBrace2, 0.7853982f, -2.356194f, 0.0f);
        (this.neck = new ModelRenderer((ModelBase) this, 19, 0))
            .addBox(-1.5f, 0.0f, -1.5f, 3, 5, 3);
        this.neck.setRotationPoint(0.0f, 11.0f, 0.0f);
        this.neck.setTextureSize(128, 128);
        this.neck.mirror = true;
        this.setRotation(this.neck, 0.0f, 0.0f, 0.0f);
        (this.LeftFace = new ModelRenderer((ModelBase) this, 17, 69))
            .addBox(3.0f, -1.0f, -1.0f, 2, 2, 6);
        this.LeftFace.setRotationPoint(0.0f, 11.0f, 0.0f);
        this.LeftFace.setTextureSize(128, 128);
        this.LeftFace.mirror = true;
        this.setRotation(this.LeftFace, 0.0f, 0.0f, 0.0f);
        (this.RightFace = new ModelRenderer((ModelBase) this, 0, 69))
            .addBox(-5.0f, -1.0f, -1.0f, 2, 2, 6);
        this.RightFace.setRotationPoint(0.0f, 11.0f, 0.0f);
        this.RightFace.setTextureSize(128, 128);
        this.RightFace.mirror = true;
        this.setRotation(this.RightFace, 0.0f, 0.0f, 0.0f);
        (this.Head = new ModelRenderer((ModelBase) this, 0, 55))
            .addBox(-3.0f, -2.0f, -2.0f, 6, 4, 8);
        this.Head.setRotationPoint(0.0f, 11.0f, 0.0f);
        this.Head.setTextureSize(128, 128);
        this.Head.mirror = true;
        this.setRotation(this.Head, 0.0f, 0.0f, 0.0f);
        (this.Barrel = new ModelRenderer((ModelBase) this, 0, 42))
            .addBox(-1.0f, -1.0f, -6.0f, 2, 2, 2);
        this.Barrel.setRotationPoint(0.0f, 11.0f, 0.0f);
        this.Barrel.setTextureSize(128, 128);
        this.Barrel.mirror = true;
        this.setRotation(this.Barrel, 0.0f, 0.0f, 0.0f);
        (this.Center2 = new ModelRenderer((ModelBase) this, 0, 0))
            .addBox(-2.0f, 0.0f, -2.0f, 4, 5, 4);
        this.Center2.setRotationPoint(0.0f, 14.0f, 0.0f);
        this.Center2.setTextureSize(128, 128);
        this.Center2.mirror = true;
        this.setRotation(this.Center2, 0.0f, 0.0f, 0.0f);
        (this.BarrelBrace = new ModelRenderer((ModelBase) this, 0, 36))
            .addBox(-2.0f, -1.5f, -4.0f, 4, 3, 2);
        this.BarrelBrace.setRotationPoint(0.0f, 11.0f, 0.0f);
        this.BarrelBrace.setTextureSize(128, 128);
        this.BarrelBrace.mirror = true;
        this.setRotation(this.BarrelBrace, 0.0f, 0.0f, 0.0f);
        (this.BarrelCap = new ModelRenderer((ModelBase) this, 0, 47))
            .addBox(-1.5f, -1.5f, -7.0f, 3, 3, 2);
        this.BarrelCap.setRotationPoint(0.0f, 11.0f, 0.0f);
        this.BarrelCap.setTextureSize(128, 128);
        this.BarrelCap.mirror = true;
        this.setRotation(this.BarrelCap, 0.0f, 0.0f, 0.0f);
    }

    public void render(
        final Entity par1Entity,
        final float f,
        final float f1,
        final float f2,
        final float f3,
        final float f4,
        final float f5
    ) {
        this.setRotationAngles(f, f1, f2, f3, f4, f5);
        this.LeftFace.render(f5);
        this.RightFace.render(f5);
        this.Head.render(f5);
        this.Barrel.render(f5);
        this.BarrelBrace.render(f5);
        this.BarrelCap.render(f5);
    }

    public void renderLegs(final float f5) {
        this.FRBrace.render(f5);
        this.BRBrace.render(f5);
        this.FLBrace.render(f5);
        this.BLBrace.render(f5);
        this.FLBrace2.render(f5);
        this.FLBrace3.render(f5);
        this.FRBrace2.render(f5);
        this.FRBrace3.render(f5);
        this.BRBrace2.render(f5);
        this.BRBrace3.render(f5);
        this.BLBrace3.render(f5);
        this.BLBrace2.render(f5);
        this.neck.render(f5);
        this.Center.render(f5);
        this.Center2.render(f5);
    }

    private void
    setRotation(final ModelRenderer model, final float x, final float y, final float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(
        final float par1,
        final float par2,
        final float par3,
        final float par4,
        final float par5,
        final float par6
    ) {
        final ModelRenderer[] Body
            = { this.Center,   this.FRBrace,  this.BRBrace,  this.FLBrace,  this.BLBrace,
                this.FLBrace2, this.FLBrace3, this.FRBrace2, this.FRBrace3, this.BRBrace2,
                this.BRBrace3, this.BLBrace3, this.BLBrace2, this.neck };

        for (int i = 0; i < Body.length; ++i) {}

        this.LeftFace.rotateAngleY = par4 / 57.295776f;
        this.LeftFace.rotateAngleX = par5 / 57.295776f;
        this.RightFace.rotateAngleY = par4 / 57.295776f;
        this.RightFace.rotateAngleX = par5 / 57.295776f;
        this.Head.rotateAngleY = par4 / 57.295776f;
        this.Head.rotateAngleX = par5 / 57.295776f;
        this.Barrel.rotateAngleY = par4 / 57.295776f;
        this.Barrel.rotateAngleX = par5 / 57.295776f;
        this.BarrelBrace.rotateAngleY = par4 / 57.295776f;
        this.BarrelBrace.rotateAngleX = par5 / 57.295776f;
        this.BarrelCap.rotateAngleY = par4 / 57.295776f;
        this.BarrelCap.rotateAngleX = par5 / 57.295776f;
    }
}
