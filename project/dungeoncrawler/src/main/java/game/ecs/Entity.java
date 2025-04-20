package game.ecs;

import java.util.HashMap;
import java.util.Map;

public class Entity {
    private Map<Class<?>, Component> components = new HashMap<>();

    public void addComponent(Component component) {
        components.put(component.getClass(), component);
    }

    public <T extends Component> T getComponent(Class<T> componentClass) {
        return componentClass.cast(components.get(componentClass));
    }
}