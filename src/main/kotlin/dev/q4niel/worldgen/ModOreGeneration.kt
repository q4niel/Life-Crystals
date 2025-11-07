package dev.q4niel.worldgen

import dev.q4niel.datagen.worldgen.ModPlacedFeatures
import net.fabricmc.fabric.api.biome.v1.BiomeModifications
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors
import net.minecraft.world.gen.GenerationStep

object ModOreGeneration {
    fun generateOres(): Unit {
        BiomeModifications.addFeature (
            BiomeSelectors.foundInOverworld(),
            GenerationStep.Feature.UNDERGROUND_ORES,
            ModPlacedFeatures.lifeCrystalOrePlacedKey_
        );
    }
}