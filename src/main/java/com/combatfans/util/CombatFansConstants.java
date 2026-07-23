package com.combatfans.util;

public final class CombatFansConstants {

    private CombatFansConstants() {
    }

    public static final String MOD_ID = "combatfans";
    public static final String TAG_COMBAT_FANS = "combat_fans";
    public static final String TAG_KATANAS = "katanas";
    public static final String WEAPON_TYPE_FAN = "fan_weapons";
    public static final String TAG_BETTERCOMBAT_WEAPONS = "bettercombat_weapons";

    public static String id(String path) {
        return MOD_ID + ":" + path;
    }
}
