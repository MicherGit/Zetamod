package zeta.zetamod.api.util.math.hash;

import zeta.zetamod.mod.managers.ConfigManager;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SecureHash {
    protected int[] aint;
    private boolean debugmode = true;
    public SecureHash(int input) {
        this.aint = new int[]{input, (int) (input % 1E8), (int) (input % 1E7), (int) (input%1e6), (int) (input%1e5), (int) (input%1e4), (int) (input%1e3),
                (int) (input%1e2), (int) (input%1e1)};
        int i = 8;
        this.aint = new int[]{(int) (aint[i-8] % Math.pow(3, i)),
                (int) (aint[i-7] % Math.pow(3, i-1)), (int) (aint[i-6] % Math.pow(3, i-2)),
                (int) (aint[i-5] % Math.pow(3, i-3)), (int) (aint[i-4] % Math.pow(3, i-4)),
                (int) (aint[i-3] % Math.pow(3, i-5)), (int) (aint[i-2] % Math.pow(3, i-6)),
                (int) (aint[i-1] % Math.pow(3, i-7)), (int) (aint[i] % Math.pow(3, i-8))};
    }
    private static List<String> hashList;

    static {
        try {
            hashList = new ArrayList<>(Arrays.asList(
                    genRandomHash()
                    //genRandomHashUpperBound()
            ));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

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
    public String hash() throws NoSuchAlgorithmException {
        String updatingHash = MD5();
        int counter = 0;
        while (counter < 65536) {
            updatingHash = MD5Layered(updatingHash);
            counter++;
        }
        String hashed = updatingHash;
        return hashed;
    }
    private String MD5() throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(printArray(true).getBytes());
        byte[] digest = md.digest();
        String myHash = DatatypeConverter
                .printHexBinary(digest).toUpperCase();
        return myHash;
    }
    private String MD5Layered(String s) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(s.getBytes());
        byte[] digest = md.digest();
        String myHash = DatatypeConverter
                .printHexBinary(digest).toUpperCase();
        return myHash;
    }
    protected static String[] genRandomHash() throws NoSuchAlgorithmException {
        //Scanner sc = new Scanner(System.in);
        //int largestHashInitialValue = sc.nextInt();
        int largestHashInitialValue = 256;
        String[] trinaryHashes = new String[largestHashInitialValue];
        int i = 0;
        System.out.println("Mind us while we count to " + largestHashInitialValue);
        while (i < largestHashInitialValue) {
            trinaryHashes[i] = new SecureHash(i).hash();
            i++;
        }
        return trinaryHashes;
    }
}
