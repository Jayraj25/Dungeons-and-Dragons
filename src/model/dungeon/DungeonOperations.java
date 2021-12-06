package model.dungeon;

import rand.RandomGenerator;
import rand.RandomGeneratorImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class that consists the internal implementation of how dungeon is created.
 * It uses some private helper functions to complete the task.
 */
class DungeonOperations implements model.dungeon.IDungeonOperations {

  private final int rows;
  private final int cols;
  private final model.dungeon.Graph a;
  private final List<List<Integer>> possiblePaths;
  private final List<List<Integer>> leftOvers ;
  private final Map<Integer, List<Integer>> actualPaths;
  private final int interconnectivity;
  private final List<model.dungeon.Location> locationList;
  private final RandomGenerator r;

  /**
   * Constructs Dungeon based on the rows, cols and interconnectivity.
   * @param rows the number of rows in the dungeon
   * @param cols the number of columns in the dungeon
   * @param interconnectivity the degree of interconnectivity
   * @param dungeonLabel the label (Wrapping/NonWrapping) of dungeon
   */
  public DungeonOperations(int rows, int cols, int interconnectivity,
                           int rand, String dungeonLabel) {
    this.rows = rows;
    this.cols = cols;
    this.a = new model.dungeon.Graph();
    this.possiblePaths = new ArrayList<>();
    this.leftOvers = new ArrayList<>();
    this.actualPaths = new HashMap<>();
    this.interconnectivity = interconnectivity;
    this.r = new RandomGeneratorImpl(rand + 6); //6
    possibleDirections();
    this.locationList = a.getLocationList();
    if (dungeonLabel.equals("NonWrapping")) {
      linkAllDirectionsXNonWrapping(locationList);
      linkAllDirectionsYNonWrapping(locationList);
    }
    else {
      linkAllDirectionsXWrapping(locationList);
      linkAllDirectionsYWrapping(locationList);
    }
    Map<Integer, List<Integer>> allPathsMap = a.getPaths();
    linkValidPaths(allPathsMap);
  }

  @Override
  public List<model.dungeon.Location> getLocationList() {
    return locationList;
  }

  @Override
  public Map<Integer, List<Integer>> mst() {
    List<List<Integer>> mst = new ArrayList<>();
    int count = 0;
    while (count < possiblePaths.size()) {
      int t = r.getRandomNumber(0,possiblePaths.size() - 1);
      List<Integer> l = possiblePaths.get(t);
      if (mst.size() == 0) {
        mst.add(l);
      }
      else {
        List<Integer> tempList1 = new ArrayList<>();
        List<Integer> tempList2 = new ArrayList<>();
        for (List<Integer> temp : mst) {
          if (temp.contains(l.get(0))) {
            tempList1 = temp;
          }
          if (temp.contains(l.get(1))) {
            tempList2 = temp;
          }
        }
        if (!tempList1.isEmpty() && tempList2.isEmpty()) {
          List<Integer> w = mst.get(mst.indexOf(tempList1));
          w.add(l.get(1));
        }
        else if (tempList1.isEmpty() && !tempList2.isEmpty()) {
          List<Integer> w = mst.get(mst.indexOf(tempList2));
          w.add(l.get(0));
        }
        else if (tempList1.isEmpty() && tempList2.isEmpty()) {
          mst.add(l);
        }
        else if (tempList1.equals(tempList2)) {
          leftOvers.add(l);
        }
        else {
          mst.remove(mst.get(mst.indexOf(tempList1)));
          mst.remove(mst.get(mst.indexOf(tempList2)));
          tempList1.addAll(tempList2);
          mst.add(tempList1);
        }
      }
      if (actualPaths.containsKey(l.get(0)) && actualPaths.containsKey(l.get(1))) {
        if (!leftOvers.contains(l)) {
          List<Integer> ls1 = actualPaths.get(l.get(0));
          ls1.add(l.get(1));
          actualPaths.put(l.get(0),ls1);
          List<Integer> ls2 = actualPaths.get(l.get(1));
          ls2.add(l.get(0));
          actualPaths.put(l.get(1),ls2);
        }
      }
      else if (!actualPaths.containsKey(l.get(0)) && actualPaths.containsKey(l.get(1))) {
        List<Integer> ls1 = actualPaths.get(l.get(1));
        ls1.add(l.get(0));
        actualPaths.put(l.get(1),ls1);
        List<Integer> ls2 = new ArrayList<>();
        ls2.add(l.get(1));
        actualPaths.put(l.get(0),ls2);
      }
      else if (actualPaths.containsKey(l.get(0)) && !actualPaths.containsKey(l.get(1))) {
        List<Integer> ls1 = actualPaths.get(l.get(0));
        ls1.add(l.get(1));
        actualPaths.put(l.get(0),ls1);
        List<Integer> ls2 = new ArrayList<>();
        ls2.add(l.get(0));
        actualPaths.put(l.get(1),ls2);
      }
      else {
        List<Integer> mapVals1 = new ArrayList<>();
        List<Integer> mapVals2 = new ArrayList<>();
        mapVals1.add(l.get(1));
        mapVals2.add(l.get(0));
        this.actualPaths.put(l.get(0),mapVals1);
        this.actualPaths.put(l.get(1),mapVals2);
      }
      possiblePaths.remove(l);
    }
    Map<Integer, List<Integer>> paths = this.actualPaths;
    return mstFromInterConnectivity(paths);
  }


