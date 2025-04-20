package game.ecs.components;

import game.ecs.Component;

public class PositionComponent implements Component {
    private int x;
    private int y;

    public PositionComponent(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getXPosition() {
        return x;
    }

    public int getYPosition() {
        return y;
    }
}
