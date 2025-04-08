package com.mystic.rockyminerals;

import com.mystic.rockyminerals.datagen.MineralProviders;
import com.mystic.rockyminerals.init.Init;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Main.MOD_ID)
public class Main {
    public static final String MOD_ID = "rockyminerals";

    public Main(FMLJavaModLoadingContext context) {
        var bus = context.getModEventBus();
        Init.init(bus);
        MineralProviders.init(bus);
    }
}
