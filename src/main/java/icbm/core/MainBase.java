package icbm.core;

import java.io.File;
import java.util.Arrays;
import java.util.logging.Logger;

import atomicscience.api.poison.PotionRadiation;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import dan200.computercraft.api.ComputerCraftAPI;
import icbm.core.di.ItICBM;
import net.minecraft.block.Block;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandManager;
import net.minecraft.command.ServerCommandManager;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.MathHelper;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import universalelectricity.core.vector.Vector3;
import universalelectricity.prefab.flag.CommandFlag;
import universalelectricity.prefab.flag.FlagRegistry;
import universalelectricity.prefab.flag.ModFlag;
import universalelectricity.prefab.flag.NBTFileLoader;
import universalelectricity.prefab.multiblock.BlockMulti;
import universalelectricity.prefab.multiblock.TileEntityMulti;
import universalelectricity.prefab.ore.OreGenBase;
import universalelectricity.prefab.ore.OreGenerator;

public class MainBase {
    public static final String VERSION = "{VERSION}";
    public static final MainBase INSTANCE;
    public static boolean ZAI_KUAI;
    public static Block bLiu;
    public static BlockMulti bJia;
    public static Item itSulfur;
    public static Item itDu;
    public static OreGenBase liuGenData;
    public static final String PREFIX = "icbm:";
    public static final String RESOURCE_PATH = "/mods/icbm/";
    public static final String TEXTURE_PATH = "/mods/icbm/textures/";
    public static final String GUI_PATH = "/mods/icbm/textures/gui/";
    public static final String MODEL_PATH = "/mods/icbm/textures/models/";
    public static final String SMINE_TEXTURE = "/mods/icbm/textures/models/s-mine.png";
    public static final String BLOCK_PATH = "/mods/icbm/textures/blocks/";
    public static final String ITEM_PATH = "/mods/icbm/textures/items/";
    public static final String YU_YAN_PATH = "/mods/icbm/yuyan/";
    public static int DAO_DAN_ZUI_YUAN;
    public static final int GUI_XIA_FA_SHE_QI = 1;
    public static final int GUI_FA_SHE_SHI_MUO = 2;
    public static final int GUI_LEI_DA_TAI = 3;
    public static final int GUI_YIN_GAN_QI = 4;
    public static final int GUI_SHENG_BUO = 5;
    public static final int GUI_DIAN_CI_QI = 6;
    public static final int GUI_FA_SHE_DI = 7;
    private static boolean isPreInit;
    private static boolean isPostInit;
    public static final Configuration CONFIGURATION;
    public static final Logger LOGGER;

    @Mod.EventHandler
    public void preInit(final FMLPreInitializationEvent event) {
        if (!MainBase.isPreInit) {
            MinecraftForge.EVENT_BUS.register((Object) MainBase.INSTANCE);
            MainBase.CONFIGURATION.load();
            //PotionRadiation.INSTANCE.getId();
            MainBase.ZAI_KUAI
                = MainBase.CONFIGURATION.get("general", "Allow Chunk Loading", true)
                      .getBoolean(true);
            MainBase.DAO_DAN_ZUI_YUAN
                = MainBase.CONFIGURATION
                      .get("general", "Max Missile Distance", MainBase.DAO_DAN_ZUI_YUAN)
                      .getInt(MainBase.DAO_DAN_ZUI_YUAN);
            MainBase.bLiu = (Block) new BSulfurOre();
            MainBase.bJia = new BlockMulti()
                                .setTextureName("icbm:machine")
                                .setChannel(this.getChannel());
            MainBase.itDu = new ItICBM("poisonPowder");
            MainBase.itSulfur = new ItICBM("sulfur");
            GameRegistry.registerBlock(MainBase.bLiu, "bLiu");
            GameRegistry.registerBlock((Block) MainBase.bJia, "bJia");
            GameRegistry.registerItem(MainBase.itDu, "poisonPowder");
            GameRegistry.registerItem(MainBase.itSulfur, "sulfur");
            MainBase.liuGenData = new GenSulfur(
                                      "Sulfur Ore",
                                      "oreSulfur",
                                      new ItemStack(MainBase.bLiu),
                                      Blocks.air,
                                      40,
                                      20,
                                      4
            )
                                      .enable(MainBase.CONFIGURATION);

            MainBase.CONFIGURATION.save();
            OreDictionary.registerOre("dustSulfur", MainBase.itSulfur);
            OreGenerator.addOre(MainBase.liuGenData);
            GameRegistry.registerTileEntity(TileEntityMulti.class, "ICBMMulti");
            MainBase.isPreInit = true;
        }
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent ev) {
        ComputerCraftAPI.registerPeripheralProvider(new ICBMPeripheralProvider());
    }

