import java.text.DecimalFormat;
import java.util.*;


public class HT {
    private Node root;

    public HT(String value){
        root = null; //initialize the root
        buildTree(value); //Build the tree based on the input value
    }


    //Creates the tree based on the value of characters given as input
    private void buildTree(String valueForTree) {
        PriorityQueue<Node> queue = new PriorityQueue<>(); //Queue that automatically sorts by frequency
        Node newNode = null;

        //Loops through each map in findFrequencies and adds it to the priority queue
        for (HashMap<Character, Integer> map : findFrequencies(valueForTree)) {
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

            parent.setLeft(leaf1); //Set the left to the less frequent of the two nodes
            parent.setRight(leaf2); //Set the right to the more frequent of the two nodes

            queue.add(parent); //Add the parent node back to the tree
            root = parent;
        }
    }


    public String encode(String value) {
        HashMap<Character, String> huffmanCode = new HashMap<>();
        encode(root, "", huffmanCode);

        StringBuilder sb = new StringBuilder();
        for (char c: value.toCharArray()) {
            sb.append(huffmanCode.get(c));
        }

        return (""+sb);
    }

    public HashMap<Character, String> huffmanCode(String value){
        HashMap<Character, String> huffmanCode = new HashMap<>();
        encode(root, "", huffmanCode);

        return huffmanCode;

    }

    //traverses the tree and then stores the codes in a map
    private String encode(Node root, String str, Map<Character, String> huffmanCode) {
        String returnData = "";

        if (root == null) {
            return returnData;
        }

        // Found a leaf node
        if (root.isLeaf()) {
            huffmanCode.put(root.getValue(), str.length() > 0 ? str : "1");
        }
//        returnData += huffmanCode.get(root.getValue());

        returnData += str;
        encode(root.getRight(), str + '1', huffmanCode);
        encode(root.getLeft(), str + '0', huffmanCode);


//        if (root.getValue() != null) {
//            System.out.println("Encoded value of " + root.getValue() + ": " + str);
//        }

        return returnData;
    }

    public String decode(String decodeValue) {
        char[] encodedInputAsArray = decodeValue.toCharArray();
        ArrayList<Character> encodedInputList = new ArrayList<Character>();

        String returnData = "";
        Node tempNode = root;

        for (char c : encodedInputAsArray) {
            encodedInputList.add(c);
        }

        while (!encodedInputList.isEmpty()) {
            if (encodedInputList.get(0) == '0') {
                tempNode = tempNode.getLeft();
                encodedInputList.remove(0);

                if (tempNode.isLeaf()) {
                    returnData += tempNode.getValue();
                    tempNode = root;
                }
            }
            else if (encodedInputList.get(0) == '1') {
                tempNode = tempNode.getRight();
                encodedInputList.remove(0);

                if (tempNode.isLeaf()) {
                    returnData += tempNode.getValue();
                    tempNode = root;
                }
            }
        }

        return returnData;
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

        //Put all the characters in inputChars into the ArrayList (so they can be removed later)
        for (char c : inputChars) {
            chars.add(c);
        }

        while (!chars.isEmpty()) {
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
            out += rt.getFrequency();
            out += inOrder(rt.getRight());
            return out;
        }
    }

    public String compareStorage(String originalValue, String encodedValue) {
        Double returnData = 0.00;

        int originalByteTotal = 0;
        String pattern = "##.##";
        DecimalFormat df = new DecimalFormat(pattern);

        for (int i = 0; i < originalValue.getBytes().length; i++) {
            int bytes = (int) originalValue.getBytes()[i];
            originalByteTotal += bytes;
        }

        int encodedByteTotal = 0;

        for (int i = 0; i < encodedValue.getBytes().length; i++) {
            int bytes = (int) encodedValue.getBytes()[i];
            encodedByteTotal += bytes;
        }

        returnData = (Math.abs(originalByteTotal - encodedByteTotal)/((originalByteTotal + encodedByteTotal) / 2.00)) * 100.00;


        return df.format(returnData);
    }
}
