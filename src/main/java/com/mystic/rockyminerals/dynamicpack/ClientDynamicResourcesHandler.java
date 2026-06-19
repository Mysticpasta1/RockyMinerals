package com.mystic.rockyminerals.dynamicpack;

import com.mystic.rockyminerals.RockyMineral;
import com.mystic.rockyminerals.configs.RockyMineralConfigs;
import net.mehvahdjukaar.moonlight.api.events.AfterLanguageLoadEvent;
import net.mehvahdjukaar.moonlight.api.resources.pack.DynamicClientResourceProvider;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceGenTask;

import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;

public class ClientDynamicResourcesHandler extends DynamicClientResourceProvider {
    private static ClientDynamicResourcesHandler INSTANCE;

    public static ClientDynamicResourcesHandler getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ClientDynamicResourcesHandler();
        }
        return INSTANCE;
    }

    public ClientDynamicResourcesHandler() {
        super(RockyMineral.res("generated_pack"), RockyMineralConfigs.CLIENT_GENERATION_MODE.get().pickStrategy());
    }

    @Override
    protected Collection<String> gatherSupportedNamespaces() {
        return List.of();
    }

    @Override
    protected void addDynamicTranslations(AfterLanguageLoadEvent afterLanguageLoadEvent) {

    }

    @Override
    public void regenerateDynamicAssets(Consumer<ResourceGenTask> executor) {

        executor.accept((manager, sink) ->
                ResourcesGenerator.generateResources(sink, manager)
        );
    }

}
