package dungeon_crawler.entities.objects.map;

import dungeon_crawler.entities.objects.ObjectEntity;

public class HealingMushroom extends ObjectEntity {
	private static String sprite = "/sprites/fantasy_/cave_/cave_ [resources].png";
	private static int spriteX = 147;
	private static int spriteY = 18;
	private static int spriteW = 6;
	private static int spriteH = 10;
	private static double scale = 3;

	private int healAmount = 5;

	public HealingMushroom(int x, int y) {
		super(x, y, spriteX, spriteY, spriteW, spriteH, sprite, scale);
		super.isCollidable();
	}

	public int getHealAmount() {
		return healAmount;
	}
}
