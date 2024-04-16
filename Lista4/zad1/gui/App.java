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


        Panel buttonPanel = new Panel();

        TextField inputField = new TextField();
        Button makeButton = new Button("Stworz Trojkat");

        TrianglePanel tPanel = new TrianglePanel();
        
        makeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tPanel.showTriangle(Integer.parseInt(inputField.getText()));
                mf.pack();
            }
        });
        
        buttonPanel.add(inputField);
        buttonPanel.add(makeButton);
        mf.add(buttonPanel);
        mf.add(tPanel);
        
        mf.setLayout(new GridLayout(2, 1));
        mf.setVisible(true);
    }
}