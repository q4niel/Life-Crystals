package dev.q4niel.block

import dev.q4niel.LifeCrystals
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents
import net.minecraft.block.AbstractBlock
import net.minecraft.block.Block
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.item.ItemGroups
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.sound.BlockSoundGroup
import net.minecraft.util.Identifier
import net.minecraft.util.Rarity
import java.util.function.Function

object ModBlocks {
    val lifeCrystalOre_: Block = _register (
        "life_crystal_ore",
        Rarity.EPIC,
        ::Block,
        AbstractBlock.Settings.create()
            .sounds(BlockSoundGroup.AMETHYST_BLOCK)
            .requiresTool()
            .strength(4.0f)
    );

    val deepslateLifeCrystalOre_: Block = _register (
        "deepslate_life_crystal_ore",
        Rarity.EPIC,
        ::Block,
        AbstractBlock.Settings.create()
            .sounds(BlockSoundGroup.AMETHYST_CLUSTER)
            .requiresTool()
            .strength(8.0f)
    );

    fun init(): Unit {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register {
            itemGroup: FabricItemGroupEntries
            ->

            itemGroup.add(lifeCrystalOre_.asItem());
        };
    }

    private fun _register (
        name: String,
        rarity: Rarity?,
        factory: Function<AbstractBlock.Settings, Block>,
        settings: AbstractBlock.Settings
    ): Block {
        val blockKey: RegistryKey<Block> = _blockKey(name);
        val block: Block = factory.apply(settings.registryKey(blockKey));

        if (rarity != null) {
            val itemKey: RegistryKey<Item> = _itemKey(name);

            val item: BlockItem = BlockItem (
                block,
                Item
                    .Settings()
                    .registryKey(itemKey)
                    .useBlockPrefixedTranslationKey()
                    .rarity(rarity)
            );

            Registry.register (
                Registries.ITEM,
                itemKey,
                item
            );
        }

        return Registry.register (
            Registries.BLOCK,
            blockKey,
            block
        );
    }

    private fun _blockKey(name: String): RegistryKey<Block> = RegistryKey.of (
        RegistryKeys.BLOCK,
        Identifier.of(LifeCrystals.modID_, name)
    );

    private fun _itemKey(name: String): RegistryKey<Item> = RegistryKey.of (
        RegistryKeys.ITEM,
        Identifier.of(LifeCrystals.modID_, name)
    );
}