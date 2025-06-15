package dungeon_crawler.entities.factory;

import dungeon_crawler.entities.Entity;
import dungeon_crawler.entities.attacks.Arrow;
import dungeon_crawler.entities.attacks.Slash;
import dungeon_crawler.enums.EntityAttackType;

public class EntityAttackFactory implements AbstractEntityFactory {
    @Override
    public Entity createEntity(Object type, Object... args) {
        switch ((EntityAttackType) type) {
            case SLASH:
                return new Slash((double) args[0], (double) args[1], (int) args[2], (int) args[3], (Entity) args[4]);
            case ARROW:
                return new Arrow((double) args[0], (double) args[1], (int) args[2]);
            default:
                throw new IllegalArgumentException("Unknown entity type: " + type);
        }
    }
}
