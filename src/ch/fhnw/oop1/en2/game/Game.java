package ch.fhnw.oop1.en2.game;

import ch.fhnw.oop1.en2.game.processor.Processor;
import ch.fhnw.oop1.en2.game.renderer.Renderer;
import gui.Window;

/**
 * Starting class for the game itself
 *
 * This class contains the game loop
 */
public class Game {

  /**
   * This method starts the game, opens the window and initializes the game loop
   *
   * @throws InterruptedException thrown in case something is wrong with the {@link Thread#sleep(long)} method.
   */
  public void play() throws InterruptedException {
    GameState state = GameState.getInstance();
    Window window = Renderer.getInstance().getWindow();

    window.open();
    while((state.isRunning() || state.isPaused()) && window.isOpen()) {

      Processor.getInstance().process();
      Renderer.getInstance().render();

      Thread.sleep(16);
    }
  }
}
