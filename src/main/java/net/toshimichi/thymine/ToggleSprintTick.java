package net.toshimichi.thymine;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.toshimichi.thymine.options.ThymineOptions;

public class ToggleSprintTick implements ClientTickEvents.StartTick {

    private boolean lastPressed;

    @Override
    public void onStartTick(MinecraftClient client) {
        ThymineOptions opt = ThymineMod.getOptions();
        if (!opt.toggleSprint) {
            opt.sprint = false;
            return;
        }
        boolean pressed = ThymineMod.getSprintKeyBinding().isPressed();
        if (pressed && !lastPressed) {
            opt.sprint = !opt.sprint;
        }
        this.lastPressed = pressed;
    }
}