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
            //Saltstone Variants
            var saltstoneTypes = Init.SALTSTONE;
            dropSelf(saltstoneTypes.block().get(), consumer);
            dropSelf(saltstoneTypes.slab().get(), consumer);
            dropSelf(saltstoneTypes.stairs().get(), consumer);
            dropSelf(saltstoneTypes.wall().get(), consumer);
            dropSelf(saltstoneTypes.button().get(), consumer);
            dropSelf(saltstoneTypes.pressurePlate().get(), consumer);

            var cobbledSaltstoneTypes = Init.COBBLED_SALTSTONE;
            dropSelf(cobbledSaltstoneTypes.block().get(), consumer);
            dropSelf(cobbledSaltstoneTypes.slab().get(), consumer);
            dropSelf(cobbledSaltstoneTypes.stairs().get(), consumer);
            dropSelf(cobbledSaltstoneTypes.wall().get(), consumer);
            dropSelf(cobbledSaltstoneTypes.button().get(), consumer);
            dropSelf(cobbledSaltstoneTypes.pressurePlate().get(), consumer);

            var chiseledSaltstoneTypes = Init.CHISELED_SALTSTONE;
            dropSelf(chiseledSaltstoneTypes.block().get(), consumer);
            dropSelf(chiseledSaltstoneTypes.slab().get(), consumer);
            dropSelf(chiseledSaltstoneTypes.stairs().get(), consumer);
            dropSelf(chiseledSaltstoneTypes.wall().get(), consumer);
            dropSelf(chiseledSaltstoneTypes.button().get(), consumer);
            dropSelf(chiseledSaltstoneTypes.pressurePlate().get(), consumer);

            var crackedSaltstoneTypes = Init.CRACKED_SALTSTONE;
            dropSelf(crackedSaltstoneTypes.block().get(), consumer);
            dropSelf(crackedSaltstoneTypes.slab().get(), consumer);
            dropSelf(crackedSaltstoneTypes.stairs().get(), consumer);
            dropSelf(crackedSaltstoneTypes.wall().get(), consumer);
            dropSelf(crackedSaltstoneTypes.button().get(), consumer);
            dropSelf(crackedSaltstoneTypes.pressurePlate().get(), consumer);

            var saltstoneBrickTypes = Init.SALTSTONE_BRICK;
            dropSelf(saltstoneBrickTypes.block().get(), consumer);
            dropSelf(saltstoneBrickTypes.slab().get(), consumer);
            dropSelf(saltstoneBrickTypes.stairs().get(), consumer);
            dropSelf(saltstoneBrickTypes.wall().get(), consumer);
            dropSelf(saltstoneBrickTypes.button().get(), consumer);
            dropSelf(saltstoneBrickTypes.pressurePlate().get(), consumer);

            var saltstoneTileTypes = Init.SALTSTONE_TILE;
            dropSelf(saltstoneTileTypes.block().get(), consumer);
            dropSelf(saltstoneTileTypes.slab().get(), consumer);
            dropSelf(saltstoneTileTypes.stairs().get(), consumer);
            dropSelf(saltstoneTileTypes.wall().get(), consumer);
            dropSelf(saltstoneTileTypes.button().get(), consumer);
            dropSelf(saltstoneTileTypes.pressurePlate().get(), consumer);

            var polishedSaltstoneTypes = Init.POLISHED_SALTSTONE;
            dropSelf(polishedSaltstoneTypes.block().get(), consumer);
            dropSelf(polishedSaltstoneTypes.slab().get(), consumer);
            dropSelf(polishedSaltstoneTypes.stairs().get(), consumer);
            dropSelf(polishedSaltstoneTypes.wall().get(), consumer);
            dropSelf(polishedSaltstoneTypes.button().get(), consumer);
            dropSelf(polishedSaltstoneTypes.pressurePlate().get(), consumer);

            var saltstonePillarTypes = Init.SALTSTONE_PILLAR;
            dropSelf(saltstonePillarTypes.block().get(), consumer);
            dropSelf(saltstonePillarTypes.slab().get(), consumer);
            dropSelf(saltstonePillarTypes.stairs().get(), consumer);
            dropSelf(saltstonePillarTypes.wall().get(), consumer);
            dropSelf(saltstonePillarTypes.button().get(), consumer);
            dropSelf(saltstonePillarTypes.pressurePlate().get(), consumer);

            var saltstoneMosaicTypes = Init.SALTSTONE_MOSAIC;
            dropSelf(saltstoneMosaicTypes.block().get(), consumer);
            dropSelf(saltstoneMosaicTypes.slab().get(), consumer);
            dropSelf(saltstoneMosaicTypes.stairs().get(), consumer);
            dropSelf(saltstoneMosaicTypes.wall().get(), consumer);
            dropSelf(saltstoneMosaicTypes.button().get(), consumer);
            dropSelf(saltstoneMosaicTypes.pressurePlate().get(), consumer);

            var cutSaltstoneTypes = Init.CUT_SALTSTONE;
            dropSelf(cutSaltstoneTypes.block().get(), consumer);
            dropSelf(cutSaltstoneTypes.slab().get(), consumer);
            dropSelf(cutSaltstoneTypes.stairs().get(), consumer);
            dropSelf(cutSaltstoneTypes.wall().get(), consumer);
            dropSelf(cutSaltstoneTypes.button().get(), consumer);
            dropSelf(cutSaltstoneTypes.pressurePlate().get(), consumer);

            var saltstoneLampTypes = Init.SALTSTONE_LAMP;
            dropSelf(saltstoneLampTypes.block().get(), consumer);
            dropSelf(saltstoneLampTypes.slab().get(), consumer);
            dropSelf(saltstoneLampTypes.stairs().get(), consumer);
            dropSelf(saltstoneLampTypes.wall().get(), consumer);
            dropSelf(saltstoneLampTypes.button().get(), consumer);
            dropSelf(saltstoneLampTypes.pressurePlate().get(), consumer);

            var saltstoneRedstoneLampTypes = Init.SALTSTONE_REDSTONE_LAMP;
            dropSelf(saltstoneRedstoneLampTypes.block().get(), consumer);
            dropSelf(saltstoneRedstoneLampTypes.slab().get(), consumer);
            dropSelf(saltstoneRedstoneLampTypes.stairs().get(), consumer);
            dropSelf(saltstoneRedstoneLampTypes.wall().get(), consumer);
            dropSelf(saltstoneRedstoneLampTypes.button().get(), consumer);
            dropSelf(saltstoneRedstoneLampTypes.pressurePlate().get(), consumer);
        }, LootContextParamSets.BLOCK);

        BlockTagsProvider blockTagsProvider = new BlockTagsProvider(output, event.getLookupProvider(), Main.MODID, event.getExistingFileHelper()) {
            @Override
            protected void addTags(HolderLookup.@NotNull Provider pProvider) {
                //Saltstone Variants
                var saltstoneTypes = Init.SALTSTONE;
                tag(BlockTags.MINEABLE_WITH_PICKAXE).add(saltstoneTypes.block().get());
                tag(BlockTags.MINEABLE_WITH_PICKAXE).add(saltstoneTypes.slab().get());
                tag(BlockTags.MINEABLE_WITH_PICKAXE).add(saltstoneTypes.stairs().get());
                tag(BlockTags.MINEABLE_WITH_PICKAXE).add(saltstoneTypes.wall().get());
                tag(BlockTags.MINEABLE_WITH_PICKAXE).add(saltstoneTypes.button().get());
                tag(BlockTags.MINEABLE_WITH_PICKAXE).add(saltstoneTypes.pressurePlate().get());

                var cobbledSaltstoneTypes = Init.COBBLED_SALTSTONE;
                tag(BlockTags.MINEABLE_WITH_PICKAXE).add(cobbledSaltstoneTypes.block().get());
                tag(BlockTags.MINEABLE_WITH_PICKAXE).add(cobbledSaltstoneTypes.slab().get());
                tag(BlockTags.MINEABLE_WITH_PICKAXE).add(cobbledSaltstoneTypes.stairs().get());
                tag(BlockTags.MINEABLE_WITH_PICKAXE).add(cobbledSaltstoneTypes.wall().get());
                tag(BlockTags.MINEABLE_WITH_PICKAXE).add(cobbledSaltstoneTypes.button().get());
                tag(BlockTags.MINEABLE_WITH_PICKAXE).add(cobbledSaltstoneTypes.pressurePlate().get());

                var chiseledSaltstoneTypes = Init.CHISELED_SALTSTONE;
                tag(BlockTags.MINEABLE_WITH_PICKAXE).add(chiseledSaltstoneTypes.block().get());
                tag(BlockTags.MINEABLE_WITH_PICKAXE).add(chiseledSaltstoneTypes.slab().get());
                tag(BlockTags.MINEABLE_WITH_PICKAXE).add(chiseledSaltstoneTypes.stairs().get());
                tag(BlockTags.MINEABLE_WITH_PICKAXE).add(chiseledSaltstoneTypes.wall().get());
                tag(BlockTags.MINEABLE_WITH_PICKAXE).add(chiseledSaltstoneTypes.button().get());
                tag(BlockTags.MINEABLE_WITH_PICKAXE).add(chiseledSaltstoneTypes.pressurePlate().get());

                var crackedSaltstoneTypes = Init.CRACKED_SALTSTONE;
                tag(BlockTags.MINEABLE_WITH_PICKAXE).add(crackedSaltstoneTypes.block().get());
                tag(BlockTags.MINEABLE_WITH_PICKAXE).add(crackedSaltstoneTypes.slab().get());
                tag(BlockTags.MINEABLE_WITH_PICKAXE).add(crackedSaltstoneTypes.stairs().get());
                tag(BlockTags.MINEABLE_WITH_PICKAXE).add(crackedSaltstoneTypes.wall().get());
                tag(BlockTags.MINEABLE_WITH_PICKAXE).add(crackedSaltstoneTypes.button().get());
                tag(BlockTags.MINEABLE_WITH_PICKAXE).add(crackedSaltstoneTypes.pressurePlate().get());

                var saltstoneBrickTypes = Init.SALTSTONE_BRICK;
                tag(BlockTags.MINEABLE_WITH_PICKAXE).add(saltstoneBrickTypes.block().get());
                tag(BlockTags.MINEABLE_WITH_PICKAXE).add(saltstoneBrickTypes.slab().get());
                tag(BlockTags.MINEABLE_WITH_PICKAXE).add(saltstoneBrickTypes.stairs().get());
                tag(BlockTags.MINEABLE_WITH_PICKAXE).add(saltstoneBrickTypes.wall().get());
                tag(BlockTags.MINEABLE_WITH_PICKAXE).add(saltstoneBrickTypes.button().get());
                tag(BlockTags.MINEABLE_WITH_PICKAXE).add(saltstoneBrickTypes.pressurePlate().get());

                var saltstoneTileTypes = Init.SALTSTONE_TILE;
                tag(BlockTags.MINEABLE_WITH_PICKAXE).add(saltstoneTileTypes.block().get());
                tag(BlockTags.MINEABLE_WITH_PICKAXE).add(saltstoneTileTypes.slab().get());
                tag(BlockTags.MINEABLE_WITH_PICKAXE).add(saltstoneTileTypes.stairs().get());
                tag(BlockTags.MINEABLE_WITH_PICKAXE).add(saltstoneTileTypes.wall().get());
                tag(BlockTags.MINEABLE_WITH_PICKAXE).add(saltstoneTileTypes.button().get());
                tag(BlockTags.MINEABLE_WITH_PICKAXE).add(saltstoneTileTypes.pressurePlate().get());

                var polishedSaltstoneTypes = Init.POLISHED_SALTSTONE;
                tag(BlockTags.MINEABLE_WITH_PICKAXE).add(polishedSaltstoneTypes.block().get());
                tag(BlockTags.MINEABLE_WITH_PICKAXE).add(polishedSaltstoneTypes.slab().get());
                tag(BlockTags.MINEABLE_WITH_PICKAXE).add(polishedSaltstoneTypes.stairs().get());
                tag(BlockTags.MINEABLE_WITH_PICKAXE).add(polishedSaltstoneTypes.wall().get());
                tag(BlockTags.MINEABLE_WITH_PICKAXE).add(polishedSaltstoneTypes.button().get());
                tag(BlockTags.MINEABLE_WITH_PICKAXE).add(polishedSaltstoneTypes.pressurePlate().get());

                var saltstonePillarTypes = Init.SALTSTONE_PILLAR;
                tag(BlockTags.MINEABLE_WITH_PICKAXE).add(saltstonePillarTypes.block().get());
                tag(BlockTags.MINEABLE_WITH_PICKAXE).add(saltstonePillarTypes.slab().get());
                tag(BlockTags.MINEABLE_WITH_PICKAXE).add(saltstonePillarTypes.stairs().get());
                tag(BlockTags.MINEABLE_WITH_PICKAXE).add(saltstonePillarTypes.wall().get());
                tag(BlockTags.MINEABLE_WITH_PICKAXE).add(saltstonePillarTypes.button().get());
                tag(BlockTags.MINEABLE_WITH_PICKAXE).add(saltstonePillarTypes.pressurePlate().get());

                var saltstoneMosaicTypes = Init.SALTSTONE_MOSAIC;
                tag(BlockTags.MINEABLE_WITH_PICKAXE).add(saltstoneMosaicTypes.block().get());
                tag(BlockTags.MINEABLE_WITH_PICKAXE).add(saltstoneMosaicTypes.slab().get());
                tag(BlockTags.MINEABLE_WITH_PICKAXE).add(saltstoneMosaicTypes.stairs().get());
                tag(BlockTags.MINEABLE_WITH_PICKAXE).add(saltstoneMosaicTypes.wall().get());
                tag(BlockTags.MINEABLE_WITH_PICKAXE).add(saltstoneMosaicTypes.button().get());
                tag(BlockTags.MINEABLE_WITH_PICKAXE).add(saltstoneMosaicTypes.pressurePlate().get());

                var cutSaltstoneTypes = Init.CUT_SALTSTONE;
                tag(BlockTags.MINEABLE_WITH_PICKAXE).add(cutSaltstoneTypes.block().get());
                tag(BlockTags.MINEABLE_WITH_PICKAXE).add(cutSaltstoneTypes.slab().get());
                tag(BlockTags.MINEABLE_WITH_PICKAXE).add(cutSaltstoneTypes.stairs().get());
                tag(BlockTags.MINEABLE_WITH_PICKAXE).add(cutSaltstoneTypes.wall().get());
                tag(BlockTags.MINEABLE_WITH_PICKAXE).add(cutSaltstoneTypes.button().get());
                tag(BlockTags.MINEABLE_WITH_PICKAXE).add(cutSaltstoneTypes.pressurePlate().get());

                var saltstoneLampTypes = Init.SALTSTONE_LAMP;
                tag(BlockTags.MINEABLE_WITH_PICKAXE).add(saltstoneLampTypes.block().get());
                tag(BlockTags.MINEABLE_WITH_PICKAXE).add(saltstoneLampTypes.slab().get());
                tag(BlockTags.MINEABLE_WITH_PICKAXE).add(saltstoneLampTypes.stairs().get());
                tag(BlockTags.MINEABLE_WITH_PICKAXE).add(saltstoneLampTypes.wall().get());
                tag(BlockTags.MINEABLE_WITH_PICKAXE).add(saltstoneLampTypes.button().get());
                tag(BlockTags.MINEABLE_WITH_PICKAXE).add(saltstoneLampTypes.pressurePlate().get());

                var saltstoneRedstoneLampTypes = Init.SALTSTONE_REDSTONE_LAMP;
                tag(BlockTags.MINEABLE_WITH_PICKAXE).add(saltstoneRedstoneLampTypes.block().get());
                tag(BlockTags.MINEABLE_WITH_PICKAXE).add(saltstoneRedstoneLampTypes.slab().get());
                tag(BlockTags.MINEABLE_WITH_PICKAXE).add(saltstoneRedstoneLampTypes.stairs().get());
                tag(BlockTags.MINEABLE_WITH_PICKAXE).add(saltstoneRedstoneLampTypes.wall().get());
                tag(BlockTags.MINEABLE_WITH_PICKAXE).add(saltstoneRedstoneLampTypes.button().get());
                tag(BlockTags.MINEABLE_WITH_PICKAXE).add(saltstoneRedstoneLampTypes.pressurePlate().get());
            }
        };

        ItemTagsProvider itemTagsProvider = new ItemTagsProvider(output, event.getLookupProvider(), blockTagsProvider.contentsGetter(), Main.MODID, event.getExistingFileHelper()) {
            @Override
            protected void addTags(HolderLookup.@NotNull Provider pProvider) {
                var saltstoneTypes = Init.SALTSTONE;
                tag(ItemTags.STONE_CRAFTING_MATERIALS).add(saltstoneTypes.block().get().asItem());
                tag(ItemTags.STONE_TOOL_MATERIALS).add(saltstoneTypes.block().get().asItem());

                var cobbleSaltstoneTypes = Init.COBBLED_SALTSTONE;
                tag(ItemTags.STONE_CRAFTING_MATERIALS).add(cobbleSaltstoneTypes.block().get().asItem());
                tag(ItemTags.STONE_TOOL_MATERIALS).add(cobbleSaltstoneTypes.block().get().asItem());
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

    private static void dropSelf(Block block, BiConsumer<ResourceLocation, LootTable.Builder> builder) {
        builder.accept(block.getLootTable(), LootTable.lootTable().withPool(LootPool.lootPool().add(LootItem.lootTableItem(block)
                .when(ExplosionCondition.survivesExplosion())).add(LootItem.lootTableItem(block))));
    }
}
