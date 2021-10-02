package net.toshimichi.thymine;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ScreenTexts;
import net.minecraft.client.gui.screen.options.GameOptionsScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.options.BooleanOption;
import net.minecraft.client.options.DoubleOption;
import net.minecraft.client.options.GameOptions;
import net.minecraft.client.options.Option;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.TranslatableText;
import net.toshimichi.thymine.options.ThymineOptions;

public class ThymineOptionsScreen extends GameOptionsScreen {

    private static final Option[] options;

    private static ThymineOptions options() {
        return ThymineMod.getOptions();
    }

    static {
        options = new Option[]{
                new BooleanOption("thymine.options.fullBright", p -> options().fullBright, (g, t) -> options().fullBright = t),
                new BooleanOption("thymine.options.fastSneak", p -> options().fastSneak, (g, t) -> options().fastSneak = t),
                new BooleanOption("thymine.options.toggleSprint", p -> options().toggleSprint, (g, t) -> options().toggleSprint = t),
                new BooleanOption("thymine.options.noHurtBobbing", p -> options().noHurtBobbing, (g, t) -> options().noHurtBobbing = t),
                new BooleanOption("thymine.options.shiftFix", p -> options().shiftFix, (g, t) -> options().shiftFix = t),
                new BooleanOption("thymine.options.ignoreCooldown", p -> options().ignoreCooldown, (g, t) -> options().ignoreCooldown = t),
                new BooleanOption("thymine.options.softSneak", p -> options().softSneak, (g, t) -> options().softSneak = t),
                new BooleanOption("thymine.options.forceIcon", p -> options().forceIcon, (g, t) -> options().forceIcon = t),
                new BooleanOption("thymine.options.antiSwim", p -> options().antiSwim, (g, t) -> options().antiSwim = t),
                new BooleanOption("thymine.options.potionHud", p -> options().potionHud, (g, t) -> options().potionHud = t),
                new BooleanOption("thymine.options.armorHud", p -> options().armorHud, (g, t) -> options().armorHud = t),
                new BooleanOption("thymine.options.noStatusOverlay", p -> options().noStatusOverlay, (g, t) -> options().noStatusOverlay = t),
                new DoubleOption("thymine.options.lowFire", 0, 100, 1, p -> options().lowFire, (s, b) -> options().lowFire = b,
                        (s, t) -> new TranslatableText("thymine.options.lowFire", String.format("%.0f", options().lowFire)))
        };
    }

    public ThymineOptionsScreen(Screen parent, GameOptions gameOptions) {
        super(parent, gameOptions, new TranslatableText("thymine.options.title"));
    }

    @Override
    protected void init() {
        int count = 0;
        int size = options.length;

        for (int i = 0; i < size; i++) {
            Option option = options[i];
            int x = this.width / 2 - 155 + i % 2 * 160;
            int y = this.height / 6 + 24 * (i / 2);
            addButton(option.createButton(this.client.options, x, y, 150));
            count++;
        }

        this.addButton(new ButtonWidget(this.width / 2 - 100, this.height / 6 + 24 * (count + 1) / 2, 200, 20, ScreenTexts.DONE, (buttonWidget) -> {
            onClose();
        }));
    }

    @Override
    public void onClose() {
        ThymineMod.saveOptions();
        super.onClose();
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        renderBackground(matrices);
        drawCenteredText(matrices, this.textRenderer, this.title, this.width / 2, 20, 16777215);
        super.render(matrices, mouseX, mouseY, delta);
    }
}
