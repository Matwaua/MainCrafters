package mySelf.crafters.initializing;

import mySelf.crafters.objects.ItemStack;
import mySelf.crafters.objects.Slot;

import java.awt.*;

import static mySelf.crafters.MainCrafters.tileSize;

public class Slots implements CommonInitializing{
    public Slot[] invSlots = new Slot[9];
    public Slot[] craftSlots = new Slot[3];
    public Items items = new Items();

    @Override
    public void initializer() {
        items.initializer();
        for (int i = 0; i < 9; i++) {
            invSlots[i] = new Slot((8 + i) * tileSize, 10 * tileSize, tileSize, 0.8F, Color.LIGHT_GRAY);
        }
                //resolve class Items not loading before this one
        craftSlots[0] = new Slot(10 * tileSize, 7 * tileSize, tileSize, 0.8F, Color.LIGHT_GRAY);
        craftSlots[0].setItemStack(new ItemStack(1, items.filledPot));
        craftSlots[1] = new Slot(11 * tileSize, 7 * tileSize, tileSize, 0.8F, Color.LIGHT_GRAY);
        craftSlots[1].setItemStack(new ItemStack(1, items.flowerSeeds));
        craftSlots[2] = new Slot(13 * tileSize, 7 * tileSize, 2 * tileSize, tileSize, 0.8F, Color.LIGHT_GRAY);
        craftSlots[2].setItemStack(new ItemStack(1, items.flowerPot));
    }
}
