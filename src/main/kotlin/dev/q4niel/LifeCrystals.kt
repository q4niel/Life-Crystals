package dev.q4niel

import dev.q4niel.item.ModItems
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents
import net.fabricmc.fabric.api.networking.v1.PacketSender
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.damage.DamageSource
import net.minecraft.server.MinecraftServer
import net.minecraft.server.network.ServerPlayNetworkHandler
import net.minecraft.server.network.ServerPlayerEntity
import org.slf4j.LoggerFactory

object LifeCrystals : ModInitializer {
    val modID_: String = "life_crystals";

    private val _logger_ = LoggerFactory.getLogger(modID_);
    fun print(string: String): Unit = _logger_.info(string);

    private var _server: MinecraftServer? = null;
    fun serverExec(runnable: Runnable): Unit? = _server?.execute(runnable);

    override fun onInitialize() {
        ModConfig.init();
        ModItems.init();

        ServerLifecycleEvents.SERVER_STARTED.register {
            server: MinecraftServer
            ->

            _server = server;
        }

        ServerPlayConnectionEvents.JOIN.register {
            handler: ServerPlayNetworkHandler,
            sender: PacketSender,
            server: MinecraftServer
            ->

            PlayerPersistentData.initPlayerData(handler.player);
        }

        ServerLivingEntityEvents.AFTER_DEATH.register {
            entity: LivingEntity,
            dmgSrc: DamageSource
            ->

            if (entity is ServerPlayerEntity) {
                PlayerPersistentData.initPlayerData(entity);

                PlayerPersistentData.decrementMaxHealth (
                    entity,
                    ModConfig.get().deathHealthPenalty.toInt()
                );
            }
        }
    }
}