package states;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import menus.TempButton;
import other.Handler;

/**
 * Allows the user to see/change the game settings/controls.
 * @author Vadim Goncharuk
 * @version September 2017
 *
 */
public class SettingState extends AbstractGameState {

	private TempButton backbutton = new TempButton("Back");

	public SettingState(Handler hander) {
		super(hander);
	}

	public void render(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 640 + 40, 480 + 40);
		g.setColor(Color.WHITE);
		g.drawString("Setting not yet implemented", 400, 100);
		backbutton.render(g, 5, 10);
	}

	public void tick() {
		if (handler.keys[KeyEvent.VK_ESCAPE]) {
			handler.keys[KeyEvent.VK_ESCAPE] = false;
			//handler.setGameState(new MenuState(handler));
			currentState = new MenuState(handler);
		}
	}

	@Override
	public void leftClick(int x, int y) {
		if (x > 5 && x < 5 + 250 && y > 5 && y < 38 + 10) {
			//handler.setGameState(new MenuState(handler));
			currentState = new MenuState(handler);
		}
	}

	@Override
	public void rightClick(int x, int y) {}

	@Override
	public void moveWheel(int rot) {}

	/**
	 * Highlights the back button.
	 */
	@Override
	public void mouseMove(int x, int y) {
		if (x > 5 && x < 5 + 250 && y > 5 && y < 38 + 10) {
			backbutton.highlight();
		} else {
			backbutton.unhighlight();
		}

	}

}
