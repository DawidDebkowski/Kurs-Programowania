package gui;

import java.awt.Color;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.ScrollPane;

public class TrianglePanel extends Panel{
    Panel insidePanel;

    public TrianglePanel()
    {
        //super(ScrollPane.SCROLLBARS_ALWAYS);
        this.setBounds(100, 100, 400, 400);
        this.setBackground(new Color(50));
    }

    public void showTriangle(int triangleSize)
    {
        if(insidePanel!=null)
            this.remove(insidePanel);
        insidePanel = new Panel();
        insidePanel.setBackground(new Color(123));
        insidePanel.setLayout(new GridLayout(triangleSize, 1));

        for(int i=0;i<triangleSize;i++)
        {
            PascalLabelRow testRow = new PascalLabelRow(i);
            testRow.Initialise();

            insidePanel.add(testRow);
        }

        // insidePanel.setVisible(true);
        this.add(insidePanel);
        this.revalidate();
    }
}
