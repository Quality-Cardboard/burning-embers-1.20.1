package net.qualitycard.burningembers;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.qualitycard.burningembers.damage_type.ModDamageTypes;
import net.qualitycard.burningembers.effect.ModEffects;
import net.qualitycard.burningembers.event.InfernalHudRendererCallback;
import net.qualitycard.burningembers.item.ModItems;
import net.qualitycard.burningembers.lodestone.packets.ModClientPackets;
import net.qualitycard.burningembers.lodestone.packets.ModPackets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BurningEmbers implements ModInitializer {
	public static final String MOD_ID = "burningembers";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.registerModItems();
		ModDamageTypes.registerDamageTypes();
		ModEffects.registerEffects();
		ModPackets.registerPackets();
		ModClientPackets.registerClientPackets();

		HudRenderCallback.EVENT.register(new InfernalHudRendererCallback());
	}
}