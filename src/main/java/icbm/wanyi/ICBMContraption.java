package icbm.wanyi;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import icbm.core.ICBMTab;
import icbm.core.MainBase;
import icbm.wanyi.b.BCamouflage;
import icbm.wanyi.b.BConcrete;
import icbm.wanyi.b.BGlassButton;
import icbm.wanyi.b.BGlassPressurePlate;
import icbm.wanyi.b.BGlassReinforced;
import icbm.wanyi.b.BProximityDetector;
import icbm.wanyi.b.BSpikes;
import icbm.wanyi.b.IBConcrete;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.oredict.ShapedOreRecipe;
import universalelectricity.core.item.ElectricItemHelper;
import universalelectricity.core.item.ItemElectric;

@Mod(
    modid = "ICBM|Contraption",
    name = "ICBM|Contraption",
    version = MainBase.VERSION,
    dependencies = "required-after:AtomicScience",
    useMetadata = true
)
public class ICBMContraption extends MainBase {
    public static final String NAME = "ICBM|Contraption";
    public static final String CHANNEL = "ICBM|C";
    @Mod.Instance("ICBM|Contraption")
    public static ICBMContraption instance;
    @Mod.Metadata("ICBM|Contraption")
    public static ModMetadata metadata;
    @SidedProxy(
        clientSide = "icbm.wanyi.ClientProxy", serverSide = "icbm.wanyi.CommonProxy"
    )
    public static CommonProxy proxy;
    public static Block bGlassPressurePlate;
    public static Block bGlassButton;
    public static Block bProximityDetector;
    public static Block bSpikes;
    public static Block bCamouflage;
    public static Block bConcrete;
    public static Block bGlassReinforced;
    public static Item itAntidote;
    public static ItemElectric itSignalDisrupter;
    public static ItemElectric itTracker;

    public static SimpleNetworkWrapper channel;

    @Mod.EventHandler
    @Override
    public void preInit(final FMLPreInitializationEvent event) {
        super.preInit(event);
        NetworkRegistry.INSTANCE.registerGuiHandler(
            (Object) this, (IGuiHandler) ICBMContraption.proxy
        );
        MainBase.CONFIGURATION.load();
        ICBMContraption.bGlassPressurePlate = (Block) new BGlassPressurePlate();
        ICBMContraption.bGlassButton = (Block) new BGlassButton();
        ICBMContraption.bProximityDetector = (Block) new BProximityDetector();
        ICBMContraption.bSpikes = (Block) new BSpikes();
        ICBMContraption.bCamouflage = (Block) new BCamouflage();
        ICBMContraption.bConcrete = (Block) new BConcrete();
        ICBMContraption.bGlassReinforced = (Block) new BGlassReinforced();
        ICBMContraption.itAntidote = new ItAntidote();
        ICBMContraption.itSignalDisrupter = new ItSignalDisrupter();
        ICBMContraption.itTracker = new ItTracker();
        MainBase.CONFIGURATION.save();

        GameRegistry.registerBlock(
            ICBMContraption.bGlassPressurePlate, "bGlassPressurePlate"
        );
        GameRegistry.registerBlock(ICBMContraption.bGlassButton, "bGlassButton");
        GameRegistry.registerBlock(
            ICBMContraption.bProximityDetector, "bProximityDetector"
        );
        GameRegistry.registerBlock(ICBMContraption.bCamouflage, "bCamouflage");
        GameRegistry.registerBlock(ICBMContraption.bGlassReinforced, "bGlassReinforced");
        GameRegistry.registerBlock(ICBMContraption.bSpikes, IBSpikes.class, "bSpikes");
        GameRegistry.registerBlock(
            ICBMContraption.bConcrete, IBConcrete.class, "bConcrete"
        );

        GameRegistry.registerItem(ICBMContraption.itAntidote, "itAntidote");
        GameRegistry.registerItem(ICBMContraption.itSignalDisrupter, "itSignalDisrupter");
        GameRegistry.registerItem(ICBMContraption.itTracker, "itTracker");

        ICBMTab.itemStack = new ItemStack(ICBMContraption.bProximityDetector);
        ICBMContraption.proxy.preInit();

        channel = NetworkRegistry.INSTANCE.newSimpleChannel("icbm_contraption");
        int pktId = 0;
        channel.registerMessage(
            SetSignalDisrupterFrequencyPacketHandler.class,
            SetSignalDisrupterFrequencyPacket.class,
            pktId++,
            Side.SERVER
        );
        channel.registerMessage(
            ProximityDetectorModePacketHandler.class,
            ProximityDetectorModePacket.class,
            pktId++,
            Side.SERVER
        );
    }

    @Mod.EventHandler
    public void load(final FMLInitializationEvent evt) {
        MainBase.setModMetadata("ICBM|Contraption", ICBMContraption.metadata);
    }

