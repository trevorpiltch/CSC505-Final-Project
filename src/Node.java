class Node implements Comparable{
    private Character ch;
    private Integer freq;
    private Node left = null, right = null;

    public Node(Character ch, Integer freq) {
        this.ch = ch;
        this.freq = freq;
    }

    public Node(Character ch, Integer freq, Node left, Node right) {
        this.ch = ch;
        this.freq = freq;
        this.left = left;
        this.right = right;
    }

    public Node(Integer freq) {
        this.freq = freq;
    }

    public Character getValue() {
        return ch;
    }

    public void setValue(Character value) {
        this.ch = value;
    }

    public int getFrequency() {
        return freq;
    }

    public void setFrequency(int frequency) {
        this.freq = frequency;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node newNode) {
        left = newNode;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node newNode) {
        right = newNode;
    }

    // function to check if Huffman Tree contains only a single node
    public boolean isLeaf() {
        return this.left == null && this.right == null;
    }


    @Override
    public int compareTo(Object o) {
        Node newNode = (Node) o;

        return freq - newNode.getFrequency();
    }
}


