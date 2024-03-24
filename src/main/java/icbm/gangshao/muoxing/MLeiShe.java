package icbm.gangshao.muoxing;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class MLeiShe extends ModelBase {
    ModelRenderer basePlate;
    ModelRenderer leftMount;
    ModelRenderer rightMount;
    ModelRenderer body;
    ModelRenderer bodyTop;
    ModelRenderer bodyRight;
    ModelRenderer bodyLeft;
    ModelRenderer leftBarrel;
    ModelRenderer rightBarrel;
    ModelRenderer l1;
    ModelRenderer l2;
    ModelRenderer l3;
    ModelRenderer l4;
    ModelRenderer l5;
    ModelRenderer r1;
    ModelRenderer r2;
    ModelRenderer r3;
    ModelRenderer r4;
    ModelRenderer r5;
    ModelRenderer lCap;
    ModelRenderer rCap;
    ModelRenderer Hat;
    ModelRenderer LowerHat;
    ModelRenderer BatteryPack;
    ModelRenderer MiddleWire;
    ModelRenderer BatWire;
    ModelRenderer HatWire;
    ModelRenderer Details;
    ModelRenderer RightFootStrength;
    ModelRenderer LeftFootStrength;
    ModelRenderer FrontDetail;
    ModelRenderer LeftFootBase;
    ModelRenderer RightFootBase;
    ModelRenderer lEar1;
    ModelRenderer lEar2;
    ModelRenderer rEar1;
    ModelRenderer rEar2;

    public MLeiShe() {
        super.textureWidth = 128;
        super.textureHeight = 128;
        (this.basePlate = new ModelRenderer((ModelBase) this, 0, 67))
            .addBox(-5.5f, 0.0f, -5.5f, 11, 1, 11);
        this.basePlate.setRotationPoint(0.0f, 23.0f, 0.0f);
        this.basePlate.setTextureSize(128, 128);
        this.basePlate.mirror = true;
        this.setRotation(this.basePlate, 0.0f, 0.0f, 0.0f);
        (this.leftMount = new ModelRenderer((ModelBase) this, 0, 53))
            .addBox(4.0f, -8.0f, -2.0f, 1, 8, 4);
        this.leftMount.setRotationPoint(0.0f, 23.0f, 0.0f);
        this.leftMount.setTextureSize(128, 128);
        this.leftMount.mirror = true;
        this.setRotation(this.leftMount, 0.0f, 0.0f, 0.0f);
        (this.rightMount = new ModelRenderer((ModelBase) this, 11, 53))
            .addBox(-5.0f, -8.0f, -2.0f, 1, 8, 4);
        this.rightMount.setRotationPoint(0.0f, 23.0f, 0.0f);
        this.rightMount.setTextureSize(128, 128);
        this.rightMount.mirror = true;
        this.setRotation(this.rightMount, 0.0f, 0.0f, 0.0f);
        (this.body = new ModelRenderer((ModelBase) this, 0, 37))
            .addBox(-3.0f, -2.8f, -4.0f, 6, 5, 9);
        this.body.setRotationPoint(0.0f, 17.0f, 0.0f);
        this.body.setTextureSize(128, 128);
        this.body.mirror = true;
        this.setRotation(this.body, 0.0f, 0.0f, 0.0f);
        (this.bodyTop = new ModelRenderer((ModelBase) this, 36, 51))
            .addBox(-2.0f, -4.0f, -3.0f, 4, 2, 7);
        this.bodyTop.setRotationPoint(0.0f, 17.0f, 0.0f);
        this.bodyTop.setTextureSize(128, 128);
        this.bodyTop.mirror = true;
        this.setRotation(this.bodyTop, 0.0f, 0.0f, 0.0f);
        (this.bodyRight = new ModelRenderer((ModelBase) this, 31, 37))
            .addBox(-4.0f, -2.5f, -3.0f, 1, 4, 7);
        this.bodyRight.setRotationPoint(0.0f, 17.0f, 0.0f);
        this.bodyRight.setTextureSize(128, 128);
        this.bodyRight.mirror = true;
        this.setRotation(this.bodyRight, 0.0f, 0.0f, 0.0f);
        (this.bodyLeft = new ModelRenderer((ModelBase) this, 48, 37))
            .addBox(3.0f, -2.5f, -3.0f, 1, 4, 7);
        this.bodyLeft.setRotationPoint(0.0f, 17.0f, 0.0f);
        this.bodyLeft.setTextureSize(128, 128);
        this.bodyLeft.mirror = true;
        this.setRotation(this.bodyLeft, 0.0f, 0.0f, 0.0f);
        (this.leftBarrel = new ModelRenderer((ModelBase) this, 29, 81))
            .addBox(2.0f, -1.0f, -17.0f, 1, 1, 13);
        this.leftBarrel.setRotationPoint(0.0f, 17.0f, 0.0f);
        this.leftBarrel.setTextureSize(128, 128);
        this.leftBarrel.mirror = true;
        this.setRotation(this.leftBarrel, 0.0f, 0.0f, 0.0f);
        (this.rightBarrel = new ModelRenderer((ModelBase) this, 0, 81))
            .addBox(-3.0f, -1.0f, -17.0f, 1, 1, 13);
        this.rightBarrel.setRotationPoint(0.0f, 17.0f, 0.0f);
        this.rightBarrel.setTextureSize(128, 128);
        this.rightBarrel.mirror = true;
        this.setRotation(this.rightBarrel, 0.0f, 0.0f, 0.0f);
        (this.l1 = new ModelRenderer((ModelBase) this, 1, 14))
            .addBox(0.9f, -2.0f, -6.0f, 3, 3, 3);
        this.l1.setRotationPoint(0.0f, 17.0f, 0.0f);
        this.l1.setTextureSize(128, 128);
        this.l1.mirror = true;
        this.setRotation(this.l1, 0.0f, 0.0f, 0.0f);
        (this.l2 = new ModelRenderer((ModelBase) this, 9, 28))
            .addBox(-1.5f, -1.5f, 0.0f, 3, 3, 1);
        this.l2.setRotationPoint(2.5f, 16.5f, -8.0f);
        this.l2.setTextureSize(128, 128);
        this.l2.mirror = true;
        this.setRotation(this.l2, 0.0f, 0.0f, 0.0f);
        (this.l3 = new ModelRenderer((ModelBase) this, 9, 28))
            .addBox(-1.5f, -1.5f, 0.0f, 3, 3, 1);
        this.l3.setRotationPoint(2.5f, 16.5f, -10.0f);
        this.l3.setTextureSize(128, 128);
        this.l3.mirror = true;
        this.setRotation(this.l3, 0.0f, 0.0f, 0.0f);
        (this.l4 = new ModelRenderer((ModelBase) this, 9, 28))
            .addBox(-1.5f, -1.5f, 0.0f, 3, 3, 1);
        this.l4.setRotationPoint(2.5f, 16.5f, -12.0f);
        this.l4.setTextureSize(128, 128);
        this.l4.mirror = true;
        this.setRotation(this.l4, 0.0f, 0.0f, 0.0f);
        (this.l5 = new ModelRenderer((ModelBase) this, 9, 28))
            .addBox(-1.5f, -1.5f, 0.0f, 3, 3, 1);
        this.l5.setRotationPoint(2.5f, 16.5f, -14.0f);
        this.l5.setTextureSize(128, 128);
        this.l5.mirror = true;
        this.setRotation(this.l5, 0.0f, 0.0f, 0.0f);
        (this.r1 = new ModelRenderer((ModelBase) this, 14, 14))
            .addBox(-3.9f, -2.0f, -6.0f, 3, 3, 3);
        this.r1.setRotationPoint(0.0f, 17.0f, 0.0f);
        this.r1.setTextureSize(128, 128);
        this.r1.mirror = true;
        this.setRotation(this.r1, 0.0f, 0.0f, 0.0f);
        (this.r2 = new ModelRenderer((ModelBase) this, 9, 28))
            .addBox(-1.5f, -1.5f, 0.0f, 3, 3, 1);
        this.r2.setRotationPoint(-2.5f, 16.5f, -8.0f);
        this.r2.setTextureSize(128, 128);
        this.r2.mirror = true;
        this.setRotation(this.r2, 0.0f, 0.0f, 0.0f);
        (this.r3 = new ModelRenderer((ModelBase) this, 9, 28))
            .addBox(-1.5f, -1.5f, -2.0f, 3, 3, 1);
        this.r3.setRotationPoint(-2.5f, 16.5f, -8.0f);
        this.r3.setTextureSize(128, 128);
        this.r3.mirror = true;
        this.setRotation(this.r3, 0.0f, 0.0f, 0.0f);
        (this.r4 = new ModelRenderer((ModelBase) this, 9, 28))
            .addBox(-1.5f, -1.5f, -4.0f, 3, 3, 1);
        this.r4.setRotationPoint(-2.5f, 16.5f, -8.0f);
        this.r4.setTextureSize(128, 128);
        this.r4.mirror = true;
        this.setRotation(this.r4, 0.0f, 0.0f, 0.0f);
        (this.r5 = new ModelRenderer((ModelBase) this, 9, 28))
            .addBox(-1.5f, -1.5f, -6.0f, 3, 3, 1);
        this.r5.setRotationPoint(-2.5f, 16.5f, -8.0f);
        this.r5.setTextureSize(128, 128);
        this.r5.mirror = true;
        this.setRotation(this.r5, 0.0f, 0.0f, 0.0f);
        (this.lCap = new ModelRenderer((ModelBase) this, 33, 25))
            .addBox(1.0f, -2.0f, -20.0f, 3, 3, 4);
        this.lCap.setRotationPoint(0.0f, 17.0f, 0.0f);
        this.lCap.setTextureSize(128, 128);
        this.lCap.mirror = true;
        this.setRotation(this.lCap, 0.0f, 0.0f, 0.0f);
        (this.rCap = new ModelRenderer((ModelBase) this, 18, 25))
            .addBox(-4.0f, -2.0f, -20.0f, 3, 3, 4);
        this.rCap.setRotationPoint(0.0f, 17.0f, 0.0f);
        this.rCap.setTextureSize(128, 128);
        this.rCap.mirror = true;
        this.setRotation(this.rCap, 0.0f, 0.0f, 0.0f);
        (this.Hat = new ModelRenderer((ModelBase) this, 28, 0))
            .addBox(0.0f, 0.0f, 0.0f, 3, 1, 7);
        this.Hat.setRotationPoint(-1.5f, 12.0f, -2.0f);
        this.Hat.setTextureSize(128, 128);
        this.Hat.mirror = true;
        this.setRotation(this.Hat, 0.0f, 0.0f, 0.0f);
        (this.LowerHat = new ModelRenderer((ModelBase) this, 50, 0))
            .addBox(0.0f, 0.0f, 0.0f, 3, 1, 1);
        this.LowerHat.setRotationPoint(-1.5f, 13.0f, 4.0f);
        this.LowerHat.setTextureSize(128, 128);
        this.LowerHat.mirror = true;
        this.setRotation(this.LowerHat, 0.0f, 0.0f, 0.0f);
        (this.BatteryPack = new ModelRenderer((ModelBase) this, 50, 3))
            .addBox(0.0f, 0.0f, 0.0f, 4, 4, 1);
        this.BatteryPack.setRotationPoint(-2.0f, 15.0f, 5.0f);
        this.BatteryPack.setTextureSize(128, 128);
        this.BatteryPack.mirror = true;
        this.setRotation(this.BatteryPack, 0.0f, 0.0f, 0.0f);
        (this.MiddleWire = new ModelRenderer((ModelBase) this, 64, 0))
            .addBox(0.0f, 0.0f, 0.0f, 1, 4, 1);
        this.MiddleWire.setRotationPoint(-0.5f, 13.0f, 7.0f);
        this.MiddleWire.setTextureSize(128, 128);
        this.MiddleWire.mirror = true;
        this.setRotation(this.MiddleWire, 0.0f, 0.0f, 0.0f);
        (this.BatWire = new ModelRenderer((ModelBase) this, 64, 6))
            .addBox(0.0f, 0.0f, 0.0f, 1, 1, 1);
        this.BatWire.setRotationPoint(-0.5f, 16.0f, 6.0f);
        this.BatWire.setTextureSize(128, 128);
        this.BatWire.mirror = true;
        this.setRotation(this.BatWire, 0.0f, 0.0f, 0.0f);
        (this.HatWire = new ModelRenderer((ModelBase) this, 69, 0))
            .addBox(0.0f, 0.0f, 0.0f, 1, 1, 2);
        this.HatWire.setRotationPoint(-0.5f, 13.0f, 5.0f);
        this.HatWire.setTextureSize(128, 128);
        this.HatWire.mirror = true;
        this.setRotation(this.HatWire, 0.0f, 0.0f, 0.0f);
        (this.Details = new ModelRenderer((ModelBase) this, 28, 10))
            .addBox(0.0f, 0.0f, 0.0f, 3, 1, 1);
        this.Details.setRotationPoint(-1.5f, 22.0f, -5.5f);
        this.Details.setTextureSize(128, 128);
        this.Details.mirror = true;
        this.setRotation(this.Details, 0.0f, 0.0f, 0.0f);
        (this.RightFootStrength = new ModelRenderer((ModelBase) this, 28, 14))
            .addBox(0.0f, 0.0f, 0.0f, 1, 2, 4);
        this.RightFootStrength.setRotationPoint(3.0f, 20.0f, -2.0f);
        this.RightFootStrength.setTextureSize(128, 128);
        this.RightFootStrength.mirror = true;
        this.setRotation(this.RightFootStrength, 0.0f, 0.0f, 0.0f);
        (this.LeftFootStrength = new ModelRenderer((ModelBase) this, 28, 14))
            .addBox(0.0f, 0.0f, 0.0f, 1, 2, 4);
        this.LeftFootStrength.setRotationPoint(-4.0f, 20.0f, -2.0f);
        this.LeftFootStrength.setTextureSize(128, 128);
        this.LeftFootStrength.mirror = true;
        this.setRotation(this.LeftFootStrength, 0.0f, 0.0f, 0.0f);
        (this.FrontDetail = new ModelRenderer((ModelBase) this, 40, 10))
            .addBox(0.0f, 0.0f, 0.0f, 3, 3, 1);
        this.FrontDetail.setRotationPoint(-1.5f, 21.0f, -6.5f);
        this.FrontDetail.setTextureSize(128, 128);
        this.FrontDetail.mirror = true;
        this.setRotation(this.FrontDetail, 0.0f, 0.0f, 0.0f);
        (this.LeftFootBase = new ModelRenderer((ModelBase) this, 0, 0))
            .addBox(0.0f, 0.0f, 0.0f, 3, 1, 6);
        this.LeftFootBase.setRotationPoint(-5.5f, 22.0f, -3.0f);
        this.LeftFootBase.setTextureSize(128, 128);
        this.LeftFootBase.mirror = true;
        this.setRotation(this.LeftFootBase, 0.0f, 0.0f, 0.0f);
        (this.RightFootBase = new ModelRenderer((ModelBase) this, 0, 0))
            .addBox(0.0f, 0.0f, 0.0f, 3, 1, 6);
        this.RightFootBase.setRotationPoint(2.5f, 22.0f, -3.0f);
        this.RightFootBase.setTextureSize(128, 128);
        this.RightFootBase.mirror = true;
        this.setRotation(this.RightFootBase, 0.0f, 0.0f, 0.0f);
        (this.lEar1 = new ModelRenderer((ModelBase) this, 28, 56))
            .addBox(2.0f, -5.0f, -4.0f, 1, 5, 1);
        this.lEar1.setRotationPoint(0.0f, 17.0f, 5.0f);
        this.lEar1.setTextureSize(128, 128);
        this.lEar1.mirror = true;
        this.setRotation(this.lEar1, -0.6632251f, 0.0f, 0.0f);
        (this.lEar2 = new ModelRenderer((ModelBase) this, 28, 56))
            .addBox(2.0f, -5.0f, -4.0f, 1, 5, 1);
        this.lEar2.setRotationPoint(0.0f, 17.0f, 0.0f);
        this.lEar2.setTextureSize(128, 128);
        this.lEar2.mirror = true;
        this.setRotation(this.lEar2, -0.6632251f, 0.0f, 0.0f);
        (this.rEar1 = new ModelRenderer((ModelBase) this, 23, 56))
            .addBox(-3.0f, -5.0f, -4.0f, 1, 5, 1);
        this.rEar1.setRotationPoint(0.0f, 17.0f, 5.0f);
        this.rEar1.setTextureSize(128, 128);
        this.rEar1.mirror = true;
        this.setRotation(this.rEar1, -0.6632251f, 0.0f, 0.0f);
        (this.rEar2 = new ModelRenderer((ModelBase) this, 23, 56))
            .addBox(-3.0f, -5.0f, -4.0f, 1, 5, 1);
        this.rEar2.setRotationPoint(0.0f, 17.0f, 0.0f);
        this.rEar2.setTextureSize(128, 128);
        this.rEar2.mirror = true;
        this.setRotation(this.rEar2, -0.6632251f, 0.0f, 0.0f);
    }

    public void renderYaw(final float f5) {
        this.basePlate.render(f5);
        this.leftMount.render(f5);
        this.rightMount.render(f5);
        this.Details.render(f5);
        this.FrontDetail.render(f5);
        this.RightFootStrength.render(f5);
        this.LeftFootStrength.render(f5);
        this.LeftFootBase.render(f5);
        this.RightFootBase.render(f5);
    }

    public void renderYawPitch(final float f5, final float rotation) {
        this.body.render(f5);
        this.bodyTop.render(f5);
        this.bodyRight.render(f5);
        this.bodyLeft.render(f5);
        this.leftBarrel.render(f5);
        this.rightBarrel.render(f5);
        this.l1.render(f5);
        this.r1.render(f5);
        this.l2.rotateAngleZ = -rotation;
        this.l2.render(f5);
        this.l3.rotateAngleZ = -rotation;
        this.l3.render(f5);
        this.l4.rotateAngleZ = -rotation;
        this.l4.render(f5);
        this.l5.rotateAngleZ = -rotation;
        this.l5.render(f5);
        this.r2.rotateAngleZ = rotation;
        this.r2.render(f5);
        this.r3.rotateAngleZ = rotation;
        this.r3.render(f5);
        this.r4.rotateAngleZ = rotation;
        this.r4.render(f5);
        this.r5.rotateAngleZ = rotation;
        this.r5.render(f5);
        this.lCap.render(f5);
        this.rCap.render(f5);
        this.Hat.render(f5);
        this.LowerHat.render(f5);
        this.BatteryPack.render(f5);
        this.MiddleWire.render(f5);
        this.BatWire.render(f5);
        this.HatWire.render(f5);
        this.lEar1.render(f5);
        this.lEar2.render(f5);
        this.rEar1.render(f5);
        this.rEar2.render(f5);
    }

    private void
    setRotation(final ModelRenderer model, final float x, final float y, final float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
}
