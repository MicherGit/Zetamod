package zeta.zet2mod.api.util.math.hash;

import java.io.FileWriter;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class GenerateHashes {
    public static void main(String... args) throws IOException, NoSuchAlgorithmException {
        System.out.println(Arrays.toString(DecupleHash.genRandomHash()));
        FileWriter writeHashes = new FileWriter("hashkeys3.th5");
        writeHashes.write(Arrays.toString(DecupleHash.genRandomHash()));
    }
}
