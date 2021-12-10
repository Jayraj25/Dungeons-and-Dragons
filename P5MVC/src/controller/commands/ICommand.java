package controller.commands;

import model.dungeon.Model;

/**
 * The command interface for the different commands sent through controller.
 */
public interface ICommand<T> {

  /**
   * function which executes the given command.
   * @param m the model on which the commands are to be executed.
   * @return user defined data type return.
   */
  T execute(Model m);
}
