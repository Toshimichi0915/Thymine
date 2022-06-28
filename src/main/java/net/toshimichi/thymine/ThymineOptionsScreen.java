package net.toshimichi.thymine;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.GameOptionsScreen;
import net.minecraft.client.gui.widget.ButtonListWidget;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.option.SimpleOption;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Text;
import net.toshimichi.thymine.options.ThymineOptions;

public class ThymineOptionsScreen extends GameOptionsScreen {

    private ButtonListWidget list;

    private static ThymineOptions options() {
        return ThymineMod.getOptions();
    }

    public ThymineOptionsScreen(Screen parent, GameOptions gameOptions) {
        super(parent, gameOptions, Text.translatable("thymine.options.title"));
    }

    @Override
    protected void init() {
        SimpleOption<?>[] options = {
                SimpleOption.ofBoolean("thymine.options.fullBright", options().fullBright, t -> options().fullBright = t),
                SimpleOption.ofBoolean("thymine.options.fastSneak", options().fastSneak, t -> options().fastSneak = t),
                SimpleOption.ofBoolean("thymine.options.toggleSprint", options().toggleSprint, t -> options().toggleSprint = t),
                SimpleOption.ofBoolean("thymine.options.noHurtBobbing", options().noHurtBobbing, t -> options().noHurtBobbing = t),
                SimpleOption.ofBoolean("thymine.options.shiftFix", options().shiftFix, t -> options().shiftFix = t),
                SimpleOption.ofBoolean("thymine.options.ignoreCooldown", options().ignoreCooldown, t -> options().ignoreCooldown = t),
                SimpleOption.ofBoolean("thymine.options.softSneak", options().softSneak, t -> options().softSneak = t),
                SimpleOption.ofBoolean("thymine.options.forceIcon", options().forceIcon, t -> options().forceIcon = t),
                SimpleOption.ofBoolean("thymine.options.antiSwim", options().antiSwim, t -> options().antiSwim = t),
                SimpleOption.ofBoolean("thymine.options.potionHud", options().potionHud, t -> options().potionHud = t),
                SimpleOption.ofBoolean("thymine.options.armorHud", options().armorHud, t -> options().armorHud = t),
                SimpleOption.ofBoolean("thymine.options.noStatusOverlay", options().noStatusOverlay, t -> options().noStatusOverlay = t),
                new SimpleOption<>("thymine.options.lowFire", SimpleOption.emptyTooltip(), ThymineOptionsScreen::getPercentValueText,
                        SimpleOption.DoubleSliderCallbacks.INSTANCE, options().lowFire, (v) -> options().lowFire = v),
        };

        this.list = new ButtonListWidget(this.client, this.width, this.height, 32, this.height - 32, 25);
        list.addAll(options);

        addSelectableChild(list);
        addDrawableChild(new ButtonWidget(this.width / 2 - 100, this.height - 27, 200, 20, ScreenTexts.DONE, (buttonWidget) -> {
            close();
        }));
    }

    @Override
    public void close() {
        ThymineMod.saveOptions();
        super.close();
    }

    private static Text getPercentValueText(Text prefix, double value) {
        return Text.translatable("options.percent_value", prefix, (int) (value * 100.0));
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        if (super.mouseReleased(mouseX, mouseY, button)) return true;
        if (this.list.mouseReleased(mouseX, mouseY, button)) return true;
        return false;
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        renderBackground(matrices);
        list.render(matrices, mouseX, mouseY, delta);
        drawCenteredText(matrices, this.textRenderer, this.title, this.width / 2, 5, 16777215);
        super.render(matrices, mouseX, mouseY, delta);
    }
}
