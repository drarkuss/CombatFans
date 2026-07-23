package com.combatfans.effects;

import com.combatfans.config.CombatFansConfig;
import com.combatfans.items.weapons.AmethystKatanaItem;
import com.combatfans.items.weapons.AmethystOdachiItem;
import com.combatfans.items.weapons.CombatFanItem;
import com.combatfans.sounds.ModSounds;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;

/**
 * Dispara partícula e som de impacto quando o jogador acerta uma entidade
 * usando um leque, katana ou odachi (versão Forge/NeoForge legado 1.20.1).
 *
 * No Fabric usávamos {@code AttackEntityCallback} (Fabric API). O
 * equivalente direto no Forge/NeoForge legado é o {@link AttackEntityEvent},
 * disparado pelo bus geral do jogo ({@code MinecraftForge.EVENT_BUS}),
 * registrado em {@link com.combatfans.CombatFans}.
 *
 * IMPORTANTE: assim como no Fabric, isso é ouvir o evento, não substituir a
 * lógica de combo do Better Combat - por isso não chamamos
 * {@code event.setCanceled(true)} nem alteramos o resultado.
 */
public final class WeaponEffectsHandler {

    private WeaponEffectsHandler() {
    }

    @SubscribeEvent
    public static void onAttack(AttackEntityEvent event) {
        if (!CombatFansConfig.INSTANCE.particlesEnabled && !CombatFansConfig.INSTANCE.soundsEnabled) {
            return;
        }

        Player player = event.getEntity();
        if (!(player.level() instanceof ServerLevel serverLevel)) {
            // Só processa no servidor, para evitar duplicar efeitos entre
            // a predição do cliente e a autoridade do servidor.
            return;
        }

        ItemStack stack = player.getItemInHand(InteractionHand.MAIN_HAND);
        ParticleOptions particle;
        SoundEvent sound;
        int particleCount;

        if (stack.getItem() instanceof CombatFanItem fanItem) {
            particle = ModParticles.getFor(fanItem.getFanMaterial());
            sound = ModSounds.getFor(fanItem.getFanMaterial());
            particleCount = ModParticles.getCountFor(fanItem.getFanMaterial());
        } else if (stack.getItem() instanceof AmethystKatanaItem) {
            particle = ModParticles.KATANA_PARTICLE;
            sound = ModSounds.KATANA_SOUND;
            particleCount = ModParticles.KATANA_PARTICLE_COUNT;
        } else if (stack.getItem() instanceof AmethystOdachiItem) {
            particle = ModParticles.KATANA_PARTICLE;
            sound = ModSounds.KATANA_SOUND;
            particleCount = ModParticles.KATANA_PARTICLE_COUNT;
        } else {
            return;
        }

        Vec3 pos = event.getTarget().position().add(0, event.getTarget().getBbHeight() / 2.0, 0);

        if (CombatFansConfig.INSTANCE.particlesEnabled && particle != null) {
            serverLevel.sendParticles(particle, pos.x, pos.y, pos.z, particleCount, 0.3, 0.3, 0.3, 0.02);
        }
        if (CombatFansConfig.INSTANCE.soundsEnabled) {
            serverLevel.playSound(null, pos.x, pos.y, pos.z, sound, SoundSource.PLAYERS, 1.0f, 1.0f);
        }
    }
}
