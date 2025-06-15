package dungeon_crawler.systems;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import dungeon_crawler.Game;
import dungeon_crawler.entities.Entity;

public class EntitySystem {
	public void update(List<Entity> entities) {
		Game game = Game.getInstance();
		List<Entity> toRemove = new ArrayList<>();
		List<Entity> toAdd = game.getNewEntitiesList();

		Iterator<Entity> iterator = entities.iterator();
		while (iterator.hasNext()) {
			Entity entity = iterator.next();
			if (entity.getToRemove())
				toRemove.add(entity);
		}
		entities.removeAll(toRemove);
		entities.addAll(toAdd);

		game.clearNewEntitiesList();
	}
}
