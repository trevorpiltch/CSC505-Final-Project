import java.util;

public class HT<HTLeafNode> {
    private HTInternalNode<E> root;

    public HT(){
        root=null;
    }

   // function to check if Huffman Tree contains only a single node
    public static boolean isLeaf(Node root) {
        return root.left == null && root.right == null;
    }
    //traverses the tree and then stores the codes in a map
    public static void encode(Node root, String str, Map<Character, String> huffmanCode)
    {
        if (root == null) {
            return;
        }

        // Found a leaf node
        if (isLeaf(root)) {
            huffmanCode.put(root.ch, str.length() > 0 ? str : "1");
        }

        encode(root.left, str + '0', huffmanCode);
        encode(root.right, str + '1', huffmanCode);
    }

}
