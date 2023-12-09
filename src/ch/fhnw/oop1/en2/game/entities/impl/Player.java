package ch.fhnw.oop1.en2.game.entities.impl;

import ch.fhnw.oop1.en2.game.entities.ABubble;
import gui.Color;

/**
 * This clas depicts the Player of the game
 */
public class Player extends ABubble {

  public Player(double x, double y) {
    super(x,y,MAX_SIZE, new Color(0,0,0), 0 , 0);
  }
}
