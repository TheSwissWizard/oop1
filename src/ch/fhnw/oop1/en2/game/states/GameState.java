package ch.fhnw.oop1.en2.game.states;

import ch.fhnw.oop1.en2.game.entities.Ball;
import ch.fhnw.oop1.en2.game.entities.impl.Player;
import gui.Window;
import java.util.ArrayList;
import java.util.List;

public class GameState {

  private enum States {
    RUNNING, PAUSED, WIN, LOSS
  }

  private List<Ball> entities = new ArrayList<>();

  private static GameState instance;
  public static GameState getInstance(Window window) {
    if (instance == null) {
      instance = new GameState();
      instance.addEntity(new Player(window.getWidth() / 2, window.getHeight() / 2, 20));
      instance.unpause();
    }
    return instance;
  }

  private GameState() {}

  private States state;

  public void pause() {
    state = States.PAUSED;
  }

  public void win() {
    state = States.WIN;
  }

  public void loss() {
    state = States.LOSS;
  }

  public void unpause() {
    state = States.RUNNING;
  }

  public boolean isRunning() {
    return state == States.RUNNING;
  }

  public boolean isPaused() {
    return state == States.PAUSED;
  }

  public States getState() {
    return state;
  }

  public List<Ball> getEntities() {
    return entities;
  }

  public void addEntity(Ball entity) {
    entities.add(entity);
  }

  public void removeEntity(Ball entity) {
    entities.remove(entity);
  }
}