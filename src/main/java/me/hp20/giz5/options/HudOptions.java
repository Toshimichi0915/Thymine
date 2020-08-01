package me.hp20.giz5.options;

public class HudOptions {
    public int x;
    public int y;
    public Position position;

    public int getX() {
        return x + position.getX();
    }

    public int getY() {
        return y + position.getY();
    }
}
