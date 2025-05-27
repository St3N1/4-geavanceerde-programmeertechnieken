package dungeon_crawler.systems;

import java.util.List;

import dungeon_crawler.components.MovementComponent;
import dungeon_crawler.components.PositionComponent;
import dungeon_crawler.components.VelocityComponent;
import dungeon_crawler.entities.Entity;
import dungeon_crawler.entities.enemies.EnemyEntity;
import dungeon_crawler.entities.players.PlayerEntity;

public class MovementSystem {
    public MovementSystem() {

    }

    public void update(List<Entity> entities, long deltaTime) {
        double seconds = deltaTime / 1000.0;
        for (Entity entity : entities) {
            if (entity instanceof PlayerEntity || entity instanceof EnemyEntity) {
                entity.getComponent(MovementComponent.class).ifPresent(movement -> {
                    entity.getComponent(PositionComponent.class).ifPresent(position -> {
                        entity.getComponent(VelocityComponent.class).ifPresent(velocity -> {
                            position.setX(position.getX() + velocity.getDx() * movement.getX() * seconds);
                            position.setY(position.getY() + velocity.getDy() * movement.getY() * seconds);
                        });
                    });
                });
            }
        }
    }
}
