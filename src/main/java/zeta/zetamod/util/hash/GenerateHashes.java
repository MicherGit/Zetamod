package zeta.zetamod.util.hash;

public class GenerateHashes {
    public static void main(String... args) {
        TrinaryHash hash = new TrinaryHash(10);
        System.out.println(hash.hash());
    }
}
