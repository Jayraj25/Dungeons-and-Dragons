import controller.DungeonControllerImpl;
import view.DungeonForm;
import view.SpecsForm;

/**
 * Main driver class where the simulation takes place. The dungeon view is created and
 * passed to the controller which handles the simulation based on given inputs.
 */
public class Game {
  /**
   * The driver main method that runs the program.
   * @param args command line arguments.
   */
  public static void main(String[] args) {
    // Assemble all the pieces of the MVC
    DungeonForm v = new SpecsForm("MVC Game");
    DungeonControllerImpl c = new DungeonControllerImpl(v);
  }
}
