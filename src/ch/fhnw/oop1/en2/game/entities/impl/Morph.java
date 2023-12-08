package ch.fhnw.oop1.en2.game.entities.impl;

import ch.fhnw.oop1.en2.game.entities.ABubble;
import ch.fhnw.oop1.en2.game.renderer.Renderer;
import gui.Color;

import java.util.Random;

public class Morph extends ABubble {

  private enum MorphStates {
    META, KILLER, PREY
  }

  private enum MorphDNA {
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

    int getPoints() {
      return this.points;
    }

    Color getColor() {
      return this.color;
    }
  }

  private MorphStates currentMorphState = MorphStates.META;

  private Morph(int x, int y, int radius, MorphDNA dna, int xSpeed, int ySpeed) {
    super(x, y, radius, dna.getColor(), xSpeed, ySpeed);
  }

  public static Morph createNewMorph() {

    MorphDNA dna = MorphDNA.values()[(int) (Math.random() * MorphDNA.values().length)];
    int x = new Random().nextInt((int) Renderer.getInstance().getWindow().getWidth()) + 1;
    int y = new Random().nextInt((int) Renderer.getInstance().getWindow().getHeight()) + 1;

    return new Morph(x, y, 20, dna, 0, 0);
  }
}
