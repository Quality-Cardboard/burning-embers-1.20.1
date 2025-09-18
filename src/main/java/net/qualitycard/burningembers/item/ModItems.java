package net.qualitycard.burningembers.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterials;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.qualitycard.burningembers.BurningEmbers;
import net.qualitycard.burningembers.item.custom.AncientCompass;
import net.qualitycard.burningembers.item.custom.ArdentChestplate;
import net.qualitycard.burningembers.item.custom.EMP;
import net.qualitycard.burningembers.item.custom.RoaringInferno;

public class ModItems {
    public static final Item ROARING_INFERNO = registerItem("roaring_inferno",
            new RoaringInferno(ToolMaterials.NETHERITE, 4, -1.5f,
                    new FabricItemSettings().fireproof().rarity(Rarity.EPIC)));
    public static final Item ARDENT_ALLOY = registerItem("ardent_alloy",
            new Item(new FabricItemSettings()));
    public static final Item ANCIENT_COMPASS = registerItem("ancient_compass",
            new AncientCompass(new FabricItemSettings().fireproof().maxCount(1)));
    public static final Item EMP = registerItem("emp",
            new EMP(new FabricItemSettings().maxCount(1)));

    public static final Item ARDENT_MASK = registerItem("ardent_mask",
            new ArmorItem(ModArmorMaterials.ARDENT_ALLOY, ArmorItem.Type.HELMET, new FabricItemSettings()));
    public static final Item ARDENT_CHESTPLATE = registerItem("ardent_chestplate",
            new ArdentChestplate(ModArmorMaterials.ARDENT_ALLOY, ArmorItem.Type.CHESTPLATE, new FabricItemSettings()));
    public static final Item ARDENT_LEGGINGS = registerItem("ardent_leggings",
            new ArmorItem(ModArmorMaterials.ARDENT_ALLOY, ArmorItem.Type.LEGGINGS, new FabricItemSettings()));
    public static final Item ARDENT_BOOTS = registerItem("ardent_boots",
            new ArmorItem(ModArmorMaterials.ARDENT_ALLOY, ArmorItem.Type.BOOTS, new FabricItemSettings()));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(BurningEmbers.MOD_ID, name), item);
    }

    public static void registerModItems() {
        BurningEmbers.LOGGER.info("Registering Mod Items for: " + BurningEmbers.MOD_ID);
    }
}
