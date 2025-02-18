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
        SLOTGROUPS.allItemsGroup.setSlot(
                new Slot(tileSize, tileSize, tileSize, 0.8F, Color.LIGHT_GRAY), 0);
        SLOTGROUPS.allItemsGroup.getSlot(0).setItemStack(new ItemStack(ITEMS.blueFilledPot, 0));
        SLOTGROUPS.allItemsGroup.setSlot(
                new Slot(tileSize, 2 * tileSize, tileSize, 0.8F, Color.LIGHT_GRAY), 1);
        SLOTGROUPS.allItemsGroup.getSlot(1).setItemStack(new ItemStack(ITEMS.filledPot, 0));
        SLOTGROUPS.allItemsGroup.setSlot(
                new Slot(tileSize, 3 * tileSize, tileSize, 0.8F, Color.LIGHT_GRAY), 2);
        SLOTGROUPS.allItemsGroup.getSlot(2).setItemStack(new ItemStack(ITEMS.flowerSeeds, 0));
        SLOTGROUPS.allItemsGroup.setSlot(
                new Slot(tileSize, 4 * tileSize, tileSize, 0.8F, Color.LIGHT_GRAY), 3);
        SLOTGROUPS.allItemsGroup.getSlot(3).setItemStack(new ItemStack(ITEMS.flowerPot, 0));

        for (int i = 0; i < 9; i++) {
            SLOTGROUPS.invSlots.setSlot(
                    new Slot((8 + i) * tileSize, 10 * tileSize, tileSize, 0.8F, Color.LIGHT_GRAY), i);
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

        SLOTGROUPS.craftSlots3.setSlot(
                new Slot(10 * tileSize, 3 * tileSize, tileSize, 0.8F, Color.LIGHT_GRAY), 0);
        SLOTGROUPS.craftSlots3.setSlot(
                new Slot(11 * tileSize, 3 * tileSize, tileSize, 0.8F, Color.LIGHT_GRAY), 1);
        SLOTGROUPS.craftSlots3.setSlot(
                new Slot(13 * tileSize, 3 * tileSize, 2 * tileSize, tileSize, 0.8F, Color.LIGHT_GRAY), 2);
    }
}
