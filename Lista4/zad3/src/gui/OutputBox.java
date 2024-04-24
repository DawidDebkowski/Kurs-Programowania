package gui;

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
        Label errorLabel = new Label(text);
        errorLabel.setStyle("-fx-background-color: #9E3636");
        lastOutput.getChildren().add(errorLabel);
    }

    public void showTriangle(int triangleSize)
    {
        Clear();
    }
}
