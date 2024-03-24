package icbm.gangshao.muoxing;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelSentryCannon extends ModelBase {
    ModelRenderer BaseYawR;
    ModelRenderer BaseYawRPlate;
    ModelRenderer RightBrace;
    ModelRenderer RightBraceF;
    ModelRenderer RightBraceF2;
    ModelRenderer LeftBrace;
    ModelRenderer LeftBraceF;
    ModelRenderer LeftBraceF2;
    ModelRenderer FrontPlate;
    ModelRenderer SideDecor;
    ModelRenderer midPlate;
    ModelRenderer AmmoBox;
    ModelRenderer rod;
    ModelRenderer radioIntena;
    ModelRenderer Camera;
    ModelRenderer Camera2;
    ModelRenderer Camera3;
    ModelRenderer CannonBody;
    ModelRenderer CannonBody2;
    ModelRenderer CannonBody3;
    ModelRenderer CannonBody4;
    ModelRenderer Barrel;
    ModelRenderer BarrelCap;

    public ModelSentryCannon() {
        super.textureWidth = 128;
        super.textureHeight = 128;
        (this.BaseYawR = new ModelRenderer((ModelBase) this, 0, 119))
            .addBox(-3.0f, -3.0f, -3.0f, 6, 3, 6);
        this.BaseYawR.setRotationPoint(0.0f, 24.0f, 0.0f);
        this.BaseYawR.setTextureSize(128, 128);
        this.BaseYawR.mirror = true;
        this.setRotation(this.BaseYawR, 0.0f, 0.0f, 0.0f);
        (this.BaseYawRPlate = new ModelRenderer((ModelBase) this, 0, 104))
            .addBox(-5.0f, -3.0f, -5.0f, 10, 4, 10);
        this.BaseYawRPlate.setRotationPoint(0.0f, 20.0f, 0.0f);
        this.BaseYawRPlate.setTextureSize(128, 128);
        this.BaseYawRPlate.mirror = true;
        this.setRotation(this.BaseYawRPlate, 0.0f, 0.0f, 0.0f);
        (this.RightBrace = new ModelRenderer((ModelBase) this, 0, 77))
            .addBox(-5.0f, -3.0f, -3.0f, 1, 8, 8);
        this.RightBrace.setRotationPoint(0.0f, 12.0f, 0.0f);
        this.RightBrace.setTextureSize(128, 128);
        this.RightBrace.mirror = true;
        this.setRotation(this.RightBrace, 0.0f, 0.0f, 0.0f);
        (this.RightBraceF = new ModelRenderer((ModelBase) this, 19, 85))
            .addBox(-5.0f, -2.0f, -4.0f, 1, 7, 1);
        this.RightBraceF.setRotationPoint(0.0f, 12.0f, 0.0f);
        this.RightBraceF.setTextureSize(128, 128);
        this.RightBraceF.mirror = true;
        this.setRotation(this.RightBraceF, 0.0f, 0.0f, 0.0f);
        (this.RightBraceF2 = new ModelRenderer((ModelBase) this, 24, 86))
            .addBox(-5.0f, -1.0f, -5.0f, 1, 6, 1);
        this.RightBraceF2.setRotationPoint(0.0f, 12.0f, 0.0f);
        this.RightBraceF2.setTextureSize(128, 128);
        this.RightBraceF2.mirror = true;
        this.setRotation(this.RightBraceF2, 0.0f, 0.0f, 0.0f);
        (this.LeftBrace = new ModelRenderer((ModelBase) this, 41, 77))
            .addBox(4.0f, -3.0f, -3.0f, 1, 8, 8);
        this.LeftBrace.setRotationPoint(0.0f, 12.0f, 0.0f);
        this.LeftBrace.setTextureSize(128, 128);
        this.LeftBrace.mirror = true;
        this.setRotation(this.LeftBrace, 0.0f, 0.0f, 0.0f);
        (this.LeftBraceF = new ModelRenderer((ModelBase) this, 35, 85))
            .addBox(4.0f, -2.0f, -4.0f, 1, 7, 1);
        this.LeftBraceF.setRotationPoint(0.0f, 12.0f, 0.0f);
        this.LeftBraceF.setTextureSize(128, 128);
        this.LeftBraceF.mirror = true;
        this.setRotation(this.LeftBraceF, 0.0f, 0.0f, 0.0f);
        (this.LeftBraceF2 = new ModelRenderer((ModelBase) this, 30, 86))
            .addBox(4.0f, -1.0f, -5.0f, 1, 6, 1);
        this.LeftBraceF2.setRotationPoint(0.0f, 12.0f, 0.0f);
        this.LeftBraceF2.setTextureSize(128, 128);
        this.LeftBraceF2.mirror = true;
        this.setRotation(this.LeftBraceF2, 0.0f, 0.0f, 0.0f);
        (this.FrontPlate = new ModelRenderer((ModelBase) this, 42, 108))
            .addBox(-4.0f, -2.0f, -6.0f, 8, 4, 1);
        this.FrontPlate.setRotationPoint(0.0f, 20.0f, 0.0f);
        this.FrontPlate.setTextureSize(128, 128);
        this.FrontPlate.mirror = true;
        this.setRotation(this.FrontPlate, 0.0f, 0.0f, 0.0f);
        (this.SideDecor = new ModelRenderer((ModelBase) this, 61, 92))
            .addBox(-6.0f, -3.0f, -3.0f, 1, 4, 7);
        this.SideDecor.setRotationPoint(0.0f, 20.0f, 0.0f);
        this.SideDecor.setTextureSize(128, 128);
        this.SideDecor.mirror = true;
        this.setRotation(this.SideDecor, 0.0f, 0.0f, 0.0f);
        (this.midPlate = new ModelRenderer((ModelBase) this, 0, 94))
            .addBox(-4.0f, -4.0f, -4.0f, 8, 1, 8);
        this.midPlate.setRotationPoint(0.0f, 20.0f, 0.0f);
        this.midPlate.setTextureSize(128, 128);
        this.midPlate.mirror = true;
        this.setRotation(this.midPlate, 0.0f, 0.0f, 0.0f);
        (this.AmmoBox = new ModelRenderer((ModelBase) this, 61, 79))
            .addBox(5.0f, -5.0f, -3.0f, 3, 5, 7);
        this.AmmoBox.setRotationPoint(0.0f, 20.0f, 0.0f);
        this.AmmoBox.setTextureSize(128, 128);
        this.AmmoBox.mirror = true;
        this.setRotation(this.AmmoBox, 0.0f, 0.0f, 0.0f);
        (this.rod = new ModelRenderer((ModelBase) this, 33, 34))
            .addBox(-6.0f, -1.5f, -1.5f, 12, 3, 3);
        this.rod.setRotationPoint(0.0f, 12.0f, 1.0f);
        this.rod.setTextureSize(128, 128);
        this.rod.mirror = true;
        this.setRotation(this.rod, 0.0f, 0.0f, 0.0f);
        (this.radioIntena = new ModelRenderer((ModelBase) this, 66, 43))
            .addBox(2.0f, -8.0f, 6.0f, 1, 6, 1);
        this.radioIntena.setRotationPoint(0.0f, 12.0f, 0.0f);
        this.radioIntena.setTextureSize(128, 128);
        this.radioIntena.mirror = true;
        this.setRotation(this.radioIntena, 0.0f, 0.0f, 0.0f);
        (this.Camera = new ModelRenderer((ModelBase) this, 41, 16))
            .addBox(-9.5f, -2.5f, -2.0f, 4, 4, 4);
        this.Camera.setRotationPoint(0.0f, 12.0f, 1.0f);
        this.Camera.setTextureSize(128, 128);
        this.Camera.mirror = true;
        this.setRotation(this.Camera, 0.0f, 0.0f, 0.0f);
        (this.Camera2 = new ModelRenderer((ModelBase) this, 41, 11))
            .addBox(-9.0f, -1.5f, -2.5f, 3, 2, 1);
        this.Camera2.setRotationPoint(0.0f, 12.0f, 1.0f);
        this.Camera2.setTextureSize(128, 128);
        this.Camera2.mirror = true;
        this.setRotation(this.Camera2, 0.0f, 0.0f, 0.0f);
        (this.Camera3 = new ModelRenderer((ModelBase) this, 59, 18))
            .addBox(-9.0f, 1.5f, -2.0f, 3, 1, 4);
        this.Camera3.setRotationPoint(0.0f, 12.0f, 1.0f);
        this.Camera3.setTextureSize(128, 128);
        this.Camera3.mirror = true;
        this.setRotation(this.Camera3, 0.0f, 0.0f, 0.0f);
        (this.CannonBody = new ModelRenderer((ModelBase) this, 0, 31))
            .addBox(-3.5f, -2.5f, -1.5f, 7, 5, 9);
        this.CannonBody.setRotationPoint(0.0f, 12.0f, 0.0f);
        this.CannonBody.setTextureSize(128, 128);
        this.CannonBody.mirror = true;
        this.setRotation(this.CannonBody, 0.0f, 0.0f, 0.0f);
        (this.CannonBody2 = new ModelRenderer((ModelBase) this, 19, 46))
            .addBox(-2.5f, -1.5f, -6.5f, 5, 3, 5);
        this.CannonBody2.setRotationPoint(0.0f, 12.0f, 0.0f);
        this.CannonBody2.setTextureSize(128, 128);
        this.CannonBody2.mirror = true;
        this.setRotation(this.CannonBody2, 0.0f, 0.0f, 0.0f);
        (this.CannonBody3 = new ModelRenderer((ModelBase) this, 0, 47))
            .addBox(-2.0f, -2.1f, -6.5f, 4, 1, 5);
        this.CannonBody3.setRotationPoint(0.0f, 12.0f, 0.0f);
        this.CannonBody3.setTextureSize(128, 128);
        this.CannonBody3.mirror = true;
        this.setRotation(this.CannonBody3, 0.0f, 0.0f, 0.0f);
        (this.CannonBody4 = new ModelRenderer((ModelBase) this, 40, 47))
            .addBox(-2.0f, 1.0f, -6.5f, 4, 1, 5);
        this.CannonBody4.setRotationPoint(0.0f, 12.0f, 0.0f);
        this.CannonBody4.setTextureSize(128, 128);
        this.CannonBody4.mirror = true;
        this.setRotation(this.CannonBody4, 0.0f, 0.0f, 0.0f);
        (this.Barrel = new ModelRenderer((ModelBase) this, 10, 55))
            .addBox(-1.0f, -1.0f, -14.5f, 2, 2, 8);
        this.Barrel.setRotationPoint(0.0f, 12.0f, 0.0f);
        this.Barrel.setTextureSize(128, 128);
        this.Barrel.mirror = true;
        this.setRotation(this.Barrel, 0.0f, 0.0f, 0.0f);
        (this.BarrelCap = new ModelRenderer((ModelBase) this, 32, 56))
            .addBox(-1.5f, -1.5f, -16.5f, 3, 3, 2);
        this.BarrelCap.setRotationPoint(0.0f, 12.0f, 0.0f);
        this.BarrelCap.setTextureSize(128, 128);
        this.BarrelCap.mirror = true;
        this.setRotation(this.BarrelCap, 0.0f, 0.0f, 0.0f);
    }

    public void renderYaw(final float f5) {
        this.BaseYawR.render(f5);
        this.BaseYawRPlate.render(f5);
        this.RightBrace.render(f5);
        this.RightBraceF.render(f5);
        this.RightBraceF2.render(f5);
        this.LeftBrace.render(f5);
        this.LeftBraceF.render(f5);
        this.LeftBraceF2.render(f5);
        this.FrontPlate.render(f5);
        this.SideDecor.render(f5);
        this.midPlate.render(f5);
        this.AmmoBox.render(f5);
    }

    public void renderYawPitch(final float f5) {
        this.rod.render(f5);
        this.radioIntena.render(f5);
        this.Camera.render(f5);
        this.Camera2.render(f5);
        this.Camera3.render(f5);
        this.CannonBody.render(f5);
        this.CannonBody2.render(f5);
        this.CannonBody3.render(f5);
        this.CannonBody4.render(f5);
        this.Barrel.render(f5);
        this.BarrelCap.render(f5);
    }

    private void
    setRotation(final ModelRenderer model, final float x, final float y, final float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
}
