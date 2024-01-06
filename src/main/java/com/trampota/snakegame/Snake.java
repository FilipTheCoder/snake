package com.trampota.snakegame;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.ArrayList;

public class Snake {
    final private ArrayList<SnakeNode> parts;
    final private SnakeHead head;
    private Direction direction;
    final private Paint partColor;
    final private int stageWidth;
    private final int stageHeight;

    public Snake(int partwidth, int partheight, Paint partColor, int stageWidth, int stageHeight) {
        this.partColor = partColor;
        this.stageWidth = stageWidth;
        this.stageHeight = stageHeight;
        this.head = new SnakeHead(0,0, Color.BLUE, partwidth, partheight);
        this.parts = new ArrayList<>();
        direction = Direction.RIGHT;
    }

    public void drawOnPane(Pane pane) {
        pane.getChildren().add(head.getRect());
    }
    public void setDirection(Direction direction) {
        this.direction = direction;
    }
    public void move(int amount) {
        switch (direction) {
            case UP -> up(amount);
            case DOWN -> down(amount);
            case LEFT -> left(amount);
            case RIGHT -> right(amount);
        }
    }

    private void up(int amount) {
        int lastX = head.getX();
        int lastY = head.getY();
        head.up(amount);
        moveTail(lastX, lastY);
    }

    private void moveTail(int lastX, int lastY) {
        for (var part: parts) {
            int currentX = part.getX();
            int currentY = part.getY();

            part.setX(lastX);
            part.setY(lastY);

            lastX = currentX;
            lastY = currentY;
        }
    }

    private void down(int amount) {
        int lastX = head.getX();
        int lastY = head.getY();
        head.down(amount);
        moveTail(lastX, lastY);
    }


    private void left(int amount) {
        int lastX = head.getX();
        int lastY = head.getY();
        head.left(amount);
        moveTail(lastX, lastY);
    }

    private void right(int amount) {
        int lastX = head.getX();
        int lastY = head.getY();
        head.right(amount);
        moveTail(lastX, lastY);
    }
    private SnakeNode grow() {
        SnakeNode lastNode;
        if(parts.size() > 0) {
            lastNode = parts.getLast();
        }
        else {
            lastNode = head;
        }
        SnakeNode node = null;
        switch (direction) {
            case RIGHT -> node = new SnakeNode(lastNode.getX() - lastNode.getWidth(), lastNode.getY(), this.partColor, lastNode.getWidth(), lastNode.getHeight());
            case LEFT -> node = new SnakeNode(lastNode.getX() + lastNode.getWidth(), lastNode.getY(), this.partColor, lastNode.getWidth(), lastNode.getHeight());
            case UP -> node = new SnakeNode(lastNode.getX() , lastNode.getY() + lastNode.getHeight(), this.partColor, lastNode.getWidth(), lastNode.getHeight());
            case DOWN -> node = new SnakeNode(lastNode.getX() , lastNode.getY() - lastNode.getHeight(), this.partColor, lastNode.getWidth(), lastNode.getHeight());
        }
        //SnakeNode node = new SnakeNode(lastNode.getX() - lastNode.getWidth(), lastNode.getY(), this.partColor, lastNode.getWidth(), lastNode.getHeight());
        parts.add(node);
        return node;
    }
    public boolean canEat(Food food) {
        return head.collidedWith(food);
    }
    public void eatAndDraw(Pane pane) {
        var node = grow();
        pane.getChildren().add(node.getRect());
    }
    public int tailLength() {
        return parts.size();
    }

    public boolean died() {
        int x = head.getX();
        int y = head.getY();
        if(x < 0 || x > stageWidth || y < 0 || y > stageHeight) {
            return true;
        }
        for (SnakeNode node: parts) {
            if((node.getY() == y && node.getX() == x)) {
                return true;
            }

        }
        return false;
    }
}
