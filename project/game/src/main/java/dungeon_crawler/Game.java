package dungeon_crawler;

import java.util.ArrayList;
import java.util.List;

import dungeon_crawler.components.HealthComponent;
import dungeon_crawler.components.PositionComponent;
import dungeon_crawler.config.Config;
import dungeon_crawler.entities.Entity;
import dungeon_crawler.entities.factory.EntityAttackFactory;
import dungeon_crawler.entities.factory.EntityEnemyFactory;
import dungeon_crawler.entities.factory.EntityFactoryProvider;
import dungeon_crawler.entities.factory.EntityObjectFactory;
import dungeon_crawler.entities.factory.EntityPlayerFactory;
import dungeon_crawler.entities.players.PlayerEntity;
import dungeon_crawler.enums.EntityFactoryType;
import dungeon_crawler.enums.EntityPlayerType;
import dungeon_crawler.systems.AnimationSystem;
import dungeon_crawler.systems.CollisionSystem;
import dungeon_crawler.systems.EnemyEntitySystem;
import dungeon_crawler.systems.EntitySystem;
import dungeon_crawler.systems.MovementSystem;
import dungeon_crawler.systems.ProjectileSystem;
import dungeon_crawler.systems.RenderSystem;

public class Game {
    // Config
    private final Config config;
    private final int SCREEN_HEIGHT;
    private final int SCREEN_WIDTH;
    private final boolean SHOW_FPS;
    private final int TARGET_FPS;
    private final long FRAME_TIME;

    // Game
    private static Game instance;
    private GraphicsContext graphicsContext;

    // Factories
    private EntityPlayerFactory entityPlayerFactory;
    private EntityEnemyFactory entityEnemyFactory;
    private EntityAttackFactory entityAttackFactory;
    private EntityObjectFactory entityObjectFactory;

    // Systems
    private MovementSystem movementSystem;
    private CollisionSystem collisionSystem;
    private ProjectileSystem projectileSystem;
    private RenderSystem renderSystem;
    private AnimationSystem animationSystem;
    private EnemyEntitySystem enemyEntitySystem;
    private EntitySystem entitySystem;

    // Stopwatch
    private Stopwatch stopwatch;

    // Input
    private Input input;

    // Entities
    private List<Entity> entities;
    private List<Entity> newEntities;

    // Map
    private Map map;
    private String startMap = "/maps/map1.json";
    private final int MAP_OFFSET = 40;

    private boolean running = true;

    private Game() {
        // Config
        config = Config.loadConfig();
        SCREEN_HEIGHT = config.screenHeight;
        SCREEN_WIDTH = config.screenWidth;
        SHOW_FPS = config.showFps;
        TARGET_FPS = config.targetFps;
        FRAME_TIME = 1000 / TARGET_FPS;

        // Graphics
        graphicsContext = new GraphicsContext();

        // Factories
        entityPlayerFactory = (EntityPlayerFactory) EntityFactoryProvider.getFactory(EntityFactoryType.PLAYER);
        entityEnemyFactory = (EntityEnemyFactory) EntityFactoryProvider.getFactory(EntityFactoryType.ENEMY);
        entityAttackFactory = (EntityAttackFactory) EntityFactoryProvider.getFactory(EntityFactoryType.ATTACK);
        entityObjectFactory = (EntityObjectFactory) EntityFactoryProvider.getFactory(EntityFactoryType.OBJECT);

        // Systems
        movementSystem = new MovementSystem();
        collisionSystem = new CollisionSystem();
        projectileSystem = new ProjectileSystem();
        renderSystem = new RenderSystem(graphicsContext);
        animationSystem = new AnimationSystem();
        enemyEntitySystem = new EnemyEntitySystem();
        entitySystem = new EntitySystem();

        // Stopwatch
        stopwatch = new Stopwatch();

        // Input
        input = new Input(graphicsContext);

        // Entities
        entities = new ArrayList<>();
        newEntities = new ArrayList<>();

        // Map
        map = new Map(MAP_OFFSET, entityObjectFactory, entityEnemyFactory);
    }

    public static Game getInstance() {
        if (instance == null) {
            synchronized (Game.class) {
                if (instance == null) {
                    instance = new Game();
                }
            }
        }
        return instance;
    }

    public void setRunning(boolean state) {
        running = state;
    }

    public Map getMap() {
        return map;
    }

    public void newEntity(Entity entity) {
        newEntities.add(entity);
    }

    public void clearNewEntitiesList() {
        newEntities.clear();
    }

    public List<Entity> getNewEntitiesList() {
        return newEntities;
    }

    public void addEntity(Entity entity) {
        entities.add(entity);
    }

    public void removeEntity(Entity entity) {
        entities.remove(entity);
    }

    public EntityAttackFactory getEntityAttackFactory() {
        return entityAttackFactory;
    }

    public PlayerEntity initPlayer(EntityPlayerType playerEntity, int[] playerStartPos) {
        PlayerEntity player = (PlayerEntity) entityPlayerFactory.createEntity(playerEntity);
        player.getComponent(PositionComponent.class).get().setX(playerStartPos[0]);
        player.getComponent(PositionComponent.class).get().setY(playerStartPos[1]);
        newEntity(player);
        return player;
    }

    public void run() {
        graphicsContext.setGameDimensions(SCREEN_HEIGHT, SCREEN_WIDTH);
        map.loadMap(startMap);
        int[] playerStartPos = map.getPlayerStartPos();
        PlayerEntity player = initPlayer(EntityPlayerType.ROGUE, playerStartPos);

        stopwatch.start();
        while (running) {
            long start = stopwatch.getElapsedTime();

            movementSystem.processPlayerInputs(player, input);
            entitySystem.update(entities);
            projectileSystem.update(entities, FRAME_TIME);
            movementSystem.update(player, entities, FRAME_TIME);
            enemyEntitySystem.update(player, entities, entityEnemyFactory, FRAME_TIME);
            collisionSystem.update(player, entities);
            animationSystem.update(entities, stopwatch);
            renderSystem.render(entities, SHOW_FPS, FRAME_TIME);

            if (player.getComponent(HealthComponent.class).get().getHealth() <= 0)
                running = false;

            long elapsed = stopwatch.getElapsedTime() - start;
            long sleepTime = FRAME_TIME - elapsed;
            if (sleepTime > 0) {
                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}