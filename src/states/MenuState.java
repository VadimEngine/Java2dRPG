package states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import menus.TempButton;
import other.Handler;

/**
 * Menu state, allows the user to choose to play, quit, or change game settings.
 * @author Vadim Goncharuk
 * @version
 *
 */
public class MenuState extends AbstractGameState {//remove the constants, render the buttons in the center

	/**
	 * ArrayList of available buttons in the state.
	 */
	private ArrayList<TempButton> buttons = new ArrayList<>();

	/**
	 * Adds the "New Game", "Load Game", "Setting", and "Exit" button
	 * @param handler
	 */
	public MenuState(Handler handler) {
		super(handler);
		buttons.add(new TempButton("New Game"));
		buttons.add(new TempButton("Load Game"));
		buttons.add(new TempButton("Settings"));
		buttons.add(new TempButton("Exit"));
	}

	/**
	 * Draws the background and buttons.
	 */
	@Override
	public void render(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 640 + 40, 480 + 40);//+40 for width and height
		g.setColor(Color.WHITE);
		g.setFont(new Font("TimesRoman", 1, 64));
		g.drawString("The Game", 200, 75);

		for (int i = 0; i < buttons.size(); i++) {
			buttons.get(i).render(g, 640/2 -100, 110 + i*86);
		}
	}

	@Override
	public void tick() {
		if (handler.keys[KeyEvent.VK_ESCAPE]) {
			System.exit(0);
		}
	}

	/**
	 * Controls what happens when a button is pressed.
	 */
	@Override
	public void leftClick(int x, int y) {
		if (x > 640/2 -100 && x < 640/2 -100 + 250 &&
				y > 110 + 0*86 && y < 110 + 0*86 + 38) {
			//handler.setGameState(new PlayState2(handler));
			//currentState = new PlayState2(handler);
			currentState = new PlayState(handler);
		} else if (x > 640/2 -100 && x < 640/2 -100 + 250 &&
				y > 110 + 1*86 && y <110 + 1*86 + 38) {
			//handler.setGameState(new LoadGameState(handler));
			currentState = new LoadGameState(handler);
		} else if (x > 640/2 -100 && x < 640/2 -100 + 250 &&
				y > 110 + 2*86 && y <110 + 2*86 + 38) {
			//handler.setGameState(new SettingState(handler));
			currentState = new SettingState(handler);
		} else if (x > 640/2 -100 && x < 640/2 -100 + 250 &&
				y > 110 + 3*86 && y <110 + 3*86 + 38) {
			System.exit(0);
		}

	}

	@Override
	public void rightClick(int x, int y) {
		// TODO Auto-generated method stub

	}

	@Override
	public void moveWheel(int rot) {
		// TODO Auto-generated method stub

	}

	/**
	 * Highlights buttons when the mouse is over them.
	 */
	@Override
	public void mouseMove(int x, int y) {
		for (int i = 0; i < buttons.size(); i++) {
			buttons.get(i).unhighlight();
		}
		if (x > 640/2 -100 && x < 640/2 -100 + 250 &&
				y > 110 + 0*86 && y < 110 + 0*86 + 38) {
			buttons.get(0).highlight();
		} else if (x > 640/2 -100 && x < 640/2 -100 + 250 &&
				y > 110 + 1*86 && y <110 + 1*86 + 38) {
			buttons.get(1).highlight();
		} else if (x > 640/2 -100 && x < 640/2 -100 + 250 &&
				y > 110 + 2*86 && y <110 + 2*86 + 38) {
			buttons.get(2).highlight();
		} else if (x > 640/2 -100 && x < 640/2 -100 + 250 &&
				y > 110 + 3*86 && y <110 + 3*86 + 38) {
			buttons.get(3).highlight();
		}

	}

}
