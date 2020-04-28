package states;

import java.awt.Font;
import java.awt.Graphics;
import other.Handler;

/**
 * Into state, draws a logo for a period then proceeds to the next state.
 * @author Vadim Goncharuk
 * @version September 2017
 *
 */
public class IntroState extends AbstractGameState {
	
	private static final int MAX_COUNT = 200;
	
	private int counter;
	
	public IntroState(Handler handler) {
		super(handler);
	}


	
	public void render(Graphics g) {
		g.setFont(new Font("Comic Sans", 1, 54));
		g.drawString("Vadim Games Inc.\u00a9", 320 - 248, 240 - 32);
	}
	
	public void tick() {
		counter++;
		if (counter > MAX_COUNT) {
			currentState = new MenuState(handler);
			//handler.setGameState(new MenuState(handler));
		}
		
		for (int i = 0; i < handler.keys.length; i++) {
			if (handler.keys[i]) {
				handler.keys[i] = false;
				currentState = new MenuState(handler);
				//handler.setGameState(new MenuState(handler));
			}
		}
	}
	

	@Override
	public void leftClick(int x, int y) {
		//handler.setGameState(new MenuState(handler));
		currentState = new MenuState(handler);
	}

	@Override
	public void rightClick(int x, int y) {
		//handler.setGameState(new MenuState(handler));
		currentState = new MenuState(handler);
	}

	@Override
	public void moveWheel(int rot) {}

	@Override
	public void mouseMove(int x, int y) {}


}
