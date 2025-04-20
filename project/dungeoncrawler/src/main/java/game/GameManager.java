package game;

import java.util.ArrayList;
import java.util.List;

import game.ecs.Entity;
import game.ecs.System;

public class GameManager {
    private static GameManager instance;
    private List<Entity> entities;
    private List<System> systems;
    private int score;

    private GameManager() {
        entities = new ArrayList<>();
        systems = new ArrayList<>();
        score = 0;
    }

    public static GameManager getInstance() {
        if (instance == null) {
            instance = new GameManager();
        }
        return instance;
    }

    public int getScore() {
        return score;
    }

    public void addEntity(Entity entity) {
        entities.add(entity);
    }

    public void removeEntity(Entity entity) {
        entities.remove(entity);
    }

    public void addSystem(System system) {
        systems.add(system);
    }

    public void gameLoop() {
        while (true) {
            for (System system : systems) {
                system.update(entities);
            }
            // Controleer spelstatus, botsingen, enz.
        }
    }
}