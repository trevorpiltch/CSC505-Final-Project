//import java.util;

import java.util.*;

public class HT {
    private Node root;
//    private String relative

    public HT(){
        root = null;
        buildTree("eebbeecdebeeebecceeeddebbbeceedebeeddeeeecceeeedeeedeeebeedeceedebeeedeceeedebee");
        System.out.println(inOrder());
    }


    //TODO: Finish
    //Note for Eric: This isn't working so whatever you do, DO NOT touch it unless you figure it out.
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

        while (!queue.isEmpty()) {
            Node leaf1 = queue.remove();
            System.out.println(leaf1.getValue());
            Node leaf2 = queue.remove();
            System.out.println(leaf2.getValue());
            Node parent = new Node(leaf1.getFrequency() + leaf2.getFrequency());

            parent.setLeft(leaf1);
            parent.setRight(leaf2);
            root = parent;
        }


//        System.out.println(queue.remove().getValue());
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
