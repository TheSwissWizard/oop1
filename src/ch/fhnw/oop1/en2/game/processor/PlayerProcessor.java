package ch.fhnw.oop1.en2.game.processor;

import ch.fhnw.oop1.en2.game.entities.Ball;
import ch.fhnw.oop1.en2.game.entities.impl.Player;

public class PlayerProcessor implements Processor{

  @Override
  public void process(Ball player) {
    if (!(player instanceof Player)) throw new RuntimeException("This is not a player");


  }
}