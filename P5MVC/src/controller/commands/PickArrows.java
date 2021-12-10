package controller.commands;

import model.dungeon.Model;

/**
 * Pick arrow command which picks the arrows from player's current dungeon location.
 */
public class PickArrows implements ICommand<String> {
  @Override
  public String execute(Model m) {
    if (m.pickArrow()) {
      return "\nPlayer picked up the arrows\n";
    }
    else {
      return "\nNo Arrows to pick\n";
    }
  }
}
