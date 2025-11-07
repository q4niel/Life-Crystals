package dev.q4niel

import dev.q4niel.block.ModBlocks
import dev.q4niel.item.ModItems
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents
import net.minecraft.server.MinecraftServer
import net.minecraft.util.WorldSavePath
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
        ModBlocks.init();

        ServerLifecycleEvents.SERVER_STARTED.register {
            server: MinecraftServer
            ->

            _server = server;
            ModSave.init (
                _server?.getSavePath(WorldSavePath.ROOT).toString().dropLast(1),
                modID_
            );
        }
    }
}