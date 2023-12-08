package ch.fhnw.oop1.en2.game.processor;

import ch.fhnw.oop1.en2.game.GameState;
import ch.fhnw.oop1.en2.game.Renderer;
import gui.Window;

public class PlayerUpdater {
  
  PlayerUpdater() {
    
  }

  public void update(){
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
}
