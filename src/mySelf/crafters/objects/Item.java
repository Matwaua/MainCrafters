package mySelf.crafters.objects;

import java.awt.*;

//the item object that gives the code mainly the information the itemStack
//needs about what it holds
public class Item {
    String name;
    Image texture;

    public Item(String name, Image texture) {
        this.name = name;
        this.texture = texture;
    }
}
