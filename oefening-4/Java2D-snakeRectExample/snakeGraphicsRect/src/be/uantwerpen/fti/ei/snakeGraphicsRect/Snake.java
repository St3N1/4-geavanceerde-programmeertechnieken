package be.uantwerpen.fti.ei.snakeGraphicsRect;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

public class Snake {
    private final int DEFAULT_SIZE = 5;
    private int addSize = 0;
    private int score = 0;
    private Boolean eatenFood = false;
    private GraphicsContext grCtx;
    private int dx = 1;
    private int dy = 0;
    private int GameCellsX;
    private int GameCellsY;
    private ArrayList<Point> snake = new ArrayList<>();
    private int curX = 7;
    private int curY = 7;

    public Snake(GraphicsContext grCtx, int gameCellsX, int gameCellsY) {
        this.grCtx = grCtx;
        this.GameCellsX = gameCellsX;
        this.GameCellsY = gameCellsY;
        for (int i = 0; i < DEFAULT_SIZE; i++) // make snake
            snake.add(new Point(i + 7, 7));
    }

    public void setDirection(Input.Inputs direction) {
        switch (direction) {
            case LEFT -> {
                dx = -1;
                dy = 0;
            }
            case RIGHT -> {
                dx = 1;
                dy = 0;
            }
            case DOWN -> {
                dx = 0;
                dy = 1;
            }
            case UP -> {
                dx = 0;
                dy = -1;
            }
            default -> throw new IllegalArgumentException("Unexpected value: " + direction);
        }
    }

    public void update() {
        curX = (curX + dx + GameCellsX) % GameCellsX;
        curY = (curY + dy + GameCellsY) % GameCellsY;
        if (!eatenFood)
            snake.remove(0); // remove tail
        snake.add(new Point(curX, curY)); // add new head
    }

    public void draw() {
        Graphics2D g2d = grCtx.getG2d();
        int size = grCtx.getSize();
        for (int i = 0; i < DEFAULT_SIZE + addSize; i++) {
            if (i == DEFAULT_SIZE + addSize - 1)
                g2d.setColor(new Color(170, 170, 0));
            else
                g2d.setColor(new Color(170, 0, 0));

            g2d.fillRect(snake.get(i).x * size, snake.get(i).y * size, size, size);
        }
    }

    public void checkSelfCollisions() {
        for (int i = 0; i < snake.size() - 1; i++) {
            if (curX == snake.get(i).x && curY == snake.get(i).y) {
                score = 0;
                System.out.println("Score:\t" + score);
                return;
            }
        }
    }

    public Boolean checkFoodCollision(int[] position) {
        eatenFood = false;
        if (position[0] == curX && position[1] == curY) {
            eatenFood = true;
            addSize++;
            score++;
            System.out.println("Score:\t" + score);
            return false;
        }
        return true;
    }
}
