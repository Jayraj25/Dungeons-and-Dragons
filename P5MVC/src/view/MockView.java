package view;

import controller.Features;
import model.dungeon.Directions;
import model.dungeon.Model;
import model.dungeon.TreasuresTypes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Mock the view and use it for testing.
 * Here, when the method is called it appends info into the log,
 * and it is used to verify in testing.
 */
public class MockView implements DungeonView {

  private final Appendable log;

  /**
   * Constructs the mock view which is used for testing.
   * @param log the appendable which is used to maintain log.
   */
  public MockView(Appendable log) {
    this.log = log;
  }

  @Override
  public List<Integer> getSpecs() {
    List<Integer> temp = new ArrayList<>();
    temp.add(4);
    temp.add(5);
    temp.add(1);
    temp.add(1);
    temp.add(1);
    temp.add(1);
    temp.add(1);
    temp.add(1);
    try {
      log.append("getSpecs method was called in view\n");
    } catch (IOException e) {
      // Do nothing
    }
    return temp;
  }

  @Override
  public void setFeatures(Features f) {
    try {
      log.append("setFeatures method was called in view\n");
    } catch (IOException e) {
      // Do nothing
    }
  }

  @Override
  public void hideWindow() {
    try {
      log.append("hideWindow method was called in view\n");
    } catch (IOException e) {
      // Do nothing
    }
  }

  @Override
  public void resetFocus() {
    try {
      log.append("resetFocus method was called in view\n");
    } catch (IOException e) {
      // Do nothing
    }
  }

  @Override
  public void createDungeon(Model model) {
    try {
      log.append("createDungeon method was called in view\n");
    } catch (IOException e) {
      // Do nothing
    }
  }

  @Override
  public void display(Map<Integer, Map<List<Directions>, List<TreasuresTypes>>> currentLocation,
                      int arrowAtCurrentLoc, int smell, boolean status) {
    try {
      log.append("display method was called in view\n");
    } catch (IOException e) {
      // Do nothing
    }
  }

  @Override
  public void getDescription(Map<String, Integer> description) {
    try {
      log.append("getDescription method was called in view\n");
    } catch (IOException e) {
      // Do nothing
    }
  }

  @Override
  public void addLabel(String s) {
    try {
      log.append("addLabel method was called in view\n");
    } catch (IOException e) {
      // Do nothing
    }
  }

  @Override
  public void displayInfo(int rows, int cols, int interConnectivity, String type,
                          int amount, int numOtyughs) {
    try {
      log.append("displayInfo method was called in view\n");
    } catch (IOException e) {
      // Do nothing
    }
  }

  @Override
  public void refresh() {
    try {
      log.append("refresh method was called in view\n");
    } catch (IOException e) {
      // Do nothing
    }
  }

  @Override
  public void shootInfo(int res) {
    try {
      log.append("shootInfo method was called in view\n");
    } catch (IOException e) {
      // Do nothing
    }
  }

  @Override
  public void displayWinInfo() {
    try {
      log.append("displayWinInfo method was called in view\n");
    } catch (IOException e) {
      // Do nothing
    }
  }

  @Override
  public void displayLoseInfo() {
    try {
      log.append("displayLoseInfo method was called in view\n");
    } catch (IOException e) {
      // Do nothing
    }
  }

  @Override
  public void displayPitInfo() {
    try {
      log.append("displayPitInfo method was called in view\n");
    } catch (IOException e) {
      // Do nothing
    }
  }

  @Override
  public void displayLoseInfoByPit() {
    try {
      log.append("displayLoseInfoByPit method was called in view\n");
    } catch (IOException e) {
      // Do nothing
    }
  }

  @Override
  public void exitGame() {
    try {
      log.append("exitGame method was called in view\n");
    } catch (IOException e) {
      // Do nothing
    }
  }
}
