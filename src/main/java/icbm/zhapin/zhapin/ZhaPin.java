package icbm.zhapin.zhapin;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import icbm.api.explosion.ExplosionEvent;
import icbm.api.explosion.IExplosive;
import icbm.core.HaoMa;
import icbm.core.MainBase;
import icbm.zhapin.ICBMExplosion;
import icbm.zhapin.daodan.EMissile;
import icbm.zhapin.daodan.MissileBase;
import icbm.zhapin.zhapin.ex.ExAntiGravitational;
import icbm.zhapin.zhapin.ex.ExAntimatter;
import icbm.zhapin.zhapin.ex.ExBreaching;
import icbm.zhapin.zhapin.ex.ExChemical;
import icbm.zhapin.zhapin.ex.ExCondensed;
import icbm.zhapin.zhapin.ex.ExDebilitation;
import icbm.zhapin.zhapin.ex.ExDecayLand;
import icbm.zhapin.zhapin.ex.ExEmp;
import icbm.zhapin.zhapin.ex.ExEmpSignal;
import icbm.zhapin.zhapin.ex.ExEmpWave;
import icbm.zhapin.zhapin.ex.ExEnder;
import icbm.zhapin.zhapin.ex.ExEndothermic;
import icbm.zhapin.zhapin.ex.ExEndothermic2;
import icbm.zhapin.zhapin.ex.ExExothermic;
import icbm.zhapin.zhapin.ex.ExExothermic2;
import icbm.zhapin.zhapin.ex.ExHypersonic;
import icbm.zhapin.zhapin.ex.ExIncendiary;
import icbm.zhapin.zhapin.ex.ExMutateLiving;
import icbm.zhapin.zhapin.ex.ExNuclear;
import icbm.zhapin.zhapin.ex.ExPushPull;
import icbm.zhapin.zhapin.ex.ExRedMatter;
import icbm.zhapin.zhapin.ex.ExRejuvenation;
import icbm.zhapin.zhapin.ex.ExSMine;
import icbm.zhapin.zhapin.ex.ExShrapnel;
import icbm.zhapin.zhapin.ex.ExSonic;
import icbm.zhapin.zhapin.ex.ExThermobaric;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.ForgeDirection;
import universalelectricity.core.vector.Vector3;
import universalelectricity.prefab.TranslationHelper;
import universalelectricity.prefab.flag.FlagRegistry;
import universalelectricity.prefab.implement.ITier;

public abstract class ZhaPin implements ITier, IExplosive {
    public static final ZhaPin condensed;
    public static final ZhaPin shrapnel;
    public static final ZhaPin indenciary;
    public static final ZhaPin debilitation;
    public static final ZhaPin chemical;
    public static final ZhaPin anvil;
    public static final ZhaPin repulsive;
    public static final ZhaPin attractive;
    public static final int E_YI_ID;
    public static final ZhaPin fragmentation;
    public static final ZhaPin contagious;
    public static final ZhaPin sonic;
    public static final ZhaPin breaching;
    public static final ZhaPin rejuvenation;
    public static final ZhaPin thermobaric;
    public static final int E_ER_ID;
    public static final ZhaPin nuclear;
    public static final ZhaPin emp;
    public static final ZhaPin exothermic;
    public static final ZhaPin endothermic;
    public static final ZhaPin antiGravitational;
    public static final ZhaPin ender;
    public static final ZhaPin hypersonic;
    public static final int E_SAN_ID;
    public static final ZhaPin antimatter;
    public static final ZhaPin redMatter;
    public static final int E_SI_ID;
    public static final ZhaPin sMine;
    public static final ZhaPin empWave;
    public static final ZhaPin empSignal;
    public static final ZhaPin exothermic2;
    public static final ZhaPin decayLand;
    public static final ZhaPin mutateLiving;
    public static final ZhaPin endothermic2;
    public static ZhaPin[] list;
    private String name;
    private int ID;
    private int tier;
    private int yinXin;
    public MissileBase daoDan;
    public final String qiZi;
    protected boolean isDisabled;
    protected boolean isMobile;

