package ch.fhnw.oop1.en2.game;

import ch.fhnw.oop1.en2.game.states.GameState;
import gui.Window;


public class Game {

  private GameState state;

  public Game() {
    state = GameState.getInstance(new Renderer(new Window("Metamorpher", 1000, 800)));
  }

  public void play() throws InterruptedException {
    Window window = state.getRenderer().getWindow();
    window.open();
    while((state.isRunning() || state.isPaused()) && window.isOpen()) {
      if (state.isRunning()) {
        // update game


        state.getRenderer().render(state.getEntities());

        Thread.sleep(16);
      }
      // game paused
    }
  }
}
