package dungeon_crawler.components;

public class VelocityComponent extends Component {
    private double dx;
    private double dy;

    public VelocityComponent(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public double getDx() {
        return dx;
    }

    public double getDy() {
        return dy;
    }
}
