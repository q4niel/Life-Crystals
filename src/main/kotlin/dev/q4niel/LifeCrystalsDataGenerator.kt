package dev.q4niel

import dev.q4niel.datagen.ModEnglishLangProvider
import dev.q4niel.datagen.ModModelProvider
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator

object LifeCrystalsDataGenerator : DataGeneratorEntrypoint {
	override fun onInitializeDataGenerator(fabricDataGenerator: FabricDataGenerator) {
        val pack_: FabricDataGenerator.Pack = fabricDataGenerator.createPack();
        pack_.addProvider(::ModEnglishLangProvider);
        pack_.addProvider(::ModModelProvider);
    }
}