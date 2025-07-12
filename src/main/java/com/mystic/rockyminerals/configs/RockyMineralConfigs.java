package com.mystic.rockyminerals.configs;

import com.mystic.rockyminerals.RockyMineral;
import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.mehvahdjukaar.moonlight.api.platform.configs.ConfigBuilder;
import net.mehvahdjukaar.moonlight.api.platform.configs.ConfigSpec;
import net.mehvahdjukaar.moonlight.api.platform.configs.ConfigType;

import java.util.function.Supplier;

//loaded after registry
public class RockyMineralConfigs {

    public static ConfigSpec SPEC;
//    public static ConfigSpec CLIENT_SPED;

    public static final Supplier<Boolean> DEPEND_ON_PACKS;
    public static final Supplier<Boolean> DEBUG_RESOURCES;
//    public static final Supplier<Boolean> GENERATE_DYNAMIC_SERVER;
//    public static final Supplier<Boolean> GENERATE_DYNAMIC_CLIENT;

    public static void init() {}

    static {

//        if(PlatHelper.getPhysicalSide().isClient()) {
//            ConfigBuilder builder = ConfigBuilder.create(RockyMineral.MOD_ID, ConfigType.CLIENT);
//            builder.push("general");
//            GENERATE_DYNAMIC_CLIENT = builder.comment("Enables the generation of dynamic assets. This is required for the mod to work properly. Turn off if you chose to add all the generated assets via datapack manually. This can speedup boot times for modpacks. Note that the generated assets will depend on loaded datapacks")
//                    .define("generate_dynamic_assets", true);
//            builder.pop();
//            CLIENT_SPED = builder.buildAndRegister();
//            CLIENT_SPED.loadFromFile(); //manually load early
//        }else{
//            GENERATE_DYNAMIC_CLIENT = () -> false;
//        }

        ConfigBuilder builder = ConfigBuilder.create(RockyMineral.MOD_ID, ConfigType.COMMON);

        builder.push("general");
//        GENERATE_DYNAMIC_SERVER = builder.comment("Enables the generation of dynamic assets. This is required for the mod to work properly. Turn off if you chose to add all the generated assets via datapack manually. This can speedup boot times for modpacks. Note that the generated assets will depend on loaded datapacks")
//                .define("generate_dynamic_assets", true);
        DEPEND_ON_PACKS = builder.comment("Makes dynamic assets that are generated depend on loaded resource packs. Turn off to make them just use vanilla assets")
                .define("assets_depend_on_loaded_packs", true);
        DEBUG_RESOURCES = builder.comment("Creates a debug folder inside your instance directory where all the dynamically generated resources will be saved")
                .define("save_debug_resources", false);


        SPEC = builder.buildAndRegister();

        SPEC.loadFromFile(); //manually load early
    }

}
