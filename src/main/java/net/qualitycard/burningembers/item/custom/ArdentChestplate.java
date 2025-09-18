package net.qualitycard.burningembers.item.custom;

import io.github.fabricators_of_create.porting_lib.mixin.accessors.common.accessor.PlayerAccessor;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.qualitycard.burningembers.BurningEmbers;
import net.qualitycard.burningembers.item.ModItems;

import java.util.Iterator;

public class ArdentChestplate extends ArmorItem {
    public ArdentChestplate(ArmorMaterial material, Type type, Settings settings) {
        super(material, type, settings);
    }

    private float arson = 0;

    public float returnArson() {
        return arson;
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        super.inventoryTick(stack, world, entity, slot, selected);
        if (entity instanceof PlayerEntity target && target.getEquippedStack(EquipmentSlot.HEAD).getItem() == ModItems.ARDENT_MASK
                && target.getEquippedStack(EquipmentSlot.CHEST).getItem() instanceof ArdentChestplate ardentChestplate
                && target.getEquippedStack(EquipmentSlot.LEGS).getItem() == ModItems.ARDENT_LEGGINGS
                && target.getEquippedStack(EquipmentSlot.FEET).getItem() == ModItems.ARDENT_BOOTS && arson <= 90) {
            ardentChestplate.arson += 0.05F;
        }
    }
}
