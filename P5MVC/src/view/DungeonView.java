package view;

import model.dungeon.Directions;
import model.dungeon.Model;
import model.dungeon.TreasuresTypes;

import java.util.List;
import java.util.Map;

/**
 * Create the different views for dungeon like specifications form and dungeon window.
 */
public interface DungeonView extends DungeonForm {

  /**
   * create the dungeon.
   * @param model the model.
   */
  void createDungeon(Model model);

  /**
   * Display the game log like current location details.
   * @param currentLocation the current location details
   * @param arrowAtCurrentLoc number of arrows at current location.
   * @param smell smell type if monster present.
   * @param status if move made successfully or not.
   */
  void display(Map<Integer, Map<List<Directions>, List<TreasuresTypes>>> currentLocation,
               int arrowAtCurrentLoc,int smell,boolean status);

  /**
   * Get the player description.
   * @param description the description.
   */
  void getDescription(Map<String, Integer> description);

  /**
   * Send the message that arrow/treasure have been picked.
   * @param s the message.
   */
  void addLabel(String s);

  /**
   * Display dungeon specs.
   * @param rows the rows
   * @param cols the cols
   * @param interConnectivity the interconnectivity
   * @param type the type of dungeon
   * @param amount the percentage of treasure distributed
   * @param numOtyughs number of monsters.
   */
  void displayInfo(int rows, int cols, int interConnectivity, String type,
                   int amount, int numOtyughs);

  /**
   * Refresh the contents and repaint.
   */
  void refresh();

  /**
   * Display message if arrow hit or missed.
   * @param res the result from controller.
   */
  void shootInfo(int res);

  /**
   * Display the message that player has won.
   */
  void displayWinInfo();

  /**
   * Display message that player was killed by monster.
   */
  void displayLoseInfo();

  /**
   * Display popup if pit is nearby within 1 units.
   */
  void displayPitInfo();

  /**
   * Player killed by pit info.
   */
  void displayLoseInfoByPit();

  /**
   * Quit the game.
   */
  void exitGame();
}
