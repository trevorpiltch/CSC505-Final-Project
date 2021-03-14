//import java.util;

import java.util.*;

public class HT {
    private Node root;

    public HT(String sampleStream){
        root = null;
        buildTree(sampleStream);
        System.out.println(inOrder());

        HashMap<Character, String> map = new HashMap<>();
        map.put('a', sampleStream);
        encode(root, sampleStream, map);
    }


    //Creates the tree based on the sampleStream of characters given as input
    private void buildTree(String sampleStream) {
        PriorityQueue<Node> queue = new PriorityQueue<>(); //Queue that automatically sorts by frequency
        Node newNode = null;

        //Loops through each map in findFrequencies and adds it to the priority queue
        for (HashMap<Character, Integer> map : findFrequencies(sampleStream)) {
            Set<Character> keySet = map.keySet(); //Find the keys (in this case characters) in the map
            Object[] keySetArray = keySet.toArray();//Convert the ketSet to an Array so it can be indexed in the next step

            newNode = new Node((Character) keySetArray[0], map.get(keySetArray[0])); //Creates a new node with a value of the character and a frequency from the map

            queue.add(newNode); //Add the node to the queue
        }

        //Creates the tree
        while (queue.size() != 1) {
            Node leaf1 = queue.remove(); //Get the next least frequent node
            Node leaf2 = queue.remove(); //Get the next least frequent node
            Node parent = new Node(leaf1.getFrequency() + leaf2.getFrequency()); //Create a new node with a frequency of the combined previous nodes

            parent.setLeft(leaf2); //Set the left to the more frequent of the two nodes
            parent.setRight(leaf1); //Set the right to the less frequent of the two nodes

            queue.add(parent); //Add the parent node back to the tree
        }

        root = queue.peek(); //Set the root to the only node in the queue
    }

    //traverses the tree and then stores the codes in a map
    private void encode(Node root, String str, Map<Character, String> huffmanCode) {
        if (root == null) {
            return;
        }

        // Found a leaf node
        if (root.isLeaf()) {
            huffmanCode.put(root.getValue(), str.length() > 0 ? str : "1");
        }

        encode(root.getLeft(), str + '0', huffmanCode);
        encode(root.getRight(), str + '1', huffmanCode);
        System.out.println(str);
    }

    public static int decode(Node root, int index, StringBuilder sb)
    {
        if (root == null) {
            return index;
        }

        // Found a leaf node
        if (root.isLeaf())
        {
            System.out.print(root.getValue());
            return index;
        }

        index++;

        root = (sb.charAt(index) == '0') ? root.getLeft() : root.getRight();
        index = decode(root, index, sb);
        return index;
    }




    //Finds the number of times the searchValue appears in the input and returns a HashMap
    //with searchValue and its respective frequency
    private static HashMap<Character, Integer> findFrequency(char searchValue, String input) {
        int frequency = 0;

        char[] charArray = input.toCharArray();

        HashMap<Character, Integer> returnData = new HashMap<>();

        for (int i = 0; i < charArray.length; i ++) {
            if (charArray[i] == searchValue) {
                frequency += 1;
            }
        }

        returnData.put(searchValue, frequency);

        return returnData;
    }

    //Finds the frequencies of every character in the value and returns an ArrayList of HashMaps
    //with the characters and their respective frequencies
    private static ArrayList<HashMap<Character, Integer>> findFrequencies(String value) {
        ArrayList<Character> chars = new ArrayList<Character>();
        ArrayList<HashMap<Character, Integer>> returnData = new ArrayList<>();

        char[] inputChars = value.toCharArray();
        char firstChar = inputChars[0];

        int numDeleted = 0;

        //Put all the characters in inputChars into the ArrayList (so they can be removed later)
        for (char c : inputChars) {
            chars.add(c);
        }

        while(!chars.isEmpty()) {
            returnData.add(findFrequency(firstChar, value)); //Finds the frequency of the first character in chars

            chars.removeAll(Collections.singleton(firstChar)); //Removes all occurrences of the first character in chars

            //If the size is still greater than 0 then assign the first character to firstChar
            if (chars.size() > 0) {
                firstChar = chars.get(0);
            }
        }

        return returnData;
    }

    //Goes through left half of tree first then right half and returns value
    public String inOrder() {
        return inOrder(root);
    }

    //Helper method for inOrder transversal
    private String inOrder(Node rt) {
        if (rt == null) {
            return ", ";
        } else {
            String out = "";
            out += inOrder(rt.getLeft());
            out += rt.getValue();
            out += inOrder(rt.getRight());
            return out;
        }
    }
}
