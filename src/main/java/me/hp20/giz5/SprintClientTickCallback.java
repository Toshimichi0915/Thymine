package me.hp20.giz5;

import net.fabricmc.fabric.api.event.client.ClientTickCallback;
import net.minecraft.client.MinecraftClient;

public class SprintClientTickCallback implements ClientTickCallback {

    private boolean lastPressed;

    @Override
    public void tick(MinecraftClient client) {
        Giz5Options opt = Giz5Mod.getOptions();
        if (opt.toggleSprint) {
            boolean pressed = Giz5Mod.getSprintKeyBinding().isPressed();
            if (pressed && !lastPressed)
                opt.sprint = !opt.sprint;
            lastPressed = pressed;
        } else {
            opt.sprint = false;
        }

        if (opt.toggleSprint && opt.sprint)
            client.options.keySprint.setPressed(true);
    }
}
