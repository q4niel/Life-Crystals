package dev.q4niel.datagen

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider
import net.minecraft.registry.RegistryWrapper
import java.util.concurrent.CompletableFuture

class ModEnglishLangProvider (
    dataOutput: FabricDataOutput,
    registryLookup: CompletableFuture<RegistryWrapper.WrapperLookup>
) : FabricLanguageProvider (
    dataOutput,
    "en_us",
    registryLookup
) {

    override fun generateTranslations(
        wrapper: RegistryWrapper.WrapperLookup,
        builder: TranslationBuilder
    ) {
        builder.add("item.life_crystals.life_crystal", "Life Crystal");
        builder.add("block.life_crystals.life_crystal_ore", "Life Crystal Ore");
        builder.add("block.life_crystals.deepslate_life_crystal_ore", "Deepslate Life Crystal Ore");
    }
}