package gui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

public class InputProcesser {
    private InputBox inBox;
    private OutputBoxMethods outBox;

    public InputProcesser(InputBox inputBox, OutputBoxMethods outputBox) {
        inBox = inputBox;
        outBox = outputBox;
    }

    public void SetupInButton(Stage stageToResize) {
        inBox.setButtonFunction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                try {
                    outBox.showText(processInput());
                } catch (IOException e) {
                    outBox.showError("Blad IO");
                    e.printStackTrace();
                }
                stageToResize.sizeToScene();
            }

        });
    }

    public String processInput() throws IOException {
        String[] otherArgs = inBox.getOtherArgument().split(" ");
        String[] command = new String[2 + otherArgs.length];

        command[0] = ".\\bin\\trojkat\\Test.exe";
        command[1] = inBox.getMainArgument();
        for (int i = 2; i < otherArgs.length + 2; i++) {
            command[i] = otherArgs[i - 2];
        }

        String output = "";
        Process p = Runtime.getRuntime().exec(command);

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(p.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            output += line + "\n";
        }

        return output;
    }

}
