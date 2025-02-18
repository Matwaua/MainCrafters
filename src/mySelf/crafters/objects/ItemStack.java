package mySelf.crafters.objects;

//the items you will deal with in game
public class ItemStack implements Cloneable{
    int itemsNumber;
    Item item;

    public ItemStack(Item item, int itemsNumber) {
        this.itemsNumber = itemsNumber;
        this.item = item;
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
