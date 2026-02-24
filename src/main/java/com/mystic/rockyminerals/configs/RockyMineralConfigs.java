package com.mystic.rockyminerals.configs;

import com.mystic.rockyminerals.RockyMineral;
import net.mehvahdjukaar.moonlight.api.platform.configs.ConfigBuilder;
import net.mehvahdjukaar.moonlight.api.platform.configs.ConfigSpec;
import net.mehvahdjukaar.moonlight.api.platform.configs.ConfigType;

import java.util.function.Supplier;

//loaded after registry
public class RockyMineralConfigs {

    public static ConfigSpec SPEC;

    public static final Supplier<Boolean> DEPEND_ON_PACKS;
    public static final Supplier<Boolean> DEBUG_RESOURCES;

    public static void init() {}

    static {
        ConfigBuilder builder = ConfigBuilder.create(RockyMineral.MOD_ID, ConfigType.COMMON);

        builder.push("general");
        DEPEND_ON_PACKS = builder.comment("Makes dynamic assets that are generated depend on loaded resource packs. Turn off to make them just use vanilla assets")
                .define("assets_depend_on_loaded_packs", true);
        DEBUG_RESOURCES = builder.comment("Creates a debug folder inside your instance directory where all the dynamically generated resources will be saved")
                .define("save_debug_resources", false);


        SPEC = builder.buildAndRegister();

        SPEC.loadFromFile(); //manually load early
    }

}
