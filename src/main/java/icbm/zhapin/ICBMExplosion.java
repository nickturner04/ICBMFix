package icbm.zhapin;

import java.util.List;

import basiccomponents.common.BasicComponents;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import icbm.api.ICBM;
import icbm.api.ICBMFlags;
import icbm.core.ICBMTab;
import icbm.core.MainBase;
import icbm.zhapin.cart.ECart;
import icbm.zhapin.cart.ItCart;
import icbm.zhapin.daodan.EMissile;
import icbm.zhapin.daodan.ItMissile;
import icbm.zhapin.daodan.ItModuleMissile;
import icbm.zhapin.daodan.MissileBase;
import icbm.zhapin.dianqi.ItDefuser;
import icbm.zhapin.dianqi.ItLaserDesignator;
import icbm.zhapin.dianqi.ItRadarGun;
import icbm.zhapin.dianqi.ItRemoteDetonator;
import icbm.zhapin.dianqi.ItRocketLauncher;
import icbm.zhapin.jiqi.BMachine;
import icbm.zhapin.jiqi.CruiseLauncherGuiPacket;
import icbm.zhapin.jiqi.CruiseLauncherGuiPacketHandler;
import icbm.zhapin.jiqi.EmpTowerGuiPacket;
import icbm.zhapin.jiqi.EmpTowerGuiPacketHandler;
import icbm.zhapin.jiqi.IBMachine;
import icbm.zhapin.jiqi.LauncherControlPanelGuiPacket;
import icbm.zhapin.jiqi.LauncherControlPanelGuiPacketHandler;
import icbm.zhapin.jiqi.RadarTowerGuiPacket;
import icbm.zhapin.jiqi.RadarTowerGuiPacketHandler;
import icbm.zhapin.po.PChuanRanDu;
import icbm.zhapin.po.PDaDu;
import icbm.zhapin.po.PDongShang;
import icbm.zhapin.zhapin.BExplosives;
import icbm.zhapin.zhapin.EExplosion;
import icbm.zhapin.zhapin.EExplosive;
import icbm.zhapin.zhapin.EGrenade;
import icbm.zhapin.zhapin.IBExplosive;
import icbm.zhapin.zhapin.ItGrenade;
import icbm.zhapin.zhapin.ZhaPin;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDispenser;
import net.minecraft.block.BlockRailBase;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandManager;
import net.minecraft.command.ServerCommandManager;
import net.minecraft.dispenser.BehaviorDefaultDispenseItem;
import net.minecraft.dispenser.IBehaviorDispenseItem;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeChunkManager;
import net.minecraftforge.common.ForgeChunkManager.Ticket;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import universalelectricity.core.item.ElectricItemHelper;
import universalelectricity.core.item.ItemElectric;
import universalelectricity.core.vector.Vector3;
import universalelectricity.prefab.RecipeHelper;
import universalelectricity.prefab.flag.FlagRegistry;

@Mod(
    modid = "ICBM|Explosion",
    name = "ICBM|Explosion",
    version = MainBase.VERSION,
    dependencies = "after:ICBM|Sentry;required-after:AtomicScience",
    useMetadata = true
)
public class ICBMExplosion extends MainBase {
    public static final String NAME = "ICBM|Explosion";
    public static final String CHANNEL = "ICBM|E";
    @Mod.Instance("ICBM|Explosion")
    public static ICBMExplosion instance;
    @Mod.Metadata("ICBM|Explosion")
    public static ModMetadata metadata;
    @SidedProxy(
        clientSide = "icbm.zhapin.ClientProxy", serverSide = "icbm.zhapin.CommonProxy"
    )
    public static CommonProxy proxy;
    public static Item Du;
    public static final int ENTITY_ID_PREFIX = 50;
    public static Block bExplosives;
    public static Block bMachine;
    public static Item itDaoDan;
    public static Item itTeBieDaoDan;
    public static ItemElectric itJieJa;
    public static ItemElectric itLeiDaQiang;
    public static ItemElectric itYaoKong;
    public static ItemElectric itLeiSheZhiBiao;
    public static ItemElectric itFaSheQi;
    public static Item itShouLiuDan;
    public static Item itChe;
    public static final Du DU_DU;
    public static final Du DU_CHUAN_RAN;
    public static boolean USE_FUEL;
    public static SimpleNetworkWrapper channel;

