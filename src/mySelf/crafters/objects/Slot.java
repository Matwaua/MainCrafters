package mySelf.crafters.objects;

import org.w3c.dom.css.RGBColor;

import javax.swing.*;
import java.awt.*;

public class Slot {
    int x, y, width, height;
    float scaleFactor;
    boolean selected = false;
    Color color;
    ItemStack stack;

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

        //setting internal rectangle centralized at a certain scale from the first
        g.setColor(color.darker());
        g.fillRect(Math.round(x + width * (1 - scaleFactor)/2),
                (int) (y + height * (1 - scaleFactor)/2),
                Math.round(scaleFactor * width),
                Math.round(scaleFactor * height));

        //resetting the last color
        g.setColor(lastColor);
        drawStack(g, stretchStack);
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

        //for some reason, decentralizes if only one method of rounding is used
        g.fillRect(Math.round(x + width * (1 - scaleFactor)/2),
                (int) (y + height * (1 - scaleFactor)/2),
                Math.round(scaleFactor * width),
                Math.round(scaleFactor * height));

        //resetting the last color
        g.setColor(lastColor);
        drawStack(g, stretchStack);
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

        //for some reason, decentralizes if only one method of rounding is used
        g.fillRect(Math.round(x + shiftX + width * (1 - scaleFactor)/2),
                (int) (y + shiftY + height * (1 - scaleFactor)/2),
                Math.round(scaleFactor * width),
                Math.round(scaleFactor * height));

        //resetting the last color
        g.setColor(lastColor);
        drawStack(g, shiftX, shiftY, stretchStack);
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

        //drawing the stack
        g.drawImage(stack.item.texture
                , x + shiftX + (width - stackWidth)/2, y + shiftY + (height - stackHeight)/2
                , stackWidth, stackHeight, null);

        //drawing the size of the stack
        //                />only so it is considered a string and not a number
        g.drawString("" + stack.itemsNumber, x + shiftX + (int) ((1 - scaleFactor)*width/2),
                y + shiftY + 9 + (int) ((1 - scaleFactor)*height/2));
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

    public int incrementStackSize(int increment, boolean allowRemove) {
        this.stack.itemsNumber += increment;

        //making so it's not negative
        if (this.stack.itemsNumber <= 0) {
            if (allowRemove) {
                this.setItemStack(null);
                return 0;
            }
            return this.stack.itemsNumber = 0;
        }
        return this.stack.itemsNumber;
    }

    public int moveStack (Slot endSlot, int amount, boolean allowRemove) {
        int amountToMove = Math.min(this.getStackSize(), amount);
        // if the items in the stacks isn't equal, and the endSlot isn't free, do nothing
        if ((endSlot.stack != null && !endSlot.stack.item.equals(this.stack.item)) || amountToMove == 0) {
            return 0;
        }
        //moving the number required or the amount that it has
        if (endSlot.stack == null) {
            endSlot.stack = this.stack.clone();
            endSlot.setStackSize(amountToMove);
        } else {
            endSlot.incrementStackSize(amountToMove, false);
        }
        this.incrementStackSize(-amountToMove, allowRemove);
        return amountToMove;
    }

    //checking if the point is inside a slot
    public boolean collidedWithSlot(int pointX, int pointY) {
        return getX() < pointX && getX() + getWidth() > pointX &&
                getY() < pointY && getY() + getHeight() > pointY;
    }
}
