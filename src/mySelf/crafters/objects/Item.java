package mySelf.crafters.objects;

import java.awt.*;

//the item object that gives the code mainly the information the itemStack
//needs about what it holds
public class Item {
    String name;
    Image texture;
    int maxStackSize;
    int maxDurability;
    boolean indestructible;

    //if it has no durability info, then it can't be broken in any way
    public Item(String name, int maxStackSize, Image texture) {
        this.name = name;
        this.texture = texture;
        this.maxStackSize = maxStackSize;
        this.maxDurability = 1;
        indestructible = true;
    }

    public Item(String name, int maxStackSize, int maxDurability, Image texture) {
        this.name = name;
        this.texture = texture;
        this.maxStackSize = maxStackSize;
        this.maxDurability = maxDurability;
        this.indestructible = false;
    }

    @Override
    public String toString () {
        return "item: " + name;
    }
}
