package icbm.zhapin.gui;

import icbm.zhapin.ICBMExplosion;
import icbm.zhapin.daodan.EMissile;
import icbm.zhapin.jiqi.BMachine;
import icbm.zhapin.jiqi.RadarTowerGuiPacket;
import icbm.zhapin.jiqi.TRadarTower;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import universalelectricity.api.energy.UnitDisplay;
import universalelectricity.core.vector.Vector2;
import universalelectricity.prefab.GuiBase;
import universalelectricity.prefab.TranslationHelper;
import universalelectricity.prefab.vector.Region2;

public class GRadarTower extends GuiBase {
    private TRadarTower tileEntity;
    private int containerPosX;
    private int containerPosY;
    private GuiTextField textFieldAlarmRange;
    private GuiTextField textFieldSafetyZone;
    private Vector2 mouseOverCoords;
    private Vector2 mousePosition;
    private Vector2 radarCenter;
    private float radarMapRadius;
    private String info;
    private String info2;

    public GRadarTower(final TRadarTower tileEntity) {
        this.mouseOverCoords = new Vector2();
        this.mousePosition = new Vector2();
        this.info = "";
        this.tileEntity = tileEntity;
        this.mouseOverCoords
            = new Vector2(this.tileEntity.xCoord, this.tileEntity.zCoord);
        super.xSize = 256;
        this.radarCenter = new Vector2(
            this.containerPosX + super.xSize / 3 - 14,
            this.containerPosY + super.ySize / 2 + 4
        );
        this.radarMapRadius = 7.836991f;
    }

    @Override
    public void initGui() {
        super.initGui();
        (this.textFieldSafetyZone
         = new GuiTextField(this.fontRendererObj, 155, 83, 30, 12))
            .setMaxStringLength(3);
        this.textFieldSafetyZone.setText(this.tileEntity.safetyRadius + "");
        (this.textFieldAlarmRange
         = new GuiTextField(this.fontRendererObj, 155, 110, 30, 12))
            .setMaxStringLength(3);
        this.textFieldAlarmRange.setText(this.tileEntity.alarmRadius + "");
    }

    @Override
    public void keyTyped(final char par1, final int par2) {
        super.keyTyped(par1, par2);
        this.textFieldSafetyZone.textboxKeyTyped(par1, par2);
        this.textFieldAlarmRange.textboxKeyTyped(par1, par2);

        try {
            final int newSafetyRadius = Math.min(
                500, Math.max(0, Integer.parseInt(this.textFieldSafetyZone.getText()))
            );
            this.tileEntity.safetyRadius = newSafetyRadius;
            ICBMExplosion.channel.sendToServer(new RadarTowerGuiPacket(this.tileEntity));
        } catch (final NumberFormatException ex) {}

        try {
            final int newAlarmRadius = Math.min(
                500, Math.max(0, Integer.parseInt(this.textFieldAlarmRange.getText()))
            );
            this.tileEntity.alarmRadius = newAlarmRadius;
            ICBMExplosion.channel.sendToServer(new RadarTowerGuiPacket(this.tileEntity));
        } catch (final NumberFormatException ex2) {}
    }

    @Override
    public void mouseClicked(final int par1, final int par2, final int par3) {
        super.mouseClicked(par1, par2, par3);
        this.textFieldAlarmRange.mouseClicked(
            par1 - this.containerPosX, par2 - this.containerPosY, par3
        );
        this.textFieldSafetyZone.mouseClicked(
            par1 - this.containerPosX, par2 - this.containerPosY, par3
        );
    }

    @Override
    protected void drawForegroundLayer(final int var2, final int var3, final float var1) {
        this.fontRendererObj.drawString(
            "§7" + TranslationHelper.getLocal("icbm.machine.9.name"),
            super.xSize / 2 - 30,
            6,
            4210752
        );
        this.fontRendererObj.drawString("Coordinates:", 155, 18, 4210752);
        this.fontRendererObj.drawString(
            "X: " + (int) Math.round(this.mouseOverCoords.x)
                + " Z: " + (int) Math.round(this.mouseOverCoords.y),
            155,
            30,
            4210752
        );
        this.fontRendererObj.drawString("§6" + this.info, 155, 42, 4210752);
        this.fontRendererObj.drawString("§4" + this.info2, 155, 54, 4210752);
        this.fontRendererObj.drawString("Safe Zone:", 155, 70, 4210752);
        this.textFieldSafetyZone.drawTextBox();
        this.fontRendererObj.drawString("Blocks", 190, 85, 4210752);
        this.fontRendererObj.drawString("Alarm Range:", 155, 98, 4210752);
        this.textFieldAlarmRange.drawTextBox();
        this.fontRendererObj.drawString("Blocks", 190, 112, 4210752);
        this.fontRendererObj.drawString(
            UnitDisplay.getDisplay(
                this.tileEntity.getRequest().getWatts() * 20.0,
                UnitDisplay.Unit.WATT
            ),
            155,
            128,
            4210752
        );
        this.fontRendererObj.drawString(
            UnitDisplay.getDisplay(
                this.tileEntity.getVoltage(), UnitDisplay.Unit.VOLTAGE
            ),
            155,
            138,
            4210752
        );
        String color = "§4";
        String status = "Idle";

        if (this.tileEntity.isDisabled()) {
            status = "Disabled!";
        } else if (this.tileEntity.canRun()) {
            color = "§2";
            status = "Radar On!";
        } else {
            status = "No Electricity!";
        }

        this.fontRendererObj.drawString(color + status, 155, 150, 4210752);
    }

