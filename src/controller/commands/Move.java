package controller.commands;

import model.dungeon.Directions;
import model.dungeon.Model;

/**
 * Move the player in the given direction.
 */
public class Move implements ICommand<String> {

  private final Directions directions;

  /**
   * The move constructor which takes direction as input.
   * @param dir the direction.
   */
  public Move(String dir) {
    if (dir.equalsIgnoreCase("north")) {
      directions = Directions.NORTH;
    }
    else if (dir.equalsIgnoreCase("south")) {
      directions = Directions.SOUTH;
    }
    else if (dir.equalsIgnoreCase("east")) {
      directions = Directions.EAST;
    }
    else if (dir.equalsIgnoreCase("west")) {
      directions = Directions.WEST;
    }
    else {
      throw new IllegalArgumentException("Invalid direction provided\n");
    }
  }

  @Override
  public String execute(Model m) {
    boolean temp = m.makeMove(directions);
    StringBuilder res = new StringBuilder();
    if (temp) {
      if (m.isTreasureStolen()) {
        res.append("Thief encountered and your treasure was stolen!").append("\n");
      }
      res.append("Move made successfully").append("\n");
    }
    else {
      res.append("Cannot move in the given direction\n");
    }
    return res.toString();
  }
}
