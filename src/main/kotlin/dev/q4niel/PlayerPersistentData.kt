package dev.q4niel

import dev.q4niel.mixin_interfaces.IEntityMixin
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.server.network.ServerPlayerEntity
import java.util.UUID

object PlayerPersistentData {
    private var _data: MutableMap<UUID, Int> = mutableMapOf();

    fun initPlayerData(player: ServerPlayerEntity): Unit? = LifeCrystals.serverExec {
        _data[player.uuid] = (player as IEntityMixin).playerMaxHealth;
        setMaxHealth(player, _data[player.uuid]!!);
    }

    fun setMaxHealth(player: ServerPlayerEntity, value: Int): Unit? = LifeCrystals.serverExec {
        _data[player.uuid] = value;
        (player as IEntityMixin).playerMaxHealth = value;
        player.getAttributeInstance(EntityAttributes.MAX_HEALTH)?.baseValue = value.toDouble();
    }

    fun incrementMaxHealth(player: ServerPlayerEntity, amount: Int = 1): Unit? = LifeCrystals.serverExec {
        setMaxHealth(
            player,
            _data[player.uuid]!! + amount
        );
    }

    fun decrementMaxHealth(player: ServerPlayerEntity, amount: Int = 1): Unit? = LifeCrystals.serverExec {
        setMaxHealth (
            player,
            _data[player.uuid]!! - amount
        );
    }
}