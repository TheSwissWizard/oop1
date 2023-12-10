package ch.fhnw.oop1.en2.game.renderer;

import ch.fhnw.oop1.en2.game.GameState;
import ch.fhnw.oop1.en2.game.entities.ABubble;
import ch.fhnw.oop1.en2.game.entities.impl.Morph;
import gui.Color;
import gui.Window;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This class contains part of the game's render/draw logic.
 * It orchestrates and delegates functions to draw the different entities onto the window
 */
public class Renderer {

    private static Renderer instance;

    /**
     * Returns the current Renderer object or creates a new one if the current one is null
     *
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
            renderText("PAUSED", window.getWidth() / 2, window.getHeight() / 2);
        } else if (GameState.getInstance().isWon()) {
            renderText(String.format("You won with %s points!!!\nRestart with space", GameState.getInstance().getPoints()),
                    window.getWidth() / 2, window.getHeight() / 2);
        } else if (GameState.getInstance().isLost()) {
            renderText(String.format("GameOver!!!\nPoints: %s\nTime left: %s", GameState.getInstance().getPoints(), formatGameTimeLeft()),
                    window.getWidth() / 2, window.getHeight() / 2);
        } else if (GameState.getInstance().isRunning()) {
            renderGame();
        }

        window.refreshAndClear();
    }

    private void renderText(String text, double x, double y) {
        window.setFontSize(30);
        for (String s : text.split("\n")) {
            window.drawStringCentered(s, x, y);
            y += window.getFontSize();
        }
    }

    private void renderGame() {
        window.setFontSize(20);
        window.drawStringCentered("Punkte: " + GameState.getInstance().getPoints(), 100, 50);
        window.drawStringCentered("Zeit: " + formatGameTimeLeft(), 300, 50);

        for (ABubble entity : GameState.getInstance().getEntities()) {
            window.setColor(entity.getColor());
            if (entity instanceof Morph && ((Morph) entity).isMeta()) {
                renderMetaMorph((Morph) entity);
            } else if (entity instanceof Morph && ((Morph) entity).isPrey()) {
                if (((Morph) entity).isPreyRender()) {
                    renderPreyMorph((Morph) entity);
                }
            } else {
                drawBubble(entity);
            }
        }
    }

    private void renderMetaMorph(Morph morph) {
        window.drawCircle(morph.getX(), morph.getY(), morph.getRadius());
    }

    /**
     * + 7 ist just an arbitrary number to center the text within the morph
     *
     * @param morph prey morph to be rendered
     */
    private void renderPreyMorph(Morph morph) {
        drawBubble(morph);
        window.setColor(new Color(255, 255, 255));
        window.drawStringCentered(Integer.toString(morph.getPoints()), morph.getX(), morph.getY() + 7);
    }

    private void drawBubble(ABubble entity) {
        window.fillCircle(entity.getX(), entity.getY(), entity.getRadius());
    }

    private String formatGameTimeLeft() {
        Date date = new Date(GameState.getInstance().getGameTime());
        SimpleDateFormat format = new SimpleDateFormat("mm:ss:SSS");
        return format.format(date);
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