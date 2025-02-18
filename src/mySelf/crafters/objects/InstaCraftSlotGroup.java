package mySelf.crafters.objects;

public class InstaCraftSlotGroup extends SlotGroup{

    private boolean isBetweenCraft = false;

    //a separation between input and output, input comes first
    //the ids start from 0
    int firstOutputSlotId;

    //both input and output, if "useGhostItem" is true, then this will be the order of the items
    //                       if false, any order of input is accepted
    //the input stack amount is how many consumed during the crafting
    //the output stack amount is how many is created after crafting
    ItemStack[] crafting;

    public InstaCraftSlotGroup(Slot[] slots, boolean useGhostItem, int firstOutputSlotId, ItemStack[] crafting) {
        super(slots, useGhostItem);

        //creates a ghost item if requested and if the slot is empty
        //will not fill excess slots
        if (useGhostItem) {
            for (int i = 0; i < crafting.length; i++) {
                if (slots[i].getItemStack() == null) {
                    slots[i].setItemStack(new ItemStack(crafting[i].getItem(), 0));
                }
            }
        }

        //you should not be able to input on output slots
        for (int i = firstOutputSlotId; i < slots.length; i++) {
            slots[i].setInsertness(false);
        }
        this.firstOutputSlotId = firstOutputSlotId;
        this.crafting = crafting;
    }

    public InstaCraftSlotGroup(int slotAmount, boolean useGhostItem, int firstOutputSlotId, ItemStack[] crafting) {
        super(slotAmount, useGhostItem);
        this.firstOutputSlotId = firstOutputSlotId;
        this.crafting = crafting;
    }

    @Override
    public void setSlot(Slot slot, int id) {
        super.setSlot(slot, id);

        if (useGhostItem && crafting.length > id && slot.getItemStack() == null) {
            slot.setItemStack(new ItemStack(crafting[id].getItem(), 0));
        }

        //if an output slot, block inserting at it
        if (id >= firstOutputSlotId) {
            slot.setInsertness(false);
        }
    }

    @Override
    public void setAllSlots(Slot[] slots) {
        super.setAllSlots(slots);

        //all of this can be seen at the constructor
        if (useGhostItem) {
            for (int i = 0; i < crafting.length; i++) {
                if (slots[i].getItemStack() == null) {
                    slots[i].setItemStack(new ItemStack(crafting[i].getItem(), 0));
                }
            }
        }

        for (int i = firstOutputSlotId; i < slots.length; i++) {
            slots[i].setInsertness(false);
        }
    }

    public ItemStack[] getCrafting() {
        return crafting;
    }

    //returns the id of the corresponding item stack in the crafting array
    //which lets the code simulate with a different slot not in the group
    //if the item is null, then the code will search by the reference of the slot
    //if nothing was found, return -1, normally leading to an error on the following code, but not always
    int getCraftingStack(Slot referenceSlot) {
        if (referenceSlot.getItem() == null) {
            for (int i = 0; i < getAllSlots().length; i++) {
                if (referenceSlot == getSlot(i)) {
                    return i;
                }
            }
        }
        for (int i = 0; i < getCrafting().length; i++) {
            if (referenceSlot.getItem().equals(getCrafting()[i].item)) {
                return i;
            }
        }
        return -1;
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

    public boolean isOutput(Slot slotToTest) {
        for (int i = getFirstOutputSlotId(); i < getAllSlots().length; i++) {
            if (slotToTest.equals(getSlot(i))) {
                return true;
            }
        }
        return false;
    }

    public void checkCraft() {
        int amountCraftable = 999;
        boolean foundItem = false;

        //goes through all slots checking for inputs
        for (int i = 0; i < firstOutputSlotId; i++) {

            //goes through all possible inputs checking if corresponds to the item
            for (int j = 0; j < firstOutputSlotId; j++) {
                if (getCrafting()[j].getItem().equals(getSlot(i).getItem())) {
                    amountCraftable = Math.min(amountCraftable, getSlot(i).getStackSize());
                    foundItem = true;
                    break;
                }
            }
            if (!foundItem) {
                amountCraftable = 0;
                break;
            }
            foundItem = false;
        }

        //optimization: only create a stack if there isn't a ghost item //
        //will update the output slots to have the amount craftable
        if (useGhostItem) {
            for (int i = firstOutputSlotId; i < getCrafting().length; i++) {
                getSlot(i).setStackSize(amountCraftable);
            }
        } else {
            for (int i = firstOutputSlotId; i < getCrafting().length; i++) {
                if (amountCraftable * getCrafting()[i].itemsNumber != 0) {
                    getSlot(i).setItemStack(
                            new ItemStack(getCrafting()[i].getItem(), amountCraftable * getCrafting()[i].itemsNumber));
                } else {
                    getSlot(i).setStackSize(0);
                }
            }
        }
    }

    public void update(Slot slotChanged, int amountChanged) {

        if (isBetweenCraft) {
            boolean allEmpty = true;
            for (int i = firstOutputSlotId; i < getCrafting().length; i++) {
                if (getSlot(i).getStackSize() != 0) {
                    allEmpty = false;
                }
            }

            //if everything is empty, at least one slot had a stackSize of zero, which means that the inputs are already consumed
            if (allEmpty) {
                isBetweenCraft = false;
                for (int i = 0; i < firstOutputSlotId; i++) {
                    getSlot(i).setInsertness(true);
                    getSlot(i).setExtractness(true);
                }
            } else {
                //simply the stack size of the first slot divided by its corresponding stack size of the crafting array
                //this gives the amount that could be crafted with this resource
                //it expects that the previous crafting was set correctly and only one slot was changed from there
                int lastRemainingCraft = getSlot(0).getStackSize() / getCrafting()[getCraftingStack(getSlot(0))].itemsNumber;
                int craftsRemaining = Math.min(
                        lastRemainingCraft, slotChanged.getStackSize() / getCrafting()[getCraftingStack(slotChanged)].itemsNumber);
                if (lastRemainingCraft >= craftsRemaining) {
                    for (int i = 0; i < firstOutputSlotId; i++) {
                        getSlot(i).incrementStackSize(-(
                                lastRemainingCraft - craftsRemaining * getCrafting()[getCraftingStack(getSlot(i))].itemsNumber));
                    }
                }
            }
        } else {
            if (((InstaCraftSlotGroup) slotChanged.getGroupContaining()).isOutput(slotChanged)) {

                //if there is only one output slot, do the following
                if (crafting.length - firstOutputSlotId <= 1) {
                    for (int i = 0; i < firstOutputSlotId; i++) {

                        //the division gets the amount of available crafts that got removed,
                        //and the multiplication gets the amount real amount that needs to be removed from the slot
                        getSlot(i).incrementStackSize(
                                (-amountChanged / getCrafting()[getCraftingStack(slotChanged)].itemsNumber) *
                                        getCrafting()[getCraftingStack(getSlot(i))].itemsNumber);
                    }
                    checkCraft();
                } else {
                    isBetweenCraft = true;
                    for (int i = 0; i < firstOutputSlotId; i++) {
                        getSlot(i).setInsertness(false);
                        getSlot(i).setExtractness(false);
                        getSlot(i).incrementStackSize((-amountChanged / getCrafting()[getCraftingStack(slotChanged)].itemsNumber) *
                                getCrafting()[getCraftingStack(getSlot(i))].itemsNumber);
                    }
                }
            } else {
                checkCraft();
            }
        }
    }
}
