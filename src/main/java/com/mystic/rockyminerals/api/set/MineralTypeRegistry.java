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

public class MineralTypeRegistry extends BlockTypeRegistry<MineralType> {
    public static final MineralTypeRegistry INSTANCE = new MineralTypeRegistry();

    public MineralTypeRegistry() {
        super(MineralType.class, "mineral_type");
    }


    public static Collection<MineralType> getTypes() {
        return INSTANCE.getValues();
    }

    public static MineralType getValue(String mineralTypeId) {
        return INSTANCE.get(ResourceLocation.parse(mineralTypeId));
    }

    @Override
    public MineralType getDefaultType() {
        return this.get(ResourceLocation.parse("amethyst"));
    }

    @Override
    public Optional<MineralType> detectTypeFromBlock(Block baseblock, ResourceLocation baseRes) {
        String path = baseRes.getPath();

        if (baseRes.getNamespace().equals("rockyminerals")) {
            // Check for <type>_bricks
            if (path.matches("[a-z]+_(bricks|stairs)") && baseblock.defaultBlockState().instrument() == NoteBlockInstrument.HARP ) {
                String mineralName = path.substring(0, path.length() - 7); // get mineralName from namespace:mineralName_bricks
                ResourceLocation idBlockType = baseRes.withPath(mineralName);

                // Check if a BlockType is already added
                if ( Objects.isNull(get(idBlockType)) ) {
                    var opt = BuiltInRegistries.BLOCK.getOptional(idBlockType);
                    if (opt.isPresent()) return Optional.of(new MineralType(baseRes.withPath(mineralName), opt.get()));
                }

            }
            // Check for polished_<type>
            else if (path.matches("polished_[a-z]+") && baseblock.defaultBlockState().instrument() == NoteBlockInstrument.HARP ) {
                String mineralName = path.replace("polished_", ""); // get mineralName from namespace:polished_mineralName
                ResourceLocation idBlockType = baseRes.withPath(mineralName);

                // Check if a BlockType is already added
                if ( Objects.isNull(get(idBlockType)) ) {
                    var opt = BuiltInRegistries.BLOCK.getOptional(idBlockType);
                    if (opt.isPresent()) return Optional.of(new MineralType(baseRes.withPath(mineralName), opt.get()));

                }
            }
        }
        return Optional.empty();
    }

    @Override
    public void addTypeTranslations(AfterLanguageLoadEvent language) {
        this.getValues().forEach((mineralType) -> {
            if (language.isDefault()) language.addEntry(mineralType.getTranslationKey(), mineralType.getReadableName());
        });
    }

    @Override
    public int priority() {
        return 110;
    }
}
