package mySelf.crafters.initializing;

import mySelf.crafters.objects.Item;

import javax.swing.*;

public class Items implements CommonInitializing{

    public Item filledPot;
    public Item flowerSeeds;
    public Item flowerPot;

    @Override
    public void initializer() {
        flowerPot = new Item("flower pot", new ImageIcon(Items.class.getResource("../resources/images/flower_pot.png")).getImage());
        flowerSeeds = new Item("flower seeds", new ImageIcon(Items.class.getResource("../resources/images/flower_seeds.png")).getImage());
        filledPot = new Item("filled pot", new ImageIcon(Items.class.getResource("../resources/images/filled_pot.png")).getImage());
    }
}
