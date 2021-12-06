package controller;

import controller.commands.ICommand;
import controller.commands.Move;
import controller.commands.PickArrows;
import controller.commands.PickTreasure;
import controller.commands.PlayerDescription;
import controller.commands.Shoot;
import model.dungeon.Directions;
import model.dungeon.Model;
import model.dungeon.TreasuresTypes;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

/**
 * The console controller for the dungeon game which allows to play the game and do necessary
 * move operations.
 */
public class DungeonConsoleController implements DungeonController {

  private final Appendable out;
  private final Scanner scan;
  private final Map<String, Function<Scanner, ICommand>> commands;

  /**
   * Constructor for the controller.
   *
   * @param in  the source to read from
   * @param out the target to print to
   */
  public DungeonConsoleController(Readable in, Appendable out) {
    if (in == null || out == null) {
      throw new IllegalArgumentException("Readable and Appendable can't be null");
    }
    this.out = out;
    scan = new Scanner(in);
    commands = new HashMap<>();
    commands.put("1", s -> new Move(scan.next()));
    commands.put("2", s -> new PickTreasure());
    commands.put("3", s -> new PickArrows());
    commands.put("4", s -> new Shoot(scan.next(), scan.next()));
    commands.put("5", s -> new PlayerDescription());
  }

  @Override
  public void playGame(Model m) {
    if (m == null) {
      throw new IllegalArgumentException("Model can't be null");
    }
    try {
      out.append("\nWelcome to the game!\n");
      ICommand c;
      while (!m.isReachedDest() && m.isPlayerAlive()) {
        currentLocationInfo(m);
        out.append(printOptions());
        String in = scan.next();
        if (in.equalsIgnoreCase("q")) {
          break;
        }
        Function<Scanner, ICommand> cmd = commands.getOrDefault(in, null);
        try {
          if (cmd == null) {
            out.append("Unknown command: ").append(in).append("\n");
          } else {
            try {
              c = cmd.apply(scan);
              out.append(c.execute(m).toString());
            }
            catch (IllegalArgumentException ie) {
              out.append("Invalid input: ").append(ie.getMessage()).append("\n");
            }
          }
        }
        catch (IllegalArgumentException ie) {
          out.append(ie.getMessage());
        }
      }
      if (!m.isPlayerAlive()) {
        out.append("\nChomp, chomp, chomp, you are eaten by an Otyugh!\n");
        out.append("Game over!\n");
      }
      if (m.isReachedDest() && m.isPlayerAlive()) {
        out.append("Reached the end!\n");
        out.append("You won the game\n");
        out.append("Game over!\n");
      }
    }
    catch (IOException exp) {
      scan.close();
      throw new IllegalStateException("Append failed", exp);
    }
  }

  private String printOptions() {
    return "1. Press 1 for move (After choosing the option enter the"
            + " direction [east/west/north/south]\n"
            + "2. Press 2 to pick treasure\n"
            + "3. Press 3 to pick arrows\n"
            + "4. Press 4 to shoot (After choosing the option enter direction and distance [1-5])\n"
            + "5. Press 5 for player's description\n"
            + "6. Press q to quit the game\n";
  }

  private void currentLocationInfo(Model m) {
    Map<Integer, Map<List<Directions>, List<TreasuresTypes>>> map = m.getCurrentLocation();
    try {
      for (Map.Entry<Integer, Map<List<Directions>, List<TreasuresTypes>>> m1 : map.entrySet()) {
        Map<List<Directions>, List<TreasuresTypes>> temp = m1.getValue();
        temp.forEach(
            (key1, temp1) -> {
              if (key1.size() != 2) {
                try {
                  out.append("You are in a cave.").append("\n");
                } catch (IOException e) {
                  e.printStackTrace();
                }
              }
              else {
                try {
                  out.append("You are in a tunnel.").append("\n");
                } catch (IOException e) {
                  e.printStackTrace();
                }
              }
            }
        );
        m1.getValue().forEach(
            (key, value) -> {
              try {
                out.append("The available directions to move are: ")
                        .append(key.toString()).append("\n");
                out.append("The available treasures to pick are: ")
                        .append(value.toString()).append("\n");
              } catch (IOException e) {
                e.printStackTrace();
              }
            }
        );
      }
      out.append("The available arrows to pick are: ")
              .append(String.valueOf(m.getArrowAtCurrentLoc())).append("\n").append("\n");
    } catch (IOException e) {
      e.printStackTrace();
    }

    if (m.detectSmell() == 1) {
      try {
        out.append("You smell something terrible nearby!!\n\n");
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    else if (m.detectSmell() == 2) {
      try {
        out.append("You smell something less pungent nearby!!\n\n");
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
