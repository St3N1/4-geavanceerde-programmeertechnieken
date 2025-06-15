package dungeon_crawler.components;

public class MovementComponent extends Component {
	private int x;
	private int y;
	private int lastX = 1;
	private int lastY = 1;

	public MovementComponent() {
		this.x = 0;
		this.y = 0;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getLastX() {
		return lastX;
	}

	public int getLastY() {
		return lastY;
	}

	public void setDirection(int x, int y) {
		this.x = x;
		this.y = y;

		if (x != 0 || y != 0) {
			lastX = x;
			lastY = y;
		}
	}
}