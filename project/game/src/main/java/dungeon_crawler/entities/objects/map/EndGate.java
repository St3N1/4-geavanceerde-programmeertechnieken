package dungeon_crawler.entities.objects.map;

import dungeon_crawler.Game;
import dungeon_crawler.entities.objects.ObjectEntity;

public class EndGate extends ObjectEntity {
	private static String sprite = "/sprites/fantasy_/cave_/cave_ [fencesAndWalls].png";
	private static int spriteX = 91;
	private static int spriteY = 24;
	private static int spriteW = 8;
	private static int spriteH = 16;
	private static double scale = 4;

	public EndGate(int x, int y) {
		super(x, y, spriteX, spriteY, spriteW, spriteH, sprite, scale);
		super.isCollidable();
	}

	public void playerHasKey(boolean hasKey) {
		Game.getInstance().setRunning(!hasKey);
	}
}
