package mySelf.crafters.initializing;

import mySelf.crafters.objects.SlotGroup;

import java.util.ArrayList;
import java.util.List;

import static mySelf.crafters.initializing.InitializeAll.ITEMS;

public class SlotGroups implements CommonInitializing{

    public SlotGroup allItemsGroup;
    public SlotGroup invSlots;
    public List<SlotGroup> everyGroup;

    @Override
    public void initializer() {
        everyGroup = new ArrayList<>();

        allItemsGroup = new SlotGroup(ITEMS.ALL_THE_ITEMS.size(), true);
        everyGroup.add(allItemsGroup);

        invSlots = new SlotGroup(9, false);
        everyGroup.add(invSlots);
    }
}
