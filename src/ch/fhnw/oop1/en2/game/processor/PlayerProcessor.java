package ch.fhnw.oop1.en2.game.processor;

import ch.fhnw.oop1.en2.game.entities.Ball;
import ch.fhnw.oop1.en2.game.entities.impl.Player;
import ch.fhnw.oop1.en2.game.states.GameState;
import gui.Window;

public class PlayerProcessor implements Processor{

  @Override
  public void process(Ball player, GameState gameState) {
    if (!(player instanceof Player)) throw new RuntimeException("This is not a player");


  }

  private void getDirection (Window window, Player player) {
    if (window.isKeyPressed("W")) {
      player.setYSpeed(player.getYSpeed() - 10);
    }
    if (window.isKeyPressed("S")) {
      player.setYSpeed(player.getYSpeed() + 10);
    }
    if (window.isKeyPressed("A")) {
      player.setXSpeed(player.getXSpeed() - 10);
    }
    if (window.isKeyPressed("D")) {
      player.setXSpeed(player.getXSpeed() + 10);
    }
  }
}