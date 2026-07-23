package com.combatfans.items.materials;

import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;

/**
 * Ponte entre {@link FanMaterial} e o {@link Tier} vanilla equivalente.
 *
 * Em mapeamentos MojMap (NeoForge), o que no Yarn/Fabric se chama
 * "ToolMaterial" se chama "Tier", e o enum com os materiais vanilla prontos
 * (Wood/Stone/Iron/Gold/Diamond/Netherite) se chama "Tiers" (no plural).
 */
public final class CombatFanToolMaterials {

    private CombatFanToolMaterials() {
    }

    public static Tier from(FanMaterial fanMaterial) {
        return switch (fanMaterial) {
            case PAPER -> Tiers.WOOD;
            case STONE -> Tiers.STONE;
            case IRON -> Tiers.IRON;
            case GOLD -> Tiers.GOLD;
            case AMETHYST -> Tiers.DIAMOND;
            case DIAMOND -> Tiers.DIAMOND;
            case NETHERITE, LILITH -> Tiers.NETHERITE;
        };
    }
}
