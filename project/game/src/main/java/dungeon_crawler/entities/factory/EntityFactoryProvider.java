package dungeon_crawler.entities.factory;

import dungeon_crawler.enums.EntityFactoryType;

public class EntityFactoryProvider {
    public static AbstractEntityFactory getFactory(EntityFactoryType entityFactoryType) {
        switch (entityFactoryType) {
            case PLAYER:
                return new PlayerEntityFactory();
            case ENEMY:
                return new EnemyEntityFactory();
            case PROJECTILE:
                return new ProjectileEntityFactory();
            case ITEM:
                return new ItemEntityFactory();
            default:
                throw new IllegalArgumentException("Unknown entity factory type: " + entityFactoryType);
        }
    }
}
