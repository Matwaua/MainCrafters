package mySelf.crafters.objects;

//the items you will deal with in game
public class ItemStack {
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
}
