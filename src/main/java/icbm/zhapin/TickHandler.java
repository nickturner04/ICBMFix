package icbm.zhapin;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.PlayerTickEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class TickHandler {
    @SubscribeEvent
    public void onTick(PlayerTickEvent ev) {
        if (ev.phase == TickEvent.Phase.START)
            tickStart(ev.player);
    }

    public void tickStart(final EntityPlayer player) {
        try {
            final ItemStack currentItem = player.getCurrentEquippedItem();

            if (currentItem != null
                && (player != Minecraft.getMinecraft().renderViewEntity
                    || Minecraft.getMinecraft().gameSettings.thirdPersonView != 0)
                && currentItem.getItem() == ICBMExplosion.itFaSheQi
                && player.getItemInUseCount() <= 0) {
                player.setItemInUse(currentItem, Integer.MAX_VALUE);
            }
        } catch (final Exception e) {
            System.out.println("ICBM|Explosion failed to tick properly.");
            e.printStackTrace();
        }
    }
}
