package dungeon_crawler.systems;

import java.awt.Point;
import java.util.Iterator;
import java.util.List;

import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.jse.CoerceJavaToLua;
import org.luaj.vm2.lib.jse.JsePlatform;

import dungeon_crawler.Game;
import dungeon_crawler.components.HealthComponent;
import dungeon_crawler.components.PositionComponent;
import dungeon_crawler.entities.Entity;
import dungeon_crawler.entities.enemies.EnemyEntity;
import dungeon_crawler.entities.enemies.Goblin;
import dungeon_crawler.entities.enemies.Skeleton;
import dungeon_crawler.entities.factory.EntityEnemyFactory;
import dungeon_crawler.entities.players.PlayerEntity;
import dungeon_crawler.enums.EntityEnemyType;

public class EnemyEntitySystem {
	private void runSript(Entity entity, PlayerEntity player, double stepTime) {
		String path = "";
		if (entity instanceof Goblin)
			path = "/scripts/GoblinAI.lua";
		else if (entity instanceof Skeleton)
			path = "/scripts/SkeletonAI.lua";

		LuaValue globals = JsePlatform.standardGlobals();
		globals.get("dofile").call(LuaValue.valueOf(path));
		LuaValue updateFunc = globals.get("update");
		if (!updateFunc.isnil()) {
			LuaValue _entity = CoerceJavaToLua.coerce(entity);
			LuaValue _player = CoerceJavaToLua.coerce(player);
			LuaValue _stepTime = CoerceJavaToLua.coerce(stepTime);
			updateFunc.call(_entity, _player, _stepTime);
		}
	}

	public void update(PlayerEntity player, List<Entity> entities, EntityEnemyFactory enemyEntityFactory,
			long deltaTime) {
		double stepTime = deltaTime / 1000.0;

		Iterator<Entity> iterator = entities.iterator();
		while (iterator.hasNext()) {
			Entity entity = iterator.next();
			if (entity instanceof EnemyEntity) {
				entity.getComponent(HealthComponent.class).ifPresent(hc -> {
					if (hc.isDead()) {
						player.addScore();

						EnemyEntity newEnemy = null;
						if (entity instanceof Skeleton)
							newEnemy = (EnemyEntity) enemyEntityFactory.createEntity(EntityEnemyType.SKELETON);
						else if (entity instanceof Goblin)
							newEnemy = (EnemyEntity) enemyEntityFactory.createEntity(EntityEnemyType.GOBLIN);

						entity.toRemove();

						List<Point> floorTiles = Game.getInstance().getMap().getFloorTiles();
						if (!floorTiles.isEmpty()) {
							Point spawnTile = floorTiles.get((int) (Math.random() * floorTiles.size()));
							newEnemy.getComponent(PositionComponent.class).ifPresent(pos -> {
								pos.setX(spawnTile.x);
								pos.setY(spawnTile.y);
							});
						}

						Game.getInstance().newEntity(newEnemy);
					}
				});
				// Hier kun je AI, beweging, aanvallen, enz. toevoegen

				runSript(entity, player, stepTime);
			}
		}
	}
}
