package model.dungeon;

import java.util.ArrayList;
import java.util.List;

/**
 * Create the location i.e. a tunnel or cave based on the given condition.
 */
class LocationGenerator implements Location {

  private final int label;
  private boolean isVisited;
  private final int[] directionX;
  private final int[] directionY;
  private List<Directions> directionsList;
  private final List<model.dungeon.TreasuresTypes> treasuresTypesList;
  private boolean isCave;
  private int arrowsCount;
  private Monsters monsters;
  private boolean monsterAssigned;
  private int monsterHealth;

  /**
   * Constructs a location for given position of a 2d array.
   * @param lab the label(name) of the node.
   * @param directionX array with if move is possible in -x and +x direction
   * @param directionY array with if move is possible in -y and +y direction
   */
  public LocationGenerator(int lab, int[] directionX, int[] directionY) {
    label = lab;
    isVisited = false;
    this.directionX = directionX;
    this.directionY = directionY;
    this.directionsList = new ArrayList<>();
    this.treasuresTypesList = new ArrayList<>();
    this.isCave = true;
    this.arrowsCount = 0;
    this.monsterAssigned = false;
    this.monsters = null;
    this.monsterHealth = 0;
  }

  @Override
  public boolean isMonsterAssigned() {
    return monsterAssigned;
  }

  @Override
  public int getMonsterHealth() {
    return monsterHealth;
  }

  @Override
  public void reduceMonsterHealth() {
    this.monsterHealth = this.monsters.reduceMonsterHealth();
  }

  @Override
  public void setMonsterAssigned(boolean monsterAssigned) {
    this.monsterAssigned = monsterAssigned;
    this.monsterHealth = this.monsters.getHealth();
  }


  @Override
  public void makeMonster() {
    this.monsters = new Otyugh();
  }

  @Override
  public void destroyMonster() {
    if (this.monsters.getHealth() == 100) {
      this.monsters.reduceMonsterHealth();
    }
    else {
      this.monsters = null;
    }
  }

  @Override
  public int getArrowsCount() {
    return this.arrowsCount;
  }

  @Override
  public void makeChangeInArrowCount(int arrowsCount) {
    this.arrowsCount += arrowsCount;
  }

  @Override
  public void setDirectionsList(List<Directions> l) {
    this.directionsList = l;
  }

  @Override
  public List<Directions> getDirectionsList() {
    return this.directionsList;
  }

  @Override
  public boolean getIsVisited() {
    return isVisited;
  }

  @Override
  public void setVisited(boolean visited) {
    this.isVisited = visited;
  }

  @Override
  public void setIsCave(boolean cave) {
    isCave = cave;
  }

  @Override
  public boolean isCave() {
    return this.isCave;
  }

  @Override
  public List<TreasuresTypes> getTreasuresList() {
    return treasuresTypesList;
  }

  @Override
  public int getLabel() {
    return label;
  }

  @Override
  public int[] getDirectionX() {
    return directionX;
  }

  @Override
  public int[] getDirectionY() {
    return directionY;
  }

  @Override
  public String toString() {
    return "Location{"
            + "label=" + this.label
            + ", directions=" + this.directionsList
            + ", isCave=" + this.isCave
            + ", treasureList=" + this.treasuresTypesList
            + ", numOfArrows=" + this.arrowsCount
            + ", Monster=" + this.monsters
            + '}';
  }
}
