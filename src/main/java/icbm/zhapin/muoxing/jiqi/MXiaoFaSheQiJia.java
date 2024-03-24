package icbm.zhapin.muoxing.jiqi;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

@SideOnly(Side.CLIENT)
public class MXiaoFaSheQiJia extends ModelBase {
    ModelRenderer CLAMP_1_ROT;
    ModelRenderer CLAMP_2_ROT;
    ModelRenderer COUNTERBALANCE_1_ROT;
    ModelRenderer COUNTERBALANCE_2_ROT;
    ModelRenderer RAIL_1_ROT;
    ModelRenderer RAIL_2_ROT;
    ModelRenderer TORQUE_SUPPORT_ROT;

    public MXiaoFaSheQiJia() {
        super.textureWidth = 128;
        super.textureHeight = 128;
        (this.CLAMP_1_ROT = new ModelRenderer((ModelBase) this, 20, 0))
            .addBox(-1.0f, -1.0f, -17.0f, 6, 6, 1);
        this.CLAMP_1_ROT.setRotationPoint(0.0f, 13.0f, 0.0f);
        this.CLAMP_1_ROT.setTextureSize(128, 128);
        this.CLAMP_1_ROT.mirror = true;
        this.setRotation(this.CLAMP_1_ROT, 0.0f, 0.0f, -2.356194f);
        (this.CLAMP_2_ROT = new ModelRenderer((ModelBase) this, 20, 0))
            .addBox(-1.0f, -1.0f, 0.0f, 6, 6, 1);
        this.CLAMP_2_ROT.setRotationPoint(0.0f, 13.0f, 0.0f);
        this.CLAMP_2_ROT.setTextureSize(128, 128);
        this.CLAMP_2_ROT.mirror = true;
        this.setRotation(this.CLAMP_2_ROT, 0.0f, 0.0f, -2.356194f);
        (this.COUNTERBALANCE_1_ROT = new ModelRenderer((ModelBase) this, 84, 0))
            .addBox(1.0f, -4.0f, 1.0f, 4, 4, 8);
        this.COUNTERBALANCE_1_ROT.setRotationPoint(0.0f, 14.0f, 0.0f);
        this.COUNTERBALANCE_1_ROT.setTextureSize(128, 128);
        this.COUNTERBALANCE_1_ROT.mirror = true;
        this.setRotation(this.COUNTERBALANCE_1_ROT, 0.0f, 0.0f, 0.0f);
        (this.COUNTERBALANCE_2_ROT = new ModelRenderer((ModelBase) this, 84, 0))
            .addBox(-5.0f, -4.0f, 1.0f, 4, 4, 8);
        this.COUNTERBALANCE_2_ROT.setRotationPoint(0.0f, 14.0f, 0.0f);
        this.COUNTERBALANCE_2_ROT.setTextureSize(128, 128);
        this.COUNTERBALANCE_2_ROT.mirror = true;
        this.setRotation(this.COUNTERBALANCE_2_ROT, 0.0f, 0.0f, 0.0f);
        (this.RAIL_1_ROT = new ModelRenderer((ModelBase) this, 65, 13))
            .addBox(-5.0f, -1.0f, -17.0f, 4, 1, 18);
        this.RAIL_1_ROT.setRotationPoint(0.0f, 14.0f, 0.0f);
        this.RAIL_1_ROT.setTextureSize(128, 128);
        this.RAIL_1_ROT.mirror = true;
        this.setRotation(this.RAIL_1_ROT, 0.0f, 0.0f, 0.7853982f);
        (this.RAIL_2_ROT = new ModelRenderer((ModelBase) this, 65, 13))
            .addBox(-5.0f, 0.0f, -17.0f, 4, 1, 18);
        this.RAIL_2_ROT.setRotationPoint(0.0f, 14.0f, 0.0f);
        this.RAIL_2_ROT.setTextureSize(128, 128);
        this.RAIL_2_ROT.mirror = true;
        this.setRotation(this.RAIL_2_ROT, 0.0f, 0.0f, 2.356194f);
        (this.TORQUE_SUPPORT_ROT = new ModelRenderer((ModelBase) this, 47, 17))
            .addBox(-2.0f, 0.0f, -2.0f, 4, 2, 4);
        this.TORQUE_SUPPORT_ROT.setRotationPoint(0.0f, 13.0f, 0.0f);
        this.TORQUE_SUPPORT_ROT.setTextureSize(128, 128);
        this.TORQUE_SUPPORT_ROT.mirror = true;
        this.setRotation(this.TORQUE_SUPPORT_ROT, 0.0f, 0.0f, 0.0f);
    }

    public void render(final float f5) {
        this.CLAMP_1_ROT.render(f5);
        this.CLAMP_2_ROT.render(f5);
        this.COUNTERBALANCE_1_ROT.render(f5);
        this.COUNTERBALANCE_2_ROT.render(f5);
        this.RAIL_1_ROT.render(f5);
        this.RAIL_2_ROT.render(f5);
        this.TORQUE_SUPPORT_ROT.render(f5);
    }

    private void
    setRotation(final ModelRenderer model, final float x, final float y, final float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(
        final float f,
        final float f1,
        final float f2,
        final float f3,
        final float f4,
        final float f5,
        final Entity entity
    ) {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    }
}
