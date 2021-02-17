package me.hp20.giz5.mixin;

import me.hp20.giz5.Giz5OptionsScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.options.OptionsScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.options.GameOptions;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(OptionsScreen.class)
public abstract class OptionsScreenMixin extends Screen {

    @Shadow
    private GameOptions settings;

    protected OptionsScreenMixin(Text title) {
        super(title);
    }

    @Inject(at = @At("TAIL"), method = "init()V", require = 1)
    public void init(CallbackInfo info) {
        addButton(new ButtonWidget(width / 2 - 155, height / 6 + 144 - 6, 150, 20,
                new TranslatableText("giz5.options.title"), (w) -> {
            client.openScreen(new Giz5OptionsScreen(this, settings));
        }));
    }
}
