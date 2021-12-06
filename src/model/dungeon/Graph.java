package model.dungeon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Graph {

  private final List<model.dungeon.Location> locationList;
  private final HashMap<Integer, List<Integer>> paths;
  private int nLocations;

  /**
   * Creates a graph with list of locations (nodes), number of locations and
   * possible paths empty map.
   */
  public Graph() {
    locationList = new ArrayList<>();
    nLocations = 0;
    paths = new HashMap<>();
  }

  /**
   * Adds the node to the list of locations and the possible paths map.
   * @param lab the label of the location.
   * @param x array of x direction (1 if it can move forward/backward)
   * @param y array of y direction (1 if it can move up/down)
   */
  public void addLocation(int lab, int[] x, int[] y) {
    locationList.add(new model.dungeon.LocationGenerator(lab, x, y));
    paths.put(locationList.get(nLocations++).getLabel(), new ArrayList<>());
  }

  /**
   * Adds the possible edge between the nodes.
   * @param start label of starting node
   * @param end label of ending node
   */
  public void addEdge(int start, int end) {
    List<Integer> p = paths.get(start);
    p.add(end);
    paths.put(locationList.get(start).getLabel(), p);
  }

  /**
   * Returns the map of possible paths.
   * @return the map
   */
  public Map<Integer, List<Integer>> getPaths() {
    return paths;
  }

  /**
   * Get the list which contains all the location nodes.
   * @return the list of locations
   */
  public List<Location> getLocationList() {
    return locationList;
  }

}
