package icbm.zhapin.muoxing.jiqi;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

@SideOnly(Side.CLIENT)
public class MDianCiQi extends ModelBase {
    ModelRenderer Base;
    ModelRenderer Support;
    ModelRenderer Turret_Support;
    ModelRenderer Main_Coil;
    ModelRenderer Support_Beam_A1;
    ModelRenderer Support_Beam_B1;
    ModelRenderer Support_Beam_C1;
    ModelRenderer Support_Beam_D1;
    ModelRenderer Support_Beam_DB1;
    ModelRenderer Support_Beam_CA1;
    ModelRenderer Support_Beam_CA2;
    ModelRenderer Support_Beam_DB2;

    public MDianCiQi() {
        super.textureWidth = 128;
        super.textureHeight = 128;
        (this.Base = new ModelRenderer((ModelBase) this, 0, 0))
            .addBox(0.0f, 0.0f, 0.0f, 16, 6, 16);
        this.Base.setRotationPoint(-8.0f, 18.0f, -8.0f);
        this.Base.setTextureSize(128, 128);
        this.Base.mirror = true;
        this.setRotation(this.Base, 0.0f, 0.0f, 0.0f);
        (this.Support = new ModelRenderer((ModelBase) this, 0, 24))
            .addBox(0.0f, 0.0f, 0.0f, 10, 8, 10);
        this.Support.setRotationPoint(-5.0f, 10.0f, -5.0f);
        this.Support.setTextureSize(128, 128);
        this.Support.mirror = true;
        this.setRotation(this.Support, 0.0f, 0.0f, 0.0f);
        (this.Turret_Support = new ModelRenderer((ModelBase) this, 0, 44))
            .addBox(-7.0f, 0.0f, -7.0f, 14, 1, 14);
        this.Turret_Support.setRotationPoint(0.0f, 9.0f, 0.0f);
        this.Turret_Support.setTextureSize(128, 128);
        this.Turret_Support.mirror = true;
        this.setRotation(this.Turret_Support, 0.0f, 0.7853982f, 0.0f);
        (this.Main_Coil = new ModelRenderer((ModelBase) this, 66, 0))
            .addBox(-2.0f, 0.0f, -2.0f, 4, 11, 4);
        this.Main_Coil.setRotationPoint(0.0f, -5.0f, 0.0f);
        this.Main_Coil.setTextureSize(128, 128);
        this.Main_Coil.mirror = true;
        this.setRotation(this.Main_Coil, 0.0f, 0.0f, 0.0f);
        (this.Support_Beam_A1 = new ModelRenderer((ModelBase) this, 66, 16))
            .addBox(8.0f, 0.0f, -1.0f, 1, 18, 2);
        this.Support_Beam_A1.setRotationPoint(0.0f, -8.0f, 0.0f);
        this.Support_Beam_A1.setTextureSize(128, 128);
        this.Support_Beam_A1.mirror = true;
        this.setRotation(this.Support_Beam_A1, 0.0f, 0.0f, 0.0f);
        (this.Support_Beam_B1 = new ModelRenderer((ModelBase) this, 73, 16))
            .addBox(-1.0f, 0.0f, -9.0f, 2, 18, 1);
        this.Support_Beam_B1.setRotationPoint(0.0f, -8.0f, 0.0f);
        this.Support_Beam_B1.setTextureSize(128, 128);
        this.Support_Beam_B1.mirror = true;
        this.setRotation(this.Support_Beam_B1, 0.0f, 0.0f, 0.0f);
        (this.Support_Beam_C1 = new ModelRenderer((ModelBase) this, 66, 16))
            .addBox(-9.0f, 0.0f, -1.0f, 1, 18, 2);
        this.Support_Beam_C1.setRotationPoint(0.0f, -8.0f, 0.0f);
        this.Support_Beam_C1.setTextureSize(128, 128);
        this.Support_Beam_C1.mirror = true;
        this.setRotation(this.Support_Beam_C1, 0.0f, 0.0f, 0.0f);
        (this.Support_Beam_D1 = new ModelRenderer((ModelBase) this, 73, 16))
            .addBox(-1.0f, 0.0f, 8.0f, 2, 18, 1);
        this.Support_Beam_D1.setRotationPoint(0.0f, -8.0f, 0.0f);
        this.Support_Beam_D1.setTextureSize(128, 128);
        this.Support_Beam_D1.mirror = true;
        this.setRotation(this.Support_Beam_D1, 0.0f, 0.0f, 0.0f);
        (this.Support_Beam_DB1 = new ModelRenderer((ModelBase) this, 85, 0))
            .addBox(-1.0f, 0.0f, -8.0f, 2, 1, 16);
        this.Support_Beam_DB1.setRotationPoint(0.0f, 3.0f, 0.0f);
        this.Support_Beam_DB1.setTextureSize(128, 128);
        this.Support_Beam_DB1.mirror = true;
        this.setRotation(this.Support_Beam_DB1, 0.0f, 0.0f, 0.0f);
        (this.Support_Beam_CA1 = new ModelRenderer((ModelBase) this, 85, 19))
            .addBox(-8.0f, 0.0f, -1.0f, 16, 1, 2);
        this.Support_Beam_CA1.setRotationPoint(0.0f, 3.0f, 0.0f);
        this.Support_Beam_CA1.setTextureSize(128, 128);
        this.Support_Beam_CA1.mirror = true;
        this.setRotation(this.Support_Beam_CA1, 0.0f, 0.0f, 0.0f);
        (this.Support_Beam_CA2 = new ModelRenderer((ModelBase) this, 85, 19))
            .addBox(-8.0f, 0.0f, -1.0f, 16, 1, 2);
        this.Support_Beam_CA2.setRotationPoint(0.0f, -3.0f, 0.0f);
        this.Support_Beam_CA2.setTextureSize(128, 128);
        this.Support_Beam_CA2.mirror = true;
        this.setRotation(this.Support_Beam_CA2, 0.0f, 0.0f, 0.0f);
        (this.Support_Beam_DB2 = new ModelRenderer((ModelBase) this, 85, 0))
            .addBox(-1.0f, 0.0f, -8.0f, 2, 1, 16);
        this.Support_Beam_DB2.setRotationPoint(0.0f, -3.0f, 0.0f);
        this.Support_Beam_DB2.setTextureSize(128, 128);
        this.Support_Beam_DB2.mirror = true;
        this.setRotation(this.Support_Beam_DB2, 0.0f, 0.0f, 0.0f);
    }

