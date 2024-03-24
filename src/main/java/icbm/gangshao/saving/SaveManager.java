package icbm.gangshao.saving;

import java.util.ArrayList;
import java.util.List;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLServerStoppingEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.WorldEvent;
import universalelectricity.prefab.flag.NBTFileLoader;

public class SaveManager {
    public static List<INbtSave> nbtSaveList;
    public static boolean isInitialized;
    public static SaveManager intance;

    public void registerNbtSave(final INbtSave saveClass) {
        if (!SaveManager.isInitialized) {
            MinecraftForge.EVENT_BUS.register((Object) this);
            SaveManager.isInitialized = true;
        }

        if (saveClass != null && !SaveManager.nbtSaveList.contains(saveClass)) {
            SaveManager.nbtSaveList.add(saveClass);
        }
    }

    @SubscribeEvent
    public void onWorldSave(final WorldEvent.Save event) {
        this.save(!((WorldEvent) event).world.isRemote);
    }

    @Mod.EventHandler
    public void serverStopping(final FMLServerStoppingEvent event) {
        this.save(true);
    }

    public void save(final boolean isServer) {
        for (final INbtSave save : SaveManager.nbtSaveList) {
            if (save.shouldSave(isServer)) {
                NBTFileLoader.saveData(save.saveFileName(), save.getSaveData());
            }
        }
    }

    static {
        SaveManager.nbtSaveList = new ArrayList<>();
        SaveManager.isInitialized = false;
        SaveManager.intance = new SaveManager();
    }
}
