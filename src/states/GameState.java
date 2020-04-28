package states;

import java.awt.Graphics;

public interface GameState {

	void render(Graphics g);

	void tick();
	
	void leftClick(int x, int y);
	
	void rightClick(int x, int y);
	
	void moveWheel(int rot);
	
	void mouseMove(int x, int y);
	
	GameState currentState();

}
