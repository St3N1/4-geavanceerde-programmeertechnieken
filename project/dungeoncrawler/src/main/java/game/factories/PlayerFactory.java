package game.factories;

import game.ecs.Entity;
import game.ecs.components.HealthComponent;
import game.ecs.components.PositionComponent;

public class PlayerFactory implements EntityFactory {
    @Override
    public Entity createEntity() {
        Entity player = new Entity();
        player.addComponent(new PositionComponent(0, 0));
        player.addComponent(new HealthComponent(100));
        return player;
    }
}