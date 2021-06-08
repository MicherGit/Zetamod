package zeta.zetamod.api.util.math.hash;

import zeta.zetamod.mod.managers.ConfigManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Deprecated
public class TrinaryHash {
    protected int[] aint;
    private boolean debugmode = true;
    public TrinaryHash(int input) {
        this.aint = new int[]{input, (int) (input % 1E8), (int) (input % 1E7), (int) (input%1e6), (int) (input%1e5), (int) (input%1e4), (int) (input%1e3),
                (int) (input%1e2), (int) (input%1e1)};
        int i = 8;
        this.aint = new int[]{(int) (aint[i-8] % Math.pow(3, i)),
                (int) (aint[i-7] % Math.pow(3, i-1)), (int) (aint[i-6] % Math.pow(3, i-2)),
                (int) (aint[i-5] % Math.pow(3, i-3)), (int) (aint[i-4] % Math.pow(3, i-4)),
                (int) (aint[i-3] % Math.pow(3, i-5)), (int) (aint[i-2] % Math.pow(3, i-6)),
                (int) (aint[i-1] % Math.pow(3, i-7)), (int) (aint[i] % Math.pow(3, i-8))};
    }
    private static List<String> hashList = new ArrayList<>(Arrays.asList(
            genRandomHash()
            //genRandomHashUpperBound()
    ));
    public static boolean checkHash() {
        if (hashList.contains(ConfigManager.getConfig().hash.getValue())) {
            return true;
        }
        else {
            throw new Error("Hash invalid!");

        }
    }

    public int decimalHash() {
        return aint[0]+aint[1]+aint[2]+aint[3]+aint[4]+aint[5]+aint[6]+aint[7]+aint[8];
    }
    private String printArray(boolean debug) {
        if(debug == debugmode) {
            return String.valueOf(aint[0])+aint[1]+aint[2]+aint[3]+aint[4]+aint[5]+aint[6]+aint[7]+aint[8];
        } else {throw new Error("debug mode not enabled!");}
    }
    public String hash() {
        return printArray(true);
    }
    public static String[] genRandomHash() {
        //Scanner sc = new Scanner(System.in);
        //int largestHashInitialValue = sc.nextInt();
        int largestHashInitialValue = 256;
        String[] trinaryHashes = new String[largestHashInitialValue];
        int i = 0;
        System.out.println("Mind us while we count to " + largestHashInitialValue);
        while (i < largestHashInitialValue) {
            trinaryHashes[i] = new TrinaryHash(i).hash();
            i++;
        }
        return trinaryHashes;
    }
    public static String[] genRandomHashUpperBound() {
        //Scanner sc = new Scanner(System.in);
        //int largestHashInitialValue = sc.nextInt();
        int largestHashInitialValue = Integer.MAX_VALUE;
        int i = (int)Math.pow(2,30)-Short.MAX_VALUE;
        String[] trinaryHashes = new String[i];

        //System.out.println("Mind us while we count to " + largestHashInitialValue);
        while (i < largestHashInitialValue) {
            trinaryHashes[i] = new TrinaryHash(i).hash();
            i++;
        }
        return trinaryHashes;
    }
}
