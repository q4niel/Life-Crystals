package dev.q4niel.item

import dev.q4niel.ModConfig
import dev.q4niel.ModSave
import dev.q4niel.mixin_interfaces.IServerPlayerEntityMixin
import net.minecraft.entity.LivingEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.sound.SoundCategory
import net.minecraft.sound.SoundEvents
import net.minecraft.world.World

class LifeCrystalItem(settings: Settings) : Item(settings) {
    override fun finishUsing(stack: ItemStack, world: World, user: LivingEntity): ItemStack {
        if (user is ServerPlayerEntity) {
            if (!user.abilities.creativeMode) {
                stack.decrement(1);
                (user as IServerPlayerEntityMixin).setMaxHealth (
                    ModSave.getPlayerMaxHealth(user.uuid) +
                            ModConfig.get().lifeCrystalHealth.toInt()
                );
            }

            world.playSound (
                null,
                user.blockPos,
                SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP,
                SoundCategory.PLAYERS,
                0.25f,
                1.0f
            );
        }

        return stack;
    }
}