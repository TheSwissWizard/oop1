package ch.fhnw.oop1.en2.game.processor;

import ch.fhnw.oop1.en2.game.entities.Ball;
import ch.fhnw.oop1.en2.game.states.GameState;

public interface Processor {
  public void process(Ball ball, GameState gameState);
}
