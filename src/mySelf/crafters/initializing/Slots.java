package mySelf.crafters.initializing;

import mySelf.crafters.objects.ItemStack;
import mySelf.crafters.objects.Slot;
import mySelf.crafters.objects.SlotGroup;

import java.awt.*;

import static mySelf.crafters.MainCrafters.tileSize;

public class Slots implements CommonInitializing {
    public SlotGroup invSlots = new SlotGroup(9);
    public SlotGroup craftSlots = new SlotGroup(3);
    public Items items = new Items();

    @Override
    public void initializer() {
        items.initializer();
        for (int i = 0; i < 9; i++) {
            invSlots.setSlot(new Slot((8 + i) * tileSize, 10 * tileSize, tileSize, 0.8F, Color.LIGHT_GRAY), i);
        }
        craftSlots.setSlot(new Slot(10 * tileSize, 7 * tileSize, tileSize, 0.8F, Color.LIGHT_GRAY), 0);
        craftSlots.getSlot(0).setItemStack(new ItemStack(1, items.filledPot));
        craftSlots.setSlot(new Slot(11 * tileSize, 7 * tileSize, tileSize, 0.8F, Color.LIGHT_GRAY), 1);
        craftSlots.getSlot(1).setItemStack(new ItemStack(1, items.flowerSeeds));
        craftSlots.setSlot(new Slot(13 * tileSize, 7 * tileSize, 2 * tileSize, tileSize, 0.8F, Color.LIGHT_GRAY), 2);
        craftSlots.getSlot(2).setItemStack(new ItemStack(1, items.flowerPot));
    }
}
