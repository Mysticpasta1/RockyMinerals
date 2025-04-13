package com.mystic.rockyminerals.api.intergration;

import com.mystic.rockyminerals.api.set.MineralType;
import net.mehvahdjukaar.moonlight.api.set.BlockSetAPI;

public class CompatMineralType {

    public static void init() {



    }

//!! MineralType

    /**
     * @param nameMineralType ID of the StoneType for all of children including the Mineral
     */
    public static void simpleMineralFinder(String modId, String nameMineralType) {
        var stonetypeFinder = MineralType.Finder.simple(modId, nameMineralType, nameMineralType);

        BlockSetAPI.addBlockTypeFinder(MineralType.class, stonetypeFinder);
    }

    /**
     * @param nameMineralType ID of the StoneType for all of children
     * @param nameMineral ID of the stone block - some block have unique naming like amethyst_block
     */
    public static void mineralBlockFinder(String modId, String nameMineralType, String nameMineral) {
        var stonetypeFinder = MineralType.Finder.simple(modId, nameMineralType, nameMineral);

        BlockSetAPI.addBlockTypeFinder(MineralType.class, stonetypeFinder);
    }
}
