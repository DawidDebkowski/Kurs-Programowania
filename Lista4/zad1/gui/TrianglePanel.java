package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.ScrollPane;

public class TrianglePanel extends Panel{
    Panel insidePanel;

    public TrianglePanel()
    {
        //super(ScrollPane.SCROLLBARS_ALWAYS);
        // this.setBackground(new Color(50));
    }

    public void showTriangle(int triangleSize)
    {
        if(insidePanel!=null)
            this.remove(insidePanel);
        insidePanel = new Panel();
        insidePanel.setBackground(new Color(242, 242, 242));
        insidePanel.setLayout(new GridLayout(triangleSize, 1));

        for(int i=0;i<triangleSize;i++)
        {
            PascalLabelRow testRow = new PascalLabelRow(i);
            testRow.Initialise();
            testRow.setFont(new Font(getName(), 0+2*(i%2), 12));
            insidePanel.add(testRow);
        }

        // insidePanel.setVisible(true);
        this.add(insidePanel);
        this.revalidate();
    }
}
