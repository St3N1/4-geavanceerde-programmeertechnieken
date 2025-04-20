package be.uantwerpen.fti.ei.snakeGraphicsRect;

public class Main {

    public static void main(String[] args) {
        GraphicsContext gr = new GraphicsContext();
        Game game = new Game(gr);
        game.run();
    }
}

