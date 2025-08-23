package net.qualitycard.burningembers.sound;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.qualitycard.burningembers.BurningEmbers;

public class ModSounds {
    public static final SoundEvent EMP_EXPLOSION = registerSoundEvent("emp_explosion");

    private static SoundEvent registerSoundEvent(String name) {
        Identifier id = new Identifier(BurningEmbers.MOD_ID, name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }

    public static void registerSounds() {
        BurningEmbers.LOGGER.info("Registering Sounds for: " + BurningEmbers.MOD_ID);
    }
}
