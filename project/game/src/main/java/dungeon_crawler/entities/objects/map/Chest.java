package dungeon_crawler.entities.objects.map;

import java.util.List;

import dungeon_crawler.components.LootComponent;
import dungeon_crawler.components.RenderComponent;
import dungeon_crawler.entities.objects.ObjectEntity;
import dungeon_crawler.entities.objects.loot.Loot;

public class Chest extends ObjectEntity {
    private static String sprite = "/sprites/fantasy_/cave_/cave_ [resources].png";
    private static int spriteXClosed = 18;
    private static int spriteYClosed = 94;
    private static int spriteWClosed = 12;
    private static int spriteHClosed = 14;
    private static int spriteXOpen = 90;
    private static int spriteYOpen = 34;
    private static int spriteWOpen = 12;
    private static int spriteHOpen = 20;
    private static double scale = 2.666666666666667;

    public Chest(int x, int y) {
        super(x, y, spriteXClosed, spriteYClosed, spriteWClosed, spriteHClosed, sprite, scale);
        super.isCollidable();
    }

    public void addLoot(List<Loot> loot) {
        super.containsLoot(loot);
    }

    public boolean open() {
        RenderComponent rc = this.getComponent(RenderComponent.class).get();
        rc.setSpriteX(spriteXOpen);
        rc.setSpriteX(spriteYOpen);
        rc.setSpriteW(spriteWOpen);
        rc.setSpriteH(spriteHOpen);

        return this.getComponent(LootComponent.class).get().hasKey();
    }
}
