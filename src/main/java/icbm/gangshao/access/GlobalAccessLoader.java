package icbm.gangshao.access;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import icbm.gangshao.saving.INbtSave;
import icbm.gangshao.saving.SaveManager;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.MinecraftForge;

public class GlobalAccessLoader implements INbtSave {
    public static boolean isInitialized;
    public static GlobalAccessLoader intance;
    public static final String SAVE_NAME = "Global_Access_List";

    public void initiate() {
        if (!GlobalAccessLoader.isInitialized) {
            MinecraftForge.EVENT_BUS.register((Object) this);
            SaveManager.intance.registerNbtSave(this);
            GlobalAccessLoader.isInitialized = true;
        }
    }

    @Mod.EventHandler
    public void serverStarting(final FMLServerStartingEvent event) {
        if (!GlobalAccessManager.hasLoaded) {
            GlobalAccessManager.getMasterSaveFile();
        }
    }

    @Override
    public String saveFileName() {
        return "global-access-list";
    }

    @Override
    public NBTTagCompound getSaveData() {
        return GlobalAccessManager.getMasterSaveFile();
    }

    @Override
    public boolean shouldSave(final boolean isServer) {
        return isServer && GlobalAccessManager.hasLoaded && !GlobalAccessManager.loading;
    }

    static {
        GlobalAccessLoader.isInitialized = false;
        GlobalAccessLoader.intance = new GlobalAccessLoader();
    }
}
