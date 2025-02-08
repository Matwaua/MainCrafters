package mySelf.crafters;

import mySelf.crafters.initializing.Items;
import mySelf.crafters.objects.ItemStack;
import mySelf.crafters.objects.Slot;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import mySelf.crafters.initializing.Slots;

public class MainCrafters extends JPanel implements KeyListener, ActionListener, MouseListener{
        static final int rawWidth = 25;
        static final int rawHeight = 15;
        public static final int tileSize = 64;
        static final int windowWidth = rawWidth * tileSize;
        static final int windowHeight = rawHeight * tileSize;

    Point click = new Point(0, 0);
    Slots allSlots = new Slots();
    public Items items = new Items();
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
        repaint();
    }

    public void draw (Graphics g) {

        //it is better looking
        g.setFont(g.getFont().deriveFont(Font.BOLD));
        allSlots.craftSlots.drawSlots(g, 0, 0, false);
        allSlots.invSlots.drawSlots(g, 0, 0, false);

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

    @Override
    public void mouseClicked(MouseEvent e) {
        Point clickPos = e.getPoint();

        //for debug purposes, more at method draw
        click.setLocation(clickPos);

        //searching for any slot in the clicked position
        Slot clickedSlot = null;
        for (int i = 0; i < allSlots.craftSlots.getArraySize() ; i++) {
            if (allSlots.craftSlots.getSlot(i).collidedWithSlot((int) clickPos.getX(), (int) clickPos.getY())) {
                clickedSlot = allSlots.craftSlots.getSlot(i);
                break;
            }
        }
        if (clickedSlot == null) {
            for (int i = 0; i < allSlots.invSlots.getArraySize(); i++) {
                if (allSlots.invSlots.getSlot(i).collidedWithSlot((int) clickPos.getX(), (int) clickPos.getY())) {
                    clickedSlot = allSlots.invSlots.getSlot(i);
                    break;
                }
            }
        }
        if (e.getButton() == 1) {
            //changing the selected slot to the clicked one
            if (clickedSlot != null) {
                try {
                    selectedSlot.invertSelected();
                } catch (NullPointerException r) {/**/}
                clickedSlot.setSelected(true);
                selectedSlot = clickedSlot;
            }
        }
        if (e.getButton() == 3) {
            try {
                selectedSlot.moveStack(clickedSlot, 999, true);
            } catch (NullPointerException r) {/**/}
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
