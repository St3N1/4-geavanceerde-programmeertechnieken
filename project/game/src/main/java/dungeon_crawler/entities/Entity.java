package dungeon_crawler.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import dungeon_crawler.components.Component;
import dungeon_crawler.entities.interfaces.IEntity;

public class Entity implements IEntity {
    private static int nextID = 0;
    private final int id;
    private final List<Component> components;
    private boolean toRemove = false;

    private boolean isHit = false;
    private long hitTimer = 0;
    private static final long HIT_DURATION = 100;

    public Entity() {
        this.id = nextID++;
        this.components = new ArrayList<>();
    }

    public int getID() {
        return id;
    }

    public void addComponent(Component component) {
        components.add(component);
    }

    public <T extends Component> Optional<T> getComponent(Class<T> componentType) {
        return components.stream()
                .filter(componentType::isInstance)
                .map(componentType::cast)
                .findFirst();
    }

    public boolean hasComponent(Class<? extends Component> componentType) {
        return components.stream().anyMatch(componentType::isInstance);
    }

    public void toRemove() {
        toRemove = true;
    }

    public boolean getToRemove() {
        return toRemove;
    }

    public void setHit() {
        isHit = true;
        hitTimer = System.currentTimeMillis();
    }

    public boolean isHit() {
        if (isHit && System.currentTimeMillis() - hitTimer > HIT_DURATION)
            isHit = false;
    
        return isHit;
    }
}
