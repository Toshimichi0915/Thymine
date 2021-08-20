package net.toshimichi.thymine;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.effect.StatusEffectInstance;

public class PotionHud extends DrawableHelper {

    public void render(MatrixStack stack, float partial) {
        MinecraftClient client = MinecraftClient.getInstance();
        TextRenderer renderer = client.textRenderer;
        client.getProfiler().push("potionHud");

        float x = ThymineMod.getOptions().potionHudOptions.getX();
        float y = ThymineMod.getOptions().potionHudOptions.getY();
        int index = 0;
        for (StatusEffectInstance effect : client.player.getStatusEffects()) {
            int seconds = effect.getDuration() / 20;
            int minutes = seconds / 60;
            String text = String.format("%02d", minutes) + ':' + String.format("%02d", seconds % 60);

            Sprite sprite = client.getStatusEffectSpriteManager().getSprite(effect.getEffectType());
            RenderSystem.setShaderTexture(0, sprite.getAtlas().getId());
            drawSprite(stack, (int) x, (int) y + (index * 25), getZOffset(), 18, 18, sprite);
            renderer.drawWithShadow(stack, text, x + 21, y + 5 + (index * 25),
                    ThymineMod.getOptions().potionHudOptions.color);
            index++;
        }

        client.getProfiler().pop();
    }
}