    protected ZhaPin(final String name, final int ID, final int tier) {
        this.isMobile = false;

        if (ZhaPin.list == null) {
            ZhaPin.list = new ZhaPin[32];
        }

        if (ZhaPin.list[ID] != null) {
            throw new IllegalArgumentException(
                "Explosive " + ID + " is already occupied by "
                + ZhaPin.list[ID].getClass().getSimpleName() + "!"
            );
        }

        ZhaPin.list[ID] = this;
        this.name = name;
        this.tier = tier;
        this.yinXin = 100;
        this.ID = ID;
        this.daoDan = new MissileBase(name, ID, tier);
        this.qiZi = FlagRegistry.registerFlag("ban_" + this.name);
        MainBase.CONFIGURATION.load();
        this.isDisabled = MainBase.CONFIGURATION
                              .get("Disable_Explosives", "Disable " + this.name, false)
                              .getBoolean(false);
        MainBase.CONFIGURATION.save();
    }

    @Override
    public int getID() {
        return this.ID;
    }

    @Override
    public String getUnlocalizedName() {
        return this.name;
    }

    @Override
    public String getExplosiveName() {
        return TranslationHelper.getLocal("icbm.explosive." + this.name + ".name");
    }

    @Override
    public String getGrenadeName() {
        return TranslationHelper.getLocal("icbm.grenade." + this.name + ".name");
    }

    @Override
    public String getMissileName() {
        return TranslationHelper.getLocal("icbm.missile." + this.name + ".name");
    }

    @Override
    public String getMinecartName() {
        return TranslationHelper.getLocal("icbm.minecart." + this.name + ".name");
    }

    @Override
    public int getTier() {
        return this.tier;
    }

    @Override
    public void setTier(final int tier) {
        this.tier = tier;
    }

    public void setFuse(final int fuse) {
        this.yinXin = fuse;
    }

    public int getYinXin() {
        return this.yinXin;
    }

    public void yinZhaQian(final World worldObj, final Entity entity) {
        worldObj.playSoundAtEntity(entity, "random.fuse", 1.0f, 1.0f);
    }

    public void
    onYinZha(final World worldObj, final Vector3 position, final int fuseTicks) {
        worldObj.spawnParticle(
            "smoke", position.x, position.y + 0.5, position.z, 0.0, 0.0, 0.0
        );
    }

    public int onBeiZha() {
        return (int) (this.yinXin / 2 + Math.random() * this.yinXin / 4.0);
    }

    protected int proceduralInterval() {
        return -1;
    }

    public int proceduralInterval(final World worldObj, final int callCounts) {
        return this.proceduralInterval();
    }

    public void baoZhaQian(
        final World worldObj, final Vector3 position, final Entity explosionSource
    ) {
        MinecraftForge.EVENT_BUS.post(new ExplosionEvent.PreExplosionEvent(
            worldObj, position.x, position.y, position.z, this
        ));
    }

    @SideOnly(Side.CLIENT)
    public Object[] getRenderData() {
        return null;
    }

    public void
    doBaoZha(final World worldObj, final Vector3 position, final Entity explosionSource) {
    }

    public boolean doBaoZha(
        final World worldObj,
        final Vector3 position,
        final Entity explosionSource,
        final int callCount
    ) {
        this.doBaoZha(worldObj, position, explosionSource);
        return false;
    }

    public boolean doBaoZha(
        final World worldObj,
        final Vector3 position,
        final Entity explosionSource,
        final int metadata,
        final int callCount
    ) {
        return this.doBaoZha(worldObj, position, explosionSource, callCount);
    }

    public void
    gengXin(final World worldObj, final Vector3 position, final int ticksExisted) {}

    public void baoZhaHou(
        final World worldObj, final Vector3 position, final Entity explosionSource
    ) {
        MinecraftForge.EVENT_BUS.post(new ExplosionEvent.PostExplosionEvent(
            worldObj, position.x, position.y, position.z, this
        ));
    }

    public int countIncrement() {
        return 1;
    }

    public void spawnZhaDan(
        final World worldObj,
        final Vector3 position,
        final ForgeDirection orientation,
        final byte cause
    ) {
        if (!this.isDisabled) {
            position.add(0.5);
            final EExplosive eZhaDan = new EExplosive(
                worldObj, position, (byte) orientation.ordinal(), this.getID()
            );

            switch (cause) {
                case 1: {
                    eZhaDan.destroyedByExplosion();
                    break;
                }

                case 2: {
                    eZhaDan.setFire(10);
                    break;
                }
            }

            worldObj.spawnEntityInWorld((Entity) eZhaDan);
        }
    }

    public void
    spawnZhaDan(final World worldObj, final Vector3 position, final byte orientation) {
        this.spawnZhaDan(
            worldObj, position, ForgeDirection.getOrientation((int) orientation), (byte) 0
        );
    }

    public void init() {}

    public ItemStack getItemStack() {
        return new ItemStack(ICBMExplosion.bExplosives, 1, this.getID());
    }

