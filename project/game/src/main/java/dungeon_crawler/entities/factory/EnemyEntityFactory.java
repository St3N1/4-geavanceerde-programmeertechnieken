package dungeon_crawler.entities.factory;

import dungeon_crawler.entities.Entity;
import dungeon_crawler.entities.enemies.Goblin;
import dungeon_crawler.entities.enemies.Skeleton;
import dungeon_crawler.enums.EntityType;

public class EnemyEntityFactory implements AbstractEntityFactory {
    public Entity getEntity(EntityType entityType) {
        switch (entityType) {
            case GOBLIN:
                return new Goblin();
            case SKELETON:
                return new Skeleton();
            default:
                throw new IllegalArgumentException("Unknown entity type: " + entityType);
        }
    }
}
