package dungeon_crawler.components;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import dungeon_crawler.entities.objects.loot.Key;
import dungeon_crawler.entities.objects.loot.Loot;

public class LootComponent extends Component {
	private List<Loot> loot = new ArrayList<>();

	public LootComponent(List<Loot> loot) {
		this.loot = loot;
	}

	public boolean hasKey() {
		Iterator<Loot> iterator = loot.iterator();
		while (iterator.hasNext()) {
			Loot item = iterator.next();
			if (item instanceof Key)
				return true;
		}
		return false;
	}
}
