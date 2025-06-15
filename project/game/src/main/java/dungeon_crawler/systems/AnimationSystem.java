package dungeon_crawler.systems;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import dungeon_crawler.Game;
import dungeon_crawler.Stopwatch;
import dungeon_crawler.entities.Entity;
import dungeon_crawler.entities.attacks.Slash;

public class AnimationSystem {
	public void update(List<Entity> entities, Stopwatch stopwatch) {
		Iterator<Entity> iterator = entities.iterator();
		while (iterator.hasNext()) {
			Entity entity = iterator.next();
			if (entity instanceof Slash slash) {
				boolean finished = slash.updateAndCheckFinished(stopwatch.getElapsedTime());
				if (finished)
					slash.toRemove();
			} else
				continue;
		}
	}
}
