package com.mystic.rockyminerals;

import com.mystic.rockyminerals.api.set.StoneTypeRegistry;
import com.mystic.rockyminerals.datagen.MineralProviders;
import com.mystic.rockyminerals.dynamicpack.ClientDynamicResourcesHandler;
import com.mystic.rockyminerals.init.Init;
import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.mehvahdjukaar.moonlight.api.set.BlockSetAPI;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@Mod(Main.MOD_ID)
public class Main {
    public static final String MOD_ID = "rockyminerals";
    public static final Logger LOGGER = LogManager.getLogger("Rocky Minerals");

    public Main(FMLJavaModLoadingContext context) {
        var bus = context.getModEventBus();
        Init.init(bus);
        MineralProviders.init(bus);
        BlockSetAPI.registerBlockSetDefinition(StoneTypeRegistry.INSTANCE);

        if (PlatHelper.getPhysicalSide().isClient()) {
            ClientDynamicResourcesHandler.getInstance().register();
        }
    }

    public static ResourceLocation res(String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    }
}
