package mySelf.crafters.objects.crafting;

import mySelf.crafters.objects.Item;

public class CraftingItem {
    Item item;
    int amount;
    int durabilityMin;
    int durabilityMax;

    public CraftingItem (Item item, int amount) {
        this.item = item;
        this.amount = amount;
        durabilityMin = 0;
        durabilityMax = Integer.MAX_VALUE;
    }

    public CraftingItem (Item item, int amount, int durabilityMin) {
        this.item = item;
        this.amount = amount;
        this.durabilityMin = durabilityMin;
        durabilityMax = Integer.MAX_VALUE;
    }

    public CraftingItem (Item item, int amount, int durabilityMin, int durabilityMax) {
        this.item = item;
        this.amount = amount;
        this.durabilityMin = durabilityMin;
        this.durabilityMax = durabilityMax;
    }
}