    @Mod.EventHandler
    @Override
    public void preInit(final FMLPreInitializationEvent event) {
        super.preInit(event);
        NetworkRegistry.INSTANCE.registerGuiHandler(
            (Object) this, (IGuiHandler) ICBMExplosion.proxy
        );
        MainBase.CONFIGURATION.load();
        ICBMExplosion.USE_FUEL
            = MainBase.CONFIGURATION.get("general", "Use Fuel", ICBMExplosion.USE_FUEL)
                  .getBoolean(ICBMExplosion.USE_FUEL);
        ICBMExplosion.bExplosives = (Block) new BExplosives();
        ICBMExplosion.bMachine = (Block) new BMachine();
        ICBMExplosion.itDaoDan = new ItMissile("missile");
        ICBMExplosion.itTeBieDaoDan = new ItModuleMissile();
        ICBMExplosion.itJieJa = new ItDefuser();
        ICBMExplosion.itLeiDaQiang = new ItRadarGun();
        ICBMExplosion.itYaoKong = new ItRemoteDetonator();
        ICBMExplosion.itLeiSheZhiBiao = new ItLaserDesignator();
        ICBMExplosion.itFaSheQi = new ItRocketLauncher();
        ICBMExplosion.itShouLiuDan = new ItGrenade();
        ICBMExplosion.itChe = new ItCart();
        PDaDu.INSTANCE = new PDaDu(22, true, 5149489, "toxin");
        PChuanRanDu.INSTANCE = new PChuanRanDu(23, false, 5149489, "virus");
        PDongShang.INSTANCE = new PDongShang(24, false, 5149489, "frostBite");
        MainBase.CONFIGURATION.save();
        BlockDispenser.dispenseBehaviorRegistry.putObject(
            (Object) ICBMExplosion.itShouLiuDan,
            (Object) new IBehaviorDispenseItem() {
                public ItemStack dispense(
                    final IBlockSource blockSource, final ItemStack itemStack
                ) {
                    final World world = blockSource.getWorld();

                    if (!world.isRemote) {
                        final int x = blockSource.getXInt();
                        final int y = blockSource.getYInt();
                        final int z = blockSource.getZInt();
                        final EnumFacing enumFacing
                            = EnumFacing.getFront(blockSource.getBlockMetadata());
                        final EGrenade entity = new EGrenade(
                            world, new Vector3(x, y, z), itemStack.getItemDamage()
                        );
                        entity.setThrowableHeading(
                            enumFacing.getFrontOffsetX(),
                            0.10000000149011612,
                            enumFacing.getFrontOffsetZ(),
                            0.5f,
                            1.0f
                        );
                        world.spawnEntityInWorld((Entity) entity);
                    }

                    --itemStack.stackSize;
                    return itemStack;
                }
            }
        );
        BlockDispenser.dispenseBehaviorRegistry.putObject(
            (Object) ICBMExplosion.itChe,
            (Object) new IBehaviorDispenseItem() {
                private final BehaviorDefaultDispenseItem defaultItemDispenseBehavior
                    = new BehaviorDefaultDispenseItem();

                public ItemStack dispense(
                    final IBlockSource blockSource, final ItemStack itemStack
                ) {
                    final World world = blockSource.getWorld();

                    if (!world.isRemote) {
                        final int x = blockSource.getXInt();
                        final int y = blockSource.getYInt();
                        final int z = blockSource.getZInt();
                        final EnumFacing var3
                            = EnumFacing.getFront(blockSource.getBlockMetadata());
                        final World var4 = blockSource.getWorld();
                        final double var5
                            = blockSource.getX() + var3.getFrontOffsetX() * 1.125f;
                        final double var6 = blockSource.getY();
                        final double var7
                            = blockSource.getZ() + var3.getFrontOffsetZ() * 1.125f;
                        final int var8 = blockSource.getXInt() + var3.getFrontOffsetX();
                        final int var9 = blockSource.getYInt();
                        final int var10 = blockSource.getZInt() + var3.getFrontOffsetZ();
                        final Block var11 = var4.getBlock(var8, var9, var10);
                        double var12;

                        if (BlockRailBase.func_150051_a(var11)) {
                            var12 = 0.0;
                        } else {
                            if (var11 != Blocks.air
                                || !BlockRailBase.func_150051_a(
                                    var4.getBlock(var8, var9 - 1, var10)
                                )) {
                                return this.defaultItemDispenseBehavior.dispense(
                                    blockSource, itemStack
                                );
                            }

                            var12 = -1.0;
                        }

                        final ECart var13 = new ECart(
                            world, var5, var6 + var12, var7, itemStack.getItemDamage()
                        );
                        world.spawnEntityInWorld((Entity) var13);
                        world.playAuxSFX(1000, x, y, z, 0);
                    }

                    --itemStack.stackSize;
                    return itemStack;
                }
            }
        );
        GameRegistry.registerBlock(
            ICBMExplosion.bExplosives, IBExplosive.class, "bExplosives"
        );
        GameRegistry.registerBlock(ICBMExplosion.bMachine, IBMachine.class, "bMachine");
        ForgeChunkManager.setForcedChunkLoadingCallback(
            (Object) this,
            (ForgeChunkManager.LoadingCallback) new ForgeChunkManager.LoadingCallback() {
                public void ticketsLoaded(final List<Ticket> tickets, final World world) {
                    for (final ForgeChunkManager.Ticket ticket : tickets) {
                        if (ticket.getEntity() != null) {
                            ((EMissile) ticket.getEntity()).daoDanInit(ticket);
                        }
                    }
                }
            }
        );

        GameRegistry.registerItem(itDaoDan, "icbm:itDaoDan");
        GameRegistry.registerItem(itTeBieDaoDan, "icbm:itTeBieDaoDan");
        GameRegistry.registerItem(itJieJa, "icbm:itJieJa");
        GameRegistry.registerItem(itLeiDaQiang, "icbm:itLeiDaQiang");
        GameRegistry.registerItem(itYaoKong, "icbm:itYaoKong");
        GameRegistry.registerItem(itLeiSheZhiBiao, "icbm:itLeiSheZhiBiao");
        GameRegistry.registerItem(itFaSheQi, "icbm:itFaSheQi");
        GameRegistry.registerItem(itShouLiuDan, "icbm:itShouLiuDan");
        GameRegistry.registerItem(itChe, "icbm:itChe");

        ICBMTab.itemStack = new ItemStack(ICBMExplosion.bExplosives);

        ICBM.explosionManager = ZhaPin.class;
        ICBMExplosion.proxy.preInit();

        channel = NetworkRegistry.INSTANCE.newSimpleChannel("icbm_explosion");
        int pktId = 0;
        channel.registerMessage(
            ItemUsePacketHandler.class, ItemUsePacket.class, pktId++, Side.SERVER
        );
        channel.registerMessage(
            EmpTowerGuiPacketHandler.class, EmpTowerGuiPacket.class, pktId++, Side.SERVER
        );
        channel.registerMessage(
            LauncherControlPanelGuiPacketHandler.class,
            LauncherControlPanelGuiPacket.class,
            pktId++,
            Side.SERVER
        );
        channel.registerMessage(
            CruiseLauncherGuiPacketHandler.class,
            CruiseLauncherGuiPacket.class,
            pktId++,
            Side.SERVER
        );
        channel.registerMessage(
            RadarTowerGuiPacketHandler.class,
            RadarTowerGuiPacket.class,
            pktId++,
            Side.SERVER
        );
    }

