package ch.fhnw.oop1.en2.game.entities.impl;

import ch.fhnw.oop1.en2.game.entities.Ball;
import gui.Color;

public class Npc extends Ball {

  public Npc(double x, double y, int radius, Color color) {
    super(x, y, radius, color, 0, 0);
  }
}
