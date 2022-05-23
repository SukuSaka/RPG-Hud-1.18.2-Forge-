package net.spellcraftgaming.rpghud.gui.hud.element.modern;



;


;

import com.mojang.blaze3d.vertex.PoseStack;;

import net.minecraft.world.entity.LivingEntity;import net.minecraftforge.api.distmarker.Dist;


import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraft.client.gui.Gui;


import net.spellcraftgaming.rpghud.gui.hud.element.HudElement;
import net.spellcraftgaming.rpghud.gui.hud.element.HudElementType;
import net.spellcraftgaming.rpghud.settings.Settings;

@OnlyIn(Dist.CLIENT)
public class HudElementJumpBarModern extends HudElement {

	public HudElementJumpBarModern() {
		super(HudElementType.JUMP_BAR, 0, 0, 0, 0, true);
	}

	@Override
	public boolean checkConditions() {
		return  this.mc.player.getVehicle() instanceof LivingEntity && (this.settings.getBoolValue(Settings.limit_jump_bar) ? this.mc.player.getJumpRidingScale() > 0F : true);
	}

	@Override
	public void drawElement(Gui gui, PoseStack ms, float zLevel, float partialTicks, int scaledWidth, int scaledHeight) {
		int height = scaledHeight + this.settings.getPositionValue(Settings.jump_bar_position)[1];
		int width = scaledWidth;
		float jumpPower = this.mc.player.getJumpRidingScale();
		int value = (int) (jumpPower * 100.0F);
		int posX = this.settings.getPositionValue(Settings.jump_bar_position)[0];
		drawRect(ms, width / 2 - 72 + posX, height - 78, 144, 2, 0xA0000000);
		drawRect(ms, width / 2 - 72 + posX, height - 70, 144, 2, 0xA0000000);
		drawRect(ms, width / 2 - 72 + posX, height - 76, 2, 6, 0xA0000000);
		drawRect(ms, width / 2 + 70 + posX, height - 76, 2, 6, 0xA0000000);
		drawRect(ms, width / 2 - 70 + posX, height - 76, 140, 6, 0x20FFFFFF);
		drawRect(ms, width / 2 - 70 + posX, height - 76, (int) (140 * (value / 100.0D)), 6, this.settings.getIntValue(Settings.color_jump_bar));
	}

}
