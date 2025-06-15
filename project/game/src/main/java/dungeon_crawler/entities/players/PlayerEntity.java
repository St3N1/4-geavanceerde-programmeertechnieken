package dungeon_crawler.entities.players;

import dungeon_crawler.Game;
import dungeon_crawler.components.AttackComponent;
import dungeon_crawler.components.MovementComponent;
import dungeon_crawler.components.PositionComponent;
import dungeon_crawler.entities.Entity;
import dungeon_crawler.entities.factory.EntityAttackFactory;
import dungeon_crawler.enums.EntityAttackType;

public class PlayerEntity extends Entity {
	private int score = 0;
	private boolean key = false;

	// default values
	volatile int _slashType = 1;
	volatile int _slashColor = 1;

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

			_slashType++;
			if (_slashType == 4)
				_slashType = 1;
		}
	}

	public void setKey(boolean key) {
		if (!key)
			return;
		this.key = key;
	}
	
	public boolean getKey() {
		return key;
	}

	public void addScore() {
		score++;
	}

	public int getScore() {
		return score;
	}

}
