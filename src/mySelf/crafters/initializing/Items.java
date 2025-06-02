package mySelf.crafters.initializing;

import mySelf.crafters.objects.Item;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Items implements CommonInitializing{

    public List<Item> ALL_THE_ITEMS = new ArrayList<>();

    public Item blueFilledPot;
    public Item redFilledPot;
    public Item filledPot;

    public Item flowerSeeds;

    public Item brownFlowerPot;
    public Item flowerPot;

    public Item brownFlowerPetals;
    public Item flowerPetals;

    private Item createItem(String name, String filePath, int maxStackSize) {
        Item newItem = new Item(name, maxStackSize, new ImageIcon(Items.class.getResource(filePath)).getImage());
        ALL_THE_ITEMS.add(newItem);
        return newItem;
    }
    @Override
    public void initializer() {
        String filesPlace = "../resources/images/png_images/";

        blueFilledPot = createItem("blue filled pot", filesPlace + "blue_filled_pot.png", 10);
        redFilledPot = createItem("red filled pot", filesPlace + "red_filled_pot.png", 10);
        filledPot = createItem("filled pot", filesPlace + "filled_pot.png", 10);

        flowerSeeds = createItem("flower seeds", filesPlace + "flower_seeds.png", 99);

        brownFlowerPot = createItem("brown flower pot", filesPlace + "brown_flower_pot.png", 5);
        flowerPot = createItem("flower pot", filesPlace + "flower_pot.png", 5);

        brownFlowerPetals = createItem("brown flower petals", filesPlace + "brown_flower_petals.png", 99);
        flowerPetals = createItem("flower petals", filesPlace + "red_flower_petals.png", 99);
    }

    public Item getItemByName(String itemName) {
        for (Item item : ALL_THE_ITEMS) {
            if (item.toString().substring(6).equals(itemName)) {
                return item;
            }
        }
        return null;
    }
}
