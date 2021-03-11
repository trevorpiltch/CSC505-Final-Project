import java.util.ArrayList;
import java.util.Dictionary;

public class Tester {
    public static void main(String[] args) {
        System.out.println("Hello Eric");
        HTInternalNode node = new HTInternalNode();
        char firstChar;
        String value = "eebbeecdebeeebecceeeddebbbeceedebeeddeeeecceeeedeeedeeebeedeceedebeeedeceeedebee";

        firstChar = value.toCharArray()[0];

//        while (firstChar != null) {

            System.out.println(findFrequencies(value));
//        }

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

    public static Dictionary<Character, Integer>[] findFrequencies(String value) {
        ArrayList<Character> chars = new ArrayList<Character>();

        char[] inputChars = value.toCharArray();

        char firstChar = inputChars[0];

        Dictionary<Character, Integer> returnData = new Dictionary<Character, Integer>;

        //Put all the characters in inputChars into the ArrayList (so they can be removed later)
        for (char c : inputChars) {
            chars.add(c);
        }

        while (!chars.isEmpty()) {
            returnData.put(firstChar, findFrequency(firstChar, value).);
        }
    }
}


