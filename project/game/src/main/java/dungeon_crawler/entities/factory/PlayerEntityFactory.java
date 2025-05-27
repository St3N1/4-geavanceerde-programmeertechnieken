package dungeon_crawler.entities.factory;

import dungeon_crawler.entities.Entity;
import dungeon_crawler.entities.players.Rogue;
import dungeon_crawler.entities.players.Warrior;
import dungeon_crawler.enums.EntityType;

public class PlayerEntityFactory implements AbstractEntityFactory {
    public Entity getEntity(EntityType entityType) {
        switch (entityType) {
            case WARRIOR:
                return new Warrior();
            case ROGUE:
                return new Rogue();
            default:
                throw new IllegalArgumentException("Unknown entity type: " + entityType);
        }
    }
}
