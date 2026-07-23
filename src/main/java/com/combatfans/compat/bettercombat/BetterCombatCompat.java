package com.combatfans.compat.bettercombat;

import com.combatfans.CombatFans;
import com.combatfans.config.CombatFansConfig;
import net.minecraftforge.fml.ModList;

/**
 * Integração com o Better Combat (versão Forge/NeoForge legado 1.20.1).
 *
 * Assim como na versão Fabric, a integração REAL não usa nenhuma API Java —
 * é feita via arquivos de dados em
 * {@code src/main/resources/data/combatfans/weapon_attributes/}, que são
 * 100% compartilhados entre Fabric e Forge/NeoForge (sistema de datapack do
 * próprio Minecraft, independente do mod loader).
 *
 * Esta classe só loga, na inicialização, se o Better Combat foi detectado.
 */
public final class BetterCombatCompat {

    private BetterCombatCompat() {
    }

    public static void onInitialize() {
        if (!CombatFansConfig.INSTANCE.betterCombatIntegrationEnabled) {
            CombatFans.LOGGER.info("Integração com Better Combat desativada via config.");
            return;
        }

        boolean betterCombatPresent = ModList.get().isLoaded("bettercombat");
        if (betterCombatPresent) {
            CombatFans.LOGGER.info(
                    "Better Combat detectado — armas usarão os presets definidos em " +
                    "data/combatfans/weapon_attributes/.");
        } else {
            CombatFans.LOGGER.info("Better Combat não encontrado — leques funcionarão como espadas vanilla.");
        }
    }
}
