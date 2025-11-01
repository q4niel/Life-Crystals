package dev.q4niel

import com.moandjiezana.toml.Toml
import java.io.File

data class ModConfigFile (
    val defaultPlayerHealth: Long = 6,
    val lifeCrystalHealth: Long = 2,
    val deathHealthPenalty: Long = 2,
    val maxPlayerHealth: Long = 20,
    val minPlayerHealth: Long = 6
)

object ModConfig {
    var _config: ModConfigFile = ModConfigFile();
    val _cfgFile: File = File("config/${LifeCrystals.modID_}.toml");

    fun get(): ModConfigFile = _config;

    public fun init() {
        if (!_cfgFile.exists()) return;

        val toml = Toml().read(_cfgFile);
        _config = ModConfigFile (
            toml.getLong("default_player_health", _config.defaultPlayerHealth),
            toml.getLong("life_crystal_health", _config.lifeCrystalHealth),
            toml.getLong("death_health_penalty", _config.deathHealthPenalty),
            toml.getLong("max_player_health", _config.maxPlayerHealth),
            toml.getLong("min_player_health", _config.minPlayerHealth)
        )
    }
}