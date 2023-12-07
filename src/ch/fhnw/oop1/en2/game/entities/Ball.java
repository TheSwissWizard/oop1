package ch.fhnw.oop1.en2.game.entities;

import gui.Color;

public abstract class Ball {

  private double x;
  private double y;
  private int radius;
  private Color color;
  private int xSpeed;
  private int ySpeed;

  public Ball(double x, double y, int radius, Color color, int xSpeed, int ySpeed) {
    this.x = x;
    this.y = y;
    this.radius = radius;
    this.color = color;
    this.xSpeed = xSpeed;
    this.ySpeed = ySpeed;
  }

  public double getX() {
    return x;
  }

  public void setX(double x) {
    this.x = x;
  }

  public double getY() {
    return y;
  }

  public void setY(double y) {
    this.y = y;
  }

  public int getRadius() {
    return radius;
  }

  public void setRadius(int radius) {
    this.radius = radius;
  }

  public Color getColor() {
    return color;
  }

  public void setColor(Color color) {
    this.color = color;
  }

  public int getXSpeed() {
    return xSpeed;
  }

  public void setXSpeed(int xSpeed) {
    this.xSpeed = xSpeed;
  }

  public int getYSpeed() {
    return ySpeed;
  }

  public void setYSpeed(int ySpeed) {
    this.ySpeed = ySpeed;
  }
}