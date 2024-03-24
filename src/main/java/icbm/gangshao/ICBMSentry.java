package icbm.gangshao;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import icbm.api.ICBMFlags;
import icbm.core.ICBMTab;
import icbm.core.MainBase;
import icbm.gangshao.damage.EntityTileDamagable;
import icbm.gangshao.packet.PacketHandlerTurret;
import icbm.gangshao.packet.PacketTurret;
import icbm.gangshao.platform.BlockTurretPlatform;
import icbm.gangshao.terminal.TerminalCommandPacket;
import icbm.gangshao.terminal.TerminalCommandPacketHandler;
import icbm.gangshao.terminal.TerminalOutputPacket;
import icbm.gangshao.terminal.TerminalOutputPacketHandler;
import icbm.gangshao.terminal.command.CommandAccess;
import icbm.gangshao.terminal.command.CommandDestroy;
import icbm.gangshao.terminal.command.CommandGet;
import icbm.gangshao.terminal.command.CommandHelp;
import icbm.gangshao.terminal.command.CommandRegistry;
import icbm.gangshao.terminal.command.CommandTarget;
import icbm.gangshao.terminal.command.CommandUser;
import icbm.gangshao.turret.BlockTurret;
import icbm.gangshao.turret.ItemAmmo;
import icbm.gangshao.turret.ItemBlockTurret;
import icbm.gangshao.turret.mount.ESeat;
import icbm.gangshao.turret.upgrades.ItPaoTaiUpgrades;
import net.minecraft.block.Block;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandManager;
import net.minecraft.command.ServerCommandManager;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.oredict.ShapedOreRecipe;
import universalelectricity.core.vector.Vector3;
import universalelectricity.prefab.flag.CommandFlag;
import universalelectricity.prefab.flag.FlagRegistry;
import universalelectricity.prefab.flag.ModFlag;
import universalelectricity.prefab.flag.NBTFileLoader;

@Mod(modid = "ICBM|Sentry", name = "ICBM|Sentry", version = MainBase.VERSION, useMetadata = true)
public class ICBMSentry extends MainBase {
    public static final String NAME = "ICBM|Sentry";
    public static final String CHANNEL = "ICBM";
    @SidedProxy(
        clientSide = "icbm.gangshao.ClientProxy", serverSide = "icbm.gangshao.CommonProxy"
    )
    public static CommonProxy proxy;
    @Mod.Instance("ICBM|Sentry")
    public static ICBMSentry instance;
    @Mod.Metadata("ICBM|Sentry")
    public static ModMetadata metadata;
    public static final int BLOCK_ID_PREFIX = 3517;
    public static final int ITEM_ID_PREFIX = 20948;
    public static final int ENTITY_ID_PREFIX = 50;
    public static Block blockTurret;
    public static Block blockPlatform;
    public static Item itemAmmo;
    public static Item itemUpgrade;
    public static ItemStack conventionalBullet;
    public static ItemStack railgunBullet;
    public static ItemStack antimatterBullet;
    public static ItemStack bulletShell;
    public static final String FLAG_RAILGUN;
    public static SimpleNetworkWrapper channel;

