package dungeon_crawler.systems;

import java.util.Iterator;
import java.util.List;

import dungeon_crawler.Game;
import dungeon_crawler.Map;
import dungeon_crawler.components.AttackComponent;
import dungeon_crawler.components.CollidableComponent;
import dungeon_crawler.components.HealthComponent;
import dungeon_crawler.components.PositionComponent;
import dungeon_crawler.entities.Entity;
import dungeon_crawler.entities.attacks.Arrow;
import dungeon_crawler.entities.attacks.AttackEntity;
import dungeon_crawler.entities.attacks.Slash;
import dungeon_crawler.entities.enemies.EnemyEntity;
import dungeon_crawler.entities.objects.ObjectEntity;
import dungeon_crawler.entities.objects.map.Chest;
import dungeon_crawler.entities.objects.map.EndGate;
import dungeon_crawler.entities.objects.map.Floor;
import dungeon_crawler.entities.objects.map.Gate;
import dungeon_crawler.entities.objects.map.HealingMushroom;
import dungeon_crawler.entities.objects.map.Wall;
import dungeon_crawler.entities.players.PlayerEntity;

public class CollisionSystem {
	private List<Entity> entities;
	private PlayerEntity player;
	private PositionComponent playerPos;

	private void checkObjectCollision(ObjectEntity object) {
		PositionComponent objectPos = object.getComponent(PositionComponent.class).get();
		if (object instanceof Gate) {
			if (playerPos.overlaps(objectPos)) {
				for (Entity e : entities)
					if (!(e instanceof PlayerEntity))
						e.toRemove();

				Map map = Game.getInstance().getMap();
				if (map.getId() == 1)
					map.loadMap("/maps/map2.json");
				else if (map.getId() == 2)
					map.loadMap("/maps/map1.json");
				int[] playerPos = map.getPlayerStartPos();
				player.getComponent(PositionComponent.class).get().setX(playerPos[0]);
				player.getComponent(PositionComponent.class).get().setY(playerPos[1]);
			}
		}

		if (object instanceof HealingMushroom) {
			if (playerPos.overlaps(objectPos)) {
				object.toRemove();
				player.getComponent(HealthComponent.class).get().heal(((HealingMushroom) object).getHealAmount());
			}
		}

		if (object instanceof Chest) {
			if (playerPos.overlaps(objectPos)) {
				boolean hasKey = ((Chest) object).open();
				if (hasKey)
					player.setKey(hasKey);
			}
		}

		if (object instanceof EndGate) {
			if (playerPos.overlaps(objectPos))
				((EndGate) object).playerHasKey(player.getKey());
		}

		if (playerPos.overlaps(objectPos)) {
			playerPos.restorePreviousX();
			double originalX = playerPos.getX();
			playerPos.setX(playerPos.getCurrentX());

			if (playerPos.overlaps(objectPos))
				playerPos.setX(originalX);

			playerPos.restorePreviousY();
			double originalY = playerPos.getY();
			playerPos.setY(playerPos.getCurrentY());

			if (playerPos.overlaps(objectPos))
				playerPos.setY(originalY);
		}

		Iterator<Entity> enemyIterator = entities.iterator();
		while (enemyIterator.hasNext()) {
			Entity enemy = enemyIterator.next();

			PositionComponent enemyPos = enemy.getComponent(PositionComponent.class).get();
			if (enemyPos.overlaps(objectPos)) {
				if (enemyPos.overlapsX(objectPos))
					enemyPos.restorePreviousX();

				if (enemyPos.overlapsY(objectPos))
					enemyPos.restorePreviousY();
			}
		}
	}

	private void checkEnemyCollision(EnemyEntity enemy) {
		PositionComponent enemyPos = enemy.getComponent(PositionComponent.class).get();
		if (playerPos.overlaps(enemyPos)) {
			playerPos.restorePreviousX();
			playerPos.restorePreviousY();
			if (enemyPos.getCurrentX() != 0)
				enemyPos.restorePreviousX();
			if (enemyPos.getCurrentY() != 0)
				enemyPos.restorePreviousY();
		}
	}

	private void checkAttackCollision(AttackEntity attack) {
		Iterator<Entity> enemyIterator = entities.iterator();
		while (enemyIterator.hasNext()) {
			Entity target = enemyIterator.next();
			if (!(target instanceof PlayerEntity || target instanceof EnemyEntity || target instanceof Wall))
				continue;

			if (attack instanceof Slash) {
				Entity owner = ((Slash) attack).getOwner();
				if (owner instanceof PlayerEntity && target instanceof EnemyEntity)
					checkIfattackHit(attack, target);
				else if (owner instanceof EnemyEntity && target instanceof PlayerEntity)
					checkIfattackHit(attack, target);
			} else if (attack instanceof Arrow) {
				if (target instanceof PlayerEntity)
					checkIfattackHit(attack, target);
				else if (target instanceof Wall) {
					PositionComponent wallPC = target.getComponent(PositionComponent.class).get();
					PositionComponent attackPC = attack.getComponent(PositionComponent.class).orElse(null);
					if (attackPC.overlaps(wallPC))
						attack.toRemove();
				}
			}
		}
	}

	private void checkIfattackHit(AttackEntity attack, Entity target) {
		PositionComponent attackPC = attack.getComponent(PositionComponent.class).orElse(null);
		if (!attack.hasHitTarget(target)) {
			PositionComponent targetPC = target.getComponent(PositionComponent.class).get();
			if (attackPC.overlaps(targetPC)) {
				if (target instanceof Wall) {
					attack.toRemove();
					return;
				}
				target.setHit();
				HealthComponent targetHealth = target.getComponent(HealthComponent.class).get();
				AttackComponent attackComponent = attack.getComponent(AttackComponent.class).get();
				targetHealth.damage(attackComponent.getDamage());
				attack.addHitTarget(target);
				if (attack instanceof Arrow)
					attack.toRemove();
			}
		}
	}

	public void update(PlayerEntity player, List<Entity> entities) {
		this.entities = entities;
		this.player = player;
		this.playerPos = player.getComponent(PositionComponent.class).orElse(null);

		Iterator<Entity> iterator = entities.iterator();
		while (iterator.hasNext()) {
			Entity entity = iterator.next();
			if (entity == player)
				continue;

			if (!entity.hasComponent(CollidableComponent.class))
				continue;

			PositionComponent entityPos = entity.getComponent(PositionComponent.class).orElse(null);
			if (entityPos == null)
				continue;

			if (entity instanceof AttackEntity)
				checkAttackCollision((AttackEntity) entity);

			if (entity instanceof ObjectEntity)
				checkObjectCollision((ObjectEntity) entity);

			if (entity instanceof EnemyEntity)
				checkEnemyCollision((EnemyEntity) entity);

		}
	}
}
