package controller.commands;

import model.dungeon.Model;

/**
 * Pick treasure command which picks the treasure from player's current dungeon location.
 */
public class PickTreasure implements ICommand<String> {
  @Override
  public String execute(Model m) {
    if (m.pickTreasure()) {
      return "\nPicked the treasure!\n";
    }
    else {
      return "\nNo treasure to pick!\n";
    }
  }
}
