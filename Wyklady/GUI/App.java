import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;
import java.util.random.RandomGenerator;
import javax.swing.plaf.basic.BasicOptionPaneUI.ButtonActionListener;

class WindowAdapterDemo extends WindowAdapter {
    public void windowClosing(WindowEvent e) {System.err.println("ej");System.exit(0);}
    // {System.out.println("Hello world!");}
    public void windowActivated(WindowEvent e) {System.err.println("ej");}
}

/**
 * Addition
 */ 
class Addition extends Button{
    Addition(Label target, Boolean works)
    {
        super("+");

        addActionListener(new AdditionAction(target, works));
    }
}

class AdditionAction implements ActionListener
{
    Label target;
    Boolean works;
    AdditionAction(Label target, Boolean works)
    {
        this.works = works;
        this.target = target;
    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
        int result = 0;
        try
        {
            int a = Integer.parseInt(App.firstNumberTF.getText());
            int b = Integer.parseInt(App.secondNumberTF.getText());

            result = a+b;
            works = true;
        }
        catch (NumberFormatException aha)
        {
            works = false;
            System.err.println(aha);
        }
        if(works)
            target.setText(Integer.toString(result));
        else
            target.setText("Hello World!");
    }

}

class MyMouse extends MouseAdapter {

    Button target;

    MyMouse(Button a)
    {
        target = a;
    }

    public void mouseEntered(MouseEvent e)
    {
        target.setBounds((int)Math.floor(Math.random()), 100, 100, 100);
    }
}

public class App {
    public static TextField firstNumberTF = new TextField();
    public static TextField secondNumberTF = new TextField();
    public static void main(String[] args) {
        Frame okno = new Frame("Aplikacja");

        Label resultLabel = new Label();
        resultLabel.setBackground(new Color(12, 234, 123));
        Addition additionButton = new Addition(resultLabel, true);
        
        additionButton.addMouseListener(new MyMouse(additionButton));
        okno.add(firstNumberTF);
        okno.add(secondNumberTF);
        okno.add(additionButton);
        okno.add(resultLabel);
        

        okno.addWindowListener(new WindowAdapterDemo());
        okno.setBounds(100, 100, 400, 400);
        okno.setLayout(new FlowLayout(FlowLayout.LEFT));

        okno.setVisible(true);
    }
}
