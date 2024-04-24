package gui;


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
