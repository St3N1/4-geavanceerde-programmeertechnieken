package dungeon_crawler;

public class MapData {
	public int id;
	public int[][] grid;
	public String[] gates;

	public MapData() {
	}

	public int getId() {
		return id;
	}

	public int[][] getGrid() {
		return grid;
	}

	public String[] getGates() {
		return gates;
	}
}