    public ItemStack getItemStack(final int amount) {
        return new ItemStack(ICBMExplosion.bExplosives, amount, this.getID());
    }

    public static IExplosive getExplosiveByName(final String name) {
        for (final IExplosive explosive : ZhaPin.list) {
            if (explosive.getUnlocalizedName().equalsIgnoreCase(name)) {
                return explosive;
            }
        }

        return null;
    }

    public static void createExplosion(
        final World worldObj,
        final Double x,
        final Double y,
        final Double z,
        final Entity entity,
        final Integer explosiveID
    ) {
        createExplosion(worldObj, new Vector3(x, y, z), entity, explosiveID);
    }

    public static void createExplosion(
        final World worldObj,
        final Vector3 position,
        final Entity entity,
        final int explosiveID
    ) {
        if (!ZhaPin.list[explosiveID].isDisabled) {
            if (ZhaPin.list[explosiveID].proceduralInterval(worldObj, -1) > 0) {
                if (!worldObj.isRemote) {
                    worldObj.spawnEntityInWorld((Entity) new EExplosion(
                        worldObj,
                        position.clone(),
                        explosiveID,
                        ZhaPin.list[explosiveID].isMobile
                    ));
                }
            } else {
                ZhaPin.list[explosiveID].baoZhaQian(worldObj, position.clone(), entity);
                ZhaPin.list[explosiveID].doBaoZha(
                    worldObj, position.clone(), entity, explosiveID, -1
                );
                ZhaPin.list[explosiveID].baoZhaHou(worldObj, position.clone(), entity);
            }
        }
    }

    public void doDamageEntities(
        final World worldObj,
        final Vector3 position,
        final float radius,
        final float power
    ) {
        this.doDamageEntities(worldObj, position, radius, power, true);
    }

    public void doDamageEntities(
        final World worldObj,
        final Vector3 position,
        float radius,
        final float power,
        final boolean destroyItem
    ) {
        radius *= 2.0f;
        final Vector3 minCoord = position.clone();
        minCoord.add(-radius - 1.0f);
        final Vector3 maxCoord = position.clone();
        maxCoord.add(radius + 1.0f);
        final List<Entity> allEntities = worldObj.getEntitiesWithinAABB(
            Entity.class,
            AxisAlignedBB.getBoundingBox(
                (double) minCoord.intX(),
                (double) minCoord.intY(),
                (double) minCoord.intZ(),
                (double) maxCoord.intX(),
                (double) maxCoord.intY(),
                (double) maxCoord.intZ()
            )
        );
        final Vec3 var31 = Vec3.createVectorHelper(position.x, position.y, position.z);

        for (int i = 0; i < allEntities.size(); ++i) {
            final Entity entity = allEntities.get(i);

            if (!this.onDamageEntity(entity)) {
                if (entity instanceof EMissile) {
                    ((EMissile) entity).setExplode();
                } else if (!(entity instanceof EntityItem) || destroyItem) {
                    final double distance
                        = entity.getDistance(position.x, position.y, position.z) / radius;

                    if (distance <= 1.0) {
                        double xDifference = entity.posX - position.x;
                        double yDifference = entity.posY - position.y;
                        double zDifference = entity.posZ - position.z;
                        final double var32 = MathHelper.sqrt_double(
                            xDifference * xDifference + yDifference * yDifference
                            + zDifference * zDifference
                        );
                        xDifference /= var32;
                        yDifference /= var32;
                        zDifference /= var32;
                        final double var33
                            = worldObj.getBlockDensity(var31, entity.boundingBox);
                        final double var34 = (1.0 - distance) * var33;
                        int damage = 0;
                        damage
                            = (int) ((var34 * var34 + var34) / 2.0 * 8.0 * power + 1.0);
                        entity.attackEntityFrom(
                            DamageSource.setExplosionSource((Explosion) null), damage
                        );
                        final Entity entity2 = entity;
                        entity2.motionX += xDifference * var34;
                        final Entity entity3 = entity;
                        entity3.motionY += yDifference * var34;
                        final Entity entity4 = entity;
                        entity4.motionZ += zDifference * var34;
                    }
                }
            }
        }
    }

    protected boolean onDamageEntity(final Entity entity) {
        return false;
    }

