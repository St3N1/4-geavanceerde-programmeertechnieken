package dungeon_crawler;

import java.io.InputStream;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Map {
	private ObjectMapper mapper;
	private int[][] grid;

	public Map() {
		this.mapper = new ObjectMapper();
	}

	public void loadFromJson(String path) {
		try {
			InputStream is = getClass().getResourceAsStream(path);
			Map map = mapper.readValue(is, Map.class);
			this.grid = map.grid;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void printGrid() {
		if (grid == null) {
			System.out.println("Grid is empty!");
			return;
		}
		for (int[] row : grid) {
			for (int cell : row) {
				System.out.print(cell + " ");
			}
			System.out.println();
		}
	}

	public int[][] getGrid() {
		return grid;
	}
}
