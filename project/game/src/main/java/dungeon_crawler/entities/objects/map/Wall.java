package dungeon_crawler.entities.objects.map;

import dungeon_crawler.components.CollidableComponent;
import dungeon_crawler.components.PositionComponent;
import dungeon_crawler.components.RenderComponent;
import dungeon_crawler.entities.objects.ObjectEntity;

public class Wall extends ObjectEntity {
	private String sprite = "/sprites/fantasy_/cave_/cave_ [fencesAndWalls].png";
	private int spriteX = 20;
	private int spriteY = 92;
	private static int spriteW = 8;
	private static int spriteH = 17;
	private static int scale = 4;

	public Wall(int x, int y) {

		this.addComponent(new RenderComponent(sprite, spriteX, spriteY, spriteW, spriteH, scale));
		this.addComponent(new PositionComponent(x, y, spriteW * scale, spriteH * scale));
		this.addComponent(new CollidableComponent());
	}

	public static int getWidth() {
		return spriteW;
	}

	public static int getHeigth() {
		return spriteH;
	}

	public static int getScale() {
		return scale;
	}
}
