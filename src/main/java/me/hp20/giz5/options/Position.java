package me.hp20.giz5.options;

import net.minecraft.client.MinecraftClient;

import java.util.function.IntSupplier;

public enum Position {

    LEFT_TOP(() -> 0, () -> 0),
    LEFT_CENTER(() -> 0, () -> getHeight() / 2),
    LEFT_BOTTOM(() -> 0, Position::getHeight),
    CENTER_TOP(() -> getWidth() / 2, () -> 0),
    CENTER(() -> getWidth() / 2, () -> getHeight() / 2),
    CENTER_BOTTOM(() -> getWidth() / 2, Position::getHeight),
    RIGHT_TOP(Position::getWidth, () -> 0),
    RIGHT_CENTER(Position::getWidth, () -> getHeight() / 2),
    RIGHT_BOTTOM(Position::getWidth, Position::getHeight);

    private final IntSupplier x;
    private final IntSupplier y;

    Position(IntSupplier x, IntSupplier y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x.getAsInt();
    }

    public int getY() {
        return y.getAsInt();
    }

    private static int getWidth() {
        return MinecraftClient.getInstance().getWindow().getScaledWidth();
    }

    private static int getHeight() {
        return MinecraftClient.getInstance().getWindow().getScaledHeight();
    }
}
