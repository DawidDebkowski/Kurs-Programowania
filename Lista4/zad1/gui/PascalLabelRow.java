package gui;

import java.awt.*;

import trojkatpascala.*;

public class PascalLabelRow extends Panel{
    private WierszTrojkataPascala wsp;
    private int rowNumber;
    
    public PascalLabelRow(int rowNumber)
    {
        this.rowNumber = rowNumber;
        wsp = new WierszTrojkataPascala(rowNumber);
    }

    public void Initialise()
    {
        wsp.obliczWiersz();
        String text = "";
        for(int i=0;i<=rowNumber;i++)
        {
            try
            {
                text+="   " + wsp.wezElement(i);
            }
            catch(BladZakresu e)
            {
                // nie widze powodu aby blad zakresu mogl wystapic w tej petli pozdrawiam
                Label l = new Label("Blad Zakresu");
                this.add(l);
            }
        }
        this.add(new Label(text));
    }
}
