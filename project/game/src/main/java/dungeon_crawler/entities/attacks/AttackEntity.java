package dungeon_crawler.entities.attacks;

import java.util.HashSet;
import java.util.Set;

import dungeon_crawler.entities.Entity;

public class AttackEntity extends Entity {
	private Set<Entity> hasHitTarget = new HashSet<>();
	private Entity owner;

	public void setOwner(Entity owner) {
		this.owner = owner;
	}

	public Entity getOwner() {
		return owner;
	}

	public boolean hasHitTarget(Entity entity) {
		return hasHitTarget.contains(entity) ? true : false;
	}

	public void addHitTarget(Entity target) {
		hasHitTarget.add(target);
	}
}
