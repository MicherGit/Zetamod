package com.zeta.zetamod.features;

import net.minecraft.util.math.MathHelper;
import org.apache.logging.log4j.Level;

public class FarLandsManager {
    public static boolean farlands = false;
    public static Boolean FARLANDS_INTIALIZED = false;
    public static double maintainPrecisionManageable(double d) {
        if(farlands) {
            if (FARLANDS_INTIALIZED) {
                ZetaModInitializer.log(Level.INFO, "FarLands intialized!");
                FARLANDS_INTIALIZED = true;
            }
            return d;
        }
        else {
            return d - (double) MathHelper.lfloor(d / 3.3554432E7D + 0.5D) * 3.3554432E7D;
        }
    }
    public static void init() {}
}
