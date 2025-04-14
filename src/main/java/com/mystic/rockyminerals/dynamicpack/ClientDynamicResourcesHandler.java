package com.mystic.rockyminerals.dynamicpack;

import com.mystic.rockyminerals.Main;
import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.mehvahdjukaar.moonlight.api.resources.pack.DynClientResourcesGenerator;
import net.mehvahdjukaar.moonlight.api.resources.pack.DynamicTexturePack;
import net.minecraft.server.packs.resources.ResourceManager;
import org.apache.logging.log4j.Logger;

public class ClientDynamicResourcesHandler extends DynClientResourcesGenerator {
    private static ClientDynamicResourcesHandler INSTANCE;

    public static ClientDynamicResourcesHandler getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ClientDynamicResourcesHandler();
        }
        return INSTANCE;
    }

    public ClientDynamicResourcesHandler() {
        super(new DynamicTexturePack(Main.res("generated_pack")));
    }

    @Override
    public Logger getLogger() {
        return Main.LOGGER;
    }

    @Override
    public boolean dependsOnLoadedPacks() {
        return false;
    }

    @Override
    public void regenerateDynamicAssets(ResourceManager manager) {
        this.dynamicPack.setGenerateDebugResources(PlatHelper.isDev());

        ResourcesGenerator.generateResources(this, manager);
    }
}
