package be.uantwerpen.fti.ei.snakeGraphicsExample;

import java.awt.*;

public class Snake {
    private GraphicsContext grCtx;
    private Input.Inputs direction;
    private int curX = 5;
    private int curY = 5;

    public Snake(GraphicsContext grCtx) {
        this.grCtx = grCtx;
        direction = Input.Inputs.RIGHT;
    }

    public void setDirection(Input.Inputs direction)
    {
        this.direction = direction;
    }

    public void update() {
        switch (direction) {
            case LEFT  -> curX--;
            case RIGHT -> curX++;
            case DOWN  -> curY++;
            case UP    -> curY--;
        }
    }

    public void draw() {
        Graphics2D g2d = grCtx.getG2d();
        int size = grCtx.getSize();
        switch (direction) {
            case LEFT:
                g2d.drawImage(grCtx.snakeSprite.getSubimage(3 * size, 1 * size, size, size), (curX-1) * size, curY * size, null);
                g2d.drawImage(grCtx.snakeSprite.getSubimage(1 * size, 0 * size, size, size), curX * size, curY * size, null);
                g2d.drawImage(grCtx.snakeSprite.getSubimage(3 * size, 3 * size, size, size), (curX+1) * size, curY * size, null);
                break;
            case RIGHT:
                g2d.drawImage(grCtx.snakeSprite.getSubimage(4 * size, 2 * size, size, size), (curX-1) * size, curY * size, null);
                g2d.drawImage(grCtx.snakeSprite.getSubimage(1 * size, 0 * size, size, size), curX * size, curY * size, null);
                g2d.drawImage(grCtx.snakeSprite.getSubimage(4 * size, 0 * size, size, size), (curX+1) * size, curY * size, null);
                break;
            case DOWN:
                g2d.drawImage(grCtx.snakeSprite.getSubimage(4 * size, 3 * size, size, size), curX * size, (curY-1) * size, null);
                g2d.drawImage(grCtx.snakeSprite.getSubimage(2 * size, 1 * size, size, size), curX * size, curY * size, null);
                g2d.drawImage(grCtx.snakeSprite.getSubimage(4 * size, 1 * size, size, size), curX * size, (curY+1) * size, null);
                break;
            case UP:
                g2d.drawImage(grCtx.snakeSprite.getSubimage(3 * size, 0 * size, size, size), curX * size, (curY-1) * size, null);
                g2d.drawImage(grCtx.snakeSprite.getSubimage(2 * size, 1 * size, size, size), curX * size, curY * size, null);
                g2d.drawImage(grCtx.snakeSprite.getSubimage(3 * size, 2 * size, size, size), curX * size, (curY+1) * size, null);
                break;
        }
    }

}
