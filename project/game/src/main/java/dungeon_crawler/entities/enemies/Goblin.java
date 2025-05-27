package dungeon_crawler.entities.enemies;

import dungeon_crawler.components.AttackComponent;
import dungeon_crawler.components.HealthComponent;
import dungeon_crawler.components.PositionComponent;

public class Goblin extends EnemyEntity {
    private int health = 6;
    private int attackSpeed = 3;
    private int damage = 2;

    public Goblin() {
        this.addComponent(new HealthComponent(health));
        this.addComponent(new AttackComponent(damage, attackSpeed));
        this.addComponent(new PositionComponent(5, 10, 10, 10));
    }
}
