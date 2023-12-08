package ch.fhnw.oop1.en2.game;

import ch.fhnw.oop1.en2.game.entities.ABubble;
import ch.fhnw.oop1.en2.game.entities.impl.Npc;
import ch.fhnw.oop1.en2.game.entities.impl.Player;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameState {

  private enum States {
    RUNNING, PAUSED, WIN, LOSS
  }

  private final List<Npc> npcs = new ArrayList<>();
  private final Player player;
  private static GameState instance;
  private States state;

  private GameState() {
    this.player = new Player(Renderer.getInstance().getWindow().getWidth() / 2,
        Renderer.getInstance().getWindow().getHeight() / 2, 20);
    unpause();
  }

  public static GameState getInstance() {
    if (instance == null) {
      instance = new GameState();
    }
    return instance;
  }

  public void pause() {
    this.state = States.PAUSED;
  }

  public void win() {
    this.state = States.WIN;
  }

  public void loss() {
    this.state = States.LOSS;
  }

  public void unpause() {
    this.state = States.RUNNING;
  }

  public boolean isRunning() {
    return this.state == States.RUNNING;
  }

  public boolean isPaused() {
    return this.state == States.PAUSED;
  }


  public List<Npc> getNpcs() {
    return this.npcs;
  }

  public void addNpc(Npc npc) {
    this.npcs.add(npc);
  }

  public void removeNpc(Npc npc) {
    this.npcs.remove(npc);
  }

  public List<ABubble> getEntities(){
    List<ABubble> entities = new ArrayList<>(this.npcs);
    entities.add(this.player);
    return entities;
  }

  public Player getPlayer() {
    return this.player;
  }
}