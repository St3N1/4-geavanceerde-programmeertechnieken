package dungeon_crawler.entities.enemies;

import dungeon_crawler.Game;
import dungeon_crawler.components.AttackComponent;
import dungeon_crawler.components.CollidableComponent;
import dungeon_crawler.components.HealthComponent;
import dungeon_crawler.components.MovementComponent;
import dungeon_crawler.components.PositionComponent;
import dungeon_crawler.components.RenderComponent;
import dungeon_crawler.components.VelocityComponent;
import dungeon_crawler.entities.Entity;
import dungeon_crawler.entities.factory.EntityAttackFactory;
import dungeon_crawler.enums.EntityAttackType;

public class Skeleton extends EnemyEntity {
    private int health = 4;
    private int attackSpeed = 2000;
    private int damage = 1;
    private int velocity = 30;
    private int arrowVelocity = 150;

    private String sprite = "/sprites/entities/skeleton.png";
    private int spriteX = 48;
    private int spriteY = 2616;
    private int spriteW = 16;
    private int spriteH = 24;
    private double scale = 2;

    public Skeleton() {
        this.addComponent(new RenderComponent(sprite, spriteX, spriteY, spriteW, spriteH, scale));
        this.addComponent(new HealthComponent(health));
        this.addComponent(new AttackComponent(damage, attackSpeed));
        this.addComponent(new PositionComponent(0, 0, spriteW * scale, spriteH * scale));
        this.addComponent(new VelocityComponent(velocity, velocity));
        this.addComponent(new MovementComponent());
        this.addComponent(new CollidableComponent());
    }

    public void shootArrow(double targetX, double targetY) {
        AttackComponent ac = this.getComponent(AttackComponent.class).get();
        MovementComponent mc = this.getComponent(MovementComponent.class).get();

        if (ac.canAttack()) {
            PositionComponent pc = this.getComponent(PositionComponent.class).get();

            EntityAttackFactory factory = Game.getInstance().getEntityAttackFactory();
            Entity arrow = factory.createEntity(EntityAttackType.ARROW, pc.getX(), pc.getY(), arrowVelocity);

            double dx = Math.abs(targetX - pc.getX());
            double dy = Math.abs(targetY - pc.getY());
            double length = Math.sqrt(dx * dx + dy * dy);
            if (length == 0)
                return;

            dx /= length;
            dy /= length;

            arrow.addComponent(new VelocityComponent(dx * arrowVelocity, dy * arrowVelocity));

            AttackComponent arrowAC = new AttackComponent(ac.getDamage(), attackSpeed);
            arrow.addComponent(arrowAC);

            MovementComponent arrowMC = new MovementComponent();
            arrowMC.setDirection(mc.getX(), mc.getY());
            arrow.addComponent(arrowMC);
            Game.getInstance().newEntity(arrow);

            ac.updateLastAttackTime();
        }
    }
}
