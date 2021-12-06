package model.dungeon;

import java.util.List;
import java.util.Map;

/**
 * The movement of player, picking up of treasure, getting description of player takes place here.
 */
interface Player {

  /**
   * Get the player description like name, treasures picked up if any, current location.
   * @return description of player as a string.
   */
  Map<String, Integer> getDescription();

  /**
   * Method using which a player can collect the treasure from the current location.
   * @return true if treasure picked else false.
   */
  boolean pickTreasure() throws IllegalArgumentException;

  /**
   * Pick the arrow if it is available at that location.
   * @return true/false if player picked arrow successfully or not.
   */
  boolean pickArrow();

  /**
   * Detect the smell of the monster if the monster is nearby one or two blocks.
   * @return 1 if monster present within 1 block, 2 if monster present within 2 blocks else 0.
   */
  int detectSmell();

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
   * Check if the player has reached the destination.
   * @return true if reached else false
   */
  boolean isReachedDest();

  /**
   * Get the current location of the player.
   * @return the location.
   */
  Map<Integer, Map<List<Directions>, List<TreasuresTypes>>> getCurrentLocation();

  /**
   * Check if the player is alive or not.
   * @return true if alive else false.
   */
  boolean isPlayerAlive();

  /**
   * Get the number of arrows at current location.
   * @return the number of arrows.
   */
  int getNumOfArrows();

  /**
   * Get num of arrows at current location.
   * @return the num of arrows.
   */
  int getArrowAtCurrentLoc();
}
