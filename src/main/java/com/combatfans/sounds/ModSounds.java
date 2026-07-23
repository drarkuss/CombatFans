package com.combatfans.sounds;

import com.combatfans.items.materials.FanMaterial;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;

import java.util.EnumMap;
import java.util.Map;

/**
 * Mapeia cada {@link FanMaterial} para o som de impacto reproduzido no
 * momento do acerto (versão NeoForge/MojMap).
 */
public final class ModSounds {

    private ModSounds() {
    }

    private static final Map<FanMaterial, SoundEvent> FAN_SOUNDS = new EnumMap<>(FanMaterial.class);

    static {
        FAN_SOUNDS.put(FanMaterial.PAPER, SoundEvents.BOOK_PAGE_TURN);
        FAN_SOUNDS.put(FanMaterial.STONE, SoundEvents.BASALT_BREAK);
        FAN_SOUNDS.put(FanMaterial.IRON, SoundEvents.CHAIN_HIT);
        FAN_SOUNDS.put(FanMaterial.GOLD, SoundEvents.ARMOR_EQUIP_GOLD);
        FAN_SOUNDS.put(FanMaterial.AMETHYST, SoundEvents.AMETHYST_BLOCK_HIT);
        FAN_SOUNDS.put(FanMaterial.DIAMOND, SoundEvents.CALCITE_BREAK);
        FAN_SOUNDS.put(FanMaterial.NETHERITE, SoundEvents.WARDEN_ATTACK_IMPACT);
        FAN_SOUNDS.put(FanMaterial.LILITH, SoundEvents.PLAYER_HURT_FREEZE);
    }

    /** Som usado pela Katana e Odachi de Ametista - igual ao do Leque de Ametista. */
    public static final SoundEvent KATANA_SOUND = FAN_SOUNDS.get(FanMaterial.AMETHYST);

    public static SoundEvent getFor(FanMaterial material) {
        return FAN_SOUNDS.getOrDefault(material, SoundEvents.TRIDENT_HIT);
    }
}
