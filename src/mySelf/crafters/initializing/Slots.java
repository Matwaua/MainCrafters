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
        for (int i = 0; i < ITEMS.ALL_THE_ITEMS.size(); i++) {
            SLOTGROUPS.allItemsGroup.setSlot(
                    new Slot(tileSize, (2 + i) * tileSize, tileSize, 0.8F, Color.LIGHT_GRAY), i);
            SLOTGROUPS.allItemsGroup.getSlot(i).setItemStack(new ItemStack(ITEMS.ALL_THE_ITEMS.get(i), 0));
        }

        for (int i = 0; i < 9; i++) {
            SLOTGROUPS.invSlots.setSlot(
                    new Slot((8 + i) * tileSize, 10 * tileSize, tileSize, 0.8F, Color.LIGHT_GRAY), i);
        }
        SLOTGROUPS.flowerPotCraftingGroup.setSlot(
                new Slot(10 * tileSize, 7 * tileSize, tileSize, 0.8F, Color.LIGHT_GRAY), 0);
        SLOTGROUPS.flowerPotCraftingGroup.setSlot(
                new Slot(11 * tileSize, 7 * tileSize, tileSize, 0.8F, Color.LIGHT_GRAY), 1);
        SLOTGROUPS.flowerPotCraftingGroup.setSlot(
                new Slot(13 * tileSize, 7 * tileSize, 2 * tileSize, tileSize, 0.8F, Color.LIGHT_GRAY), 2);

        SLOTGROUPS.flowerPotDECraftingGroup.setSlot(
                new Slot(10 * tileSize, 5 * tileSize,tileSize, 0.8F, Color.LIGHT_GRAY), 0);
        SLOTGROUPS.flowerPotDECraftingGroup.setSlot(
                new Slot(12 * tileSize, 5 * tileSize, tileSize, 0.8F, Color.LIGHT_GRAY), 1);
        SLOTGROUPS.flowerPotDECraftingGroup.setSlot(
                new Slot(13 * tileSize, 5 * tileSize, 2 * tileSize, tileSize, 0.8F, Color.LIGHT_GRAY), 2);

        SLOTGROUPS.craftSlots3.setSlot(
                new Slot(10 * tileSize, 3 * tileSize, tileSize, 0.8F, Color.LIGHT_GRAY), 0);
        SLOTGROUPS.craftSlots3.setSlot(
                new Slot(11 * tileSize, 3 * tileSize, tileSize, 0.8F, Color.LIGHT_GRAY), 1);
        SLOTGROUPS.craftSlots3.setSlot(
                new Slot(13 * tileSize, 3 * tileSize, 2 * tileSize, tileSize, 0.8F, Color.LIGHT_GRAY), 2);

        SLOTGROUPS.flowerPetalsCraftingGroup.setSlot(
                new Slot(10 * tileSize, 1 * tileSize, tileSize, 0.8F, Color.LIGHT_GRAY), 0);
        SLOTGROUPS.flowerPetalsCraftingGroup.setSlot(
                new Slot(11 * tileSize, 1 * tileSize, tileSize, 0.8F, Color.LIGHT_GRAY), 1);
        SLOTGROUPS.flowerPetalsCraftingGroup.setSlot(
                new Slot(13 * tileSize, 1 * tileSize, 2 * tileSize, tileSize, 0.8F, Color.LIGHT_GRAY), 2);
    }
}
