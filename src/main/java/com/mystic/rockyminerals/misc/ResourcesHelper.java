package com.mystic.rockyminerals.misc;

import com.mystic.rockyminerals.RockyMineral;
import com.mystic.rockyminerals.api.TextureInfo;

import java.util.List;

import static com.mystic.rockyminerals.RockyMineral.res;

public class ResourcesHelper {

    /**
     * STONETYPE:
     *      saltstone
     *      rhyolite
     *      pumice
     *      worn_granite
     **/
    public static List<TextureInfo> Saltstone_Templates = List.of(
            TextureInfo.of("cobbled_saltstone", res("block/cobbled_saltstone")).build(),
            TextureInfo.of("cracked_saltstone", res("block/cracked_saltstone")).build(),
            TextureInfo.of("cut_saltstone", res("block/cut_saltstone")).build(),
            TextureInfo.of("mosaic_saltstone", res("block/mosaic_saltstone")).build(),
            TextureInfo.of("polished_saltstone", res("block/polished_saltstone")).build(),
            TextureInfo.of("stone_bricks", res("block/saltstone_bricks")).build(),
            TextureInfo.of("stone_lamp", res("block/saltstone_lamp")).build(),
            TextureInfo.of("stone_tile", res("block/saltstone_tile")).build(),

            TextureInfo.of("chiseled_saltstone", res("block/chiseled_saltstone")).build(),
            TextureInfo.of("chiseled_saltstone", res("block/chiseled_saltstone_top")).build(),

            TextureInfo.of("saltstone_pillar", res("block/saltstone_pillar")).build(),
            TextureInfo.of("saltstone_pillar", res("block/saltstone_pillar_top")).build(),

            TextureInfo.of("saltstone_redstone_lamp", res("block/saltstone_redstone_lamp_lit"),
                    res("block/mask/rocktype_redstone_lamp_common")).build(),
            TextureInfo.of("saltstone_redstone_lamp", res("block/saltstone_redstone_lamp_unlit"),
                    res("block/mask/rocktype_redstone_lamp_common")).build(),

            TextureInfo.of("mossy_saltstone", res("block/saltstone")).customPath("block/mossy_saltstone").build()
    );

    /**
     *  MINERALTYPE:
     *      blue_calcite
     *      aphydrite
     *      halite
     *      opal
    **/
    public static List<TextureInfo> BlueCacite_Templates = List.of(
            TextureInfo.of("cobbled_blue_calcite", res("block/cobbled_blue_calcite")).build(),
            TextureInfo.of("cracked_blue_calcite", res("block/cracked_blue_calcite")).build(),
            TextureInfo.of("cut_blue_calcite", res("block/cut_blue_calcite")).build(),
            TextureInfo.of("mosaic_blue_calcite", res("block/mosaic_blue_calcite")).build(),
            TextureInfo.of("polished_blue_calcite", res("block/polished_blue_calcite")).build(),
            TextureInfo.of("blue_calcite_bricks", res("block/blue_calcite_bricks")).build(),
            TextureInfo.of("blue_calcite_lamp", res("block/blue_calcite_lamp")).build(),
            TextureInfo.of("blue_calcite_tile", res("block/blue_calcite_tile")).build(),

            TextureInfo.of("chiseled_blue_calcite", res("block/chiseled_blue_calcite")).build(),
            TextureInfo.of("chiseled_blue_calcite", res("block/chiseled_blue_calcite_top")).build(),

            TextureInfo.of("blue_calcite_pillar", res("block/blue_calcite_pillar")).build(),
            TextureInfo.of("blue_calcite_pillar", res("block/blue_calcite_pillar_top")).build(),

            TextureInfo.of("blue_calcite_redstone_lamp", res("block/blue_calcite_redstone_lamp_lit"),
                    res("block/mask/rocktype_redstone_lamp_common")).build(),
            TextureInfo.of("blue_calcite_redstone_lamp", res("block/blue_calcite_redstone_lamp_unlit"),
                    res("block/mask/rocktype_redstone_lamp_common")).build()
    );
}
