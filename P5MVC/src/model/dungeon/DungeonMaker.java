package model.dungeon;

import rand.RandomGenerator;
import rand.RandomGeneratorImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class that shares the common functionality of wrapping and non-wrapping dungeon like
 * creation of dungeon with given rows, cols, interconnectivity, retrieving of dungeon,
 * adding treasure.
 */
class DungeonMaker implements model.dungeon.Dungeon {

  private final int rows;
  private final int cols;
  private final int numPits;
  private final int numThieves;
  private final Map<Integer, List<Integer>> actualPaths;
  private final List<model.dungeon.Location> locationList;
  private int pathLength;
  private final List<model.dungeon.Location> listOfCaves;
  private final RandomGenerator rand;
  private int start;
  private int end;

  /**
   * Constructs a dungeon with given rows, columns and interconnectivity.
   * @param rows number of rows
   * @param cols number of columns
   * @param interconnectivity the degree of interconnectivity
   * @param dungeonLabel the label of whether to make wrapping or non-wrapping dungeon
   */
  public DungeonMaker(int rows, int cols, int interconnectivity, int randomSeed,
                      String dungeonLabel, int amount, int numOtyughs,
                      int numPits, int numThieves) {
    this.rows = rows;
    this.cols = cols;
    model.dungeon.DungeonOperations g = new model.dungeon.DungeonOperations(
            rows, cols, interconnectivity,
            randomSeed, dungeonLabel);
    this.actualPaths = g.mst();
    this.numPits = numPits;
    this.numThieves = numThieves;
    this.locationList = g.getLocationList();
    this.pathLength = 0;
    this.rand = new RandomGeneratorImpl(randomSeed); //0
    this.start = 0;
    this.end = 0;
    assignDirections(this.actualPaths);
    assignLocationType(this.locationList);
    makePath(this.actualPaths);
    this.listOfCaves = makeListOfCaves();
    addTreasure(amount);
    assignArrows(amount);
    addMonsters(numOtyughs);
    addPit(numPits);
    addThief(numThieves);
  }

  private void addThief(int numThieves) {
    List<Integer> temp = new ArrayList<>();
    for (int i = 0; i < numThieves; ) {
      int randNum1 = rand.getRandomNumber(0, locationList.size() - 1);
      Location l1 = locationList.get(randNum1);
      while (l1.getLabel() == end) {
        randNum1 = rand.getRandomNumber(0, locationList.size() - 1);
        l1 = locationList.get(randNum1);
      }
      if (!temp.contains(l1.getLabel())) {
        l1.setThiefPresent();
        temp.add(l1.getLabel());
        i++;
      }
    }
  }

  private void addPit(int numPits) {
    List<Integer> temp = new ArrayList<>();
    for (int i = 0; i < numPits; ) {
      int randNum1 = rand.getRandomNumber(0, locationList.size() - 1);
      Location l1 = locationList.get(randNum1);
      while (l1.getLabel() == end) {
        randNum1 = rand.getRandomNumber(0, locationList.size() - 1);
        l1 = locationList.get(randNum1);
      }
      if (!temp.contains(l1.getLabel())) {
        l1.setPitPresent();
        temp.add(l1.getLabel());
        i++;
      }
    }
  }

  private void addMonsters(int numOtyughs) {
    if (numOtyughs > listOfCaves.size()) {
      throw new IllegalArgumentException("Unable to add");
    }
    locationList.get(end).makeMonster();
    locationList.get(end).setMonsterAssigned(true);
    List<Integer> track = new ArrayList<>();
    track.add(end);
    track.add(start);
    int count = 0;
    while (count < numOtyughs - 1) {
      int randNum = rand.getRandomNumber(0, locationList.size() - 1);
      if (!track.contains(randNum)) {
        model.dungeon.Location l = locationList.get(randNum);
        if (l.isCave()) {
          if (!l.isMonsterAssigned()) {
            l.makeMonster();
            l.setMonsterAssigned(true);
            count++;
          }
        }
      }
    }
  }

