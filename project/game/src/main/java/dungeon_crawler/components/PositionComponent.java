package dungeon_crawler.components;

public class PositionComponent extends Component {
    private double x;
    private double y;
    private double prevX;
    private double prevY;
    private double width;
    private double height;

    public PositionComponent(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.prevX = x;
        this.prevY = y;
        this.width = width;
        this.height = height;
    }

    public void setX(double x) {
        this.prevX = this.x;
        this.x = x;
    }

    public void setY(double y) {
        this.prevY = this.y;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public boolean overlapsX(PositionComponent other) {
        return this.getX() < other.getX() + other.getWidth() && this.getX() + this.getWidth() > other.getX();
    }

    public boolean overlapsY(PositionComponent other) {
        return this.getY() < other.getY() + other.getHeight() && this.getY() + this.getHeight() > other.getY();
    }

    public boolean overlaps(PositionComponent other) {
        return overlapsX(other) && overlapsY(other);
    }

    public void restorePreviousX() {
        this.x = this.prevX;
    }

    public void restorePreviousY() {
        this.y = this.prevY;
    }
}
