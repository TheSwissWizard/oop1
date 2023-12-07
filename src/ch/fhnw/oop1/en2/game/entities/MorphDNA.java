package ch.fhnw.oop1.en2.game.entities;

import gui.Color;

public enum MorphDNA {

  TURQUOISE(1, new Color(6, 188, 156)),
  GREEN(5, new Color(46, 204, 113)),
  BLUE(10, new Color(52, 152, 219)),
  VIOLET(20, new Color(155, 89, 182)),
  YELLOW(40, new Color(241, 196, 15)),
  ORANGE(70, new Color(230, 126, 34)),
  RED(99, new Color(231, 76, 60));


  private int points;
  private Color color;

  MorphDNA(int points, Color color) {
    this.points = points;
    this.color = color;
  }

  public int getPoints() {
    return points;
  }

  public Color getColor() {
    return color;
  }
}
