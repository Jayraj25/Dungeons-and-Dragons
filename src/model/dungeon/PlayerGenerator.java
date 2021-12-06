package model.dungeon;

import rand.RandomGenerator;
import rand.RandomGeneratorImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Make the player for the dungeon game such that a player can move and pick up a treasure
 * and can kill monster with arrows. Player starts with 3 arrows by default.
 */
class PlayerGenerator implements Player {

  private final List<Location> locationList;
  private Location currentLocation;
  private final Map<Integer, List<Integer>> actualPaths;
  private Location lastLocation;
  private int numOfArrows;
  private final Map<TreasuresTypes,Integer> treasurePickedMap;
  private final RandomGenerator rand;
  private final int end;
  private boolean isAlive;

  /**
   * Constructs the player with a given name.
   * @param playerName the name of the player.
   * @param dungeon the dungeon where player will play the game.
   */
  public PlayerGenerator(String playerName, Dungeon dungeon) throws NullPointerException {
    Objects.requireNonNull(playerName);
    Objects.requireNonNull(dungeon);
    this.rand = new RandomGeneratorImpl(2);
    this.locationList = dungeon.getLocationList();
    this.lastLocation = locationList.get(dungeon.getStart());
    this.currentLocation = locationList.get(dungeon.getStart());
    this.actualPaths = dungeon.getActualPaths();
    this.numOfArrows = 3;
    this.end = dungeon.getEnd();
    this.treasurePickedMap = new HashMap<>();
    this.isAlive = true;
    for (TreasuresTypes t : TreasuresTypes.values()) {
      treasurePickedMap.put(t,0);
    }
  }

  @Override
  public Map<String, Integer> getDescription() {
    Map<String, Integer> temp = new HashMap<>();
    for (Map.Entry<TreasuresTypes, Integer> m : treasurePickedMap.entrySet()) {
      temp.put(m.getKey().toString(),m.getValue());
    }
    temp.put("Arrows",numOfArrows);
    return temp;
  }

  @Override
  public boolean pickTreasure() throws IllegalArgumentException {
    if (!this.isAlive) {
      throw new IllegalStateException("Player is dead");
    }
    else {
      List<TreasuresTypes> treasuresList = currentLocation.getTreasuresList();
      boolean flag;
      if (treasuresList.isEmpty()) {
        flag = false;
      }
      else {
        for (TreasuresTypes t : treasuresList) {
          Integer temp = treasurePickedMap.get(t);
          temp += 1;
          treasurePickedMap.put(t,temp);
        }
        currentLocation.getTreasuresList().clear();
        flag = true;
      }
      if (this.currentLocation.isMonsterAssigned()) {
        if (this.currentLocation.getMonsterHealth() != 100) {
          survivalChances();
        }
      }
      return flag;
    }
  }

  @Override
  public boolean pickArrow() {
    if (!this.isAlive) {
      throw new IllegalStateException("Player is dead");
    }
    else {
      int arrowAtCurrentLoc = currentLocation.getArrowsCount();
      boolean flag;
      if (arrowAtCurrentLoc != 0) {
        numOfArrows += arrowAtCurrentLoc;
        currentLocation.makeChangeInArrowCount(-arrowAtCurrentLoc);
        flag = true;
      }
      else {
        flag = false;
      }
      if (this.currentLocation.isMonsterAssigned()) {
        if (this.currentLocation.getMonsterHealth() != 100) {
          survivalChances();
        }
      }
      return flag;
    }
  }

