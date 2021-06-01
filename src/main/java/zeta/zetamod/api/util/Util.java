package zeta.zetamod.api.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class Util
extends AbstractUtility
//implements AbstractUtility
{
    public static String SPACE = " ";
    static class FileUtil {}
    public static String concatArray(int[] array) {
        //String[] stringArray = ;
        //List<String> stringList = Arrays.asList();
        //TODO: MAKE THIS WORK!!!
        return null;
    }
    public static String hash(File file) {
        String md5 = null;
        try (
                InputStream is = Files.newInputStream(file.getAbsoluteFile().toPath())
        ) {
            md5 = org.apache.commons.codec.digest.DigestUtils.md5Hex(is);
        } catch (IOException e) {
            e.printStackTrace();
             //hash(file);
        }
        if (md5 == null) {
            throw new NullPointerException();
        }
        return md5;
    }
}
