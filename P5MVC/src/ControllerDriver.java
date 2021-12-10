import controller.DungeonConsoleController;
import controller.DungeonController;
import model.dungeon.DungeonModel;
import model.dungeon.Model;

import java.io.InputStreamReader;

/**
 * Main driver class where the simulation takes place. The dungeon model is created and
 * passed to the controller which handles the simulation based on given inputs.
 */
public class ControllerDriver {
  /**
   * The driver main method that runs the program.
   * @param args command line arguments.
   */
  public static void main(String[] args) {
    try {
      // int row = Integer.parseInt(args[0]);
      // int col = Integer.parseInt(args[1]);
      // int connectivity = Integer.parseInt(args[2]);
      // boolean type = Boolean.parseBoolean(args[3]);
      // int amountTreasure = Integer.parseInt(args[4]);
      // int numOtyughs = Integer.parseInt(args[5]);
      // Model m = new DungeonModel(new DungeonModel(row,col,connectivity,
      // -1,type,amountTreasure,numOtyughs));
      Model m = new DungeonModel(new DungeonModel(5,6,2,
             1,true,50,2,1,1));
      Readable in = new InputStreamReader(System.in);
      DungeonController control = new DungeonConsoleController(in,System.out);
      control.playGame(m);
    }
    catch (IllegalArgumentException ie) {
      System.out.println(ie.getMessage());
      System.exit(0);
    }
  }
}
