package mySelf.crafters;

import mySelf.crafters.objects.Slot;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MainCrafters extends JPanel implements KeyListener, ActionListener {
        final int rawWidth = 21;
        final int rawHeight = 21;
        final int tileSize = 32;
        final int windowWidth = rawWidth * tileSize;
        final int windowHeight = rawHeight * tileSize;
    Slot slot;

    Timer gameLoopTimer = new Timer(33, this);
    MainCrafters() {
        setPreferredSize(new Dimension(windowWidth, windowHeight));
        setBackground(Color.BLACK);
        addKeyListener(this);
        setFocusable(true);
        gameLoopTimer.start();
        slot = new Slot(32, 32, 32, 32,0.8F, Color.ORANGE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    public void draw (Graphics g) {
        slot.draw(g);
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
}
