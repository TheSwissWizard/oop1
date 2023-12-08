package ch.fhnw.oop1.en2.game.renderer;

import ch.fhnw.oop1.en2.game.GameState;
import ch.fhnw.oop1.en2.game.entities.ABubble;
import gui.Window;

/**
 * This class contains part of the game's render/draw logic.
 * It orchestrates and delegates functions to draw the different entities onto the window
 */
public class Renderer {

    private static Renderer instance;

    /**
     * @return the current Renderer object of this application
     */
    public static Renderer getInstance() {
        if (instance == null) {
            instance = new Renderer();
        }
        return instance;
    }

    private Renderer() {
        this.window = new Window("Metamorpher", 1000, 800);
    }

    private final Window window;

    /**
     * With this method the render/draw process is being started
     */
    public void render() {

        if (GameState.getInstance().isPaused()) {
            window.drawStringCentered("PAUSED", window.getWidth() / 2, window.getHeight() / 2);

        } else if (GameState.getInstance().isRunning()) {
            for (ABubble entity : GameState.getInstance().getEntities()) {
                window.setColor(entity.getColor());
                window.fillCircle(entity.getX(), entity.getY(), entity.getRadius());
            }
        }
        window.refreshAndClear();
    }

    /**
     * Getter method for the Renderer's window
     *
     * @return current {@link Window window} object
     */
    public Window getWindow() {
        return window;
    }
}