package mySelf.crafters.objects;

import java.awt.*;
import java.util.Arrays;
import java.util.function.Predicate;

public class Slot {
    int x, y, width, height;
    float scaleFactor;
    boolean selected = false;
    Color color;
    ItemStack stack;

    //this is set by the group when the Slot is added to it
    SlotGroup groupContaining;

    public Slot(int x, int y, int width, int height, float scaleFactor, Color color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.scaleFactor = scaleFactor;
        this.color = color;
    }

    public Slot(int x, int y, int size, float scaleFactor, Color color) {
        this.x = x;
        this.y = y;
        this.width = size;
        this.height = size;
        this.scaleFactor = scaleFactor;
        this.color = color;
    }

    public void draw(Graphics g, float scaleFactor, boolean stretchStack) {
        Color lastColor = g.getColor();

        //setting external rectangle
        if (selected) {
            g.setColor(color.brighter());
        } else {
            g.setColor(color);
        }
        g.fillRect(x, y, width, height);

        int stackWidth = Math.round(scaleFactor * width);
        int stackHeight = Math.round(scaleFactor * height);

        //setting internal rectangle centralized at a certain scale from the first
        g.setColor(color.darker());
        g.fillRect(Math.round(x + width * (1 - scaleFactor)/2),
                (int) (y + height * (1 - scaleFactor)/2),
                stackWidth, stackHeight);

        //resetting the last color
        g.setColor(lastColor);
        drawStack(g, stretchStack);

        //if stack size is zero, the item is a ghost, so it gets a semitransparent red square above it
        if (stack == null || stack.item == null || stack.item.texture == null) {
            return;
        }
        if (stack.itemsNumber == 0) {
            g.setColor(new Color(200, 0, 0, 35));
            g.fillRect(x + (width - stackWidth)/2, y + (height - stackHeight)/2, stackWidth, stackHeight);
            g.setColor(lastColor);
        }
    }

    public void draw(Graphics g, boolean stretchStack) {
        Color lastColor = g.getColor();

        //setting external rectangle
        if (selected) {
            g.setColor(color.brighter());
        } else {
            g.setColor(color);
        }
        g.fillRect(x, y, width, height);

        //setting internal rectangle centralized at a certain scale from the first
        g.setColor(color.darker());

        int stackWidth = Math.round(scaleFactor * width);
        int stackHeight = Math.round(scaleFactor * height);

        //for some reason, decentralizes if only one method of rounding is used
        g.fillRect(Math.round(x + width * (1 - scaleFactor)/2),
                (int) (y + height * (1 - scaleFactor)/2),
                stackWidth, stackHeight);

        //resetting the last color
        g.setColor(lastColor);
        drawStack(g, stretchStack);

        //if stack size is zero, the item is a ghost, so it gets a semitransparent red square above it
        if (stack == null || stack.item == null || stack.item.texture == null) {
            return;
        }
        if (stack.itemsNumber == 0) {
            g.setColor(new Color(200, 0, 0, 35));
            g.fillRect(x + (width - stackWidth)/2, y + (height - stackHeight)/2, stackWidth, stackHeight);
            g.setColor(lastColor);
        }
    }

    public void draw(Graphics g, int shiftX, int shiftY, boolean stretchStack) {
        Color lastColor = g.getColor();

        //setting external rectangle
        if (selected) {
            g.setColor(color.brighter());
        } else {
            g.setColor(color);
        }
        g.fillRect(x, y, width, height);

        //setting internal rectangle centralized at a certain scale from the first
        g.setColor(color.darker());

        int stackWidth = Math.round(scaleFactor * width);
        int stackHeight = Math.round(scaleFactor * height);

        //for some reason, decentralizes if only one method of rounding is used
        g.fillRect(Math.round(x + shiftX + width * (1 - scaleFactor)/2),
                (int) (y + shiftY + height * (1 - scaleFactor)/2),
                stackWidth, stackHeight);

        //resetting the last color
        g.setColor(lastColor);
        drawStack(g, shiftX, shiftY, stretchStack);

        if (stack == null || stack.item == null || stack.item.texture == null) {
            return;
        }
        if (stack.itemsNumber == 0) {
            g.setColor(new Color(200, 0, 0, 35));
            g.fillRect(x + (width - stackWidth)/2, y + (height - stackHeight)/2, stackWidth, stackHeight);
            g.setColor(lastColor);
        }
    }

    public void drawStack(Graphics g, boolean stretch) {
        //pass if texture does not exist
        if (stack == null || stack.item == null || stack.item.texture == null) {
            return;
        }

        //making sure it fills the slot
        int stackWidth = Math.round(scaleFactor * width);
        int stackHeight = Math.round(scaleFactor * height);

        //stopping stretch if requested by using only the smallest size of both dimensions
        if (!stretch) {
            stackWidth = Math.min(stackWidth, stackHeight);
            //noinspection SuspiciousNameCombination
            stackHeight = stackWidth;
        }

        //drawing the stack
        if (stack.itemsNumber == 0) {
            g.drawImage(stack.item.texture
                    , x + (width - stackWidth)/2, y + (height - stackHeight)/2
                    , stackWidth, stackHeight, null);
            return;
        }

        g.drawImage(stack.item.texture, x + (width - stackWidth)/2, y + (height - stackHeight)/2, stackWidth, stackHeight, null);

        //drawing the size of the stack
        //                />only so it is considered a string and not a number
        g.drawString("" + stack.itemsNumber, x + (int) ((1 - scaleFactor)*width/2),
                y + 9 + (int) ((1 - scaleFactor)*height/2));
    }

