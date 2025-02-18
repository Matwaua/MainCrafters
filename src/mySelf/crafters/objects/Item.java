package mySelf.crafters.objects;

import javax.swing.*;
import java.awt.*;

//the item object that gives the code mainly the information the itemStack
//needs about what it holds
public class Item {
    String name;
    Image texture;
    int maxStackSize;

    public Item(String name, int maxStackSize, Image texture) {
        this.name = name;
        this.texture = texture;
        this.maxStackSize = maxStackSize;
    }

    @Override
    public String toString () {
        return "item: " + name;
    }
}
