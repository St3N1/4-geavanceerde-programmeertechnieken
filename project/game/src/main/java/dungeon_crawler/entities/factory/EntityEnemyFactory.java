package dungeon_crawler.entities.factory;

import dungeon_crawler.entities.Entity;
import dungeon_crawler.entities.enemies.Goblin;
import dungeon_crawler.entities.enemies.Skeleton;
import dungeon_crawler.enums.EntityEnemyType;

public class EntityEnemyFactory implements AbstractEntityFactory {
    @Override
    public Entity createEntity(Object type, Object... args) {
        switch ((EntityEnemyType) type) {
            case GOBLIN:
                return new Goblin();
            case SKELETON:
                return new Skeleton();
            default:
                throw new IllegalArgumentException("Unknown entity type: " + type);
        }
    }
}
