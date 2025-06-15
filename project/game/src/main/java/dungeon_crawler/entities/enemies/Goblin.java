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

public class Goblin extends EnemyEntity {
    private int health = 6;
    private int attackSpeed = 1000;
    private int velocity = 40;
    private int damage = 2;

    private String sprite = "/sprites/entities/goblin.png";
    private int spriteX = 0;
    private int spriteY = 2760;
    private int spriteW = 16;
    private int spriteH = 24;
    private int scale = 2;

    private int _slashType = 3;
    private int _slashColor = 1;

    public Goblin() {
        this.addComponent(new RenderComponent(sprite, spriteX, spriteY, spriteW, spriteH, scale));
        this.addComponent(new HealthComponent(health));
        this.addComponent(new AttackComponent(damage, attackSpeed));
        this.addComponent(new PositionComponent(0, 0, spriteW * scale, spriteH * scale));
        this.addComponent(new VelocityComponent(velocity, velocity));
        this.addComponent(new MovementComponent());
        this.addComponent(new CollidableComponent());
    }

	public void attack() {
		AttackComponent ac = this.getComponent(AttackComponent.class).get();
		if (ac.canAttack()) {
			PositionComponent positionComponent = this.getComponent(PositionComponent.class).get();
			MovementComponent movementComponent = this.getComponent(MovementComponent.class).get();

			int dx = movementComponent.getX() != 0 ? movementComponent.getX() : movementComponent.getLastX();
			int dy = movementComponent.getY() != 0 ? movementComponent.getY() : movementComponent.getLastY();
            
			double slashX = positionComponent.getX() + dx * (positionComponent.getWidth() - 12);
			double slashY = positionComponent.getY() + dy * (positionComponent.getHeight() - 12);

			if (dx < 0)
				slashX -= (positionComponent.getWidth() - 12);

			EntityAttackFactory factory = Game.getInstance().getEntityAttackFactory();
			Entity slash = factory.createEntity(EntityAttackType.SLASH, slashX, slashY, _slashType, _slashColor, this);

			MovementComponent movComponent = new MovementComponent();
			movComponent.setDirection(dx, dy);
			slash.addComponent(movComponent);
			Game.getInstance().newEntity(slash);

			ac.updateLastAttackTime();
		}
	}
}