  private Map<Integer, Integer> bfsCall(int start) {
    Map<Integer, Integer> distanceMap = new HashMap<>();
    List<Integer> queue = new ArrayList<>();
    queue.add(start);
    locationList.get(start).setVisited(true);
    distanceMap.put(start, 0);
    while (queue.size() != 0) {
      Location currentNode = locationList.get(queue.remove(0));
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

  @Override
  public int detectSmell() {
    locationList.forEach(
        (l) -> l.setVisited(false)
    );
    Map<Integer, Integer> distMap = bfsCall(this.currentLocation.getLabel());
    List<Integer> dist1 = new ArrayList<>();
    List<Integer> dist2 = new ArrayList<>();
    distMap.forEach(
        (key, value) -> {
          if (value == 1 && locationList.get(key).isMonsterAssigned()) {
            dist1.add(key);
          }
          else if (value == 2 && locationList.get(key).isMonsterAssigned()) {
            dist2.add(key);
          }
        }
    );
    if (!dist1.isEmpty() || dist2.size() > 1) {
      return 1;
    }
    else if (dist2.size() == 1) {
      return 2;
    }
    else {
      return 0;
    }
  }

  @Override
  public int shoot(Directions dir, int distance) {
    if (!this.isAlive) {
      throw new IllegalStateException("Player is dead");
    }
    else {
      if (this.numOfArrows <= 0) {
        return 4;
      }

      Directions direction = dir;
      Location arrowLocation = currentLocation;


      boolean flag = false;
      int distanceTrack = 0;

      while (!flag) {
        List<Directions> directionsList = arrowLocation.getDirectionsList();
        if (arrowLocation.isCave()) {
          if (arrowLocation.getDirectionsList().contains(direction) && distanceTrack < distance) {
            List<Integer> temp = actualPaths.get(arrowLocation.getLabel());
            int locInt = temp.get(directionsList.indexOf(direction));
            arrowLocation = locationList.get(locInt);
            if (arrowLocation.isCave()) {
              distanceTrack += 1;
            }
          }
          else {
            flag = true;
          }
        }
        else {
          if (arrowLocation.getDirectionsList().contains(direction) && distanceTrack < distance) {
            List<Integer> temp = actualPaths.get(arrowLocation.getLabel());
            int locInt = temp.get(directionsList.indexOf(direction));
            arrowLocation = locationList.get(locInt);
            if (arrowLocation.isCave()) {
              distanceTrack += 1;
            }
          }
          else if (!arrowLocation.getDirectionsList().contains(direction)
                  && distanceTrack < distance) {
            List<Integer> temp = actualPaths.get(arrowLocation.getLabel());
            Directions d = direction.getOppositeDirection();
            for (Directions direct : directionsList) {
              if (!direct.equals(d)) {
                direction = direct;
              }
            }
            int locInt = temp.get(directionsList.indexOf(direction));
            arrowLocation = locationList.get(locInt);
            if (arrowLocation.isCave()) {
              distanceTrack += 1;
            }
          }
          else {
            flag = true;
          }
        }
      }

      if (distanceTrack == distance) {
        if (arrowLocation.isMonsterAssigned()) {
          if (arrowLocation.getMonsterHealth() != 100) {
            arrowLocation.setMonsterAssigned(false);
            arrowLocation.destroyMonster();
            this.numOfArrows -= 1;
            return 1;
          }
          else {
            this.numOfArrows -= 1;
            arrowLocation.reduceMonsterHealth();
            return 2;
          }
        }
        else {
          this.numOfArrows -= 1;
          return 3;
        }
      }
      else {
        this.numOfArrows -= 1;
        return 3;
      }
    }
  }

  @Override
  public boolean makeMove(Directions direction) {
    Objects.requireNonNull(direction);
    boolean flag;
    if (this.isAlive) {
      this.lastLocation = currentLocation;
      Location loc = locationList.get(lastLocation.getLabel());
      List<Directions> directionsList = loc.getDirectionsList();
      if (!directionsList.contains(direction)) {
        flag = false;
      }
      else {
        List<Integer> temp = actualPaths.get(lastLocation.getLabel());
        int locInt = temp.get(directionsList.indexOf(direction));
        currentLocation = locationList.get(locInt);
        flag = true;
      }
      if (this.currentLocation.isMonsterAssigned()) {
        if (this.currentLocation.getMonsterHealth() == 100) {
          this.isAlive = false;
        }
        else {
          survivalChances();
        }
      }
      return flag;
    }
    else {
      throw new IllegalStateException("Player is dead");
    }
  }

  private void survivalChances() {
    int r = rand.getRandomNumber(0,2);
    if (r == 0) {
      this.isAlive = false;
    }
  }

  @Override
  public boolean isReachedDest() {
    return end == this.currentLocation.getLabel();
  }

  @Override
  public Map<Integer, Map<List<Directions>, List<TreasuresTypes>>> getCurrentLocation() {
    Map<Integer, Map<List<Directions>,List<TreasuresTypes>>> temp1 = new HashMap<>();
    Map<List<Directions>,List<TreasuresTypes>> temp2 = new HashMap<>();
    temp2.put(currentLocation.getDirectionsList(),currentLocation.getTreasuresList());
    temp1.put(currentLocation.getLabel(), temp2);
    return temp1;
  }

  @Override
  public boolean isPlayerAlive() {
    return this.isAlive;
  }

  @Override
  public int getNumOfArrows() {
    return this.numOfArrows;
  }

  @Override
  public int getArrowAtCurrentLoc() {
    return this.currentLocation.getArrowsCount();
  }

}
