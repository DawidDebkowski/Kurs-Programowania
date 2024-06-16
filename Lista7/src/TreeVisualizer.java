import java.util.ArrayList;

import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class TreeVisualizer extends Pane {
    private String lastFormat = " ";

    public TreeVisualizer() {
        super();
        this.widthProperty().addListener((arg) -> {
            this.getChildren().clear();
            visualizeTree(lastFormat);
        });
    }

    public Pane visualizeTree(String format) {
        lastFormat = format;
        System.out.println(format);

        drawNode(format.replaceAll(":", ""), 1, 0);

        // String[] nodes = format.split(" ");
        // int depth = 0;
        // int levelCount = 1;
        // for (String node : nodes) {
        //     Text text = new Text(levelCount * this.getWidth() / (Math.pow(2, depth)+1), depth * 50 + 50, node);
        //     System.out.println(text.getX() + " " + text.getY() + " " + levelCount + " " + depth);
        //     levelCount++;
        //     if (levelCount > Math.pow(2, depth)) {
        //         depth++;
        //         levelCount = 1;
        //     }
        //     this.getChildren().add(text);
        // }

        System.out.println(this.getWidth());
        return this;
    }

    public void drawNode(String tree, int index, int depth) {
        if (depth != 0)
            tree = tree.substring(1, tree.length() - 1);
        // else {
        //     lastFormat = tree;
        //     this.getChildren().clear();
        // }

        String value = tree.substring(0, tree.indexOf("("));
        tree = tree.substring(tree.indexOf("(")); //depth++

        int rowIndex = index - (int)Math.pow(2, depth) + 1;
        Text text = new Text(rowIndex * this.getWidth() / (Math.pow(2, depth)+1), depth * 50 + 50, value);
        this.getChildren().add(text);

        drawNode(tree, 2*index, depth+1); //left
        drawNode(tree, 2*index+1, depth+1); //right

    }
}
