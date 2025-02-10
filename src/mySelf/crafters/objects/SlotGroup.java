package mySelf.crafters.objects;

import java.awt.*;
import java.util.Arrays;
import java.util.Iterator;

public class SlotGroup {
    Slot[] allSlots;
    boolean useGhostItem;

    public SlotGroup (Slot[] slots, boolean useGhostItem) {
        allSlots = slots.clone();
        for (Slot slot : slots) {
            slot.groupContaining = this;
        }
        this.useGhostItem = useGhostItem;
    }

    public SlotGroup (int slotAmount, boolean useGhostItem) {
        allSlots = new Slot[slotAmount];
        this.useGhostItem = useGhostItem;
    }

    public void drawSlots (Graphics g, int shiftX, int shiftY, boolean stretchStack) {
        for (Slot slot : allSlots) {
            slot.draw(g, shiftX, shiftY, stretchStack);
        }
    }

    public Slot getSlot (int id) {
        return allSlots[id];
    }

    public void setSlot (Slot slot, int id) {
        //granting that no slot is left thinking it is at this group
        if (allSlots[id] != null) {
            allSlots[id].setGroupContaining(null);
        }
        slot.groupContaining = this;
        allSlots[id] = slot;
    }

    //makes so that I don't need to check if the instance is from "CraftSlotGroup" first
    public boolean isOutput (Slot slotToTest) {
        return false;
    }

    public Slot[] getAllSlots () {
        return allSlots;
    }

    public void setAllSlots (Slot[] slots) {
        //granting that no slot is left thinking it is at this group
        for (Slot slot : allSlots) {
            if (slot != null) {
                slot.setGroupContaining(null);
            }
        }

        for (Slot slot : slots) {
            slot.groupContaining = this;
        }
        allSlots = slots.clone();
    }

    @Override
    public String toString () {
        String output = "slotGroup: {";

        Iterator<Slot> iterator =  Arrays.stream(allSlots).iterator();
        while(iterator.hasNext()) {
            Slot curSlot = iterator.next();
            output = output.concat("[" + (curSlot == null? null : curSlot.toString()) + "]" + (iterator.hasNext()? ", " : ""));
        }

        return output + "}";
    }
}