    @Mod.EventHandler
    public void load(final FMLInitializationEvent evt) {
        MainBase.setModMetadata("ICBM|Explosion", ICBMExplosion.metadata);
    }

    @Mod.EventHandler
    @Override
    public void postInit(final FMLPostInitializationEvent event) {
        super.postInit(event);

        for (int i = 0; i < ZhaPin.list.length; ++i) {
            if (ZhaPin.list[i] != null) {
                ZhaPin.list[i].init();
            }
        }

        GameRegistry.addRecipe((IRecipe) new ShapedOreRecipe(
            (Item) ICBMExplosion.itFaSheQi,
            new Object[] {
                "SCR",
                "SB ",
                'R',
                ICBMExplosion.itLeiDaQiang,
                'C',
                new ItemStack(
                    ICBMExplosion.bMachine, 1, BMachine.JiQi.XiaoFaSheQi.ordinal() + 6
                ),
                'B',
                Blocks.stone_button,
                'S',
                "ingotSteel" }
        ));
        GameRegistry.addRecipe((IRecipe) new ShapedOreRecipe(
            new ItemStack((Item) ICBMExplosion.itLeiDaQiang),
            new Object[] { "@#!",
                           " $!",
                           "  !",
                           '@',
                           Blocks.glass,
                           '!',
                           "ingotSteel",
                           '#',
                           "circuitBasic",
                           '$',
                           Blocks.stone_button }
        ));
        GameRegistry.addRecipe((IRecipe) new ShapedOreRecipe(
            new ItemStack((Item) ICBMExplosion.itYaoKong),
            new Object[] { "?@@",
                           "@#$",
                           "@@@",
                           '@',
                           "ingotSteel",
                           '?',
                           Items.redstone,
                           '#',
                           "circuitAdvanced",
                           '$',
                           Blocks.stone_button }
        ));
        GameRegistry.addRecipe((IRecipe) new ShapedOreRecipe(
            new ItemStack((Item) ICBMExplosion.itLeiSheZhiBiao),
            new Object[] { "!  ",
                           " ? ",
                           "  @",
                           '@',
                           ElectricItemHelper.getUncharged(ICBMExplosion.itYaoKong),
                           '?',
                           "circuitElite",
                           '!',
                           ElectricItemHelper.getUncharged(ICBMExplosion.itLeiDaQiang) }
        ));
        GameRegistry.addRecipe((IRecipe) new ShapedOreRecipe(
            new ItemStack((Item) ICBMExplosion.itJieJa),
            new Object[] { "I  ",
                           " W ",
                           "  C",
                           'C',
                           "circuitAdvanced",
                           'W',
                           BasicComponents.itemWrench,
                           'I',
                           "copperWire" }
        ));
        GameRegistry.addRecipe((IRecipe) new ShapedOreRecipe(
            new ItemStack(ICBMExplosion.bMachine, 1, 0),
            new Object[] {
                "! !", "!C!", "!!!", '!', "ingotBronze", 'C', "circuitBasic" }
        ));
        GameRegistry.addRecipe((IRecipe) new ShapedOreRecipe(
            new ItemStack(ICBMExplosion.bMachine, 1, 1),
            new Object[] { "! !",
                           "!C!",
                           "!@!",
                           '@',
                           new ItemStack(ICBMExplosion.bMachine, 1, 0),
                           '!',
                           "ingotSteel",
                           'C',
                           "circuitAdvanced" }
        ));
        GameRegistry.addRecipe((IRecipe) new ShapedOreRecipe(
            new ItemStack(ICBMExplosion.bMachine, 1, 2),
            new Object[] { "! !",
                           "!C!",
                           "!@!",
                           '@',
                           new ItemStack(ICBMExplosion.bMachine, 1, 1),
                           '!',
                           "plateSteel",
                           'C',
                           "circuitElite" }
        ));
        GameRegistry.addRecipe((IRecipe) new ShapedOreRecipe(
            new ItemStack(ICBMExplosion.bMachine, 1, 3),
            new Object[] { "!!!",
                           "!#!",
                           "!?!",
                           '#',
                           "circuitBasic",
                           '!',
                           Blocks.glass,
                           '?',
                           "copperWire" }
        ));
        GameRegistry.addRecipe((IRecipe) new ShapedOreRecipe(
            new ItemStack(ICBMExplosion.bMachine, 1, 4),
            new Object[] { "!$!",
                           "!#!",
                           "!?!",
                           '#',
                           "circuitAdvanced",
                           '!',
                           "ingotSteel",
                           '?',
                           "copperWire",
                           '$',
                           new ItemStack(ICBMExplosion.bMachine, 1, 3) }
        ));
        GameRegistry.addRecipe((IRecipe) new ShapedOreRecipe(
            new ItemStack(ICBMExplosion.bMachine, 1, 5),
            new Object[] { "!$!",
                           "!#!",
                           "!?!",
                           '#',
                           "circuitElite",
                           '!',
                           Items.gold_ingot,
                           '?',
                           "copperWire",
                           '$',
                           new ItemStack(ICBMExplosion.bMachine, 1, 4) }
        ));
        GameRegistry.addRecipe((IRecipe) new ShapedOreRecipe(
            new ItemStack(ICBMExplosion.bMachine, 1, 6),
            new Object[] { "! !", "!!!", "! !", '!', "ingotBronze" }
        ));
        GameRegistry.addRecipe((IRecipe) new ShapedOreRecipe(
            new ItemStack(ICBMExplosion.bMachine, 1, 7),
            new Object[] { "! !",
                           "!@!",
                           "! !",
                           '!',
                           "ingotSteel",
                           '@',
                           new ItemStack(ICBMExplosion.bMachine, 1, 6) }
        ));
        GameRegistry.addRecipe((IRecipe) new ShapedOreRecipe(
            new ItemStack(ICBMExplosion.bMachine, 1, 8),
            new Object[] { "! !",
                           "!@!",
                           "! !",
                           '!',
                           "plateSteel",
                           '@',
                           new ItemStack(ICBMExplosion.bMachine, 1, 7) }
        ));
        GameRegistry.addRecipe((IRecipe) new ShapedOreRecipe(
            new ItemStack(ICBMExplosion.bMachine, 1, 9),
            new Object[] { "?@?",
                           " ! ",
                           "!#!",
                           '@',
                           ElectricItemHelper.getUncharged(ICBMExplosion.itLeiDaQiang),
                           '!',
                           "plateSteel",
                           '#',
                           "circuitBasic",
                           '?',
                           Items.gold_ingot }
        ));
        RecipeHelper.addRecipe(
            (IRecipe) new ShapedOreRecipe(
                new ItemStack(ICBMExplosion.bMachine, 1, 10),
                new Object[] { "?W?",
                               "@!@",
                               "?#?",
                               '?',
                               "plateSteel",
                               '!',
                               "circuitElite",
                               '@',
                               "batteryBox",
                               '#',
                               "motor",
                               'W',
                               "copperWire" }
            ),
            "EMP Tower",
            MainBase.CONFIGURATION,
            true
        );
        GameRegistry.addRecipe((IRecipe) new ShapedOreRecipe(
            new ItemStack(ICBMExplosion.bMachine, 1, 11),
            new Object[] { "?! ",
                           "@@@",
                           '@',
                           "plateSteel",
                           '!',
                           new ItemStack(ICBMExplosion.bMachine, 1, 2),
                           '?',
                           new ItemStack(ICBMExplosion.bMachine, 1, 8) }
        ));
        GameRegistry.addRecipe((IRecipe) new ShapedOreRecipe(
            new ItemStack(ICBMExplosion.itTeBieDaoDan),
            new Object[] { " @ ",
                           "@#@",
                           "@?@",
                           '@',
                           "ingotSteel",
                           '?',
                           Items.coal,
                           '#',
                           "circuitBasic" }
        ));
        GameRegistry.addRecipe((IRecipe) new ShapedOreRecipe(
            new ItemStack(ICBMExplosion.itTeBieDaoDan, 1, 1),
            new Object[] { " B ",
                           " C ",
                           "BMB",
                           'M',
                           new ItemStack(ICBMExplosion.itTeBieDaoDan, 1, 0),
                           'C',
                           "circuitBasic",
                           'B',
                           "ingotBronze" }
        ));
        GameRegistry.addRecipe((IRecipe) new ShapedOreRecipe(
            new ItemStack(ICBMExplosion.itTeBieDaoDan, 1, 2),
            new Object[] { "!",
                           "?",
                           "@",
                           '@',
                           new ItemStack(ICBMExplosion.itTeBieDaoDan, 1, 0),
                           '?',
                           new ItemStack(ICBMExplosion.bExplosives, 1, 0),
                           '!',
                           "circuitBasic" }
        ));
        GameRegistry.addRecipe((IRecipe) new ShapedOreRecipe(
            new ItemStack(ICBMExplosion.itTeBieDaoDan, 1, 3),
            new Object[] { " ! ",
                           " ? ",
                           "!@!",
                           '@',
                           new ItemStack(ICBMExplosion.itTeBieDaoDan, 1, 0),
                           '?',
                           MissileBase.list[ZhaPin.fragmentation.getID()].getItemStack(),
                           '!',
                           new ItemStack(ICBMExplosion.itDaoDan, 1, 0) }
        ));
        GameRegistry.addRecipe((IRecipe) new ShapedOreRecipe(
            new ItemStack(ICBMExplosion.itTeBieDaoDan, 1, 4),
            new Object[] { " N ",
                           "NCN",
                           'C',
                           new ItemStack(ICBMExplosion.itTeBieDaoDan, 1, 3),
                           'N',
                           ZhaPin.nuclear.getItemStack() }
        ));

        for (int i = 0; i < ZhaPin.E_SI_ID; ++i) {
            RecipeHelper.addRecipe(
                (IRecipe) new ShapelessOreRecipe(
                    new ItemStack(ICBMExplosion.itDaoDan, 1, i),
                    new Object[] { new ItemStack(ICBMExplosion.itTeBieDaoDan, 1, 0),
                                   new ItemStack(ICBMExplosion.bExplosives, 1, i) }
                ),
                ZhaPin.list[i].getUnlocalizedName() + " Missile",
                MainBase.CONFIGURATION,
                true
            );

            if (i < ZhaPin.E_YI_ID) {
                RecipeHelper.addRecipe(
                    (IRecipe) new ShapedOreRecipe(
                        new ItemStack(ICBMExplosion.itShouLiuDan, 1, i),
                        new Object[] { "?",
                                       "@",
                                       '@',
                                       new ItemStack(ICBMExplosion.bExplosives, 1, i),
                                       '?',
                                       Items.string }
                    ),
                    ZhaPin.list[i].getUnlocalizedName() + " Grenade",
                    MainBase.CONFIGURATION,
                    true
                );
            }

            if (i < ZhaPin.E_ER_ID) {
                RecipeHelper.addRecipe(
                    (IRecipe) new ShapedOreRecipe(
                        new ItemStack(ICBMExplosion.itChe, 1, i),
                        new Object[] { "?",
                                       "@",
                                       '?',
                                       new ItemStack(ICBMExplosion.bExplosives, 1, i),
                                       '@',
                                       Items.minecart }
                    ),
                    ZhaPin.list[i].getUnlocalizedName() + " Minecart",
                    MainBase.CONFIGURATION,
                    true
                );
            }
        }

        EntityRegistry.registerGlobalEntityID(
            EExplosive.class, "ICBMExplosive", EntityRegistry.findGlobalUniqueEntityId()
        );
        EntityRegistry.registerGlobalEntityID(
            EMissile.class, "ICBMMissile", EntityRegistry.findGlobalUniqueEntityId()
        );
        EntityRegistry.registerGlobalEntityID(
            EExplosion.class,
            "ICBMProceduralExplosion",
            EntityRegistry.findGlobalUniqueEntityId()
        );
        EntityRegistry.registerGlobalEntityID(
            EGravityBlock.class,
            "ICBMGravityBlock",
            EntityRegistry.findGlobalUniqueEntityId()
        );
        EntityRegistry.registerGlobalEntityID(
            ELightBeam.class, "ICBMLightBeam", EntityRegistry.findGlobalUniqueEntityId()
        );
        EntityRegistry.registerGlobalEntityID(
            ESuiPian.class, "ICBMFragment", EntityRegistry.findGlobalUniqueEntityId()
        );
        EntityRegistry.registerGlobalEntityID(
            EGrenade.class, "ICBMGrenade", EntityRegistry.findGlobalUniqueEntityId()
        );
        EntityRegistry.registerGlobalEntityID(
            ECart.class, "ICBMChe", EntityRegistry.findGlobalUniqueEntityId()
        );
        EntityRegistry.registerModEntity(
            EExplosive.class, "ICBMExplosive", 50, (Object) this, 50, 5, true
        );
        EntityRegistry.registerModEntity(
            EMissile.class, "ICBMMissile", 51, (Object) this, 500, 1, true
        );
        EntityRegistry.registerModEntity(
            EExplosion.class, "ICBMProceduralExplosion", 52, (Object) this, 100, 5, true
        );
        EntityRegistry.registerModEntity(
            EGravityBlock.class, "ICBMGravityBlock", 53, (Object) this, 50, 15, true
        );
        EntityRegistry.registerModEntity(
            ELightBeam.class, "ICBMLightBeam", 54, (Object) this, 80, 5, true
        );
        EntityRegistry.registerModEntity(
            ESuiPian.class, "ICBMFragment", 55, (Object) this, 40, 8, true
        );
        EntityRegistry.registerModEntity(
            EGrenade.class, "ICBMGrenade", 56, (Object) this, 50, 5, true
        );
        EntityRegistry.registerModEntity(
            ECart.class, "ICBMChe", 58, (Object) this, 50, 4, true
        );
        ICBMExplosion.proxy.init();
    }

