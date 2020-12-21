package io.shapez;

public class StateManager {
    int stateId = 0;
    private Main app;
    GameState[] states;

    StateManager(Main app) {
        this.app = app;
        states = new GameState[]{new LoadingState(app)};
    }

    GameState getState() {
        return states[stateId];
    }
}
