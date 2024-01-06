package com.trampota.snakegame;

import javafx.scene.paint.Paint;

public class SnakeHead extends  SnakeNode {
    public SnakeHead(int x, int y, Paint color, int width, int height) {
        super(x, y, color, width, height);
    }
    public void up(int amount) {
        super.setY(super.getY() - amount);

    }
    public void right(int amount) {
        super.setX(super.getX() + amount);
    }
    public void down(int amount) {
        super.setY(super.getY() + amount);
    }
    public void left(int amount) {
        super.setX(super.getX() - amount);
    }
    public boolean collidedWith(Collideable thing) {
        return x == thing.getX() && y == thing.getY();
    }


}
