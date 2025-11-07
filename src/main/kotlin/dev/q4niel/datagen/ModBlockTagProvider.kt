package dev.q4niel.datagen

import dev.q4niel.block.ModBlocks
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider
import net.minecraft.registry.RegistryWrapper
import net.minecraft.registry.tag.BlockTags
import java.util.concurrent.CompletableFuture

class ModBlockTagProvider (
    output: FabricDataOutput,
    registriesFuture: CompletableFuture<RegistryWrapper.WrapperLookup>
) : FabricTagProvider.BlockTagProvider(output, registriesFuture) {

    override fun configure(wrapperLookup: RegistryWrapper.WrapperLookup) {
        valueLookupBuilder(BlockTags.PICKAXE_MINEABLE)
            .add(ModBlocks.lifeCrystalOre_)
        ;

        valueLookupBuilder(BlockTags.INCORRECT_FOR_WOODEN_TOOL)
            .add(ModBlocks.lifeCrystalOre_)
        ;

        valueLookupBuilder(BlockTags.INCORRECT_FOR_STONE_TOOL)
            .add(ModBlocks.lifeCrystalOre_)
        ;
    }
}