package ch.fhnw.oop1.en2.game.entities.impl;

import ch.fhnw.oop1.en2.game.entities.Ball;
import gui.Color;

public class Player extends Ball {

  public Player(double x, double y, int radius) {
    super(x,y,radius, new Color(0,0,0), 0 , 0);
  }
}
