package dungeon_crawler;

import java.awt.Point;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import dungeon_crawler.components.PositionComponent;
import dungeon_crawler.entities.Entity;
import dungeon_crawler.entities.factory.EntityEnemyFactory;
import dungeon_crawler.entities.factory.EntityObjectFactory;
import dungeon_crawler.entities.objects.loot.Key;
import dungeon_crawler.entities.objects.loot.Loot;
import dungeon_crawler.entities.objects.map.Chest;
import dungeon_crawler.entities.objects.map.Floor;
import dungeon_crawler.entities.objects.map.Gate;
import dungeon_crawler.entities.objects.map.Wall;
import dungeon_crawler.enums.EntityEnemyType;
import dungeon_crawler.enums.EntityObjectType;

public class Map {
	private int id;
	private int prevId = 0;
	private int[][] grid;
	private String[] gates;

	private EntityObjectFactory entityObjectFactory;
	private EntityEnemyFactory entityEnemyFactory;
	private int mapOffset;
	private double playerStartX = 0;
	private double playerStartY = 0;
	private List<Point> floorTiles;

	public Map(int mapoffset, EntityObjectFactory entityObjectFactory, EntityEnemyFactory entityEnemyFactory) {
		this.mapOffset = mapoffset;
		this.entityObjectFactory = entityObjectFactory;
		this.entityEnemyFactory = entityEnemyFactory;
	}

	public void loadMap(String Path) {
		loadFromJson(Path);
		init();
	}

	public int getId() {
		return id;
	}

	private void loadFromJson(String path) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			InputStream is = MapData.class.getResourceAsStream(path);
			MapData mapData = mapper.readValue(is, MapData.class);
			prevId = id;
			id = mapData.getId();
			grid = mapData.getGrid();
			gates = mapData.getGates();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Entity placeEnemy(EntityEnemyType enemyType, int posX, int posY) {
		Entity enemy = entityEnemyFactory.createEntity(enemyType);
		PositionComponent pos = enemy.getComponent(PositionComponent.class).get();
		pos.setX((double) posX);
		pos.setY((double) posY);
		return enemy;
	}

	private void init() {
		final int WALL_WIDTH = new Wall(0, 0).getWidth();
		final int WALL_HEIGTH = new Wall(0, 0).getHeigth();
		final double WALL_SCALE = new Wall(0, 0).getScale();

		final int FLOOR_WIDTH = new Floor(0, 0).getWidth();
		final int FLOOR_HEIGTH = new Floor(0, 0).getHeigth();
		final double FLOOR_SCALE = new Floor(0, 0).getScale();

		final int GATE_WIDTH = new Gate(0, 0).getWidth();
		final int GATE_HEIGTH = new Gate(0, 0).getHeigth();
		final double GATE_SCALE = new Gate(0, 0).getScale();

		this.floorTiles = new ArrayList<>();
		List<Entity> entitiesToAdd = new ArrayList<>();
		for (int y = 0; y < grid.length; y++) {
			for (int x = 0; x < grid[y].length; x++) {
				int posX = mapOffset + x * FLOOR_WIDTH * (int) FLOOR_SCALE;
				int posY = mapOffset + y * FLOOR_HEIGTH * (int) FLOOR_SCALE;
				Object object = entityObjectFactory.createEntity(EntityObjectType.FLOOR, posX, posY);
				switch (grid[y][x]) {
					case 0: { // floor
						floorTiles.add(new Point(posX, posY));
						break;
					}
					case 1: { // wall
						posX = mapOffset + x * WALL_WIDTH * (int) WALL_SCALE;
						posY = mapOffset + y * WALL_HEIGTH * (int) WALL_SCALE;
						object = entityObjectFactory.createEntity(EntityObjectType.WALL, posX, posY);
						break;
					}
					case 2: { // gate
						floorTiles.add(new Point(posX, posY));
						posX = mapOffset + x * GATE_WIDTH * (int) GATE_SCALE;
						posY = mapOffset + y * GATE_HEIGTH * (int) GATE_SCALE;
						entitiesToAdd.add(entityObjectFactory.createEntity(EntityObjectType.GATE, posX, posY));
						break;
					}
					case 3: { // chest
						floorTiles.add(new Point(posX, posY));
						Entity chest = entityObjectFactory.createEntity(EntityObjectType.CHEST, posX, posY);
						List<Loot> loot = new ArrayList<>();
						loot.add(new Key());
						((Chest) chest).addLoot(loot);
						entitiesToAdd.add(chest);
						break;
					}
					case 4: { // healing mushroom
						floorTiles.add(new Point(posX, posY));
						entitiesToAdd.add(entityObjectFactory.createEntity(EntityObjectType.MUSHROOM, posX, posY));
						break;
					}
					case 5: { // player
						floorTiles.add(new Point(posX, posY));
						if (prevId == 0) {
							playerStartX = mapOffset + x * FLOOR_WIDTH * FLOOR_SCALE;
							playerStartY = mapOffset + y * FLOOR_HEIGTH * FLOOR_SCALE;
						}
						break;
					}
					case 6: { // player
						floorTiles.add(new Point(posX, posY));
						if (prevId >= 1) {
							playerStartX = mapOffset + x * FLOOR_WIDTH * FLOOR_SCALE;
							playerStartY = mapOffset + y * FLOOR_HEIGTH * FLOOR_SCALE;
						}
						break;
					}
					case 7: { // end
						floorTiles.add(new Point(posX, posY));
						entitiesToAdd.add(entityObjectFactory.createEntity(EntityObjectType.END_GATE, posX, posY));
						break;
					}
					case 8: { // goblin
						floorTiles.add(new Point(posX, posY));
						entitiesToAdd.add(placeEnemy(EntityEnemyType.GOBLIN, posX, posY));
						break;
					}
					case 9: { // skeleton
						floorTiles.add(new Point(posX, posY));
						entitiesToAdd.add(placeEnemy(EntityEnemyType.SKELETON, posX, posY));
						break;
					}
				}
				if (object != null)
					Game.getInstance().newEntity((Entity) object);
			}
		}
		for (Entity e : entitiesToAdd)
			Game.getInstance().newEntity(e);
	}

	public void printGrid() {
		if (grid == null) {
			System.out.println("Grid is empty!");
			return;
		}
		for (int[] row : grid) {
			for (int cell : row)
				System.out.print(cell + "\t");
			System.out.println();
		}
	}

	public int[] getPlayerStartPos() {
		return new int[] { (int) playerStartX, (int) playerStartY };
	}

	public List<Point> getFloorTiles() {
		return floorTiles;
	}
}
