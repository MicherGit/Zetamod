package zeta.zetamod.api.util.math.hash;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class GenerateHashes {
    public static void main(String... args) throws IOException {
        System.out.println(Arrays.toString(TrinaryHash.genRandomHash()));
        FileWriter writeHashes = new FileWriter("hashkeys.th5");
        writeHashes.write(Arrays.toString(TrinaryHash.genRandomHash()));
    }
}
