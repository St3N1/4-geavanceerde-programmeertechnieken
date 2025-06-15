package dungeon_crawler.entities.factory;

import dungeon_crawler.entities.Entity;
import dungeon_crawler.entities.objects.map.Chest;
import dungeon_crawler.entities.objects.map.EndGate;
import dungeon_crawler.entities.objects.map.Floor;
import dungeon_crawler.entities.objects.map.Gate;
import dungeon_crawler.entities.objects.map.HealingMushroom;
import dungeon_crawler.entities.objects.map.Wall;
import dungeon_crawler.enums.EntityObjectType;

public class EntityObjectFactory implements AbstractEntityFactory {
    public Entity createEntity(Object type, Object... args) {
        switch ((EntityObjectType) type) {
            case CHEST:
                return new Chest((int) args[0], (int) args[1]);
            case WALL:
                return new Wall((int) args[0], (int) args[1]);
            case FLOOR:
                return new Floor((int) args[0], (int) args[1]);
            case GATE:
                return new Gate((int) args[0], (int) args[1]);
            case END_GATE:
                return new EndGate((int) args[0], (int) args[1]);
            case MUSHROOM:
                return new HealingMushroom((int) args[0], (int) args[1]);
            default:
                throw new IllegalArgumentException("Unknown entity type: " + type);
        }
    }
}
