package gui;

import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.ScrollPane;

public class TrianglePanel extends ScrollPane{
    private int triangleSize;

    public TrianglePanel(int triangleSize)
    {
        super(ScrollPane.SCROLLBARS_ALWAYS);
        this.setBounds(100, 100, 400, 400);
        this.triangleSize = triangleSize;
    }

    public void showTriangle()
    {
        Panel insidePanel = new Panel();
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
