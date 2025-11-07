package dev.q4niel.datagen.worldgen

import dev.q4niel.LifeCrystals
import dev.q4niel.block.ModBlocks
import net.minecraft.registry.Registerable
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.entry.RegistryEntry
import net.minecraft.registry.tag.BlockTags
import net.minecraft.structure.rule.RuleTest
import net.minecraft.structure.rule.TagMatchRuleTest
import net.minecraft.util.Identifier
import net.minecraft.world.gen.feature.ConfiguredFeature
import net.minecraft.world.gen.feature.Feature
import net.minecraft.world.gen.feature.FeatureConfig
import net.minecraft.world.gen.feature.OreFeatureConfig

object ModConfiguredFeatures {
    val lifeCrystalOreKey_: RegistryKey<ConfiguredFeature<*, *>> = _registerKey("life_crystal_ore");

    fun bootstrap(context: Registerable<ConfiguredFeature<*, *>>): Unit {
        val stoneRule: RuleTest = TagMatchRuleTest(BlockTags.STONE_ORE_REPLACEABLES);
        val deepslateRule: RuleTest = TagMatchRuleTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);

        val overworldLifeCrystalOres: List<OreFeatureConfig.Target> = listOf (
            OreFeatureConfig.createTarget(stoneRule, ModBlocks.lifeCrystalOre_.defaultState),
            OreFeatureConfig.createTarget(deepslateRule, ModBlocks.deepslateLifeCrystalOre_.defaultState)
        );

        _register (
            context,
            lifeCrystalOreKey_,
            Feature.ORE,
            OreFeatureConfig(overworldLifeCrystalOres, 3)
        );
    }

    private fun _registerKey(name: String): RegistryKey<ConfiguredFeature<*, *>> = RegistryKey.of (
        RegistryKeys.CONFIGURED_FEATURE,
        Identifier.of(LifeCrystals.modID_, name)
    );

    private fun <FC: FeatureConfig, F: Feature<FC>> _register (
        context: Registerable<ConfiguredFeature<*, *>>,
        key: RegistryKey<ConfiguredFeature<*, *>>,
        feature: F,
        configuration: FC
    ): RegistryEntry.Reference<ConfiguredFeature<*, *>> = context.register (
        key,
        ConfiguredFeature<FC, F>(feature, configuration)
    );
}