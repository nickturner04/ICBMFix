package icbm.zhapin.daodan;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.audio.SoundManager;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.util.MathHelper;

@SideOnly(Side.CLIENT)
public class MissileSound implements IUpdatePlayerListBox {
    private final SoundManager theSoundManager;
    private final EMissile entity;
    private final EntityPlayerSP thePlayer;
    private boolean playerSPRidingMinecart;
    private boolean minecartIsDead;
    private boolean minecartIsMoving;
    private boolean silent;
    private float minecartSoundPitch;
    private float minecartMoveSoundVolume;
    private float minecartRideSoundVolume;
    private double minecartSpeed;

    public MissileSound(
        final SoundManager par1SoundManager,
        final EMissile entity,
        final EntityPlayerSP par3EntityPlayerSP
    ) {
        this.playerSPRidingMinecart = false;
        this.minecartIsDead = false;
        this.minecartIsMoving = false;
        this.silent = false;
        this.minecartSoundPitch = 0.0f;
        this.minecartMoveSoundVolume = 0.0f;
        this.minecartRideSoundVolume = 0.0f;
        this.minecartSpeed = 0.0;
        this.theSoundManager = par1SoundManager;
        this.entity = entity;
        this.thePlayer = par3EntityPlayerSP;
    }

    @Override
    public void update() {
        boolean var1 = false;
        final boolean var2 = this.playerSPRidingMinecart;
        final boolean var3 = this.minecartIsDead;
        final boolean var4 = this.minecartIsMoving;
        final float var5 = this.minecartMoveSoundVolume;
        final float var6 = this.minecartSoundPitch;
        final float var7 = this.minecartRideSoundVolume;
        this.playerSPRidingMinecart
            = (this.thePlayer != null && this.entity.riddenByEntity == this.thePlayer);
        this.minecartIsDead = this.entity.isDead;
        this.minecartSpeed = 20.0;
        this.minecartIsMoving = (this.minecartSpeed >= 0.01);

        if (var2 && !this.playerSPRidingMinecart) {
            // TODO: WTF
            // this.theSoundManager.stopEntitySound((Entity)this.thePlayer);
        }

        if (this.minecartIsDead
            || (!this.silent && this.minecartMoveSoundVolume == 0.0f
                && this.minecartRideSoundVolume == 0.0f)) {
            // TODO: WTF
            // if (!var3) {
            // this.theSoundManager.stopEntitySound((Entity)this.entity);
            // if (var2 || this.playerSPRidingMinecart) {
            // this.theSoundManager.stopEntitySound((Entity)this.thePlayer);
            // }
            // }
            this.silent = true;

            if (this.minecartIsDead) {
                return;
            }
        }

        if (this.theSoundManager != null && this.entity != null
            && this.minecartMoveSoundVolume > 0.0f) {
            //TODO: WTF
            //this.theSoundManager.playEntitySound("icbm.missileinair",
            //        (Entity) this.entity, 7.0f,
            //        this.minecartSoundPitch, true);
            this.silent = false;
            var1 = true;
        }

        if (this.entity.getTicksInAir() > 0) {
            if (this.minecartSoundPitch < 1.0f) {
                this.minecartSoundPitch += 0.0025f;
            }

            if (this.minecartSoundPitch > 1.0f) {
                this.minecartSoundPitch = 1.0f;
            }

            float var8
                = MathHelper.clamp_float((float) this.minecartSpeed, 0.0f, 4.0f) / 4.0f;
            this.minecartRideSoundVolume = 0.0f + var8 * 0.75f;
            var8 = MathHelper.clamp_float(var8 * 2.0f, 0.0f, 1.0f);
            this.minecartMoveSoundVolume = 0.0f + var8 * 6.7f;

            if (this.entity.posY > 1000.0) {
                this.minecartMoveSoundVolume = 0.0f;
                this.minecartRideSoundVolume = 0.0f;
            }
        } else if (var4) {
            this.minecartMoveSoundVolume = 0.0f;
            this.minecartSoundPitch = 0.0f;
            this.minecartRideSoundVolume = 0.0f;
        }

        if (!this.silent) {
            if (this.minecartSoundPitch != var6) {
                //this.theSoundManager.setEntitySoundPitch((Entity) this.entity,
                //        this.minecartSoundPitch);
            }

            if (this.minecartMoveSoundVolume != var5) {
                //this.theSoundManager.setEntitySoundVolume((Entity) this.entity,
                //        this.minecartMoveSoundVolume);
            }

            if (this.minecartRideSoundVolume != var7) {
                //this.theSoundManager.setEntitySoundVolume((Entity) this.thePlayer,
                //        this.minecartRideSoundVolume);
            }
        }

        if (!var1) {
            //this.theSoundManager.updateSoundLocation((Entity) this.entity);
            if (this.playerSPRidingMinecart) {
                //this.theSoundManager.updateSoundLocation((Entity) this.thePlayer,
                //        (Entity) this.entity);
            }
        }
    }
}