    @Mod.EventHandler
    @Override
    public void preInit(final FMLPreInitializationEvent event) {
        super.preInit(event);
        NetworkRegistry.INSTANCE.registerGuiHandler(
            (Object) this, (IGuiHandler) ICBMSentry.proxy
        );
        MinecraftForge.EVENT_BUS.register((Object) this);
        MainBase.CONFIGURATION.load();
        ICBMSentry.blockTurret = (Block) new BlockTurret();
        ICBMSentry.blockPlatform = (Block) new BlockTurretPlatform();
        ICBMSentry.itemAmmo = new ItemAmmo();
        ICBMSentry.itemUpgrade = new ItPaoTaiUpgrades();
        MainBase.CONFIGURATION.save();
        ICBMSentry.bulletShell = new ItemStack(ICBMSentry.itemAmmo, 1, 0);
        ICBMSentry.conventionalBullet = new ItemStack(ICBMSentry.itemAmmo, 1, 1);
        ICBMSentry.railgunBullet = new ItemStack(ICBMSentry.itemAmmo, 1, 2);
        ICBMSentry.antimatterBullet = new ItemStack(ICBMSentry.itemAmmo, 1, 3);
        GameRegistry.registerBlock(
            ICBMSentry.blockTurret, ItemBlockTurret.class, "ICBMTurret"
        );
        GameRegistry.registerBlock(ICBMSentry.blockPlatform, "ICBMPlatform");
        EntityRegistry.registerGlobalEntityID(
            ESeat.class, "ICBMFake", EntityRegistry.findGlobalUniqueEntityId()
        );
        EntityRegistry.registerModEntity(
            ESeat.class, "ICBMFake", 57, (Object) this, 50, 5, true
        );
        EntityRegistry.registerGlobalEntityID(
            EntityTileDamagable.class,
            "ICBMFakeTile",
            EntityRegistry.findGlobalUniqueEntityId()
        );
        EntityRegistry.registerModEntity(
            EntityTileDamagable.class, "ICBMFakeTile", 58, (Object) this, 50, 5, true
        );

        GameRegistry.registerItem(itemAmmo, "icbm:itemAmmo");
        GameRegistry.registerItem(itemUpgrade, "icbm:itemUpgrade");

        ICBMTab.itemStack = new ItemStack(ICBMSentry.blockTurret);        

        ICBMSentry.proxy.preInit();

        channel = NetworkRegistry.INSTANCE.newSimpleChannel("icbm_sentry");

        int pktId = 0;
        channel.registerMessage(
            TerminalCommandPacketHandler.class,
            TerminalCommandPacket.class,
            pktId++,
            Side.SERVER
        );
        ICBMSentry.channel.registerMessage(
            PacketHandlerTurret.class, PacketTurret.class, pktId++, Side.CLIENT
        );
        ICBMSentry.channel.registerMessage(
            TerminalOutputPacketHandler.class,
            TerminalOutputPacket.class,
            pktId++,
            Side.CLIENT
        );
    }

    @Mod.EventHandler
    public void init(final FMLInitializationEvent event) {
        MainBase.setModMetadata("ICBM|Sentry", ICBMSentry.metadata);
    }

