package dungeon_crawler.entities.attacks;

import dungeon_crawler.components.CollidableComponent;
import dungeon_crawler.components.PositionComponent;
import dungeon_crawler.components.RenderComponent;

public class Arrow extends AttackEntity {
    private String sprite = "/sprites/entities/Arrows_pack.png";
    private int spriteX = 0;
    private int spriteY = 320;
    private int spriteW = 160;
    private int spriteH = 160;
    private double scale = 0.3;

    public Arrow(double x, double y, int arrowVelocity) {
        this.addComponent(new RenderComponent(sprite, spriteX, spriteY, spriteW, spriteH, scale));
        this.addComponent(new PositionComponent(x, y, 120 * scale, 20 * scale));
        this.addComponent(new CollidableComponent());
    }
}
