package zeta.zetamod.util.hash;

public class GenerateHashes {
    public static void main(String... args) {
        byte largestHashInitialValue = Byte.MAX_VALUE;
        String[] trinaryHashes = new String[largestHashInitialValue];
        int i = 0;
        System.out.println("Mind us while we count to " + largestHashInitialValue);
        while (i < largestHashInitialValue) {
            trinaryHashes[i] = new TrinaryHash(i).hash();
            System.out.println(trinaryHashes[i]);
            i++;
        }
    }
}
