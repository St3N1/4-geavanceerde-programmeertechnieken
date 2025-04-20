package game.ecs;

import java.util.List;

public interface System {
    void update(List<Entity> entities);
}