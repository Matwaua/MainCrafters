package mySelf.crafters.objects;

import java.util.ArrayList;
import java.util.List;

//the items you will deal with in game
public class ItemStack implements Cloneable{
    Item item;
    int itemsNumber;
    int durability = 1;
    List<String> properties = new ArrayList<>();

    public ItemStack(Item item, int itemsNumber) {
        this.itemsNumber = itemsNumber;
        this.item = item;
        durability = item.maxDurability;
    }

    public ItemStack(Item item, int itemsNumber, int durability) {
        this.itemsNumber = itemsNumber;
        this.item = item;

        //don't makes sense to set durability to an indestructible item
        if (!item.indestructible) {
            this.durability = durability;
        }
    }

    public ItemStack(Item item) {
        this.itemsNumber = 1;
        this.item = item;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
       this.item = item;
    }

    public int getDurability() {
        return durability;
    }

    public void setDurability(int durability) {
        this.durability = durability;
    }

    public List<String> getProperties() {
        return properties;
    }

    public void setProperties(List<String> properties) {
        //like cloning, but without the private method
        this.properties = properties.subList(0, properties.size() - 1);
    }

    public boolean addProperty(String property) {
        if (!hasProperty(property)){
            properties.add(property);
            return true;
        }
        return false;
    }

    public boolean removeProperty(String property) {
        return properties.remove(property);
    }

    public boolean hasProperty(String property) {
        return properties.contains(property);
    }

    public ItemStack clone() {
        try {
            return (ItemStack) super.clone();
        } catch (CloneNotSupportedException e) {/**/}
        return null;
    }

    @Override
    public String toString () {
        return "itemStack: " + "[" + (item == null? null : item.toString()) + "," + itemsNumber + "]";
    }
}
