package net.toshimichi.thymine;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.text.Text;

public class ToggleSprintHud {

    public void render(DrawContext context) {
        MinecraftClient client = MinecraftClient.getInstance();
        client.getProfiler().push("toggleSprint");
        TextRenderer renderer = client.textRenderer;

        int x = (int) ThymineMod.getOptions().toggleSprintHud.getX();
        int y = (int) ThymineMod.getOptions().toggleSprintHud.getY();
        int color = ThymineMod.getOptions().toggleSprintHud.color;
        if (ThymineMod.getOptions().sprint) {
            String toggleSprintEnabled = Text.translatable("thymine.messages.toggleSprint.enabled").getString();
            context.drawTextWithShadow(renderer, toggleSprintEnabled, x, y, color);
        } else {
            String toggleSprintDisabled = Text.translatable("thymine.messages.toggleSprint.disabled").getString();
            context.drawTextWithShadow(renderer, toggleSprintDisabled, x, y, color);
        }
        client.getProfiler().pop();
    }
}
