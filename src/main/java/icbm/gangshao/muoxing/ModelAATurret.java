package icbm.gangshao.muoxing;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelAATurret extends ModelBase {
    ModelRenderer rot1;
    ModelRenderer radarNeck;
    ModelRenderer radarBase;
    ModelRenderer radarBack;
    ModelRenderer radarRight;
    ModelRenderer radarLeft;
    ModelRenderer baseEdge4;
    ModelRenderer base;
    ModelRenderer baseEdge3;
    ModelRenderer baseEdge1;
    ModelRenderer baseEdge2;
    ModelRenderer body;
    ModelRenderer bodyFace;
    ModelRenderer bodyTop;
    ModelRenderer bodyEdge;
    ModelRenderer bodyArmRight;
    ModelRenderer bodyBack;
    ModelRenderer bodyBubble;
    ModelRenderer bodyArmLeft;
    ModelRenderer cannonRight;
    ModelRenderer cannonFaceRight;
    ModelRenderer cannonBarrelTopRight;
    ModelRenderer cannonBarrelBotRight;
    ModelRenderer cannonCapTopRight;
    ModelRenderer cannonCapBotRight;
    ModelRenderer cannonInFaceRight;
    ModelRenderer cannonBarrelCouple;
    ModelRenderer cannonBarrelNeck;
    ModelRenderer cannonCapBotLeft;
    ModelRenderer cannonCapTopLeft;
    ModelRenderer cannonFaceLeft;
    ModelRenderer cannonLeft;
    ModelRenderer cannonInFaceLeft;
    ModelRenderer cannonBarrelNeckLeft;
    ModelRenderer cannonBarrelBotLeft;
    ModelRenderer cannonBarrelCoupleLeft;
    ModelRenderer cannonBarrelTopLeft;

    public ModelAATurret() {
        super.textureWidth = 128;
        super.textureHeight = 128;
        (this.rot1 = new ModelRenderer((ModelBase) this, 0, 0))
            .addBox(-8.0f, 0.0f, -8.0f, 16, 2, 18);
        this.rot1.setRotationPoint(0.0f, 19.0f, 0.0f);
        this.rot1.setTextureSize(128, 128);
        this.rot1.mirror = true;
        this.setRotation(this.rot1, 0.0f, 0.0f, 0.0f);
        (this.radarNeck = new ModelRenderer((ModelBase) this, 69, 0))
            .addBox(-1.5f, -1.0f, -0.5f, 3, 3, 2);
        this.radarNeck.setRotationPoint(0.0f, -3.0f, 2.0f);
        this.radarNeck.setTextureSize(128, 128);
        this.radarNeck.mirror = true;
        this.setRotation(this.radarNeck, 0.0f, 0.0f, 0.0f);
        (this.radarBase = new ModelRenderer((ModelBase) this, 81, 0))
            .addBox(-3.0f, -1.0f, -3.0f, 6, 1, 6);
        this.radarBase.setRotationPoint(0.0f, 0.0f, 2.0f);
        this.radarBase.setTextureSize(128, 128);
        this.radarBase.mirror = true;
        this.setRotation(this.radarBase, 0.0f, 0.0f, 0.0f);
        (this.radarBack = new ModelRenderer((ModelBase) this, 68, 6))
            .addBox(-2.5f, -1.0f, -0.5f, 5, 3, 1);
        this.radarBack.setRotationPoint(0.0f, -4.0f, 2.0f);
        this.radarBack.setTextureSize(128, 128);
        this.radarBack.mirror = true;
        this.setRotation(this.radarBack, 0.0f, 0.0f, 0.0f);
        (this.radarRight = new ModelRenderer((ModelBase) this, 68, 6))
            .addBox(0.5f, -1.0f, 1.5f, 5, 3, 1);
        this.radarRight.setRotationPoint(0.0f, -4.0f, 2.0f);
        this.radarRight.setTextureSize(128, 128);
        this.radarRight.mirror = true;
        this.setRotation(this.radarRight, 0.0f, 0.7853982f, 0.0f);
        this.radarRight.mirror = false;
        (this.radarLeft = new ModelRenderer((ModelBase) this, 68, 6))
            .addBox(-5.5f, -1.0f, 1.5f, 5, 3, 1);
        this.radarLeft.setRotationPoint(0.0f, -4.0f, 2.0f);
        this.radarLeft.setTextureSize(128, 128);
        this.radarLeft.mirror = true;
        this.setRotation(this.radarLeft, 0.0f, -0.7853982f, 0.0f);
        (this.baseEdge4 = new ModelRenderer((ModelBase) this, 73, 70))
            .addBox(-7.0f, 0.0f, -11.0f, 14, 3, 2);
        this.baseEdge4.setRotationPoint(0.0f, 21.0f, 0.0f);
        this.baseEdge4.setTextureSize(128, 128);
        this.baseEdge4.mirror = true;
        this.setRotation(this.baseEdge4, 0.0f, 3.141593f, 0.0f);
        (this.base = new ModelRenderer((ModelBase) this, 0, 70))
            .addBox(-9.0f, 0.0f, -9.0f, 18, 3, 18);
        this.base.setRotationPoint(0.0f, 21.0f, 0.0f);
        this.base.setTextureSize(128, 128);
        this.base.mirror = true;
        this.setRotation(this.base, 0.0f, 0.0f, 0.0f);
        (this.baseEdge3 = new ModelRenderer((ModelBase) this, 73, 70))
            .addBox(-7.0f, 0.0f, -11.0f, 14, 3, 2);
        this.baseEdge3.setRotationPoint(0.0f, 21.0f, 0.0f);
        this.baseEdge3.setTextureSize(128, 128);
        this.baseEdge3.mirror = true;
        this.setRotation(this.baseEdge3, 0.0f, -1.570796f, 0.0f);
        (this.baseEdge1 = new ModelRenderer((ModelBase) this, 73, 70))
            .addBox(-7.0f, 0.0f, -11.0f, 14, 3, 2);
        this.baseEdge1.setRotationPoint(0.0f, 21.0f, 0.0f);
        this.baseEdge1.setTextureSize(128, 128);
        this.baseEdge1.mirror = true;
        this.setRotation(this.baseEdge1, 0.0f, 0.0f, 0.0f);
        (this.baseEdge2 = new ModelRenderer((ModelBase) this, 73, 70))
            .addBox(-7.0f, 0.0f, -11.0f, 14, 3, 2);
        this.baseEdge2.setRotationPoint(0.0f, 21.0f, 0.0f);
        this.baseEdge2.setTextureSize(128, 128);
        this.baseEdge2.mirror = true;
        this.setRotation(this.baseEdge2, 0.0f, 1.570796f, 0.0f);
        (this.body = new ModelRenderer((ModelBase) this, 0, 92))
            .addBox(-7.0f, 0.0f, -6.0f, 14, 17, 16);
        this.body.setRotationPoint(0.0f, 2.0f, 0.0f);
        this.body.setTextureSize(128, 128);
        this.body.mirror = true;
        this.setRotation(this.body, 0.0f, 0.0f, 0.0f);
        (this.bodyFace = new ModelRenderer((ModelBase) this, 61, 106))
            .addBox(-5.0f, 0.0f, -8.0f, 10, 17, 2);
        this.bodyFace.setRotationPoint(0.0f, 2.0f, 0.0f);
        this.bodyFace.setTextureSize(128, 128);
        this.bodyFace.mirror = true;
        this.setRotation(this.bodyFace, 0.0f, 0.0f, 0.0f);
        (this.bodyTop = new ModelRenderer((ModelBase) this, 73, 85))
            .addBox(-5.0f, -2.0f, -7.0f, 10, 2, 16);
        this.bodyTop.setRotationPoint(0.0f, 2.0f, 0.0f);
        this.bodyTop.setTextureSize(128, 128);
        this.bodyTop.mirror = true;
        this.setRotation(this.bodyTop, 0.0f, 0.0f, 0.0f);
        (this.bodyEdge = new ModelRenderer((ModelBase) this, 65, 126))
            .addBox(-4.5f, -1.0f, -7.0f, 9, 1, 1);
        this.bodyEdge.setRotationPoint(0.0f, 2.0f, 0.0f);
        this.bodyEdge.setTextureSize(128, 128);
        this.bodyEdge.mirror = true;
        this.setRotation(this.bodyEdge, 0.0f, 0.0f, 0.0f);
        (this.bodyArmRight = new ModelRenderer((ModelBase) this, 86, 104))
            .addBox(-1.0f, -3.5f, -3.5f, 2, 16, 7);
        this.bodyArmRight.setRotationPoint(-8.0f, 6.0f, 1.0f);
        this.bodyArmRight.setTextureSize(128, 128);
        this.bodyArmRight.mirror = true;
        this.setRotation(this.bodyArmRight, 0.0f, 0.0f, 0.0f);
        (this.bodyBack = new ModelRenderer((ModelBase) this, 61, 104))
            .addBox(-5.0f, 0.0f, 10.0f, 10, 19, 2);
        this.bodyBack.setRotationPoint(0.0f, 2.0f, 0.0f);
        this.bodyBack.setTextureSize(128, 128);
        this.bodyBack.mirror = true;
        this.setRotation(this.bodyBack, 0.0f, 0.0f, 0.0f);
        (this.bodyBubble = new ModelRenderer((ModelBase) this, 105, 106))
            .addBox(-4.0f, 3.0f, -10.0f, 8, 8, 2);
        this.bodyBubble.setRotationPoint(0.0f, 2.0f, 0.0f);
        this.bodyBubble.setTextureSize(128, 128);
        this.bodyBubble.mirror = true;
        this.setRotation(this.bodyBubble, 0.0f, 0.0f, 0.0f);
        (this.bodyArmLeft = new ModelRenderer((ModelBase) this, 86, 104))
            .addBox(-1.0f, -3.5f, -3.5f, 2, 16, 7);
        this.bodyArmLeft.setRotationPoint(8.0f, 6.0f, 1.0f);
        this.bodyArmLeft.setTextureSize(128, 128);
        this.bodyArmLeft.mirror = true;
        this.setRotation(this.bodyArmLeft, 0.0f, 0.0f, 0.0f);
        (this.cannonRight = new ModelRenderer((ModelBase) this, 69, 11))
            .addBox(-4.0f, -5.5f, -10.5f, 4, 11, 20);
        this.cannonRight.setRotationPoint(-10.0f, 7.0f, 1.0f);
        this.cannonRight.setTextureSize(128, 128);
        this.cannonRight.mirror = true;
        this.setRotation(this.cannonRight, -0.6108652f, 0.0f, 0.0f);
        (this.cannonFaceRight = new ModelRenderer((ModelBase) this, 0, 43))
            .addBox(-5.0f, -4.0f, -9.5f, 1, 8, 18);
        this.cannonFaceRight.setRotationPoint(-5.0f, 7.0f, 1.0f);
        this.cannonFaceRight.setTextureSize(128, 128);
        this.cannonFaceRight.mirror = true;
        this.setRotation(this.cannonFaceRight, -0.6108652f, 0.0f, 0.0f);
        (this.cannonBarrelTopRight = new ModelRenderer((ModelBase) this, 42, 52))
            .addBox(-3.0f, -4.0f, -25.5f, 2, 2, 15);
        this.cannonBarrelTopRight.setRotationPoint(-10.0f, 7.0f, 1.0f);
        this.cannonBarrelTopRight.setTextureSize(128, 128);
        this.cannonBarrelTopRight.mirror = true;
        this.setRotation(this.cannonBarrelTopRight, -0.6108652f, 0.0f, 0.0f);
        (this.cannonBarrelBotRight = new ModelRenderer((ModelBase) this, 42, 52))
            .addBox(-3.0f, 1.0f, -25.5f, 2, 2, 15);
        this.cannonBarrelBotRight.setRotationPoint(-10.0f, 7.0f, 1.0f);
        this.cannonBarrelBotRight.setTextureSize(128, 128);
        this.cannonBarrelBotRight.mirror = true;
        this.setRotation(this.cannonBarrelBotRight, -0.6108652f, 0.0f, 0.0f);
        (this.cannonCapTopRight = new ModelRenderer((ModelBase) this, 73, 77))
            .addBox(-3.5f, -4.5f, -29.5f, 3, 3, 4);
        this.cannonCapTopRight.setRotationPoint(-10.0f, 7.0f, 1.0f);
        this.cannonCapTopRight.setTextureSize(128, 128);
        this.cannonCapTopRight.mirror = true;
        this.setRotation(this.cannonCapTopRight, -0.6108652f, 0.0f, 0.0f);
        (this.cannonCapBotRight = new ModelRenderer((ModelBase) this, 73, 77))
            .addBox(-3.5f, 0.5f, -29.5f, 3, 3, 4);
        this.cannonCapBotRight.setRotationPoint(-10.0f, 7.0f, 1.0f);
        this.cannonCapBotRight.setTextureSize(128, 128);
        this.cannonCapBotRight.mirror = true;
        this.setRotation(this.cannonCapBotRight, -0.6108652f, 0.0f, 0.0f);
        (this.cannonInFaceRight = new ModelRenderer((ModelBase) this, 77, 43))
            .addBox(-5.0f, -4.0f, -9.5f, 1, 8, 18);
        this.cannonInFaceRight.setRotationPoint(-10.0f, 7.0f, 1.0f);
        this.cannonInFaceRight.setTextureSize(128, 128);
        this.cannonInFaceRight.mirror = true;
        this.setRotation(this.cannonInFaceRight, -0.6108652f, 0.0f, 0.0f);
        (this.cannonBarrelCouple = new ModelRenderer((ModelBase) this, 89, 77))
            .addBox(-2.5f, -4.0f, -14.5f, 1, 4, 3);
        this.cannonBarrelCouple.setRotationPoint(-10.0f, 9.0f, 1.0f);
        this.cannonBarrelCouple.setTextureSize(128, 128);
        this.cannonBarrelCouple.mirror = true;
        this.setRotation(this.cannonBarrelCouple, -0.6108652f, 0.0f, 0.0f);
        (this.cannonBarrelNeck = new ModelRenderer((ModelBase) this, 106, 70))
            .addBox(-3.5f, -6.0f, -13.3f, 3, 8, 2);
        this.cannonBarrelNeck.setRotationPoint(-10.0f, 9.0f, 1.0f);
        this.cannonBarrelNeck.setTextureSize(128, 128);
        this.cannonBarrelNeck.mirror = true;
        this.setRotation(this.cannonBarrelNeck, -0.6108652f, 0.0f, 0.0f);
        (this.cannonCapBotLeft = new ModelRenderer((ModelBase) this, 73, 77))
            .addBox(0.5f, 0.5f, -29.5f, 3, 3, 4);
        this.cannonCapBotLeft.setRotationPoint(10.0f, 7.0f, 1.0f);
        this.cannonCapBotLeft.setTextureSize(128, 128);
        this.cannonCapBotLeft.mirror = true;
        this.setRotation(this.cannonCapBotLeft, -0.6108652f, 0.0f, 0.0f);
        (this.cannonCapTopLeft = new ModelRenderer((ModelBase) this, 73, 77))
            .addBox(0.7f, -4.5f, -29.5f, 3, 3, 4);
        this.cannonCapTopLeft.setRotationPoint(10.0f, 7.0f, 1.0f);
        this.cannonCapTopLeft.setTextureSize(128, 128);
        this.cannonCapTopLeft.mirror = true;
        this.setRotation(this.cannonCapTopLeft, -0.6108652f, 0.0f, 0.0f);
        (this.cannonFaceLeft = new ModelRenderer((ModelBase) this, 0, 43))
            .addBox(-1.0f, -4.0f, -9.5f, 1, 8, 18);
        this.cannonFaceLeft.setRotationPoint(10.0f, 7.0f, 1.0f);
        this.cannonFaceLeft.setTextureSize(128, 128);
        this.cannonFaceLeft.mirror = true;
        this.setRotation(this.cannonFaceLeft, -0.6108652f, 0.0f, 0.0f);
        (this.cannonLeft = new ModelRenderer((ModelBase) this, 69, 11))
            .addBox(0.0f, -5.5f, -10.5f, 4, 11, 20);
        this.cannonLeft.setRotationPoint(10.0f, 7.0f, 1.0f);
        this.cannonLeft.setTextureSize(128, 128);
        this.cannonLeft.mirror = true;
        this.setRotation(this.cannonLeft, -0.6108652f, 0.0f, 0.0f);
        (this.cannonInFaceLeft = new ModelRenderer((ModelBase) this, 77, 43))
            .addBox(4.0f, -4.0f, -9.5f, 1, 8, 18);
        this.cannonInFaceLeft.setRotationPoint(10.0f, 7.0f, 1.0f);
        this.cannonInFaceLeft.setTextureSize(128, 128);
        this.cannonInFaceLeft.mirror = true;
        this.setRotation(this.cannonInFaceLeft, -0.6108652f, 0.0f, 0.0f);
        (this.cannonBarrelNeckLeft = new ModelRenderer((ModelBase) this, 106, 70))
            .addBox(0.5f, -6.0f, -13.3f, 3, 8, 2);
        this.cannonBarrelNeckLeft.setRotationPoint(10.0f, 9.0f, 1.0f);
        this.cannonBarrelNeckLeft.setTextureSize(128, 128);
        this.cannonBarrelNeckLeft.mirror = true;
        this.setRotation(this.cannonBarrelNeckLeft, -0.6108652f, 0.0f, 0.0f);
        (this.cannonBarrelBotLeft = new ModelRenderer((ModelBase) this, 42, 52))
            .addBox(1.0f, 1.0f, -25.5f, 2, 2, 15);
        this.cannonBarrelBotLeft.setRotationPoint(10.0f, 7.0f, 1.0f);
        this.cannonBarrelBotLeft.setTextureSize(128, 128);
        this.cannonBarrelBotLeft.mirror = true;
        this.setRotation(this.cannonBarrelBotLeft, -0.6108652f, 0.0f, 0.0f);
        (this.cannonBarrelCoupleLeft = new ModelRenderer((ModelBase) this, 89, 77))
            .addBox(1.5f, -4.0f, -14.5f, 1, 4, 3);
        this.cannonBarrelCoupleLeft.setRotationPoint(10.0f, 9.0f, 1.0f);
        this.cannonBarrelCoupleLeft.setTextureSize(128, 128);
        this.cannonBarrelCoupleLeft.mirror = true;
        this.setRotation(this.cannonBarrelCoupleLeft, -0.6108652f, 0.0f, 0.0f);
        (this.cannonBarrelTopLeft = new ModelRenderer((ModelBase) this, 42, 52))
            .addBox(1.0f, -4.0f, -25.5f, 2, 2, 15);
        this.cannonBarrelTopLeft.setRotationPoint(10.0f, 7.0f, 1.0f);
        this.cannonBarrelTopLeft.setTextureSize(128, 128);
        this.cannonBarrelTopLeft.mirror = true;
        this.setRotation(this.cannonBarrelTopLeft, -0.6108652f, 0.0f, 0.0f);
    }

    public void render(final float f5) {
        this.rot1.render(f5);
        this.radarNeck.render(f5);
        this.radarBase.render(f5);
        this.radarBack.render(f5);
        this.radarRight.render(f5);
        this.radarLeft.render(f5);
        this.baseEdge4.render(f5);
        this.base.render(f5);
        this.baseEdge3.render(f5);
        this.baseEdge1.render(f5);
        this.baseEdge2.render(f5);
        this.body.render(f5);
        this.bodyFace.render(f5);
        this.bodyTop.render(f5);
        this.bodyEdge.render(f5);
        this.bodyArmRight.render(f5);
        this.bodyBack.render(f5);
        this.bodyBubble.render(f5);
        this.bodyArmLeft.render(f5);
        this.cannonRight.render(f5);
        this.cannonFaceRight.render(f5);
        this.cannonBarrelTopRight.render(f5);
        this.cannonBarrelBotRight.render(f5);
        this.cannonCapTopRight.render(f5);
        this.cannonCapBotRight.render(f5);
        this.cannonInFaceRight.render(f5);
        this.cannonBarrelCouple.render(f5);
        this.cannonBarrelNeck.render(f5);
        this.cannonCapBotLeft.render(f5);
        this.cannonCapTopLeft.render(f5);
        this.cannonFaceLeft.render(f5);
        this.cannonLeft.render(f5);
        this.cannonInFaceLeft.render(f5);
        this.cannonBarrelNeckLeft.render(f5);
        this.cannonBarrelBotLeft.render(f5);
        this.cannonBarrelCoupleLeft.render(f5);
        this.cannonBarrelTopLeft.render(f5);
    }

    public void renderBody(final float f5) {
        this.rot1.render(f5);
        this.baseEdge4.render(f5);
        this.base.render(f5);
        this.baseEdge3.render(f5);
        this.baseEdge1.render(f5);
        this.baseEdge2.render(f5);
        this.body.render(f5);
        this.bodyFace.render(f5);
        this.bodyTop.render(f5);
        this.bodyEdge.render(f5);
        this.bodyArmRight.render(f5);
        this.bodyBack.render(f5);
        this.bodyBubble.render(f5);
        this.bodyArmLeft.render(f5);
        this.radarBase.render(f5);
    }

    public void renderCannon(final float f5, final float rot) {
        final ModelRenderer[] cannon = { this.cannonRight,
                                         this.cannonFaceRight,
                                         this.cannonBarrelTopRight,
                                         this.cannonBarrelBotRight,
                                         this.cannonCapTopRight,
                                         this.cannonCapBotRight,
                                         this.cannonInFaceRight,
                                         this.cannonBarrelCouple,
                                         this.cannonBarrelNeck,
                                         this.cannonCapBotLeft,
                                         this.cannonCapTopLeft,
                                         this.cannonFaceLeft,
                                         this.cannonLeft,
                                         this.cannonInFaceLeft,
                                         this.cannonBarrelNeckLeft,
                                         this.cannonBarrelBotLeft,
                                         this.cannonBarrelCoupleLeft,
                                         this.cannonBarrelTopLeft };

        for (int i = 0; i < cannon.length; ++i) {
            cannon[i].rotateAngleX = rot;
        }

        for (int i = 0; i < cannon.length; ++i) {
            cannon[i].render(f5);
        }
    }

    public void renderRadar(final float f5) {
        this.radarNeck.render(f5);
        this.radarBack.render(f5);
        this.radarRight.render(f5);
        this.radarLeft.render(f5);
    }

    private void
    setRotation(final ModelRenderer model, final float x, final float y, final float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
}
