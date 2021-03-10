public class HTInternalNode<E> {
    private int value;
    private E right;
    private E left;

    public HTInternalNode() {
        value = 0;
        right = null;
        left = null;
    }

    public void setValue(int val) {
        value = val;
    }

    public int getValue() {
        return value;
    }

    public void setRight(E node) {
        right = node;
    }

    public E getRight() {
        return right;
    }

    public void setLeft(E node) {
        left = node;
    }

    public E getLeft() {
        return left;
    }
}
