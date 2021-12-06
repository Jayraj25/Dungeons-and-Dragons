package controller;

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

  void createModel();

  void restartSameGame();

  void restartNewGame();

  void getInformation();
}
