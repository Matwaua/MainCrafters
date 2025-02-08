package mySelf.crafters.objects;

//the items you will deal with in game
public class ItemStack implements Cloneable{
    int itemsNumber;
    Item item;

    public ItemStack(int itemsNumber, Item item) {
        this.itemsNumber = itemsNumber;
        this.item = item;
    }
    public ItemStack(Item item) {
        this.itemsNumber = 1;
        this.item = item;
    }
    public ItemStack clone() {
        try {
            return (ItemStack) super.clone();
        } catch (CloneNotSupportedException e) {/**/}
        return null;
    }
}
