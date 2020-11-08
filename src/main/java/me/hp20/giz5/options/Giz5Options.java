package me.hp20.giz5.options;

public class Giz5Options {
    public boolean fullBright;
    public boolean fastSneak;
    public boolean shiftFix;
    public boolean toggleSprint;
    public boolean sprint;
    public boolean noHurtBobbing;
    public boolean ignoreCooldown;
    public boolean softSneak;
    public boolean forceIcon;
    public double lowFire;
    public ToggleSprintOptions toggleSprintHud;

    public Giz5Options() {
        fullBright = true;
        fastSneak = false;
        shiftFix = true;
        toggleSprint = false;
        sprint = false;
        noHurtBobbing = true;
        ignoreCooldown = true;
        softSneak = true;
        forceIcon = true;
        lowFire = 40;
        toggleSprintHud = new ToggleSprintOptions();
        toggleSprintHud.x = -200;
        toggleSprintHud.y = -15;
        toggleSprintHud.position = Position.CENTER_BOTTOM;
        toggleSprintHud.color = 0xE0E0E0;
    }
}
