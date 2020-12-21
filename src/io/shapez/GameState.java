package io.shapez;

import java.awt.*;

public abstract class GameState {
    protected Main app;

    GameState(Main app) {
        this.app = app;
    }
    abstract void update();
    abstract void render(Graphics2D context);
}
