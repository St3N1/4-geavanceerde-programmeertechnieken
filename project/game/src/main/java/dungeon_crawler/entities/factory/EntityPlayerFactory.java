package dungeon_crawler.entities.factory;

import dungeon_crawler.entities.Entity;
import dungeon_crawler.entities.players.Rogue;
import dungeon_crawler.entities.players.Warrior;
import dungeon_crawler.enums.EntityPlayerType;

public class EntityPlayerFactory implements AbstractEntityFactory {
    public Entity createEntity(Object type, Object... args) {
        switch ((EntityPlayerType) type) {
            case WARRIOR:
                return new Warrior();
            case ROGUE:
                return new Rogue();
            default:
                throw new IllegalArgumentException("Unknown entity type: " + type);
        }
    }
}
