package net.spellcraftgaming.rpghud.gui.hud.element.modern;



;


;

import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodData;
import net.minecraft.world.item.ItemStack;

import com.mojang.blaze3d.vertex.PoseStack;;

import net.minecraftforge.api.distmarker.Dist;


import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraft.client.gui.Gui;




import net.spellcraftgaming.rpghud.gui.hud.HudModern;
import net.spellcraftgaming.rpghud.gui.hud.element.HudElement;
import net.spellcraftgaming.rpghud.gui.hud.element.HudElementType;
import net.spellcraftgaming.rpghud.settings.Settings;

@OnlyIn(Dist.CLIENT)
public class HudElementFoodModern extends HudElement {

	public HudElementFoodModern() {
		super(HudElementType.FOOD, 0, 0, 0, 0, true);
		parent = HudElementType.WIDGET;
	}

	@Override
	public boolean checkConditions() {
		return !this.mc.options.hideGui;
	}

	@Override
	public void drawElement(Gui gui, PoseStack ms, float zLevel, float partialTicks, int scaledWidth, int scaledHeight) {
		FoodData stats = this.mc.player.getFoodData();
		int stamina = stats.getFoodLevel();
		int staminaMax = 20;
		int xOffset = ((HudModern) this.rpgHud.huds.get("modern")).getPosX();
		String staminaString = this.settings.getBoolValue(Settings.hunger_percentage) ? (int) Math.floor((double) stamina / (double) staminaMax * 100) + "%" : stamina + "/" + staminaMax;
		int width = this.mc.font.width(staminaString) / 2 + 4;
		if(width < xOffset) width = xOffset;
		else ((HudModern) this.rpgHud.huds.get("modern")).setPosX(width);

		int posX = (this.settings.getBoolValue(Settings.render_player_face) ? 24 : 2) + ((this.settings.getBoolValue(Settings.show_numbers_health) && this.settings.getBoolValue(Settings.show_numbers_food)) ? xOffset : 0) + this.settings.getPositionValue(Settings.hunger_position)[0];
		int textPosX = this.settings.getPositionValue(Settings.hunger_position)[0];
		int posY = this.settings.getPositionValue(Settings.hunger_position)[1];

		if (this.settings.getBoolValue(Settings.show_numbers_health) && this.settings.getBoolValue(Settings.show_numbers_food)) {
			drawRect(ms, textPosX + (this.settings.getBoolValue(Settings.render_player_face) ? 23 : 2), posY + 12, width, 8, 0xA0000000);
			ms.scale(0.5f, 0.5f, 0.5f);
			Gui.drawCenteredString(ms, this.mc.font, staminaString, textPosX * 2 + (this.settings.getBoolValue(Settings.render_player_face) ? 42 : 0) + width + 4, posY * 2 + 28, -1);
			ms.scale(2f, 2f, 2f);
		}

		drawTetragon(posX, posX, 13 + posY, 13 + posY, 70, 58, 8, 8, 0xA0000000);
		drawTetragon(posX + 2, posX + 2, 13 + posY, 13 + posY, 64, 54, 6, 6, 0x20FFFFFF);

		ItemStack itemMain = this.mc.player.getMainHandItem();
		ItemStack itemSec = this.mc.player.getOffhandItem();

		if (stats.needsFood() && this.settings.getBoolValue(Settings.show_hunger_preview)) {
			float value = 0;
			if (itemMain != ItemStack.EMPTY && itemMain.getItem().getFoodProperties() != null) {
				value = itemMain.getItem().getFoodProperties().getNutrition();
			} else if (itemSec != ItemStack.EMPTY && itemMain.getItem().getFoodProperties() != null) {
				value = itemSec.getItem().getFoodProperties().getNutrition();
			}
			if (value > 0) {
				int bonusHunger = (int) (value + stamina);
				if (bonusHunger > staminaMax)
					bonusHunger = staminaMax;
				drawTetragon(posX + 2, posX + 2, 13 + posY, 13 + posY, (int) (64 * ((double) bonusHunger / (double) staminaMax)), (int) (63 * ((double) bonusHunger / (double) 20)) - 10, 6, 6, offsetColor(this.settings.getIntValue(Settings.color_food), OFFSET_PREVIEW));
			}
		}

		if (this.mc.player.hasEffect(MobEffects.HUNGER)) {
			drawTetragon(posX + 2, posX + 2, 13 + posY, 13 + posY, (int) (64 * ((double) stamina / (double) staminaMax)), (int) (64 * ((double) stamina / (double) 20)) - 10, 6, 6, this.settings.getIntValue(Settings.color_hunger));
		} else {
			drawTetragon(posX + 2, posX + 2, 13 + posY, 13 + posY, (int) (64 * ((double) stamina / (double) staminaMax)), (int) (64 * ((double) stamina / (double) 20)) - 10, 6, 6, this.settings.getIntValue(Settings.color_food));
		}
	}

}
