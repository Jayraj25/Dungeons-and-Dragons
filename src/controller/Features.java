package controller;

import model.dungeon.Directions;
import model.dungeon.Model;

/**
 * 
 */
public interface Features {
  /**
   * Exit the program.
   */
  void exitProgram();

  void playGame(Model m);

  /**
   * Create the dungeon.
   */
  void createModel();

  /**
   * Restart the same game with same specs.
   */
  void restartSameGame();

  /**
   * Generate new game with new specs form.
   */
  void restartNewGame();

  /**
   * Get the specifications' information.
   */
  void getInformation();

  /**
   * Shoot on key press.
   * @param direction direction to shoot
   * @param distance distance to shoot
   */
  void shoot(Directions direction, int distance);

  /**
   * Pick the treasure at current location if available.
   */
  void pickTreasure();

  /**
   * Pick the arrow at current location if available.
   */
  void pickArrow();

  /**
   * Get the description of the player.
   */
  void getDescription();

  /**
   * Move the player in the given direction.
   * @param direction Direction to move
   */
  void move(Directions direction);

  /**
   * Move the player using mouse click.
   * @param x the x coordinate of mouse.
   * @param y the y coordinate of mouse.
   */
  void moveClickListener(int x, int y);
}