  private void makePath(Map<Integer, List<Integer>> p) {
    while (this.pathLength < 5) {
      this.start = rand.getRandomNumber(0, locationList.size() - 1);
      this.end = rand.getRandomNumber(0, locationList.size() - 1);
      while (!locationList.get(this.start).isCave()) {
        this.start = rand.getRandomNumber(0, locationList.size() - 1);
      }
      while (!locationList.get(this.end).isCave()) {
        this.end = rand.getRandomNumber(0, locationList.size() - 1);
      }
      while (this.start == this.end) {
        this.end = rand.getRandomNumber(0, locationList.size() - 1);
        while (!locationList.get(this.end).isCave()) {
          this.end = rand.getRandomNumber(0, locationList.size() - 1);
        }
      }
      for (model.dungeon.Location l : locationList) {
        l.setVisited(false);
      }
      Map<Integer, Integer> distMap = bfsCall(start);
      this.pathLength = distMap.get(end);
    }
  }

  private Map<Integer, Integer> bfsCall(int start) {
    Map<Integer, Integer> distanceMap = new HashMap<>();
    List<Integer> queue = new ArrayList<>();
    queue.add(start);
    locationList.get(start).setVisited(true);
    distanceMap.put(start, 0);
    while (queue.size() != 0) {
      model.dungeon.Location currentNode = locationList.get(queue.remove(0));
      for (Integer i : actualPaths.get(currentNode.getLabel())) {
        if (!locationList.get(i).getIsVisited()) {
          queue.add(i);
          distanceMap.put(i,distanceMap.get(currentNode.getLabel()) + 1);
          locationList.get(i).setVisited(true);
        }
      }
      if (currentNode.getLabel() == end) {
        return distanceMap;
      }
    }
    return distanceMap;
  }


  private void assignLocationType(List<model.dungeon.Location> locationList) {
    for (model.dungeon.Location l : locationList) {
      if (l.getDirectionsList().size() == 2) {
        l.setIsCave(false);
      }
    }
  }

  private void assignDirections(Map<Integer, List<Integer>> actualPaths) {
    actualPaths.forEach(
        (key, value) -> {
          List<model.dungeon.Directions> directionsList = new ArrayList<>();
          for (Integer i : value) {
            if (rows == 1) {
              if (i - key < 0) {
                directionsList.add(model.dungeon.Directions.WEST);
              } else if (i - key > 0) {
                directionsList.add(model.dungeon.Directions.EAST);
              }
            }
            else if (rows == cols) {
              if (i - key == 1 || i - key == -(getRows() - 1)) {
                directionsList.add(model.dungeon.Directions.EAST);
              } else if (i - key == -1 || i - key == (getRows() - 1)) {
                directionsList.add(model.dungeon.Directions.WEST);
              } else {
                assignSouthNorth(key, directionsList, i);
              }
            }
            else if ((rows - cols)  < 0 || (rows - cols)  > 1) {
              if (i - key == 1 || i - key == -(getCols() - 1)) {
                directionsList.add(model.dungeon.Directions.EAST);
              } else if (i - key == -1 || i - key == (getCols() - 1)) {
                directionsList.add(model.dungeon.Directions.WEST);
              } else {
                assignSouthNorth(key, directionsList, i);
              }
            }
            else {
              if (i - key == 1 || i - key == -getRows()) {
                directionsList.add(model.dungeon.Directions.EAST);
              } else if (i - key == -1 || i - key == getRows()) {
                directionsList.add(model.dungeon.Directions.WEST);
              } else {
                assignSouthNorth(key, directionsList, i);
              }
            }
          }
          model.dungeon.Location loc = locationList.get(key);
          loc.setDirectionsList(directionsList);
        });
  }

  private void assignSouthNorth(Integer key, List<model.dungeon.Directions> directionsList,
                                Integer i) {
    if (i - key == getCols() || i - key == -(getCols() * (getRows() - 1))) {
      directionsList.add(model.dungeon.Directions.SOUTH);
    } else if (i - key == -getCols() || i - key == (getCols() * (getRows() - 1))) {
      directionsList.add(model.dungeon.Directions.NORTH);
    }
  }

  @Override
  public int getStart() {
    return this.start;
  }

  @Override
  public int getEnd() {
    return this.end;
  }

