package controller.commands;

import model.dungeon.Directions;
import model.dungeon.Model;

/**
 * Execute the shoot command given the direction and distance.
 */
public class Shoot implements ICommand<String> {

  private final Directions direction;
  private final int distance;

  /**
   * Creates the constructor for the shoot command.
   * @param dir the direction for shooting.
   * @param dist the distance to shoot.
   */
  public Shoot(String dir, String dist) {
    if (dir.equalsIgnoreCase("north")) {
      direction = Directions.NORTH;
    }
    else if (dir.equalsIgnoreCase("south")) {
      direction = Directions.SOUTH;
    }
    else if (dir.equalsIgnoreCase("east")) {
      direction = Directions.EAST;
    }
    else if (dir.equalsIgnoreCase("west")) {
      direction = Directions.WEST;
    }
    else {
      throw new IllegalArgumentException("Invalid direction provided\n");
    }

    if (Integer.parseInt(dist) < 1 || Integer.parseInt(dist) > 5) {
      throw new IllegalArgumentException("Invalid distance provided\n");
    }
    else {
      this.distance = Integer.parseInt(dist);
    }
  }

  @Override
  public String execute(Model m) {
    int temp = m.shoot(direction, distance);
    if (temp == 1) {
      return "\nMonster slayed!\n";
    }
    else if (temp == 2) {
      return "\nMonster hit successfully!\n";
    }
    else if (temp == 3) {
      return "\nArrow went in dark!\n";
    }
    else {
      return "\nOut of Arrows!!\n";
    }
  }
}
