package dungeon_crawler.entities.projectiles;

import dungeon_crawler.components.AttackComponent;
import dungeon_crawler.components.PositionComponent;
import dungeon_crawler.components.VelocityComponent;
import dungeon_crawler.entities.Entity;

public class Arrow extends Entity {
    public Arrow() {

    }

    public void setPosition(double x, double y) {
        this.addComponent(new PositionComponent(x, y, 10, 10));
    }

    public void setVelocity(double dirX, double dirY) {
        this.addComponent(new VelocityComponent(dirX, dirY));

    }

    public void setDamage(int damage) {
        this.addComponent(new AttackComponent(damage, 0));
    }
}
