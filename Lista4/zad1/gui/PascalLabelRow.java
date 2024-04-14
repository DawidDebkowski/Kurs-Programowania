package gui;

import java.awt.Label;
import java.awt.Panel;

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
        for(int i=0;i<=rowNumber;i++)
        {
            try
            {
                Label l = new Label(Integer.toString(wsp.wezElement(i)));
                this.add(l);
            }
            catch(BladZakresu e)
            {
                // nie widze powodu aby blad zakresu mogl wystapic w tej petli pozdrawiam
                Label l = new Label("Blad Zakresu");
                this.add(l);
            }
        }


    }
}
