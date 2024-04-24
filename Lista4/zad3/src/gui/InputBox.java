package gui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javafx.event.*;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

class ParamTextField extends VBox {
    private Label descLabel;
    private TextField inputTextField;

    public ParamTextField(String labelText)
    {
        super();

        inputTextField = new TextField();
        inputTextField.setAlignment(Pos.CENTER);
        
        descLabel = new Label(labelText);
        descLabel.setStyle("-fx-text-fill: white;");
        
        this.getChildren().addAll(descLabel, inputTextField);
    }

    public String getInputValue()
    {
        return inputTextField.getText();
    }
}

public class InputBox extends HBox{
    private int inputMaxWidth = 100;
    private int inputMaxHeigth = 40;

    private Button mkButton;
    private ParamTextField mainArgField;
    private ParamTextField otherArgField;
    
    private OutputBoxMethods outputBox;
    public InputBox()
    {
        setupGUI();

        this.setPadding(new Insets(10));
        this.setSpacing(10);
        this.setStyle("-fx-background-color: #336699;");
        // this.setBackground(new Background(Color.BLANCHEDALMOND));
    }
    
    public void setButtonFunction(EventHandler<ActionEvent> e)
    {
        mkButton.setOnAction(e);
    }

    public String getMainArgument()
    {
        return mainArgField.getInputValue();
    }

    public String getOtherArgument()
    {
        return otherArgField.getInputValue();
    }

    public void setOutputBox(OutputBoxMethods outputBox)
    {
        this.outputBox = outputBox;

        mkButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                try {
                    outputBox.showText(processInput());
                } catch (IOException e) {
                    outputBox.showError("Blad IO");
                    e.printStackTrace();
                }
            }
            
        });
    }

    public String processInput() throws IOException
    {
        String[] otherArgs = getOtherArgument().split(" ");
        String[] command = new String[2+otherArgs.length];
        
        command[0] = ".\\Test.exe";
        command[1] = getMainArgument();
        for(int i=2;i<otherArgs.length+2;i++)
        {
            command[i] = otherArgs[i-2];
        }
        
        String output = "";
        Process p = Runtime.getRuntime().exec(command);

        BufferedReader reader = new BufferedReader(
            new InputStreamReader(p.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            output += line;
        }

        return output;
    }

    public void setupGUI()
    {
        mkButton = new Button("Stworz");
        mkButton.setPrefSize(inputMaxWidth, inputMaxHeigth);
        
        mainArgField = new ParamTextField("Podaj numer wiersza: ");
        otherArgField = new ParamTextField("Podaj numery elementów: ");

        this.getChildren().addAll(mainArgField, otherArgField, mkButton);
        this.setAlignment(Pos.CENTER);
    }
}
