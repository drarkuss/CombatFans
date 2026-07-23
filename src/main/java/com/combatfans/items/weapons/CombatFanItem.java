package com.combatfans.items.weapons;

import com.combatfans.items.materials.FanMaterial;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;

import java.util.UUID;

/**
 * Item base para todos os Leques de Combate (versão NeoForge/MojMap).
 *
 * Mesma lógica da versão Fabric: estende SwordItem (aqui em MojMap, ainda
 * chamado SwordItem) e corrige o "double counting" de dano que a fórmula
 * interna do jogo aplicaria por conta do bônus embutido no Tier vanilla.
 */
public class CombatFanItem extends SwordItem {

    private final FanMaterial fanMaterial;

    public CombatFanItem(Tier baseMaterial, FanMaterial fanMaterial, Properties settings) {
        // SwordItem(Tier, attackDamage, attackSpeed, Properties) - mesma
        // assinatura conceitual do Yarn, só que o parâmetro se chama Tier em
        // vez de ToolMaterial, e Properties em vez de Settings.
        //
        // DANO: total exibido = 1 (base do jogador) + attackDamageIn +
        // baseMaterial.getAttackDamageBonus() (bônus embutido do Tier
        // vanilla). Subtraímos os dois para o valor de FanMaterial ser
        // exatamente o número exibido.
        //
        // VELOCIDADE: modificador aditivo sobre o atributo base 4.0.
        super(baseMaterial,
                (int) (fanMaterial.getAttackDamage() - 1 - baseMaterial.getAttackDamageBonus()),
                fanMaterial.getAttackSpeed() - 4.0f,
                settings);
        this.fanMaterial = fanMaterial;
    }

    public FanMaterial getFanMaterial() {
        return fanMaterial;
    }

    /**
     * Identificador estável usado para o modificador de attack reach.
     */
    public static AttributeModifier createReachModifier(FanMaterial material, double value) {
        UUID uuid = UUID.nameUUIDFromBytes(
                ("combatfans:reach:" + material.getName()).getBytes());
        return new AttributeModifier(uuid, "Combat Fan reach bonus", value,
                AttributeModifier.Operation.ADDITION);
    }
}
