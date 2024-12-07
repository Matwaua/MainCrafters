package mySelf.crafters;

import javax.swing.*;

public class WindowOpener {
    public static void main(String[] args) {
        int rowCount = 21;
        int collumCount = 19;
        int tileSize = 32;
        int boardWidth = collumCount * tileSize;
        int boardHeight = rowCount * tileSize;

        JFrame frame = new JFrame("Main Crafters");
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        MainCrafters mainCrafters = new MainCrafters();
        frame.add(mainCrafters);
        frame.pack();
        mainCrafters.requestFocus();
        frame.setVisible(true);
    }
}
