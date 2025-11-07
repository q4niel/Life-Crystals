package dev.q4niel

import dev.q4niel.datagen.*
import dev.q4niel.datagen.worldgen.*
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator
import net.minecraft.registry.RegistryBuilder
import net.minecraft.registry.RegistryKeys

object LifeCrystalsDataGenerator : DataGeneratorEntrypoint {
	override fun onInitializeDataGenerator(fabricDataGenerator: FabricDataGenerator) {
        val pack_: FabricDataGenerator.Pack = fabricDataGenerator.createPack();

        pack_.addProvider(::ModBlockTagProvider);
        pack_.addProvider(::ModEnglishLangProvider);
        pack_.addProvider(::ModBlockLootTableProvider);
        pack_.addProvider(::ModModelProvider);
        pack_.addProvider(::ModRegistryProvider);
    }

    override fun buildRegistry(builder: RegistryBuilder) {
        builder.addRegistry(RegistryKeys.CONFIGURED_FEATURE, ModConfiguredFeatures::bootstrap);
        builder.addRegistry(RegistryKeys.PLACED_FEATURE, ModPlacedFeatures::bootstrap);
    }
}