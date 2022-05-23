package net.spellcraftgaming.rpghud.gui.hud;


;

import net.minecraftforge.api.distmarker.Dist;


import net.minecraftforge.api.distmarker.OnlyIn;import net.minecraft.client.Minecraft;
import net.spellcraftgaming.rpghud.gui.hud.element.HudElement;
import net.spellcraftgaming.rpghud.gui.hud.element.texture.*;

@OnlyIn(Dist.CLIENT)
public class HudFullTexture extends HudExtendedWidget {

	public HudFullTexture(Minecraft mc, String hudKey, String hudName) {
		super(mc, hudKey, hudName);
	}

	@Override
	public HudElement setElementAir() {
		return new HudElementAirTexture();
	}

	@Override
	public HudElement setElementExperience() {
		return new HudElementExperienceTexture();
	}

	@Override
	public HudElement setElementHealth() {
		return new HudElementHealthTexture();
	}

	@Override
	public HudElement setElementHealthMount() {
		return new HudElementHealthMountTexture();
	}

	@Override
	public HudElement setElementJumpBar() {
		return new HudElementJumpBarTexture();
	}

	@Override
	public HudElement setElementFood() {
		return new HudElementFoodTexture();
	}
}
