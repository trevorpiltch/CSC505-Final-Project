import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

public class Tester {
    public static void main(String[] args) {
        String value = "Hello this is our Huffman Tree implementation";
        HT tree = new HT(value);

        System.out.println("Encoded value: " + tree.encode(value));
        System.out.println("Huffman Codes are "+ tree.huffmanCode(value));
        System.out.println("Decoded value: " + tree.decode(tree.encode(value)));
        System.out.println("The original value has " + tree.compareStorage(value, tree.encode(value)) + "% more bits than encoded value.");
    }
}


