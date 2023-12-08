package ch.fhnw.oop1.en2;

import ch.fhnw.oop1.en2.game.Game;

/**
 * This is the entrypoint for the application that is the metamorpher minigame for OOP1 HS23
 *
 * The class simply contains the main-Method to start the Java application
 */
public class Main {

    /**
     * main-Method to start the application
     * When the {@link Game} class produces and {@link InterruptedException} the application will exit with code 1
     *
     * @param args Command-line arguments
     */
    public static void main(String[] args) {

        Game game = new Game();

        try {
            game.play();
        } catch (InterruptedException e) {
            System.out.println("Something went wrong with the game loop");
            e.printStackTrace();

            System.exit(1);
        }
    }
}
