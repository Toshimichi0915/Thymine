package me.hp20.giz5.options;

public class Giz5Options {
    public boolean fullBright;
    public boolean fastSneak;
    public boolean shiftFix;
    public boolean toggleSprint;
    public boolean sprint;
    public boolean noHurtBobbing;
    public boolean ignoreCooldown;
    public ToggleSprintOptions toggleSprintHud;

    public Giz5Options() {
        fullBright = true;
        fastSneak = false;
        shiftFix = true;
        toggleSprint = true;
        sprint = false;
        noHurtBobbing = false;
        ignoreCooldown = false;
        toggleSprintHud = new ToggleSprintOptions();
        toggleSprintHud.x = -200;
        toggleSprintHud.y = -15;
        toggleSprintHud.position = Position.CENTER_BOTTOM;
        toggleSprintHud.color = 0xE0E0E0;
    }
}
