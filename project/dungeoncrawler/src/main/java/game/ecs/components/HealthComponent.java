package game.ecs.components;

import game.ecs.Component;

public class HealthComponent implements Component {
    private int health;

    public HealthComponent(int health) {
        this.health = health;
    }

    public int getHealth() {
        return health;
    }
}
