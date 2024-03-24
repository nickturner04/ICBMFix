package icbm.gangshao.muoxing;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelGunTurret extends ModelBase {
    ModelRenderer BasePlate;
    ModelRenderer neck;
    ModelRenderer neck2;
    ModelRenderer neck3;
    ModelRenderer neck4;
    ModelRenderer Base;
    ModelRenderer LeftSide;
    ModelRenderer LeftSideB;
    ModelRenderer RightSide;
    ModelRenderer RightSideB;
    ModelRenderer CannonBackStoper;
    ModelRenderer CannonBarrelBrace;
    ModelRenderer CannonS1;
    ModelRenderer CannonS2;
    ModelRenderer CannonS3;
    ModelRenderer CannonS4;
    ModelRenderer CannonBarrel;
    ModelRenderer CannonBrace;
    ModelRenderer CannonLBeam;
    ModelRenderer CannonRBeam;
    ModelRenderer CannonBody;
    ModelRenderer AmmoCase;
    ModelRenderer AmmoB1;
    ModelRenderer AmmoB2;
    ModelRenderer AmmoB3;
    ModelRenderer AmmoB3B;
    ModelRenderer AmmoB2B;
    ModelRenderer AmmoB1B;
    ModelRenderer Shell1Tip;
    ModelRenderer Shell1Body;
    ModelRenderer Shell1Tip2;
    ModelRenderer Shell1Body2;
    ModelRenderer Shell1Tip3;
    ModelRenderer Shell1Body3;
    ModelRenderer Shell1Tip4;
    ModelRenderer Shell1Body4;
    ModelRenderer Shell1Tip5;
    ModelRenderer Shell1Body5;
    ModelRenderer AmmoCaseT;

    public ModelGunTurret() {
        super.textureWidth = 128;
        super.textureHeight = 128;
        (this.BasePlate = new ModelRenderer((ModelBase) this, 0, 113))
            .addBox(-7.5f, 3.0f, -6.5f, 15, 2, 13);
        this.BasePlate.setRotationPoint(0.0f, 20.0f, 0.0f);
        this.BasePlate.setTextureSize(128, 128);
        this.BasePlate.mirror = true;
        this.setRotation(this.BasePlate, 0.0f, 0.0f, 0.0f);
        (this.neck = new ModelRenderer((ModelBase) this, 19, 0))
            .addBox(-4.0f, 0.0f, -4.0f, 8, 5, 8);
        this.neck.setRotationPoint(0.0f, 19.0f, 0.0f);
        this.neck.setTextureSize(128, 128);
        this.neck.mirror = true;
        this.setRotation(this.neck, 0.0f, 0.3926991f, 0.0f);
        (this.neck2 = new ModelRenderer((ModelBase) this, 19, 0))
            .addBox(-4.0f, 0.0f, -4.0f, 8, 5, 8);
        this.neck2.setRotationPoint(0.0f, 19.0f, 0.0f);
        this.neck2.setTextureSize(128, 128);
        this.neck2.mirror = true;
        this.setRotation(this.neck2, 0.0f, 0.0f, 0.0f);
        (this.neck3 = new ModelRenderer((ModelBase) this, 19, 0))
            .addBox(-4.0f, 0.0f, -4.0f, 8, 5, 8);
        this.neck3.setRotationPoint(0.0f, 19.0f, 0.0f);
        this.neck3.setTextureSize(128, 128);
        this.neck3.mirror = true;
        this.setRotation(this.neck3, 0.0f, 1.178097f, 0.0f);
        (this.neck4 = new ModelRenderer((ModelBase) this, 19, 0))
            .addBox(-4.0f, 0.0f, -4.0f, 8, 5, 8);
        this.neck4.setRotationPoint(0.0f, 19.0f, 0.0f);
        this.neck4.setTextureSize(128, 128);
        this.neck4.mirror = true;
        this.setRotation(this.neck4, 0.0f, 0.7853982f, 0.0f);
        (this.Base = new ModelRenderer((ModelBase) this, 0, 97))
            .addBox(-7.5f, -2.0f, -6.5f, 15, 2, 13);
        this.Base.setRotationPoint(0.0f, 19.0f, 0.0f);
        this.Base.setTextureSize(128, 128);
        this.Base.mirror = true;
        this.setRotation(this.Base, 0.0f, 0.0f, 0.0f);
        (this.LeftSide = new ModelRenderer((ModelBase) this, 0, 59))
            .addBox(5.5f, -13.0f, -5.5f, 2, 2, 11);
        this.LeftSide.setRotationPoint(0.0f, 19.0f, 0.0f);
        this.LeftSide.setTextureSize(128, 128);
        this.LeftSide.mirror = true;
        this.setRotation(this.LeftSide, 0.0f, 0.0f, 0.0f);
        (this.LeftSideB = new ModelRenderer((ModelBase) this, 0, 73))
            .addBox(5.5f, -11.0f, -6.5f, 2, 9, 13);
        this.LeftSideB.setRotationPoint(0.0f, 19.0f, 0.0f);
        this.LeftSideB.setTextureSize(128, 128);
        this.LeftSideB.mirror = true;
        this.setRotation(this.LeftSideB, 0.0f, 0.0f, 0.0f);
        (this.RightSide = new ModelRenderer((ModelBase) this, 26, 59))
            .addBox(-5.5f, -13.0f, -5.5f, 2, 2, 11);
        this.RightSide.setRotationPoint(0.0f, 19.0f, 0.0f);
        this.RightSide.setTextureSize(128, 128);
        this.RightSide.mirror = true;
        this.setRotation(this.RightSide, 0.0f, 0.0f, 0.0f);
        (this.RightSideB = new ModelRenderer((ModelBase) this, 31, 73))
            .addBox(-5.5f, -11.0f, -6.5f, 2, 9, 13);
        this.RightSideB.setRotationPoint(0.0f, 19.0f, 0.0f);
        this.RightSideB.setTextureSize(128, 128);
        this.RightSideB.mirror = true;
        this.setRotation(this.RightSideB, 0.0f, 0.0f, 0.0f);
        (this.CannonBackStoper = new ModelRenderer((ModelBase) this, 0, 22))
            .addBox(-2.5f, -3.0f, 7.5f, 7, 7, 4);
        this.CannonBackStoper.setRotationPoint(0.0f, 10.0f, 0.0f);
        this.CannonBackStoper.setTextureSize(128, 128);
        this.CannonBackStoper.mirror = true;
        this.setRotation(this.CannonBackStoper, -0.1745329f, 0.0f, 0.0f);
        (this.CannonBarrelBrace = new ModelRenderer((ModelBase) this, 63, 61))
            .addBox(0.0f, 2.5f, -16.5f, 2, 2, 13);
        this.CannonBarrelBrace.setRotationPoint(0.0f, 10.0f, 0.0f);
        this.CannonBarrelBrace.setTextureSize(128, 128);
        this.CannonBarrelBrace.mirror = true;
        this.setRotation(this.CannonBarrelBrace, -0.1745329f, 0.0f, 0.0f);
        (this.CannonS1 = new ModelRenderer((ModelBase) this, 101, 44))
            .addBox(2.5f, -2.5f, -19.5f, 2, 5, 3);
        this.CannonS1.setRotationPoint(0.0f, 10.0f, 0.0f);
        this.CannonS1.setTextureSize(128, 128);
        this.CannonS1.mirror = true;
        this.setRotation(this.CannonS1, -0.1745329f, 0.0f, 0.0f);
        (this.CannonS2 = new ModelRenderer((ModelBase) this, 92, 53))
            .addBox(-1.5f, 1.5f, -19.5f, 5, 2, 3);
        this.CannonS2.setRotationPoint(0.0f, 10.0f, 0.0f);
        this.CannonS2.setTextureSize(128, 128);
        this.CannonS2.mirror = true;
        this.setRotation(this.CannonS2, -0.1745329f, 0.0f, 0.0f);
        (this.CannonS3 = new ModelRenderer((ModelBase) this, 89, 44))
            .addBox(-2.5f, -2.5f, -19.5f, 2, 5, 3);
        this.CannonS3.setRotationPoint(0.0f, 10.0f, 0.0f);
        this.CannonS3.setTextureSize(128, 128);
        this.CannonS3.mirror = true;
        this.setRotation(this.CannonS3, -0.1745329f, 0.0f, 0.0f);
        (this.CannonS4 = new ModelRenderer((ModelBase) this, 92, 38))
            .addBox(-1.5f, -3.5f, -19.5f, 5, 2, 3);
        this.CannonS4.setRotationPoint(0.0f, 10.0f, 0.0f);
        this.CannonS4.setTextureSize(128, 128);
        this.CannonS4.mirror = true;
        this.setRotation(this.CannonS4, -0.1745329f, 0.0f, 0.0f);
        (this.CannonBarrel = new ModelRenderer((ModelBase) this, 53, 45))
            .addBox(-1.5f, -2.5f, -16.5f, 5, 5, 8);
        this.CannonBarrel.setRotationPoint(0.0f, 10.0f, 0.0f);
        this.CannonBarrel.setTextureSize(128, 128);
        this.CannonBarrel.mirror = true;
        this.setRotation(this.CannonBarrel, -0.1745329f, 0.0f, 0.0f);
        (this.CannonBrace = new ModelRenderer((ModelBase) this, 43, 34))
            .addBox(-2.0f, -3.0f, -8.5f, 6, 6, 4);
        this.CannonBrace.setRotationPoint(0.0f, 10.0f, 0.0f);
        this.CannonBrace.setTextureSize(128, 128);
        this.CannonBrace.mirror = true;
        this.setRotation(this.CannonBrace, -0.1745329f, 0.0f, 0.0f);
        (this.CannonLBeam = new ModelRenderer((ModelBase) this, 66, 34))
            .addBox(5.0f, -2.0f, -2.0f, 2, 4, 4);
        this.CannonLBeam.setRotationPoint(0.0f, 10.0f, 0.0f);
        this.CannonLBeam.setTextureSize(128, 128);
        this.CannonLBeam.mirror = true;
        this.setRotation(this.CannonLBeam, -0.1745329f, 0.0f, 0.0f);
        (this.CannonRBeam = new ModelRenderer((ModelBase) this, 66, 34))
            .addBox(-4.0f, -2.0f, -2.0f, 2, 4, 4);
        this.CannonRBeam.setRotationPoint(0.0f, 10.0f, 0.0f);
        this.CannonRBeam.setTextureSize(128, 128);
        this.CannonRBeam.mirror = true;
        this.setRotation(this.CannonRBeam, -0.1745329f, 0.0f, 0.0f);
        (this.CannonBody = new ModelRenderer((ModelBase) this, 0, 34))
            .addBox(-3.0f, -4.0f, -4.5f, 8, 9, 12);
        this.CannonBody.setRotationPoint(0.0f, 10.0f, 0.0f);
        this.CannonBody.setTextureSize(128, 128);
        this.CannonBody.mirror = true;
        this.setRotation(this.CannonBody, -0.1745329f, 0.0f, 0.0f);
        (this.AmmoCase = new ModelRenderer((ModelBase) this, 91, 105))
            .addBox(-10.5f, -5.0f, -6.5f, 5, 9, 14);
        this.AmmoCase.setRotationPoint(0.0f, 20.0f, 0.0f);
        this.AmmoCase.setTextureSize(128, 128);
        this.AmmoCase.mirror = true;
        this.setRotation(this.AmmoCase, 0.0f, 0.0f, 0.0f);
        (this.AmmoB1 = new ModelRenderer((ModelBase) this, 114, 98))
            .addBox(-0.5f, -7.0f, -3.5f, 3, 5, 1);
        this.AmmoB1.setRotationPoint(0.0f, 9.0f, 0.0f);
        this.AmmoB1.setTextureSize(128, 128);
        this.AmmoB1.mirror = true;
        this.setRotation(this.AmmoB1, -0.1745329f, 0.0f, 0.0f);
        (this.AmmoB2 = new ModelRenderer((ModelBase) this, 96, 93))
            .addBox(-8.5f, -9.0f, -3.5f, 10, 3, 1);
        this.AmmoB2.setRotationPoint(0.0f, 9.0f, 0.0f);
        this.AmmoB2.setTextureSize(128, 128);
        this.AmmoB2.mirror = true;
        this.setRotation(this.AmmoB2, -0.1745329f, 0.0f, 0.0f);
        (this.AmmoB3 = new ModelRenderer((ModelBase) this, 91, 98))
            .addBox(-9.5f, -7.0f, -3.5f, 3, 5, 1);
        this.AmmoB3.setRotationPoint(0.0f, 9.0f, 0.0f);
        this.AmmoB3.setTextureSize(128, 128);
        this.AmmoB3.mirror = true;
        this.setRotation(this.AmmoB3, -0.1745329f, 0.0f, 0.0f);
        (this.AmmoB3B = new ModelRenderer((ModelBase) this, 91, 98))
            .addBox(-9.5f, -7.0f, 6.5f, 3, 5, 1);
        this.AmmoB3B.setRotationPoint(0.0f, 9.0f, 0.0f);
        this.AmmoB3B.setTextureSize(128, 128);
        this.AmmoB3B.mirror = true;
        this.setRotation(this.AmmoB3B, -0.1745329f, 0.0f, 0.0f);
        (this.AmmoB2B = new ModelRenderer((ModelBase) this, 96, 93))
            .addBox(-8.5f, -9.0f, 6.5f, 10, 3, 1);
        this.AmmoB2B.setRotationPoint(0.0f, 9.0f, 0.0f);
        this.AmmoB2B.setTextureSize(128, 128);
        this.AmmoB2B.mirror = true;
        this.setRotation(this.AmmoB2B, -0.1745329f, 0.0f, 0.0f);
        (this.AmmoB1B = new ModelRenderer((ModelBase) this, 114, 98))
            .addBox(-0.5f, -7.0f, 6.5f, 3, 5, 1);
        this.AmmoB1B.setRotationPoint(0.0f, 9.0f, 0.0f);
        this.AmmoB1B.setTextureSize(128, 128);
        this.AmmoB1B.mirror = true;
        this.setRotation(this.AmmoB1B, -0.1745329f, 0.0f, 0.0f);
        (this.Shell1Tip = new ModelRenderer((ModelBase) this, 112, 82))
            .addBox(-9.0f, -6.5f, -2.5f, 2, 2, 2);
        this.Shell1Tip.setRotationPoint(0.0f, 10.0f, 0.0f);
        this.Shell1Tip.setTextureSize(128, 128);
        this.Shell1Tip.mirror = true;
        this.setRotation(this.Shell1Tip, -0.1745329f, 0.0f, 0.0f);
        (this.Shell1Body = new ModelRenderer((ModelBase) this, 91, 82))
            .addBox(-9.5f, -7.0f, -0.5f, 3, 3, 7);
        this.Shell1Body.setRotationPoint(0.0f, 10.0f, 0.0f);
        this.Shell1Body.setTextureSize(128, 128);
        this.Shell1Body.mirror = true;
        this.setRotation(this.Shell1Body, -0.1745329f, 0.0f, 0.0f);
        (this.Shell1Tip2 = new ModelRenderer((ModelBase) this, 112, 82))
            .addBox(-8.0f, -9.5f, -2.5f, 2, 2, 2);
        this.Shell1Tip2.setRotationPoint(0.0f, 10.0f, 0.0f);
        this.Shell1Tip2.setTextureSize(128, 128);
        this.Shell1Tip2.mirror = true;
        this.setRotation(this.Shell1Tip2, -0.1745329f, 0.0f, 0.0f);
        (this.Shell1Body2 = new ModelRenderer((ModelBase) this, 91, 82))
            .addBox(-8.5f, -10.0f, -0.5f, 3, 3, 7);
        this.Shell1Body2.setRotationPoint(0.0f, 10.0f, 0.0f);
        this.Shell1Body2.setTextureSize(128, 128);
        this.Shell1Body2.mirror = true;
        this.setRotation(this.Shell1Body2, -0.1745329f, 0.0f, 0.0f);
        (this.Shell1Tip3 = new ModelRenderer((ModelBase) this, 112, 82))
            .addBox(-4.5f, -9.5f, -2.5f, 2, 2, 2);
        this.Shell1Tip3.setRotationPoint(0.0f, 10.0f, 0.0f);
        this.Shell1Tip3.setTextureSize(128, 128);
        this.Shell1Tip3.mirror = true;
        this.setRotation(this.Shell1Tip3, -0.1745329f, 0.0f, 0.0f);
        (this.Shell1Body3 = new ModelRenderer((ModelBase) this, 91, 82))
            .addBox(-5.0f, -10.0f, -0.5f, 3, 3, 7);
        this.Shell1Body3.setRotationPoint(0.0f, 10.0f, 0.0f);
        this.Shell1Body3.setTextureSize(128, 128);
        this.Shell1Body3.mirror = true;
        this.setRotation(this.Shell1Body3, -0.1745329f, 0.0f, 0.0f);
        (this.Shell1Tip4 = new ModelRenderer((ModelBase) this, 112, 82))
            .addBox(-1.0f, -9.5f, -2.5f, 2, 2, 2);
        this.Shell1Tip4.setRotationPoint(0.0f, 10.0f, 0.0f);
        this.Shell1Tip4.setTextureSize(128, 128);
        this.Shell1Tip4.mirror = true;
        this.setRotation(this.Shell1Tip4, -0.1745329f, 0.0f, 0.0f);
        (this.Shell1Body4 = new ModelRenderer((ModelBase) this, 91, 82))
            .addBox(-1.5f, -10.0f, -0.5f, 3, 3, 7);
        this.Shell1Body4.setRotationPoint(0.0f, 10.0f, 0.0f);
        this.Shell1Body4.setTextureSize(128, 128);
        this.Shell1Body4.mirror = true;
        this.setRotation(this.Shell1Body4, -0.1745329f, 0.0f, 0.0f);
        (this.Shell1Tip5 = new ModelRenderer((ModelBase) this, 112, 82))
            .addBox(0.0f, -6.5f, -2.5f, 2, 2, 2);
        this.Shell1Tip5.setRotationPoint(0.0f, 10.0f, 0.0f);
        this.Shell1Tip5.setTextureSize(128, 128);
        this.Shell1Tip5.mirror = true;
        this.setRotation(this.Shell1Tip5, -0.1745329f, 0.0f, 0.0f);
        (this.Shell1Body5 = new ModelRenderer((ModelBase) this, 91, 82))
            .addBox(-0.5f, -7.0f, -0.5f, 3, 3, 7);
        this.Shell1Body5.setRotationPoint(0.0f, 10.0f, 0.0f);
        this.Shell1Body5.setTextureSize(128, 128);
        this.Shell1Body5.mirror = true;
        this.setRotation(this.Shell1Body5, -0.1745329f, 0.0f, 0.0f);
        (this.AmmoCaseT = new ModelRenderer((ModelBase) this, 55, 0))
            .addBox(-10.5f, -2.0f, -4.5f, 5, 9, 13);
        this.AmmoCaseT.setRotationPoint(0.0f, 9.0f, 0.0f);
        this.AmmoCaseT.setTextureSize(128, 128);
        this.AmmoCaseT.mirror = true;
        this.setRotation(this.AmmoCaseT, -0.1745329f, 0.0f, 0.0f);
    }

    public void render(final float f5) {
        this.BasePlate.render(f5);
    }

    public void renderBody(final float f5) {
        this.neck.render(f5);
        this.neck2.render(f5);
        this.neck3.render(f5);
        this.neck4.render(f5);
        this.Base.render(f5);
        this.LeftSide.render(f5);
        this.LeftSideB.render(f5);
        this.RightSide.render(f5);
        this.RightSideB.render(f5);
        this.AmmoCase.render(f5);
    }

    public void renderCannon(final float f5) {
        this.CannonBackStoper.render(f5);
        this.CannonBarrelBrace.render(f5);
        this.CannonS1.render(f5);
        this.CannonS2.render(f5);
        this.CannonS3.render(f5);
        this.CannonS4.render(f5);
        this.CannonBarrel.render(f5);
        this.CannonBrace.render(f5);
        this.CannonLBeam.render(f5);
        this.CannonRBeam.render(f5);
        this.CannonBody.render(f5);
        this.AmmoB1.render(f5);
        this.AmmoB2.render(f5);
        this.AmmoB3.render(f5);
        this.AmmoB3B.render(f5);
        this.AmmoB2B.render(f5);
        this.AmmoB1B.render(f5);
        this.Shell1Tip.render(f5);
        this.Shell1Body.render(f5);
        this.Shell1Tip2.render(f5);
        this.Shell1Body2.render(f5);
        this.Shell1Tip3.render(f5);
        this.Shell1Body3.render(f5);
        this.Shell1Tip4.render(f5);
        this.Shell1Body4.render(f5);
        this.Shell1Tip5.render(f5);
        this.Shell1Body5.render(f5);
        this.AmmoCaseT.render(f5);
    }

    private void
    setRotation(final ModelRenderer model, final float x, final float y, final float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
}
