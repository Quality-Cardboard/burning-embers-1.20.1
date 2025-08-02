package net.qualitycard.burningembers.util;

import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.util.Identifier;
import net.qualitycard.burningembers.item.ModItems;

public class ModModelPredicates {
    public static void registerModelPredicates() {
        ModelPredicateProviderRegistry.register(ModItems.ROARING_INFERNO, Identifier.of("burningembers", "activated"),
                (itemStack, clientWorld, livingEntity, seed) -> {
                    return itemStack.getOrCreateNbt().getBoolean("activated") ? 1.0f : 0.0f;
                });

    }
}
