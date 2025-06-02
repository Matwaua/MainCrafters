package mySelf.crafters.objects.crafting;

public class CraftingBase {
    CraftingItem[] inputItems;
    CraftingItem[] outputItems;
    String craftingType;
    boolean isInstaCrafting;
    int craftingTime;

    //these constructors copy the input array and uses the copy
    //if it uses "instaCrafting", then crafting time is ignored
    public CraftingBase (CraftingItem[] inputItems, CraftingItem[] outputItems, boolean isInstaCrafting, int craftingTime) {
        this.inputItems = inputItems.clone();
        this.outputItems = outputItems.clone();
        if (isInstaCrafting) {
            this.craftingTime = -1;
        } else {
            this.craftingTime = craftingTime;
        }
    }
}
