package mySelf.crafters.objects;

public class InstaCraftSlotGroup extends CraftSlotGroup{

    private boolean isBetweenCraft = false;

    //both input and output, if "useGhostItem" is true, then this will be the order of the items
    //                       if false, any order of input is accepted
    //the input stack amount is how many consumed during the crafting
    //the output stack amount is how many is created after crafting
    ItemStack[] crafting;

    public InstaCraftSlotGroup(Slot[] slots, boolean useGhostItem, int firstOutputSlotId, ItemStack[] crafting) {
        super(slots, useGhostItem, firstOutputSlotId);

        //creates a ghost item if requested and if the slot is empty
        //will not fill excess slots
        if (useGhostItem) {
            for (int i = 0; i < crafting.length; i++) {
                if (slots[i].getItemStack() == null) {
                    slots[i].setItemStack(new ItemStack(crafting[i].getItem(), 0));
                }
            }
        }
        this.crafting = crafting;
    }

    public InstaCraftSlotGroup(int slotAmount, boolean useGhostItem, int firstOutputSlotId, ItemStack[] crafting) {
        super(slotAmount, useGhostItem, firstOutputSlotId);
        this.crafting = crafting;
    }

    @Override
    public void setSlot(Slot slot, int id) {
        super.setSlot(slot, id);

        if (useGhostItem && crafting.length > id && slot.getItemStack() == null) {
            slot.setItemStack(new ItemStack(crafting[id].getItem(), 0));
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

    public void setFirstOutputSlotId(int firstOutputSlotId) {
        this.firstOutputSlotId = firstOutputSlotId;
    }

    public double getAvailableCrafts(Slot referenceSlot) {
        return (double) referenceSlot.getStackSize() / getCrafting()[getCraftingStack(referenceSlot)].itemsNumber;
    }

    public double getAvailableCrafts() {

        //this will store the items of the crafting array that was already checked
        byte sawItem = 0;
        double availableCrafts = 999;
        for (int i = 0; i < firstOutputSlotId; i++) {

            //this gets the position equivalent to the crafting array item
            byte slotPos = (byte) Math.pow(2, getCraftingStack(getSlot(i)));

            //checks if the crafting array item already is in an input
            if ((sawItem & slotPos) == 0) {
                availableCrafts = Math.min(availableCrafts, getAvailableCrafts(getSlot(i)));

                //this marks that this item is in an input slot
                sawItem += slotPos;
            }
            if (availableCrafts == 0) {return 0;}
        }

        //checks if all the crafting array items is present
        if (sawItem == (Math.pow(2, firstOutputSlotId) - 1)) {
            return availableCrafts;
        }
        return 0;
    }

    public void checkCraft() {
        int amountCraftable = (int) getAvailableCrafts();

        //optimization: only create a stack if there isn't a ghost item //
        //will update the output slots to have the amount craftable
        if (useGhostItem) {
            for (int i = firstOutputSlotId; i < getCrafting().length; i++) {
                getSlot(i).setStackSize(amountCraftable * getCrafting()[i].itemsNumber);
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

    @Override
    public void update(Slot slotChanged, int amountChanged) {

        if (isBetweenCraft) {
            boolean allEmpty = true;
            for (int i = firstOutputSlotId; i < getCrafting().length; i++) {
                if (getSlot(i).getStackSize() / crafting[getCraftingStack(getSlot(i))].itemsNumber >= 1) {
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
                //this gives the amount that could be crafted with this resource
                //it expects that the previous crafting was set correctly and only one slot was changed from there
                int lastRemainingCraft = (int) getAvailableCrafts();
                int craftsRemaining = (int) Math.min(
                        lastRemainingCraft, getAvailableCrafts(slotChanged));
                if (lastRemainingCraft >= craftsRemaining) {
                    for (int i = 0; i < firstOutputSlotId; i++) {
                        getSlot(i).incrementStackSize(
                                -(lastRemainingCraft - craftsRemaining) * getCrafting()[getCraftingStack(getSlot(i))].itemsNumber);
                    }
                }
            }
        } else {
            if (((InstaCraftSlotGroup) slotChanged.getGroupContaining()).isOutput(slotChanged)) {

                //if there is only one output slot, do the following
                if (crafting.length - firstOutputSlotId <= 1) {

                    //if the decimals of the remaining crafts is greater than the ones from before the craft,
                    //the player crafted an additional time
                    double removedCrafts = amountChanged / (double) crafting[firstOutputSlotId].itemsNumber;
                    double remainingCrafts = getSlot(firstOutputSlotId).getStackSize() / (double) crafting[firstOutputSlotId].itemsNumber;
                    if (amountChanged % crafting[firstOutputSlotId].itemsNumber != 0) {
                        double lastAvailableCrafts = getAvailableCrafts(getSlot(firstOutputSlotId).incrementStackSize(amountChanged));
                        if (lastAvailableCrafts - (int) lastAvailableCrafts < removedCrafts - (int) removedCrafts) {
                            removedCrafts++;
                        }
                    }

                    for (int i = 0; i < firstOutputSlotId; i++) {
                        getSlot(i).incrementStackSize((int) -removedCrafts * crafting[getCraftingStack(getSlot(i))].itemsNumber);
                    }

                    //checks if the output is a multiple of its crafting array equivalent,
                    //only so that the method doesn't remove any amount of the output items
                    if (getAvailableCrafts(getSlot(firstOutputSlotId)) - (int) getAvailableCrafts(getSlot(firstOutputSlotId)) == 0) {
                        checkCraft();
                    }
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
