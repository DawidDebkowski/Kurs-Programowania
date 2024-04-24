package gui;


import javafx.event.*;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;

public class InputBox extends HBox{
    private int inputMaxWidth = 100;
    private int inputMaxHeigth = 40;

    private Button mkButton;
    private TextField inputTextField;
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

    public int getTriangleSize()
    {
        return Integer.parseInt(inputTextField.getText());
    }

    public void setupGUI()
    {
        mkButton = new Button("Stworz");
        inputTextField = new TextField();
        inputTextField.setMaxSize(inputMaxWidth, inputMaxHeigth);
        inputTextField.setAlignment(Pos.CENTER);
        mkButton.setPrefSize(inputMaxWidth, inputMaxHeigth);
        
        this.getChildren().addAll(inputTextField, mkButton);
        this.setAlignment(Pos.CENTER);
    }
}
