package gui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.BoxLayout;

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
        buttonPanel.setPreferredSize(new Dimension(300, 40));
        buttonPanel.setBackground(new Color(5, 84, 242));

        TextField inputField = new TextField();
        Button makeButton = new Button("Stworz Trojkat");

        TrianglePanel tPanel = new TrianglePanel();
        
        makeButton.addActionListener(new ActionListener() {
            Label lastErrorLabel;

            public void actionPerformed(ActionEvent e) {
                if(lastErrorLabel!=null)
                {
                    buttonPanel.remove(lastErrorLabel);
                }
                try
                {
                    tPanel.showTriangle(Integer.parseInt(inputField.getText()));
                }
                catch(NumberFormatException ex)
                {
                    lastErrorLabel = new Label("Tylko liczby całkowite!");
                    lastErrorLabel.setBackground(new Color(242, 5, 5));
                    buttonPanel.add(lastErrorLabel);
                }
                mf.pack();
            }
        });
        
        tPanel.showTriangle(5);
        buttonPanel.add(inputField);
        buttonPanel.add(makeButton);
        mf.add(buttonPanel);
        mf.add(tPanel);
        
        mf.setLayout(new BoxLayout(mf, 1));
        mf.setVisible(true);
        mf.pack();
    }
}