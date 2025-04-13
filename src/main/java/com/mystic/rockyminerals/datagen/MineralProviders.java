package com.mystic.rockyminerals.datagen;

import com.mystic.rockyminerals.Main;
import com.mystic.rockyminerals.init.Init;
import com.mystic.rockyminerals.utils.BlockType;
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
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
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
                        Init.SALTSTONE_BRICKS, Init.SALTSTONE_TILE, Init.POLISHED_SALTSTONE, Init.SALTSTONE_PILLAR,
                        Init.SALTSTONE_MOSAIC, Init.CUT_SALTSTONE, Init.SALTSTONE_LAMP, Init.SALTSTONE_REDSTONE_LAMP.get());

                buildRecipesForAllVariants(recipeOutput,
                        Init.HALITE, Init.COBBLED_HALITE, Init.CHISELED_HALITE, Init.CRACKED_HALITE,
                        Init.HALITE_BRICKS, Init.HALITE_TILE, Init.POLISHED_HALITE, Init.HALITE_PILLAR,
                        Init.HALITE_MOSAIC, Init.CUT_HALITE, Init.HALITE_LAMP, Init.HALITE_REDSTONE_LAMP.get());

                buildRecipesForAllVariants(recipeOutput,
                        Init.WORN_GRANITE, Init.COBBLED_WORN_GRANITE, Init.CHISELED_WORN_GRANITE, Init.CRACKED_WORN_GRANITE,
                        Init.WORN_GRANITE_BRICKS, Init.WORN_GRANITE_TILE, Init.POLISHED_WORN_GRANITE, Init.WORN_GRANITE_PILLAR,
                        Init.WORN_GRANITE_MOSAIC, Init.CUT_WORN_GRANITE, Init.WORN_GRANITE_LAMP, Init.WORN_GRANITE_REDSTONE_LAMP.get());

                buildRecipesForAllVariants(recipeOutput,
                        Init.ANHYDRITE, Init.COBBLED_ANHYDRITE, Init.CHISELED_ANHYDRITE, Init.CRACKED_ANHYDRITE,
                        Init.ANHYDRITE_BRICKS, Init.ANHYDRITE_TILE, Init.POLISHED_ANHYDRITE, Init.ANHYDRITE_PILLAR,
                        Init.ANHYDRITE_MOSAIC, Init.CUT_ANHYDRITE, Init.ANHYDRITE_LAMP, Init.ANHYDRITE_REDSTONE_LAMP.get());

                buildRecipesForAllVariants(recipeOutput,
                        Init.OPAL, Init.COBBLED_OPAL, Init.CHISELED_OPAL, Init.CRACKED_OPAL,
                        Init.OPAL_BRICKS, Init.OPAL_TILE, Init.POLISHED_OPAL, Init.OPAL_PILLAR,
                        Init.OPAL_MOSAIC, Init.CUT_OPAL, Init.OPAL_LAMP, Init.OPAL_REDSTONE_LAMP.get());

                buildRecipesForAllVariants(recipeOutput,
                        Init.BLUE_CALCITE, Init.COBBLED_BLUE_CALCITE, Init.CHISELED_BLUE_CALCITE, Init.CRACKED_BLUE_CALCITE,
                        Init.BLUE_CALCITE_BRICKS, Init.BLUE_CALCITE_TILE, Init.POLISHED_BLUE_CALCITE, Init.BLUE_CALCITE_PILLAR,
                        Init.BLUE_CALCITE_MOSAIC, Init.CUT_BLUE_CALCITE, Init.BLUE_CALCITE_LAMP, Init.BLUE_CALCITE_REDSTONE_LAMP.get());

                buildRecipesForAllVariants(recipeOutput,
                        Init.PUMICE, Init.COBBLED_PUMICE, Init.CHISELED_PUMICE, Init.CRACKED_PUMICE,
                        Init.PUMICE_BRICKS, Init.PUMICE_TILE, Init.POLISHED_PUMICE, Init.PUMICE_PILLAR,
                        Init.PUMICE_MOSAIC, Init.CUT_PUMICE, Init.PUMICE_LAMP, Init.PUMICE_REDSTONE_LAMP.get());

                buildRecipesForAllVariants(recipeOutput,
                        Init.RHYOLITE, Init.COBBLED_RHYOLITE, Init.CHISELED_RHYOLITE, Init.CRACKED_RHYOLITE,
                        Init.RHYOLITE_BRICKS, Init.RHYOLITE_TILE, Init.POLISHED_RHYOLITE, Init.RHYOLITE_PILLAR,
                        Init.RHYOLITE_MOSAIC, Init.CUT_RHYOLITE, Init.RHYOLITE_LAMP, Init.RHYOLITE_REDSTONE_LAMP.get());
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
            //Anhydrite Variants
            var anhydriteTypes = Init.ANHYDRITE;
            dropCobbleVariant(anhydriteTypes.block().get(), Init.COBBLED_ANHYDRITE.block().get(), consumer);
            dropSelf(anhydriteTypes.slab().get(), consumer);
            dropSelf(anhydriteTypes.stairs().get(), consumer);
            dropSelf(anhydriteTypes.wall().get(), consumer);
            dropSelf(anhydriteTypes.button().get(), consumer);
            dropSelf(anhydriteTypes.pressurePlate().get(), consumer);
            generateLootTableStoneTypes(Init.COBBLED_ANHYDRITE, consumer);
            generateLootTableStoneTypes(Init.CHISELED_ANHYDRITE, consumer);
            generateLootTableStoneTypes(Init.CRACKED_ANHYDRITE, consumer);
            generateLootTableStoneTypes(Init.ANHYDRITE_BRICKS, consumer);
            generateLootTableStoneTypes(Init.ANHYDRITE_TILE, consumer);
            generateLootTableStoneTypes(Init.POLISHED_ANHYDRITE, consumer);
            generateLootTableStoneTypes(Init.ANHYDRITE_PILLAR, consumer);
            generateLootTableStoneTypes(Init.ANHYDRITE_MOSAIC, consumer);
            generateLootTableStoneTypes(Init.CUT_ANHYDRITE, consumer);
            generateLootTableStoneTypes(Init.ANHYDRITE_LAMP, consumer);
            dropSelf(Init.ANHYDRITE_REDSTONE_LAMP.get(), consumer);

            //Opal Variants
            var opalTypes = Init.OPAL;
            dropCobbleVariant(opalTypes.block().get(), Init.COBBLED_OPAL.block().get(), consumer);
            dropSelf(opalTypes.slab().get(), consumer);
            dropSelf(opalTypes.stairs().get(), consumer);
            dropSelf(opalTypes.wall().get(), consumer);
            dropSelf(opalTypes.button().get(), consumer);
            dropSelf(opalTypes.pressurePlate().get(), consumer);
            generateLootTableStoneTypes(Init.COBBLED_OPAL, consumer);
            generateLootTableStoneTypes(Init.CHISELED_OPAL, consumer);
            generateLootTableStoneTypes(Init.CRACKED_OPAL, consumer);
            generateLootTableStoneTypes(Init.OPAL_BRICKS, consumer);
            generateLootTableStoneTypes(Init.OPAL_TILE, consumer);
            generateLootTableStoneTypes(Init.POLISHED_OPAL, consumer);
            generateLootTableStoneTypes(Init.OPAL_PILLAR, consumer);
            generateLootTableStoneTypes(Init.OPAL_MOSAIC, consumer);
            generateLootTableStoneTypes(Init.CUT_OPAL, consumer);
            generateLootTableStoneTypes(Init.OPAL_LAMP, consumer);
            dropSelf(Init.OPAL_REDSTONE_LAMP.get(), consumer);

            //Blue Calcite Variants
            var blueCalciteTypes = Init.BLUE_CALCITE;
            dropCobbleVariant(blueCalciteTypes.block().get(), Init.COBBLED_BLUE_CALCITE.block().get(), consumer);
            dropSelf(blueCalciteTypes.slab().get(), consumer);
            dropSelf(blueCalciteTypes.stairs().get(), consumer);
            dropSelf(blueCalciteTypes.wall().get(), consumer);
            dropSelf(blueCalciteTypes.button().get(), consumer);
            dropSelf(blueCalciteTypes.pressurePlate().get(), consumer);
            generateLootTableStoneTypes(Init.COBBLED_BLUE_CALCITE, consumer);
            generateLootTableStoneTypes(Init.CHISELED_BLUE_CALCITE, consumer);
            generateLootTableStoneTypes(Init.CRACKED_BLUE_CALCITE, consumer);
            generateLootTableStoneTypes(Init.BLUE_CALCITE_BRICKS, consumer);
            generateLootTableStoneTypes(Init.BLUE_CALCITE_TILE, consumer);
            generateLootTableStoneTypes(Init.POLISHED_BLUE_CALCITE, consumer);
            generateLootTableStoneTypes(Init.BLUE_CALCITE_PILLAR, consumer);
            generateLootTableStoneTypes(Init.BLUE_CALCITE_MOSAIC, consumer);
            generateLootTableStoneTypes(Init.CUT_BLUE_CALCITE, consumer);
            generateLootTableStoneTypes(Init.BLUE_CALCITE_LAMP, consumer);
            dropSelf(Init.BLUE_CALCITE_REDSTONE_LAMP.get(), consumer);

            //Pumice Variants
            var pumiceTypes = Init.PUMICE;
            dropCobbleVariant(pumiceTypes.block().get(), Init.COBBLED_PUMICE.block().get(), consumer);
            dropSelf(pumiceTypes.slab().get(), consumer);
            dropSelf(pumiceTypes.stairs().get(), consumer);
            dropSelf(pumiceTypes.wall().get(), consumer);
            dropSelf(pumiceTypes.button().get(), consumer);
            dropSelf(pumiceTypes.pressurePlate().get(), consumer);
            generateLootTableStoneTypes(Init.COBBLED_PUMICE, consumer);
            generateLootTableStoneTypes(Init.CHISELED_PUMICE, consumer);
            generateLootTableStoneTypes(Init.CRACKED_PUMICE, consumer);
            generateLootTableStoneTypes(Init.PUMICE_BRICKS, consumer);
            generateLootTableStoneTypes(Init.PUMICE_TILE, consumer);
            generateLootTableStoneTypes(Init.POLISHED_PUMICE, consumer);
            generateLootTableStoneTypes(Init.PUMICE_PILLAR, consumer);
            generateLootTableStoneTypes(Init.PUMICE_MOSAIC, consumer);
            generateLootTableStoneTypes(Init.CUT_PUMICE, consumer);
            generateLootTableStoneTypes(Init.PUMICE_LAMP, consumer);
            dropSelf(Init.PUMICE_REDSTONE_LAMP.get(), consumer);

            //Rhyolite Variants
            var rhyoliteTypes = Init.RHYOLITE;
            dropCobbleVariant(rhyoliteTypes.block().get(), Init.COBBLED_RHYOLITE.block().get(), consumer);
            dropSelf(rhyoliteTypes.slab().get(), consumer);
            dropSelf(rhyoliteTypes.stairs().get(), consumer);
            dropSelf(rhyoliteTypes.wall().get(), consumer);
            dropSelf(rhyoliteTypes.button().get(), consumer);
            dropSelf(rhyoliteTypes.pressurePlate().get(), consumer);
            generateLootTableStoneTypes(Init.COBBLED_RHYOLITE, consumer);
            generateLootTableStoneTypes(Init.CHISELED_RHYOLITE, consumer);
            generateLootTableStoneTypes(Init.CRACKED_RHYOLITE, consumer);
            generateLootTableStoneTypes(Init.RHYOLITE_BRICKS, consumer);
            generateLootTableStoneTypes(Init.RHYOLITE_TILE, consumer);
            generateLootTableStoneTypes(Init.POLISHED_RHYOLITE, consumer);
            generateLootTableStoneTypes(Init.RHYOLITE_PILLAR, consumer);
            generateLootTableStoneTypes(Init.RHYOLITE_MOSAIC, consumer);
            generateLootTableStoneTypes(Init.CUT_RHYOLITE, consumer);
            generateLootTableStoneTypes(Init.RHYOLITE_LAMP, consumer);
            dropSelf(Init.RHYOLITE_REDSTONE_LAMP.get(), consumer);

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
            generateLootTableStoneTypes(Init.SALTSTONE_BRICKS, consumer);
            generateLootTableStoneTypes(Init.SALTSTONE_TILE, consumer);
            generateLootTableStoneTypes(Init.POLISHED_SALTSTONE, consumer);
            generateLootTableStoneTypes(Init.SALTSTONE_PILLAR, consumer);
            generateLootTableStoneTypes(Init.SALTSTONE_MOSAIC, consumer);
            generateLootTableStoneTypes(Init.CUT_SALTSTONE, consumer);
            generateLootTableStoneTypes(Init.SALTSTONE_LAMP, consumer);
            dropSelf(Init.SALTSTONE_REDSTONE_LAMP.get(), consumer);

            //Halite Variants
            var haliteTypes = Init.HALITE;
            dropCobbleVariant(haliteTypes.block().get(), Init.COBBLED_HALITE.block().get(), consumer);
            dropSelf(haliteTypes.slab().get(), consumer);
            dropSelf(haliteTypes.stairs().get(), consumer);
            dropSelf(haliteTypes.wall().get(), consumer);
            dropSelf(haliteTypes.button().get(), consumer);
            dropSelf(haliteTypes.pressurePlate().get(), consumer);
            generateLootTableStoneTypes(Init.COBBLED_HALITE, consumer);
            generateLootTableStoneTypes(Init.CHISELED_HALITE, consumer);
            generateLootTableStoneTypes(Init.CRACKED_HALITE, consumer);
            generateLootTableStoneTypes(Init.HALITE_BRICKS, consumer);
            generateLootTableStoneTypes(Init.HALITE_TILE, consumer);
            generateLootTableStoneTypes(Init.POLISHED_HALITE, consumer);
            generateLootTableStoneTypes(Init.HALITE_PILLAR, consumer);
            generateLootTableStoneTypes(Init.HALITE_MOSAIC, consumer);
            generateLootTableStoneTypes(Init.CUT_HALITE, consumer);
            generateLootTableStoneTypes(Init.HALITE_LAMP, consumer);
            dropSelf(Init.HALITE_REDSTONE_LAMP.get(), consumer);

            //Worn Granite Variants
            var wornGraniteTypes = Init.WORN_GRANITE;
            dropCobbleVariant(wornGraniteTypes.block().get(), Init.COBBLED_WORN_GRANITE.block().get(), consumer);
            dropSelf(wornGraniteTypes.slab().get(), consumer);
            dropSelf(wornGraniteTypes.stairs().get(), consumer);
            dropSelf(wornGraniteTypes.wall().get(), consumer);
            dropSelf(wornGraniteTypes.button().get(), consumer);
            dropSelf(wornGraniteTypes.pressurePlate().get(), consumer);
            generateLootTableStoneTypes(Init.COBBLED_WORN_GRANITE, consumer);
            generateLootTableStoneTypes(Init.CHISELED_WORN_GRANITE, consumer);
            generateLootTableStoneTypes(Init.CRACKED_WORN_GRANITE, consumer);
            generateLootTableStoneTypes(Init.WORN_GRANITE_BRICKS, consumer);
            generateLootTableStoneTypes(Init.WORN_GRANITE_TILE, consumer);
            generateLootTableStoneTypes(Init.POLISHED_WORN_GRANITE, consumer);
            generateLootTableStoneTypes(Init.WORN_GRANITE_PILLAR, consumer);
            generateLootTableStoneTypes(Init.WORN_GRANITE_MOSAIC, consumer);
            generateLootTableStoneTypes(Init.CUT_WORN_GRANITE, consumer);
            generateLootTableStoneTypes(Init.WORN_GRANITE_LAMP, consumer);
            dropSelf(Init.WORN_GRANITE_REDSTONE_LAMP.get(), consumer);
        }, LootContextParamSets.BLOCK);

        BlockTagsProvider blockTagsProvider = new BlockTagsProvider(output, event.getLookupProvider(), Main.MOD_ID, event.getExistingFileHelper()) {
            @Override
            protected void addTags(HolderLookup.@NotNull Provider pProvider) {
                //Anhydrite Variant
                generateBlockTypeTags(Init.ANHYDRITE);
                generateBlockTypeTags(Init.COBBLED_ANHYDRITE);
                generateBlockTypeTags(Init.CHISELED_ANHYDRITE);
                generateBlockTypeTags(Init.CRACKED_ANHYDRITE);
                generateBlockTypeTags(Init.ANHYDRITE_BRICKS);
                generateBlockTypeTags(Init.ANHYDRITE_TILE);
                generateBlockTypeTags(Init.POLISHED_ANHYDRITE);
                generateBlockTypeTags(Init.ANHYDRITE_PILLAR);
                generateBlockTypeTags(Init.ANHYDRITE_MOSAIC);
                generateBlockTypeTags(Init.CUT_ANHYDRITE);
                generateBlockTypeTags(Init.ANHYDRITE_LAMP);
                tag(BlockTags.MINEABLE_WITH_PICKAXE).add(Init.ANHYDRITE_REDSTONE_LAMP.get());

                //Opal Variant
                generateBlockTypeTags(Init.OPAL);
                generateBlockTypeTags(Init.COBBLED_OPAL);
                generateBlockTypeTags(Init.CHISELED_OPAL);
                generateBlockTypeTags(Init.CRACKED_OPAL);
                generateBlockTypeTags(Init.OPAL_BRICKS);
                generateBlockTypeTags(Init.OPAL_TILE);
                generateBlockTypeTags(Init.POLISHED_OPAL);
                generateBlockTypeTags(Init.OPAL_PILLAR);
                generateBlockTypeTags(Init.OPAL_MOSAIC);
                generateBlockTypeTags(Init.CUT_OPAL);
                generateBlockTypeTags(Init.OPAL_LAMP);
                tag(BlockTags.MINEABLE_WITH_PICKAXE).add(Init.OPAL_REDSTONE_LAMP.get());

                //Blue Calcite Variant
                generateBlockTypeTags(Init.BLUE_CALCITE);
                generateBlockTypeTags(Init.COBBLED_BLUE_CALCITE);
                generateBlockTypeTags(Init.CHISELED_BLUE_CALCITE);
                generateBlockTypeTags(Init.CRACKED_BLUE_CALCITE);
                generateBlockTypeTags(Init.BLUE_CALCITE_BRICKS);
                generateBlockTypeTags(Init.BLUE_CALCITE_TILE);
                generateBlockTypeTags(Init.POLISHED_BLUE_CALCITE);
                generateBlockTypeTags(Init.BLUE_CALCITE_PILLAR);
                generateBlockTypeTags(Init.BLUE_CALCITE_MOSAIC);
                generateBlockTypeTags(Init.CUT_BLUE_CALCITE);
                generateBlockTypeTags(Init.BLUE_CALCITE_LAMP);
                tag(BlockTags.MINEABLE_WITH_PICKAXE).add(Init.BLUE_CALCITE_REDSTONE_LAMP.get());

                //Pumice Variant
                generateBlockTypeTags(Init.PUMICE);
                generateBlockTypeTags(Init.COBBLED_PUMICE);
                generateBlockTypeTags(Init.CHISELED_PUMICE);
                generateBlockTypeTags(Init.CRACKED_PUMICE);
                generateBlockTypeTags(Init.PUMICE_BRICKS);
                generateBlockTypeTags(Init.PUMICE_TILE);
                generateBlockTypeTags(Init.POLISHED_PUMICE);
                generateBlockTypeTags(Init.PUMICE_PILLAR);
                generateBlockTypeTags(Init.PUMICE_MOSAIC);
                generateBlockTypeTags(Init.CUT_PUMICE);
                generateBlockTypeTags(Init.PUMICE_LAMP);
                tag(BlockTags.MINEABLE_WITH_PICKAXE).add(Init.PUMICE_REDSTONE_LAMP.get());

                //Rhyolite Variant
                generateBlockTypeTags(Init.RHYOLITE);
                generateBlockTypeTags(Init.COBBLED_RHYOLITE);
                generateBlockTypeTags(Init.CHISELED_RHYOLITE);
                generateBlockTypeTags(Init.CRACKED_RHYOLITE);
                generateBlockTypeTags(Init.RHYOLITE_BRICKS);
                generateBlockTypeTags(Init.RHYOLITE_TILE);
                generateBlockTypeTags(Init.POLISHED_RHYOLITE);
                generateBlockTypeTags(Init.RHYOLITE_PILLAR);
                generateBlockTypeTags(Init.RHYOLITE_MOSAIC);
                generateBlockTypeTags(Init.CUT_RHYOLITE);
                generateBlockTypeTags(Init.RHYOLITE_LAMP);
                tag(BlockTags.MINEABLE_WITH_PICKAXE).add(Init.RHYOLITE_REDSTONE_LAMP.get());

                //Saltstone Variant
                generateBlockTypeTags(Init.SALTSTONE);
                generateBlockTypeTags(Init.COBBLED_SALTSTONE);
                generateBlockTypeTags(Init.CHISELED_SALTSTONE);
                generateBlockTypeTags(Init.CRACKED_SALTSTONE);
                generateBlockTypeTags(Init.SALTSTONE_BRICKS);
                generateBlockTypeTags(Init.SALTSTONE_TILE);
                generateBlockTypeTags(Init.POLISHED_SALTSTONE);
                generateBlockTypeTags(Init.SALTSTONE_PILLAR);
                generateBlockTypeTags(Init.SALTSTONE_MOSAIC);
                generateBlockTypeTags(Init.CUT_SALTSTONE);
                generateBlockTypeTags(Init.SALTSTONE_LAMP);
                tag(BlockTags.MINEABLE_WITH_PICKAXE).add(Init.SALTSTONE_REDSTONE_LAMP.get());

                //Halite Variants
                generateBlockTypeTags(Init.HALITE);
                generateBlockTypeTags(Init.COBBLED_HALITE);
                generateBlockTypeTags(Init.CHISELED_HALITE);
                generateBlockTypeTags(Init.CRACKED_HALITE);
                generateBlockTypeTags(Init.HALITE_BRICKS);
                generateBlockTypeTags(Init.HALITE_TILE);
                generateBlockTypeTags(Init.POLISHED_HALITE);
                generateBlockTypeTags(Init.HALITE_PILLAR);
                generateBlockTypeTags(Init.HALITE_MOSAIC);
                generateBlockTypeTags(Init.CUT_HALITE);
                generateBlockTypeTags(Init.HALITE_LAMP);
                tag(BlockTags.MINEABLE_WITH_PICKAXE).add(Init.HALITE_REDSTONE_LAMP.get());

                //Worn Granite Variants
                generateBlockTypeTags(Init.WORN_GRANITE);
                generateBlockTypeTags(Init.COBBLED_WORN_GRANITE);
                generateBlockTypeTags(Init.CHISELED_WORN_GRANITE);
                generateBlockTypeTags(Init.CRACKED_WORN_GRANITE);
                generateBlockTypeTags(Init.WORN_GRANITE_BRICKS);
                generateBlockTypeTags(Init.WORN_GRANITE_TILE);
                generateBlockTypeTags(Init.POLISHED_WORN_GRANITE);
                generateBlockTypeTags(Init.WORN_GRANITE_PILLAR);
                generateBlockTypeTags(Init.WORN_GRANITE_MOSAIC);
                generateBlockTypeTags(Init.CUT_WORN_GRANITE);
                generateBlockTypeTags(Init.WORN_GRANITE_LAMP);
                tag(BlockTags.MINEABLE_WITH_PICKAXE).add(Init.WORN_GRANITE_REDSTONE_LAMP.get());
            }

            private void generateBlockTypeTags(BlockType blockType) {
                tag(BlockTags.MINEABLE_WITH_PICKAXE).add(blockType.block().get());
                tag(BlockTags.MINEABLE_WITH_PICKAXE).add(blockType.slab().get());
                tag(BlockTags.MINEABLE_WITH_PICKAXE).add(blockType.stairs().get());
                tag(BlockTags.MINEABLE_WITH_PICKAXE).add(blockType.wall().get());
                tag(BlockTags.MINEABLE_WITH_PICKAXE).add(blockType.button().get());
                tag(BlockTags.MINEABLE_WITH_PICKAXE).add(blockType.pressurePlate().get());
                tag(BlockTags.WALLS).add(blockType.wall().get());
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

                var haliteTypes = Init.HALITE;
                tag(ItemTags.STONE_CRAFTING_MATERIALS).add(haliteTypes.block().get().asItem());
                tag(ItemTags.STONE_TOOL_MATERIALS).add(haliteTypes.block().get().asItem());

                var cobbleHaliteTypes = Init.COBBLED_HALITE;
                tag(ItemTags.STONE_CRAFTING_MATERIALS).add(cobbleHaliteTypes.block().get().asItem());
                tag(ItemTags.STONE_TOOL_MATERIALS).add(cobbleHaliteTypes.block().get().asItem());

                var wornGraniteTypes = Init.WORN_GRANITE;
                tag(ItemTags.STONE_CRAFTING_MATERIALS).add(wornGraniteTypes.block().get().asItem());
                tag(ItemTags.STONE_TOOL_MATERIALS).add(wornGraniteTypes.block().get().asItem());

                var cobbledWornGraniteTypes = Init.COBBLED_WORN_GRANITE;
                tag(ItemTags.STONE_CRAFTING_MATERIALS).add(cobbledWornGraniteTypes.block().get().asItem());
                tag(ItemTags.STONE_TOOL_MATERIALS).add(cobbledWornGraniteTypes.block().get().asItem());

                var opalTypes = Init.OPAL;
                tag(ItemTags.STONE_CRAFTING_MATERIALS).add(opalTypes.block().get().asItem());
                tag(ItemTags.STONE_TOOL_MATERIALS).add(opalTypes.block().get().asItem());

                var cobbledOpalTypes = Init.COBBLED_OPAL;
                tag(ItemTags.STONE_CRAFTING_MATERIALS).add(cobbledOpalTypes.block().get().asItem());
                tag(ItemTags.STONE_TOOL_MATERIALS).add(cobbledOpalTypes.block().get().asItem());

                var blueCalciteTypes = Init.BLUE_CALCITE;
                tag(ItemTags.STONE_CRAFTING_MATERIALS).add(blueCalciteTypes.block().get().asItem());
                tag(ItemTags.STONE_TOOL_MATERIALS).add(blueCalciteTypes.block().get().asItem());

                var cobbledBlueCalciteTypes = Init.COBBLED_BLUE_CALCITE;
                tag(ItemTags.STONE_CRAFTING_MATERIALS).add(cobbledBlueCalciteTypes.block().get().asItem());
                tag(ItemTags.STONE_TOOL_MATERIALS).add(cobbledBlueCalciteTypes.block().get().asItem());

                var pumiceTypes = Init.PUMICE;
                tag(ItemTags.STONE_CRAFTING_MATERIALS).add(pumiceTypes.block().get().asItem());
                tag(ItemTags.STONE_TOOL_MATERIALS).add(pumiceTypes.block().get().asItem());

                var cobbledPumiceTypes = Init.COBBLED_PUMICE;
                tag(ItemTags.STONE_CRAFTING_MATERIALS).add(cobbledPumiceTypes.block().get().asItem());
                tag(ItemTags.STONE_TOOL_MATERIALS).add(cobbledPumiceTypes.block().get().asItem());

                var rhyoliteTypes = Init.RHYOLITE;
                tag(ItemTags.STONE_CRAFTING_MATERIALS).add(rhyoliteTypes.block().get().asItem());
                tag(ItemTags.STONE_TOOL_MATERIALS).add(rhyoliteTypes.block().get().asItem());

                var cobbledRhyoliteTypes = Init.COBBLED_RHYOLITE;
                tag(ItemTags.STONE_CRAFTING_MATERIALS).add(cobbledRhyoliteTypes.block().get().asItem());
                tag(ItemTags.STONE_TOOL_MATERIALS).add(cobbledRhyoliteTypes.block().get().asItem());

                var anhydriteTypes = Init.ANHYDRITE;
                tag(ItemTags.STONE_CRAFTING_MATERIALS).add(anhydriteTypes.block().get().asItem());
                tag(ItemTags.STONE_TOOL_MATERIALS).add(anhydriteTypes.block().get().asItem());

                var cobbledAnhydriteTypes = Init.COBBLED_ANHYDRITE;
                tag(ItemTags.STONE_CRAFTING_MATERIALS).add(cobbledAnhydriteTypes.block().get().asItem());
                tag(ItemTags.STONE_TOOL_MATERIALS).add(cobbledAnhydriteTypes.block().get().asItem());
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
