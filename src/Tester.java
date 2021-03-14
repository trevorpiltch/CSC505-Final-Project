import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

public class Tester {
    public static void main(String[] args) {
        String value = "aba ab cabbb";
        HT tree = new HT(value);

        System.out.println("Encoded value: " + tree.encode(value));
        System.out.println("Encoded value: 1101110111010110011000");
        System.out.println("Decoded value: " + tree.decode(tree.encode(value)));


    }
}


