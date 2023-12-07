package ch.fhnw.oop1.en2.game.states;

import ch.fhnw.oop1.en2.game.Renderer;
import ch.fhnw.oop1.en2.game.entities.Ball;
import ch.fhnw.oop1.en2.game.entities.impl.Player;
import java.util.ArrayList;
import java.util.List;

public class GameState {

  private enum States {
    RUNNING, PAUSED, WIN, LOSS
  }

  private List<Ball> entities = new ArrayList<>();
  private static GameState instance;
  private Renderer renderer;
  private States state;

  private GameState() {}

  public static GameState getInstance(Renderer renderer) {
    if (instance == null) {
      instance = new GameState();
      instance.addEntity(
          new Player(renderer.getWindow().getWidth() / 2, renderer.getWindow().getHeight() / 2,
              20));
      instance.renderer = renderer;
      instance.unpause();
    }
    return instance;
  }

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

  public Renderer getRenderer() {
    return renderer;
  }
}