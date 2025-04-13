package com.mystic.rockyminerals.api.intergration;

import com.mystic.rockyminerals.api.set.CrystalType;
import net.mehvahdjukaar.moonlight.api.set.BlockSetAPI;

public class CompatCrystalType {

    public static void init() {



    }

//!! CrystalType
    public static void simpleStoneFinder(String modId, String nameCrystalType) {
        var stonetypeFinder = CrystalType.Finder.simple(modId, nameCrystalType, nameCrystalType);

        BlockSetAPI.addBlockTypeFinder(CrystalType.class, stonetypeFinder);
    }

    public static void stoneFinder(String modId, String nameCrystalType, String nameStone) {
        var stonetypeFinder = CrystalType.Finder.simple(modId, nameCrystalType, nameStone);

        BlockSetAPI.addBlockTypeFinder(CrystalType.class, stonetypeFinder);
    }
}
