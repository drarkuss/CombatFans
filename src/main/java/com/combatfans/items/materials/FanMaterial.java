package com.combatfans.items.materials;

import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Supplier;

/**
 * Define os atributos de cada material de Leque de Combate.
 *
 * Portado de Fabric para NeoForge - a lógica e os valores numéricos são os
 * mesmos definidos pelo usuário no projeto original; só os pacotes de
 * import mudaram (NeoForge usa os mapeamentos oficiais da Mojang, "MojMap",
 * em vez do Yarn do Fabric).
 *
 * IMPORTANTE sobre os números aqui: cada valor de "attackSpeed" é o número
 * "amigável" que deve aparecer no HUD/tooltip do jogo. A subtração de 4.0
 * (para virar o modificador real do atributo do Minecraft) acontece em
 * {@link com.combatfans.items.weapons.CombatFanItem}, não aqui.
 */
public enum FanMaterial {

    PAPER(
            "paper",
            45,     //Durabilidade
            1.5f,   // Dano
            2.5f,   // Velocidade de Ataque
            0.0f,   // Bonus de alcance
            0.5f,   // Knockback
            Rarity.COMMON,
            () -> Ingredient.of(Items.PAPER)
    ),
    STONE(
            "stone",
            100,
            3.0f,
            1.5f,
            0.0f,
            0.5f,
            Rarity.COMMON,
            () -> Ingredient.of(Items.COBBLESTONE)
    ),
    IRON(
            "iron",
            200,
            5.0f,
            2.5f,
            0.0f,
            0.5f,
            Rarity.COMMON,
            () -> Ingredient.of(Items.IRON_INGOT)
    ),
    GOLD(
            "gold",
            65,
            2.5f,
            2.5f,
            0.0f,
            0.5f,
            Rarity.UNCOMMON,
            () -> Ingredient.of(Items.GOLD_INGOT)
    ),
    AMETHYST(
            "amethyst",
            900,
            6.0f,
            2.5f,
            0.0f,
            0.5f,
            Rarity.RARE,
            () -> Ingredient.of(Items.AMETHYST_SHARD)
    ),
    DIAMOND(
            "diamond",
            1450,
            7.0f,
            2.75f,
            0.0f,
            0.5f,
            Rarity.RARE,
            () -> Ingredient.of(Items.DIAMOND)
    ),
    NETHERITE(
            "netherite",
            1950,
            8.5f,
            2.75f,
            0.0f,
            0.5f,
            Rarity.EPIC,
            () -> Ingredient.of(Items.NETHERITE_INGOT)
    ),
    LILITH(
            "lilith",
            2700,
            17.0f,
            3.0f,
            0.0f,
            0.5f,
            Rarity.EPIC,
            () -> Ingredient.of(Items.BLUE_ICE)
    );

    private final String name;
    private final int durability;
    private final float attackDamage;
    private final float attackSpeed;
    private final float attackReachBonus;
    private final float knockback;
    private final Rarity rarity;
    private final Supplier<Ingredient> repairIngredient;

    FanMaterial(String name, int durability, float attackDamage, float attackSpeed,
                float attackReachBonus, float knockback,
                Rarity rarity, Supplier<Ingredient> repairIngredient) {
        this.name = name;
        this.durability = durability;
        this.attackDamage = attackDamage;
        this.attackSpeed = attackSpeed;
        this.attackReachBonus = attackReachBonus;
        this.knockback = knockback;
        this.rarity = rarity;
        this.repairIngredient = repairIngredient;
    }

    public String getName() {
        return name;
    }

    public int getDurability() {
        return durability;
    }

    public float getAttackDamage() {
        return attackDamage;
    }

    public float getAttackSpeed() {
        return attackSpeed;
    }

    public float getAttackReachBonus() {
        return attackReachBonus;
    }

    public float getKnockback() {
        return knockback;
    }

    public Rarity getRarity() {
        return rarity;
    }

    public Ingredient getRepairIngredient() {
        return repairIngredient.get();
    }
}
