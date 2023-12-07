package ch.fhnw.oop1.en2.game;

import ch.fhnw.oop1.en2.game.entities.Ball;
import ch.fhnw.oop1.en2.game.states.GameState;
import gui.Window;
import java.util.List;

public class Renderer {

  private Window window;

  public Renderer(Window window) {
    this.window = window;
  }

  public void render(List<Ball> entities) {
    for (Ball entity : entities) {
      window.setColor(entity.getColor());
      window.fillCircle(entity.getX(), entity.getY(), entity.getRadius());
    }
    window.refreshAndClear();
  }

  public Window getWindow() {
    return window;
  }
}
