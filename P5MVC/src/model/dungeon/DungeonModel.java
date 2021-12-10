package model.dungeon;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * It creates the dungeon model which contains player and allows various operations to be
 * performed by player.
 */
public class DungeonModel implements Model {

  private final int rows;
  private final int cols;
  private final int interconnectivity;
  private final boolean dungeonLabel;
  private final int amount;
  private final int numOtyughs;
  private final Dungeon dungeon;
  private final Player player;
  private final int start;
  private final int end;

  /**
   * Create the dungeon model with the player.
   * @param rows the number of rows for dungeon.
   * @param cols the number of cols for dungeon.
   * @param interconnectivity the interconnectivity for dungeon.
   * @param randomseed the seed for randomness.
   * @param dungeonLabel the type of dungeon (Wrapping/ NonWrapping).
   * @param amount the amount of treasure and arrows to be distributed.
   * @param numOtyughs the number of monsters to be allowed in dungeon.
   * @throws IllegalArgumentException if invalid rows/cols/interconnectivity/label provided
   */
  public DungeonModel(int rows, int cols, int interconnectivity, int randomseed,
                      boolean dungeonLabel, int amount, int numOtyughs, int numPits, int numThieves)
          throws IllegalArgumentException {
    if (rows < 3 || cols < 3) {
      throw new IllegalArgumentException("Rows and columns cannot be less than 3");
    }
    if (rows * cols < 5) {
      throw new IllegalArgumentException("The product of rows and columns should be at least 5 "
              + "for a valid dungeon");
    }
    if (interconnectivity < 0) {
      throw new IllegalArgumentException("Interconnectivity cannot be less than 0");
    }
    if (amount < 0) {
      throw new IllegalArgumentException("Amount to be distributed cannot be zero");
    }
    if (numOtyughs < 1 || numOtyughs > rows * cols) {
      throw new IllegalArgumentException("Invalid number of monsters provided");
    }
    if (numPits < 0) {
      throw new IllegalArgumentException("Pits cannot be less than 0");
    }
    if (numThieves < 0) {
      throw new IllegalArgumentException("Thieves cannot be less than 0");
    }
    this.rows = rows;
    this.cols = cols;
    this.interconnectivity = interconnectivity;
    this.dungeonLabel = dungeonLabel;
    this.amount = amount;
    this.numOtyughs = numOtyughs;
    if (dungeonLabel) {
      this.dungeon = new DungeonMaker(rows, cols, interconnectivity,
              randomseed, "Wrapping", amount, numOtyughs, numPits, numThieves);
    }
    else {
      this.dungeon = new DungeonMaker(rows, cols, interconnectivity,
              randomseed, "NonWrapping", amount, numOtyughs, numPits, numThieves);
    }
    this.start = this.dungeon.getStart();
    this.end = this.dungeon.getEnd();
    this.player = new PlayerGenerator("Alex",this.dungeon);
  }

  /**
   * Copy constructor of the Model.
   * @param d the dungeon
   */
  public DungeonModel(Model d) {
    Objects.requireNonNull(d);
    this.rows = d.getRows();
    this.cols = d.getCols();
    this.interconnectivity = d.interConnectivity();
    this.dungeonLabel = d.getDungeonLabel();
    this.amount = d.getAmount();
    this.numOtyughs = d.getNumOtyughs();
    this.start = d.getStart();
    this.end = d.getEnd();
    this.dungeon = d.getDungeon();
    this.player = d.getPlayer();
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
  public int interConnectivity() {
    return this.interconnectivity;
  }

  @Override
  public boolean getDungeonLabel() {
    return this.dungeonLabel;
  }

  @Override
  public int getAmount() {
    return this.amount;
  }

  @Override
  public int getNumOtyughs() {
    return this.numOtyughs;
  }

  @Override
  public Map<String, Integer> getDescription() {
    Map<String, Integer> temp = this.player.getDescription();
    return temp;
  }

  @Override
  public boolean pickTreasure() throws IllegalArgumentException {
    return this.player.pickTreasure();
  }

  @Override
  public boolean pickArrow() {
    return this.player.pickArrow();
  }

  @Override
  public int detectSmell() {
    return this.player.detectSmell();
  }

  @Override
  public int shoot(Directions dir, int distance) {
    return this.player.shoot(dir,distance);
  }

  @Override
  public boolean makeMove(Directions direction) {
    return this.player.makeMove(direction);
  }

  @Override
  public boolean isPlayerAlive() {
    return this.player.isPlayerAlive();
  }

  @Override
  public Map<Integer, Map<List<Directions>, List<TreasuresTypes>>> getCurrentLocation() {
    Map<Integer, Map<List<Directions>,
            List<TreasuresTypes>>> temp = this.player.getCurrentLocation();
    return temp;
  }

  @Override
  public List<Integer> getPlayerLocRowColIndex() {
    List<Integer> temp = this.player.getCurrentLocRowIndex();
    return temp;
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
  public Dungeon getDungeon() {
    return this.dungeon;
  }

  @Override
  public boolean detectPit() {
    return this.player.detectPit();
  }

  @Override
  public boolean isTreasureStolen() {
    return this.player.isTreasureStolen();
  }

  @Override
  public boolean isKilledByPit() {
    return this.player.isKilledByPit();
  }

  @Override
  public Player getPlayer() {
    return this.player;
  }

  @Override
  public String toString() {
    return this.dungeon.toString();
  }

  @Override
  public int getNumArrows() {
    return this.player.getNumOfArrows();
  }

  @Override
  public boolean isReachedDest() {
    return this.player.isReachedDest();
  }

  @Override
  public int getArrowAtCurrentLoc() {
    return this.player.getArrowAtCurrentLoc();
  }

  @Override
  public List<Location> getLocationList() {
    List<Location> temp = this.dungeon.getLocationList();
    return temp;
  }

  @Override
  public int getNumPits() {
    return this.dungeon.getNumPits();
  }

  @Override
  public int getNumThieves() {
    return this.dungeon.getNumThieves();
  }
}