    public void render(final float rotation, final float f5) {
        this.Base.render(f5);
        this.Support.render(f5);
        this.Turret_Support.rotateAngleY = -rotation;
        this.Turret_Support.render(f5);
        this.Main_Coil.rotateAngleY = -rotation;
        this.Main_Coil.render(f5);
        this.Support_Beam_A1.rotateAngleY = rotation;
        this.Support_Beam_A1.render(f5);
        this.Support_Beam_B1.rotateAngleY = rotation;
        this.Support_Beam_B1.render(f5);
        this.Support_Beam_C1.rotateAngleY = rotation;
        this.Support_Beam_C1.render(f5);
        this.Support_Beam_D1.rotateAngleY = rotation;
        this.Support_Beam_D1.render(f5);
        this.Support_Beam_DB1.rotateAngleY = rotation;
        this.Support_Beam_DB1.render(f5);
        this.Support_Beam_CA1.rotateAngleY = rotation;
        this.Support_Beam_CA1.render(f5);
        this.Support_Beam_CA2.rotateAngleY = rotation;
        this.Support_Beam_CA2.render(f5);
        this.Support_Beam_DB2.rotateAngleY = rotation;
        this.Support_Beam_DB2.render(f5);
    }

    private void
    setRotation(final ModelRenderer model, final float x, final float y, final float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
}
