package dungeon_crawler.components;

public class MovementComponent extends Component {
	private int x;
	private int y;
	private boolean facingLeft = false;

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

	public void setDirection(int x, int y) {
		this.x = x;
		this.y = y;

		if (x < 0)
			facingLeft = true;
		else if (x > 0)
			facingLeft = false;
	}

	public boolean isFacingLeft() {
		return facingLeft;
	}
}