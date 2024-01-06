package com.trampota.snakegame;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import java.util.Objects;

public class Food implements Collideable {
    private final int x;
    private final int y;

    private final Rectangle rect;



    public Food(int x, int y, Paint color, int width, int height) {
        this.x = x;
        this.y = y;
        this.rect = new Rectangle(x,y, width, height);
        rect.setFill(color);
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public int getWidth() {
        return (int)rect.getWidth();
    }

    @Override
    public int getHeight() {
        return (int) rect.getHeight();
    }
    public Rectangle getRect() {
        return rect;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Food food = (Food) o;
        return x == food.x && y == food.y && Objects.equals(rect, food.rect);
    }


}
