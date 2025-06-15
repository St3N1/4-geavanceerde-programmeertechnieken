package dungeon_crawler.entities.objects.map;

import dungeon_crawler.entities.objects.ObjectEntity;

public class Wall extends ObjectEntity {
	private static String sprite = "/sprites/fantasy_/cave_/cave_ [fencesAndWalls].png";
	private static int spriteX = 20;
	private static int spriteY = 92;
	private static int spriteW = 8;
	private static int spriteH = 16;
	private static double scale = 4;

	public Wall(int x, int y) {
		super(x, y, spriteX, spriteY, spriteW, spriteH, sprite, scale);
		super.isCollidable();
	}
}