    @Mod.EventHandler
    @Override
    public void postInit(final FMLPostInitializationEvent event) {
        super.postInit(event);
        GameRegistry.addRecipe((IRecipe) new ShapedOreRecipe(
            new ItemStack(ICBMSentry.itemAmmo, 16, 0),
            new Object[] { "T", "T", 'T', "ingotTin" }
        ));
        GameRegistry.addRecipe((IRecipe) new ShapedOreRecipe(
            new ItemStack(ICBMSentry.itemAmmo, 16, 1),
            new Object[] { "SBS",
                           "SGS",
                           "SSS",
                           'B',
                           Items.iron_ingot,
                           'G',
                           Items.gunpowder,
                           'S',
                           ICBMSentry.bulletShell.copy() }
        ));
        GameRegistry.addRecipe((IRecipe) new ShapedOreRecipe(
            new ItemStack(ICBMSentry.itemAmmo, 2, 2),
            new Object[] {
                "D", "B", "B", 'D', Items.diamond, 'B', ICBMSentry.conventionalBullet }
        ));
        GameRegistry.addRecipe((IRecipe) new ShapedOreRecipe(
            ICBMSentry.antimatterBullet,
            new Object[] {
                "A", "B", 'A', "antimatterGram", 'B', ICBMSentry.railgunBullet }
        ));
        GameRegistry.addRecipe((IRecipe) new ShapedOreRecipe(
            ICBMSentry.blockPlatform,
            new Object[] { "SPS",
                           "CBC",
                           "SAS",
                           'P',
                           Blocks.piston,
                           'A',
                           "battery",
                           'S',
                           "plateSteel",
                           'C',
                           Blocks.chest,
                           'B',
                           "circuitBasic" }
        ));
        GameRegistry.addRecipe((IRecipe) new ShapedOreRecipe(
            new ItemStack(ICBMSentry.blockTurret, 1, 0),
            new Object[] { "SSS", "CS ", 'C', "circuitBasic", 'S', "ingotSteel" }
        ));
        GameRegistry.addRecipe((IRecipe) new ShapedOreRecipe(
            new ItemStack(ICBMSentry.blockTurret, 1, 1),
            new Object[] { "DDD",
                           "CS ",
                           "GS ",
                           'D',
                           Items.diamond,
                           'S',
                           "plateSteel",
                           'C',
                           "circuitElite",
                           'G',
                           new ItemStack(ICBMSentry.blockTurret, 1, 0) }
        ));
        GameRegistry.addRecipe((IRecipe) new ShapedOreRecipe(
            new ItemStack(ICBMSentry.blockTurret, 1, 2),
            new Object[] { "DDS",
                           "CS ",
                           "GS ",
                           'D',
                           "plateBronze",
                           'S',
                           "plateSteel",
                           'C',
                           "circuitAdvanced",
                           'G',
                           new ItemStack(ICBMSentry.blockTurret, 1, 0) }
        ));
        GameRegistry.addRecipe((IRecipe) new ShapedOreRecipe(
            new ItemStack(ICBMSentry.blockTurret, 1, 3),
            new Object[] { "DDG",
                           "CS ",
                           "GS ",
                           'D',
                           "plateBronze",
                           'S',
                           "plateSteel",
                           'C',
                           "circuitBasic",
                           'D',
                           Blocks.glass,
                           'G',
                           Blocks.glass }
        ));
        GameRegistry.addRecipe((IRecipe) new ShapedOreRecipe(
            new ItemStack(
                ICBMSentry.itemUpgrade,
                3,
                ItPaoTaiUpgrades.TurretUpgradeType.RANGE.ordinal()
            ),
            new Object[] { "B", "I", 'B', Items.bow, 'I', Items.iron_ingot }
        ));
        GameRegistry.addRecipe((IRecipe) new ShapedOreRecipe(
            new ItemStack(
                ICBMSentry.itemUpgrade,
                1,
                ItPaoTaiUpgrades.TurretUpgradeType.COLLECTOR.ordinal()
            ),
            new Object[] { "BBB", " I ", "BBB", 'B', Items.bucket, 'I', Items.bowl }
        ));
        CommandRegistry.register(new CommandAccess());
        CommandRegistry.register(new CommandDestroy());
        CommandRegistry.register(new CommandUser());
        CommandRegistry.register(new CommandHelp());
        CommandRegistry.register(new CommandGet());
        CommandRegistry.register(new CommandTarget());
        ICBMSentry.proxy.init();
    }

    public static boolean
    isProtected(final World world, final Vector3 diDian, final String banFlag) {
        return FlagRegistry.getModFlag("ModFlags") != null
            && (FlagRegistry.getModFlag("ModFlags")
                    .containsValue(world, ICBMFlags.FLAG_BAN_GLOBAL, "true", diDian)
                || FlagRegistry.getModFlag("ModFlags")
                       .containsValue(world, banFlag, "true", diDian));
    }

    @Mod.EventHandler
    @Override
    public void serverStarting(final FMLServerStartingEvent event) {
        FlagRegistry.registerModFlag(
            "ModFlags", new ModFlag(NBTFileLoader.loadData("ModFlags"))
        );
        final ICommandManager commandManager
            = FMLCommonHandler.instance().getMinecraftServerInstance().getCommandManager(
            );
        final ServerCommandManager serverCommandManager
            = (ServerCommandManager) commandManager;
        serverCommandManager.registerCommand((ICommand
        ) new CommandFlag(FlagRegistry.getModFlag("ModFlags")));
    }

    @SubscribeEvent
    @Override
    public void worldSave(final WorldEvent.Save evt) {
        if (!((WorldEvent) evt).world.isRemote) {
            NBTFileLoader.saveData(
                "ModFlags", FlagRegistry.getModFlag("ModFlags").getNBT()
            );
        }
    }

    @Override
    protected String getChannel() {
        return "ICBM";
    }

    static {
        FLAG_RAILGUN = FlagRegistry.registerFlag("ban_railgun");
    }
}
