package com.mystic.rockyminerals.dynamicpack;

import com.mystic.rockyminerals.RockyMineral;
import com.mystic.rockyminerals.api.set.StoneTypeRegistry;
import net.mehvahdjukaar.moonlight.api.resources.pack.DynServerResourcesGenerator;
import net.mehvahdjukaar.moonlight.api.resources.pack.DynamicDataPack;
import net.minecraft.server.packs.resources.ResourceManager;
import org.apache.logging.log4j.Logger;

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
    public boolean dependsOnLoadedPacks() {
        return false;
    }

    @Override
    public void regenerateDynamicAssets(ResourceManager resourceManager) {

        for (var test : StoneTypeRegistry.getTypes()) {
            if (test.isVanilla()) continue;

            if (test.getTypeName().equals("anhydrite")) RockyMineral.LOGGER.warn("STONE: Anhydrite");
        }
    }
}
