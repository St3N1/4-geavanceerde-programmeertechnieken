package dungeon_crawler.systems;

import java.util.Iterator;
import java.util.List;

import dungeon_crawler.components.MovementComponent;
import dungeon_crawler.components.PositionComponent;
import dungeon_crawler.components.VelocityComponent;
import dungeon_crawler.entities.Entity;
import dungeon_crawler.entities.attacks.Arrow;

public class ProjectileSystem {
    public void update(List<Entity> entities, long deltaTime) {
        double stepTime = deltaTime / 1000.0;

        Iterator<Entity> iterator = entities.iterator();
        while (iterator.hasNext()) {
            Entity entity = iterator.next();
            if (!(entity instanceof Arrow))
                continue;

            Arrow arrow = (Arrow) entity;
            arrow.getComponent(MovementComponent.class).ifPresent(mov -> {
                arrow.getComponent(PositionComponent.class).ifPresent(pos -> {
                    arrow.getComponent(VelocityComponent.class).ifPresent(vel -> {
                        pos.setX(pos.getX() + vel.getDx() * mov.getX() * stepTime);
                        pos.setY(pos.getY() + vel.getDy() * mov.getY() * stepTime);
                    });
                });
            });
        }
    }
}
