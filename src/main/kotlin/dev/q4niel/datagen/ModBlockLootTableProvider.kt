package dev.q4niel.datagen

import dev.q4niel.block.ModBlocks
import dev.q4niel.item.ModItems
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider
import net.minecraft.loot.entry.ItemEntry
import net.minecraft.registry.RegistryWrapper
import java.util.concurrent.CompletableFuture

class ModBlockLootTableProvider (
    dataOutput: FabricDataOutput,
    registryLookup: CompletableFuture<RegistryWrapper.WrapperLookup>
) : FabricBlockLootTableProvider(dataOutput, registryLookup) {

    override fun generate() {
        addDrop (
            ModBlocks.lifeCrystalOre_,
            dropsWithSilkTouch (
                ModBlocks.lifeCrystalOre_,
                ItemEntry.builder(ModItems.lifeCrystal_)
            )
        );

        addDrop (
            ModBlocks.deepslateLifeCrystalOre_,
            dropsWithSilkTouch (
                ModBlocks.deepslateLifeCrystalOre_,
                ItemEntry.builder(ModItems.lifeCrystal_)
            )
        );
    }
}