package mySelf.crafters.initializing;

import mySelf.crafters.objects.InstaCraftSlotGroup;
import mySelf.crafters.objects.ItemStack;
import mySelf.crafters.objects.SlotGroup;

import static mySelf.crafters.initializing.InitializeAll.ITEMS;

public class SlotGroups implements CommonInitializing{

    public SlotGroup allItemsGroup;
    public SlotGroup invSlots;
    public SlotGroup craftSlots1;
    public SlotGroup craftSlots2;
    public SlotGroup craftSlots3;

    public SlotGroup[] everyGroup;

    @Override
    public void initializer() {
        allItemsGroup = new SlotGroup(4, true);
        invSlots = new SlotGroup(9, false);

        ItemStack[] flowerPotCrafting = {
                new ItemStack(ITEMS.filledPot, 1),
                new ItemStack(ITEMS.flowerSeeds, 1),
                new ItemStack(ITEMS.flowerPot, 1)};
        craftSlots1 = new InstaCraftSlotGroup(3, false, 2, flowerPotCrafting);

        ItemStack[] flowerPotDECrafting = {
                new ItemStack(ITEMS.flowerPot, 1),
                new ItemStack(ITEMS.filledPot, 1),
                new ItemStack(ITEMS.flowerSeeds, 1),
                new ItemStack(ITEMS.flowerSeeds, 1)};
        craftSlots2 = new InstaCraftSlotGroup(4, true, 1, flowerPotDECrafting);

        ItemStack[] bluePotCraft = {
                new ItemStack(ITEMS.flowerPot, 1),
                new ItemStack(ITEMS.filledPot, 1),
                new ItemStack(ITEMS.blueFilledPot, 1)};
        craftSlots3 = new InstaCraftSlotGroup(3, true, 2, bluePotCraft);

        everyGroup = new SlotGroup[]{allItemsGroup, invSlots, craftSlots1, craftSlots2, craftSlots3};
    }
}
