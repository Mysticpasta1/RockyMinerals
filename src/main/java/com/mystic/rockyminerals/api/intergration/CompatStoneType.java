package com.mystic.rockyminerals.api.intergration;

import com.mystic.rockyminerals.api.set.StoneType;
import net.mehvahdjukaar.moonlight.api.set.BlockSetAPI;

public class CompatStoneType {

    public static void init() {
        simpleStoneFinder("rockyminerals", "worn_granite");
    }


//!! StoneType
    public static void simpleStoneFinder(String modId, String nameStoneType) {
        var stonetypeFinder = StoneType.Finder.simple(modId, nameStoneType, nameStoneType);

        BlockSetAPI.addBlockTypeFinder(StoneType.class, stonetypeFinder);
    }

    public static void stoneFinder(String modId, String nameStoneType, String nameStone) {
        var stonetypeFinder = StoneType.Finder.simple(modId, nameStoneType, nameStone);

        BlockSetAPI.addBlockTypeFinder(StoneType.class, stonetypeFinder);
    }
}
