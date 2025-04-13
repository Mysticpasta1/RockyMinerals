package com.mystic.rockyminerals.api.set;

import net.mehvahdjukaar.moonlight.api.events.AfterLanguageLoadEvent;
import net.mehvahdjukaar.moonlight.api.set.BlockTypeRegistry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

public class CrystalTypeRegistry extends BlockTypeRegistry<CrystalType> {
    public static final CrystalTypeRegistry INSTANCE = new CrystalTypeRegistry();

    public CrystalTypeRegistry() {
        super(CrystalType.class, "crystal_type");

        this.addFinder(CrystalType.Finder.vanilla("amethyst", "amethyst_block"));
    }

    public static CrystalType getAmethystType() {
        return getValue("amethyst");
    }

    public static Collection<CrystalType> getTypes() {
        return INSTANCE.getValues();
    }

    public static CrystalType getValue(String CrystalTypeId) {
        return INSTANCE.get(ResourceLocation.parse(CrystalTypeId));
    }

    @Override
    public CrystalType getDefaultType() {
        return this.get(ResourceLocation.parse("amethyst"));
    }

    @Override
    public Optional<CrystalType> detectTypeFromBlock(Block baseblock, ResourceLocation baseRes) {
        String path = baseRes.getPath();

        if (baseRes.getNamespace().equals("rockyminerals")) {
            // Check for <type>_bricks
            if (path.matches("[a-z]+_(bricks|stairs)") && baseblock.defaultBlockState().instrument() == NoteBlockInstrument.HARP ) {
                String crystalName = path.substring(0, path.length() - 7); // get crystalName from namespace:crystalName_bricks
                ResourceLocation idBlockType = baseRes.withPath(crystalName);

                // Check if a BlockType is already added
                if ( Objects.isNull(get(idBlockType)) ) {
                    var opt = BuiltInRegistries.BLOCK.getOptional(idBlockType);
                    if (opt.isPresent()) return Optional.of(new CrystalType(baseRes.withPath(crystalName), opt.get()));
                }

            }
            // Check for polished_<type>
            else if (path.matches("polished_[a-z]+") && baseblock.defaultBlockState().instrument() == NoteBlockInstrument.HARP ) {
                String crystalName = path.replace("polished_", ""); // get crystalName from namespace:polished_crystalName
                ResourceLocation idBlockType = baseRes.withPath(crystalName);

                // Check if a BlockType is already added
                if ( Objects.isNull(get(idBlockType)) ) {
                    var opt = BuiltInRegistries.BLOCK.getOptional(idBlockType);
                    if (opt.isPresent()) return Optional.of(new CrystalType(baseRes.withPath(crystalName), opt.get()));

                }
            }
        }
        return Optional.empty();
    }

    @Override
    public void addTypeTranslations(AfterLanguageLoadEvent language) {
        this.getValues().forEach((crystalType) -> {
            if (language.isDefault()) language.addEntry(crystalType.getTranslationKey(), crystalType.getReadableName());
        });
    }

    @Override
    public int priority() {
        return 110;
    }
}
