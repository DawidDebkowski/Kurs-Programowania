import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class TreeVisualizer extends Pane {
    private String lastFormat = "()";
    private final double CIRCLE_RADIUS = 23;

    public TreeVisualizer() {
        super();
        this.widthProperty().addListener((arg) -> {
            visualizeTree(lastFormat);
        });
    }

    public Pane visualizeTree(String format) {
        this.getChildren().clear();

        lastFormat = format;
        format = format.substring(1, format.length() - 1);
        format = format.replaceAll(":", "");
        System.out.println(format);

        drawNode(format, 1, 0);

        System.out.println(this.getWidth());
        return this;
    }

    private int getRightIndex(String substring) {
        int depth = 0;
        for (int i = 0; i < substring.length(); i++) {
            Character c = substring.charAt(i);
            if (c == '(') {
                depth++;
            } else if (c == ')') {
                depth--;
            }

            if (depth == 0 && i + 1 < substring.length()) {
                return i + 1;
            }
        }
        return -1;
    }

    public void drawNode(String tree, int index, int depth) {
        if (tree.length() < 2) {
            return;
        }

        if (tree.charAt(1) == ')')
            return;

        // nawiasy zewnetrzne
        if (depth != 0)
            tree = tree.substring(1, tree.length() - 1); // + ;

        String value = tree.substring(0, tree.indexOf("("));
        tree = tree.substring(tree.indexOf("(")); // depth++

        int rowIndex = index - (int) Math.pow(2, depth) + 1;
        double x = rowIndex * this.getWidth() / (Math.pow(2, depth) + 1);
        double y = depth * 50 + 50;
        if (depth != 0) {
            rowIndex = (index) / 2 - (int) Math.pow(2, depth - 1) + 1;
            double parentX = rowIndex * this.getWidth() / (Math.pow(2, depth - 1) + 1);
            double parentY = (depth - 1) * 50 + 50;

            Line line = new Line(parentX, parentY, x, y);
            this.getChildren().add(0, line);
        }
        Circle circle = new Circle(x, y, CIRCLE_RADIUS, Color.web("#57211D"));
        Text text = new Text(x - CIRCLE_RADIUS/2, y, value);
        text.setTextAlignment(TextAlignment.CENTER);
        text.setFill(Color.WHITE);
        text.setWrappingWidth(CIRCLE_RADIUS);
        this.getChildren().addAll(circle, text);

        int sRightIndex = getRightIndex(tree);
        if (sRightIndex == -1) {
            return;
        }
        System.out.println("v: " + value + " t: " + tree);
        drawNode(tree.substring(0, sRightIndex), 2 * index, depth + 1); // left
        drawNode(tree.substring(sRightIndex, tree.length()), 2 * index + 1, depth + 1); // right

    }
}
