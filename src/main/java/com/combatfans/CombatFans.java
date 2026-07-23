package com.combatfans;

import com.combatfans.compat.bettercombat.BetterCombatCompat;
import com.combatfans.config.CombatFansConfig;
import com.combatfans.effects.WeaponEffectsHandler;
import com.combatfans.registry.ModItems;
import com.combatfans.util.CombatFansConstants;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.common.MinecraftForge;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Ponto de entrada principal do mod (versão NeoForge/Forge legado 1.20.1).
 *
 * No Fabric, isso era uma classe implementando {@code ModInitializer}, com
 * um método {@code onInitialize()} chamado pelo Loader. No Forge/NeoForge
 * legado (1.20.1), o padrão é uma classe anotada com {@code @Mod}, cujo
 * bus de eventos do mod é obtido via {@link FMLJavaModLoadingContext}
 * (diferente das versões mais novas, que injetam o {@link IEventBus}
 * direto no construtor).
 */
@Mod(CombatFansConstants.MOD_ID)
public class CombatFans {

    public static final Logger LOGGER = LoggerFactory.getLogger(CombatFansConstants.MOD_ID);

    public CombatFans() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        LOGGER.info("Inicializando Combat Fans (NeoForge/Forge legado)...");
        CombatFansConfig.load();

        // Registra o DeferredRegister de itens no bus do MOD (não no bus
        // geral do jogo) - é assim que o Forge/NeoForge sabe quando de fato
        // registrar os itens no momento certo do ciclo de vida do mod.
        ModItems.ITEMS.register(modEventBus);

        BetterCombatCompat.onInitialize();

        // Eventos de gameplay (como ataque) vão no bus GERAL do Forge,
        // não no bus do mod - por isso registramos esta classe separada lá.
        MinecraftForge.EVENT_BUS.register(WeaponEffectsHandler.class);

        modEventBus.addListener(this::addCreative);

        LOGGER.info("Combat Fans inicializado com {} leques registrados.",
                ModItems.getAllFans().size());
    }

    /**
     * Adiciona todos os itens do mod na aba de combate criativa vanilla.
     * Equivalente ao {@code ItemGroupEvents.modifyEntriesEvent(...)} que
     * usávamos no Fabric.
     */
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
    if (event.getTabKey() == CreativeModeTabs.COMBAT) {
        ModItems.getAllFans().values().forEach(event::accept);
        event.accept(ModItems.AMETHYST_KATANA);
        event.accept(ModItems.AMETHYST_ODACHI);
    }
}
}
