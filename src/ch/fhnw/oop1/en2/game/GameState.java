package ch.fhnw.oop1.en2.game;

import ch.fhnw.oop1.en2.game.entities.ABubble;
import ch.fhnw.oop1.en2.game.entities.impl.Morph;
import ch.fhnw.oop1.en2.game.entities.impl.Player;
import java.util.ArrayList;
import java.util.List;

public class GameState {

  private enum GameStates {
    RUNNING, PAUSED, WIN, LOSS
  }

  public static final int INITIAL_MORPH_COUNT = 2;

  private final List<Morph> npcs = new ArrayList<>();
  private final Player player;
  private static GameState instance;
  private GameStates currentGameState;

  private GameState() {
    this.player = new Player(Renderer.getInstance().getWindow().getWidth() / 2,
        Renderer.getInstance().getWindow().getHeight() / 2, 20);

    for (int i = 0; i < INITIAL_MORPH_COUNT; i++) {
      this.npcs.add(Morph.createNewMorph());
    }

    unpause();
  }

  public static GameState getInstance() {
    if (instance == null) {
      instance = new GameState();
    }
    return instance;
  }

  public void pause() {
    this.currentGameState = GameStates.PAUSED;
  }

  public void win() {
    this.currentGameState = GameStates.WIN;
  }

  public void loss() {
    this.currentGameState = GameStates.LOSS;
  }

  public void unpause() {
    this.currentGameState = GameStates.RUNNING;
  }

  public boolean isRunning() {
    return this.currentGameState == GameStates.RUNNING;
  }

  public boolean isPaused() {
    return this.currentGameState == GameStates.PAUSED;
  }


  public List<Morph> getNpcs() {
    return this.npcs;
  }

  public void addNpc(Morph npc) {
    this.npcs.add(npc);
  }

  public void removeNpc(Morph npc) {
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