package dungeon_crawler.entities.objects.map;

import dungeon_crawler.entities.objects.ObjectEntity;

public class Floor extends ObjectEntity {
	private static String sprite = "/sprites/fantasy_/cave_/cave_.png";
	private static int spriteX = 172;
	private static int spriteY = 24;
	private static int spriteW = 8;
	private static int spriteH = 16;
	private static double scale = 4;

	public Floor(int x, int y) {
		super(x, y, spriteX, spriteY, spriteW, spriteH, sprite, scale);

		int[] chances = { 1, 1, 1, 1, 2, 3, 4 };
		int lastFloor = -1;

		int randomFloor = chances[(int) (Math.random() * chances.length)];
		while (lastFloor != 1 && lastFloor == randomFloor)
			randomFloor = chances[(int) (Math.random() * chances.length)];

		if (randomFloor == 1) {
			spriteX = 172;
			spriteY = 24;
		} else if (randomFloor == 2) {
			spriteX = 132;
			spriteY = 16;
		} else if (randomFloor == 3) {
			spriteX = 162;
			spriteY = 34;
		} else if (randomFloor == 4) {
			spriteX = 162;
			spriteY = 50;
		}
		lastFloor = randomFloor;
	}
}
