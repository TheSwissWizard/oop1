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

  private int points;

  /**
   * Amount of milliseconds the morph is in its meta state
   */
  public static final long META_DURATION = 3000;

  /**
   * Amount of milliseconds the morph is in its prey state
   */
  public static long PREY_DURATION = 5000;
  private MorphStates currentMorphState = MorphStates.META;
  private long timer = 0;
  private long movementTimeInterval;
  private boolean preyRender = true;
  private long blinkTimer = 0;


  private Morph(int x, int y, int radius, MorphDNA dna, int xSpeed, int ySpeed, long movementTimeInterval, int points) {
    super(x, y, radius, dna.getColor(), xSpeed, ySpeed);
    this.movementTimeInterval = movementTimeInterval;
    this.points = points;
  }

  /**
   * Creates a new {@link Morph} object with the proper randomized values
   * @return newly created {@link Morph} object
   */
  public static Morph createNewMorph() {

    MorphDNA dna = MorphDNA.values()[(int) (Math.random() * MorphDNA.values().length)];
    int x = new Random().nextInt((int) Renderer.getInstance().getWindow().getWidth());
    int y = new Random().nextInt((int) Renderer.getInstance().getWindow().getHeight());
    long movementTimeInterval = new Random().nextInt(500, 2000);

    return new Morph(x, y, 0, dna, 0, 0, movementTimeInterval, dna.getPoints());
  }

  /**
   * @return if the morph is in its meta state
   */
  public boolean isMeta() {
    return this.currentMorphState == MorphStates.META;
  }

  /**
   * @return if the morph is in its prey state
   */
  public boolean isPrey() {
    return this.currentMorphState == MorphStates.PREY;
  }

  /**
   * @return if the morphs is in its killer state
   */
  public boolean isKiller() {
    return this.currentMorphState == MorphStates.KILLER;
  }

  /**
   * Sets a new timer value
   * @param timer new timer value
   */
  public void setTimer(long timer) {
    this.timer = timer;
  }

  /**
   * @return current timer of the morph
   */
  public long getTimer() {
    return this.timer;
  }

  /**
   * Makes the morph a killer morph
   */
  public void killer() {
    this.currentMorphState = MorphStates.KILLER;
  }

  /**
   * Makes the morph a prey morph
   */
  public void prey() {
    this.currentMorphState = MorphStates.PREY;
  }

  /**
   * @return points for this morph
   */
  public int getPoints() {
    return this.points;
  }

  /**
   * @return time till morph's movement changes
   */
  public long getMovementTimeInterval () {
    return this.movementTimeInterval;
  }

  /**
   * Sets a new interval value after which the morph's speed and direction is changed
   * @param interval the new interval
   */
  public void setMovementTimeInterval(long interval){
    this.movementTimeInterval = interval;
  }

  /**
   * @return preyRender value - if the prey morph should be rendered in the next frame
   */
  public boolean isPreyRender() {
    return preyRender;
  }

  /**
   * Sets a new preRender value - if the prey morph should be rendered in the next frame
   * @param preyRender new value
   */
  public void setPreyRender(boolean preyRender) {
    this.preyRender = preyRender;
  }

  /**
   * @return amount in millisecond since last blink
   */
  public long getBlinkTimer() {
    return blinkTimer;
  }

  /**
   * Sets a new blinkTimer value
   * @param blinkTimer new value
   */
  public void setBlinkTimer(long blinkTimer) {
    this.blinkTimer = blinkTimer;
  }
}