    @Override
    protected void drawBackgroundLayer(final int var2, final int var3, final float var1) {
        this.mc.renderEngine.bindTexture(
            new ResourceLocation("icbm", "textures/gui/gui_radar.png")
        );
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        this.containerPosX = (this.width - super.xSize) / 2;
        this.containerPosY = (this.height - super.ySize) / 2;
        this.drawTexturedModalRect(
            this.containerPosX, this.containerPosY, 0, 0, super.xSize, super.ySize
        );
        this.radarCenter = new Vector2(
            this.containerPosX + super.xSize / 3 - 10,
            this.containerPosY + super.ySize / 2 + 4
        );
        this.radarMapRadius = 7.0422535f;
        this.info = "";
        this.info2 = "";

        if (this.tileEntity.canRun()) {
            int range = 4;

            for (final Entity entity : this.tileEntity.entitiesInRange) {
                final Vector2 position = new Vector2(
                    this.radarCenter.x
                        + (entity.posX - this.tileEntity.xCoord) / this.radarMapRadius,
                    this.radarCenter.y
                        - (entity.posZ - this.tileEntity.zCoord) / this.radarMapRadius
                );

                if (entity instanceof EMissile) {
                    if (this.tileEntity.isWeiXianDaoDan((EMissile) entity)) {
                        this.mc.renderEngine.bindTexture(
                            new ResourceLocation("icbm", "textures/gui/reddot.png")
                        );
                    } else {
                        this.mc.renderEngine.bindTexture(
                            new ResourceLocation("icbm", "textures/gui/yellowdot.png")
                        );
                    }
                } else {
                    this.mc.renderEngine.bindTexture(
                        new ResourceLocation("icbm", "textures/gui/yellowdot.png")
                    );
                }

                this.drawTexturedModalRect(position.intX(), position.intY(), 0, 0, 2, 2);
                final Vector2 minPosition = position.clone();
                minPosition.add(-range);
                final Vector2 maxPosition = position.clone();
                maxPosition.add(range);

                if (new Region2(minPosition, maxPosition).isIn(this.mousePosition)) {
                    this.info = entity.getCommandSenderName();

                    if (entity instanceof EntityPlayer) {
                        this.info = "§1" + this.info;
                    }

                    if (!(entity instanceof EMissile)
                        || ((EMissile) entity).target == null) {
                        continue;
                    }

                    this.info2 = "(" + ((EMissile) entity).target.intX() + ", "
                        + ((EMissile) entity).target.intZ() + ")";
                }
            }

            range = 2;

            for (final TileEntity jiQi : this.tileEntity.tileEntitiesInRange) {
                final Vector2 position = new Vector2(
                    this.radarCenter.x
                        + (jiQi.xCoord - this.tileEntity.xCoord) / this.radarMapRadius,
                    this.radarCenter.y
                        - (jiQi.zCoord - this.tileEntity.zCoord) / this.radarMapRadius
                );
                this.mc.renderEngine.bindTexture(
                    new ResourceLocation("icbm", "textures/gui/whitedot.png")
                );
                this.drawTexturedModalRect(position.intX(), position.intY(), 0, 0, 2, 2);
                final Vector2 minPosition = position.clone();
                minPosition.add(-range);
                final Vector2 maxPosition = position.clone();
                maxPosition.add(range);

                if (new Region2(minPosition, maxPosition).isIn(this.mousePosition)
                    && jiQi.getBlockType() != null) {
                    if (jiQi.getBlockType() instanceof BMachine) {
                        this.info = BMachine.getMachineName(jiQi);
                    } else {
                        this.info = jiQi.getBlockType().getLocalizedName();
                    }
                }
            }
        }
    }

    @Override
    public void updateScreen() {
        super.updateScreen();

        if (Mouse.isInsideWindow() && Mouse.getEventButton() == -1) {
            this.mousePosition = new Vector2(
                Mouse.getEventX() * this.width / this.mc.displayWidth,
                this.height - Mouse.getEventY() * this.height / this.mc.displayHeight - 1
            );
            final float difference = 500.0f / this.radarMapRadius;

            if (this.mousePosition.x > this.radarCenter.x - difference
                && this.mousePosition
                        .x<this.radarCenter.x + difference&& this.mousePosition.y> this
                        .radarCenter.y
                    - difference
                && this.mousePosition.y < this.radarCenter.y + difference) {
                final int xDifference = (int) (this.mousePosition.x - this.radarCenter.x);
                final int yDifference = (int) (this.mousePosition.y - this.radarCenter.y);
                final int xBlockDistance = (int) (xDifference * this.radarMapRadius);
                final int yBlockDistance = (int) (yDifference * this.radarMapRadius);
                this.mouseOverCoords = new Vector2(
                    this.tileEntity.xCoord + xBlockDistance,
                    this.tileEntity.zCoord - yBlockDistance
                );
            }
        }

        if (!this.textFieldSafetyZone.isFocused()) {
            this.textFieldSafetyZone.setText(this.tileEntity.safetyRadius + "");
        }

        if (!this.textFieldAlarmRange.isFocused()) {
            this.textFieldAlarmRange.setText(this.tileEntity.alarmRadius + "");
        }
    }
}
