package com.combatfans.config;

import com.combatfans.CombatFans;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.minecraftforge.fml.loading.FMLPaths;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Configuração global do mod, persistida em {@code config/combatfans.json}.
 *
 * No Fabric usávamos {@code FabricLoader.getInstance().getConfigDir()}. No
 * Forge/NeoForge legado (1.20.1), o caminho equivalente é
 * {@code FMLPaths.CONFIGDIR}.
 */
public final class CombatFansConfig {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final String FILE_NAME = "combatfans.json";

    public static CombatFansConfig INSTANCE = new CombatFansConfig();

    public double damageMultiplier = 1.0;
    public double attackSpeedMultiplier = 1.0;
    public double durabilityMultiplier = 1.0;
    public double reachMultiplier = 1.0;
    public int comboResetTicks = 20;
    public boolean particlesEnabled = true;
    public boolean soundsEnabled = true;
    public boolean customEnchantmentsEnabled = true;
    public boolean betterCombatIntegrationEnabled = true;

    private CombatFansConfig() {
    }

    private static Path configPath() {
        return FMLPaths.CONFIGDIR.get().resolve(FILE_NAME);
    }

    public static void load() {
        Path path = configPath();
        if (Files.exists(path)) {
            try (Reader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
                INSTANCE = GSON.fromJson(reader, CombatFansConfig.class);
                CombatFans.LOGGER.info("Configuração do Combat Fans carregada de {}", path);
                return;
            } catch (IOException e) {
                CombatFans.LOGGER.warn("Falha ao ler config do Combat Fans, usando padrões.", e);
            }
        }
        INSTANCE = new CombatFansConfig();
        save();
    }

    public static void save() {
        Path path = configPath();
        try {
            Files.createDirectories(path.getParent());
            try (Writer writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8)) {
                GSON.toJson(INSTANCE, writer);
            }
        } catch (IOException e) {
            CombatFans.LOGGER.error("Falha ao salvar config do Combat Fans.", e);
        }
    }
}
