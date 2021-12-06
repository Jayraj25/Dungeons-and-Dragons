package controller.commands;

import model.dungeon.Model;

import java.util.Map;

/**
 * Gives the description of player upon calling this command.
 */
public class PlayerDescription implements ICommand<String> {
  @Override
  public String execute(Model m) {
    Map<String, Integer> temp = m.getDescription();
    StringBuilder res = new StringBuilder();
    res.append("\nThe following are the treasures and arrows the player currently have:\n");
    temp.forEach(
        (key, value) -> {
          if (value != 0) {
            res.append(key).append(": ").append(String.valueOf(value)).append("\n");
          }
        }
    );
    if (res.length() == 71) {
      res.append("Treasure: 0\n").append("Arrows: 0\n");
    }
    else {
      if (!res.toString().contains("Arrows")) {
        res.append("Arrows: 0\n");
      }
    }
    res.append("\n");
    return res.toString();
  }
}
