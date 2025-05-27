package dungeon_crawler.entities.factory;

import dungeon_crawler.entities.Entity;
import dungeon_crawler.entities.objects.Chest;
import dungeon_crawler.enums.EntityType;

public class ItemEntityFactory implements AbstractEntityFactory {
    public Entity getEntity(EntityType entityType) {
        switch (entityType) {
            case CHEST:
                return new Chest();
            default:
                throw new IllegalArgumentException("Unknown entity type: " + entityType);
        }
    }
}
