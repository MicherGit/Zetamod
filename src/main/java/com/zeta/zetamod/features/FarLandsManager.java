package com.zeta.zetamod.features;

import com.zeta.zetamod.main.ZMMain;
import net.minecraft.util.math.MathHelper;
import org.apache.logging.log4j.Level;

public class FarLandsManager {
    // This is now mixinable!
    public static boolean farlands() {
        return false;
    }
    public static Boolean FARLANDS_INTIALIZED = false;
    public static final double maintainPrecisionManageable(double d) {
        if(farlands()) {
            if (!FARLANDS_INTIALIZED) {
                FARLANDS_INTIALIZED = !FARLANDS_INTIALIZED;
                ZMMain.log(Level.INFO, "FarLands intialized!");
            }
            return d;
        }
        else {
            return d - (double) MathHelper.lfloor(d / 3.3554432E7D + 0.5D) * 3.3554432E7D;
        }
    }

}
