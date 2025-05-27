package dungeon_crawler.systems;

import java.util.List;

import dungeon_crawler.components.CollidableComponent;
import dungeon_crawler.components.PositionComponent;
import dungeon_crawler.entities.Entity;
import dungeon_crawler.entities.players.PlayerEntity;

public class CollisionSystem {
	public CollisionSystem() {

	}

	public void update(PlayerEntity player, List<Entity> entities) {
		PositionComponent playerPos = player.getComponent(PositionComponent.class).orElse(null);
		if (playerPos == null || !player.hasComponent(CollidableComponent.class))
			return;

		for (Entity entity : entities) {
			if (entity == player)
				continue;
			if (!entity.hasComponent(CollidableComponent.class))
				continue;

			PositionComponent entityPos = entity.getComponent(PositionComponent.class).orElse(null);
			if (entityPos == null)
				continue;

			if (playerPos.overlaps(entityPos)) {
				double currentX = playerPos.getX();
				double currentY = playerPos.getY();

				playerPos.restorePreviousX();
				if (playerPos.overlapsX(entityPos)) {
					playerPos.setX(currentX);
				}

				playerPos.restorePreviousY();
				if (playerPos.overlapsY(entityPos)) {
					playerPos.setY(currentY);
				}
			}
		}
	}
}
