package dungeon_crawler.entities.players;

import dungeon_crawler.components.AttackComponent;
import dungeon_crawler.components.CollidableComponent;
import dungeon_crawler.components.HealthComponent;
import dungeon_crawler.components.MovementComponent;
import dungeon_crawler.components.PositionComponent;
import dungeon_crawler.components.RenderComponent;
import dungeon_crawler.components.VelocityComponent;

public class Warrior extends PlayerEntity {
    // stats
    private int health = 20;
    private int damage = 1;
    private int attackSpeed = 700;
    private int velocity = 70;
    private int slashColor = 4;

    // sprites
    private String sprite = "/sprites/entities/warrior.png";
    private int spriteX = 32;
    private int spriteY = 2664;
    private int spriteW = 16;
    private int spriteH = 24;
    private int scale = 2;

    public Warrior() {
        this._slashColor = slashColor;
        this.addComponent(new RenderComponent(sprite, spriteX, spriteY, spriteW, spriteH, 2));
        this.addComponent(new HealthComponent(health));
        this.addComponent(new AttackComponent(damage, attackSpeed));
        this.addComponent(new PositionComponent(0, 0, spriteW * scale, spriteH * scale));
        this.addComponent(new VelocityComponent(velocity, velocity));
        this.addComponent(new MovementComponent());
        this.addComponent(new CollidableComponent());
    }
}
