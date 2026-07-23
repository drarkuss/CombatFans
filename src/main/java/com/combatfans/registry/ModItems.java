package com.combatfans.registry;

import com.combatfans.items.materials.CombatFanToolMaterials;
import com.combatfans.items.materials.FanMaterial;
import com.combatfans.items.weapons.AmethystKatanaItem;
import com.combatfans.items.weapons.AmethystOdachiItem;
import com.combatfans.items.weapons.CombatFanItem;
import com.combatfans.util.CombatFansConstants;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * Registro centralizado de todos os itens do mod (versão Forge/NeoForge legado 1.20.1).
 *
 * Forge/NeoForge legado usa {@link DeferredRegister} em vez do
 * {@code Registry.register(...)} direto que usávamos no Fabric — a lógica é
 * a mesma (evita registrar coisa antes da hora), só a API muda. Nesta versão
 * legada, o tipo retornado é {@link RegistryObject}, não {@code DeferredHolder}
 * (que só existe nas versões mais novas do NeoForge).
 */
public final class ModItems {

    private ModItems() {
    }

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, CombatFansConstants.MOD_ID);

    private static final Map<FanMaterial, RegistryObject<CombatFanItem>> FAN_HOLDERS = new LinkedHashMap<>();

    public static final RegistryObject<CombatFanItem> PAPER_FAN = registerFan(FanMaterial.PAPER);
    public static final RegistryObject<CombatFanItem> STONE_FAN = registerFan(FanMaterial.STONE);
    public static final RegistryObject<CombatFanItem> IRON_FAN = registerFan(FanMaterial.IRON);
    public static final RegistryObject<CombatFanItem> GOLD_FAN = registerFan(FanMaterial.GOLD);
    public static final RegistryObject<CombatFanItem> AMETHYST_FAN = registerFan(FanMaterial.AMETHYST);
    public static final RegistryObject<CombatFanItem> DIAMOND_FAN = registerFan(FanMaterial.DIAMOND);
    public static final RegistryObject<CombatFanItem> NETHERITE_FAN = registerFan(FanMaterial.NETHERITE);
    public static final RegistryObject<CombatFanItem> LILITH_FAN = registerFan(FanMaterial.LILITH);

    public static final RegistryObject<AmethystKatanaItem> AMETHYST_KATANA = ITEMS.register(
            "amethyst_katana",
            () -> new AmethystKatanaItem(
                    Tiers.DIAMOND,
                    2,             // attack damage (exibe 6 no jogo, igual ao Artifex)
                    1.5f - 4.0f,   // attack speed (exibe 1.5, igual ao Artifex)
                    new Item.Properties().rarity(Rarity.RARE).durability(1561)
            )
    );

    public static final RegistryObject<AmethystOdachiItem> AMETHYST_ODACHI = ITEMS.register(
            "amethyst_odachi",
            () -> new AmethystOdachiItem(
                    Tiers.DIAMOND,
                    4,             // attack damage (exibe 8 no jogo, aprox. de 8.5 do Artifex)
                    1.2f - 4.0f,   // attack speed (exibe 1.2, igual ao Artifex)
                    new Item.Properties().rarity(Rarity.RARE).durability(1561)
            )
    );

    private static RegistryObject<CombatFanItem> registerFan(FanMaterial material) {
        RegistryObject<CombatFanItem> holder = ITEMS.register(
                material.getName() + "_fan",
                () -> new CombatFanItem(
                        CombatFanToolMaterials.from(material),
                        material,
                        new Item.Properties().rarity(material.getRarity()).durability(material.getDurability())
                )
        );
        FAN_HOLDERS.put(material, holder);
        return holder;
    }

    public static Map<FanMaterial, RegistryObject<CombatFanItem>> getAllFans() {
        return FAN_HOLDERS;
    }
}
