package net.toshimichi.thymine.options;

public class ThymineOptions {
    public boolean fullBright;
    public boolean fastSneak;
    public boolean shiftFix;
    public boolean toggleSprint;
    public boolean sprint;
    public boolean noHurtBobbing;
    public boolean ignoreCooldown;
    public boolean softSneak;
    public boolean forceIcon;
    public boolean antiSwim;
    public boolean potionHud;
    public boolean armorHud;
    public boolean noStatusOverlay;
    public boolean noScreenBobbing;
    public boolean showHitBox;
    public double lowFire;
    public ToggleSpringHudOptions toggleSprintHud;
    public PotionHudOptions potionHudOptions;

    public ThymineOptions() {
        fullBright = true;
        fastSneak = false;
        shiftFix = true;
        toggleSprint = false;
        sprint = false;
        noHurtBobbing = true;
        ignoreCooldown = true;
        softSneak = true;
        forceIcon = true;
        antiSwim = false;
        potionHud = true;
        armorHud = true;
        noStatusOverlay = true;
        lowFire = 0.4;
        noScreenBobbing = false;
        showHitBox = false;
        toggleSprintHud = new ToggleSpringHudOptions();
        toggleSprintHud.x = -200;
        toggleSprintHud.y = -15;
        toggleSprintHud.position = Position.CENTER_BOTTOM;
        toggleSprintHud.color = 0xE0E0E0;
        potionHudOptions = new PotionHudOptions();
        potionHudOptions.x = 10;
        potionHudOptions.y = 10;
        potionHudOptions.position = Position.LEFT_TOP;
        potionHudOptions.color = 0xE0E0E0;
    }
}
