package mySelf.crafters.objects;

import java.awt.*;

public class SlotGroup {
    Slot[] allSlots;

    public SlotGroup (Slot[] slots) {
        allSlots = slots.clone();
    }

    public void drawSlots (Graphics g, int shiftX, int shiftY, boolean stretchStack) {
        for (Slot slot : allSlots) {
            slot.draw(g, shiftX, shiftY, stretchStack);
        }
    }

    public SlotGroup (int slotAmount) {
        allSlots = new Slot[slotAmount];
    }

    public Slot getSlot (int id) {
        return allSlots[id];
    }

    public void setSlot (Slot slot, int id) {
        allSlots[id] = slot;
    }

    public Slot[] getAllSlots () {
        return allSlots;
    }

    public void setAllSlots (Slot[] slots) {
        allSlots = slots.clone();
    }

    public int getArraySize () {
        return allSlots.length;
    }
}
