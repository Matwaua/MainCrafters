package mySelf.crafters.initializing;

import mySelf.crafters.objects.ItemStack;
import mySelf.crafters.objects.Slot;

import java.awt.*;

import static mySelf.crafters.MainCrafters.tileSize;
import static mySelf.crafters.initializing.InitializeAll.ITEMS;
import static mySelf.crafters.initializing.InitializeAll.SLOTGROUPS;

public class Slots implements CommonInitializing {

    @Override
    public void initializer() {

        for (int i = 0; i < 9; i++) {
            SLOTGROUPS.invSlots.setSlot(new Slot((8 + i) * tileSize, 10 * tileSize, tileSize, 0.8F, Color.LIGHT_GRAY), i);
        }
        SLOTGROUPS.craftSlots1.setSlot(
                new Slot(10 * tileSize, 7 * tileSize, tileSize, 0.8F, Color.LIGHT_GRAY), 0);
        SLOTGROUPS.craftSlots1.setSlot(
                new Slot(11 * tileSize, 7 * tileSize, tileSize, 0.8F, Color.LIGHT_GRAY), 1);
        SLOTGROUPS.craftSlots1.setSlot(
                new Slot(13 * tileSize, 7 * tileSize, 2 * tileSize, tileSize, 0.8F, Color.LIGHT_GRAY), 2);

        SLOTGROUPS.craftSlots2.setSlot(
                new Slot(10 * tileSize, 5 * tileSize,tileSize, 0.8F, Color.LIGHT_GRAY), 0);
        SLOTGROUPS.craftSlots2.setSlot(
                new Slot(12 * tileSize, 5 * tileSize, tileSize, 0.8F, Color.LIGHT_GRAY), 1);
        SLOTGROUPS.craftSlots2.setSlot(
                new Slot(13 * tileSize, 5 * tileSize, tileSize, 0.8F, Color.LIGHT_GRAY), 2);
        SLOTGROUPS.craftSlots2.setSlot(
                new Slot(14 * tileSize, 5 * tileSize, tileSize, 0.8F, Color.LIGHT_GRAY), 3);

    }
}
