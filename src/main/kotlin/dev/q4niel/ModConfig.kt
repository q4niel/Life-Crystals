package dev.q4niel

import com.moandjiezana.toml.Toml
import java.io.File

data class ModConfigFile (
    val defaultPlayerHealth: Int = 6,
    val lifeCrystalHealth: Int = 2,
    val deathHealthPenalty: Int = 2,
    val maxPlayerHealth: Int = 20,
    val minPlayerHealth: Int = 6
)

object ModConfig {
    var _config: ModConfigFile = ModConfigFile();
    val _cfgFile: File = File("config/${LifeCrystals.modID_}.toml");

    fun get(): ModConfigFile = _config;

    public fun init() {
        if (!_cfgFile.exists()) return;

        val toml = Toml().read(_cfgFile);
        _config = ModConfigFile (
            toml.getLong("default_player_health", _config.defaultPlayerHealth.toLong()).toInt(),
            toml.getLong("life_crystal_health", _config.lifeCrystalHealth.toLong()).toInt(),
            toml.getLong("death_health_penalty", _config.deathHealthPenalty.toLong()).toInt(),
            toml.getLong("max_player_health", _config.maxPlayerHealth.toLong()).toInt(),
            toml.getLong("min_player_health", _config.minPlayerHealth.toLong()).toInt()
        )
    }
}