package icbm.zhapin.muoxing.jiqi;

import icbm.core.di.MICBM;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class MYinDaoQi extends MICBM {
    ModelRenderer Stand2;
    ModelRenderer Stand1;
    ModelRenderer Console6;
    ModelRenderer Stand3;
    ModelRenderer Console1;
    ModelRenderer Console3;
    ModelRenderer Screen1;
    ModelRenderer Screen2;
    ModelRenderer Radio1;
    ModelRenderer Radio_Cable2;
    ModelRenderer Radio_Antennae;
    ModelRenderer Radio2;
    ModelRenderer Radio_Antennae2;
    ModelRenderer Keyboard;
    ModelRenderer Button5;
    ModelRenderer Button4;
    ModelRenderer Button14;
    ModelRenderer Button15;
    ModelRenderer Button13;
    ModelRenderer Console5;
    ModelRenderer Console4;
    ModelRenderer Button8;
    ModelRenderer Button2;
    ModelRenderer Button7;
    ModelRenderer Button1;
    ModelRenderer Button11;
    ModelRenderer Button12;
    ModelRenderer Button10;
    ModelRenderer Button16;
    ModelRenderer Button17;
    ModelRenderer Button18;
    ModelRenderer Standend2;
    ModelRenderer Standend1;
    ModelRenderer Console2;
    ModelRenderer Radio_Cable;

    public MYinDaoQi() {
        super.textureWidth = 64;
        super.textureHeight = 32;
        (this.Stand2 = new ModelRenderer((ModelBase) this, 0, 9))
            .addBox(-1.0f, 0.0f, -10.0f, 2, 1, 17);
        this.Stand2.setRotationPoint(0.0f, 23.0f, 0.0f);
        this.Stand2.setTextureSize(64, 32);
        this.Stand2.mirror = true;
        this.setRotation(this.Stand2, 0.0f, 0.7853982f, 0.0f);
        (this.Stand1 = new ModelRenderer((ModelBase) this, 0, 9))
            .addBox(-1.0f, 0.0f, -10.0f, 2, 1, 17);
        this.Stand1.setRotationPoint(0.0f, 23.0f, 0.0f);
        this.Stand1.setTextureSize(64, 32);
        this.Stand1.mirror = true;
        this.setRotation(this.Stand1, 0.0f, -0.7853982f, 0.0f);
        (this.Console6 = new ModelRenderer((ModelBase) this, 12, 30))
            .addBox(0.75f, -1.1f, 0.8f, 1, 1, 1);
        this.Console6.setRotationPoint(0.0f, 20.0f, 1.0f);
        this.Console6.setTextureSize(64, 32);
        this.Console6.mirror = true;
        this.setRotation(this.Console6, 0.1396263f, 0.0f, 0.0f);
        (this.Stand3 = new ModelRenderer((ModelBase) this, 56, 15))
            .addBox(-1.0f, -5.0f, -1.0f, 2, 6, 2);
        this.Stand3.setRotationPoint(0.0f, 23.0f, 0.0f);
        this.Stand3.setTextureSize(64, 32);
        this.Stand3.mirror = true;
        this.setRotation(this.Stand3, -0.7853982f, 0.0f, 0.0f);
        (this.Console1 = new ModelRenderer((ModelBase) this, 12, 30))
            .addBox(0.8f, -1.1f, 0.8f, 1, 1, 1);
        this.Console1.setRotationPoint(-3.8f, 20.0f, -0.6f);
        this.Console1.setTextureSize(64, 32);
        this.Console1.mirror = true;
        this.setRotation(this.Console1, 0.1396263f, -0.7853982f, 0.0f);
        (this.Console3 = new ModelRenderer((ModelBase) this, 12, 30))
            .addBox(0.8f, -1.1f, 0.8f, 1, 1, 1);
        this.Console3.setRotationPoint(3.85f, 20.0f, -0.6f);
        this.Console3.setTextureSize(64, 32);
        this.Console3.mirror = true;
        this.setRotation(this.Console3, 0.1396263f, 0.7853982f, 0.0f);
        (this.Screen1 = new ModelRenderer((ModelBase) this, 0, 27))
            .addBox(-7.0f, -3.0f, 0.0f, 6, 5, 0);
        this.Screen1.setRotationPoint(0.0f, 15.0f, 4.0f);
        this.Screen1.setTextureSize(64, 32);
        this.Screen1.mirror = true;
        this.setRotation(this.Screen1, 0.0f, -0.2792527f, 0.0f);
        (this.Screen2 = new ModelRenderer((ModelBase) this, 0, 27))
            .addBox(1.0f, -3.0f, 0.0f, 6, 5, 0);
        this.Screen2.setRotationPoint(0.0f, 15.0f, 4.0f);
        this.Screen2.setTextureSize(64, 32);
        this.Screen2.mirror = true;
        this.setRotation(this.Screen2, 0.0f, 0.2792527f, 0.0f);
        (this.Radio1 = new ModelRenderer((ModelBase) this, 50, 23))
            .addBox(-1.0f, 0.0f, -1.0f, 4, 6, 3);
        this.Radio1.setRotationPoint(-4.0f, 24.0f, 5.0f);
        this.Radio1.setTextureSize(64, 32);
        this.Radio1.mirror = true;
        this.setRotation(this.Radio1, 0.0f, 0.0f, 3.141593f);
        (this.Radio_Cable2 = new ModelRenderer((ModelBase) this, 12, 20))
            .addBox(-0.5f, 0.0f, -0.5f, 1, 5, 1);
        this.Radio_Cable2.setRotationPoint(-3.5f, 22.0f, 5.0f);
        this.Radio_Cable2.setTextureSize(64, 32);
        this.Radio_Cable2.mirror = true;
        this.setRotation(this.Radio_Cable2, -1.570796f, -0.7853982f, 3.141593f);
        (this.Radio_Antennae = new ModelRenderer((ModelBase) this, 46, 27))
            .addBox(-0.5f, 0.0f, -0.5f, 1, 4, 1);
        this.Radio_Antennae.setRotationPoint(-6.0f, 18.0f, 5.5f);
        this.Radio_Antennae.setTextureSize(64, 32);
        this.Radio_Antennae.mirror = true;
        this.setRotation(this.Radio_Antennae, 0.0f, 0.0f, 3.141593f);
        (this.Radio2 = new ModelRenderer((ModelBase) this, 50, 23))
            .addBox(-1.0f, 0.0f, -1.0f, 4, 6, 3);
        this.Radio2.setRotationPoint(6.0f, 24.0f, 5.0f);
        this.Radio2.setTextureSize(64, 32);
        this.Radio2.mirror = true;
        this.setRotation(this.Radio2, 0.0f, 0.0f, 3.141593f);
        (this.Radio_Antennae2 = new ModelRenderer((ModelBase) this, 46, 27))
            .addBox(-0.5f, 0.0f, -0.5f, 1, 4, 1);
        this.Radio_Antennae2.setRotationPoint(5.75f, 18.0f, 5.5f);
        this.Radio_Antennae2.setTextureSize(64, 32);
        this.Radio_Antennae2.mirror = true;
        this.setRotation(this.Radio_Antennae2, 0.0f, 0.0f, 3.141593f);
        (this.Keyboard = new ModelRenderer((ModelBase) this, 21, 23))
            .addBox(-4.0f, -1.0f, 0.0f, 8, 1, 2);
        this.Keyboard.setRotationPoint(0.0f, 20.0f, 0.0f);
        this.Keyboard.setTextureSize(64, 32);
        this.Keyboard.mirror = true;
        this.setRotation(this.Keyboard, 3.141593f, 0.0f, 0.0f);
        (this.Button5 = new ModelRenderer((ModelBase) this, 12, 30))
            .addBox(-0.5f, -1.1f, 0.8f, 1, 1, 1);
        this.Button5.setRotationPoint(0.0f, 20.0f, 1.0f);
        this.Button5.setTextureSize(64, 32);
        this.Button5.mirror = true;
        this.setRotation(this.Button5, 0.1396263f, 0.0f, 0.0f);
        (this.Button4 = new ModelRenderer((ModelBase) this, 12, 30))
            .addBox(-1.8f, -1.1f, 0.8f, 1, 1, 1);
        this.Button4.setRotationPoint(0.0f, 20.0f, 1.0f);
        this.Button4.setTextureSize(64, 32);
        this.Button4.mirror = true;
        this.setRotation(this.Button4, 0.1396263f, 0.0f, 0.0f);
        (this.Button14 = new ModelRenderer((ModelBase) this, 12, 30))
            .addBox(-0.5f, -1.1f, -0.5f, 1, 1, 1);
        this.Button14.setRotationPoint(0.0f, 20.0f, 1.0f);
        this.Button14.setTextureSize(64, 32);
        this.Button14.mirror = true;
        this.setRotation(this.Button14, 0.1396263f, 0.0f, 0.0f);
        (this.Button15 = new ModelRenderer((ModelBase) this, 12, 30))
            .addBox(0.75f, -1.1f, -0.5f, 1, 1, 1);
        this.Button15.setRotationPoint(0.0f, 20.0f, 1.0f);
        this.Button15.setTextureSize(64, 32);
        this.Button15.mirror = true;
        this.setRotation(this.Button15, 0.1396263f, 0.0f, 0.0f);
        (this.Button13 = new ModelRenderer((ModelBase) this, 12, 30))
            .addBox(-1.8f, -1.1f, -0.5f, 1, 1, 1);
        this.Button13.setRotationPoint(0.0f, 20.0f, 1.0f);
        this.Button13.setTextureSize(64, 32);
        this.Button13.mirror = true;
        this.setRotation(this.Button13, 0.1396263f, 0.0f, 0.0f);
        (this.Console5 = new ModelRenderer((ModelBase) this, 16, 28))
            .addBox(-3.0f, -1.0f, -1.0f, 6, 1, 3);
        this.Console5.setRotationPoint(3.85f, 20.0f, -0.6f);
        this.Console5.setTextureSize(64, 32);
        this.Console5.mirror = true;
        this.setRotation(this.Console5, 0.1396263f, 0.7853982f, 0.0f);
        (this.Console4 = new ModelRenderer((ModelBase) this, 16, 28))
            .addBox(-3.0f, -1.0f, -1.0f, 6, 1, 3);
        this.Console4.setRotationPoint(-3.8f, 20.0f, -0.6f);
        this.Console4.setTextureSize(64, 32);
        this.Console4.mirror = true;
        this.setRotation(this.Console4, 0.1396263f, -0.7853982f, 0.0f);
        (this.Button8 = new ModelRenderer((ModelBase) this, 12, 30))
            .addBox(-0.5f, -1.1f, 0.8f, 1, 1, 1);
        this.Button8.setRotationPoint(3.85f, 20.0f, -0.6f);
        this.Button8.setTextureSize(64, 32);
        this.Button8.mirror = true;
        this.setRotation(this.Button8, 0.1396263f, 0.7853982f, 0.0f);
        (this.Button2 = new ModelRenderer((ModelBase) this, 12, 30))
            .addBox(-0.5f, -1.1f, 0.8f, 1, 1, 1);
        this.Button2.setRotationPoint(-3.8f, 20.0f, -0.6f);
        this.Button2.setTextureSize(64, 32);
        this.Button2.mirror = true;
        this.setRotation(this.Button2, 0.1396263f, -0.7853982f, 0.0f);
        (this.Button7 = new ModelRenderer((ModelBase) this, 12, 30))
            .addBox(-1.8f, -1.1f, 0.8f, 1, 1, 1);
        this.Button7.setRotationPoint(3.85f, 20.0f, -0.6f);
        this.Button7.setTextureSize(64, 32);
        this.Button7.mirror = true;
        this.setRotation(this.Button7, 0.1396263f, 0.7853982f, 0.0f);
        (this.Button1 = new ModelRenderer((ModelBase) this, 12, 30))
            .addBox(-1.8f, -1.1f, 0.8f, 1, 1, 1);
        this.Button1.setRotationPoint(-3.8f, 20.0f, -0.6f);
        this.Button1.setTextureSize(64, 32);
        this.Button1.mirror = true;
        this.setRotation(this.Button1, 0.1396263f, -0.7853982f, 0.0f);
        (this.Button11 = new ModelRenderer((ModelBase) this, 12, 30))
            .addBox(-0.5f, -1.1f, -0.5f, 1, 1, 1);
        this.Button11.setRotationPoint(-3.8f, 20.0f, -0.6f);
        this.Button11.setTextureSize(64, 32);
        this.Button11.mirror = true;
        this.setRotation(this.Button11, 0.1396263f, -0.7853982f, 0.0f);
        (this.Button12 = new ModelRenderer((ModelBase) this, 12, 30))
            .addBox(0.8f, -1.1f, -0.5f, 1, 1, 1);
        this.Button12.setRotationPoint(-3.8f, 20.0f, -0.6f);
        this.Button12.setTextureSize(64, 32);
        this.Button12.mirror = true;
        this.setRotation(this.Button12, 0.1396263f, -0.7853982f, 0.0f);
        (this.Button10 = new ModelRenderer((ModelBase) this, 12, 30))
            .addBox(-1.8f, -1.1f, -0.5f, 1, 1, 1);
        this.Button10.setRotationPoint(-3.8f, 20.0f, -0.6f);
        this.Button10.setTextureSize(64, 32);
        this.Button10.mirror = true;
        this.setRotation(this.Button10, 0.1396263f, -0.7853982f, 0.0f);
        (this.Button16 = new ModelRenderer((ModelBase) this, 12, 30))
            .addBox(-1.8f, -1.1f, -0.5f, 1, 1, 1);
        this.Button16.setRotationPoint(3.85f, 20.0f, -0.6f);
        this.Button16.setTextureSize(64, 32);
        this.Button16.mirror = true;
        this.setRotation(this.Button16, 0.1396263f, 0.7853982f, 0.0f);
        (this.Button17 = new ModelRenderer((ModelBase) this, 12, 30))
            .addBox(-0.5f, -1.1f, -0.5f, 1, 1, 1);
        this.Button17.setRotationPoint(3.85f, 20.0f, -0.6f);
        this.Button17.setTextureSize(64, 32);
        this.Button17.mirror = true;
        this.setRotation(this.Button17, 0.1396263f, 0.7853982f, 0.0f);
        (this.Button18 = new ModelRenderer((ModelBase) this, 12, 30))
            .addBox(0.8f, -1.1f, -0.5f, 1, 1, 1);
        this.Button18.setRotationPoint(3.85f, 20.0f, -0.6f);
        this.Button18.setTextureSize(64, 32);
        this.Button18.mirror = true;
        this.setRotation(this.Button18, 0.1396263f, 0.7853982f, 0.0f);
        (this.Standend2 = new ModelRenderer((ModelBase) this, 0, 21))
            .addBox(0.0f, 0.0f, 0.0f, 3, 2, 3);
        this.Standend2.setRotationPoint(4.9f, 22.9f, -7.9f);
        this.Standend2.setTextureSize(64, 32);
        this.Standend2.mirror = true;
        this.setRotation(this.Standend2, 0.0f, 0.0f, 0.0f);
        (this.Standend1 = new ModelRenderer((ModelBase) this, 0, 21))
            .addBox(0.0f, 0.0f, 0.0f, 3, 2, 3);
        this.Standend1.setRotationPoint(-7.9f, 22.9f, -7.9f);
        this.Standend1.setTextureSize(64, 32);
        this.Standend1.mirror = true;
        this.setRotation(this.Standend1, 0.0f, 0.0f, 0.0f);
        (this.Console2 = new ModelRenderer((ModelBase) this, 16, 28))
            .addBox(-3.0f, -1.0f, -1.0f, 6, 1, 3);
        this.Console2.setRotationPoint(0.0f, 20.0f, 1.0f);
        this.Console2.setTextureSize(64, 32);
        this.Console2.mirror = true;
        this.setRotation(this.Console2, 0.1396263f, 0.0f, 0.0f);
        (this.Radio_Cable = new ModelRenderer((ModelBase) this, 12, 20))
            .addBox(-0.5f, 0.0f, -0.5f, 1, 5, 1);
        this.Radio_Cable.setRotationPoint(3.5f, 22.0f, 5.0f);
        this.Radio_Cable.setTextureSize(64, 32);
        this.Radio_Cable.mirror = true;
        this.setRotation(this.Radio_Cable, -1.570796f, 0.7853982f, 3.141593f);
    }

    public void render(final float rotation, final float f5) {
        this.Stand2.render(f5);
        this.Stand1.render(f5);
        this.Console6.render(f5);
        this.Stand3.render(f5);
        this.Console1.render(f5);
        this.Console3.render(f5);
        this.Screen1.render(f5);
        this.Screen2.render(f5);
        this.Radio1.render(f5);
        this.Radio_Cable2.render(f5);
        this.Radio_Antennae.render(f5);
        this.Radio2.render(f5);
        this.Radio_Antennae2.render(f5);
        this.Keyboard.render(f5);
        this.Button5.render(f5);
        this.Button4.render(f5);
        this.Button14.render(f5);
        this.Button15.render(f5);
        this.Button13.render(f5);
        this.Console5.render(f5);
        this.Console4.render(f5);
        this.Button8.render(f5);
        this.Button2.render(f5);
        this.Button7.render(f5);
        this.Button1.render(f5);
        this.Button11.render(f5);
        this.Button12.render(f5);
        this.Button10.render(f5);
        this.Button16.render(f5);
        this.Button17.render(f5);
        this.Button18.render(f5);
        this.Standend2.render(f5);
        this.Standend1.render(f5);
        this.Console2.render(f5);
        this.Radio_Cable.render(f5);
    }

    private void
    setRotation(final ModelRenderer model, final float x, final float y, final float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
}
