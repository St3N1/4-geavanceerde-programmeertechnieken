package dungeon_crawler;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import dungeon_crawler.Input.Inputs;
import dungeon_crawler.components.MovementComponent;
import dungeon_crawler.components.PositionComponent;
import dungeon_crawler.config.Config;
import dungeon_crawler.entities.Entity;
import dungeon_crawler.entities.factory.EnemyEntityFactory;
import dungeon_crawler.entities.factory.EntityFactoryProvider;
import dungeon_crawler.entities.factory.ItemEntityFactory;
import dungeon_crawler.entities.factory.PlayerEntityFactory;
import dungeon_crawler.entities.factory.ProjectileEntityFactory;
import dungeon_crawler.entities.objects.map.Floor;
import dungeon_crawler.entities.objects.map.Gate;
import dungeon_crawler.entities.objects.map.Wall;
import dungeon_crawler.entities.players.PlayerEntity;
import dungeon_crawler.enums.EntityFactoryType;
import dungeon_crawler.enums.EntityType;
import dungeon_crawler.systems.CollisionSystem;
import dungeon_crawler.systems.MovementSystem;
import dungeon_crawler.systems.ProjectileSystem;
import dungeon_crawler.systems.RenderSystem;

public class Game {
    private static Game instance;
    private final Config config;
    private GraphicsContext graphicsContext;
    private PlayerEntityFactory playerEntityFactory;
    private EnemyEntityFactory enemyEntityFactory;
    private ProjectileEntityFactory projectileEntityFactory;
    private ItemEntityFactory itemEntityFactory;
    private MovementSystem movementSystem;
    private CollisionSystem collisionSystem;
    private ProjectileSystem projectileSystem;
    private RenderSystem renderSystem;
    private Stopwatch stopwatch;
    private Input input;
    private List<Entity> entities;
    private Map map;
    private final int MAP_OFFSET = 40;
    private int score = 0;

    private Game() {
        // Graphics
        this.graphicsContext = new GraphicsContext();

        // Config
        this.config = Config.loadConfig();

        // Factories
        this.playerEntityFactory = (PlayerEntityFactory) EntityFactoryProvider.getFactory(EntityFactoryType.PLAYER);
        this.enemyEntityFactory = (EnemyEntityFactory) EntityFactoryProvider.getFactory(EntityFactoryType.ENEMY);
        this.projectileEntityFactory = (ProjectileEntityFactory) EntityFactoryProvider
                .getFactory(EntityFactoryType.PROJECTILE);
        this.itemEntityFactory = (ItemEntityFactory) EntityFactoryProvider.getFactory(EntityFactoryType.ITEM);

        // Systems
        this.movementSystem = new MovementSystem();
        this.collisionSystem = new CollisionSystem();
        this.projectileSystem = new ProjectileSystem(projectileEntityFactory);
        this.renderSystem = new RenderSystem(graphicsContext);

        // Stopwatch
        this.stopwatch = new Stopwatch();

        // Input
        this.input = new Input(graphicsContext);

        // Entities
        this.entities = new ArrayList<>();

        // Map
        this.map = new Map();
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

    private void addEntity(Entity entity) {
        entities.add(entity);
    }

    public PlayerEntity getPlayer() {
        for (Entity entity : entities) {
            if (entity instanceof PlayerEntity) {
                return (PlayerEntity) entity;
            }
        }
        return null;
    }

    private void initPlayer(EntityType playerEntity, int[] playerStartPos) {
        PlayerEntity player = (PlayerEntity) playerEntityFactory.getEntity(playerEntity);
        player.getComponent(PositionComponent.class).get().setX(playerStartPos[0]);
        player.getComponent(PositionComponent.class).get().setY(playerStartPos[1]);
        addEntity(player);
    }

    private int[] initMap() {
        final int WALL_WIDTH = Wall.getWidth();
        final int WALL_HEIGTH = Wall.getHeigth();
        final int WALL_SCALE = Wall.getScale();

        final int FLOOR_WIDTH = Floor.getWidth();
        final int FLOOR_HEIGTH = Floor.getHeigth();
        final int FLOOR_SCALE = Floor.getScale();

        map.loadFromJson("/maps/map1.json");
        // map.printGrid();

        int[][] grid = map.getGrid();
        int playerStartX = MAP_OFFSET + grid.length * WALL_WIDTH;
        int playerStartY = MAP_OFFSET + grid[0].length * WALL_HEIGTH;

        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[y].length; x++) {
                Object object = null;
                switch (grid[y][x]) {
                    case 0 -> object = new Floor(MAP_OFFSET + x * FLOOR_WIDTH * FLOOR_SCALE,
                            MAP_OFFSET + y * FLOOR_HEIGTH * FLOOR_SCALE);
                    case 1 -> object = new Wall(MAP_OFFSET + x * WALL_WIDTH * WALL_SCALE,
                            MAP_OFFSET + y * WALL_HEIGTH * WALL_SCALE);
                    case 2 -> object = new Gate();
                }

                if (object != null)
                    addEntity((Entity) object);
            }
        }
        return new int[] { playerStartX, playerStartY };
    }

    private void setMovement(Entity entity, int x, int y) {
        entity.getComponent(MovementComponent.class).ifPresent(movement -> {
            movement.setDirection(x, y);
        });
    }

    private void processPlayerInputs(PlayerEntity player) {
        Set<Inputs> inputs = input.getActiveInputs();

        boolean left = inputs.contains(Inputs.LEFT);
        boolean right = inputs.contains(Inputs.RIGHT);
        boolean up = inputs.contains(Inputs.UP);
        boolean down = inputs.contains(Inputs.DOWN);
        boolean attack = inputs.contains(Inputs.SPACE);

        final int x = left && !right ? -1 : right && !left ? 1 : 0;
        final int y = up && !down ? -1 : down && !up ? 1 : 0;

        setMovement(player, x, y);

        if (attack)
            player.attack();
    }

    public void run() {
        final int SCREEN_HEIGHT = config.screenHeight;
        final int SCREEN_WIDTH = config.screenWidth;
        final boolean SHOW_FPS = config.showFps;
        final int TARGET_FPS = config.targetFps;
        final long FRAME_TIME = 1000 / TARGET_FPS;

        graphicsContext.setGameDimensions(SCREEN_HEIGHT, SCREEN_WIDTH);
        int[] playerStartPos = initMap();
        initPlayer(EntityType.ROGUE, playerStartPos);

        addEntity(enemyEntityFactory.getEntity(EntityType.SKELETON));

        long frameTimeEnd = FRAME_TIME;
        while (true) {
            stopwatch.start();

            processPlayerInputs(getPlayer());
            movementSystem.update(entities, frameTimeEnd);
            collisionSystem.update(getPlayer(), entities);
            projectileSystem.update(entities, frameTimeEnd);
            renderSystem.render(entities, SHOW_FPS, frameTimeEnd, score);

            long frameTime = stopwatch.getElapsedTime();
            long sleepTime = FRAME_TIME - frameTime;
            if (sleepTime <= 0)
                sleepTime = FRAME_TIME;

            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            frameTimeEnd = stopwatch.getElapsedTime();
            stopwatch.stop();
        }
    }
}
