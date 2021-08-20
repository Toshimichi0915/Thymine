package net.toshimichi.thymine;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.toshimichi.thymine.options.ThymineOptions;

public class SprintStartTick implements ClientTickEvents.StartTick {

    private boolean lastPressed;

    @Override
    public void onStartTick(MinecraftClient client) {
        ThymineOptions opt = ThymineMod.getOptions();
        if (!opt.toggleSprint) return;
        boolean pressed = ThymineMod.getSprintKeyBinding().isPressed();
        if (pressed && !lastPressed) {
            opt.sprint = !opt.sprint;
            if (!opt.sprint)
                client.options.keySprint.setPressed(false);
        }
        lastPressed = pressed;
        if (opt.sprint)
            client.options.keySprint.setPressed(true);
    }
}
