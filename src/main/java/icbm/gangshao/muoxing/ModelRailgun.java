package icbm.gangshao.muoxing;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

@SideOnly(Side.CLIENT)
public class ModelRailgun extends ModelBase {
    ModelRenderer BASE;
    ModelRenderer NECK;
    ModelRenderer SUPPORT_1_ROTATES;
    ModelRenderer MAIN_TURRET_ROTATES;
    ModelRenderer BATTERY_PACK_ROTATES;
    ModelRenderer MAIN_CANNON_ROTATES;
    ModelRenderer NOZZLE_ROTATES;
    ModelRenderer SUPPORT_2_ROTATES;
    ModelRenderer SUPPORT_PLATFORM_ROTATES;

    public ModelRailgun() {
        super.textureWidth = 128;
        super.textureHeight = 128;
        (this.BASE = new ModelRenderer((ModelBase) this, 0, 0))
            .addBox(0.0f, 0.0f, 0.0f, 10, 4, 10);
        this.BASE.setRotationPoint(-5.0f, 20.0f, -5.0f);
        this.BASE.setTextureSize(128, 128);
        this.BASE.mirror = true;
        this.setRotation(this.BASE, 0.0f, 0.0f, 0.0f);
        (this.NECK = new ModelRenderer((ModelBase) this, 0, 19))
            .addBox(0.0f, 0.0f, 0.0f, 6, 2, 6);
        this.NECK.setRotationPoint(-3.0f, 18.0f, -3.0f);
        this.NECK.setTextureSize(128, 128);
        this.NECK.mirror = true;
        this.setRotation(this.NECK, 0.0f, 0.0f, 0.0f);
        (this.SUPPORT_1_ROTATES = new ModelRenderer((ModelBase) this, 41, 10))
            .addBox(3.0f, 0.0f, -2.0f, 1, 7, 4);
        this.SUPPORT_1_ROTATES.setRotationPoint(0.0f, 10.0f, 0.0f);
        this.SUPPORT_1_ROTATES.setTextureSize(128, 128);
        this.SUPPORT_1_ROTATES.mirror = true;
        this.setRotation(this.SUPPORT_1_ROTATES, 0.0f, 0.0f, 0.0f);
        (this.MAIN_TURRET_ROTATES = new ModelRenderer((ModelBase) this, 75, 0))
            .addBox(-3.0f, -3.0f, -5.0f, 6, 6, 12);
        this.MAIN_TURRET_ROTATES.setRotationPoint(0.0f, 10.0f, 0.0f);
        this.MAIN_TURRET_ROTATES.setTextureSize(128, 128);
        this.MAIN_TURRET_ROTATES.mirror = true;
        this.setRotation(this.MAIN_TURRET_ROTATES, 0.0f, 0.0f, 0.0f);
        (this.BATTERY_PACK_ROTATES = new ModelRenderer((ModelBase) this, 53, 10))
            .addBox(-4.0f, -4.0f, 2.0f, 4, 4, 6);
        this.BATTERY_PACK_ROTATES.setRotationPoint(0.0f, 10.0f, 0.0f);
        this.BATTERY_PACK_ROTATES.setTextureSize(128, 128);
        this.BATTERY_PACK_ROTATES.mirror = true;
        this.setRotation(this.BATTERY_PACK_ROTATES, 0.0f, 0.0f, 0.0f);
        (this.MAIN_CANNON_ROTATES = new ModelRenderer((ModelBase) this, 41, 22))
            .addBox(-1.0f, -2.0f, -15.0f, 2, 2, 10);
        this.MAIN_CANNON_ROTATES.setRotationPoint(0.0f, 10.0f, 0.0f);
        this.MAIN_CANNON_ROTATES.setTextureSize(128, 128);
        this.MAIN_CANNON_ROTATES.mirror = true;
        this.setRotation(this.MAIN_CANNON_ROTATES, 0.0f, 0.0f, 0.0f);
        (this.NOZZLE_ROTATES = new ModelRenderer((ModelBase) this, 66, 22))
            .addBox(-1.0f, -2.0f, -19.0f, 2, 3, 4);
        this.NOZZLE_ROTATES.setRotationPoint(0.0f, 10.0f, 0.0f);
        this.NOZZLE_ROTATES.setTextureSize(128, 128);
        this.NOZZLE_ROTATES.mirror = true;
        this.setRotation(this.NOZZLE_ROTATES, 0.0f, 0.0f, 0.0f);
        (this.SUPPORT_2_ROTATES = new ModelRenderer((ModelBase) this, 41, 10))
            .addBox(-4.0f, 0.0f, -2.0f, 1, 7, 4);
        this.SUPPORT_2_ROTATES.setRotationPoint(0.0f, 10.0f, 0.0f);
        this.SUPPORT_2_ROTATES.setTextureSize(128, 128);
        this.SUPPORT_2_ROTATES.mirror = true;
        this.setRotation(this.SUPPORT_2_ROTATES, 0.0f, 0.0f, 0.0f);
        (this.SUPPORT_PLATFORM_ROTATES = new ModelRenderer((ModelBase) this, 41, 0))
            .addBox(-4.0f, 0.0f, -4.0f, 8, 1, 8);
        this.SUPPORT_PLATFORM_ROTATES.setRotationPoint(0.0f, 17.0f, 0.0f);
        this.SUPPORT_PLATFORM_ROTATES.setTextureSize(128, 128);
        this.SUPPORT_PLATFORM_ROTATES.mirror = true;
        this.setRotation(this.SUPPORT_PLATFORM_ROTATES, 0.0f, 0.0f, 0.0f);
    }

    public void
    render(final float rotationYaw, final float rotationPitch, final float f5) {
        this.BASE.render(f5);
        this.NECK.render(f5);
        this.SUPPORT_1_ROTATES.rotateAngleY = rotationYaw;
        this.SUPPORT_1_ROTATES.render(f5);
        this.SUPPORT_2_ROTATES.rotateAngleY = rotationYaw;
        this.SUPPORT_2_ROTATES.render(f5);
        this.SUPPORT_PLATFORM_ROTATES.rotateAngleY = rotationYaw;
        this.SUPPORT_PLATFORM_ROTATES.render(f5);
        this.MAIN_TURRET_ROTATES.rotateAngleY = rotationYaw;
        this.MAIN_TURRET_ROTATES.rotateAngleX = rotationPitch;
        this.MAIN_TURRET_ROTATES.render(f5);
        this.BATTERY_PACK_ROTATES.rotateAngleY = rotationYaw;
        this.BATTERY_PACK_ROTATES.rotateAngleX = rotationPitch;
        this.BATTERY_PACK_ROTATES.render(f5);
        this.MAIN_CANNON_ROTATES.rotateAngleY = rotationYaw;
        this.MAIN_CANNON_ROTATES.rotateAngleX = rotationPitch;
        this.MAIN_CANNON_ROTATES.render(f5);
        this.NOZZLE_ROTATES.rotateAngleY = rotationYaw;
        this.NOZZLE_ROTATES.rotateAngleX = rotationPitch;
        this.NOZZLE_ROTATES.render(f5);
    }

    private void
    setRotation(final ModelRenderer model, final float x, final float y, final float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
}
