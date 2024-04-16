package gui;

import java.awt.Button;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.*;

public class TriangleMaker extends Panel{
    TrianglePanel tPanel;

    public TriangleMaker()
    {
        tPanel = new TrianglePanel();
    }

    public void Initialise()
    {
        Panel buttonPanel = new Panel();

        TextField inputField = new TextField();
        Button makeButton = new Button("Stworz Trojkat");
        makeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tPanel.removeAll();
                tPanel.revalidate();
                tPanel.showTriangle(Integer.parseInt(inputField.getText()));
            }
        });

        buttonPanel.add(inputField);
        buttonPanel.add(makeButton);
        this.add(buttonPanel);
        this.add(tPanel);
        this.setLayout(new GridLayout(2, 1));
    }

    class MakeButtonListener implements ActionListener {
        int triangleSize;
        public MakeButtonListener(int triangleSize)
        {
            this.triangleSize = triangleSize;
        }
    
        public void actionPerformed(ActionEvent e) {
            
        }
        
    }
    class MakeButton extends Button {
    
    }

    private void MakeTriangle()
    {
        
    }
}
