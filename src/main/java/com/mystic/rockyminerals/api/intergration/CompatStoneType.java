package com.mystic.rockyminerals.api.intergration;

import com.mystic.rockyminerals.api.set.StoneType;
import net.mehvahdjukaar.moonlight.api.set.BlockSetAPI;

public class CompatStoneType {

    public static void init() {
        simpleStoneFinder("rockyminerals", "worn_granite");
    }


//!! StoneType

    /**
     * @param nameStoneType ID of the StoneType for all of children including the stone
     */
    public static void simpleStoneFinder(String modId, String nameStoneType) {
        var stonetypeFinder = StoneType.Finder.simple(modId, nameStoneType, nameStoneType);

        BlockSetAPI.addBlockTypeFinder(StoneType.class, stonetypeFinder);
    }

    /**
     * @param nameStoneType ID of the StoneType for all of children
     * @param nameStone ID of the stone block - some block have unique naming like amethyst_block
     */
    public static void stoneBlockFinder(String modId, String nameStoneType, String nameStone) {
        var stonetypeFinder = StoneType.Finder.simple(modId, nameStoneType, nameStone);

        BlockSetAPI.addBlockTypeFinder(StoneType.class, stonetypeFinder);
    }
}
