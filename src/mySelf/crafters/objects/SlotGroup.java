package mySelf.crafters.objects;

import java.awt.*;
import java.util.Arrays;
import java.util.Iterator;

public class SlotGroup {
    Slot[] allSlots;
    boolean useGhostItem;
    boolean useAsCrafting;

    public SlotGroup (Slot[] slots, boolean useGhostItem) {
        allSlots = slots.clone();
        for (Slot slot : slots) {
            slot.setGroupContaining(this);
        }
        this.useGhostItem = useGhostItem;
    }

    public SlotGroup (int slotAmount, boolean useGhostItem) {
        allSlots = new Slot[slotAmount];
        this.useGhostItem = useGhostItem;
    }

    public void drawSlots (Graphics g, int shiftX, int shiftY, boolean stretchStack) {
        for (Slot slot : allSlots) {
            if (slot != null) {
                slot.draw(g, shiftX, shiftY, stretchStack);
            }
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

    public Slot[] getAllSlots () {
        return allSlots;
    }

    //this method copies the input array and uses the copy
    public void setAllSlots (Slot[] slots) {
        //granting that no slot is left thinking it is at this group
        for (Slot slot : allSlots) {
            if (slot != null) {
                slot.setGroupContaining(null);
            }
        }

        for (Slot slot : slots) {
            slot.setGroupContaining(this);
        }
        allSlots = slots.clone();
    }

    //format:
    //slotGroup: {[curSlot.toString()], [null], [curSlot.toString()], ...[curSlot.toString()]}
    //inside brackets depends on the slot
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
