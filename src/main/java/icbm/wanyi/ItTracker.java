package icbm.wanyi;

import java.util.List;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import icbm.api.ITracker;
import icbm.core.di.ItElectricICBM;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import universalelectricity.core.electricity.ElectricityPack;

public class ItTracker extends ItElectricICBM implements ITracker {
    public ItTracker() {
        super("tracker");
        this.setTextureName("icbm:tracker");
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(final IIconRegister par1IconRegister) {
        if (par1IconRegister instanceof TextureMap) {
            ((TextureMap) par1IconRegister)
                .setTextureEntry(
                    this.getUnlocalizedName().replace("item.", ""), new TextureTracker()
                );
            this.itemIcon
                = ((TextureMap) par1IconRegister)
                      .getTextureExtry(this.getUnlocalizedName().replace("item.", ""));
        }
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(
        final ItemStack itemStack,
        final EntityPlayer par2EntityPlayer,
        final List par3List,
        final boolean par4
    ) {
        super.addInformation(itemStack, par2EntityPlayer, par3List, par4);
        final Entity trackingEntity = this.getTrackingEntity(
            (World) FMLClientHandler.instance().getClient().theWorld, itemStack
        );

        if (trackingEntity != null) {
            par3List.add("Tracking: " + trackingEntity.getCommandSenderName());
        }
    }

    @Override
    public void setTrackingEntity(final ItemStack itemStack, final Entity entity) {
        if (itemStack.stackTagCompound == null) {
            itemStack.setTagCompound(new NBTTagCompound());
        }

        if (entity != null) {
            itemStack.stackTagCompound.setInteger("trackingEntity", entity.getEntityId());
        }
    }

    @Override
    public Entity getTrackingEntity(final World worldObj, final ItemStack itemStack) {
        if (worldObj != null && itemStack.stackTagCompound != null) {
            final int trackingID
                = itemStack.stackTagCompound.getInteger("trackingEntity");
            return worldObj.getEntityByID(trackingID);
        }

        return null;
    }

    @Override
    public void onCreated(
        final ItemStack par1ItemStack,
        final World par2World,
        final EntityPlayer par3EntityPlayer
    ) {
        super.onCreated(par1ItemStack, par2World, par3EntityPlayer);
        this.setTrackingEntity(par1ItemStack, (Entity) par3EntityPlayer);
    }

    public void onUpdate(
        final ItemStack itemStack,
        final World par2World,
        final Entity par3Entity,
        final int par4,
        final boolean par5
    ) {
        super.onUpdate(itemStack, par2World, par3Entity, par4, par5);

        if (par3Entity instanceof EntityPlayer) {
            final EntityPlayer player = (EntityPlayer) par3Entity;

            if (player.inventory.getCurrentItem() != null
                && player.inventory.getCurrentItem().getItem() == this) {
                final Entity trackingEntity
                    = this.getTrackingEntity(par2World, itemStack);

                if (trackingEntity != null) {
                    this.onProvide(
                        ElectricityPack.getFromWatts(
                            0.10000000149011612, this.getVoltage(itemStack)
                        ),
                        itemStack
                    );

                    if (this.getJoules(itemStack) < 0.10000000149011612) {
                        this.setTrackingEntity(itemStack, null);
                    }
                }
            }
        }
    }

    public boolean onLeftClickEntity(
        final ItemStack itemStack, final EntityPlayer player, final Entity entity
    ) {
        if (!((Entity) player).worldObj.isRemote) {
            if (this.getJoules(itemStack) > 0.10000000149011612) {
                this.setTrackingEntity(itemStack, entity);
                player.addChatMessage(new ChatComponentText(
                    "Now tracking: " + entity.getCommandSenderName()
                ));
                return true;
            }

            player.addChatMessage(new ChatComponentText("Tracker out of electricity!"));
        }

        return false;
    }

    public double getVoltage(final ItemStack itemStack) {
        return 20.0;
    }

    public double getMaxJoules(final ItemStack itemStack) {
        return 100000.0;
    }
}
