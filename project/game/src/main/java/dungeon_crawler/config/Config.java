package dungeon_crawler.config;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Config {
	public int screenWidth;
	public int screenHeight;
	public boolean showFps;
	public int targetFps;

	public Config() {
	}

	public static Config loadConfig() {
		try {
			ObjectMapper mapper = new ObjectMapper();
			return mapper.readValue(Config.class.getResourceAsStream("config.json"), Config.class);
		} catch (Exception e) {
			e.printStackTrace();
			Config fallback = new Config();
			fallback.screenWidth = 800;
			fallback.screenHeight = 800;
			fallback.showFps = true;
			fallback.targetFps = 60;
			return fallback;
		}
	}
}
