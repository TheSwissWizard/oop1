package ch.fhnw.oop1.en2.game;

import ch.fhnw.oop1.en2.game.processor.Processor;
import ch.fhnw.oop1.en2.game.util.GameState;
import ch.fhnw.oop1.en2.game.util.Renderer;
import gui.Window;

public class Game {

  public void play() throws InterruptedException {
    GameState state = GameState.getInstance();
    Window window = Renderer.getInstance().getWindow();

    window.open();
    while((state.isRunning() || state.isPaused()) && window.isOpen()) {
      if (state.isRunning()) {
        // update game

        Processor.getInstance().process();
        Renderer.getInstance().render();

        Thread.sleep(16);
      }
      // game paused
    }
  }
}
