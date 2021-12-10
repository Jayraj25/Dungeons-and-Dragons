package model.dungeon;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Mocking the model.
 * This will be used for testing based on the methods called from the controller.
 * The log will append the function call to appendable.
 */
public class MockModel implements Model {

  private final Appendable log;

  /**
   * Constructs Mock model.
   * @param log appendable that keeps the log.
   */
  public MockModel(int r, int c, int i, int a, int b, int e, int m, int n,Appendable log) {
    Dungeon dungeon = new DungeonMaker(r, c, i,
            a, "Wrapping", b, e, m, n);
    this.log = log;
  }

  @Override
  public int getRows() {
    try {
      log.append("getRows method called.\n");
    }
    catch (IOException ioException) {
      // Do nothing
    }
    return 0;
  }

  @Override
  public int getCols() {
    try {
      log.append("getCols method called.\n");
    }
    catch (IOException ioException) {
      // Do nothing
    }
    return 0;
  }

  @Override
  public int interConnectivity() {
    try {
      log.append("getInterconnectivity method called.\n");
    }
    catch (IOException ioException) {
      // Do nothing
    }
    return 0;
  }

  @Override
  public boolean getDungeonLabel() {
    try {
      log.append("getDungeonLabel method called.\n");
    }
    catch (IOException ioException) {
      // Do nothing
    }
    return false;
  }

  @Override
  public int getAmount() {
    try {
      log.append("getAmount method called.\n");
    }
    catch (IOException ioException) {
      // Do nothing
    }
    return 0;
  }

  @Override
  public int getNumOtyughs() {
    try {
      log.append("getNumOtyughs method called.\n");
    }
    catch (IOException ioException) {
      // Do nothing
    }
    return 0;
  }

  @Override
  public Map<String, Integer> getDescription() {
    try {
      log.append("getDescription method called.\n");
    }
    catch (IOException ioException) {
      // Do nothing
    }
    return null;
  }

  @Override
  public boolean pickTreasure() {
    try {
      log.append("pickTreasure method called.\n");
    }
    catch (IOException ioException) {
      // Do nothing
    }
    return false;
  }

  @Override
  public boolean pickArrow() {
    try {
      log.append("pickArrow method called.\n");
    }
    catch (IOException ioException) {
      // Do nothing
    }
    return false;
  }

  @Override
  public int shoot(Directions dir, int distance) {
    try {
      log.append("shoot method called.\n");
    }
    catch (IOException ioException) {
      // Do nothing
    }
    return 0;
  }

  @Override
  public boolean makeMove(Directions direction) {
    try {
      log.append("makeMove method called.\n");
    }
    catch (IOException ioException) {
      // Do nothing
    }
    return false;
  }

  @Override
  public int getStart() {
    try {
      log.append("getStart method called.\n");
    }
    catch (IOException ioException) {
      // Do nothing
    }
    return 0;
  }

  @Override
  public int getEnd() {
    try {
      log.append("getEnd method called.\n");
    }
    catch (IOException ioException) {
      // Do nothing
    }
    return 0;
  }

  @Override
  public Dungeon getDungeon() {
    try {
      log.append("getDungeon method called.\n");
    }
    catch (IOException ioException) {
      // Do nothing
    }
    return null;
  }

  @Override
  public boolean detectPit() {
    try {
      log.append("detectPit method called.\n");
    }
    catch (IOException ioException) {
      // Do nothing
    }
    return false;
  }

  @Override
  public Player getPlayer() {
    try {
      log.append("getPlayer method called.\n");
    }
    catch (IOException ioException) {
      // Do nothing
    }
    return null;
  }

  @Override
  public int getNumArrows() {
    try {
      log.append("getNumArrows method called.\n");
    }
    catch (IOException ioException) {
      // Do nothing
    }
    return 0;
  }

  @Override
  public boolean isReachedDest() {
    try {
      log.append("isReachedDest method called.\n");
    }
    catch (IOException ioException) {
      // Do nothing
    }
    return false;
  }

  @Override
  public int getArrowAtCurrentLoc() {
    try {
      log.append("getArrowAtCurrentLoc method called.\n");
    }
    catch (IOException ioException) {
      // Do nothing
    }
    return 0;
  }

  @Override
  public List<Location> getLocationList() {
    try {
      log.append("getLocationList method called.\n");
    }
    catch (IOException ioException) {
      // Do nothing
    }
    return null;
  }

  @Override
  public int getNumPits() {
    try {
      log.append("getNumPits method called.\n");
    }
    catch (IOException ioException) {
      // Do nothing
    }
    return 0;
  }

  @Override
  public int getNumThieves() {
    try {
      log.append("getNumThieves method called.\n");
    }
    catch (IOException ioException) {
      // Do nothing
    }
    return 0;
  }

  @Override
  public List<Integer> getPlayerLocRowColIndex() {
    try {
      log.append("getPlayerLocRowColIndex method called.\n");
    }
    catch (IOException ioException) {
      // Do nothing
    }
    return null;
  }

  @Override
  public Map<Integer, Map<List<Directions>, List<TreasuresTypes>>> getCurrentLocation() {
    try {
      log.append("getCurrentLocation method called.\n");
    }
    catch (IOException ioException) {
      // Do nothing
    }
    return null;
  }

  @Override
  public int detectSmell() {
    try {
      log.append("detectSmell method called.\n");
    }
    catch (IOException ioException) {
      // Do nothing
    }
    return 0;
  }

  @Override
  public boolean isPlayerAlive() {
    try {
      log.append("isPlayerAlive method called.\n");
    }
    catch (IOException ioException) {
      // Do nothing
    }
    return false;
  }

  @Override
  public boolean isKilledByPit() {
    try {
      log.append("isKilledByPit method called.\n");
    }
    catch (IOException ioException) {
      // Do nothing
    }
    return false;
  }

  @Override
  public boolean isTreasureStolen() {
    try {
      log.append("isTreasureStolen method called.\n");
    }
    catch (IOException ioException) {
      // Do nothing
    }
    return false;
  }
}
