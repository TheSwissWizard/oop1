package ch.fhnw.oop1.en2.game.entities.impl;

import ch.fhnw.oop1.en2.game.entities.ABubble;
import gui.Color;

/**
 * This clas depicts the Player of the game
 */
public class Player extends ABubble {

  public Player(double x, double y, int radius) {
    super(x,y,radius, new Color(0,0,0), 0 , 0);
  }
}
