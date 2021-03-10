public class HTLeafNode<E> {
    private E value;
    private int frequency;

    public HTLeafNode(E val) {
        value = val;
        frequency = 0;
    }

    public E getValue() {
        return value;
    }

    public void setValue(E value) {
        this.value = value;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }
}