    @Mod.EventHandler
    public void postInit(final FMLPostInitializationEvent event) {
        if (!MainBase.isPostInit) {
            GameRegistry.addSmelting(
                MainBase.bLiu, new ItemStack(MainBase.itSulfur, 4), 0.8f
            );
            GameRegistry.addRecipe((IRecipe) new ShapedOreRecipe(
                new ItemStack(Items.gunpowder, 3),
                new Object[] { "@@@", "@?@", "@@@", '@', "dustSulfur", '?', Items.coal }
            ));
            GameRegistry.addRecipe((IRecipe) new ShapedOreRecipe(
                new ItemStack(Items.gunpowder, 3),
                new Object[] { "@@@",
                               "@?@",
                               "@@@",
                               '@',
                               "dustSulfur",
                               '?',
                               new ItemStack(Items.coal, 1, 1) }
            ));
            GameRegistry.addRecipe((IRecipe) new ShapedOreRecipe(
                Blocks.tnt,
                new Object[] {
                    "@@@", "@R@", "@@@", '@', Items.gunpowder, 'R', Items.redstone }
            ));
            GameRegistry.addRecipe((IRecipe) new ShapelessOreRecipe(
                new ItemStack(MainBase.itDu, 3),
                new Object[] { Items.spider_eye, Items.rotten_flesh }
            ));
            MainBase.isPostInit = true;
        }
    }

    @Mod.EventHandler
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
    public void worldSave(final WorldEvent.Save evt) {
        if (!((WorldEvent) evt).world.isRemote) {
            NBTFileLoader.saveData(
                "ModFlags", FlagRegistry.getModFlag("ModFlags").getNBT()
            );
        }
    }

    public static Vector3 getLook(final float rotationYaw, final float rotationPitch) {
        final float var2 = MathHelper.cos(-rotationYaw * 0.017453292f - 3.1415927f);
        final float var3 = MathHelper.sin(-rotationYaw * 0.017453292f - 3.1415927f);
        final float var4 = -MathHelper.cos(-rotationPitch * 0.017453292f);
        final float var5 = MathHelper.sin(-rotationPitch * 0.017453292f);
        return new Vector3(var3 * var4, var5, var2 * var4);
    }

    public static void setModMetadata(final String id, final ModMetadata metadata) {
        metadata.modId = id;
        metadata.name = "ICBM";
        metadata.description
            = "ICBM is a Minecraft Mod that introduces intercontinental ballistic missiles to Minecraft. But the fun doesn't end there! This mod also features many different explosives, missiles and machines classified in three different tiers. If strategic warfare, carefully coordinated airstrikes, messing with matter and general destruction are up your alley, then this mod is for you!";
        metadata.url = "http://www.universalelectricity.com/icbm/";
        metadata.logoFile = "icbm_logo.png";
        metadata.version = VERSION;
        metadata.authorList = Arrays.asList("Calclavia", "LordMZTE", "tilera");
        metadata.credits = "Please visit the website.";
        metadata.autogenerated = false;
    }

    protected String getChannel() {
        return null;
    }

    static {
        INSTANCE = new MainBase();
        MainBase.DAO_DAN_ZUI_YUAN = 10000;
        CONFIGURATION
            = new Configuration(new File(Loader.instance().getConfigDir(), "ICBM.cfg"));
        LOGGER = Logger.getLogger("ICBM");
    }
}
