package com.mystic.rockyminerals;

import com.mystic.rockyminerals.datagen.MineralProviders;
import com.mystic.rockyminerals.init.Init;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Main.MODID)
public class Main {
    public static final String MODID = "rockyminerals";

    @SuppressWarnings("removal")
    public Main() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        Init.init(bus);
        MineralProviders.init(bus);
    }
}
