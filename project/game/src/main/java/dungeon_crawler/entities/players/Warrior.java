package dungeon_crawler.entities.players;

import dungeon_crawler.components.AttackComponent;
import dungeon_crawler.components.CollidableComponent;
import dungeon_crawler.components.HealthComponent;
import dungeon_crawler.components.MovementComponent;
import dungeon_crawler.components.PositionComponent;
import dungeon_crawler.components.RenderComponent;
import dungeon_crawler.components.VelocityComponent;

public class Warrior extends PlayerEntity {
    private int health = 10;
    private int attackSpeed = 4;
    private int damage = 4;
    private int velocity = 70;
    private String sprite = "/sprites/entities/warrior.png";
    private int spriteX = 32;
    private int spriteY = 2664;
    private int spriteW = 16;
    private int spriteH = 24;
    private int scale = 2;

    public Warrior() {
        this.addComponent(new RenderComponent(sprite, spriteX, spriteY, spriteW, spriteH, 2));
        this.addComponent(new HealthComponent(health));
        this.addComponent(new AttackComponent(damage, attackSpeed));
        this.addComponent(new PositionComponent(0, 0, spriteW * scale, spriteH * scale));
        this.addComponent(new VelocityComponent(velocity, velocity));
        this.addComponent(new MovementComponent());
        this.addComponent(new CollidableComponent());
    }
}
