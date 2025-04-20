package be.uantwerpen.fti.ei.snakeGraphicsExample;

public class Game {
    private GraphicsContext grCtx;
    private boolean isRunning;
    private boolean isPaused;
    private Snake snake;
    private Input input;
    private int GameCellsX = 20;
    private int GameCellsY = 20;

    public Game(GraphicsContext grCtx) {
        this.grCtx = grCtx;
        snake = new Snake(grCtx);
        input = new Input(grCtx);
    }

    public void run() {
        grCtx.setGameDimensions(GameCellsX, GameCellsY);
        isRunning = true;
        isPaused = false;
        while (isRunning) {
            if (input.inputAvailable()) {
                Input.Inputs direction = input.getInput();
                if (direction == Input.Inputs.SPACE)
                    isPaused = ! isPaused;
                else
                    snake.setDirection(direction);
            }
            if (!isPaused) {
                snake.update();
                snake.draw();
                grCtx.render();
            }
            try {   // fixed delay
                Thread.sleep(200);
            } catch (InterruptedException e) {
                System.out.println(e.getStackTrace());
            }
        }
    }

}