    public static boolean shiBaoHu(
        final World world,
        final Vector3 diDian,
        final ZhaPin.ZhaPinType type,
        final ZhaPin zhaPin
    ) {
        if (FlagRegistry.getModFlag("ModFlags") == null) {
            return false;
        }

        if (FlagRegistry.getModFlag("ModFlags")
                .containsValue(world, ICBMFlags.FLAG_BAN_GLOBAL, "true", diDian)) {
            return true;
        }

        boolean baoHu = false;

        switch (type) {
            case QUAN_BU: {
                baoHu
                    = (FlagRegistry.getModFlag("ModFlags")
                           .containsValue(
                               world, ICBMFlags.FLAG_BAN_MINECART, "true", diDian
                           )
                       || FlagRegistry.getModFlag("ModFlags")
                              .containsValue(
                                  world, ICBMFlags.FLAG_BAN_MISSILE, "true", diDian
                              )
                       || FlagRegistry.getModFlag("ModFlags")
                              .containsValue(
                                  world, ICBMFlags.FLAG_BAN_GRENADE, "true", diDian
                              )
                       || FlagRegistry.getModFlag("ModFlags")
                              .containsValue(
                                  world, ICBMFlags.FLAG_BAN_EXPLOSIVE, "true", diDian
                              ));
                break;
            }

            case CHE: {
                baoHu = FlagRegistry.getModFlag("ModFlags")
                            .containsValue(
                                world, ICBMFlags.FLAG_BAN_MINECART, "true", diDian
                            );
                break;
            }

            case DAO_DAN: {
                baoHu = FlagRegistry.getModFlag("ModFlags")
                            .containsValue(
                                world, ICBMFlags.FLAG_BAN_MISSILE, "true", diDian
                            );
                break;
            }

            case SHOU_LIU_DAN: {
                baoHu = FlagRegistry.getModFlag("ModFlags")
                            .containsValue(
                                world, ICBMFlags.FLAG_BAN_GRENADE, "true", diDian
                            );
                break;
            }

            case ZHA_DAN: {
                baoHu = FlagRegistry.getModFlag("ModFlags")
                            .containsValue(
                                world, ICBMFlags.FLAG_BAN_EXPLOSIVE, "true", diDian
                            );
                break;
            }
        }

        return FlagRegistry.getModFlag("ModFlags")
                   .containsValue(world, zhaPin.qiZi, "true", diDian)
            || baoHu;
    }

    public static boolean shiBaoHu(
        final World world,
        final Vector3 diDian,
        final ZhaPin.ZhaPinType type,
        final int zhaPinID
    ) {
        return zhaPinID < ZhaPin.list.length && zhaPinID >= 0
            && shiBaoHu(world, diDian, type, ZhaPin.list[zhaPinID]);
    }

    @Mod.EventHandler
    @Override
    public void serverStarting(final FMLServerStartingEvent event) {
        super.serverStarting(event);
        final ICommandManager commandManager
            = FMLCommonHandler.instance().getMinecraftServerInstance().getCommandManager(
            );
        final ServerCommandManager serverCommandManager
            = (ServerCommandManager) commandManager;
        serverCommandManager.registerCommand((ICommand) new ICBMCommand());
    }

    @Override
    protected String getChannel() {
        return "ICBM|E";
    }

    static {
        DU_DU = new Du("Chemical", 1, false);
        DU_CHUAN_RAN = new Du("Contagious", 1, true);
        ICBMExplosion.USE_FUEL = true;
    }
}
