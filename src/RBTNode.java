public class RBTNode<Integer, String> {
    Integer key;
    String value;
    RBTNode<Integer, String> left, right, parent;
    boolean isLeftChild, black;

    public RBTNode(Integer key, String value) {
        this.key = key;
        this.value = value;
        left = right = parent = null;
        black = false;
        isLeftChild = false;
    }

}