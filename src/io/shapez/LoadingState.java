package io.shapez;

import java.awt.*;
import java.awt.geom.Arc2D;

public class LoadingState extends GameState {
    double rotation;

    LoadingState(Main app) {
        super(app);
    }

    @Override
    void update() {
        rotation += 2;
    }

    @Override
    void render(Graphics2D context) {
        context.draw(new Arc2D.Double(app.getWidth() / 2 - 50, app.getHeight() / 2 - 50, 100.0, 100.0, rotation, app.loadingState*4, Arc2D.OPEN));
        Font font = new Font("Serif", Font.PLAIN, 12);
        context.setFont(font);
        context.drawString(app.loadingText, app.getWidth() / 2 - 50, app.getHeight() / 2 + 60);
    }
}
