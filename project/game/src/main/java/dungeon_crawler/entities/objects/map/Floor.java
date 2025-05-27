package dungeon_crawler.entities.objects.map;

import dungeon_crawler.components.PositionComponent;
import dungeon_crawler.components.RenderComponent;
import dungeon_crawler.entities.objects.ObjectEntity;

public class Floor extends ObjectEntity {
	private String sprite = "/sprites/fantasy_/cave_/cave_.png";
	private int spriteX;
	private int spriteY;
	private static int spriteW = 8;
	private static int spriteH = 17;
	private static int scale = 4;

	public Floor(int x, int y) {
		int[] chances = { 1, 1, 1, 1, 2, 3, 4 };
		int lastFloor = -1;

		int randomFloor = chances[(int) (Math.random() * chances.length)];
		while (lastFloor != 1 && lastFloor == randomFloor) {
			randomFloor = chances[(int) (Math.random() * chances.length)];
		}

		if (randomFloor == 1) {
			this.spriteX = 172;
			this.spriteY = 24;
		} else if (randomFloor == 2) {
			this.spriteX = 132;
			this.spriteY = 16;
		} else if (randomFloor == 3) {
			this.spriteX = 162;
			this.spriteY = 34;
		} else if (randomFloor == 4) {
			this.spriteX = 162;
			this.spriteY = 50;
		}
		lastFloor = randomFloor;

		this.addComponent(new RenderComponent(sprite, spriteX, spriteY, spriteW, spriteH, scale));
		this.addComponent(new PositionComponent(x, y, spriteW * scale, spriteH * scale));
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
