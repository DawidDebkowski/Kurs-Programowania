package gui;

import trojkat.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class OutputBox extends VBox {
    private VBox lastOutput;

    public OutputBox()
    {
        super();
        lastOutput = null;
        //this.setAlignment(Pos.CENTER);
        this.setPadding(new Insets(5));
    }

    public void showTriangle(int triangleSize)
    {
        if(lastOutput != null)
           this.getChildren().remove(lastOutput);
        
        lastOutput = new VBox();
        for(int rowNumber=0;rowNumber<=triangleSize;rowNumber++)
        {

            String rowString = "";
            WierszTrojkataPascala wsp = new WierszTrojkataPascala(rowNumber);
            wsp.obliczWiersz();
            for(int el=0;el<=rowNumber;el++)
            {
                try
                {
                    rowString += "   ";
                    rowString += wsp.wezElement(el);
                }
                catch(BladZakresu e)
                {
                    rowString += e;
                }
            }

            Label rowLabel = new Label(rowString);
            lastOutput.getChildren().add(rowLabel);
        }
        lastOutput.setAlignment(Pos.CENTER);
        this.getChildren().add(lastOutput);
    }
}
