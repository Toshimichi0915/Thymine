package me.hp20.giz5;

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

        float x = Giz5Mod.getOptions().toggleSprintHud.getX();
        float y = Giz5Mod.getOptions().toggleSprintHud.getY();
        int color = Giz5Mod.getOptions().toggleSprintHud.color;
        if (Giz5Mod.getOptions().sprint) {
            String toggleSprintEnabled = new TranslatableText("giz5.messages.toggleSprint.enabled").getString();
            renderer.drawWithShadow(stack, toggleSprintEnabled, x, y, color);
        } else {
            String toggleSprintDisabled = new TranslatableText("giz5.messages.toggleSprint.disabled").getString();
            renderer.drawWithShadow(stack, toggleSprintDisabled, x, y, color);
        }
        client.getProfiler().pop();
    }
}
