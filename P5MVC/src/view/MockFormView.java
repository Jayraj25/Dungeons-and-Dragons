package view;

import controller.Features;
import model.dungeon.Model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Mock view for the form.
 */
public class MockFormView implements DungeonForm {

  private final Appendable log;

  /**
   * Creates the mock form view.
   * @param log the appendable log.
   */
  public MockFormView(Appendable log) {
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
      log.append("getSpecs method was called\n");
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
}
