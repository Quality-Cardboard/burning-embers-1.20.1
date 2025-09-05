package net.qualitycard.burningembers;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.qualitycard.burningembers.damage_type.ModDamageTypes;
import net.qualitycard.burningembers.effect.ModEffects;
import net.qualitycard.burningembers.event.BurningEmbersHudRenderer;
import net.qualitycard.burningembers.event.BurningEmbersServerTick;
import net.qualitycard.burningembers.item.ModItemGroups;
import net.qualitycard.burningembers.item.ModItems;
import net.qualitycard.burningembers.lodestone.packets.ModClientPackets;
import net.qualitycard.burningembers.lodestone.packets.ModPackets;
import net.qualitycard.burningembers.sound.ModSounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BurningEmbers implements ModInitializer {
	public static final String MOD_ID = "burningembers";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.registerModItems();
		ModItemGroups.registerItemGroups();
		ModDamageTypes.registerDamageTypes();
		ModEffects.registerEffects();
		ModSounds.registerSounds();
		ModPackets.registerPackets();
		ModClientPackets.registerClientPackets();

		HudRenderCallback.EVENT.register(new BurningEmbersHudRenderer());
		ServerTickEvents.START_SERVER_TICK.register(new BurningEmbersServerTick());
	}
}