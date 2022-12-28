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
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Supplier;

@Mixin(OptionsScreen.class)
public abstract class OptionsScreenMixin extends Screen {

    @Final
    @Shadow
    private GameOptions settings;

    private GridWidget.Adder adder;

    @Shadow
    protected abstract ButtonWidget createButton(Text message, Supplier<Screen> screenSupplier);

    protected OptionsScreenMixin(Text title) {
        super(title);
    }

    @ModifyVariable(at = @At(value = "STORE", target = "Lnet/minecraft/client/gui/widget/GridWidget;createAdder(I)Lnet/minecraft/client/gui/widget/GridWidget$Adder;"), method = "init()V")
    public GridWidget.Adder createAdder(GridWidget.Adder adder) {
        this.adder = adder;
        return adder;
    }

    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/widget/GridWidget$Adder;add(Lnet/minecraft/client/gui/widget/ClickableWidget;ILnet/minecraft/client/gui/widget/Positioner;)Lnet/minecraft/client/gui/widget/ClickableWidget;", ordinal = 0), method = "init()V")
    public void init(CallbackInfo ci) {
        adder.add(createButton(Text.translatable("thymine.options.title"), () -> new ThymineOptionsScreen(this, settings)));
    }
}
