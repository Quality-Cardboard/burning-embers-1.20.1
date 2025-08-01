package net.qualitycard.burningembers.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterials;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.qualitycard.burningembers.BurningEmbers;
import net.qualitycard.burningembers.item.custom.LodestoneDI;
import net.qualitycard.burningembers.item.custom.RoaringInferno;

public class ModItems {
    public static final Item ROARING_INFERNO = registerItem("roaring_inferno",
            new RoaringInferno(ToolMaterials.NETHERITE, 4, -1.5f, new FabricItemSettings()));
    public static final Item LDI = registerItem("ldi",
            new LodestoneDI.LodeStoneDI(new Item.Settings()));


    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(BurningEmbers.MOD_ID, name), item);
    }

    public static void registerModItems() {
        BurningEmbers.LOGGER.info("Registering Mod Items for: " + BurningEmbers.MOD_ID);
    }
}
