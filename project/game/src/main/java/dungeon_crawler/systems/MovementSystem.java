package dungeon_crawler.systems;

import java.util.List;
import java.util.Set;

import dungeon_crawler.Input;
import dungeon_crawler.Input.Inputs;
import dungeon_crawler.components.MovementComponent;
import dungeon_crawler.components.PositionComponent;
import dungeon_crawler.components.VelocityComponent;
import dungeon_crawler.entities.Entity;
import dungeon_crawler.entities.players.PlayerEntity;

public class MovementSystem {
    public void update(PlayerEntity player, List<Entity> entities, long deltaTime) {
        double stepTime = deltaTime / 1000.0;

        player.getComponent(MovementComponent.class).ifPresent(mov -> {
            player.getComponent(PositionComponent.class).ifPresent(pos -> {
                player.getComponent(VelocityComponent.class).ifPresent(vel -> {
                    pos.setX(pos.getX() + vel.getDx() * mov.getX() * stepTime);
                    pos.setY(pos.getY() + vel.getDy() * mov.getY() * stepTime);
                });
            });
        });
    }

    public void processPlayerInputs(PlayerEntity player, Input input) {
        Set<Inputs> inputs = input.getActiveInputs();

        boolean left = inputs.contains(Inputs.LEFT);
        boolean right = inputs.contains(Inputs.RIGHT);
        boolean up = inputs.contains(Inputs.UP);
        boolean down = inputs.contains(Inputs.DOWN);
        boolean attack = inputs.contains(Inputs.SPACE);

        final int x = left && !right ? -1 : right && !left ? 1 : 0;
        final int y = up && !down ? -1 : down && !up ? 1 : 0;

        MovementComponent movement = player.getComponent(MovementComponent.class).get();
        movement.setDirection(x, y);

        if (attack)
            player.attack();
    }
}
