package view;

import controller.Features;
import model.dungeon.Model;

import java.util.List;

/**
 * The initial view form to get the dungeon specifications.
 */
public interface DungeonForm {
  /**
   * Get the dungeon specifications.
   * @return list of specifications as integer.
   */
  List<Integer> getSpecs();

  /**
   * make the action listener for different buttons.
   * @param f set of all features.
   */
  void setFeatures(Features f);

  /**
   * Hide the window.
   */
  void hideWindow();

  /**
   * Reset the focus on the appropriate part of the view that has the keyboard
   * listener attached to it, so that keyboard events will still flow through.
   */
  void resetFocus();

  /**
   * create the dungeon.
   * @param model the model.
   */
  void createDungeon(Model model);
}
