package net.qualitycard.burningembers.damage_type;

import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.qualitycard.burningembers.BurningEmbers;

public class ModDamageTypes {
    public static final RegistryKey<DamageType> INFERNAL = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, Identifier.of("burningembers", "infernal"));
    public static final RegistryKey<DamageType> ELECTRICITY = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, Identifier.of("burningembers", "electricity"));

    public static DamageSource of(World world, RegistryKey<DamageType> key) {
        return new DamageSource(world.getRegistryManager().get(RegistryKeys.DAMAGE_TYPE).entryOf(key));
    }

    public static void registerDamageTypes() {
        BurningEmbers.LOGGER.info("Registering Mod Damage Types for " + BurningEmbers.MOD_ID);
    }
}
