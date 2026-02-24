package com.mystic.rockyminerals.dynamicpack;

import com.mystic.rockyminerals.RockyMineral;
import com.mystic.rockyminerals.configs.RockyMineralConfigs;
import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.mehvahdjukaar.moonlight.api.resources.pack.DynClientResourcesGenerator;
import net.mehvahdjukaar.moonlight.api.resources.pack.DynamicTexturePack;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceGenTask;
import org.apache.logging.log4j.Logger;

import java.util.function.Consumer;

public class ClientDynamicResourcesHandler extends DynClientResourcesGenerator {
    private static ClientDynamicResourcesHandler INSTANCE;

    public static ClientDynamicResourcesHandler getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ClientDynamicResourcesHandler();
        }
        return INSTANCE;
    }

    public ClientDynamicResourcesHandler() {
        super(new DynamicTexturePack(RockyMineral.res("generated_pack")));
    }

    @Override
    public Logger getLogger() {
        return RockyMineral.LOGGER;
    }


    @Override
    public void regenerateDynamicAssets(Consumer<ResourceGenTask> executor) {
        this.dynamicPack.setGenerateDebugResources(PlatHelper.isDev());

        if (!RockyMineralConfigs.GENERATE_DYNAMIC_CLIENT.get()) return;

        try {
            executor.accept(ResourcesGenerator::generateResources);
        } catch (Throwable e) {
            RockyMineral.LOGGER.error("Error while generating dynamic resources for client");
        }
    }

}
