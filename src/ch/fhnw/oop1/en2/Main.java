package ch.fhnw.oop1.en2;

import ch.fhnw.oop1.en2.game.Game;
import ch.fhnw.oop1.en2.game.processor.Processor;

/**
 * This is the entrypoint for the Program that is the metamorpher minigame foor OOP1 HS23
 * <p>
 * The class simply contains the main-Method to start the Java application
 */
public class Main {

    /**
     * main-Method to start the Application
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
