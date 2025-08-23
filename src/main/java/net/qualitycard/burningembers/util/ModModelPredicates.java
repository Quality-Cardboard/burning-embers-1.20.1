package net.qualitycard.burningembers.util;

import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.util.Identifier;
import net.qualitycard.burningembers.item.ModItems;

public class ModModelPredicates {
    public static void registerModelPredicates() {
        ModelPredicateProviderRegistry.register(ModItems.ROARING_INFERNO, Identifier.of("burningembers", "hit_1"),
                (itemStack, clientWorld, livingEntity, seed) -> {
                    return itemStack.getOrCreateNbt().getBoolean("hit_1") ? 1.0f : 0.0f;
                });
        ModelPredicateProviderRegistry.register(ModItems.ROARING_INFERNO, Identifier.of("burningembers", "hit_2"),
                (itemStack, clientWorld, livingEntity, seed) -> {
                    return itemStack.getOrCreateNbt().getBoolean("hit_2") ? 1.0f : 0.0f;
                });
        ModelPredicateProviderRegistry.register(ModItems.ROARING_INFERNO, Identifier.of("burningembers", "hit_3"),
                (itemStack, clientWorld, livingEntity, seed) -> {
                    return itemStack.getOrCreateNbt().getBoolean("hit_3") ? 1.0f : 0.0f;
                });

        ModelPredicateProviderRegistry.register(ModItems.EMP, Identifier.of("burningembers", "deactivated"),
                (itemStack, clientWorld, livingEntity, seed) -> {
                    return itemStack.getOrCreateNbt().getBoolean("deactivated") ? 1.0f : 0.0f;
                });

    }
}
