package net.spellcraftgaming.rpghud.gui.hud.element.defaulthud;



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
public class HudElementJumpBarDefault extends HudElement {

	public HudElementJumpBarDefault() {
		super(HudElementType.JUMP_BAR, 0, 0, 0, 0, true);
	}

	@Override
	public boolean checkConditions() {
		return this.mc.player.getVehicle() instanceof LivingEntity && (this.settings.getBoolValue(Settings.limit_jump_bar) ? this.mc.player.getJumpRidingScale() > 0F : true);
	}

	@Override
	public void drawElement(Gui gui, PoseStack ms, float zLevel, float partialTicks, int scaledWidth, int scaledHeight) {
		int height = scaledHeight + this.settings.getPositionValue(Settings.jump_bar_position)[1];
		int center = (scaledWidth / 2) + this.settings.getPositionValue(Settings.jump_bar_position)[0];
		float jumpPower = this.mc.player.getJumpRidingScale();
		int value = (int) (jumpPower * 100.0F);
		drawCustomBar(ms, center - 70, height - 80, 141, 10, value / 100.0D * 100.0D, this.settings.getIntValue(Settings.color_jump_bar), offsetColorPercent(this.settings.getIntValue(Settings.color_jump_bar), OFFSET_PERCENT));
	}

}
