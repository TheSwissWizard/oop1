package ch.fhnw.oop1.en2.game.entities.impl;

import ch.fhnw.oop1.en2.game.entities.ABubble;
import ch.fhnw.oop1.en2.game.entities.MorphDNA;
import ch.fhnw.oop1.en2.game.Renderer;
import java.util.Random;

public class Morph extends ABubble {

  private enum MorphStates {
    META, KILLER, PREY
  }

  private MorphStates currentMorphState = MorphStates.META;

  private Morph(int x, int y, int radius, MorphDNA dna, int xSpeed, int ySpeed) {
    super(x, y, radius, dna.getColor(), xSpeed, ySpeed);
  }

  public static Morph createNewMorph() {

    // TODO random speed and direction

    MorphDNA dna = MorphDNA.values()[(int) (Math.random() * MorphDNA.values().length)];
    int x = new Random().nextInt((int) Renderer.getInstance().getWindow().getWidth()) + 1;
    int y = new Random().nextInt((int) Renderer.getInstance().getWindow().getHeight()) + 1;

    return new Morph(x, y, 20, dna, 0, 0);
  }
}
