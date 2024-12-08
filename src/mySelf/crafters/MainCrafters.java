package mySelf.crafters;

import mySelf.crafters.objects.Item;
import mySelf.crafters.objects.ItemStack;
import mySelf.crafters.objects.Slot;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Objects;

public class MainCrafters extends JPanel implements KeyListener, ActionListener, MouseListener{
        static final int rawWidth = 25;
        static final int rawHeight = 15;
        static final int tileSize = 64;
        static final int windowWidth = rawWidth * tileSize;
        static final int windowHeight = rawHeight * tileSize;

    Point click = new Point(0, 0);
    Slot slot1;
    Slot slot2;
    Slot slot3;
    Slot selectedSlot;
    Slot[] slots = new Slot[3];

    //like the non-existence of an Item, only so it doesn't cause any errors
    static final Item air = new Item("air", null);
    Item flowerPot = new Item("flower pot", new ImageIcon(getClass().getResource("resources/images/flower_pot.png")).getImage());
    Item flowerSeeds = new Item("flower seeds", new ImageIcon(getClass().getResource("resources/images/flower_seeds.png")).getImage());
    Item filledPot = new Item("filled pot", new ImageIcon(getClass().getResource("resources/images/filled_pot.png")).getImage());

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
        gameLoopTimer.start();

        //creating the slots
        slot1 = new Slot(10 * tileSize, 7 * tileSize, tileSize, 0.8F, Color.LIGHT_GRAY);
        slot1.setItemStack(new ItemStack(1, filledPot));
        slot2 = new Slot(11 * tileSize, 7 * tileSize, tileSize, 0.8F, Color.LIGHT_GRAY);
        slot2.setItemStack(new ItemStack(1, flowerSeeds));
        slot3 = new Slot(13 * tileSize, 7 * tileSize, 2*tileSize, tileSize, 0.8F, Color.LIGHT_GRAY);
        slot3.setItemStack(new ItemStack(1, flowerPot));
        slots[0] = slot1;
        slots[1] = slot2;
        slots[2] = slot3;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        slot3.setStackSize(Math.min(slot1.getStackSize(), slot2.getStackSize()));
        repaint();
    }

    public void draw (Graphics g) {

        //it is better looking
        g.setFont(g.getFont().deriveFont(Font.BOLD));
        slot1.draw(g);
        slot2.draw(g);
        slot3.draw(g);

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
                if (collisionSlotPoint(slots[i], (int) clickPos.getX(), (int) clickPos.getY())) {

                    //changing the selected slot to the clicked one
                    try {selectedSlot.invertSelected();} catch (NullPointerException r) {/**/}
                    slots[i].setSelected(true);
                    slots[i].incrementStackSize(1);
                    selectedSlot = slots[i];
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
