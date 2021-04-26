package zeta.zetamod.util.hash;

import java.util.Arrays;

public class GenerateHashes {
    public static void main(String... args) {
        System.out.println(Arrays.toString(TrinaryHash.genRandomHash()));
    }
}
