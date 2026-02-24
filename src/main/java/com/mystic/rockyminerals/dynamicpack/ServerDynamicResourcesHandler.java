package com.mystic.rockyminerals.dynamicpack;

import com.mystic.rockyminerals.RockyMineral;
import com.mystic.rockyminerals.configs.RockyMineralConfigs;
import net.mehvahdjukaar.moonlight.api.resources.pack.DynServerResourcesGenerator;
import net.mehvahdjukaar.moonlight.api.resources.pack.DynamicDataPack;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceGenTask;
import org.apache.logging.log4j.Logger;

import java.util.function.Consumer;

public class ServerDynamicResourcesHandler extends DynServerResourcesGenerator {
    public static final ServerDynamicResourcesHandler INSTANCE = new ServerDynamicResourcesHandler();

    protected ServerDynamicResourcesHandler() {
        super(new DynamicDataPack(RockyMineral.res("generated_pack")));
    }

    @Override
    public Logger getLogger() {
        return RockyMineral.LOGGER;
    }

    @Override
    public void regenerateDynamicAssets(Consumer<ResourceGenTask> executor) {
//        if (!RockyMineralConfigs.GENERATE_DYNAMIC_SERVER.get()) return;
    }
}
