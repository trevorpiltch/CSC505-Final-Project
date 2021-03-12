//import java.util;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Map;

public class HT {
    private Node root;

    public HT(){
        root=null;
    }


    //traverses the tree and then stores the codes in a map
    public static void encode(Node root, String str, Map<Character, String> huffmanCode)
    {
        if (root == null) {
            return;
        }

        // Found a leaf node
        if (root.isLeaf()) {
            huffmanCode.put(root.ch, str.length() > 0 ? str : "1");
        }

        encode(root.left, str + '0', huffmanCode);
        encode(root.right, str + '1', huffmanCode);
    }


    public static Dictionary<Character, Integer> findFrequency(char searchValue, String value ) {
        int frequency = 0;
        char[] charArray = new char[value.length()];
        charArray = value.toCharArray();
        Dictionary<Character, Integer> returnData = null;

        for (int i = 0; i < charArray.length; i ++) {
            if (charArray[i] == searchValue) {
                frequency += 1;
            }
        }

        returnData.put((Character) searchValue, frequency);

        return returnData;
    }

    public static ArrayList<Dictionary<Character, Integer>> findFrequencies(String value) {
        ArrayList<Character> chars = new ArrayList<Character>();

        char[] inputChars = value.toCharArray();

        char firstChar = inputChars[0];

        //TODO: change to map
        ArrayList<Dictionary<Character, Integer>> returnData = new ArrayList<>();

        //Put all the characters in inputChars into the ArrayList (so they can be removed later)
        for (char c : inputChars) {
            chars.add(c);
        }

        while (!chars.isEmpty()) {
//            returnData.add;
        }

        return returnData;
    }
}
