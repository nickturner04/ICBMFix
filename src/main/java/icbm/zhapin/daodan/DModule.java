package icbm.zhapin.daodan;

public class DModule extends MissileBase {
    public DModule(final String name, final int id, final int tier) {
        super(name, id, tier);
    }

    @Override
    public void onExplode(final EMissile missileObj) {
        missileObj.dropMissileAsItem();
    }
}
