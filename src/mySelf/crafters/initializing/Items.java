package mySelf.crafters.initializing;

import mySelf.crafters.objects.Item;

import javax.swing.*;

public class Items implements CommonInitializing{

    public Item blueFilledPot;
    public Item filledPot;
    public Item flowerSeeds;
    public Item flowerPot;

    @Override
    public void initializer() {
        blueFilledPot = new Item("blue filled pot", 10, new ImageIcon(Items.class.getResource("../resources/images/blue_filled_pot.png")).getImage());
        filledPot = new Item("filled pot", 10, new ImageIcon(Items.class.getResource("../resources/images/filled_pot.png")).getImage());
        flowerSeeds = new Item("flower seeds", 99, new ImageIcon(Items.class.getResource("../resources/images/flower_seeds.png")).getImage());
        flowerPot = new Item("flower pot", 5, new ImageIcon(Items.class.getResource("../resources/images/flower_pot.png")).getImage());
    }
}
