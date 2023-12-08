package ch.fhnw.oop1.en2.game;

import ch.fhnw.oop1.en2.game.entities.ABubble;
import ch.fhnw.oop1.en2.game.entities.impl.Morph;
import ch.fhnw.oop1.en2.game.entities.impl.Player;
import ch.fhnw.oop1.en2.game.renderer.Renderer;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the current game's state
 * <p>
 * It contains all important information of the current game such as:
 * <ul>
 *     <li>Current sate of the game</li>
 *     <li>Current {@link Morph morph} entities</li>
 *     <li>Current {@link Player player} object</li>
 * </ul>
 * <p>
 * It also provides the methods to access this information as well as to update the  gamestate
 */
public class GameState {

    private enum GameStates {
        RUNNING, PAUSED, WIN, LOSS
    }

    private static final int INITIAL_MORPH_COUNT = 2;

    private final List<Morph> morphs = new ArrayList<>();
    private final Player player;
    private static GameState instance;
    private GameStates currentGameState = GameStates.RUNNING;

    private GameState() {
        this.player = new Player(Renderer.getInstance().getWindow().getWidth() / 2,
                Renderer.getInstance().getWindow().getHeight() / 2, 20);

        for (int i = 0; i < INITIAL_MORPH_COUNT; i++) {
            this.morphs.add(Morph.createNewMorph());
        }
    }

    /**
     * @return the current GameState object of this application
     */
    public static GameState getInstance() {
        if (instance == null) {
            instance = new GameState();
        }
        return instance;
    }

    /**
     * Sets the {@link GameState#currentGameState currentGamestate} to {@link GameStates#PAUSED}
     */
    public void pause() {
        this.currentGameState = GameStates.PAUSED;
    }

    /**
     * Sets the {@link GameState#currentGameState currentGamestate} to {@link GameStates#WIN}
     */
    public void win() {
        this.currentGameState = GameStates.WIN;
    }

    /**
     * Sets the {@link GameState#currentGameState currentGamestate} to {@link GameStates#LOSS}
     */
    public void loss() {
        this.currentGameState = GameStates.LOSS;
    }

    /**
     * Sets the {@link GameState#currentGameState currentGamestate} to {@link GameStates#RUNNING}
     */
    public void unpause() {
        this.currentGameState = GameStates.RUNNING;
    }

    /**
     * @return boolean if the game is currently running
     */
    public boolean isRunning() {
        return this.currentGameState == GameStates.RUNNING;
    }

    /**
     * @return boolean if the game is currently paused
     */
    public boolean isPaused() {
        return this.currentGameState == GameStates.PAUSED;
    }

    /**
     * @return a list of all current {@link Morph morphs}
     */
    public List<Morph> getMorphs() {
        return this.morphs;
    }

    /**
     * @param morph adds a {@link Morph}
     */
    public void addMorph(Morph morph) {
        this.morphs.add(morph);
    }

    /**
     * Removes a {@link Morph}
     * @param morph {@link Morph} to remove
     */
    public void removeMorph(Morph morph) {
        this.morphs.remove(morph);
    }

    /**
     * Returns a list of all current entities = All {@link Morph morphs} and the {@link Player}
     * @return a list of {@link ABubble}
     */
    public List<ABubble> getEntities() {
        List<ABubble> entities = new ArrayList<>(this.morphs);
        entities.add(this.player);
        return entities;
    }

    /**
     * @return the current {@link Player}
     */
    public Player getPlayer() {
        return this.player;
    }
}