package ch.fhnw.oop1.en2.game;

import ch.fhnw.oop1.en2.game.entities.GameEntity;
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
 *     <li>Current {@link Morph} object</li>
 *     <li>Current {@link Player} object</li>
 *     <li>Current points</li>
 *     <li>Time left</li>
 * </ul>
 * <p>
 * It also provides the methods to access and update this information
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
    private int points = 0;
    private long gameTime = 90000;

    private GameState() {
        this.player = new Player(Renderer.getInstance().getWindow().getWidth() / 2,
                Renderer.getInstance().getWindow().getHeight() / 2);

        for (int i = 0; i < INITIAL_MORPH_COUNT; i++) {
            this.morphs.add(Morph.createNewMorph());
        }
    }

    /**
     * Returns the current GameState object or creates a new one if the current one is null
     * @return the current GameState object of this game
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
     * @return if the game is won
     */
    public boolean isWon() {
        return this.currentGameState == GameStates.WIN;
    }

    /**
     * @return if the game is lost
     */
    public boolean isLost() {
        return this.currentGameState == GameStates.LOSS;
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
     * @return a list of {@link GameEntity}
     */
    public List<GameEntity> getEntities() {
        List<GameEntity> entities = new ArrayList<>(this.morphs);
        entities.add(this.player);
        return entities;
    }

    /**
     * @return the current {@link Player}
     */
    public Player getPlayer() {
        return this.player;
    }

    /**
     * @return the current points
     */
    public int getPoints() {
        return this.points;
    }

    /**
     * Sets a new points value
     * @param points new value
     */
    public void setPoints(int points) {
        this.points = points;
    }

    /**
     * @return the game left in this game
     */
    public long getGameTime() {
        return this.gameTime;
    }

    /**
     * Updates the game time based on the timeDelta
     * @param timeDelta duration of the previous frame
     */
    public void updateTime(long timeDelta) {
        this.gameTime -= timeDelta;
    }

    /**
     * Sets the current instance of GameState to null
     * So that in the next frame a fresh GameState is provided by {@link GameState#getInstance()}
     * and the game can be restarted
     */
    public void reset() {
        instance = null;
    }
}