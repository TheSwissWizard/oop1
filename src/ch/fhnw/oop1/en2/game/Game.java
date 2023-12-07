package ch.fhnw.oop1.en2.game;

import ch.fhnw.oop1.en2.game.renderer.Renderer;
import ch.fhnw.oop1.en2.game.states.GameState;
import gui.Window;


public class Game {

  private GameState state;
  private Window window;
  private Renderer renderer;

  public Game() {
    renderer = new Renderer();
    window = new Window("Metamorpher", 1000, 800);
    state = GameState.getInstance(window);
  }

  public void play() throws InterruptedException {
    window.open();
    while((state.isRunning() || !state.isPaused()) && window.isOpen()) {
      if (state.isRunning()) {
        // update game

        renderer.render(window, state);
        Thread.sleep(16);
      }
      // game paused
    }
  }
}
