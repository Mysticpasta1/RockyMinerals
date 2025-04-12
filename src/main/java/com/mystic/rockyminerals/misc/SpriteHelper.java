package com.mystic.rockyminerals.misc;

import com.mystic.rockyminerals.Main;
import com.mystic.rockyminerals.api.TextureInfo;

import java.util.List;

public class SpriteHelper {

    public static List<TextureInfo> textureReference = List.of(
            TextureInfo.of("rockyminerals:cobbled_stone", Main.res("block/template/cobbled_stonetype")).build(),
            TextureInfo.of("rockyminerals:cracked_stone", Main.res("block/template/cracked_stonetype")).build(),
            TextureInfo.of("rockyminerals:cut_stone", Main.res("block/template/cut_stonetype")).build(),
            TextureInfo.of("rockyminerals:mosaic_stone", Main.res("block/template/mosaic_stonetype")).build(),
            TextureInfo.of("rockyminerals:polished_stone", Main.res("block/template/polished_stonetype")).build(),
            TextureInfo.of("rockyminerals:stone_bricks", Main.res("block/template/stonetype_bricks")).build(),
            TextureInfo.of("rockyminerals:stone_lamp", Main.res("block/template/stonetype_lamp")).build(),
            TextureInfo.of("rockyminerals:stone_tile", Main.res("block/template/stonetype_tile")).build(),

            TextureInfo.of("rockyminerals:chiseled_stone", Main.res("block/template/chiseled_stonetype")).build(),
            TextureInfo.of("rockyminerals:chiseled_stone", Main.res("block/template/chiseled_stonetype_top")).build(),

            TextureInfo.of("rockyminerals:stone_pillar", Main.res("block/template/stonetype_pillar")).build(),
            TextureInfo.of("rockyminerals:stone_pillar", Main.res("block/template/stonetype_pillar_top")).build(),

            TextureInfo.of("rockyminerals:stone_redstone_lamp", Main.res("block/template/stonetype_redstone_lamp_lit"), Main.res("block/template/stonetype_redstone_lamp_common_m")).build(),
            TextureInfo.of("rockyminerals:stone_redstone_lamp", Main.res("block/template/stonetype_redstone_lamp_unlit"), Main.res("block/template/stonetype_redstone_lamp_common_m")).build()
    );
}
