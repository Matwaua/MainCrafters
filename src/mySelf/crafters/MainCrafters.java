package mySelf.crafters;

import mySelf.crafters.objects.Slot;
import mySelf.crafters.initializing.Items;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;

import mySelf.crafters.initializing.Slots;

public class MainCrafters extends JPanel implements KeyListener, ActionListener, MouseListener{
        static final int rawWidth = 25;
        static final int rawHeight = 15;
        public static final int tileSize = 64;
        static final int windowWidth = rawWidth * tileSize;
        static final int windowHeight = rawHeight * tileSize;

    Point click = new Point(0, 0);
    Slots allSlots = new Slots();
    Slot selectedSlot;

    //approximately 30 frames per second
    Timer gameLoopTimer = new Timer(33, this);
    MainCrafters() {

        //setting the component
        setPreferredSize(new Dimension(windowWidth, windowHeight));
        setBackground(Color.BLACK);
        addKeyListener(this);
        addMouseListener(this);
        setFocusable(true);

        //starting the frame timer
        allSlots.initializer();
        gameLoopTimer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
            allSlots.craftSlots[2].setStackSize(Math.min(allSlots.craftSlots[0].getStackSize(), allSlots.craftSlots[1].getStackSize()));
            repaint();
    }

    public void draw (Graphics g) {

        //it is better looking
        g.setFont(g.getFont().deriveFont(Font.BOLD));
        for (Slot slot : allSlots.craftSlots){
            try {
                slot.draw(g, false);
            } catch (NullPointerException e) {/**/}
        }
        for(Slot slot : allSlots.invSlots) {
            try {
                slot.draw(g, false);
            } catch (NullPointerException e) {/**/}
        }
        //mouse click location, only for debugging
        g.setColor(Color.white);
        g.drawRect(click.x - 1, click.y - 1, 3, 3);
    }

    @Override
    public void paintComponent (Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    //checking if the point is inside a slot
    public boolean collisionSlotPoint(Slot slot, int pointX, int pointY) {
        return slot.getX() < pointX && slot.getX() + slot.getWidth() > pointX &&
                slot.getY() < pointY && slot.getY() + slot.getHeight() > pointY;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Point clickPos = e.getLocationOnScreen();

        //fixing where it considers the click to had happen
        clickPos.translate(-6, -30);

        //for debug purposes, more at method draw
        click.setLocation(clickPos);
        if (e.getButton() == 1) {

            //searching for any slot in the clicked position
            for (int i = 0; i <= 1 ; i++) {
                if (collisionSlotPoint(allSlots.craftSlots[i], (int) clickPos.getX(), (int) clickPos.getY())) {

                    //changing the selected slot to the clicked one
                    try {selectedSlot.invertSelected();} catch (NullPointerException r) {/**/}
                    allSlots.craftSlots[i].setSelected(true);
                    allSlots.craftSlots[i].incrementStackSize(1);
                    selectedSlot = allSlots.craftSlots[i];
                    break;
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
