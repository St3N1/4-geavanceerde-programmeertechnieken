package dungeon_crawler.entities.enemies;

import dungeon_crawler.components.AttackComponent;
import dungeon_crawler.components.CollidableComponent;
import dungeon_crawler.components.HealthComponent;
import dungeon_crawler.components.MovementComponent;
import dungeon_crawler.components.PositionComponent;
import dungeon_crawler.components.RenderComponent;

public class Skeleton extends EnemyEntity {
    private int health = 4;
    private int attackSpeed = 4;
    private int damage = 4;
    private String sprite = "/sprites/entities/skeleton.png";
    private int spriteX = 48;
    private int spriteY = 2616;
    private int spriteW = 16;
    private int spriteH = 24;
    private int scale = 2;

    public Skeleton() {
        this.addComponent(new RenderComponent(sprite, spriteX, spriteY, spriteW, spriteH, 2));
        this.addComponent(new HealthComponent(health));
        this.addComponent(new AttackComponent(damage, attackSpeed));
        this.addComponent(new PositionComponent(300, 300, spriteW * scale, spriteH * scale));
        this.addComponent(new MovementComponent());
        this.addComponent(new CollidableComponent());
    }
}
