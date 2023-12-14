package ch.fhnw.oop1.en2.game.processor;

import ch.fhnw.oop1.en2.game.GameState;
import ch.fhnw.oop1.en2.game.entities.impl.Morph;
import ch.fhnw.oop1.en2.game.entities.impl.Player;
import ch.fhnw.oop1.en2.game.renderer.Renderer;
import ch.fhnw.oop1.en2.game.entities.GameEntity;
import gui.Window;

import java.util.Random;

/**
 * This class contains the game's update logic.
 * It orchestrates and delegates functions to update the {@link GameState gameState}
 * as well as the {@link ch.fhnw.oop1.en2.game.entities.impl.Player player}
 * and {@link ch.fhnw.oop1.en2.game.entities.impl.Morph morphs}
 */
public class Processor {

    private static Processor instance;

    /**
     * Returns the current Processor object or creates a new one if the current one is null
     *
     * @return the current Processor object of this application
     */
    public static Processor getInstance() {
        if (instance == null) {
            instance = new Processor();
        }
        return instance;
    }

    private Processor() {
    }

    /**
     * With this method the update process is being started
     *
     * @param timeDelta time in milliseconds passed since the last frame
     */
    public void process(long timeDelta) {

        if (Renderer.getInstance().getWindow().wasKeyTyped("escape")) {
            if (GameState.getInstance().isRunning()) {
                GameState.getInstance().pause();
            } else if (GameState.getInstance().isPaused()) {
                GameState.getInstance().unpause();
            }
        }

        if (Renderer.getInstance().getWindow().wasKeyTyped("space")) {
            GameState.getInstance().reset();
        }

        if (GameState.getInstance().isRunning()) {
            processGame(timeDelta);
        } else if (GameState.getInstance().isWon() || GameState.getInstance().isLost()) {
            processGameEnd();
        }
    }

    private void processGameEnd() {
        if (Renderer.getInstance().getWindow().isKeyPressed("space")) {
            GameState.getInstance().reset();
        }
    }

    private void processGame(long timeDelta) {
        if (GameState.getInstance().getGameTime() > 0) {
            GameState.getInstance().updateTime(timeDelta);
            updateEntities(timeDelta);
            moveEntity();
        } else {
            GameState.getInstance().win();
        }
    }

    private void updateEntities(long timeDelta) {
        updatePlayer();
        updateMorphs(timeDelta);
    }

    private void updateMorphs(long timeDelta) {
        for (Morph morph : GameState.getInstance().getMorphs()) {
            morph.setTimer(morph.getTimer() + timeDelta);
            if (morph.isMeta()) {
                processMetaMorph(morph, timeDelta);
            } else if (morph.isPrey()) {
                processPreyMorph(morph, timeDelta);
            } else {
                processKillerMorph(morph);
            }
        }
    }

    private void processKillerMorph(Morph morph) {
        if (morph.getTimer() > morph.getMovementTimeInterval()) {
            generateRandomMorphSpeed(morph);
        }

        if (new Random().nextInt(420) == 69) {
            morph.prey();
            morph.setXSpeed(0);
            morph.setYSpeed(0);
        }
    }

    private void processPreyMorph(Morph morph, long timeDelta) {
        if (morph.getTimer() > Morph.PREY_DURATION) {
            morph.killer();
            generateRandomMorphSpeed(morph);
        } else if (morph.getTimer() > 3000) {
            if (morph.getBlinkTimer() > 250) {
                morph.setPreyRender(!morph.isPreyRender());
                morph.setBlinkTimer(0);
            } else {
                morph.setBlinkTimer(morph.getBlinkTimer() + timeDelta);
            }
        }
    }

    private void processMetaMorph(Morph morph, long timeDelta) {
        if (!morph.isSpawned() && (morph.getTimer() > morph.getSpawnDelay())) {
            morph.setSpawned(true);
            morph.setTimer(0);
        } else if (morph.isSpawned()) {
            if (morph.getTimer() > Morph.META_DURATION) {
                morph.killer();
                generateRandomMorphSpeed(morph);
            } else {
                morph.setRadius(calculateMorphSize(morph, timeDelta));
            }
        }
    }

    private void generateRandomMorphSpeed(Morph morph) {
        int xSpeed = new Random().nextInt(-5, 5);
        int ySpeed = new Random().nextInt(-5, 5);
        int interval = new Random().nextInt(500, 2000);

        morph.setXSpeed(xSpeed);
        morph.setYSpeed(ySpeed);
        morph.setMovementTimeInterval(interval);

        morph.setTimer(0);
    }

    private double calculateMorphSize(Morph morph, long timeDelta) {
        double newSize = morph.getRadius() + (((double) Morph.MAX_SIZE / Morph.META_DURATION) * timeDelta);
        return newSize > GameEntity.MAX_SIZE ? GameEntity.MAX_SIZE : newSize;
    }

    private void updatePlayer() {
        Window window = Renderer.getInstance().getWindow();

        if (window.isKeyPressed("up")) {
            GameState.getInstance().getPlayer().setYSpeed(-5);
        } else if (window.isKeyPressed("down")) {
            GameState.getInstance().getPlayer().setYSpeed(5);
        } else {
            GameState.getInstance().getPlayer().setYSpeed(0);
        }

        if (window.isKeyPressed("left")) {
            GameState.getInstance().getPlayer().setXSpeed(-5);
        } else if (window.isKeyPressed("right")) {
            GameState.getInstance().getPlayer().setXSpeed(5);
        } else {
            GameState.getInstance().getPlayer().setXSpeed(0);
        }
    }

    private void moveEntity() {
        for (GameEntity entity : GameState.getInstance().getEntities()) {
            entity.setX(entity.getX() + entity.getXSpeed());
            entity.setY(entity.getY() + entity.getYSpeed());
            checkWrapAround(entity);
        }

        checkCollision();
    }

    private void checkCollision() {
        Morph collidedMorph = getMorphCollidedWith();
        if (collidedMorph != null) {
            if (collidedMorph.isKiller()) {
                GameState.getInstance().loss();
            } else if (collidedMorph.isPrey()) {
                GameState.getInstance().removeMorph(collidedMorph);
                GameState.getInstance().setPoints(GameState.getInstance().getPoints() + collidedMorph.getPoints());
                GameState.getInstance().addMorph(Morph.createNewMorph());
                GameState.getInstance().addMorph(Morph.createNewMorph());
            }
        }
    }

    private Morph getMorphCollidedWith() {
        Player player = GameState.getInstance().getPlayer();
        for (Morph morph : GameState.getInstance().getMorphs()) {
            double dX = morph.getX() - player.getX();
            double dY = morph.getY() - player.getY();
            double distance = (dX * dX) + (dY * dY);

            if (distance < (morph.getRadius() + player.getRadius()) * (morph.getRadius() + player.getRadius())) {
                return morph;
            }
        }

        return null;
    }

    private void checkWrapAround(GameEntity entity) {
        Window window = Renderer.getInstance().getWindow();
        if (entity.getX() < 0) {
            entity.setX(window.getWidth());
        } else if (entity.getX() > window.getWidth()) {
            entity.setX(0);
        }
        if (entity.getY() < 0) {
            entity.setY(window.getHeight());
        } else if (entity.getY() > window.getHeight()) {
            entity.setY(0);
        }
    }
}