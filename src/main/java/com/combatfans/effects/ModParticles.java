package com.combatfans.effects;

import com.combatfans.items.materials.FanMaterial;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import org.joml.Vector3f;

import java.util.EnumMap;
import java.util.Map;

/**
 * Mapeia cada {@link FanMaterial} para o efeito de partícula exibido no
 * momento do acerto (versão NeoForge/MojMap - lógica idêntica à do Fabric,
 * só o pacote de "ParticleTypes" mudou).
 */
public final class ModParticles {

    private ModParticles() {
    }

    /** Cor dourada usada na partícula de poeira do Leque de Ouro. */
    private static final ParticleOptions GOLD_DUST =
            new DustParticleOptions(new Vector3f(1.0f, 0.82f, 0.1f), 1.6f);

    private static final Map<FanMaterial, ParticleOptions> FAN_PARTICLES = new EnumMap<>(FanMaterial.class);

    static {
        // PAPER: de propósito, SEM entrada no mapa -> sem partícula.
        FAN_PARTICLES.put(FanMaterial.STONE, ParticleTypes.CLOUD);
        FAN_PARTICLES.put(FanMaterial.IRON, ParticleTypes.ELECTRIC_SPARK);
        FAN_PARTICLES.put(FanMaterial.GOLD, GOLD_DUST);
        FAN_PARTICLES.put(FanMaterial.AMETHYST, ParticleTypes.END_ROD);
        FAN_PARTICLES.put(FanMaterial.DIAMOND, ParticleTypes.ENCHANT);
        FAN_PARTICLES.put(FanMaterial.NETHERITE, ParticleTypes.ASH);
        FAN_PARTICLES.put(FanMaterial.LILITH, ParticleTypes.SOUL);
    }

    private static final Map<FanMaterial, Integer> PARTICLE_COUNT = new EnumMap<>(FanMaterial.class);

    static {
        PARTICLE_COUNT.put(FanMaterial.NETHERITE, 45);
    }

    private static final int DEFAULT_PARTICLE_COUNT = 20;

    /** Partícula usada pela Katana e Odachi de Ametista - igual à do Leque de Ametista. */
    public static final ParticleOptions KATANA_PARTICLE = FAN_PARTICLES.get(FanMaterial.AMETHYST);

    public static ParticleOptions getFor(FanMaterial material) {
        return FAN_PARTICLES.get(material);
    }

    public static int getCountFor(FanMaterial material) {
        return PARTICLE_COUNT.getOrDefault(material, DEFAULT_PARTICLE_COUNT);
    }

    public static final int KATANA_PARTICLE_COUNT = DEFAULT_PARTICLE_COUNT;
}
