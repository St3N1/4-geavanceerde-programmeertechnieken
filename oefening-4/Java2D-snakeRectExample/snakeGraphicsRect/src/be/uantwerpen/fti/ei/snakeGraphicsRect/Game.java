package be.uantwerpen.fti.ei.snakeGraphicsRect;

public class Game {
    private GraphicsContext grCtx;
    private boolean isRunning;
    private boolean isPaused;
    private boolean foodHasSpawned;
    private Snake snake;
    private Input input;
    private FoodSpawner foodSpawner;
    private int GameCellsX = 20;
    private int GameCellsY = 20;

    public Game(GraphicsContext grCtx) {
        this.grCtx = grCtx;
        snake = new Snake(grCtx, GameCellsX, GameCellsY);
        input = new Input(grCtx);
        foodSpawner = new FoodSpawner(grCtx, GameCellsX, GameCellsY);
    }

    public void run() {
        grCtx.setGameDimensions(GameCellsX, GameCellsY);
        isRunning = true;
        isPaused = false;
        foodHasSpawned = false;
        while (isRunning) {
            if (input.inputAvailable()) {
                Input.Inputs direction = input.getInput();
                if (direction == Input.Inputs.SPACE)
                    isPaused = !isPaused;
                else
                    snake.setDirection(direction);
            }
            if (!isPaused) {
                snake.update();
                if (!foodHasSpawned)
                    foodHasSpawned = foodSpawner.generateFood();

                foodSpawner.draw();
                snake.draw();

                snake.checkSelfCollisions();
                foodHasSpawned = snake.checkFoodCollision(foodSpawner.getPosition());

                grCtx.render();
            }
            try { // fixed delay
                Thread.sleep(250);
            } catch (InterruptedException e) {
                System.out.println(e.getStackTrace());
            }
        }
    }

}
