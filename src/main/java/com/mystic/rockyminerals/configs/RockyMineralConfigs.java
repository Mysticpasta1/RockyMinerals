package com.mystic.rockyminerals.configs;

import com.mystic.rockyminerals.RockyMineral;
import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.mehvahdjukaar.moonlight.api.platform.configs.ConfigBuilder;
import net.mehvahdjukaar.moonlight.api.platform.configs.ConfigType;
import net.mehvahdjukaar.moonlight.api.platform.configs.ModConfigHolder;
import net.mehvahdjukaar.moonlight.api.resources.pack.PackGenerationStrategy;

import java.util.function.Supplier;

//loaded after registry
public class RockyMineralConfigs {

    public enum GenMode {
        NEVER,
        RUN_ONCE,
        CACHED,
        CACHED_ZIPPED,
        ALWAYS;

        public PackGenerationStrategy pickStrategy() {
            return switch (this) {
                case NEVER -> PackGenerationStrategy.NO_OP;
                case RUN_ONCE -> PackGenerationStrategy.runOnce();
                case CACHED -> PackGenerationStrategy.CACHED;
                case CACHED_ZIPPED -> PackGenerationStrategy.CACHED_ZIPPED;
                case ALWAYS -> PackGenerationStrategy.REGEN_ON_EVERY_RELOAD;
            };
        }
    }

    public static ModConfigHolder SPEC;
    public static ModConfigHolder CLIENT_SPEC;

    public static final Supplier<GenMode> CLIENT_GENERATION_MODE;
    public static final Supplier<GenMode> SERVER_GENERATION_MODE;

    public static void init() {}

    static {

        //      ┌──────────────────────────────────────────────────────────┐
        //      │                          CLIENT                          │
        //      └──────────────────────────────────────────────────────────┘
        if (PlatHelper.getPhysicalSide().isClient()) {
            ConfigBuilder builder = ConfigBuilder.create(RockyMineral.MOD_ID, ConfigType.CLIENT);

            builder.push("general");
            CLIENT_GENERATION_MODE = builder.comment("""
                            \nHow dynamic assets are generated. If cached the cache will regenerate once any mod or pack changes
                            - NEVER: This mod will never attempt to generate the cache folder. The assets will be put in memory
                            - RUN_ONCE: Will generate once & the assets will be stored in memory every time you launched.
                            - CACHED: create a CACHE folder via .minecraft/dynamic-resource-pack-cache
                            - CACHED_ZIPPED: create a ZIP folder via .minecraft/dynamic-resource-pack-cache
                            - ALWAYS: Will always generate the assets & will be stored in memory. There will be no cache folder""")
                    .define("dynamic_assets_generation_mode", GenMode.ALWAYS);
            builder.pop();

            CLIENT_SPEC = builder.build();
            CLIENT_SPEC.forceLoad(); //manually load early
        } else {
            CLIENT_GENERATION_MODE = () -> GenMode.ALWAYS;
        }


        //      ┌──────────────────────────────────────────────────────────┐
        //      │                          COMMON                          │
        //      └──────────────────────────────────────────────────────────┘
        ConfigBuilder builder = ConfigBuilder.create(RockyMineral.MOD_ID, ConfigType.COMMON);

        builder.push("general");
        SERVER_GENERATION_MODE = builder.comment("""
                        \nHow dynamic assets are generated.
                        - NEVER: No asset will be generated. Use this if you have an external pack that adds assets for the block, otherwise you'll get missing assets everywhere
                        - RUN_ONCE: Assets will be generated once every session. Subsequent reloads of resource/data pack will not regenerate them even if assets might have changed as a consequence
                        - CACHED: Generate the assets on first boot and saves them to a cache folder in .minecraft/dynamic-data-pack-cache. If mods or packs change it will regenerate the cache. If not, subsequent reload won't generate anything and just read the cached ones as a normal pack.
                        - CACHED_ZIPPED: Generate the assets on first boot and saves them to a cache zip file in .minecraft/dynamic-data-pack-cache. If mods or packs change it will regenerate the cache. If not, subsequent reload won't generate anything and just read the cached ones as a normal pack.
                        - ALWAYS: Will always generate the assets & will be stored in memory. No cache is used. Unintuitively, this is often the fastest method as any disk access will be slow. \nTry and see what works best for you.""")
                .worldReload()
                .define("server_assets_generation_mode", GenMode.ALWAYS);

        SPEC = builder.build();

        SPEC.forceLoad(); //manually load early
    }

}
