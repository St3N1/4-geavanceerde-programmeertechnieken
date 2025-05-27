package dungeon_crawler.entities.players;

import dungeon_crawler.components.AttackComponent;
import dungeon_crawler.components.CollidableComponent;
import dungeon_crawler.components.HealthComponent;
import dungeon_crawler.components.MovementComponent;
import dungeon_crawler.components.PositionComponent;
import dungeon_crawler.components.RenderComponent;
import dungeon_crawler.components.VelocityComponent;

public class Rogue extends PlayerEntity {
    private int health = 8;
    private int attackSpeed = 5;
    private int damage = 4;
    private int velocity = 85;
    private String sprite = "/sprites/entities/rogue.png";
    private int spriteX = 0;
    private int spriteY = 2688;
    private int spriteW = 16;
    private int spriteH = 24;
    private int scale = 2;

    public Rogue() {
        this.addComponent(new RenderComponent(sprite, spriteX, spriteY, spriteW, spriteH, scale));
        this.addComponent(new HealthComponent(health));
        this.addComponent(new AttackComponent(damage, attackSpeed));
        this.addComponent(new PositionComponent(0, 0, spriteW * scale, spriteH * scale));
        this.addComponent(new VelocityComponent(velocity, velocity));
        this.addComponent(new MovementComponent());
        this.addComponent(new CollidableComponent());
    }

}
