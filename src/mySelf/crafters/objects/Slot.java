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

    public void draw(Graphics g, float scaleFactor) {
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
    }
    public void draw(Graphics g) {
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
        drawStack(g, false);
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
    public void setItemStack(ItemStack stack) {
        this.stack = stack;
    }
    public void setStackSize(int num){
        this.stack.itemsNumber = num;
    }
    public int getStackSize() {
        return this.stack.itemsNumber;
    }
    public int incrementStackSize(int increment) {
        this.stack.itemsNumber += increment;

        //making so it's not negative
        if (this.stack.itemsNumber < 0) {
            return this.stack.itemsNumber = 0;
        }
        return this.stack.itemsNumber;
    }
}
