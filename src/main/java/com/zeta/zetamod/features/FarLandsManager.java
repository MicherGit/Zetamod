package com.zeta.zetamod.features;

import com.zeta.zetamod.main.ZetaModInitializer;
import net.minecraft.util.math.MathHelper;
import org.apache.logging.log4j.Level;

public class FarLandsManager {
    public static final boolean farlands = true;
    public static Boolean FARLANDS_INTIALIZED = false;
    public static double maintainPrecisionManageable(double d) {
        if(farlands) {
            if (!FARLANDS_INTIALIZED) {
                FARLANDS_INTIALIZED = !FARLANDS_INTIALIZED;
                ZetaModInitializer.log(Level.INFO, "FarLands intialized!");
            }
            return d;
        }
        else {
            return d - (double) MathHelper.lfloor(d / 3.3554432E7D + 0.5D) * 3.3554432E7D;
        }
    }

}
