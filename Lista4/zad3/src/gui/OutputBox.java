package gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

interface OutputBoxMethods {

    public void showError(String text);
    public void showText(String text);
}

public class OutputBox extends VBox implements OutputBoxMethods{
    private VBox lastOutput;

    public OutputBox()
    {
        super();
        lastOutput = null;
        //this.setAlignment(Pos.CENTER);
        this.setPadding(new Insets(5));
        this.setStyle("-fx-background-color: #DFE5EB;");
    }

    private void Clear()
    {
        if(lastOutput != null)
           this.getChildren().remove(lastOutput);
        lastOutput = new VBox();
        lastOutput.setAlignment(Pos.CENTER);

        this.getChildren().add(lastOutput);
    }

    public void showError(String text)
    {
        Clear();
        //DRY!
        Label errorLabel = new Label(text);
        errorLabel.setStyle("-fx-background-color: #9E3636; -fx-text-fill: white;");
        errorLabel.setPadding(new Insets(10));
        lastOutput.getChildren().add(errorLabel);
    }

    public void showText(String text)
    {
        Clear();
        
        //DRY!
        Label textLabel = new Label(text);
        //textLabel.setStyle("-fx-background-color: #9A72A6; -fx-text-fill: white;");
        textLabel.setPadding(new Insets(10));
        lastOutput.getChildren().add(textLabel);
    }
}
