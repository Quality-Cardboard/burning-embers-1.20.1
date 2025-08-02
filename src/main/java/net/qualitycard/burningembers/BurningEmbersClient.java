package net.qualitycard.burningembers;

import net.fabricmc.api.ClientModInitializer;
import net.qualitycard.burningembers.util.ModModelPredicates;

public class BurningEmbersClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ModModelPredicates.registerModelPredicates();
    }
}
