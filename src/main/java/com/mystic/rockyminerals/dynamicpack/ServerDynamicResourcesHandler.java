package com.mystic.rockyminerals.dynamicpack;

import com.mystic.rockyminerals.RockyMineral;
import com.mystic.rockyminerals.configs.RockyMineralConfigs;
import net.mehvahdjukaar.moonlight.api.resources.pack.DynamicServerResourceProvider;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceGenTask;

import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;

public class ServerDynamicResourcesHandler extends DynamicServerResourceProvider {
    public static ServerDynamicResourcesHandler INSTANCE;

    public static ServerDynamicResourcesHandler getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ServerDynamicResourcesHandler();
        }
        return INSTANCE;
    }

    protected ServerDynamicResourcesHandler() {
        super(RockyMineral.res("generated_pack"), RockyMineralConfigs.SERVER_GENERATION_MODE.get().pickStrategy());
    }

    @Override
    protected Collection<String> gatherSupportedNamespaces() {
        return List.of();
    }

    @Override
    public void regenerateDynamicAssets(Consumer<ResourceGenTask> executor) {

//        for (var test : StoneTypeRegistry.getTypes()) {
//            if (test.isVanilla()) continue;
//
//            if (test.getTypeName().equals("anhydrite")) RockyMineral.LOGGER.warn("STONE: Anhydrite");
//        }
    }
}
