package com.mystic.rockyminerals.datagen;

import com.mystic.rockyminerals.Main;
import com.mystic.rockyminerals.init.Init;
import com.mystic.rockyminerals.utils.BlockType;
import net.mehvahdjukaar.moonlight.core.recipe.StoneCutterRecipeTemplate;
import net.minecraft.advancements.critereon.EnchantmentPredicate;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.data.recipes.*;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.BlastingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.SmeltingRecipe;
import net.minecraft.world.item.crafting.StonecutterRecipe;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RedstoneLampBlock;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.predicates.ExplosionCondition;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
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
        event.getGenerator().addProvider(true, new MineralBlockModelProvider(output, event.getExistingFileHelper()));
        event.getGenerator().addProvider(true, new MainProvider(output, event.getExistingFileHelper(), MineralBlockStateProvider::new));
        event.getGenerator().addProvider(true, new MineralEnglishLanguageProvider(output));
        event.getGenerator().addProvider(true, new RecipeProvider(output) {
            @Override
            protected void buildRecipes(@NotNull Consumer<FinishedRecipe> recipeOutput) {
                buildRecipesForAllVariants(recipeOutput,
                        Init.SALTSTONE, Init.COBBLED_SALTSTONE, Init.CHISELED_SALTSTONE, Init.CRACKED_SALTSTONE,
                        Init.SALTSTONE_BRICK, Init.SALTSTONE_TILE, Init.POLISHED_SALTSTONE, Init.SALTSTONE_PILLAR,
                        Init.SALTSTONE_MOSAIC, Init.CUT_SALTSTONE, Init.SALTSTONE_LAMP, Init.SALTSTONE_REDSTONE_LAMP.get());
            }

            private static void buildRecipesForAllVariants(@NotNull Consumer<FinishedRecipe> recipeOutput, BlockType original, BlockType cobbled, BlockType cracked, BlockType chiseled,
                                                           BlockType brick, BlockType tile, BlockType polished,
                                                           BlockType pillar, BlockType mosaic, BlockType cut,
                                                           BlockType lamp, Block redstoneLamp) {
                buildBlockTypeVariants(recipeOutput, original);
                buildBlockTypeVariants(recipeOutput, cobbled);
                buildBlockTypeVariants(recipeOutput, chiseled);
                buildBlockTypeVariants(recipeOutput, brick);
                buildBlockTypeVariants(recipeOutput, tile);
                buildBlockTypeVariants(recipeOutput, polished);
                buildBlockTypeVariants(recipeOutput, pillar);
                buildBlockTypeVariants(recipeOutput, mosaic);
                buildBlockTypeVariants(recipeOutput, cut);
                buildBlockTypeVariants(recipeOutput, lamp);

                SimpleCookingRecipeBuilder.smelting(Ingredient.of(brick.block().get()), RecipeCategory.BUILDING_BLOCKS, cracked.block().get(), 0.7F, 200)
                        .unlockedBy(getHasName(brick.block().get()), has(brick.block().get()))
                        .save(recipeOutput, Main.res(cracked.block().get().getDescriptionId().replace("block.rockyminerals.", "") + "_smelting"));

                SimpleCookingRecipeBuilder.blasting(Ingredient.of(brick.block().get()), RecipeCategory.BUILDING_BLOCKS, cracked.block().get(), 0.7F, 100)
                        .unlockedBy(getHasName(brick.block().get()), has(brick.block().get()))
                        .save(recipeOutput, Main.res(cracked.block().get().getDescriptionId().replace("block.rockyminerals.", "") + "_blasting"));

                SimpleCookingRecipeBuilder.smelting(Ingredient.of(cobbled.block().get()), RecipeCategory.BUILDING_BLOCKS, original.block().get(), 0.7F, 200)
                        .unlockedBy(getHasName(cobbled.block().get()), has(cobbled.block().get()))
                        .save(recipeOutput, Main.res(original.block().get().getDescriptionId().replace("block.rockyminerals.", "") + "_smelting"));

                SimpleCookingRecipeBuilder.blasting(Ingredient.of(cobbled.block().get()), RecipeCategory.BUILDING_BLOCKS, original.block().get(), 0.7F, 100)
                        .unlockedBy(getHasName(cobbled.block().get()), has(cobbled.block().get()))
                        .save(recipeOutput, Main.res(original.block().get().getDescriptionId().replace("block.rockyminerals.", "") + "_blasting"));

                ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, brick.block().get(), 4)
                        .pattern("## ")
                        .pattern("## ")
                        .pattern("   ")
                        .define('#', cobbled.block().get())
                        .unlockedBy(getHasName(cobbled.block().get()), has(cobbled.block().get().asItem()))
                        .save(recipeOutput, Main.res(brick.block().get().getDescriptionId().replace("block.rockyminerals.", "") + "_recipe"));

                ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, polished.block().get(), 4)
                        .pattern("## ")
                        .pattern("## ")
                        .pattern("   ")
                        .define('#', original.block().get())
                        .unlockedBy(getHasName(original.block().get()), has(original.block().get().asItem()))
                        .save(recipeOutput, Main.res(polished.block().get().getDescriptionId().replace("block.rockyminerals.", "") + "_recipe"));

                ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, tile.block().get(), 4)
                        .pattern("## ")
                        .pattern("## ")
                        .pattern("   ")
                        .define('#', brick.block().get())
                        .unlockedBy(getHasName(brick.block().get()), has(brick.block().get().asItem()))
                        .save(recipeOutput, Main.res(tile.block().get().getDescriptionId().replace("block.rockyminerals.", "") + "_recipe"));

                ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, chiseled.block().get(), 1)
                        .pattern(" # ")
                        .pattern(" # ")
                        .pattern("   ")
                        .define('#', brick.slab().get())
                        .unlockedBy(getHasName(brick.slab().get()), has(brick.slab().get().asItem()))
                        .save(recipeOutput, Main.res(chiseled.block().get().getDescriptionId().replace("block.rockyminerals.", "") + "_recipe"));

                ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, mosaic.block().get(), 1)
                        .pattern(" # ")
                        .pattern(" # ")
                        .pattern("   ")
                        .define('#', chiseled.slab().get())
                        .unlockedBy(getHasName(chiseled.slab().get()), has(chiseled.slab().get().asItem()))
                        .save(recipeOutput, Main.res(mosaic.block().get().getDescriptionId().replace("block.rockyminerals.", "") + "_recipe"));

                ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, pillar.block().get(), 1)
                        .pattern(" # ")
                        .pattern(" # ")
                        .pattern("   ")
                        .define('#', original.block().get())
                        .unlockedBy(getHasName(original.block().get()), has(original.block().get().asItem()))
                        .save(recipeOutput, Main.res(pillar.block().get().getDescriptionId().replace("block.rockyminerals.", "") + "_recipe"));

                ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, cut.block().get(), 4)
                        .pattern("## ")
                        .pattern("## ")
                        .pattern("   ")
                        .define('#', polished.block().get())
                        .unlockedBy(getHasName(polished.block().get()), has(polished.block().get().asItem()))
                        .save(recipeOutput, Main.res(cut.block().get().getDescriptionId().replace("block.rockyminerals.", "") + "_recipe"));

                ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, lamp.block().get(), 4)
                        .pattern(" # ")
                        .pattern("#g#")
                        .pattern(" # ")
                        .define('#', original.block().get())
                        .define('g', Items.GLOWSTONE_DUST)
                        .unlockedBy(getHasName(original.block().get()), has(original.block().get().asItem()))
                        .unlockedBy(getHasName(Items.GLOWSTONE_DUST), has(Items.GLOWSTONE_DUST))
                        .save(recipeOutput, Main.res(lamp.block().get().getDescriptionId().replace("block.rockyminerals.", "") + "_recipe"));

                ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, redstoneLamp, 4)
                        .pattern(" r ")
                        .pattern("r#r")
                        .pattern(" r ")
                        .define('#', lamp.block().get())
                        .define('r', Items.REDSTONE)
                        .unlockedBy(getHasName(lamp.block().get()), has(lamp.block().get().asItem()))
                        .unlockedBy(getHasName(Items.REDSTONE), has(Items.REDSTONE))
                        .save(recipeOutput, Main.res(redstoneLamp.getDescriptionId().replace("block.rockyminerals.", "") + "_recipe"));

                SingleItemRecipeBuilder.stonecutting(Ingredient.of(original.block().get()), RecipeCategory.BUILDING_BLOCKS, cobbled.block().get(), 1)
                        .unlockedBy(getHasName(original.block().get()), has(original.block().get().asItem()))
                        .save(recipeOutput, Main.res(cobbled.block().get().getDescriptionId().replace("block.rockyminerals.", "") + "_stonecutter"));
                SingleItemRecipeBuilder.stonecutting(Ingredient.of(original.block().get()), RecipeCategory.BUILDING_BLOCKS, cracked.block().get(), 1)
                        .unlockedBy(getHasName(original.block().get()), has(original.block().get().asItem()))
                        .save(recipeOutput, Main.res(cracked.block().get().getDescriptionId().replace("block.rockyminerals.", "") + "_stonecutter"));
                SingleItemRecipeBuilder.stonecutting(Ingredient.of(original.block().get()), RecipeCategory.BUILDING_BLOCKS, chiseled.block().get(), 1)
                        .unlockedBy(getHasName(original.block().get()), has(original.block().get().asItem()))
                        .save(recipeOutput, Main.res(chiseled.block().get().getDescriptionId().replace("block.rockyminerals.", "") + "_stonecutter"));
                SingleItemRecipeBuilder.stonecutting(Ingredient.of(original.block().get()), RecipeCategory.BUILDING_BLOCKS, brick.block().get(), 1)
                        .unlockedBy(getHasName(original.block().get()), has(original.block().get().asItem()))
                        .save(recipeOutput, Main.res(brick.block().get().getDescriptionId().replace("block.rockyminerals.", "") + "_stonecutter"));
                SingleItemRecipeBuilder.stonecutting(Ingredient.of(original.block().get()), RecipeCategory.BUILDING_BLOCKS, tile.block().get(), 1)
                        .unlockedBy(getHasName(original.block().get()), has(original.block().get().asItem()))
                        .save(recipeOutput, Main.res(tile.block().get().getDescriptionId().replace("block.rockyminerals.", "") + "_stonecutter"));
                SingleItemRecipeBuilder.stonecutting(Ingredient.of(original.block().get()), RecipeCategory.BUILDING_BLOCKS, polished.block().get(), 1)
                        .unlockedBy(getHasName(original.block().get()), has(original.block().get().asItem()))
                        .save(recipeOutput, Main.res(polished.block().get().getDescriptionId().replace("block.rockyminerals.", "") + "_stonecutter"));
                SingleItemRecipeBuilder.stonecutting(Ingredient.of(original.block().get()), RecipeCategory.BUILDING_BLOCKS, pillar.block().get(), 1)
                        .unlockedBy(getHasName(original.block().get()), has(original.block().get().asItem()))
                        .save(recipeOutput, Main.res(pillar.block().get().getDescriptionId().replace("block.rockyminerals.", "") + "_stonecutter"));
                SingleItemRecipeBuilder.stonecutting(Ingredient.of(original.block().get()), RecipeCategory.BUILDING_BLOCKS, mosaic.block().get(), 1)
                        .unlockedBy(getHasName(original.block().get()), has(original.block().get().asItem()))
                        .save(recipeOutput, Main.res(mosaic.block().get().getDescriptionId().replace("block.rockyminerals.", "") + "_stonecutter"));
                SingleItemRecipeBuilder.stonecutting(Ingredient.of(original.block().get()), RecipeCategory.BUILDING_BLOCKS, cut.block().get(), 1)
                        .unlockedBy(getHasName(original.block().get()), has(original.block().get().asItem()))
                        .save(recipeOutput, Main.res(cut.block().get().getDescriptionId().replace("block.rockyminerals.", "") + "_stonecutter"));
            }

            private static void buildBlockTypeVariants(@NotNull Consumer<FinishedRecipe> recipeOutput, BlockType blockType) {
                ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, blockType.button().get(), 1)
                        .requires(blockType.block().get())
                        .unlockedBy(getHasName(blockType.block().get()), has(blockType.block().get().asItem()))
                        .save(recipeOutput, Main.res(blockType.button().get().getDescriptionId().replace("block.rockyminerals.", "") + "_recipe"));

                ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, blockType.stairs().get(), 4)
                        .pattern("#  ")
                        .pattern("## ")
                        .pattern("###")
                        .define('#', blockType.block().get())
                        .unlockedBy(getHasName(blockType.block().get()), has(blockType.block().get().asItem()))
                        .save(recipeOutput, Main.res(blockType.stairs().get().getDescriptionId().replace("block.rockyminerals.", "") + "_recipe"));

                ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, blockType.slab().get(), 6)
                        .pattern("   ")
                        .pattern("###")
                        .pattern("   ")
                        .define('#', blockType.block().get())
                        .unlockedBy(getHasName(blockType.block().get()), has(blockType.block().get().asItem()))
                        .save(recipeOutput, Main.res(blockType.slab().get().getDescriptionId().replace("block.rockyminerals.", "") + "_recipe"));

                ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, blockType.pressurePlate().get(), 1)
                        .pattern("   ")
                        .pattern("## ")
                        .pattern("   ")
                        .define('#', blockType.block().get())
                        .unlockedBy(getHasName(blockType.block().get()), has(blockType.block().get().asItem()))
                        .save(recipeOutput, Main.res(blockType.pressurePlate().get().getDescriptionId().replace("block.rockyminerals.", "") + "_recipe"));

                ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, blockType.wall().get(), 6)
                        .pattern("   ")
                        .pattern("###")
                        .pattern("###")
                        .define('#', blockType.block().get())
                        .unlockedBy(getHasName(blockType.block().get()), has(blockType.block().get().asItem()))
                        .save(recipeOutput, Main.res(blockType.wall().get().getDescriptionId().replace("block.rockyminerals.", "") + "_recipe"));

                SingleItemRecipeBuilder.stonecutting(Ingredient.of(blockType.block().get().asItem()), RecipeCategory.BUILDING_BLOCKS, blockType.slab().get().asItem(), 2)
                        .unlockedBy(getHasName(blockType.block().get()), has(blockType.block().get().asItem()))
                        .save(recipeOutput, Main.res(blockType.slab().get().getDescriptionId().replace("block.rockyminerals.", "") + "_stonecutter"));
                SingleItemRecipeBuilder.stonecutting(Ingredient.of(blockType.block().get().asItem()), RecipeCategory.BUILDING_BLOCKS, blockType.button().get().asItem(), 1)
                        .unlockedBy(getHasName(blockType.block().get()), has(blockType.block().get().asItem()))
                        .save(recipeOutput, Main.res(blockType.button().get().getDescriptionId().replace("block.rockyminerals.", "") + "_stonecutter"));
                SingleItemRecipeBuilder.stonecutting(Ingredient.of(blockType.block().get().asItem()), RecipeCategory.BUILDING_BLOCKS, blockType.stairs().get().asItem(), 1)
                        .unlockedBy(getHasName(blockType.block().get()), has(blockType.block().get().asItem()))
                        .save(recipeOutput, Main.res(blockType.stairs().get().getDescriptionId().replace("block.rockyminerals.", "") + "_stonecutter"));
                SingleItemRecipeBuilder.stonecutting(Ingredient.of(blockType.block().get().asItem()), RecipeCategory.BUILDING_BLOCKS, blockType.wall().get().asItem(), 1)
                        .unlockedBy(getHasName(blockType.block().get()), has(blockType.block().get().asItem()))
                        .save(recipeOutput, Main.res(blockType.wall().get().getDescriptionId().replace("block.rockyminerals.", "") + "_stonecutter"));
                SingleItemRecipeBuilder.stonecutting(Ingredient.of(blockType.block().get().asItem()), RecipeCategory.BUILDING_BLOCKS, blockType.pressurePlate().get().asItem(), 1)
                        .unlockedBy(getHasName(blockType.block().get()), has(blockType.block().get().asItem()))
                        .save(recipeOutput, Main.res(blockType.pressurePlate().get().getDescriptionId().replace("block.rockyminerals.", "") + "_stonecutter"));
            }
        });

        LootTableProvider.SubProviderEntry lootTableProvider = new LootTableProvider.SubProviderEntry(() -> consumer -> {
            //Saltstone Variants
            var saltstoneTypes = Init.SALTSTONE;
            dropCobbleVariant(saltstoneTypes.block().get(), Init.COBBLED_SALTSTONE.block().get(), consumer);
            dropSelf(saltstoneTypes.slab().get(), consumer);
            dropSelf(saltstoneTypes.stairs().get(), consumer);
            dropSelf(saltstoneTypes.wall().get(), consumer);
            dropSelf(saltstoneTypes.button().get(), consumer);
            dropSelf(saltstoneTypes.pressurePlate().get(), consumer);
            generateLootTableStoneTypes(Init.COBBLED_SALTSTONE, consumer);
            generateLootTableStoneTypes(Init.CHISELED_SALTSTONE, consumer);
            generateLootTableStoneTypes(Init.CRACKED_SALTSTONE, consumer);
            generateLootTableStoneTypes(Init.SALTSTONE_BRICK, consumer);
            generateLootTableStoneTypes(Init.SALTSTONE_TILE, consumer);
            generateLootTableStoneTypes(Init.POLISHED_SALTSTONE, consumer);
            generateLootTableStoneTypes(Init.SALTSTONE_PILLAR, consumer);
            generateLootTableStoneTypes(Init.SALTSTONE_MOSAIC, consumer);
            generateLootTableStoneTypes(Init.CUT_SALTSTONE, consumer);
            generateLootTableStoneTypes(Init.SALTSTONE_LAMP, consumer);
            dropSelf(Init.SALTSTONE_REDSTONE_LAMP.get(), consumer);
        }, LootContextParamSets.BLOCK);

        BlockTagsProvider blockTagsProvider = new BlockTagsProvider(output, event.getLookupProvider(), Main.MOD_ID, event.getExistingFileHelper()) {
            @Override
            protected void addTags(HolderLookup.@NotNull Provider pProvider) {
                //Saltstone Variant
                generateBlockTypeTags(Init.SALTSTONE);
                generateBlockTypeTags(Init.COBBLED_SALTSTONE);
                generateBlockTypeTags(Init.CHISELED_SALTSTONE);
                generateBlockTypeTags(Init.CRACKED_SALTSTONE);
                generateBlockTypeTags(Init.SALTSTONE_BRICK);
                generateBlockTypeTags(Init.SALTSTONE_TILE);
                generateBlockTypeTags(Init.POLISHED_SALTSTONE);
                generateBlockTypeTags(Init.SALTSTONE_PILLAR);
                generateBlockTypeTags(Init.SALTSTONE_MOSAIC);
                generateBlockTypeTags(Init.CUT_SALTSTONE);
                generateBlockTypeTags(Init.SALTSTONE_LAMP);
                tag(BlockTags.MINEABLE_WITH_PICKAXE).add(Init.SALTSTONE_REDSTONE_LAMP.get());
            }

            private void generateBlockTypeTags(BlockType blockType) {
                tag(BlockTags.MINEABLE_WITH_PICKAXE).add(blockType.block().get());
                tag(BlockTags.MINEABLE_WITH_PICKAXE).add(blockType.slab().get());
                tag(BlockTags.MINEABLE_WITH_PICKAXE).add(blockType.stairs().get());
                tag(BlockTags.MINEABLE_WITH_PICKAXE).add(blockType.wall().get());
                tag(BlockTags.MINEABLE_WITH_PICKAXE).add(blockType.button().get());
                tag(BlockTags.MINEABLE_WITH_PICKAXE).add(blockType.pressurePlate().get());
            }
        };

        ItemTagsProvider itemTagsProvider = new ItemTagsProvider(output, event.getLookupProvider(), blockTagsProvider.contentsGetter(), Main.MOD_ID, event.getExistingFileHelper()) {
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

        event.getGenerator().

                addProvider(true, blockTagsProvider);
        event.getGenerator().

                addProvider(true, itemTagsProvider);
        event.getGenerator().

                addProvider(true, new LootTableProvider(output, Set.of(), List.

                        of()) {
                    @Override
                    public @NotNull List<SubProviderEntry> getTables() {
                        return List.of(lootTableProvider);
                    }
                });
    }

    private static void generateLootTableStoneTypes(BlockType blockType, BiConsumer<ResourceLocation, LootTable.Builder> consumer) {
        dropSelf(blockType.block().get(), consumer);
        dropSelf(blockType.slab().get(), consumer);
        dropSelf(blockType.stairs().get(), consumer);
        dropSelf(blockType.wall().get(), consumer);
        dropSelf(blockType.button().get(), consumer);
        dropSelf(blockType.pressurePlate().get(), consumer);
    }

    private static void dropSelf(Block block, BiConsumer<ResourceLocation, LootTable.Builder> builder) {
        builder.accept(block.getLootTable(), LootTable.lootTable().withPool(LootPool.lootPool().add(LootItem.lootTableItem(block)
                .when(ExplosionCondition.survivesExplosion())).add(LootItem.lootTableItem(block))));
    }

    private static void dropCobbleVariant(Block block, Block block2, BiConsumer<ResourceLocation, LootTable.Builder> builder) {
        builder.accept(block.getLootTable(), LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .add(LootItem.lootTableItem(block).when(MatchTool.toolMatches(
                                ItemPredicate.Builder.item().hasEnchantment(new EnchantmentPredicate(Enchantments.SILK_TOUCH, MinMaxBounds.Ints.ANY)))))
                        .add(LootItem.lootTableItem(block2).when(ExplosionCondition.survivesExplosion()))));
    }
}
