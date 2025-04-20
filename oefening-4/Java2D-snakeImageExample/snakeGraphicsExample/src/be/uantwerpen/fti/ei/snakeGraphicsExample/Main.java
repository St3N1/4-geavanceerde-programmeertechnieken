package be.uantwerpen.fti.ei.snakeGraphicsExample;

public class Main {

    public static void main(String[] args) {
        GraphicsContext gr = new GraphicsContext();
        Game game = new Game(gr);
        game.run();
    }
}

