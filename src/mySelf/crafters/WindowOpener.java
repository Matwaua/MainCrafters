package mySelf.crafters;

import javax.swing.*;

public class WindowOpener {
    public static void main(String[] args) {

        JFrame frame = new JFrame("Main Crafters");
        frame.setSize(MainCrafters.windowWidth, MainCrafters.windowHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        MainCrafters mainCrafters = new MainCrafters();
        frame.add(mainCrafters);
        frame.pack();
        mainCrafters.requestFocus();
        frame.setVisible(true);
    }
}
