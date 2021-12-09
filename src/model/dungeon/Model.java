package model.dungeon;

import java.util.List;
import java.util.Map;

/**
 * Model which is used to communicate to controller. It allows the creation of dungeon and the
 * player is kept in the dungeon. The player can be moved and other operations like shooting,
 * picking up treasure can be done through this model.
 */
public interface Model extends ReadOnlyModel {

  /**
   * Get the number of rows of dungeon.
   * @return the total rows.
   */
  int getRows();

  /**
   * Get the number of columns of dungeon.
   * @return the total columns.
   */
  int getCols();

  /**
   * We define an interconnectivity = 0 when there is exactly one path from every cave in the
   * dungeon to every other cave in the dungeon. Increasing the degree of interconnectivity
   * increases the number of paths between caves.
   * @return the degree of interconnectivity
   */
  int interConnectivity();

  /**
   * Get the label of dungeon i.e. true (Wrapping) or false (NonWrapping).
   * @return true/ false
   */
  boolean getDungeonLabel();

  /**
   * Get the amount of treasure distributed in the caves.
   * @return the amount
   */
  int getAmount();

  /**
   * Get the number of Otyughs in the dungeon.
   * @return the number of otyughs.
   */
  int getNumOtyughs();


  /**
   * Get the player description like name, treasures picked up if any, current location.
   * @return description of player as a string.
   */
  Map<String, Integer> getDescription();

  /**
   * Method using which a player can collect the treasure from the current location.
   * @return true if treasure picked else false.
   */
  boolean pickTreasure();

  /**
   * Pick the arrow if it is available at that location.
   * @return true if arrow picked else false.
   */
  boolean pickArrow();

  /**
   * Shoot the arrow to kill the monster.
   * @param dir the direction to shoot
   * @param distance the distance
   * @return true/false based on if monster gets the hit.
   */
  int shoot(Directions dir, int distance);

  /**
   * Make the player move in the given direction.
   * @param direction the direction in which we want to make the player move.
   * @return true if move made successfully else false.
   */
  boolean makeMove(Directions direction);

  /**
   * Get the starting cave location.
   * @return the location label
   */
  int getStart();

  /**
   * Get the ending cave location.
   * @return the location label
   */
  int getEnd();

  /**
   * Return the dungeon.
   * @return the dungeon.
   */
  Dungeon getDungeon();

  /**
   * Detect if pit is present or not.
   * @return true if present or false.
   */
  boolean detectPit();

  /**
   * Get the player from the dungeon.
   * @return the player.
   */
  Player getPlayer();

  /**
   * Get the number of arrows at current location.
   * @return the number of arrows.
   */
  int getNumArrows();

  /**
   * Check if the player has reached the destination.
   * @return true if reached else false.
   */
  boolean isReachedDest();

  /**
   * Get arrows at current location.
   * @return num of arrows.
   */
  int getArrowAtCurrentLoc();

  /**
   * Get the list of locations.
   * @return the list
   */
  List<Location> getLocationList();

  /**
   * Get number of pits in the dungeon.
   * @return number of pits.
   */
  int getNumPits();

  /**
   * Get number of thieves in the dungeon.
   * @return number of thieves.
   */
  int getNumThieves();
}
