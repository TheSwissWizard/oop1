package ch.fhnw.oop1.en2.game.entities.impl;

import ch.fhnw.oop1.en2.game.entities.ABubble;
import ch.fhnw.oop1.en2.game.renderer.Renderer;
import gui.Color;

import java.util.Random;

/**
 * This class depicts the NPCs (Morphs) in the game
 */
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

    private final int points;
    private final Color color;

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

  /**
   * Amount of milliseconds the morph is in its meta state
   */
  public static final long META_DURATION = 5000;

  /**
   * The amount a morph in its meta state grows per millisecond
   */
  public static final double GROWTH_PER_MS = (double) MAX_SIZE / META_DURATION;

  private MorphStates currentMorphState = MorphStates.META;

  private long age = 0;

  private Morph(int x, int y, int radius, MorphDNA dna, int xSpeed, int ySpeed) {
    super(x, y, radius, dna.getColor(), xSpeed, ySpeed);
  }

  /**
   * Creates a new {@link Morph} object with the proper values
   * @return newly created {@link Morph}
   */
  public static Morph createNewMorph() {

    MorphDNA dna = MorphDNA.values()[(int) (Math.random() * MorphDNA.values().length)];
    int x = new Random().nextInt((int) Renderer.getInstance().getWindow().getWidth()) + 1;
    int y = new Random().nextInt((int) Renderer.getInstance().getWindow().getHeight()) + 1;

    return new Morph(x, y, 0, dna, 0, 0);
  }

  /**
   * @return if the Morph is in its meta state
   */
  public boolean isMeta() {
    return this.currentMorphState == MorphStates.META;
  }

  /**
   * @return if the Moprh is in its prey state
   */
  public boolean isPrey() {
    return this.currentMorphState == MorphStates.PREY;
  }

  /**
   * Sets a new age value
   * @param age new age value
   */
  public void setAge(long age) {
    this.age = age;
  }

  /**
   * @return current age of the morph
   */
  public long getAge() {
    return this.age;
  }

  /**
   * Makes the morph a killer morph
   */
  public void killer() {
    this.currentMorphState = MorphStates.KILLER;
  }
}
