package dungeon_crawler.entities.factory;

import dungeon_crawler.entities.Entity;
import dungeon_crawler.enums.EntityType;

public abstract interface AbstractEntityFactory {
    public abstract Entity getEntity(EntityType entityType);
}
