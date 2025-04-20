package game.utils;

public class Stopwatch {
    private long lastTick;

    public Stopwatch() {
        lastTick = System.currentTimeMillis();
    }

    public long getElapsedTime() {
        long currentTime = System.currentTimeMillis();
        long elapsedTime = currentTime - lastTick;
        lastTick = currentTime;
        return elapsedTime;
    }
}