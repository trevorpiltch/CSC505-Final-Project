import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.util.*;

public class HT {
    private HTNode root;

    public HT(String value){
        root = null; //initialize the root
        buildTree(value); //Build the tree based on the input value
    }


    //Creates the tree based on the value of characters given as input
    private void buildTree(String valueForTree) {
        PriorityQueue<HTNode> queue = new PriorityQueue<>(); //Queue that automatically sorts by value
        HTNode newNode = null;

        //Loops through each map in findFrequencies and adds it to the priority queue
        for (HashMap<Character, Integer> map : findFrequencies(valueForTree)) {
            Set<Character> keySet = map.keySet(); //Find the keys (in this case characters) in the map
            Object[] keySetArray = keySet.toArray();//Convert the ketSet to an Array so it can be indexed in the next step

            newNode = new HTNode((Character) keySetArray[0], map.get(keySetArray[0])); //Creates a new node with a value of the character and a frequency from the map

            queue.add(newNode); //Add the node to the queue
        }

        //Creates the tree
        while (queue.size() != 1) {
            HTNode leaf1 = queue.remove(); //Get the least frequent node in the queue
            HTNode leaf2 = queue.remove(); //Get the next least frequent node
            HTNode parent = new HTNode(leaf1.getFrequency() + leaf2.getFrequency()); //Create a new node with a frequency of the combined previous nodes

            parent.setLeft(leaf1); //Set the left to the less frequent of the two nodes
            parent.setRight(leaf2); //Set the right to the more frequent of the two nodes

            queue.add(parent); //Add the parent node back to the tree
        }

        root = queue.peek(); //Set the root of the tree to the first (and only) value of the queue
    }

    //Encodes the input value to a series of 0's and 1's
    public String encode(String value) {
        HashMap<Character, String> huffmanCode = new HashMap<>(); //Creates a new map with character and String
        StringBuilder sb = new StringBuilder();

        encode(root, "", huffmanCode); //Encodes the tree into the huffmanCode

        //Loops through the value characters and appends their respective HuffmanCode's to the StringBuilder
        for (char c: value.toCharArray()) {
            sb.append(huffmanCode.get(c));
        }

        return (""+sb);
    }

    //Traverses the tree and then stores the codes in a map
    private void encode(HTNode root, String str, Map<Character, String> huffmanCode) {
        if (root == null) {
            return;
        }

        // Found a leaf node
        if (root.isLeaf()) {
            huffmanCode.put(root.getValue(), str.length() > 0 ? str : "1");
        }

        encode(root.getRight(), str + '1', huffmanCode); //Add 1 to the code to go right
        encode(root.getLeft(), str + '0', huffmanCode); //Add 0 to the code to go left
    }

    //Returns the HuffmanCode for each character in the tree
    public HashMap<Character, String> getHuffmanCodes(String value){
        HashMap<Character, String> huffmanCode = new HashMap<>();
        encode(root, "", huffmanCode);

        return huffmanCode;
    }

    //Decodes the given input (of 1's and 0's) into a value using the tree
    public String decode(String decodeValue) {
        char[] inputArray = decodeValue.toCharArray(); //Turns the value into an array of chars
        ArrayList<Character> inputList = new ArrayList<Character>();

        String returnData = "";
        HTNode tempNode = root;

        //Adds all the characters of the array to the list (so they can be removed later)
        for (char c : inputArray) {
            inputList.add(c);
        }

        while (!inputList.isEmpty()) {
            if (tempNode != null) {
                //If the first value in the list is 0 then go left and check if leaf (if so then add the node value to data)
                if (inputList.get(0) == '0') {
                    tempNode = tempNode.getLeft();
                    inputList.remove(0);

                }
                //If the first value in the list is 1 then go right and check if leaf (if so then add the node value to data)
                else if (inputList.get(0) == '1') {
                    tempNode = tempNode.getRight();
                    inputList.remove(0);

                }
                if (tempNode != null && tempNode.isLeaf()) {
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
    private String inOrder(HTNode rt) {
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

    //Returns the percent difference between the bytes of originalValue and encodedValue
    public String compareStorage(String originalValue, String encodedValue) {
        Double returnData = 0.0;

        int originalByteTotal = 0; //Variable for the byte total of the original value
        int encodedByteTotal = 0; //Variable for the byte total of the encoded value

        String pattern = "##.##"; //The pattern to which the decimal formatter should format (in this case .00)
        DecimalFormat df = new DecimalFormat(pattern);

        //Calculate the total of every byte in the original string converted to a byte array
        for (int i = 0; i < originalValue.getBytes().length; i++) {
            Byte bytes = originalValue.getBytes(StandardCharsets.UTF_8)[i];
            originalByteTotal += bytes;
        }

        encodedByteTotal = encodedValue.getBytes().length; //The encoded byte total is just the length because 0's and 1's are just 1 bit

        //Find the percent difference between the two byte totals
        returnData = Math.abs((encodedByteTotal - originalByteTotal))* 1.00;
        returnData /= ((double) (encodedByteTotal + originalByteTotal) /2);
        returnData *= 100.00;

        return df.format(returnData);
    }
}
