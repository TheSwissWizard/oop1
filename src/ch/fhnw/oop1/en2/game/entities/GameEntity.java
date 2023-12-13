package ch.fhnw.oop1.en2.game.entities;

import gui.Color;

/**
 * This class depicts all the entities in the game
 * <p>
 * Part of these entities are:
 * <ul>
 *     <li>{@link ch.fhnw.oop1.en2.game.entities.impl.Morph Morph}</li>
 *     <li>{@link ch.fhnw.oop1.en2.game.entities.impl.Player Player}</li>
 * </ul>
 */
public abstract class GameEntity {

  private double x;
  private double y;
  private double radius;
  private final Color color;
  private int xSpeed;
  private int ySpeed;

  /**
   * Max size an entity can have
   */
  public static final int MAX_SIZE = 20;

  protected GameEntity(double x, double y, double radius, Color color, int xSpeed, int ySpeed) {
    this.x = x;
    this.y = y;
    this.radius = radius;
    this.color = color;
    this.xSpeed = xSpeed;
    this.ySpeed = ySpeed;
  }

  /**
   * @return the current x position of the object
   */
  public double getX() {
    return x;
  }

  /**
   * Sets a new x position for the current object
   * @param x new x position
   */
  public void setX(double x) {
    this.x = x;
  }

  /**
   * @return current y position of the object
   */
  public double getY() {
    return y;
  }

  /**
   * Sets a new y position for the object
   * @param y new y position
   */
  public void setY(double y) {
    this.y = y;
  }

  /**
   * @return radius of the object
   */
  public double getRadius() {
    return radius;
  }

  /**
   * Updates the radius of this entity
   * @param radius new radius
   */
  public void setRadius(double radius) {
    this.radius = radius;
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