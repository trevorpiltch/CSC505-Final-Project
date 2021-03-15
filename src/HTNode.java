class HTNode implements Comparable{
    private Character ch;
    private Integer freq;
    private HTNode left = null, right = null;

    public HTNode(Character ch, Integer freq) {
        this.ch = ch;
        this.freq = freq;
    }

    public HTNode(Character ch, Integer freq, HTNode left, HTNode right) {
        this.ch = ch;
        this.freq = freq;
        this.left = left;
        this.right = right;
    }

    public HTNode(Integer freq) {
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

    public HTNode getLeft() {
        return left;
    }

    public void setLeft(HTNode newNode) {
        left = newNode;
    }

    public HTNode getRight() {
        return right;
    }

    public void setRight(HTNode newNode) {
        right = newNode;
    }

    // function to check if Huffman Tree contains only a single node
    public boolean isLeaf() {
        return this.left == null && this.right == null;
    }


    //Overrided method of compareTo that returns the frequency of this node - the frequency of the given node (used for the priorityQueue in HT.buildTree())
    @Override
    public int compareTo(Object o) {
        HTNode newNode = (HTNode) o;

        return freq - newNode.getFrequency();
    }
}


