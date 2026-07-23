# Combat Fans

A Minecraft Forge mod that adds a new category of weapons inspired by oriental combat fans, along with long blades (Amethyst Katana and Odachi), fully integrated with **Better Combat** for smooth combos and animations.

![Minecraft](https://img.shields.io/badge/Minecraft-1.20.1-brightgreen)
![Forge](https://img.shields.io/badge/Forge-47.4.10-orange)

## ⚔️ Weapons

### Combat Fans (8 total)

Each fan follows a familiar material progression, with its own damage, speed, reach, and durability stats:

| Fan | Notes |
|---|---|
| Paper Fan | Fragile, but extremely fast |
| Stone Fan | |
| Iron Fan | |
| Gold Fan | The fastest of the standard lineup |
| Amethyst Fan | |
| Diamond Fan | |
| Netherite Fan | |
| Lilith's Fan | The strongest and rarest of all, legendary |

### Amethyst Blades

| Weapon | Notes |
|---|---|
| Amethyst Katana | Agile and precise |
| Amethyst Odachi | Long blade, heavier strikes, greater reach |

## ✨ Features

- **Full Better Combat integration** — each weapon uses its own combo presets (dagger/katana/claymore), with fluid animations and attack sequences
- **Unique particles and sounds per material** — each fan has its own distinct visual and sound effect on hit
- **Careful balancing** — damage, speed, durability, and reach individually calibrated for each weapon
- **Data-driven design** — recipes, tags, and weapon attributes are plain JSON, easy to customize

## 📦 Required Dependencies

- [Better Combat](https://www.curseforge.com/minecraft/mc-mods/better-combat)
- [Cloth Config API](https://www.curseforge.com/minecraft/mc-mods/cloth-config)
- [Player Animator](https://www.curseforge.com/minecraft/mc-mods/player-animator)
- [Artifex](https://www.curseforge.com/minecraft/mc-mods/artifex) — required for crafting the Amethyst Katana and Odachi

## 🎮 Version

- Minecraft: **1.20.1**
- Mod Loader: **Forge**
- Forge: **47.4.10**
- Java: **17**

## 🛠️ Building from source

```bash
git clone https://github.com/drarkuss/CombatFans.git
cd CombatFans
./gradlew build
```

The compiled `.jar` will be in `build/libs/`.

### Running in a dev environment

```bash
./gradlew runClient
```

Place the required dependency jars (Better Combat, Cloth Config, Player Animator) in `run/mods/` before running.

## 📁 Project structure

```
src/main/java/com/combatfans/
├── items/
│   ├── materials/    # FanMaterial (stats per fan) and Tier mapping
│   └── weapons/      # CombatFanItem, AmethystKatanaItem, AmethystOdachiItem
├── registry/         # Central item registration (ModItems)
├── effects/          # Particle effects + attack event handler
├── sounds/           # Sound effects per material
├── config/           # Mod configuration
└── compat/           # Better Combat detection/compat layer

src/main/resources/
├── assets/combatfans/   # Textures, models, lang files
└── data/combatfans/     # Recipes, tags, loot tables, weapon_attributes
```

## 📄 License

All Rights Reserved.

## 🙏 Credits

Built with the help of [Better Combat](https://github.com/Kir-Antipov) and the Fabric/Forge modding community.
