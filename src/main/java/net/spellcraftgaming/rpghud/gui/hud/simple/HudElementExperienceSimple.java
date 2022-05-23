package net.spellcraftgaming.rpghud.gui.hud.simple;


;


;

import net.minecraft.util.Mth;

import com.mojang.blaze3d.vertex.PoseStack;;


import net.minecraft.client.gui.Gui;


import net.spellcraftgaming.rpghud.gui.hud.element.HudElement;
import net.spellcraftgaming.rpghud.gui.hud.element.HudElementType;
import net.spellcraftgaming.rpghud.settings.Settings;

public class HudElementExperienceSimple extends HudElement {

	public HudElementExperienceSimple() {
		super(HudElementType.EXPERIENCE, 0, 0, 0, 0, true);
	}

	@Override
	public boolean checkConditions() {
		return !this.mc.options.hideGui;
	}
	
	@Override
	public void drawElement(Gui gui, PoseStack ms, float zLevel, float partialTicks, int scaledWidth, int scaledHeight) {
		int exp = Mth.ceil(this.mc.player.getXpNeededForNextLevel() * this.mc.player.experienceProgress);
		int expCap = this.mc.player.getXpNeededForNextLevel();
		double full = 100D / expCap;
		int width = 182;
		int posX = ((scaledWidth - width) / 2)  + this.settings.getPositionValue(Settings.experience_position)[0];
		int posY = scaledHeight - 31 + this.settings.getPositionValue(Settings.experience_position)[1];
		drawCustomBar(ms, posX, posY, width, 8, exp * full, 0xA0000000, 0xA0000000, this.settings.getIntValue(Settings.color_experience), offsetColorPercent(this.settings.getIntValue(Settings.color_experience), 25), 0xA0000000);
		
		String stringExp =  this.settings.getBoolValue(Settings.experience_percentage) ? (int) Math.floor((double) exp / (double) expCap * 100) + "%" : exp + "/" + expCap;

		if (this.settings.getBoolValue(Settings.show_numbers_experience)) {
			ms.scale(0.5f, 0.5f, 0.5f);
			Gui.drawCenteredString(ms, this.mc.font, stringExp, posX * 2 + width, posY * 2 + 4, -1);
			ms.scale(2f, 2f, 2f);
		}
	}

}
