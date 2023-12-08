package ch.fhnw.oop1.en2.game;

import ch.fhnw.oop1.en2.game.entities.ABubble;
import gui.Window;

public class Renderer {

  private static Renderer instance;

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


  public void render() {
    for (ABubble entity : GameState.getInstance().getEntities()) {
      window.setColor(entity.getColor());
      window.fillCircle(entity.getX(), entity.getY(), entity.getRadius());
    }
    window.refreshAndClear();
  }

  public Window getWindow() {
    return window;
  }
}
