package be.uantwerpen.fti.ei.snakeGraphicsRect;

import java.awt.Color;
import java.awt.Graphics2D;

public class FoodSpawner {
    private GraphicsContext grCtx;
    private int GameCellsX;
    private int GameCellsY;
    private int foodXPosition;
    private int foodYPosition;

    public FoodSpawner(GraphicsContext grCtx, int gameCellsX, int gameCellsY) {
        this.grCtx = grCtx;
        this.GameCellsX = gameCellsX;
        this.GameCellsY = gameCellsY;
    }

    public Boolean generateFood() {
        foodXPosition = (int) (Math.random() * GameCellsX - 1);
        foodYPosition = (int) (Math.random() * GameCellsY - 1);

        System.out.println("Foodposition - X: " + foodXPosition + ", Y: " + foodYPosition);
        return true;
    }

    public void draw() {
        Graphics2D g2d = grCtx.getG2d();
        int size = grCtx.getSize();

        g2d.setColor(new Color(0, 200, 0));
        g2d.fillRect(foodXPosition * size, foodYPosition * size, size, size);
    }

    public int[] getPosition() {
        return new int[] { foodXPosition, foodYPosition };
    }
}
