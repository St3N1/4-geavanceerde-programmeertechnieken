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
}
