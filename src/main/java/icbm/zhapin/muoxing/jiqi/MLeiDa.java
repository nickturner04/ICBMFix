package icbm.zhapin.muoxing.jiqi;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

@SideOnly(Side.CLIENT)
public class MLeiDa extends ModelBase {
    ModelRenderer Main_Base;
    ModelRenderer Main_Base_Outer_Layer;
    ModelRenderer Pivot_Support_1;
    ModelRenderer Pivot_Support_2;
    ModelRenderer Pivot_Support_3;
    ModelRenderer Pivot_Point;
    ModelRenderer Dish_Support;
    ModelRenderer Focus_Support_1;
    ModelRenderer Focus_Support_2;
    ModelRenderer Focus_Point;
    ModelRenderer Main_Dish_Support;
    ModelRenderer Main_Dish_Piece;
    ModelRenderer Secondary_Dish_Piece_1;
    ModelRenderer Secondary_Dish_Piece_2;

    public MLeiDa() {
        super.textureWidth = 128;
        super.textureHeight = 128;
        (this.Main_Base = new ModelRenderer((ModelBase) this, 0, 29))
            .addBox(0.0f, 0.0f, 0.0f, 14, 10, 14);
        this.Main_Base.setRotationPoint(-7.0f, 14.0f, -7.0f);
        this.Main_Base.setTextureSize(128, 128);
        this.Main_Base.mirror = true;
        this.setRotation(this.Main_Base, 0.0f, 0.0f, 0.0f);
        (this.Main_Base_Outer_Layer = new ModelRenderer((ModelBase) this, 0, 0))
            .addBox(0.0f, 0.0f, 0.0f, 16, 4, 16);
        this.Main_Base_Outer_Layer.setRotationPoint(-8.0f, 20.0f, -8.0f);
        this.Main_Base_Outer_Layer.setTextureSize(128, 128);
        this.Main_Base_Outer_Layer.mirror = true;
        this.setRotation(this.Main_Base_Outer_Layer, 0.0f, 0.0f, 0.0f);
        (this.Pivot_Support_1 = new ModelRenderer((ModelBase) this, 0, 63))
            .addBox(0.0f, 0.0f, 0.0f, 12, 1, 12);
        this.Pivot_Support_1.setRotationPoint(-6.0f, 13.0f, -6.0f);
        this.Pivot_Support_1.setTextureSize(128, 128);
        this.Pivot_Support_1.mirror = true;
        this.setRotation(this.Pivot_Support_1, 0.0f, 0.0f, 0.0f);
        (this.Pivot_Support_2 = new ModelRenderer((ModelBase) this, 87, 63))
            .addBox(-2.0f, 0.0f, -1.0f, 10, 1, 10);
        this.Pivot_Support_2.setRotationPoint(-5.0f, 13.0f, 0.0f);
        this.Pivot_Support_2.setTextureSize(128, 128);
        this.Pivot_Support_2.mirror = true;
        this.setRotation(this.Pivot_Support_2, 0.0f, 0.7853982f, 0.0f);
        (this.Pivot_Support_3 = new ModelRenderer((ModelBase) this, 0, 78))
            .addBox(0.0f, 0.0f, 0.0f, 8, 2, 8);
        this.Pivot_Support_3.setRotationPoint(-4.0f, 11.0f, -4.0f);
        this.Pivot_Support_3.setTextureSize(128, 128);
        this.Pivot_Support_3.mirror = true;
        this.setRotation(this.Pivot_Support_3, 0.0f, 0.0f, 0.0f);
        (this.Pivot_Point = new ModelRenderer((ModelBase) this, 0, 89))
            .addBox(-2.0f, 0.0f, -2.0f, 4, 6, 4);
        this.Pivot_Point.setRotationPoint(0.0f, 5.0f, 0.0f);
        this.Pivot_Point.setTextureSize(128, 128);
        this.Pivot_Point.mirror = true;
        this.setRotation(this.Pivot_Point, 0.0f, 0.0f, 0.0f);
        (this.Dish_Support = new ModelRenderer((ModelBase) this, 17, 89))
            .addBox(-1.0f, 0.0f, -4.0f, 2, 2, 8);
        this.Dish_Support.setRotationPoint(0.0f, 5.0f, 0.0f);
        this.Dish_Support.setTextureSize(128, 128);
        this.Dish_Support.mirror = true;
        this.setRotation(this.Dish_Support, 0.0f, 0.0f, 0.0f);
        (this.Focus_Support_1 = new ModelRenderer((ModelBase) this, 0, 100))
            .addBox(-15.0f, 0.0f, 3.0f, 19, 2, 1);
        this.Focus_Support_1.setRotationPoint(0.0f, 5.0f, 0.0f);
        this.Focus_Support_1.setTextureSize(128, 128);
        this.Focus_Support_1.mirror = true;
        this.setRotation(this.Focus_Support_1, 0.0f, -0.2094395f, 0.0f);
        (this.Focus_Support_2 = new ModelRenderer((ModelBase) this, 0, 100))
            .addBox(-15.0f, 0.0f, -4.0f, 19, 2, 1);
        this.Focus_Support_2.setRotationPoint(0.0f, 5.0f, 0.0f);
        this.Focus_Support_2.setTextureSize(128, 128);
        this.Focus_Support_2.mirror = true;
        this.setRotation(this.Focus_Support_2, 0.0f, 0.2094395f, 0.0f);
        (this.Focus_Point = new ModelRenderer((ModelBase) this, 41, 100))
            .addBox(-16.0f, 0.0f, -1.0f, 1, 4, 2);
        this.Focus_Point.setRotationPoint(0.0f, 3.0f, 0.0f);
        this.Focus_Point.setTextureSize(128, 128);
        this.Focus_Point.mirror = true;
        this.setRotation(this.Focus_Point, 0.0f, 0.0f, 0.0f);
        (this.Main_Dish_Support = new ModelRenderer((ModelBase) this, 0, 105))
            .addBox(3.0f, 0.0f, -6.0f, 2, 2, 12);
        this.Main_Dish_Support.setRotationPoint(0.0f, 5.0f, 0.0f);
        this.Main_Dish_Support.setTextureSize(128, 128);
        this.Main_Dish_Support.mirror = true;
        this.setRotation(this.Main_Dish_Support, 0.0f, 0.0f, 0.0f);
        (this.Main_Dish_Piece = new ModelRenderer((ModelBase) this, 51, 95))
            .addBox(4.0f, 0.0f, -9.0f, 1, 15, 18);
        this.Main_Dish_Piece.setRotationPoint(0.0f, -10.0f, 0.0f);
        this.Main_Dish_Piece.setTextureSize(128, 128);
        this.Main_Dish_Piece.mirror = true;
        this.setRotation(this.Main_Dish_Piece, 0.0f, 0.0f, 0.0f);
        (this.Secondary_Dish_Piece_1 = new ModelRenderer((ModelBase) this, 51, 63))
            .addBox(8.0f, 0.0f, 4.0f, 1, 15, 16);
        this.Secondary_Dish_Piece_1.setRotationPoint(0.0f, -10.0f, 0.0f);
        this.Secondary_Dish_Piece_1.setTextureSize(128, 128);
        this.Secondary_Dish_Piece_1.mirror = true;
        this.setRotation(this.Secondary_Dish_Piece_1, 0.0f, -0.6108652f, 0.0f);
        (this.Secondary_Dish_Piece_2 = new ModelRenderer((ModelBase) this, 51, 63))
            .addBox(8.0f, 0.0f, -20.0f, 1, 15, 16);
        this.Secondary_Dish_Piece_2.setRotationPoint(0.0f, -10.0f, 0.0f);
        this.Secondary_Dish_Piece_2.setTextureSize(128, 128);
        this.Secondary_Dish_Piece_2.mirror = true;
        this.setRotation(this.Secondary_Dish_Piece_2, 0.0f, 0.6108652f, 0.0f);
    }

