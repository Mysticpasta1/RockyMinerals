package com.mystic.rockyminerals.datagen;

import com.mystic.rockyminerals.Main;
import com.mystic.rockyminerals.init.Init;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.predicates.ExplosionCondition;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class MineralProviders {
    public static void init(IEventBus bus) {
        bus.addListener(MineralProviders::dataGather);
    }

    public static void dataGather(GatherDataEvent event) {
        var output = event.getGenerator().getPackOutput();
        event.getGenerator().addProvider(true, new MainProvider(output, event.getExistingFileHelper(), MineralBlockStateProvider::new));
        event.getGenerator().addProvider(true, new MineralEnglishLanguageProvider(output));
        event.getGenerator().addProvider(true, new RecipeProvider(output) {
            @Override
            protected void buildRecipes(@NotNull Consumer<FinishedRecipe> recipeOutput) {
            }
        });

        LootTableProvider.SubProviderEntry lootTableProvider = new LootTableProvider.SubProviderEntry(() -> consumer -> {
            var saltstoneTypes = Init.SALTSTONE;
            dropSelf(saltstoneTypes.block().get(), consumer);
            dropSelf(saltstoneTypes.slab().get(), consumer);
            dropSelf(saltstoneTypes.stairs().get(), consumer);
            dropSelf(saltstoneTypes.wall().get(), consumer);
            dropSelf(saltstoneTypes.button().get(), consumer);
            dropSelf(saltstoneTypes.pressurePlate().get(), consumer);
        }, LootContextParamSets.BLOCK);

        BlockTagsProvider blockTagsProvider = new BlockTagsProvider(output, event.getLookupProvider(), Main.MODID, event.getExistingFileHelper()) {
            @Override
            protected void addTags(HolderLookup.@NotNull Provider pProvider) {
                var saltstoneTypes = Init.SALTSTONE;
                tag(BlockTags.MINEABLE_WITH_PICKAXE).add(saltstoneTypes.block().get());
                tag(BlockTags.MINEABLE_WITH_PICKAXE).add(saltstoneTypes.slab().get());
                tag(BlockTags.MINEABLE_WITH_PICKAXE).add(saltstoneTypes.stairs().get());
                tag(BlockTags.MINEABLE_WITH_PICKAXE).add(saltstoneTypes.wall().get());
                tag(BlockTags.MINEABLE_WITH_PICKAXE).add(saltstoneTypes.button().get());
                tag(BlockTags.MINEABLE_WITH_PICKAXE).add(saltstoneTypes.pressurePlate().get());
            }
        };

        ItemTagsProvider itemTagsProvider = new ItemTagsProvider(output, event.getLookupProvider(), blockTagsProvider.contentsGetter(), Main.MODID, event.getExistingFileHelper()) {
            @Override
            protected void addTags(HolderLookup.@NotNull Provider pProvider) {
                var saltstoneTypes = Init.SALTSTONE;
                tag(ItemTags.STONE_CRAFTING_MATERIALS).add(saltstoneTypes.block().get().asItem());
                tag(ItemTags.STONE_TOOL_MATERIALS).add(saltstoneTypes.block().get().asItem());
            }
        };

        event.getGenerator().addProvider(true, blockTagsProvider);
        event.getGenerator().addProvider(true, itemTagsProvider);
        event.getGenerator().addProvider(true, new LootTableProvider(output, Set.of(), List.of()) {
            @Override
            public @NotNull List<SubProviderEntry> getTables() {
                return List.of(lootTableProvider);
            }
        });
    }

    private static void dropSelf(Block block, BiConsumer<ResourceLocation, LootTable.Builder> builder){
        builder.accept(block.getLootTable(), LootTable.lootTable().withPool(LootPool.lootPool().add(LootItem.lootTableItem(block)
                .when(ExplosionCondition.survivesExplosion())).add(LootItem.lootTableItem(block))));
    }
}
