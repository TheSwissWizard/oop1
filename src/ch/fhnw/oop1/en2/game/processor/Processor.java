package ch.fhnw.oop1.en2.game.processor;

import ch.fhnw.oop1.en2.game.GameState;
import ch.fhnw.oop1.en2.game.renderer.Renderer;
import ch.fhnw.oop1.en2.game.entities.ABubble;
import gui.Window;

/**
 * This class is the entrypoint for the game's update logic.
 * It orchestrates and delegates functions to update the {@link GameState gameState}
 * as well as the {@link ch.fhnw.oop1.en2.game.entities.impl.Player player}
 * and {@link ch.fhnw.oop1.en2.game.entities.impl.Morph morphs}
 */
public class Processor {
  
  private final PlayerUpdater playerUpdater = new PlayerUpdater();
  private final MorphUpdater morphUpdater = new MorphUpdater();
  private static Processor instance;

  /**
   * Returns the current Processor object associated with this application
   *
   * @return the current Processor object of this application
   */
  public static Processor getInstance() {
    if (instance == null) {
      instance = new Processor() {
      };
    }
    return instance;
  }

  private Processor(){}

  public void process() {

    if (Renderer.getInstance().getWindow().wasKeyTyped("escape")) {
      if (GameState.getInstance().isRunning()) {
        GameState.getInstance().pause();
      } else if (GameState.getInstance().isPaused()) {
        GameState.getInstance().unpause();
      }
    }

    if (GameState.getInstance().isRunning()) {
      updateEntities();
      moveEntity();
    }
  }

  private void updateEntities(){
    this.playerUpdater.update();
    this.morphUpdater.update();
  }

  private void moveEntity() {
    for (ABubble entity : GameState.getInstance().getEntities()) {
      entity.setX(entity.getX() + entity.getXSpeed());
      entity.setY(entity.getY() + entity.getYSpeed());
      checkWrapAround(entity);
    }
  }

  private void checkWrapAround(ABubble entity) {
    Window window = Renderer.getInstance().getWindow();
    if (entity.getX() < 0) {
      entity.setX(window.getWidth());
    } else if (entity.getX() > window.getWidth()) {
      entity.setX(0);
    }
    if (entity.getY() < 0) {
      entity.setY(window.getHeight());
    } else if (entity.getY() > window.getHeight()) {
      entity.setY(0);
    }
  }
}