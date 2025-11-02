package dev.q4niel.mixin;

import dev.q4niel.LifeCrystals;
import dev.q4niel.ModConfig;
import dev.q4niel.mixin_interfaces.IEntityMixin;
import net.minecraft.entity.Entity;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public class EntityMixin implements IEntityMixin {
    private int _playerMaxHealth = (int)ModConfig.INSTANCE.get().getDefaultPlayerHealth();
    private String _key = LifeCrystals.INSTANCE.getModID_() + ":player_max_hp";

    @Override
    public int getPlayerMaxHealth() {
        return _playerMaxHealth;
    }

    @Override
    public void setPlayerMaxHealth(int value) {
        _playerMaxHealth = value;
    }

    @Inject (
        method = "writeData(Lnet/minecraft/storage/WriteView;)V",
        at = @At("HEAD")
    )
    public void writeData(WriteView view, CallbackInfo cbi) {
        view.putInt(_key, _playerMaxHealth);
    }

    @Inject (
        method = "readData(Lnet/minecraft/storage/ReadView;)V",
        at = @At("HEAD")
    )
    public void readData(ReadView view, CallbackInfo cbi) {
        _playerMaxHealth = view.getInt(_key, 0);
    }
}