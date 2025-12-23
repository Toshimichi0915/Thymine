package net.toshimichi.thymine;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.item.ItemStack;
import net.minecraft.util.profiler.Profilers;
import net.minecraft.world.GameMode;
import net.toshimichi.thymine.mixin.ClientPlayerInteractionManagerAccessor;

import java.util.ArrayList;

public class ArmorHud {

    public void render(DrawContext context) {
        MinecraftClient client = MinecraftClient.getInstance();
        GameMode current = ((ClientPlayerInteractionManagerAccessor) client.interactionManager).getGameMode();
        if (current != GameMode.SURVIVAL && current != GameMode.ADVENTURE) return;

        Profilers.get().push("armorHud");

        int index = 0;
        ArrayList<ItemStack> armorItems = new ArrayList<>();
        armorItems.add(client.player.getInventory().getStack(39));
        armorItems.add(client.player.getInventory().getStack(38));
        armorItems.add(client.player.getInventory().getStack(37));
        armorItems.add(client.player.getInventory().getStack(36));
        int width = client.getWindow().getScaledWidth();
        int height = client.getWindow().getScaledHeight();
        for (ItemStack itemStack : armorItems) {
            int x = width / 2 + 12 + (index * 20);
            int y = height - 57;
            context.drawItem(itemStack, x, y);
            context.drawStackOverlay(client.textRenderer, itemStack, x, y);
            index++;
        }
        Profilers.get().pop();
    }
}
