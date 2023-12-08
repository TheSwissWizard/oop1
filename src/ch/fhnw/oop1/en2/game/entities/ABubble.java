package ch.fhnw.oop1.en2.game.entities;

import gui.Color;

/**
 * This class depicts all the entities in the game
 * <p>
 * Part of these entities are:
 * <ul>
 *     <li>{@link ch.fhnw.oop1.en2.game.entities.impl.Morph}</li>
 *     <li>{@link ch.fhnw.oop1.en2.game.entities.impl.Player}</li>
 * </ul>
 */
public abstract class ABubble {

  private double x;
  private double y;
  private int radius;
  private Color color;
  private int xSpeed;
  private int ySpeed;

  protected ABubble(double x, double y, int radius, Color color, int xSpeed, int ySpeed) {
    this.x = x;
    this.y = y;
    this.radius = radius;
    this.color = color;
    this.xSpeed = xSpeed;
    this.ySpeed = ySpeed;
  }

  /**
   * @return the current x value of the object
   */
  public double getX() {
    return x;
  }

  /**
   * Sets a new x value for the current object
   * @param x new c value
   */
  public void setX(double x) {
    this.x = x;
  }

  /**
   * @return current y value of the object
   */
  public double getY() {
    return y;
  }

  /**
   * Sets a new y value for the object
   * @param y new y value
   */
  public void setY(double y) {
    this.y = y;
  }

  /**
   * @return radius of the  object
   */
  public int getRadius() {
    return radius;
  }

  /**
   * @return {@link Color} of the object
   */
  public Color getColor() {
    return color;
  }

  /**
   * @return current xSpeed of the object
   */
  public int getXSpeed() {
    return xSpeed;
  }

  /**
   * Sets a new xSpeed for the object
   * @param xSpeed new xSpeed
   */
  public void setXSpeed(int xSpeed) {
    this.xSpeed = xSpeed;
  }

  /**
   * @return current ySpeed of the object
   */
  public int getYSpeed() {
    return ySpeed;
  }

  /**
   * Sets a new ySpeed for the object
   * @param ySpeed new ySpeed
   */
  public void setYSpeed(int ySpeed) {
    this.ySpeed = ySpeed;
  }
}