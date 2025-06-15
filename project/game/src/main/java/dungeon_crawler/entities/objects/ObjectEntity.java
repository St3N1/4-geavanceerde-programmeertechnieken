package dungeon_crawler.entities.objects;

import java.util.List;

import dungeon_crawler.components.CollidableComponent;
import dungeon_crawler.components.LootComponent;
import dungeon_crawler.components.PositionComponent;
import dungeon_crawler.components.RenderComponent;
import dungeon_crawler.entities.Entity;
import dungeon_crawler.entities.objects.loot.Loot;

public class ObjectEntity extends Entity {
	protected String sprite;
	protected int spriteX;
	protected int spriteY;
	protected int spriteW;
	protected int spriteH;
	protected double scale;

	protected ObjectEntity(int x, int y, int spriteX, int spriteY, int spriteW, int spriteH, String sprite,
			double scale) {
		this.sprite = sprite;
		this.spriteX = spriteX;
		this.spriteY = spriteY;
		this.spriteW = spriteW;
		this.spriteH = spriteH;
		this.scale = scale;

		this.addComponent(new RenderComponent(sprite, spriteX, spriteY, spriteW, spriteH, scale));
		this.addComponent(new PositionComponent(x, y, spriteW * scale, spriteH * scale));
	}

	protected void isCollidable() {
		this.addComponent(new CollidableComponent());
	}

	protected void containsLoot(List<Loot> loot) {
		this.addComponent(new LootComponent(loot));
	}

	public int getWidth() {
		return spriteW;
	}

	public int getHeigth() {
		return spriteH;
	}

	public double getScale() {
		return scale;
	}
}
