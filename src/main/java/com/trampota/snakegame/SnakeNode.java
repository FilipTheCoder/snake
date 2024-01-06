package com.trampota.snakegame;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class SnakeNode implements Collideable{
    protected int x;
    protected int y;
    final private Rectangle rect;

    final private int width;
    final private int height;

    public SnakeNode(int x, int y, Paint color, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.rect = new Rectangle(x,y, width, height);
        this.rect.setFill(color);

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Rectangle getRect() {
        return rect;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setX(int x) {
        this.x = x;
        this.rect.setX(x);

    }
    public void setY(int y) {
        this.y = y;
        this.rect.setY(y);
    }



}
