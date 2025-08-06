package net.qualitycard.burningembers.effect;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.qualitycard.burningembers.BurningEmbers;

public class ModEffects {
    public static final RegistryEntry<StatusEffect> INFERNAL = registerStatusEffect("infernal",
            new InfernalEffect(StatusEffectCategory.NEUTRAL, 0xF2A931)
    );

    private static RegistryEntry<StatusEffect> registerStatusEffect(String name, StatusEffect statusEffect) {
        return Registry.registerReference(Registries.STATUS_EFFECT, Identifier.of(BurningEmbers.MOD_ID, name), statusEffect);
    }

    public static void registerEffects() {
        BurningEmbers.LOGGER.info("Registering Mod Effects for " + BurningEmbers.MOD_ID);
    }
}
