package states;

import other.Handler;

public abstract class AbstractGameState implements GameState {
	
	protected Handler handler;
	
	protected GameState currentState;
	
	public AbstractGameState(Handler handler) {
		this.handler = handler;
		currentState = this;
	}
	
	@Override
	public GameState currentState() {
		return currentState;
	}

}