    static {
        condensed
            = new ExCondensed("condensed", HaoMa.getID(ZhaPin.class.getSimpleName()), 1);
        shrapnel
            = new ExShrapnel("shrapnel", HaoMa.getID(ZhaPin.class.getSimpleName()), 1);
        indenciary = new ExIncendiary(
            "incendiary", HaoMa.getID(ZhaPin.class.getSimpleName()), 1
        );
        debilitation = new ExDebilitation(
            "debilitation", HaoMa.getID(ZhaPin.class.getSimpleName()), 1
        );
        chemical
            = new ExChemical("chemical", HaoMa.getID(ZhaPin.class.getSimpleName()), 1);
        anvil = new ExShrapnel("anvil", HaoMa.getID(ZhaPin.class.getSimpleName()), 1);
        repulsive
            = new ExPushPull("repulsive", HaoMa.getID(ZhaPin.class.getSimpleName()), 1);
        attractive
            = new ExPushPull("attractive", HaoMa.getID(ZhaPin.class.getSimpleName()), 1);
        E_YI_ID = ZhaPin.attractive.getID() + 1;
        fragmentation = new ExShrapnel(
            "fragmentation", HaoMa.getID(ZhaPin.class.getSimpleName()), 2
        );
        contagious
            = new ExChemical("contagious", HaoMa.getID(ZhaPin.class.getSimpleName()), 2);
        sonic = new ExSonic("sonic", HaoMa.getID(ZhaPin.class.getSimpleName()), 2);
        breaching
            = new ExBreaching("breaching", HaoMa.getID(ZhaPin.class.getSimpleName()), 2);
        rejuvenation = new ExRejuvenation(
            "rejuvenation", HaoMa.getID(ZhaPin.class.getSimpleName()), 2
        );
        thermobaric = new ExThermobaric(
            "thermobaric", HaoMa.getID(ZhaPin.class.getSimpleName()), 2
        );
        E_ER_ID = ZhaPin.thermobaric.getID() + 1;
        nuclear = new ExNuclear("nuclear", HaoMa.getID(ZhaPin.class.getSimpleName()), 3);
        emp = new ExEmp("emp", HaoMa.getID(ZhaPin.class.getSimpleName()), 3);
        exothermic = new ExExothermic(
            "exothermic", HaoMa.getID(ZhaPin.class.getSimpleName()), 3
        );
        endothermic = new ExEndothermic(
            "endothermic", HaoMa.getID(ZhaPin.class.getSimpleName()), 3
        );
        antiGravitational = new ExAntiGravitational(
            "antiGravitational", HaoMa.getID(ZhaPin.class.getSimpleName()), 3
        );
        ender = new ExEnder("ender", HaoMa.getID(ZhaPin.class.getSimpleName()), 3);
        hypersonic = new ExHypersonic(
            "hypersonic", HaoMa.getID(ZhaPin.class.getSimpleName()), 3
        );
        E_SAN_ID = ZhaPin.hypersonic.getID() + 1;
        antimatter = new ExAntimatter(
            "antimatter", HaoMa.getID(ZhaPin.class.getSimpleName()), 4
        );
        redMatter
            = new ExRedMatter("redMatter", HaoMa.getID(ZhaPin.class.getSimpleName()), 4);
        E_SI_ID = ZhaPin.redMatter.getID() + 1;
        sMine = new ExSMine("sMine", HaoMa.getID(ZhaPin.class.getSimpleName()), 2);
        empWave = new ExEmpWave("emp", HaoMa.getID(ZhaPin.class.getSimpleName()), 3);
        empSignal = new ExEmpSignal("emp", HaoMa.getID(ZhaPin.class.getSimpleName()), 3);
        exothermic2 = new ExExothermic2(
            "exothermic", HaoMa.getID(ZhaPin.class.getSimpleName()), 3
        );
        decayLand
            = new ExDecayLand("decayLand", HaoMa.getID(ZhaPin.class.getSimpleName()), 3);
        mutateLiving = new ExMutateLiving(
            "mutateLiving", HaoMa.getID(ZhaPin.class.getSimpleName()), 3
        );
        endothermic2 = new ExEndothermic2(
            "endothermic", HaoMa.getID(ZhaPin.class.getSimpleName()), 3
        );
    }

    public enum ZhaPinType {
        QUAN_BU("QUAN_BU", 0),
        ZHA_DAN("ZHA_DAN", 1),
        SHOU_LIU_DAN("SHOU_LIU_DAN", 2),
        DAO_DAN("DAO_DAN", 3),
        CHE("CHE", 4);

        private ZhaPinType(final String name, final int ordinal) {}

        public static ZhaPinType get(final int id) {
            if (id >= 0 && id < values().length) {
                return values()[id];
            }

            return null;
        }
    }
}
