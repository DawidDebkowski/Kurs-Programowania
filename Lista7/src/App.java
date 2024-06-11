public class App {
    public static void main(String[] args) throws Exception {
        Server<Integer> server = new Server<Integer>(10);
        BinaryTree<Integer> bartoszSlowik = new BinaryTree<Integer>();
        bartoszSlowik.insert(5);
        bartoszSlowik.insert(10);
        bartoszSlowik.insert(20);
        bartoszSlowik.insert(15);
        for (int i = 0; i <= 10; i++) {
            bartoszSlowik.insert(i);
        }

        TreeViewer view = new TreeViewer();
        view.show(args);
    }
}
