package com.mystic.rockyminerals.api.intergration;

import com.mystic.rockyminerals.api.set.MineralType;
import com.mystic.rockyminerals.api.set.StoneType;
import net.mehvahdjukaar.moonlight.api.set.BlockSetAPI;

public class CompatBlockType {

    public static void init() {

        /// StoneType
        simpleStoneFinder("rockyminerals", "worn_granite");

        /// MineralType
        simpleMineralFinder("rockyminerals", "blue_calcite");

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
        StoneType.Finder stonetypeFinder = StoneType.Finder.simple(modId, nameStoneType, nameStone);

        BlockSetAPI.addBlockTypeFinder(StoneType.class, stonetypeFinder);
    }

//!! MineralType
    /**
     * @param nameMineralType ID of the MineralType for all of children including the stone
     */
    public static void simpleMineralFinder(String modId, String nameMineralType) {
        MineralType.Finder stonetypeFinder = MineralType.Finder.simple(modId, nameMineralType, nameMineralType);

        BlockSetAPI.addBlockTypeFinder(MineralType.class, stonetypeFinder);
    }

    /**
     * @param nameMineralType ID of the MineralType for all of children
     * @param nameMineral ID of the stone block - some block have unique naming like amethyst_block
     */
    public static void mineralBlockFinder(String modId, String nameMineralType, String nameMineral) {
        var stonetypeFinder = MineralType.Finder.simple(modId, nameMineralType, nameMineral);

        BlockSetAPI.addBlockTypeFinder(MineralType.class, stonetypeFinder);
    }
}
