package dungeon_crawler.entities.attacks;

import java.util.ArrayList;
import java.util.List;

import dungeon_crawler.components.AttackComponent;
import dungeon_crawler.components.CollidableComponent;
import dungeon_crawler.components.PositionComponent;
import dungeon_crawler.components.RenderComponent;
import dungeon_crawler.entities.Entity;

public class Slash extends AttackEntity {
	// sprites
	private List<String> frames = new ArrayList<>();
	private int spriteX = 0;
	private int spriteY = 0;
	private int spriteW = 128;
	private int spriteH = 128;
	private double scale = 0.4;
	private int currentFrame = 0;

	// animation
	private long animationTimer = 0;
	private long animationSpeed = 30;

	public Slash(double x, double y, int slashType, int slashColor, Entity owner) {
		this.setOwner(owner);
		int totalFrames = (slashType == 2) ? 7 : 9;

		for (int i = 1; i <= totalFrames; i++) {
			try {
				frames.add("/sprites/slashes/Slash " + slashType + "/color" + slashColor + "/Frames/Slash" + slashType
						+ "_color" + slashColor + "_frame"
						+ i + ".png");
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			}
		}

		this.addComponent(owner.getComponent(AttackComponent.class).get());
		this.addComponent(new RenderComponent(frames.get(0), spriteX, spriteY, spriteW, spriteH, scale));
		this.addComponent(new PositionComponent(x, y, spriteW * (scale / 1.2), spriteH * (scale / 1.2)));
		this.addComponent(new CollidableComponent());
	}

	public boolean updateAndCheckFinished(long deltaTime) {
		if (animationTimer == 0)
			animationTimer = deltaTime;

		if (deltaTime >= animationTimer + animationSpeed) {
			animationTimer = deltaTime;
			currentFrame++;
			if (currentFrame < frames.size()) {
				this.getComponent(RenderComponent.class).ifPresent(rc -> rc.setSprite(frames.get(currentFrame)));
			} else {
				return true;
			}
		}
		return false;
	}
}
