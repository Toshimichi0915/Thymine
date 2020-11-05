package me.hp20.giz5;

import me.hp20.giz5.options.Giz5Options;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ScreenTexts;
import net.minecraft.client.gui.screen.options.GameOptionsScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.options.BooleanOption;
import net.minecraft.client.options.GameOptions;
import net.minecraft.client.options.Option;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.TranslatableText;

public class Giz5OptionsScreen extends GameOptionsScreen {

    private static final Option[] options;

    private static Giz5Options options() {
        return Giz5Mod.getOptions();
    }

    static {
        options = new Option[]{
                new BooleanOption("giz5.options.fullBright", p -> options().fullBright, (s, b) -> options().fullBright = b),
                new BooleanOption("giz5.options.fastSneak", p -> options().fastSneak, (s, b) -> options().fastSneak = b),
                new BooleanOption("giz5.options.toggleSprint", p -> options().toggleSprint, (s, b) -> options().toggleSprint = b),
                new BooleanOption("giz5.options.noHurtBobbing", p -> options().noHurtBobbing, (s, b) -> options().noHurtBobbing = b),
                new BooleanOption("giz5.options.shiftFix", p -> options().shiftFix, (s, b) -> options().shiftFix = b),
                new BooleanOption("giz5.options.ignoreCooldown", p -> options().ignoreCooldown, (s, b) -> options().ignoreCooldown = b)
        };
    }

    public Giz5OptionsScreen(Screen parent, GameOptions gameOptions) {
        super(parent, gameOptions, new TranslatableText("giz5.options.title"));
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
            Giz5Mod.saveOptions();
            this.client.openScreen(this.parent);
        }));
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        renderBackground(matrices);
        drawCenteredText(matrices, this.textRenderer, this.title, this.width / 2, 20, 16777215);
        super.render(matrices, mouseX, mouseY, delta);
    }
}
