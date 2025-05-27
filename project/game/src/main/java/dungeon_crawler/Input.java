package dungeon_crawler;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.EnumSet;
import java.util.Set;

public class Input {
	public enum Inputs {
		LEFT, RIGHT, UP, DOWN, SPACE
	};

	private final Set<Inputs> activeInputs = EnumSet.noneOf(Inputs.class);

	public Input(GraphicsContext gr) {
		gr.getFrame().addKeyListener(new KeyInputAdapter());
	}

	public Set<Inputs> getActiveInputs() {
		return EnumSet.copyOf(activeInputs);
	}

	class KeyInputAdapter extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			switch (e.getKeyCode()) {
				case KeyEvent.VK_A, KeyEvent.VK_LEFT -> activeInputs.add(Inputs.LEFT);
				case KeyEvent.VK_D, KeyEvent.VK_RIGHT -> activeInputs.add(Inputs.RIGHT);
				case KeyEvent.VK_W, KeyEvent.VK_UP -> activeInputs.add(Inputs.UP);
				case KeyEvent.VK_S, KeyEvent.VK_DOWN -> activeInputs.add(Inputs.DOWN);
				case KeyEvent.VK_SPACE -> activeInputs.add(Inputs.SPACE);
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			switch (e.getKeyCode()) {
				case KeyEvent.VK_A, KeyEvent.VK_LEFT -> activeInputs.remove(Inputs.LEFT);
				case KeyEvent.VK_D, KeyEvent.VK_RIGHT -> activeInputs.remove(Inputs.RIGHT);
				case KeyEvent.VK_W, KeyEvent.VK_UP -> activeInputs.remove(Inputs.UP);
				case KeyEvent.VK_S, KeyEvent.VK_DOWN -> activeInputs.remove(Inputs.DOWN);
				case KeyEvent.VK_SPACE -> activeInputs.remove(Inputs.SPACE);
			}
		}
	}
}