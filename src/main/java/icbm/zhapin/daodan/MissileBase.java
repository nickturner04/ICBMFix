package icbm.zhapin.daodan;

import icbm.api.explosion.IExplosive;
import icbm.zhapin.ICBMExplosion;
import icbm.zhapin.zhapin.ZhaPin;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import universalelectricity.core.vector.Vector3;
import universalelectricity.prefab.TranslationHelper;
import universalelectricity.prefab.implement.ITier;

public class MissileBase implements ITier, IExplosive {
    public static final MissileBase missileModule;
    public static final MissileBase homing;
    public static final MissileBase antiBallistic;
    public static final MissileBase cluster;
    public static final MissileBase nuclearCluster;
    public static final int MAX_DAO_DAN = 4;
    public static MissileBase[] list;
    private String mingZi;
    private int ID;
    private int tier;

    public MissileBase(final String name, final int ID, final int tier) {
        if (MissileBase.list == null) {
            MissileBase.list = new MissileBase[256];
        }

        if (MissileBase.list[ID] != null) {
            throw new IllegalArgumentException(
                "Missile " + ID + " is already occupied when adding " + this + "!"
            );
        }

        MissileBase.list[ID] = this;
        this.mingZi = name;
        this.tier = tier;
        this.ID = ID;
    }

    @Override
    public int getID() {
        return this.ID;
    }

    @Override
    public String getUnlocalizedName() {
        return this.mingZi;
    }

    public String getTranslatedMing() {
        return TranslationHelper.getLocal("icbm.missile." + this.mingZi);
    }

    @Override
    public int getTier() {
        return this.tier;
    }

    @Override
    public void setTier(final int tier) {}

    public void launch(final EMissile missileObj) {}

    public void update(final EMissile missileObj) {}

    public boolean
    onInteract(final EMissile missileObj, final EntityPlayer par1EntityPlayer) {
        return false;
    }

    public void onExplode(final EMissile missileObj) {
        ZhaPin.createExplosion(
            missileObj.worldObj, new Vector3(missileObj), missileObj, missileObj.missileId
        );
    }

    public ItemStack getItemStack() {
        return new ItemStack(ICBMExplosion.itDaoDan, 1, this.getID());
    }

    public boolean isCruise() {
        return true;
    }

    @Override
    public String getExplosiveName() {
        return this.getTranslatedMing();
    }

    @Override
    public String getGrenadeName() {
        return this.getTranslatedMing();
    }

    @Override
    public String getMissileName() {
        return this.getTranslatedMing();
    }

    @Override
    public String getMinecartName() {
        return this.getTranslatedMing();
    }

    @Override
    public float getRadius() {
        return 0.0f;
    }

    @Override
    public double getEnergy() {
        return 0.0;
    }

    static {
        missileModule = new DModule("missileModule", 100, 1);
        homing = new DHoming("homing", 101, 1);
        antiBallistic = new DAntiBallistic("antiBallistic", 102, 2);
        cluster = new DCluster("cluster", 103, 2);
        nuclearCluster = new DNuclearCluster("nuclearCluster", 104, 3);
    }
}
