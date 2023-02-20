public class Tree<I extends Number> {
    public static void main(String[] args) {

        TTree<Integer> tree = new TTree<>();
        tree.add(10);
        tree.add(9);
        tree.add(2);
        tree.add(3);
        tree.add(4);
        
        System.out.println("\n\nIn order");
        tree.inOrder(tree.getRoot());

        System.out.println("\n\nIn post-order");
        tree.postOrder(tree.getRoot());

        System.out.println("\n\nIn pre-order");
        tree.preOrder(tree.getRoot());
    }
}