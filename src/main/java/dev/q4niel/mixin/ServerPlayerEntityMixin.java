package dev.q4niel.mixin;

import dev.q4niel.ModConfig;
import dev.q4niel.ModSave;
import dev.q4niel.mixin_interfaces.IServerPlayerEntityMixin;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayerEntity.class)
public class ServerPlayerEntityMixin implements IServerPlayerEntityMixin {
    private final ServerPlayerEntity self = (ServerPlayerEntity)(Object)this;

    @Inject (
        method = "onSpawn()V",
        at = @At("HEAD")
    )
    public void onSpawn(CallbackInfo ci) {
        setMaxHealth(ModSave.INSTANCE.getPlayerMaxHealth(self.getUuid()));
    }

    @Inject (
        method = "onDeath(Lnet/minecraft/entity/damage/DamageSource;)V",
        at = @At("HEAD")
    )
    public void onDeath(DamageSource damageSource, CallbackInfo ci) {
        setMaxHealth (
                ModSave.INSTANCE.getPlayerMaxHealth(self.getUuid()) -
                (int)ModConfig.INSTANCE.get().getDeathHealthPenalty()
        );
    }

    @Override
    public void setMaxHealth(int value) {
        int min = (int)ModConfig.INSTANCE.get().getMinPlayerHealth();
        int max = (int)ModConfig.INSTANCE.get().getMaxPlayerHealth();

        if (value <= min) value = min;
        else if (value >= max) value = max;

        self.getAttributeInstance(EntityAttributes.MAX_HEALTH).setBaseValue(value);
        ModSave.INSTANCE.savePlayerMaxHealth(self.getUuid(), value);
    }
}