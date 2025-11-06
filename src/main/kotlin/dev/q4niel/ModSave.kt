package dev.q4niel

import java.io.File
import java.util.UUID

object ModSave {
    private var _saveFilePath: String? = null;

    fun init(savePath: String, modID: String) {
        val saveDir: File = File(savePath + modID);
        if (!saveDir.exists()) {
            saveDir.mkdir();
        }

        _saveFilePath = "$saveDir/data";
        if (!File(_saveFilePath!!).exists()) {
            File(_saveFilePath!!).createNewFile();
        }
    }

    fun getPlayerMaxHealth(playerID: UUID): Int {
        for (line in File(_saveFilePath!!).readText().lines()) {
            if ("\"$playerID\"" == line.toString().substringBefore("=")) {
                return line.toString().substringAfter("=").toInt();
            }
        }
        return ModConfig.get().defaultPlayerHealth;
    }

    fun savePlayerMaxHealth(playerID: UUID, value: Int): Unit {
        val saveText: String = File(_saveFilePath!!).readText();
        var newText: String = "";

        for (line in saveText.lines()) {
            if (line.contains(playerID.toString())) continue;
            newText += "\n$line";
        }
        newText += "\n\"$playerID\"=$value";

        File(_saveFilePath!!).writeText(newText.trimStart('\n'));
    }
}