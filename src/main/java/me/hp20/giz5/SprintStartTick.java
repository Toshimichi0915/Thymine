package me.hp20.giz5;

import me.hp20.giz5.options.Giz5Options;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;

public class SprintStartTick implements ClientTickEvents.StartTick {

    private boolean lastPressed;

    @Override
    public void onStartTick(MinecraftClient client) {
        Giz5Options opt = Giz5Mod.getOptions();
        if (!opt.toggleSprint) return;
        boolean pressed = Giz5Mod.getSprintKeyBinding().isPressed();
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
