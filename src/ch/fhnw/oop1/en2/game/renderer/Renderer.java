package ch.fhnw.oop1.en2.game.renderer;

import ch.fhnw.oop1.en2.game.entities.Ball;
import ch.fhnw.oop1.en2.game.states.GameState;
import gui.Window;

public class Renderer {

  public void render(Window window, GameState gameState) {
    window.refreshAndClear();
    for (Ball entity : gameState.getEntities()) {
      window.setColor(entity.getColor());
      window.fillCircle(entity.getX(), entity.getY(), entity.getRadius());
    }
    window.refresh();
  }
}
