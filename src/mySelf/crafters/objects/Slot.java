package mySelf.crafters.objects;

import java.awt.*;

public class Slot {
    int x, y, width, height;
    float scaleFactor;
    boolean selected = false;
    boolean canExtract = true;
    boolean canInsert = true;
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

    public ItemStack getItemStack() {
        return stack;
    }

    public Item getItem() {
        if (getItemStack() == null) {
            return null;
        }
        return getItemStack().getItem();
    }

    public void setItem(Item item) {
        this.getItemStack().setItem(item);
    }

    public void setItemStack(ItemStack stack) {
        this.stack = stack;
    }

    public int getStackSize() {
        if (this.getItemStack() == null) {
            return 0;
        }
        return this.getItemStack().itemsNumber;
    }

    public void setStackSize(int num) {
        //this uses the system of removing or not the stack depending on the group
        //subtracts the opposite of the stack size from itself, so it increments, from 0, the number specified
        this.incrementStackSize(-getStackSize() + num);
    }

    public int getMaxStackSize() {
        return this.getItemStack().getItem().maxStackSize;
    }

    public SlotGroup getGroupContaining() {
        return this.groupContaining;
    }

    public void setGroupContaining (SlotGroup group) {
        this.groupContaining = group;
    }

    public boolean canExtract() {
        return canExtract;
    }

    public void setExtractness(boolean canExtract) {
        this.canExtract = canExtract;
    }

    public boolean CanInsert() {
        return canInsert;
    }

    public void setInsertness(boolean canInsert) {
        this.canInsert = canInsert;
    }

    //returns the end stackSize
    public int incrementStackSize(int increment) {
        if (this.stack == null) {
            return 0;
        }
        this.getItemStack().itemsNumber += increment;

        //making so it's not negative
        if (this.getStackSize() <= 0) {
            if (!getGroupContaining().useGhostItem) {
                this.setItemStack(null);
                return 0;
            }
            //not using "setStackSize" because of recursion
            this.getItemStack().itemsNumber = 0;
            return 0;
        }
        return this.getStackSize();
    }

    //returns the amount that got moved
    public int moveStack (Slot endSlot, int maxAmount) {

        //if there is no item, if there is a prohibition on any of the two slots
        //  or if the items on both don't match when the end slot has an item, don't proceed
        if (this.getItemStack() == null  || !endSlot.CanInsert() || !this.canExtract() || this.getStackSize() == 0
                || (endSlot.getItemStack() != null && !this.getItemStack().getItem().equals(endSlot.getItemStack().getItem()))) {
            return 0;
        }

        //checking how many items can be moved
        int amountToMove = maxAmount;
        amountToMove = Math.min(amountToMove, this.getStackSize());
        if (endSlot.getItemStack() != null) {
            amountToMove = Math.min(amountToMove, endSlot.getMaxStackSize() - endSlot.getStackSize());
        } else {
            amountToMove = Math.min(amountToMove, this.getMaxStackSize());
        }

        if (endSlot.getItemStack() == null) {
            endSlot.setItemStack( new ItemStack(this.getItem(), amountToMove));
        } else {
            endSlot.incrementStackSize(amountToMove);
        }
        this.incrementStackSize(-amountToMove);

        if (this.getGroupContaining() instanceof InstaCraftSlotGroup thisCraftGroup) {
            thisCraftGroup.update(this, amountToMove);
        }

        if (endSlot.getGroupContaining() instanceof InstaCraftSlotGroup thisCraftGroup) {
            thisCraftGroup.update(endSlot, amountToMove);
        }

        return amountToMove;
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
