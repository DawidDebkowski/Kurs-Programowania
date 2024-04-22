package gui;


import javafx.event.*;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;

public class InputBox extends HBox{
    private int inputMaxWidth = 100;
    private int inputMaxHeigth = 40;

    private Button mkButton;
    private TextArea inputTextArea;
    public InputBox()
    {
        setupGUI();

        this.setPadding(new Insets(10));
        this.setSpacing(10);
        this.setStyle("-fx-background-color: #336699;");
    }
    
    public void setButtonFunction(EventHandler<ActionEvent> e)
    {
        mkButton.setOnAction(e);
    }

    public int getTriangleSize()
    {
        return Integer.parseInt(inputTextArea.getText());
    }

    public void setupGUI()
    {
        mkButton = new Button("Stworz");
        inputTextArea = new TextArea();
        inputTextArea.setMaxSize(inputMaxWidth, inputMaxHeigth);
        mkButton.setPrefSize(inputMaxWidth, inputMaxHeigth);
        
        this.getChildren().addAll(inputTextArea, mkButton);
        this.setAlignment(Pos.CENTER);
    }
}
