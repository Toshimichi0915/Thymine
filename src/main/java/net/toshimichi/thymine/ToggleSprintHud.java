package net.toshimichi.thymine;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.TranslatableText;

public class ToggleSprintHud extends DrawableHelper {

    public void render(MatrixStack stack) {
        MinecraftClient client = MinecraftClient.getInstance();
        TextRenderer renderer = client.textRenderer;
        client.getProfiler().push("toggleSprint");

        float x = ThymineMod.getOptions().toggleSprintHud.getX();
        float y = ThymineMod.getOptions().toggleSprintHud.getY();
        int color = ThymineMod.getOptions().toggleSprintHud.color;
        if (ThymineMod.getOptions().sprint) {
            String toggleSprintEnabled = new TranslatableText("thymine.messages.toggleSprint.enabled").getString();
            renderer.drawWithShadow(stack, toggleSprintEnabled, x, y, color);
        } else {
            String toggleSprintDisabled = new TranslatableText("thymine.messages.toggleSprint.disabled").getString();
            renderer.drawWithShadow(stack, toggleSprintDisabled, x, y, color);
        }
        client.getProfiler().pop();
    }
}
