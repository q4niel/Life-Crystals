package dev.q4niel

import com.mojang.serialization.Codec
import dev.q4niel.LifeCrystals.modID_
import net.fabricmc.fabric.api.attachment.v1.AttachmentRegistry
import net.fabricmc.fabric.api.attachment.v1.AttachmentSyncPredicate
import net.fabricmc.fabric.api.attachment.v1.AttachmentType
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.network.codec.PacketCodecs
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.util.Identifier
import java.util.UUID

object PlayerPersistentData {
    data class PlayerData (
        var player: ServerPlayerEntity,
        var maxHealth: Int
    );

    private var _dataMap: MutableMap<UUID, PlayerData> = mutableMapOf();

    private val _maxHealthAttachment_: AttachmentType<Int> = AttachmentRegistry.create (
        Identifier.of(modID_, "player_max_health"),
        { builder -> builder
            .initializer{6}
            .persistent(Codec.INT)
            .syncWith(PacketCodecs.VAR_INT, AttachmentSyncPredicate.targetOnly())
        }
    );

    fun initPlayerData(playerID: UUID, player: ServerPlayerEntity): Unit {
        _dataMap[playerID] = PlayerData(player, 6);

        _dataMap[playerID]!!.maxHealth = _dataMap[playerID]!!.player.getAttachedOrCreate(_maxHealthAttachment_);
        setMaxHealth(playerID, _dataMap[playerID]!!.maxHealth)
    }

    fun setMaxHealth(playerID: UUID, value: Int): Unit {
        _dataMap[playerID]!!.maxHealth = value;

        _dataMap[playerID]!!.player.setAttached (
            _maxHealthAttachment_,
            _dataMap[playerID]!!.maxHealth
        );

        _dataMap[playerID]!!.player.getAttributeInstance(EntityAttributes.MAX_HEALTH)?.baseValue =
            _dataMap[playerID]!!.player.getAttached(_maxHealthAttachment_)!!.toDouble();
    }

    fun incrementMaxHealth(playerID: UUID, amount: Int = 1): Unit = setMaxHealth (
        playerID,
        _dataMap[playerID]!!.maxHealth + amount
    );

    fun decrementMaxHealth(playerID: UUID, amount: Int = 1): Unit = setMaxHealth (
        playerID,
        _dataMap[playerID]!!.maxHealth - amount
    );
}