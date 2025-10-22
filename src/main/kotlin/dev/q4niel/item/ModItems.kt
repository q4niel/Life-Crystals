package dev.q4niel.item

import dev.q4niel.LifeCrystals
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents
import net.minecraft.item.Item
import net.minecraft.item.ItemGroups
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.util.Identifier
import net.minecraft.util.Rarity
import java.util.function.Function

object ModItems {
    val lifeCrystal_: Item = _register (
        "life_crystal",
        ::LifeCrystalItem,
        Item.Settings().maxCount(16).rarity(Rarity.EPIC).food(ModFoodComponents.lifeCrystal_)
    );

    fun init() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register{ group ->
            group.add(ModItems.lifeCrystal_)
        }
    }

    private fun _register (
        id: String,
        factory: Function<Item.Settings, Item>,
        settings: Item.Settings
    ): Item {
        val key: RegistryKey<Item> = RegistryKey.of (
            RegistryKeys.ITEM,
            Identifier.of(LifeCrystals.modID_, id)
        );

        val item: Item = factory.apply(settings.registryKey(key));

        return Registry.register(Registries.ITEM, key, item);
    }
}