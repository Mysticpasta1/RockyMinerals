package com.mystic.rockyminerals.api.intergration;

import com.mystic.rockyminerals.api.set.CrystalType;
import net.mehvahdjukaar.moonlight.api.set.BlockSetAPI;

public class CompatCrystalType {

    public static void init() {



    }

//!! CrystalType

    /**
     * @param nameCrystalType ID of the StoneType for all of children including the crystal
     */
    public static void simpleCrystalFinder(String modId, String nameCrystalType) {
        var stonetypeFinder = CrystalType.Finder.simple(modId, nameCrystalType, nameCrystalType);

        BlockSetAPI.addBlockTypeFinder(CrystalType.class, stonetypeFinder);
    }

    /**
     * @param nameCrystalType ID of the StoneType for all of children
     * @param nameCrystal ID of the stone block - some block have unique naming like amethyst_block
     */
    public static void crystalBlockFinder(String modId, String nameCrystalType, String nameCrystal) {
        var stonetypeFinder = CrystalType.Finder.simple(modId, nameCrystalType, nameCrystal);

        BlockSetAPI.addBlockTypeFinder(CrystalType.class, stonetypeFinder);
    }
}
