package com.mystic.rockyminerals.api.set;

import com.mystic.rockyminerals.RockyMineral;
import net.mehvahdjukaar.moonlight.api.events.AfterLanguageLoadEvent;
import net.mehvahdjukaar.moonlight.api.set.BlockTypeRegistry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

@SuppressWarnings("unused")
public class StoneTypeRegistry extends BlockTypeRegistry<StoneType> {

    public static final StoneTypeRegistry INSTANCE = new StoneTypeRegistry();

    public StoneTypeRegistry() {
        super(StoneType.class, RockyMineral.MOD_ID + ":stone_type");

    }

    public static StoneType getSaltstoneType() {
        return getValue("rockyminerals:saltstone");
    }

    public static Collection<StoneType> getTypes() {
        return INSTANCE.getValues();
    }

    public static StoneType getValue(String stoneTypeId) {
        return INSTANCE.get(ResourceLocation.parse(stoneTypeId));
    }

    @Override
    public StoneType getDefaultType() {
        return this.get(ResourceLocation.parse("stone"));
    }

    @Override
    public Optional<StoneType> detectTypeFromBlock(Block baseblock, ResourceLocation baseRes) {
        String path = baseRes.getPath();

        if (baseRes.getNamespace().equals("rockyminerals")) {
            // Check for <type>_bricks
            if (path.matches("[a-z]+_(bricks|stairs)") && baseblock.defaultBlockState().instrument() == NoteBlockInstrument.BASEDRUM ) {
                String stoneName = path.substring(0, path.length() - 7); // get stoneName from namespace:stoneName_bricks
                ResourceLocation idBlockType = baseRes.withPath(stoneName);

                // Check if a BlockType is already added
                if ( Objects.isNull(get(idBlockType)) ) {
                    var opt = BuiltInRegistries.BLOCK.getOptional(idBlockType);
                    if (opt.isPresent()) return Optional.of(new StoneType(baseRes.withPath(stoneName), opt.get()));
                }

            }
            // Check for polished_<type>
            else if (path.matches("polished_[a-z]+") && baseblock.defaultBlockState().instrument() == NoteBlockInstrument.BASEDRUM ) {
                String stoneName = path.replace("polished_", ""); // get stoneName from namespace:polished_stoneName
                ResourceLocation idBlockType = baseRes.withPath(stoneName);

                // Check if a BlockType is already added
                if ( Objects.isNull(get(idBlockType)) ) {
                    var opt = BuiltInRegistries.BLOCK.getOptional(idBlockType);
                    if (opt.isPresent()) return Optional.of(new StoneType(baseRes.withPath(stoneName), opt.get()));
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public void addTypeTranslations(AfterLanguageLoadEvent language) {
        this.getValues().forEach((stoneType) -> {
            if (language.isDefault()) language.addEntry(stoneType.getTranslationKey(), stoneType.getReadableName());
        });
    }

    @Override
    public int priority() {
        return 110;
    }
}
