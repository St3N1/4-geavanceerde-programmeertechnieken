package dungeon_crawler.entities.interfaces;

import java.util.Optional;

import dungeon_crawler.components.Component;

public interface IEntity {
    public int getID();

    public void addComponent(Component component);

    public <T extends Component> Optional<T> getComponent(Class<T> componentType);
}