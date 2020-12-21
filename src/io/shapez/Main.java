package io.shapez;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Main extends JPanel {
    byte loadingState = 0;
    String loadingText = "";
    StateManager stateManager = new StateManager(this);
    ArrayList<BufferedImage> textures = new ArrayList<>();

    public Main() {
        Thread animationThread = new Thread(() -> {
            while (true) {
                long start = System.currentTimeMillis();
                update();
                repaint();
                try {
                    if (1000 / 60 - (System.currentTimeMillis() - start) > 0) {
                        Thread.sleep(1000 / 60 - (System.currentTimeMillis() - start));
                    } else {
                        Thread.sleep(1);
                    }
                } catch (InterruptedException ignored) {
                }
            }
        });
        Thread mainThread = new Thread(() -> {
            loadTextures();
            loadSounds();
        });
        mainThread.start();
        animationThread.start();
    }

    private void loadSounds() {
    }

    private void loadTextures() {
        loadingText = "Loading Textures";
        String[] paths = {
                "res_raw/sprites/belt/built/forward_0.png",
                "res_raw/sprites/belt/built/forward_1.png",
                "res_raw/sprites/belt/built/forward_2.png",
                "res_raw/sprites/belt/built/forward_3.png",
                "res_raw/sprites/belt/built/forward_4.png",
                "res_raw/sprites/belt/built/forward_5.png",
                "res_raw/sprites/belt/built/forward_6.png",
                "res_raw/sprites/belt/built/forward_7.png",
                "res_raw/sprites/belt/built/forward_8.png",
                "res_raw/sprites/belt/built/forward_9.png",
                "res_raw/sprites/belt/built/forward_10.png",
                "res_raw/sprites/belt/built/forward_11.png",
                "res_raw/sprites/belt/built/forward_12.png",
                "res_raw/sprites/belt/built/forward_13.png",
                "res_raw/sprites/belt/built/left_0.png",
                "res_raw/sprites/belt/built/left_1.png",
                "res_raw/sprites/belt/built/left_2.png",
                "res_raw/sprites/belt/built/left_3.png",
                "res_raw/sprites/belt/built/left_4.png",
                "res_raw/sprites/belt/built/left_5.png",
                "res_raw/sprites/belt/built/left_6.png",
                "res_raw/sprites/belt/built/left_7.png",
                "res_raw/sprites/belt/built/left_8.png",
                "res_raw/sprites/belt/built/left_9.png",
                "res_raw/sprites/belt/built/left_10.png",
                "res_raw/sprites/belt/built/left_11.png",
                "res_raw/sprites/belt/built/left_12.png",
                "res_raw/sprites/belt/built/left_13.png",
                "res_raw/sprites/belt/built/right_0.png",
                "res_raw/sprites/belt/built/right_1.png",
                "res_raw/sprites/belt/built/right_2.png",
                "res_raw/sprites/belt/built/right_3.png",
                "res_raw/sprites/belt/built/right_4.png",
                "res_raw/sprites/belt/built/right_5.png",
                "res_raw/sprites/belt/built/right_6.png",
                "res_raw/sprites/belt/built/right_7.png",
                "res_raw/sprites/belt/built/right_8.png",
                "res_raw/sprites/belt/built/right_9.png",
                "res_raw/sprites/belt/built/right_10.png",
                "res_raw/sprites/belt/built/right_11.png",
                "res_raw/sprites/belt/built/right_12.png",
                "res_raw/sprites/belt/built/right_13.png",
                "res_raw/sprites/buildings/analyzer.png",
                "res_raw/sprites/buildings/balancer.png",
                "res_raw/sprites/buildings/balancer-merger.png",
                "res_raw/sprites/buildings/balancer-splitter.png",
                "res_raw/sprites/buildings/belt_left.png",
                "res_raw/sprites/buildings/belt_right.png",
                "res_raw/sprites/buildings/belt_top.png",
                "res_raw/sprites/buildings/comparator.png",
                "res_raw/sprites/buildings/constant_signal.png",
                "res_raw/sprites/buildings/cutter.png",
                "res_raw/sprites/buildings/cutter-quad.png",
                "res_raw/sprites/buildings/display.png"
        };
        for (String path : paths) {
            try {
                textures.add(ImageIO.read(new File(path)));
                loadingState++;
            } catch (IOException ignored) {
                System.out.println("Image not found");
            }
        }
        loadingText = "Textures Loaded";
    }

    private void update() {
        stateManager.getState().update();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.WHITE);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        stateManager.getState().render(g2d);
    }

    public static void main(String... args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Shapez.io");
            BufferedImage img = null;
            try {
                img = ImageIO.read(new File("res/logo.png"));
            } catch (IOException ignored) {
                System.out.println("Image not found");
            }
            frame.setIconImage(img);
            frame.setContentPane(new Main());
            frame.pack();
            frame.setSize(200, 200);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