    public void drawStack(Graphics g, int shiftX, int shiftY, boolean stretch) {
        //pass if texture does not exist
        if (stack == null || stack.item == null || stack.item.texture == null) {
            return;
        }

        //making sure it fills the slot
        int stackWidth = Math.round(scaleFactor * width);
        int stackHeight = Math.round(scaleFactor * height);

        //stopping stretch if requested by using only the smallest size of both dimensions
        if (!stretch) {
            stackWidth = Math.min(stackWidth, stackHeight);
            //noinspection SuspiciousNameCombination
            stackHeight = stackWidth;
        }

        //drawing the stack, only runs if stack size is different then 0
        if (stack.itemsNumber == 0) {
            g.drawImage(stack.item.texture
                    , x + shiftX + (width - stackWidth)/2, y + shiftY + (height - stackHeight)/2
                    , stackWidth, stackHeight, null);
            return;
        }

        g.drawImage(stack.item.texture
                , x + shiftX + (width - stackWidth)/2, y + shiftY + (height - stackHeight)/2
                , stackWidth, stackHeight, null);

        //drawing the size of the stack, only runs if stack size is different then 0
        //                />only so it is considered a string and not a number
        g.drawString("" + stack.itemsNumber, x + shiftX + (int) ((1 - scaleFactor) * width / 2),
                y + shiftY + 9 + (int) ((1 - scaleFactor) * height / 2));
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void invertSelected() {
        selected = !selected;
    }

    public void setSelected(boolean set) {
        selected = set;
    }

    public ItemStack getItemStack() {return stack;}

    public void setItemStack(ItemStack stack) {
        this.stack = stack;
    }

    public void setStackSize(int num){
        this.stack.itemsNumber = num;
    }

    public int getStackSize() {
        return this.stack.itemsNumber;
    }

    public SlotGroup getGroupContaining () {
        return this.groupContaining;
    }

    public void setGroupContaining (SlotGroup group) {
        this.groupContaining = group;
    }

    //returns the end stackSize
    public int incrementStackSize(int increment) {
        this.stack.itemsNumber += increment;

        //making so it's not negative
        if (this.stack.itemsNumber <= 0) {
            if (!groupContaining.useGhostItem) {
                this.setItemStack(null);
                return 0;
            }
            return this.stack.itemsNumber = 0;
        }
        return this.stack.itemsNumber;
    }

    //returns the amount that got moved
    public int moveStack (Slot endSlot, int maxAmount, boolean skipSelfExtraOutputCheck) {
        int amountToMove = Math.min(this.getStackSize(), maxAmount);

        // if the endSlot isn't free and the items in the stacks isn't equal, or if nothing would be moved, do nothing
        if ((endSlot.stack != null && !endSlot.stack.item.equals(this.stack.item)) || amountToMove == 0 ||
                (endSlot.getGroupContaining().isOutput(endSlot))) {
            return 0;
        }

        //moving the number required or the amount that it has
        if (endSlot.stack == null) {
            endSlot.stack = this.stack.clone();
            endSlot.setStackSize(amountToMove);
        } else {
            endSlot.incrementStackSize(amountToMove);
        }
        this.incrementStackSize(-amountToMove);

        //will let the group finish the craft and Update
        if (this.getGroupContaining() instanceof CraftSlotGroup groupOfSelf) {

            //making sure this step only runs when taking from the output
            if (groupOfSelf.isOutput(this)) {

                //moving remaining of items, if not done, the inputs get subtracted and the remaining outputs get destroyed
                if (!skipSelfExtraOutputCheck) {
                    for (int i = groupOfSelf.firstOutputSlotId; i < groupOfSelf.getCrafting().length; i++) {
                        if (groupOfSelf.getSlot(i).getStackSize() > 0 && !this.equals(groupOfSelf.getSlot(i))) {
                            groupOfSelf.getSlot(i).moveStack(endSlot.getGroupContaining(), maxAmount, true);
                        }
                    }
                    groupOfSelf.craft(amountToMove);
                }
            }
            groupOfSelf.update();
        }

        if (endSlot.getGroupContaining() instanceof CraftSlotGroup groupOfArgument) {
            groupOfArgument.update();
        }
        return amountToMove;
    }

    public int moveStack(SlotGroup destGroup, int maxAmount, boolean skipSelfExtraOutputCheck) {
        int remainingToMove = maxAmount;

        int i = 1;
        do {
            for (Slot slot : destGroup.getAllSlots()) {
                if (remainingToMove == 0 || destGroup.isOutput(slot)) {
                    break;
                }
                if (slot.getItemStack() == null && i == 1) {
                    continue;
                }
                if (!(slot.getItemStack() == null) && i == 1 && !this.getItemStack().item.equals(slot.getItemStack().item)) {
                    continue;
                }
                    remainingToMove -= moveStack(slot, remainingToMove, skipSelfExtraOutputCheck);
            }
        } while (i++ < 2);
        return maxAmount - remainingToMove;
    }

    //checks if the point is inside a slot
    public boolean collidedWithSlot(int pointX, int pointY) {
        return getX() < pointX && getX() + getWidth() > pointX &&
                getY() < pointY && getY() + getHeight() > pointY;
    }

    @Override
    public String toString () {
        return "slot:" + getX() + " " + getY() + " with " + (getItemStack() == null? "Null" : getItemStack().toString());
    }
}
