package model.dungeon;

import java.util.List;

/**
 * Create the location with label. Here, the location is either identified as a cave or tunnel.
 * Location is created based on the position in a 2d array. A treasure is also to be added in
 * the location by random assignment.
 */
public interface Location {

  /**
   * get true is the monster is assigned or false.
   * @return true or false.
   */
  boolean isMonsterAssigned();

  /**
   * Get the monster health.
   * @return health
   */
  int getMonsterHealth();

  /**
   * Reduce the monster health.
   */
  void reduceMonsterHealth();

  /**
   * Set true if the monster is assigned.
   * @param monsterAssigned true/false.
   */
  void setMonsterAssigned(boolean monsterAssigned);

  /**
   * Make the monster in the location.
   */
  void makeMonster();

  /**
   * Destroy the monster if hit by two arrows.
   */
  void destroyMonster();

  /**
   * add a thief to the location.
   */
  void setThiefPresent();

  /**
   * Check if thief is present or not.
   * @return true if thief present else false.
   */
  boolean isThiefPresent();

  /**
   * Get the number of arrows in the cave.
   * @return arrows count
   */
  int getArrowsCount();

  /**
   * make changes to number of arrows in the cave.
   * @param arrowsCount the number to set the arrows count in caves.
   */
  void makeChangeInArrowCount(int arrowsCount);

  /**
   * Set the direction list.
   * @param l the list of directions
   */
  void setDirectionsList(List<Directions> l);

  /**
   * Get the list of directions in which a player can move.
   * @return the list with directions
   */
  List<Directions> getDirectionsList();

  /**
   * Get if player has visited this location.
   * @return true if visited or false.
   */
  boolean getIsVisited();

  /**
   * Change the is visited value if player has visited the location.
   * @param visited true/false.
   */
  void setVisited(boolean visited);

  /**
   * Set the location of cave as false if it has exactly 2 paths of incoming/outgoing
   * else set it as true.
   * @param cave the boolean value to set the location as cave or not.
   */
  void setIsCave(boolean cave);

  /**
   * Get the boolean value as true if it is cave or false.
   * @return true or false
   */
  boolean isCave();

  /**
   * Return the list of treasures allocated for this location.
   * @return list of treasures.
   */
  List<TreasuresTypes> getTreasuresList();

  /**
   * Get the label i.e. name of the location.
   * @return the label
   */
  int getLabel();

  /**
   * The direction list where 0th position signifies if it can move left and
   * 1st position signifies if it can move right.
   * @return array of XDirection.
   */
  int[] getDirectionX();

  /**
   * The direction list where 0th position signifies if it can move up and
   * 1st position signifies if it can move down.
   * @return array of YDirection.
   */
  int[] getDirectionY();

  /**
   * Set the pit present to true.
   */
  void setPitPresent();

  /**
   * Check if this location contains a pit or not.
   * @return true if pit present else false.
   */
  boolean isPitPresent();
}
