package model.dungeon;

import java.util.List;
import java.util.Map;

/**
 * Interface that generates the minimum spanning tree using th implementation of kruskal's
 * algorithm.
 */
interface IDungeonOperations {

  /**
   * Get the list of all the location objects.
   * @return the list of locations
   */
  List<Location> getLocationList();

  /**
   * Makes the minimum Spanning tree based on Kruskal's algorithm along with interconnectivity.
   * @return a map consisting location label mapped to list of possible paths.
   */
  Map<Integer, List<Integer>> mst();
}