    @Mod.EventHandler
    @Override
    public void postInit(final FMLPostInitializationEvent event) {
        super.postInit(event);
        GameRegistry.addRecipe((IRecipe) new ShapedOreRecipe(
            new ItemStack(ICBMContraption.bSpikes, 6),
            new Object[] { "CCC", "BBB", 'C', Blocks.cactus, 'B', "ingotBronze" }
        ));
        GameRegistry.addRecipe((IRecipe) new ShapedOreRecipe(
            new ItemStack(ICBMContraption.bSpikes, 6),
            new Object[] { "CCC", "BBB", 'C', Blocks.cactus, 'B', Items.iron_ingot }
        ));
        GameRegistry.addRecipe(
            new ItemStack(ICBMContraption.bSpikes, 1, 1),
            new Object[] { "E", "S", 'E', MainBase.itDu, 'S', ICBMContraption.bSpikes }
        );
        GameRegistry.addRecipe((IRecipe) new ShapedOreRecipe(
            new ItemStack(ICBMContraption.bSpikes, 1, 2),
            new Object[] { "E", "S", 'E', "dustSulfur", 'S', ICBMContraption.bSpikes }
        ));
        GameRegistry.addRecipe((IRecipe) new ShapedOreRecipe(
            new ItemStack(ICBMContraption.bCamouflage, 12),
            new Object[] { "WGW",
                           "GCG",
                           "WGW",
                           'C',
                           "circuitBasic",
                           'G',
                           Blocks.glass,
                           'W',
                           new ItemStack(Blocks.wool, 1, 32767) }
        ));

            GameRegistry.addRecipe((IRecipe) new ShapedOreRecipe(
                new ItemStack((Item) ICBMContraption.itTracker),
                new Object[] { " Z ",
                               "SBS",
                               "SCS",
                               'Z',
                               Items.compass,
                               'C',
                               "circuitBasic",
                               'B',
                               "battery",
                               'S',
                               "ingotSteel" }
            ));

        GameRegistry.addRecipe((IRecipe) new ShapedOreRecipe(
            new ItemStack(ICBMContraption.bGlassPressurePlate, 1, 0),
            new Object[] { "##", '#', Blocks.glass }
        ));
        GameRegistry.addRecipe(
            new ItemStack(ICBMContraption.bGlassButton, 2),
            new Object[] { "G", "G", 'G', Blocks.glass }
        );
        GameRegistry.addRecipe((IRecipe) new ShapedOreRecipe(
            ICBMContraption.bProximityDetector,
            new Object[] { "SSS",
                           "S?S",
                           "SSS",
                           'S',
                           "ingotSteel",
                           '?',
                           ElectricItemHelper.getUncharged(ICBMContraption.itTracker) }
        ));
        GameRegistry.addRecipe((IRecipe) new ShapedOreRecipe(
            (Item) ICBMContraption.itSignalDisrupter,
            new Object[] { "WWW",
                           "SCS",
                           "SSS",
                           'S',
                           "ingotSteel",
                           'C',
                           "circuitBasic",
                           'W',
                           "copperWire" }
        ));
        GameRegistry.addRecipe((IRecipe) new ShapedOreRecipe(
            new ItemStack(ICBMContraption.itAntidote, 6),
            new Object[] { "@@@", "@@@", "@@@", '@', Items.pumpkin_seeds }
        ));
        GameRegistry.addRecipe((IRecipe) new ShapedOreRecipe(
            new ItemStack(ICBMContraption.itAntidote, 6),
            new Object[] { "@@@", "@@@", "@@@", '@', Items.melon_seeds }
        ));
        GameRegistry.addRecipe((IRecipe) new ShapedOreRecipe(
            new ItemStack(ICBMContraption.itAntidote),
            new Object[] { "@@@", "@@@", "@@@", '@', Items.wheat_seeds }
        ));
        GameRegistry.addRecipe((IRecipe) new ShapedOreRecipe(
            new ItemStack(ICBMContraption.bConcrete, 8, 0),
            new Object[] { "SGS",
                           "GWG",
                           "SGS",
                           'G',
                           Blocks.gravel,
                           'S',
                           Blocks.sand,
                           'W',
                           Items.water_bucket }
        ));
        GameRegistry.addRecipe((IRecipe) new ShapedOreRecipe(
            new ItemStack(ICBMContraption.bConcrete, 8, 1),
            new Object[] { "COC",
                           "OCO",
                           "COC",
                           'C',
                           new ItemStack(ICBMContraption.bConcrete, 1, 0),
                           'O',
                           Blocks.obsidian }
        ));
        GameRegistry.addRecipe((IRecipe) new ShapedOreRecipe(
            new ItemStack(ICBMContraption.bConcrete, 8, 2),
            new Object[] { "COC",
                           "OCO",
                           "COC",
                           'C',
                           new ItemStack(ICBMContraption.bConcrete, 1, 1),
                           'O',
                           "ingotSteel" }
        ));
        GameRegistry.addRecipe((IRecipe) new ShapedOreRecipe(
            new ItemStack(ICBMContraption.bGlassReinforced, 8),
            new Object[] { "IGI", "GIG", "IGI", 'G', Blocks.glass, 'I', Items.iron_ingot }
        ));
        ICBMContraption.proxy.init();
    }

    @Override
    protected String getChannel() {
        return "ICBM|C";
    }
}
