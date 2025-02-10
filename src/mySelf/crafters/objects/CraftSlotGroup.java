package mySelf.crafters.objects;

import mySelf.crafters.initializing.Slots;

public class CraftSlotGroup extends SlotGroup{

    //a separation between input and output, input comes first
    //the ids start from 0
    int firstOutputSlotId;

    //both input and output, if "useGhostItem" is true, then this will be the order of the items
    //                       if false, any order of input is accepted
    //the input stack amount is how many consumed during the crafting
    //the output stack amount is how many is created after crafting
    ItemStack[] crafting;

    public CraftSlotGroup(Slot[] slots, boolean useGhostItem, int firstOutputSlotId, ItemStack[] crafting) {
        super(slots, useGhostItem);

        //creates a ghost item if requested and if the slot is empty
        //will not fill excess slots
        if (useGhostItem) {
            for (int i = 0; i < crafting.length; i++) {
                if (slots[i].getItemStack() == null) {
                    slots[i].setItemStack(new ItemStack(0, crafting[i].item));
                }
            }
        }
        this.firstOutputSlotId = firstOutputSlotId;
        this.crafting = crafting;
    }

    public CraftSlotGroup(int slotAmount, boolean useGhostItem, int firstOutputSlotId, ItemStack[] crafting) {
        super(slotAmount, useGhostItem);
        this.firstOutputSlotId = firstOutputSlotId;
        this.crafting = crafting;
    }

    @Override
    public void setSlot(Slot slot, int id) {
        super.setSlot(slot, id);

        if (useGhostItem && crafting.length > id && slot.getItemStack() == null) {
            slot.setItemStack(new ItemStack(0, crafting[id].item));
        }
    }

    @Override
    public void setAllSlots(Slot[] slots) {
        super.setAllSlots(slots);

        if (useGhostItem) {
            for (int i = 0; i < crafting.length; i++) {
                if (slots[i].getItemStack() == null) {
                    slots[i].setItemStack(new ItemStack(0, crafting[i].item));
                }
            }
        }
    }

    public ItemStack[] getCrafting() {
        return crafting;
    }

    public void setCrafting(ItemStack[] crafting) {
        this.crafting = crafting.clone();
    }

    public int getFirstOutputSlotId() {
        return firstOutputSlotId;
    }

    public void setFirstOutputSlotId(int firstOutputSlotId) {
        this.firstOutputSlotId = firstOutputSlotId;
    }

    public boolean isInput(Slot slotToTest) {
        for (int i = 0; i < getFirstOutputSlotId(); i++) {
            if (slotToTest.equals(getSlot(i))) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isOutput(Slot slotToTest) {
        for (int i = getFirstOutputSlotId(); i < getAllSlots().length; i++) {
            if (slotToTest.equals(getSlot(i))) {
                return true;
            }
        }
        return false;
    }

    //subtracts the inputs
    public void craft(int amount) {

        for (int i = 0; i < firstOutputSlotId; i++) {
            getSlot(i).incrementStackSize(-amount * getCrafting()[i].itemsNumber);
        }
    }

    public void update() {
        int amountCraftable = 999;
        for (int i = 0; i < firstOutputSlotId; i++) {
            amountCraftable = Math.min(getAllSlots()[i].getStackSize()/getCrafting()[i].itemsNumber, amountCraftable);
        }
        for (int i = firstOutputSlotId; i < getCrafting().length; i++) {
            getSlot(i).setStackSize(amountCraftable);
        }
    }
}
