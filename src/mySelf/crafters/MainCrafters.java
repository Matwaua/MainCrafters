package mySelf.crafters;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MainCrafters extends JPanel implements KeyListener, ActionListener {
    int windowWidth = 21 * 32;
    int windowHeight = 21 * 32;

    Timer gameLoop = new Timer(33, this);
    MainCrafters() {
        setPreferredSize(new Dimension(windowWidth, windowHeight));
        setBackground(Color.BLACK);
        addKeyListener(this);
        setFocusable(true);
        gameLoop.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    public void draw (Graphics g) {

    }

    @Override
    public void paintComponent (Graphics g) {
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
