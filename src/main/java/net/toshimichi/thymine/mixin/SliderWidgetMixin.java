package net.toshimichi.thymine.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.client.gui.widget.SliderWidget;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SliderWidget.class)
abstract public class SliderWidgetMixin extends ClickableWidget {

    private boolean isClicked;

    public SliderWidgetMixin(int x, int y, int width, int height, Text message) {
        super(x, y, width, height, message);
    }

    @Inject(at = @At("HEAD"), method = "onClick(DD)V")
    public void onClick(CallbackInfo info) {
        isClicked = true;
    }

    /**
     * @author hp20
     * @reason Fixes buttons ringing sound even when they were not clicked
     */
    @Overwrite
    public void onRelease(double mouseX, double mouseY) {
        if (isClicked) {
            super.playDownSound(MinecraftClient.getInstance().getSoundManager());
            isClicked = false;
        }
    }
}
