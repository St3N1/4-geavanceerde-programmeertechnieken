package dungeon_crawler.entities.factory;

import dungeon_crawler.enums.EntityFactoryType;

public class EntityFactoryProvider {
    public static AbstractEntityFactory getFactory(EntityFactoryType entityFactoryType) {
        switch (entityFactoryType) {
            case PLAYER:
                return new EntityPlayerFactory();
            case ENEMY:
                return new EntityEnemyFactory();
            case ATTACK:
                return new EntityAttackFactory();
            case OBJECT:
                return new EntityObjectFactory();
            default:
                throw new IllegalArgumentException("Unknown entity factory type: " + entityFactoryType);
        }
    }
}
