package mySelf.crafters.initializing;

public class InitializeAll implements CommonInitializing{
    public static Items ITEMS = new Items();
    public static SlotGroups SLOTGROUPS = new SlotGroups();
    public static Slots SLOTS = new Slots();

    @Override
    public void initializer() {
        ITEMS.initializer();
        SLOTGROUPS.initializer();
        SLOTS.initializer();
    }
}
