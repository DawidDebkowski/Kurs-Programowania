package gui;

import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.ScrollPane;

public class TrianglePanel extends Panel{
    public TrianglePanel()
    {
        //super(Panel.SCROLLBARS_ALWAYS);
        //this.setBounds(100, 100, 400, 400);
    }

    public void showTriangle(int triangleSize)
    {
        //Panel insidePanel = new Panel();
        this.setLayout(new GridLayout(triangleSize, 1));

        for(int i=0;i<triangleSize;i++)
        {
            PascalLabelRow testRow = new PascalLabelRow(i);
            testRow.Initialise();

            this.add(testRow);
        }
        this.revalidate();
    }
}
