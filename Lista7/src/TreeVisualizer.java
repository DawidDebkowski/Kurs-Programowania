import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class TreeVisualizer extends Pane {
    public TreeVisualizer() {
        super();
        this.widthProperty().addListener((arg) -> {
            this.getChildren().clear();
            visualizeTree("asd");
        });
    }

    public Pane visualizeTree(String format) {
        System.out.println(this.getWidth());

        String[] nodes = { "2", "4", "3", "1", "2", "3", "4", "5", "6", "1", "2", "3", "4", "5", "6", "1", "2", "3", "4", "5", "6"};
        int depth = 0;
        int levelCount = 1;
        for (String node : nodes) {
            Text text = new Text(levelCount * this.getWidth() / (Math.pow(2, depth)+1), depth * 50 + 50, node);
            System.out.println(text.getX() + " " + text.getY() + " " + levelCount + " " + depth);
            levelCount++;
            if (levelCount > Math.pow(2, depth)) {
                depth++;
                levelCount = 1;
            }
            this.getChildren().add(text);
        }

        System.out.println(this.getWidth());
        return this;
    }
}
