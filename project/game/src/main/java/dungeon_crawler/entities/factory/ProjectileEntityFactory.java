package dungeon_crawler.entities.factory;

import dungeon_crawler.entities.Entity;
import dungeon_crawler.entities.projectiles.Arrow;
import dungeon_crawler.entities.projectiles.Sword;
import dungeon_crawler.enums.EntityType;

public class ProjectileEntityFactory implements AbstractEntityFactory {
    public Entity getEntity(EntityType entityType) {
        switch (entityType) {
            case ARROW:
                return new Arrow();
            case SWORD:
                return new Sword();
            default:
                throw new IllegalArgumentException("Unknown entity type: " + entityType);
        }
    }
}
