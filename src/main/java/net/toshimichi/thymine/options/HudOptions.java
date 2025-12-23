package net.toshimichi.thymine.options;

public class HudOptions {
    public int x;
    public int y;
    public Position position;
    public boolean hidden;

    public int getX() {
        return x + position.getX();
    }

    public int getY() {
        return y + position.getY();
    }

    public boolean isHidden() {
        return hidden;
    }
}
