package controller;

import model.dungeon.Model;

/**
 * Represents a Controller for Adventure game: handle user moves by executing them using the dungeon
 * model convey move outcomes to the user in some form.
 */
public interface DungeonController {

  /**
   * Execute a single adventure game given a dungeon Model. When the game is over,
   * the playGame method ends.
   *
   * @param m a non-null Dungeon Model
   */
  void playGame(Model m);
}
