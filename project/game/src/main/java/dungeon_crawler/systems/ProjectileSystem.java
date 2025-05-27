package dungeon_crawler.systems;

import java.util.List;

import dungeon_crawler.components.AttackComponent;
import dungeon_crawler.components.PositionComponent;
import dungeon_crawler.entities.Entity;
import dungeon_crawler.entities.enemies.Skeleton;
import dungeon_crawler.entities.factory.ProjectileEntityFactory;
import dungeon_crawler.entities.players.PlayerEntity;
import dungeon_crawler.entities.projectiles.Arrow;
import dungeon_crawler.enums.EntityType;

public class ProjectileSystem {
    private ProjectileEntityFactory projectileEntityFactory;

    public ProjectileSystem(ProjectileEntityFactory projectileEntityFactory) {
        this.projectileEntityFactory = projectileEntityFactory;
    }

    public Arrow shootArrow(Skeleton skeleton, PlayerEntity player) {
        Arrow arrow = (Arrow) projectileEntityFactory.getEntity(EntityType.ARROW);

        AttackComponent skeletonDamage = skeleton.getComponent(AttackComponent.class).get();
        PositionComponent skeletonPosition = skeleton.getComponent(PositionComponent.class).get();
        PositionComponent playerPosition = player.getComponent(PositionComponent.class).get();

        double dirX = playerPosition.getX() / playerPosition.getX() * 2;
        double dirY = playerPosition.getY() / playerPosition.getY() * 2;

        arrow.setDamage(skeletonDamage.getDamage());
        arrow.setPosition(skeletonPosition.getX(), skeletonPosition.getY());
        arrow.setVelocity(dirX, dirY);
        return arrow;
    }

    public void update(List<Entity> entities, long deltaTime) {
        // double seconds = deltaTime / 1000.0;
    }
}
