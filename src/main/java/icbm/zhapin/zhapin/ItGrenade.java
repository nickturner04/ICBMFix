package icbm.zhapin.zhapin;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import icbm.core.di.ItICBM;
import icbm.zhapin.ICBMExplosion;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import universalelectricity.core.vector.Vector3;

public class ItGrenade extends ItICBM {
    public static final IIcon[] ICONS;

    public ItGrenade() {
        super("grenade");
        this.setMaxStackSize(16);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
    }

    @Override
    public ItemStack onEaten(
        final ItemStack par1ItemStack,
        final World par2World,
        final EntityPlayer par3EntityPlayer
    ) {
        return par1ItemStack;
    }

    @Override
    public EnumAction getItemUseAction(final ItemStack par1ItemStack) {
        return EnumAction.bow;
    }

    @Override
    public int getMaxItemUseDuration(final ItemStack par1ItemStack) {
        return 60;
    }

    @Override
    public ItemStack onItemRightClick(
        final ItemStack itemStack, final World world, final EntityPlayer entityPlayer
    ) {
        if (itemStack != null) {
            final int haoMa = ZhaPin.list[itemStack.getItemDamage()].getID();

            if (!ICBMExplosion.shiBaoHu(
                    world,
                    new Vector3((Entity) entityPlayer),
                    ZhaPin.ZhaPinType.SHOU_LIU_DAN,
                    haoMa
                )) {
                entityPlayer.setItemInUse(
                    itemStack, this.getMaxItemUseDuration(itemStack)
                );
            } else {
                entityPlayer.addChatMessage(
                    new ChatComponentText("Grenades are banned in this region.")
                );
            }
        }

        return itemStack;
    }

    @Override
    public void onPlayerStoppedUsing(
        final ItemStack itemStack,
        final World world,
        final EntityPlayer entityPlayer,
        final int nengLiang
    ) {
        if (!world.isRemote) {
            final int haoMa = ZhaPin.list[itemStack.getItemDamage()].getID();

            if (!ICBMExplosion.shiBaoHu(
                    world,
                    new Vector3((Entity) entityPlayer),
                    ZhaPin.ZhaPinType.SHOU_LIU_DAN,
                    haoMa
                )) {
                if (!entityPlayer.capabilities.isCreativeMode) {
                    --itemStack.stackSize;

                    if (itemStack.stackSize <= 0) {
                        entityPlayer.inventory.setInventorySlotContents(
                            entityPlayer.inventory.currentItem, (ItemStack) null
                        );
                    }
                }

                world.playSoundAtEntity(
                    entityPlayer,
                    "random.fuse",
                    0.5f,
                    0.4f / (Item.itemRand.nextFloat() * 0.4f + 0.8f)
                );
                world.spawnEntityInWorld(new EGrenade(
                    world,
                    entityPlayer,
                    haoMa,
                    (this.getMaxItemUseDuration(itemStack) - nengLiang)
                        / (float) this.getMaxItemUseDuration(itemStack)
                ));
            } else {
                entityPlayer.addChatMessage(
                    new ChatComponentText("Grenades are banned in this region.")
                );
            }
        }
    }

    @Override
    public int getMetadata(final int damage) {
        return damage;
    }

    @Override
    public String getUnlocalizedName(final ItemStack itemstack) {
        return this.getUnlocalizedName() + "."
            + ZhaPin.list[itemstack.getItemDamage()].getUnlocalizedName();
    }

    @Override
    public String getUnlocalizedName() {
        return "icbm.grenade";
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(final IIconRegister iconRegister) {
        for (int i = 0; i < ZhaPin.E_YI_ID; ++i) {
            ItGrenade.ICONS[i] = iconRegister.registerIcon(
                "icbm:grenade_" + ZhaPin.list[i].getUnlocalizedName()
            );
        }
    }

    @Override
    public IIcon getIconFromDamage(final int i) {
        return ItGrenade.ICONS[i];
    }

    @Override
    public void getSubItems(
        final Item par1, final CreativeTabs par2CreativeTabs, final List par3List
    ) {
        for (int i = 0; i < ZhaPin.E_YI_ID; ++i) {
            par3List.add(new ItemStack((Item) this, 1, i));
        }
    }

    static {
        ICONS = new IIcon[256];
    }
}