    public void render(final float rotation, final float f5) {
        this.Main_Base.render(f5);
        this.Main_Base_Outer_Layer.render(f5);
        this.Pivot_Support_1.render(f5);
        this.Pivot_Support_2.render(f5);
        this.Pivot_Support_3.render(f5);
        this.Pivot_Point.rotateAngleY = rotation;
        this.Pivot_Point.render(f5);
        this.Dish_Support.rotateAngleY = rotation;
        this.Dish_Support.render(f5);
        this.Focus_Support_1.rotateAngleY = rotation - 0.15f;
        this.Focus_Support_1.render(f5);
        this.Focus_Support_2.rotateAngleY = rotation + 0.15f;
        this.Focus_Support_2.render(f5);
        this.Focus_Point.rotateAngleY = rotation;
        this.Focus_Point.render(f5);
        this.Main_Dish_Support.rotateAngleY = rotation;
        this.Main_Dish_Support.render(f5);
        this.Main_Dish_Piece.rotateAngleY = rotation;
        this.Main_Dish_Piece.render(f5);
        this.Secondary_Dish_Piece_1.rotateAngleY = rotation - 0.6f;
        this.Secondary_Dish_Piece_1.render(f5);
        this.Secondary_Dish_Piece_2.rotateAngleY = rotation + 0.6f;
        this.Secondary_Dish_Piece_2.render(f5);
    }

    private void
    setRotation(final ModelRenderer model, final float x, final float y, final float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
}
