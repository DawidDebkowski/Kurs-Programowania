package gui;

import java.awt.*;
import java.awt.event.*;

import trojkatpascala.WierszTrojkataPascala;

class BasicWindowAdapter extends WindowAdapter {
    public void windowClosing(WindowEvent e) {
        System.exit(1);
    }
}

class MainFrame extends Frame {
    public MainFrame() {
        this.addWindowListener(new BasicWindowAdapter());
    }

    public void Initialise() {
        this.setBounds(100, 100, 400, 400);
    }
}

public class App {
    public static void main(String[] args) {
        MainFrame mf = new MainFrame();
        mf.Initialise();

        for(int i=0;i<10;i++)
        {
            PascalLabelRow testRow = new PascalLabelRow(i);
            testRow.Initialise();
    
            mf.add(testRow);
        }
        mf.setLayout(new GridLayout(10, 1));
        mf.setVisible(true);
    }
}