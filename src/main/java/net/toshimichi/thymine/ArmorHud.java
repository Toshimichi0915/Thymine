package net.toshimichi.thymine;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.world.GameMode;
import net.toshimichi.thymine.mixin.ClientPlayerInteractionManagerAccessor;

import java.util.ArrayList;
import java.util.Collections;

public class ArmorHud extends DrawableHelper {

    public void render(MatrixStack stack, float partial) {
        MinecraftClient client = MinecraftClient.getInstance();
        GameMode current = ((ClientPlayerInteractionManagerAccessor) client.interactionManager).getGameMode();
        if (current != GameMode.SURVIVAL && current != GameMode.ADVENTURE) return;

        client.getProfiler().push("armorHud");
        ItemRenderer itemRenderer = client.getItemRenderer();

        int index = 0;
        ArrayList<ItemStack> armorItems = new ArrayList<>();
        client.player.getArmorItems().forEach(armorItems::add);
        Collections.reverse(armorItems);
        int width = client.getWindow().getScaledWidth();
        int height = client.getWindow().getScaledHeight();
        for (ItemStack itemStack : armorItems) {
            int x = width / 2 + 12 + (index * 20);
            int y = height - 57;
            itemRenderer.renderGuiItemIcon(itemStack, x, y);
            itemRenderer.renderGuiItemOverlay(client.textRenderer, itemStack, x, y);
            index++;
        }
        client.getProfiler().pop();
    }
}
