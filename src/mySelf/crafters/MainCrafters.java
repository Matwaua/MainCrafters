package mySelf.crafters;

import mySelf.crafters.initializing.*;
import mySelf.crafters.objects.CraftSlotGroup;
import mySelf.crafters.objects.Slot;
import mySelf.crafters.objects.SlotGroup;

import java.util.ArrayList;
import javax.swing.*;
import java.awt.Point;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.*;
import java.util.List;

import static mySelf.crafters.initializing.InitializeAll.*;

public class MainCrafters extends JPanel implements KeyListener, ActionListener, MouseListener{
        static final int rawWidth = 25;
        static final int rawHeight = 15;
        public static final int tileSize = 64;
        static final int windowWidth = rawWidth * tileSize;
        static final int windowHeight = rawHeight * tileSize;

    Point click = new Point(0, 0);
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

        InitializeAll initializeAll = new InitializeAll();
        initializeAll.initializer();

        //starting the frame timer
        gameLoopTimer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    public void draw(Graphics g) {

        //it is better looking
        g.setFont(g.getFont().deriveFont(Font.BOLD));
        for (SlotGroup slotGroup : SLOTGROUPS.everyGroup) {
            slotGroup.drawSlots(g, 0, 0, false);
        }

        //mouse click location, only for debugging
        g.setColor(Color.white);
        g.drawRect(click.x - 1, click.y - 1, 3, 3);
    }

    @Override
    public void paintComponent(Graphics g) {
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
        for (SlotGroup groupToCheck : SLOTGROUPS.everyGroup) {
            for (int i = 0; i < groupToCheck.getAllSlots().length ; i++) {
                if (groupToCheck.getSlot(i).collidedWithSlot((int) clickPos.getX(), (int) clickPos.getY())) {
                    clickedSlot = groupToCheck.getSlot(i);
                    break;
                }
            }
            if (clickedSlot != null) {break;}
        }
        if (e.getButton() == 1) {
            //changing the selected slot to the clicked one
            if (clickedSlot != null) {
                try {
                    selectedSlot.invertSelected();
                } catch (NullPointerException r) {/*ignored*/}
                clickedSlot.setSelected(true);
                selectedSlot = clickedSlot;
            }
        }
        if (e.getButton() == 3) {
            try {
                selectedSlot.moveStack(clickedSlot, 2, false);
            } catch (NullPointerException r) {/*ignored*/}
        }
        if (e.getButton() == 2) {
            try {
                clickedSlot.incrementStackSize(1);
            } catch (NullPointerException r) {/*ignored*/}
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
