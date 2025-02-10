package mySelf.crafters.initializing;

import mySelf.crafters.objects.CraftSlotGroup;
import mySelf.crafters.objects.ItemStack;
import mySelf.crafters.objects.SlotGroup;

import static mySelf.crafters.initializing.InitializeAll.ITEMS;

public class SlotGroups implements CommonInitializing{

    public SlotGroup allItemsGroup;
    public SlotGroup invSlots;
    public SlotGroup craftSlots1;
    public SlotGroup craftSlots2;

    public SlotGroup[] everyGroup;

    @Override
    public void initializer() {
        allItemsGroup = new SlotGroup(4, true);
        invSlots = new SlotGroup(9, false);

        ItemStack[] flowerPotCrafting = {
                new ItemStack(1, ITEMS.filledPot),
                new ItemStack(1, ITEMS.flowerSeeds),
                new ItemStack(1, ITEMS.flowerPot)};
        craftSlots1 = new CraftSlotGroup(3, true, 2, flowerPotCrafting);

        ItemStack[] flowerPotDECrafting = {
                new ItemStack(1, ITEMS.flowerPot),
                new ItemStack(1, ITEMS.filledPot),
                new ItemStack(1, ITEMS.flowerSeeds),
                new ItemStack(1, ITEMS.flowerSeeds)};
        craftSlots2 = new CraftSlotGroup(4, true, 1, flowerPotDECrafting);

        everyGroup = new SlotGroup[]{allItemsGroup, invSlots, craftSlots1, craftSlots2};
    }
}
