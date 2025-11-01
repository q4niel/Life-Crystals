package dev.q4niel

import dev.q4niel.item.ModItems
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.networking.v1.PacketSender
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents
import net.minecraft.server.MinecraftServer
import net.minecraft.server.network.ServerPlayNetworkHandler
import org.slf4j.LoggerFactory

object LifeCrystals : ModInitializer {
    val modID_: String = "life_crystals";

    private val _logger_ = LoggerFactory.getLogger(modID_);
    fun print(string: String): Unit = _logger_.info(string);

    override fun onInitialize() {
        ModConfig.init();
        ModItems.init();

        ServerPlayConnectionEvents.JOIN.register {
            handler: ServerPlayNetworkHandler,
            sender: PacketSender,
            server: MinecraftServer
            ->

            PlayerPersistentData.initPlayerData(handler.player.uuid, handler.player);
        }
    }
}