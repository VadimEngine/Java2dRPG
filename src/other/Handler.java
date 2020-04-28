package other;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import states.*;

/**
 * IDk.
 * 
 * @author Vadim Goncharuk
 * @version September 2017
 *
 */
public class Handler implements KeyListener, MouseWheelListener, MouseMotionListener, MouseListener {
	
	/**
	 * Boolean array of all the keycodes.
	 */
	public boolean keys[] = new boolean[65536];
	
	/**
	 * The current state of the game (intro, menu, play, setting, etc.), initially the intro state.
	 */
	private GameState state = new IntroState(this);

	public Handler() {}
	
	/**
	 * Draws the current state.
	 * @param g graphics from the main render method.
	 */
	public void render(Graphics g) {
		state.render(g);
	}

	/**
	 * Ticks the current state of the game/
	 */
	public void tick() {
		state.tick();
		state = state.currentState();
	}
	
	/**
	 * Used by other states to set the state of the game.
	 * @param state
	 */
	
//	public void setGameState(GameState state) {
//		this.state = state;
//	}

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getButton() == 1) {
			state.leftClick(e.getX(), e.getY());
		}
		if (e.getButton() == 3) {
			state.rightClick(e.getX(), e.getY());
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mouseDragged(MouseEvent e) {}

	@Override
	public void mouseMoved(MouseEvent e) {
		state.mouseMove(e.getX(), e.getY());
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		state.moveWheel(e.getWheelRotation());
	}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		if (code >= 0 && code < keys.length) {
			keys[code] = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();
		if (code >= 0 && code < keys.length) {
			keys[code] = false;
		}
	
	}

}
