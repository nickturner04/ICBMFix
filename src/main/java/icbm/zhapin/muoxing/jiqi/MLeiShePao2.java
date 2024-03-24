package icbm.zhapin.muoxing.jiqi;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

@SideOnly(Side.CLIENT)
public class MLeiShePao2 extends ModelBase {
    ModelRenderer Main_Turret_MOVES;
    ModelRenderer Back_Armor_MOVES;
    ModelRenderer Armor_1_MOVES;
    ModelRenderer Armor_2_MOVES;
    ModelRenderer Armor_3_MOVES;
    ModelRenderer Armor_4_MOVES;
    ModelRenderer Armor_5_MOVES;
    ModelRenderer Armor_6_MOVES;
    ModelRenderer Front_Armor_1_MOVES;
    ModelRenderer Front_Armor_2_MOVES;
    ModelRenderer Cannon_MOVES;

    public MLeiShePao2() {
        super.textureWidth = 128;
        super.textureHeight = 128;
        (this.Main_Turret_MOVES = new ModelRenderer((ModelBase) this, 60, 10))
            .addBox(-5.0f, 0.0f, -5.0f, 10, 4, 14);
        this.Main_Turret_MOVES.setRotationPoint(0.0f, -5.0f, 0.0f);
        this.Main_Turret_MOVES.setTextureSize(128, 128);
        this.Main_Turret_MOVES.mirror = true;
        this.setRotation(this.Main_Turret_MOVES, 0.0f, 0.0f, 0.0f);
        (this.Back_Armor_MOVES = new ModelRenderer((ModelBase) this, 60, 0))
            .addBox(-5.0f, 5.0f, 5.0f, 10, 3, 3);
        this.Back_Armor_MOVES.setRotationPoint(0.0f, -3.0f, 0.0f);
        this.Back_Armor_MOVES.setTextureSize(128, 128);
        this.Back_Armor_MOVES.mirror = true;
        this.setRotation(this.Back_Armor_MOVES, 0.7853982f, 0.0f, 0.0f);
        (this.Armor_1_MOVES = new ModelRenderer((ModelBase) this, 90, 0))
            .addBox(3.0f, 0.0f, 0.0f, 4, 4, 3);
        this.Armor_1_MOVES.setRotationPoint(0.0f, -5.0f, 0.0f);
        this.Armor_1_MOVES.setTextureSize(128, 128);
        this.Armor_1_MOVES.mirror = true;
        this.setRotation(this.Armor_1_MOVES, 0.0f, 0.7504916f, 0.0f);
        (this.Armor_2_MOVES = new ModelRenderer((ModelBase) this, 60, 30))
            .addBox(4.0f, 0.0f, -2.0f, 4, 4, 8);
        this.Armor_2_MOVES.setRotationPoint(0.0f, -5.0f, 0.0f);
        this.Armor_2_MOVES.setTextureSize(128, 128);
        this.Armor_2_MOVES.mirror = true;
        this.setRotation(this.Armor_2_MOVES, 0.0f, -0.4363323f, 0.0f);
        (this.Armor_3_MOVES = new ModelRenderer((ModelBase) this, 90, 30))
            .addBox(4.0f, 0.0f, -1.0f, 4, 4, 4);
        this.Armor_3_MOVES.setRotationPoint(0.0f, -5.0f, 0.0f);
        this.Armor_3_MOVES.setTextureSize(128, 128);
        this.Armor_3_MOVES.mirror = true;
        this.setRotation(this.Armor_3_MOVES, 0.0f, 0.1919862f, 0.0f);
        (this.Armor_4_MOVES = new ModelRenderer((ModelBase) this, 90, 0))
            .addBox(3.0f, 0.0f, -3.0f, 4, 4, 3);
        this.Armor_4_MOVES.setRotationPoint(0.0f, -5.0f, 0.0f);
        this.Armor_4_MOVES.setTextureSize(128, 128);
        this.Armor_4_MOVES.mirror = true;
        this.setRotation(this.Armor_4_MOVES, 0.0f, 2.391101f, 0.0f);
        (this.Armor_5_MOVES = new ModelRenderer((ModelBase) this, 90, 30))
            .addBox(-1.0f, 0.0f, 4.0f, 4, 4, 4);
        this.Armor_5_MOVES.setRotationPoint(0.0f, -5.0f, 0.0f);
        this.Armor_5_MOVES.setTextureSize(128, 128);
        this.Armor_5_MOVES.mirror = true;
        this.setRotation(this.Armor_5_MOVES, 0.0f, -1.780236f, 0.0f);
        (this.Armor_6_MOVES = new ModelRenderer((ModelBase) this, 60, 30))
            .addBox(-8.0f, 0.0f, -2.0f, 4, 4, 8);
        this.Armor_6_MOVES.setRotationPoint(0.0f, -5.0f, 0.0f);
        this.Armor_6_MOVES.setTextureSize(128, 128);
        this.Armor_6_MOVES.mirror = true;
        this.setRotation(this.Armor_6_MOVES, 0.0f, 0.4363323f, 0.0f);
        (this.Front_Armor_1_MOVES = new ModelRenderer((ModelBase) this, 110, 0))
            .addBox(-5.0f, 3.0f, -4.0f, 4, 4, 2);
        this.Front_Armor_1_MOVES.setRotationPoint(0.0f, -5.0f, 0.0f);
        this.Front_Armor_1_MOVES.setTextureSize(128, 128);
        this.Front_Armor_1_MOVES.mirror = true;
        this.setRotation(this.Front_Armor_1_MOVES, -0.6457718f, 0.0f, 0.0f);
        (this.Front_Armor_2_MOVES = new ModelRenderer((ModelBase) this, 110, 0))
            .addBox(1.0f, 3.0f, -4.0f, 4, 4, 2);
        this.Front_Armor_2_MOVES.setRotationPoint(0.0f, -5.0f, 0.0f);
        this.Front_Armor_2_MOVES.setTextureSize(128, 128);
        this.Front_Armor_2_MOVES.mirror = true;
        this.setRotation(this.Front_Armor_2_MOVES, -0.6457718f, 0.0f, 0.0f);
        (this.Cannon_MOVES = new ModelRenderer((ModelBase) this, 60, 50))
            .addBox(-1.0f, 0.0f, -22.0f, 2, 2, 17);
        this.Cannon_MOVES.setRotationPoint(0.0f, -4.0f, 0.0f);
        this.Cannon_MOVES.setTextureSize(128, 128);
        this.Cannon_MOVES.mirror = true;
        this.setRotation(this.Cannon_MOVES, 0.0f, 0.0f, 0.0f);
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
        this.Main_Turret_MOVES.render(f5);
        this.Back_Armor_MOVES.render(f5);
        this.Armor_1_MOVES.render(f5);
        this.Armor_2_MOVES.render(f5);
        this.Armor_3_MOVES.render(f5);
        this.Armor_4_MOVES.render(f5);
        this.Armor_5_MOVES.render(f5);
        this.Armor_6_MOVES.render(f5);
        this.Front_Armor_1_MOVES.render(f5);
        this.Front_Armor_2_MOVES.render(f5);
        this.Cannon_MOVES.render(f5);
    }

    public void render(final float f5) {
        this.Main_Turret_MOVES.render(f5);
        this.Back_Armor_MOVES.render(f5);
        this.Armor_1_MOVES.render(f5);
        this.Armor_2_MOVES.render(f5);
        this.Armor_3_MOVES.render(f5);
        this.Armor_4_MOVES.render(f5);
        this.Armor_5_MOVES.render(f5);
        this.Armor_6_MOVES.render(f5);
        this.Front_Armor_1_MOVES.render(f5);
        this.Front_Armor_2_MOVES.render(f5);
        this.Cannon_MOVES.render(f5);
    }

    private void
    setRotation(final ModelRenderer model, final float x, final float y, final float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
}
