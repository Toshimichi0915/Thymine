package me.hp20.giz5.options;

public class HudOptions {
    public float x;
    public float y;
    public Position position;

    public float getX() {
        return x + position.getX();
    }

    public float getY() {
        return y + position.getY();
    }
}
