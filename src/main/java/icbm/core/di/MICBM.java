package icbm.core.di;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;

@SideOnly(Side.CLIENT)
public abstract class MICBM extends ModelBase {
    public void render(final float f5) {}
}