  private void possibleDirections() {
    int k = 0;
    for (int row = 0; row < rows; row++) {
      for (int col = 0; col < cols; col++) {
        int[] x = new int[2];
        int[] y = new int[2];
        if ((row == 0) && (col != 0 && col != cols - 1)) {
          if (rows != 1) {
            y[1] = 1;
          }
          x[0] = 1;
          x[1] = 1;
        }
        else if ((row == rows - 1) && (col != 0 && col != cols - 1)) {
          x[0] = 1;
          x[1] = 1;
          y[0] = 1;
        }
        else if ((row == 0) && (col == 0)) {
          if (rows != 1) {
            y[1] = 1;
          }
          x[1] = 1;
        }
        else if ((row == rows - 1) && (col == 0)) {
          x[1] = 1;
          y[0] = 1;
        }
        else if ((row == 0) && (col == cols - 1)) {
          if (rows != 1) {
            y[1] = 1;
          }
          x[0] = 1;
        }
        else if ((row == rows - 1) && (col == cols - 1)) {
          if (cols != 1) {
            x[0] = 1;
          }
          y[0] = 1;
        }
        else if ((row > 0 && row < rows - 1) && (col != 0 && col != cols - 1)) {
          x[0] = 1;
          x[1] = 1;
          y[0] = 1;
          y[1] = 1;
        }
        else if ((row > 0 && row < rows - 1) && (col == 0)) {
          if (cols != 1) {
            x[1] = 1;
          }
          y[0] = 1;
          y[1] = 1;
        }
        else {
          x[0] = 1;
          y[0] = 1;
          y[1] = 1;
        }
        a.addLocation(k,x,y);
        k++;
      }
    }
  }


  private void linkAllDirectionsXWrapping(List<model.dungeon.Location> v) {
    int count = 0;
    for (model.dungeon.Location p : v) {
      if (p.getDirectionX()[0] == 0 && p.getDirectionX()[1] == 1) {
        if (count % cols == 0) {
          a.addEdge(p.getLabel(),p.getLabel() + cols - 1);
        }
        a.addEdge(p.getLabel(),p.getLabel() + 1);
      }
      else if (p.getDirectionX()[0] == 1 && p.getDirectionX()[1] == 0) {
        a.addEdge(p.getLabel(),p.getLabel() - 1);
      }
      else if (p.getDirectionX()[0] == 1 && p.getDirectionX()[1] == 1) {
        a.addEdge(p.getLabel(),p.getLabel() - 1);
        a.addEdge(p.getLabel(),p.getLabel() + 1);
      }
      count++;
    }
  }

