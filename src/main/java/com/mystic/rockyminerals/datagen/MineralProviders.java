package com.mystic.rockyminerals.datagen;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class MineralProviders {
    public static void init(IEventBus bus) {
        bus.addListener(MineralProviders::dataGather);
    }

    public static void dataGather(GatherDataEvent event) {
        var output = event.getGenerator().getPackOutput();
        event.getGenerator().addProvider(true, new MineralBlockModelProvider(output, event.getExistingFileHelper()));
        event.getGenerator().addProvider(true, new MainProvider(output, event.getExistingFileHelper(), MineralBlockStateProvider::new));
        event.getGenerator().addProvider(true, new MineralItemModelProvider(output, event.getExistingFileHelper()));
        event.getGenerator().addProvider(true, new MineralEnglishLanguageProvider(output));
        event.getGenerator().addProvider(true, new RecipeProvider(output) {
            @Override
            protected void buildRecipes(@NotNull Consumer<FinishedRecipe> recipeOutput) {
            }
        });
    }
}
