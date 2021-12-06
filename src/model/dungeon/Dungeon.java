package model.dungeon;

import java.util.List;
import java.util.Map;

/**
 * Dungeon creation is carried out here (wrapping and non-wrapping). Along with this locations are
 * also created and assign it as tunnel or cave. The dungeon is created based on the numberOfRows,
 * numberOfColumns and interconnectivity.
 */
interface Dungeon {

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
   * To get the map of list of all possible paths from the nodes.
   * @return map containing location label and list of possible paths for that location.
   */
  Map<Integer, List<Integer>> getActualPaths();

  /**
   * Get the list of location objects.
   * @return the list of locations
   */
  List<Location> getLocationList();

  /**
   * If the location is cave then add it to the list of caves.
   * @return the list of caves.
   */
  List<Location> makeListOfCaves();
}
