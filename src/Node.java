class Node {
    Character ch;
    Integer freq;
    Node left = null, right = null;

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

    // function to check if Huffman Tree contains only a single node
    public boolean isLeaf() {
        return this.left == null && this.right == null;
    }

}


