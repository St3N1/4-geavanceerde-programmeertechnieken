package dungeon_crawler.entities.factory;

import dungeon_crawler.entities.Entity;

public interface AbstractEntityFactory {
    Entity createEntity(Object type, Object... args);
}
