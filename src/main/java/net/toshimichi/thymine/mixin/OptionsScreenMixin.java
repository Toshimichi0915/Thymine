package net.toshimichi.thymine.mixin;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.OptionsScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.GridWidget;
import net.minecraft.client.option.GameOptions;
import net.minecraft.text.Text;
import net.toshimichi.thymine.ThymineOptionsScreen;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.function.Supplier;

@Mixin(OptionsScreen.class)
public abstract class OptionsScreenMixin extends Screen {

    @Final
    @Shadow
    private GameOptions settings;

    @Shadow
    protected abstract ButtonWidget createButton(Text message, Supplier<Screen> screenSupplier);

    protected OptionsScreenMixin(Text title) {
        super(title);
    }

    @ModifyVariable(at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/widget/ThreePartsLayoutWidget;addBody(Lnet/minecraft/client/gui/widget/Widget;)Lnet/minecraft/client/gui/widget/Widget;"), method = "init()V")
    public GridWidget.Adder createAdder(GridWidget.Adder adder) {
        adder.add(createButton(Text.translatable("thymine.options.title"), () -> new ThymineOptionsScreen(this, settings)));
        return adder;
    }
}
