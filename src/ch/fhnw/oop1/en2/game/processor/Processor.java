package ch.fhnw.oop1.en2.game.processor;

import ch.fhnw.oop1.en2.game.GameState;
import ch.fhnw.oop1.en2.game.Renderer;
import ch.fhnw.oop1.en2.game.entities.ABubble;
import gui.Window;

public class Processor {

  private static Processor instance;

  public static Processor getInstance() {
    if (instance == null) {
      instance = new Processor() {
      };
    }
    return instance;
  }

  public void process() {
    updateEntities();
    move();
  }

  private void updateEntities(){
    updatePlayer();
    updateNpcs();
  }

  private void updatePlayer(){
    Window window = Renderer.getInstance().getWindow();

    if (window.isKeyPressed("w")) {
      GameState.getInstance().getPlayer().setYSpeed(-10);
    } else {
      GameState.getInstance().getPlayer().setYSpeed(0);
    }

    if (window.isKeyPressed("s")) {
      GameState.getInstance().getPlayer().setYSpeed(10);
    } else {
      GameState.getInstance().getPlayer().setYSpeed(0);
    }

    if (window.isKeyPressed("a")) {
      GameState.getInstance().getPlayer().setXSpeed(-10);
    } else {
      GameState.getInstance().getPlayer().setXSpeed(0);
    }

    if (window.isKeyPressed("d")) {
      GameState.getInstance().getPlayer().setXSpeed(10);
    } else {
      GameState.getInstance().getPlayer().setXSpeed(0);
    }

    System.out.println(GameState.getInstance().getPlayer().getXSpeed());
  }

  private void updateNpcs() {}

  private void move() {
    for (ABubble entity : GameState.getInstance().getEntities()) {
      entity.setX(entity.getX() + entity.getXSpeed());
      entity.setY(entity.getY() + entity.getYSpeed());
    }
  }
}