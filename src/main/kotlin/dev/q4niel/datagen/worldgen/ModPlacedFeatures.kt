package dev.q4niel.datagen.worldgen

import dev.q4niel.LifeCrystals
import net.minecraft.registry.Registerable
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.entry.RegistryEntry
import net.minecraft.util.Identifier
import net.minecraft.world.gen.YOffset
import net.minecraft.world.gen.feature.ConfiguredFeature
import net.minecraft.world.gen.feature.PlacedFeature
import net.minecraft.world.gen.placementmodifier.HeightRangePlacementModifier
import net.minecraft.world.gen.placementmodifier.PlacementModifier
import java.util.List

object ModPlacedFeatures {
    val lifeCrystalOrePlacedKey_: RegistryKey<PlacedFeature> = _registerKey("life_crystal_ore_placed");

    fun bootstrap(context: Registerable<PlacedFeature>): Unit {
        val configFeats_ = context.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);

        _register (
            context,
            lifeCrystalOrePlacedKey_,
            configFeats_.getOrThrow(ModConfiguredFeatures.lifeCrystalOreKey_),
            ModOrePlacement.modifiersWithCount (
                40,
                HeightRangePlacementModifier.uniform (
                    YOffset.fixed(-128),
                    YOffset.fixed(256)
                )
            ) as MutableList<PlacementModifier>
        );
    }

    private fun _registerKey(name: String): RegistryKey<PlacedFeature> = RegistryKey.of (
        RegistryKeys.PLACED_FEATURE,
        Identifier.of(LifeCrystals.modID_, name)
    );

    private fun _register (
        context: Registerable<PlacedFeature>,
        key: RegistryKey<PlacedFeature>,
        configuration: RegistryEntry<ConfiguredFeature<*, *>>,
        modifiers: MutableList<PlacementModifier>
    ): Unit {
        context.register (
            key,
            PlacedFeature(
                configuration,
                List.copyOf<PlacementModifier>(modifiers)
            )
        );
    }
}