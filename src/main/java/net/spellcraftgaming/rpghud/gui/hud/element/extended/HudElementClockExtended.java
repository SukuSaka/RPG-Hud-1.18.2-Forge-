package net.spellcraftgaming.rpghud.gui.hud.element.extended;

import com.mojang.blaze3d.systems.RenderSystem;


;


;

import com.mojang.blaze3d.vertex.PoseStack;;

import net.minecraftforge.api.distmarker.Dist;


import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraft.client.gui.Gui;

import net.spellcraftgaming.rpghud.gui.hud.element.vanilla.HudElementClockVanilla;
import net.spellcraftgaming.rpghud.settings.Settings;

@OnlyIn(Dist.CLIENT)
public class HudElementClockExtended extends HudElementClockVanilla {

	public HudElementClockExtended() {
		super();
	}

	@Override
	public void drawElement(Gui gui, PoseStack ms, float zLevel, float partialTicks, int scaledWidth, int scaledHeight) {
		int clockColor = 0xFFFFFF;
		if (this.settings.getBoolValue(Settings.enable_clock_color)) {
			clockColor = getClockColor();
		}
		if (this.settings.getBoolValue(Settings.reduce_size))
			ms.scale(0.5f, 0.5f, 0.5f);
		this.mc.font.draw(ms, getTime(), (this.settings.getBoolValue(Settings.reduce_size) ? 8 : 4) + this.settings.getPositionValue(Settings.clock_position)[0], (this.settings.getBoolValue(Settings.reduce_size) ? 124 : 62) + this.settings.getPositionValue(Settings.clock_position)[1], clockColor);
		RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
		if (this.settings.getBoolValue(Settings.reduce_size))
			ms.scale(2f, 2f, 2f);
	}

}
