package icbm.zhapin.muoxing.jiqi;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

@SideOnly(Side.CLIENT)
public class MLeiShePao extends ModelBase {
    ModelRenderer Turret_Base;
    ModelRenderer Support_1;
    ModelRenderer Support_2;
    ModelRenderer Support_3;
    ModelRenderer Support_4;
    ModelRenderer Turret_Neck;
    ModelRenderer Turret_Body;

    public MLeiShePao() {
        super.textureWidth = 128;
        super.textureHeight = 128;
        (this.Turret_Base = new ModelRenderer((ModelBase) this, 0, 0))
            .addBox(0.0f, 0.0f, 0.0f, 14, 14, 14);
        this.Turret_Base.setRotationPoint(-7.0f, 10.0f, -7.0f);
        this.Turret_Base.setTextureSize(128, 128);
        this.Turret_Base.mirror = true;
        this.setRotation(this.Turret_Base, 0.0f, 0.0f, 0.0f);
        (this.Support_1 = new ModelRenderer((ModelBase) this, 0, 60))
            .addBox(0.0f, 0.0f, 0.0f, 2, 6, 2);
        this.Support_1.setRotationPoint(6.0f, 18.0f, 6.0f);
        this.Support_1.setTextureSize(128, 128);
        this.Support_1.mirror = true;
        this.setRotation(this.Support_1, 0.0f, 0.0f, 0.0f);
        (this.Support_2 = new ModelRenderer((ModelBase) this, 0, 60))
            .addBox(0.0f, 0.0f, 0.0f, 2, 6, 2);
        this.Support_2.setRotationPoint(6.0f, 18.0f, -8.0f);
        this.Support_2.setTextureSize(128, 128);
        this.Support_2.mirror = true;
        this.setRotation(this.Support_2, 0.0f, 0.0f, 0.0f);
        (this.Support_3 = new ModelRenderer((ModelBase) this, 0, 60))
            .addBox(0.0f, 0.0f, 0.0f, 2, 6, 2);
        this.Support_3.setRotationPoint(-8.0f, 18.0f, -8.0f);
        this.Support_3.setTextureSize(128, 128);
        this.Support_3.mirror = true;
        this.setRotation(this.Support_3, 0.0f, 0.0f, 0.0f);
        (this.Support_4 = new ModelRenderer((ModelBase) this, 0, 60))
            .addBox(0.0f, 0.0f, 0.0f, 2, 6, 2);
        this.Support_4.setRotationPoint(-8.0f, 18.0f, 6.0f);
        this.Support_4.setTextureSize(128, 128);
        this.Support_4.mirror = true;
        this.setRotation(this.Support_4, 0.0f, 0.0f, 0.0f);
        (this.Turret_Neck = new ModelRenderer((ModelBase) this, 10, 60))
            .addBox(-1.0f, 0.0f, -1.0f, 2, 1, 2);
        this.Turret_Neck.setRotationPoint(0.0f, -1.0f, 0.0f);
        this.Turret_Neck.setTextureSize(128, 128);
        this.Turret_Neck.mirror = true;
        this.setRotation(this.Turret_Neck, 0.0f, 0.0f, 0.0f);
        (this.Turret_Body = new ModelRenderer((ModelBase) this, 0, 30))
            .addBox(0.0f, 0.0f, 0.0f, 10, 11, 10);
        this.Turret_Body.setRotationPoint(-5.0f, 0.0f, -5.0f);
        this.Turret_Body.setTextureSize(128, 128);
        this.Turret_Body.mirror = true;
        this.setRotation(this.Turret_Body, 0.0f, 0.0f, 0.0f);
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
        this.Turret_Base.render(f5);
        this.Support_1.render(f5);
        this.Support_2.render(f5);
        this.Support_3.render(f5);
        this.Support_4.render(f5);
        this.Turret_Neck.render(f5);
        this.Turret_Body.render(f5);
    }

    public void render(final float f5) {
        this.Turret_Body.render(f5);
        this.Turret_Base.render(f5);
        this.Support_1.render(f5);
        this.Support_2.render(f5);
        this.Support_3.render(f5);
        this.Support_4.render(f5);
        this.Turret_Neck.render(f5);
    }

    private void
    setRotation(final ModelRenderer model, final float x, final float y, final float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
}
