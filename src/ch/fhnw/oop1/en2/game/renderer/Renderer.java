package ch.fhnw.oop1.en2.game.renderer;

import ch.fhnw.oop1.en2.game.GameState;
import ch.fhnw.oop1.en2.game.entities.ABubble;
import gui.Window;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.logging.SimpleFormatter;

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
            window.drawStringCentered("Punkte: " + GameState.getInstance().getPoints(), 50, 50);



            Date date = new Date(GameState.getInstance().getGameTime());
            SimpleDateFormat format = new SimpleDateFormat("mm:ss:SSS");
            String formatedTime = format.format(date);

            window.drawStringCentered("Zeit: " + formatedTime, 150, 50);

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