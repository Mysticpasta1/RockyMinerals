package com.mystic.rockyminerals.misc;

import com.mystic.rockyminerals.api.set.mineral.MineralType;
import com.mystic.rockyminerals.api.set.stone.StoneType;

import java.util.Set;

public class HardcodedBlockType {

    // StoneType
    public static boolean isKnownVanillaStone(StoneType stoneType){
        var id = stoneType.getId();
        if (id.getNamespace().equals("minecraft")) {
            return VANILLA_STONES.contains(id.getPath());
        }
        return false;
    }

    private static final Set<String> VANILLA_STONES = Set.of(
            "stone", "andesite", "granite", "diorite", "tuff", "calcite", "blackstone", "sandstone",
            "basalt", "deepslate", "prismarine", "nether", "end_stone"
    );

    // MineralType
    public static boolean isKnownVanillaMineral(MineralType mineralType){
        var id = mineralType.getId();
        if (id.getNamespace().equals("minecraft")) {
            return VANILLA_MINERALS.contains(id.getPath());
        }
        return false;
    }

    private static final Set<String> VANILLA_MINERALS = Set.of(
            "amethyst"
    );
}
