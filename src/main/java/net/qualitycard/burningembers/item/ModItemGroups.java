package net.qualitycard.burningembers.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.qualitycard.burningembers.BurningEmbers;

public class ModItemGroups {
    public static void registerItemGroups() {
        BurningEmbers.LOGGER.info("Registering Mod Item Groups for: " + BurningEmbers.MOD_ID);
    }

    public static final ItemGroup BURNINGEMBERS_ITEMS_GROUP = Registry.register(Registries.ITEM_GROUP, Identifier.of(BurningEmbers.MOD_ID, "burningembers_item_group"),
            FabricItemGroup.builder()
                    .icon(() -> new ItemStack(ModItems.ROARING_INFERNO))
                    .displayName(Text.translatable("itemgroup.burningembers.burningembers_item_group"))
                    .entries((displayContext, entries) -> {
                        entries.add(ModItems.ROARING_INFERNO);
                        entries.add(ModItems.ARDENT_ALLOY);
                        entries.add(ModItems.ANCIENT_COMPASS);
                        entries.add(ModItems.EMP);

                        entries.add(ModItems.ARDENT_MASK);
                        entries.add(ModItems.ARDENT_CHESTPLATE);
                        entries.add(ModItems.ARDENT_LEGGINGS);
                        entries.add(ModItems.ARDENT_BOOTS);
                    })
                    .build()
    );
}
