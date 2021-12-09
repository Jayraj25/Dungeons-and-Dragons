package model.dungeon;

import java.util.List;
import java.util.Map;

/**
 * This read only model is used by the view as well
 * to avoid the change of state of model.
 */
public interface ReadOnlyModel {
  /**
   * Get the row and column index of the player's current location.
   * @return row index at 0th index and column index at 1st index in the list.
   */
  List<Integer> getPlayerLocRowColIndex();

  /**
   * Get the current location of the player.
   * @return the location.
   */
  Map<Integer, Map<List<Directions>, List<TreasuresTypes>>> getCurrentLocation();

  /**
   * Detect the smell of the monster if the monster is nearby one or two blocks.
   * @return 1 if monster present within 1 block, 2 if monster present within 2 blocks else 0.
   */
  int detectSmell();

  /**
   * Check if the player is still alive or not.
   * @return true if player alive else false.
   */
  boolean isPlayerAlive();

  /**
   * Check if player killed by pit.
   * @return true if killed by pit else false.
   */
  boolean isKilledByPit();

  /**
   * Check if treasure stolen by thief.
   * @return true if stolen or not.
   */
  boolean isTreasureStolen();


}