  private void linkAllDirectionsYWrapping(List<model.dungeon.Location> v) {
    int count = 0;
    for (model.dungeon.Location p : v) {
      if (p.getDirectionY()[0] == 0 && p.getDirectionY()[1] == 1) {
        a.addEdge(p.getLabel(),p.getLabel() + cols);
        if ((count % cols < cols) && (count / cols == 0)) {
          a.addEdge(p.getLabel(),p.getLabel() + cols * (rows - 1));
        }
      }
      else if (p.getDirectionY()[0] == 1 && p.getDirectionY()[1] == 0) {
        a.addEdge(p.getLabel(),p.getLabel() - cols);
      }
      else if (p.getDirectionY()[0] == 1 && p.getDirectionY()[1] == 1) {
        a.addEdge(p.getLabel(),p.getLabel() - cols);
        a.addEdge(p.getLabel(),p.getLabel() + cols);
      }
      count++;
    }
  }

  private void linkAllDirectionsXNonWrapping(List<model.dungeon.Location> v) {
    for (model.dungeon.Location p : v) {
      if (p.getDirectionX()[0] == 0 && p.getDirectionX()[1] == 1) {
        a.addEdge(p.getLabel(),p.getLabel() + 1);
      }
      else if (p.getDirectionX()[0] == 1 && p.getDirectionX()[1] == 0) {
        a.addEdge(p.getLabel(),p.getLabel() - 1);
      }
      else if (p.getDirectionX()[0] == 1 && p.getDirectionX()[1] == 1) {
        a.addEdge(p.getLabel(),p.getLabel() - 1);
        a.addEdge(p.getLabel(),p.getLabel() + 1);
      }
    }
  }

  private void linkAllDirectionsYNonWrapping(List<model.dungeon.Location> v) {
    for (Location p : v) {
      if (p.getDirectionY()[0] == 0 && p.getDirectionY()[1] == 1) {
        a.addEdge(p.getLabel(),p.getLabel() + cols);
      }
      else if (p.getDirectionY()[0] == 1 && p.getDirectionY()[1] == 0) {
        a.addEdge(p.getLabel(),p.getLabel() - cols);
      }
      else if (p.getDirectionY()[0] == 1 && p.getDirectionY()[1] == 1) {
        a.addEdge(p.getLabel(),p.getLabel() - cols);
        a.addEdge(p.getLabel(),p.getLabel() + cols);
      }
    }
  }

  private void linkValidPaths(Map<Integer,List<Integer>> ps) {
    for (Map.Entry<Integer, List<Integer>> m : ps.entrySet()) {
      List<Integer> temp = m.getValue();
      for (Integer i : temp) {
        List<Integer> s = new ArrayList<>();
        if (i > m.getKey()) {
          s.add(m.getKey());
          s.add(i);
          possiblePaths.add(s);
        }
      }
    }
  }


  private Map<Integer, List<Integer>> mstFromInterConnectivity(Map<Integer, List<Integer>> paths)
          throws IllegalArgumentException {
    if (interconnectivity > leftOvers.size()) {
      throw new IllegalArgumentException("Invalid Interconnectivity");
    }
    List<List<Integer>> listFromLeftOversBasedOnInterconnectivity = new ArrayList<>();
    for (int i = 0; i < this.interconnectivity; i++) {
      int t = r.getRandomNumber(0,this.leftOvers.size() - 1);
      listFromLeftOversBasedOnInterconnectivity.add(this.leftOvers.get(t));
      this.leftOvers.remove(t);
    }

    for (List<Integer> l : listFromLeftOversBasedOnInterconnectivity) {
      int t1 = l.get(0);
      int t2 = l.get(1);
      List<Integer> temp1 = paths.get(t1);
      temp1.add(t2);
      List<Integer> temp2 = paths.get(t2);
      temp2.add(t1);
      paths.put(t1,temp1);
      paths.put(t2,temp2);
    }
    return paths;
  }
}