  @Override
  public Map<Integer, List<Integer>> getActualPaths() {
    return actualPaths;
  }

  @Override
  public List<model.dungeon.Location> getLocationList() {
    return this.locationList;
  }

  @Override
  public int getRows() {
    return this.rows;
  }

  @Override
  public int getCols() {
    return this.cols;
  }

  @Override
  public List<model.dungeon.Location> makeListOfCaves() {
    List<model.dungeon.Location> loc = getLocationList();
    List<model.dungeon.Location> caves = new ArrayList<>();
    for (model.dungeon.Location l : loc ) {
      if (l.isCave()) {
        caves.add(l);
      }
    }
    return caves;
  }

  @Override
  public int getNumPits() {
    return this.numPits;
  }

  @Override
  public int getNumThieves() {
    return numThieves;
  }

  private void assignArrows(int amount) {
    int numLocationsWithArrows = (int) Math.round((double) (
            amount * locationList.size()) / (double) 100);
    int count = 0;
    List<Location> temp = new ArrayList<>();
    while (count < numLocationsWithArrows) {
      model.dungeon.Location l = locationList.get(rand.getRandomNumber(0,locationList.size() - 1));
      if (!temp.contains(l)) {
        l.makeChangeInArrowCount(1);
        count++;
        temp.add(l);
      }
    }
  }

  private void addTreasure(int amount) {
    List<model.dungeon.TreasuresTypes> allTreasuresTypesList = new ArrayList<>(
            Arrays.asList(model.dungeon.TreasuresTypes.values()));

    int numCavesWithTreasure = (int) Math.round((double) (
            amount * listOfCaves.size()) / (double) 100);
    int count = 0;
    int countDiamonds = 0;
    int countRuby = 0;
    int countTopaz = 0;
    int countSapphire = 0;
    while (count < numCavesWithTreasure) {
      model.dungeon.Location l = listOfCaves.get(rand.getRandomNumber(0,listOfCaves.size() - 1));
      model.dungeon.TreasuresTypes tr = allTreasuresTypesList.get(
              rand.getRandomNumber(0, allTreasuresTypesList.size() - 1));
      if (tr.equals(model.dungeon.TreasuresTypes.DIAMOND)) {
        countDiamonds++;
      }
      else if (tr.equals(model.dungeon.TreasuresTypes.RUBY)) {
        countRuby++;
      }
      else if (tr.equals(model.dungeon.TreasuresTypes.TOPAZ)) {
        countTopaz++;
      }
      else {
        countSapphire++;
      }
      List<model.dungeon.TreasuresTypes> lt = l.getTreasuresList();
      lt.add(tr);
      if (lt.size() == 1) {
        count++;
      }
    }
    if (countDiamonds == 0 && numCavesWithTreasure != 0) {
      model.dungeon.Location l = listOfCaves.get(rand.getRandomNumber(0,listOfCaves.size() - 1));
      List<model.dungeon.TreasuresTypes> lt = l.getTreasuresList();
      lt.add(model.dungeon.TreasuresTypes.DIAMOND);
    }
    if (countRuby == 0 && numCavesWithTreasure != 0) {
      model.dungeon.Location l = listOfCaves.get(rand.getRandomNumber(0,listOfCaves.size() - 1));
      List<model.dungeon.TreasuresTypes> lt = l.getTreasuresList();
      lt.add(model.dungeon.TreasuresTypes.RUBY);
    }
    if (countSapphire == 0 && numCavesWithTreasure != 0) {
      model.dungeon.Location l = listOfCaves.get(rand.getRandomNumber(0,listOfCaves.size() - 1));
      List<model.dungeon.TreasuresTypes> lt = l.getTreasuresList();
      lt.add(model.dungeon.TreasuresTypes.SAPPHIRE);
    }
    if (countTopaz == 0 && numCavesWithTreasure != 0) {
      model.dungeon.Location l = listOfCaves.get(rand.getRandomNumber(0,listOfCaves.size() - 1));
      List<model.dungeon.TreasuresTypes> lt = l.getTreasuresList();
      lt.add(TreasuresTypes.SAPPHIRE);
    }
  }
}
