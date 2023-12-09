package ch.fhnw.oop1.en2.game.processor;

import ch.fhnw.oop1.en2.game.GameState;
import ch.fhnw.oop1.en2.game.entities.impl.Morph;
import ch.fhnw.oop1.en2.game.renderer.Renderer;
import ch.fhnw.oop1.en2.game.entities.ABubble;
import gui.Window;

import java.util.List;

/**
 * This class contains the game's update logic.
 * It orchestrates and delegates functions to update the {@link GameState gameState}
 * as well as the {@link ch.fhnw.oop1.en2.game.entities.impl.Player player}
 * and {@link ch.fhnw.oop1.en2.game.entities.impl.Morph morphs}
 */
public class Processor {

  private static Processor instance;

  /**
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

  /**
   * With this method the update process is being started
   */
  public void process(long timeDelta) {

    if (Renderer.getInstance().getWindow().wasKeyTyped("escape")) {
      if (GameState.getInstance().isRunning()) {
        GameState.getInstance().pause();
      } else if (GameState.getInstance().isPaused()) {
        GameState.getInstance().unpause();
      }
    }

    if (GameState.getInstance().isRunning()) {
      processGame(timeDelta);
    }else if (GameState.getInstance().isWon() || GameState.getInstance().isLost()) {
      processGameEnd();
    }
  }

  private void processGameEnd() {
    if (Renderer.getInstance().getWindow().isKeyPressed("space")) {
      GameState.getInstance().reset();
    }
  }

  private void processGame(long timeDelta) {
    if (GameState.getInstance().getGameTime() > 0) {
      GameState.getInstance().updateTime(timeDelta);
      updateEntities(timeDelta);
      moveEntity();
    }else {
      GameState.getInstance().win();
    }
  }

  private void updateEntities(long timeDelta){
    updatePlayer();
    updateMorphs(timeDelta);
  }

  private void updateMorphs(long timeDelta) {
    for (Morph morph : GameState.getInstance().getMorphs()) {
      if (morph.isMeta()) {
        if (morph.getAge() <= Morph.META_DURATION) {
          morph.setAge(morph.getAge() + timeDelta);
          morph.setRadius(calculateMorphSize(morph, timeDelta));
        } else {
          morph.killer();
        }
      }
    }
  }

  private double calculateMorphSize(Morph morph, long timeDelta) {
    double newSize = morph.getRadius() + (Morph.GROWTH_PER_MS * timeDelta);
    return newSize > ABubble.MAX_SIZE ? ABubble.MAX_SIZE : newSize;
  }

  private void updatePlayer() {
    Window window = Renderer.getInstance().getWindow();

    if (window.isKeyPressed("w")) {
      GameState.getInstance().getPlayer().setYSpeed(-5);
    } else if (window.isKeyPressed("s")) {
      GameState.getInstance().getPlayer().setYSpeed(5);
    } else {
      GameState.getInstance().getPlayer().setYSpeed(0);
    }

    if (window.isKeyPressed("a")) {
      GameState.getInstance().getPlayer().setXSpeed(-5);
    } else if (window.isKeyPressed("d")) {
      GameState.getInstance().getPlayer().setXSpeed(5);
    } else {
      GameState.getInstance().getPlayer().setXSpeed(0);
    }
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