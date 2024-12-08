package mySelf.crafters.objects;

import org.w3c.dom.css.RGBColor;

import java.awt.*;

public class Slot {
    int x, y, width, height;
    float scaleFactor;
    Color color;

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
        g.setColor(color);
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
        g.setColor(color);
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
}
