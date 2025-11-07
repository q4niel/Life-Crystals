package dev.q4niel.datagen.worldgen

import net.minecraft.world.gen.placementmodifier.*

object ModOrePlacement {
    fun modifiers (
        countMod: PlacementModifier,
        heightMod: PlacementModifier
    ): List<PlacementModifier> = listOf (
        countMod,
        SquarePlacementModifier.of(),
        heightMod,
        BiomePlacementModifier.of()
    );

    fun modifiersWithCount (
        count: Int,
        heightMod: PlacementModifier
    ): List<PlacementModifier> = modifiers (
        CountPlacementModifier.of(count),
        heightMod
    )

    fun modifiersWithRarity (
        chance: Int,
        heightMod: PlacementModifier
    ): List<PlacementModifier> = modifiers (
        RarityFilterPlacementModifier.of(chance),
        heightMod
    );
}